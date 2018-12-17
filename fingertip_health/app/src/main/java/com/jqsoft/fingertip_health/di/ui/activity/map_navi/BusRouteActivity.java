package com.jqsoft.fingertip_health.di.ui.activity.map_navi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.overlay.BusRouteOverlay;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.RouteSearch.BusRouteQuery;
import com.amap.api.services.route.RouteSearch.OnRouteSearchListener;
import com.amap.api.services.route.WalkRouteResult;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.map_navi.BusResultListAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.util.AMapUtil;
import com.jqsoft.fingertip_health.util.GetCurrentLocationUtil;
import com.jqsoft.fingertip_health.util.ToastUtil;
import com.jqsoft.fingertip_health.util.Util;

/**
 * 公交出行路线规划 实现
 */
public class BusRouteActivity extends AbstractActivity implements AMap.OnMapClickListener,
		AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.InfoWindowAdapter, OnRouteSearchListener {
	private AMap aMap;
	private MapView mapView;
	private Context mContext;
	private RouteSearch mRouteSearch;
	private BusRouteResult mBusRouteResult;
	private LatLonPoint mStartPoint;//起点，116.335891,39.942295
	private LatLonPoint mEndPoint;//终点，116.481288,39.995576
	private String mCurrentCityName = Constants.EMPTY_STRING;
	private final int ROUTE_TYPE_BUS = 1;

	BusRouteOverlay busRouteOverlay;
	
	private LinearLayout mBusResultLayout;
	private RelativeLayout mHeadLayout;
	private ListView mBusResultList;
	private RelativeLayout mBottomLayout;
	private TextView mRotueTimeDes, mRouteDetailDes;

	private ProgressDialog progDialog = null;// 搜索时进度条

	@Override
	protected int getLayoutId() {
		return R.layout.route_activity;
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initView() {
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setToolBar(toolbar, Constants.EMPTY_STRING);

	}

	@Override
	protected void loadData() {

	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
//		setContentView(R.layout.route_activity);
		
		mContext = this.getApplicationContext();
		mapView = (MapView) findViewById(R.id.route_map);
		mapView.onCreate(bundle);// 此方法必须重写
		getIntentData();
		init();
//		setFromAndToMarker();
		
//		mapView.setVisibility(View.GONE);
		mBusResultLayout.setVisibility(View.VISIBLE);

		getCurrentLocationAndSearchRoute(ROUTE_TYPE_BUS, RouteSearch.BusDefault);
//		searchRouteResult(ROUTE_TYPE_BUS, RouteSearch.BusDefault);
	}

	private void getIntentData(){
		String dstLatitude = getDeliveredStringByKey(Constants.DST_LATITIDE_KEY);
		String dstLongitude = getDeliveredStringByKey(Constants.DST_LONGITUDE_KEY);
		double canonicalizedLat = Util.getDoubleValueFromString(Util.getCanonicalLongitudeOrLatitude(dstLatitude));
		double canonicalizedLng = Util.getDoubleValueFromString(Util.getCanonicalLongitudeOrLatitude(dstLongitude));
		mEndPoint=new LatLonPoint(canonicalizedLat, canonicalizedLng);
	}

	private void setFromAndToMarker() {
		aMap.addMarker(new MarkerOptions()
		.position(AMapUtil.convertToLatLng2d(mStartPoint))
//		.position(AMapUtil.convertToLatLng(mStartPoint))
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.start)));
		aMap.addMarker(new MarkerOptions()
		.position(AMapUtil.convertToLatLng2d(mEndPoint))
//		.position(AMapUtil.convertToLatLng(mEndPoint))
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.end)));
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();	
		}
		registerListener();
		mRouteSearch = new RouteSearch(this);
		mRouteSearch.setRouteSearchListener(this);

		mHeadLayout = (RelativeLayout) findViewById(R.id.routemap_header);
//		mHeadLayout.setVisibility(View.GONE);
		mBottomLayout = (RelativeLayout) findViewById(R.id.bottom_layout);
		mRotueTimeDes = (TextView) findViewById(R.id.firstline);
		mRouteDetailDes = (TextView) findViewById(R.id.secondline);

		mBusResultLayout = (LinearLayout) findViewById(R.id.bus_result);
		mBusResultList = (ListView) findViewById(R.id.bus_result_list);
		mBusResultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				final BusPath busPath = mBusRouteResult.getPaths()
						.get(position);
				if (busRouteOverlay!=null){
					busRouteOverlay.removeFromMap();
				}
//				aMap.clear();
				busRouteOverlay = new BusRouteOverlay(
						mContext, aMap, busPath,
						mBusRouteResult.getStartPos(),
						mBusRouteResult.getTargetPos());
				busRouteOverlay.setNodeIconVisibility(true);//设置节点marker是否显示
//				busRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
//				busRouteOverlay.removeFromMap();
				busRouteOverlay.addToMap();
				busRouteOverlay.zoomToSpan();
				mBottomLayout.setVisibility(View.VISIBLE);
				int dis = (int) busPath.getDistance();
				int dur = (int) busPath.getDuration();
				String des = AMapUtil.getFriendlyTime(dur)+"("+AMapUtil.getFriendlyLength(dis)+")";
				mRotueTimeDes.setText(des);
				mRouteDetailDes.setVisibility(View.GONE);
//				int taxiCost = (int) mBusRouteResult.getTaxiCost();
//				mRouteDetailDes.setText("坐车约"+taxiCost+"元");
////				mRouteDetailDes.setText("打车约"+taxiCost+"元");
				mBottomLayout.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
//						Intent intent = new Intent(mContext,
//								BusRouteDetailActivity.class);
//						intent.putExtra("drive_path", busPath);
//						intent.putExtra("drive_result",
//								mBusRouteResult);
//						startActivity(intent);

						Intent intent = new Intent(mContext,
												BusRouteDetailActivity.class);
						intent.putExtra("bus_path", busPath);
						intent.putExtra("bus_result", mBusRouteResult);
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);

					}
				});
			}
		});

	}

	private void clickFirstResult(){
		int pos = 0;
		mBusResultList.performItemClick(mBusResultList.getChildAt(pos), pos, mBusResultList.getItemIdAtPosition(pos));
	}

	/**
	 * 注册监听
	 */
	private void registerListener() {
		aMap.setOnMapClickListener(BusRouteActivity.this);
		aMap.setOnMarkerClickListener(BusRouteActivity.this);
		aMap.setOnInfoWindowClickListener(BusRouteActivity.this);
		aMap.setInfoWindowAdapter(BusRouteActivity.this);
		
	}

	@Override
	public View getInfoContents(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getInfoWindow(Marker arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		
	}

	public void getCurrentLocationAndSearchRoute(final int routeType, final int mode){
		if (mEndPoint == null) {
			ToastUtil.show(mContext, "终点未设置");
			return;
		}

		if (mStartPoint == null) {
			ToastUtil.show(mContext, "正在定位当前位置...");
			final GetCurrentLocationUtil gclu = new GetCurrentLocationUtil(this, new GetCurrentLocationUtil.GetCurrentLocationCallback() {
				@Override
				public void onGetCurrentLocationSuccess(AMapLocation aMapLocation) {
					mCurrentCityName=aMapLocation.getCity();
					com.amap.api.maps.model.LatLng latLng = new com.amap.api.maps.model.LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
					mStartPoint=new LatLonPoint(latLng.latitude, latLng.longitude);
					setFromAndToMarker();
					searchRouteResult(routeType, mode);
				}

				@Override
				public void onGetCurrentLocationFailure() {
					showError();
				}
			});
			gclu.startGetCurrentLocation();
			return;
		}
		searchRouteResult(routeType, mode);
	}

	public void showError(){
		Util.showToast(this, "获取当前位置失败");
	}



	/**
	 * 开始搜索路径规划方案
	 */
	public void searchRouteResult(int routeType, int mode) {
//		if (mStartPoint == null) {
//			ToastUtil.show(mContext, "正在定位当前位置...");
//			return;
//		}
//		if (mEndPoint == null) {
//			ToastUtil.show(mContext, "终点未设置");
//			return;
//		}
		showProgressDialog();
		final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
				mStartPoint, mEndPoint);
		if (routeType == ROUTE_TYPE_BUS) {// 公交路径规划
			BusRouteQuery query = new BusRouteQuery(fromAndTo, mode,
					mCurrentCityName, 0);// 第一个参数表示路径规划的起点和终点，第二个参数表示公交查询模式，第三个参数表示公交查询城市区号，第四个参数表示是否计算夜班车，0表示不计算
			mRouteSearch.calculateBusRouteAsyn(query);// 异步路径规划公交模式查询
		}
	}

	/**
	 * 规划路线结果回调方法
     */
	@Override
	public void onBusRouteSearched(BusRouteResult result, int errorCode) {
		dissmissProgressDialog();
		aMap.clear();// 清理地图上的所有覆盖物
		if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
			if (result != null && result.getPaths() != null) {
				if (result.getPaths().size() > 0) {
					mBusRouteResult = result;
					BusResultListAdapter mBusResultListAdapter = new BusResultListAdapter(mContext, mBusRouteResult);
					mBusResultList.setAdapter(mBusResultListAdapter);
					clickFirstResult();
				} else if (result != null && result.getPaths() == null) {
					ToastUtil.show(mContext, R.string.no_result);
				}
			} else {
				ToastUtil.show(mContext, R.string.no_result);
			}
		} else {
			ToastUtil.showerror(this.getApplicationContext(), errorCode);
		}
	}

	@Override
	public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
		
	}

	@Override
	public void onWalkRouteSearched(WalkRouteResult result, int errorCode) {
		
	}
	

	/**
	 * 显示进度框
	 */
	private void showProgressDialog() {
		Util.showGifProgressDialog(this);
//		if (progDialog == null)
//			progDialog = new ProgressDialog(this);
//		    progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		    progDialog.setIndeterminate(false);
//		    progDialog.setCancelable(true);
//		    progDialog.setMessage("正在搜索");
//		    progDialog.show();
	    }

	/**
	 * 隐藏进度框
	 */
	private void dissmissProgressDialog() {
		Util.hideGifProgressDialog(this);
//		if (progDialog != null) {
//			progDialog.dismiss();
//		}
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	@Override
	public void onRideRouteSearched(RideRouteResult arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
}

