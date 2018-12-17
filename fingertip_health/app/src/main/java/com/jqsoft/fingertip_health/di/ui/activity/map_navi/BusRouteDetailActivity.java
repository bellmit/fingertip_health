package com.jqsoft.fingertip_health.di.ui.activity.map_navi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.overlay.BusRouteOverlay;
import com.amap.api.services.route.BusPath;
import com.amap.api.services.route.BusRouteResult;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.map_navi.BusSegmentListAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.util.AMapUtil;

import java.util.concurrent.TimeUnit;

import rx.Observer;

public class BusRouteDetailActivity extends Activity implements AMap.OnMapLoadedListener,
		AMap.OnMapClickListener, AMap.InfoWindowAdapter, AMap.OnInfoWindowClickListener, AMap.OnMarkerClickListener {
	private AMap aMap;
	private MapView mapView;
	private BusPath mBuspath;
	private BusRouteResult mBusRouteResult;
	private TextView mTitle, mTitleBusRoute, mDesBusRoute;
	private ListView mBusSegmentList;
	private BusSegmentListAdapter mBusSegmentListAdapter;
	private LinearLayout mBusMap, mBuspathview;
	private BusRouteOverlay mBusrouteOverlay;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route_detail);
		mapView = (MapView) findViewById(R.id.route_map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		getIntentData();
		init();
	}

	private void getIntentData() {
		Intent intent = getIntent();
		if (intent != null) {
			mBuspath = intent.getParcelableExtra("bus_path");
			mBusRouteResult = intent.getParcelableExtra("bus_result");
		}
	}

	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();	
		}
		registerListener();
		
		mTitle = (TextView) findViewById(R.id.title_center);
		mTitle.setText("公交路线详情");
		mTitleBusRoute = (TextView) findViewById(R.id.firstline);
		mDesBusRoute = (TextView) findViewById(R.id.secondline);

		initBackListener();

		String dur = AMapUtil.getFriendlyTime((int) mBuspath.getDuration());
		String dis = AMapUtil.getFriendlyLength((int) mBuspath.getDistance());
		mTitleBusRoute.setText(dur + "(" + dis + ")");
//		int taxiCost = (int) mBusRouteResult.getTaxiCost();
//		mDesBusRoute.setText("坐车约"+taxiCost+"元");
////		mDesBusRoute.setText("打车约"+taxiCost+"元");
		mDesBusRoute.setVisibility(View.GONE);
		mBusMap = (LinearLayout)findViewById(R.id.title_map);
		mBusMap.setVisibility(View.GONE);
		mBuspathview = (LinearLayout)findViewById(R.id.bus_path);
		configureListView();

		onShowOverlayOnMap(null);
	}

	private void initBackListener(){
		View backView = findViewById(R.id.title_back);
		RxView.clicks(backView)
				.throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
				.subscribe(new Observer<Object>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onNext(Object o) {
						BusRouteDetailActivity.this.finish();
					}
				});
	}

	private void registerListener() {
		aMap.setOnMapLoadedListener(this);
		aMap.setOnMapClickListener(this);
		aMap.setOnMarkerClickListener(this);
		aMap.setOnInfoWindowClickListener(this);
		aMap.setInfoWindowAdapter(this);
	}

	private void configureListView() {
		mBusSegmentList = (ListView) findViewById(R.id.bus_segment_list);
		mBusSegmentListAdapter = new BusSegmentListAdapter(
				this.getApplicationContext(), mBuspath.getSteps());
		mBusSegmentList.setAdapter(mBusSegmentListAdapter);
		
	}
	
	public void onBackClick(View view) {
		this.finish();
	}
	
	public void onShowOverlayOnMap(View view) {
//		mBuspathview.setVisibility(View.GONE);
//		mBusMap.setVisibility(View.GONE);
		mapView.setVisibility(View.VISIBLE);
		aMap.clear();// 清理地图上的所有覆盖物
		mBusrouteOverlay = new BusRouteOverlay(this, aMap,
				mBuspath, mBusRouteResult.getStartPos(),
				mBusRouteResult.getTargetPos());
		mBusrouteOverlay.removeFromMap();

	}

	@Override
	public void onMapLoaded() {
		if (mBusrouteOverlay != null) {
			mBusrouteOverlay.addToMap();
			mBusrouteOverlay.zoomToSpan();
		}
	}


	@Override
	public View getInfoWindow(com.amap.api.maps2d.model.Marker marker) {
		return null;
	}

	@Override
	public View getInfoContents(com.amap.api.maps2d.model.Marker marker) {
		return null;
	}

	@Override
	public void onInfoWindowClick(com.amap.api.maps2d.model.Marker marker) {

	}

	@Override
	public void onMapClick(com.amap.api.maps2d.model.LatLng latLng) {

	}

	@Override
	public boolean onMarkerClick(com.amap.api.maps2d.model.Marker marker) {
		return false;
	}
}
