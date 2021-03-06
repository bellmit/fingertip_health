package com.jqsoft.fingertip_health.di.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.google.gson.Gson;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.AddressAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonLocationBean;
import com.jqsoft.fingertip_health.util.DataConversionUtils;
import com.jqsoft.fingertip_health.util.MapUtil;
import com.jqsoft.fingertip_health.util.OnItemClickLisenter;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MainMapActivity extends AppCompatActivity {


    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;

    private MapView mMapView;
    private ImageView mIvBack;
    private ImageView mIvSearch;
    private ImageView mIvLocation;
    private ImageView mIvCenterLocation;
    private TextView mBtSend;
    private RecyclerView mRecyclerView;
    private AddressAdapter mAddressAdapter;
    private List<PoiItem> mList;
    private PoiItem userSelectPoiItem;

    private AMap mAMap;
    private Marker mMarker, mLocationGpsMarker, mSelectByListMarker;
    private UiSettings mUiSettings;
    private PoiSearch mPoiSearch;
    private PoiSearch.Query mQuery;
    private boolean isSearchData = false;//????????????????????????
    private int searchAllPageNum;//Poi???????????????????????????????????????????????????
    private int searchNowPageNum;//??????poi????????????
    private float zoom = 14;//??????????????????

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = new AMapLocationClientOption();
    private AMapLocation location;
    private AMapLocationListener mAMapLocationListener;

    private onPoiSearchLintener mOnPoiSearchListener;
    private View.OnClickListener mOnClickListener;
    private GeocodeSearch.OnGeocodeSearchListener mOnGeocodeSearchListener;

    private Gson gson;

    private ObjectAnimator mTransAnimator;//????????????????????????

    private static final int SEARCHREQUESTCODE = 1001;

    private LinearLayout ll_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        initView();
        initDatas(savedInstanceState);
        initListener();


        startLocation();

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data && SEARCHREQUESTCODE == requestCode) {
         /*   try {
                userSelectPoiItem = (PoiItem) data.getParcelableExtra(DatasKey.SEARCH_INFO);
                if (null != userSelectPoiItem) {
                    isSearchData = false;
                    doSearchQuery(true, "", location.getCity(), userSelectPoiItem.getLatLonPoint());
                    moveMapCamera(userSelectPoiItem.getLatLonPoint().getLatitude(), userSelectPoiItem.getLatLonPoint().getLongitude());
//                    refleshMark(userSelectPoiItem.getLatLonPoint().getLatitude(), userSelectPoiItem.getLatLonPoint().getLongitude());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }*/


            try {
                Tip userSelectPoiItem = data.getParcelableExtra("tip");
             //   userSelectPoiItem = (PoiItem) data.getParcelableExtra(DatasKey.SEARCH_INFO);
              //  userSelectPoiItem.getLatLonPoint()=tip.getPoint();
                if (null != userSelectPoiItem) {
                    isSearchData = false;
                    doSearchQuery(true, "", location.getCity(), userSelectPoiItem.getPoint());
                    moveMapCamera(userSelectPoiItem.getPoint().getLatitude(), userSelectPoiItem.getPoint().getLongitude());
                    getAddressInfoByLatLong(userSelectPoiItem.getPoint().getLatitude(), userSelectPoiItem.getPoint().getLongitude());

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocation();
        mMapView.onDestroy();
        if (null != mPoiSearch) {
            mPoiSearch = null;
        }
        if (null != gson) {
            gson = null;
        }
        if (null != locationClient) {
            locationClient.onDestroy();
        }
    }

    private void initView() {

        ll_back=(LinearLayout)findViewById(R.id.ll_back);
        mMapView = (MapView) findViewById(R.id.map);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvSearch = (ImageView) findViewById(R.id.iv_search);
        mIvLocation = (ImageView) findViewById(R.id.iv_location);
        mIvCenterLocation = (ImageView) findViewById(R.id.iv_center_location);
        mBtSend = (TextView) findViewById(R.id.bt_send);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

    }

    private void initListener() {
        //???????????????????????????
        mAMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                if (null != location && null != cameraPosition && isSearchData) {
                    mIvLocation.setImageResource(R.mipmap.location_gps_black);
                    zoom = cameraPosition.zoom;
                    if (null != mSelectByListMarker) {
                        mSelectByListMarker.setVisible(false);
                    }
                    getAddressInfoByLatLong(cameraPosition.target.latitude, cameraPosition.target.longitude);
                    startTransAnimator();
//                    doSearchQuery(true, "", location.getCity(), new LatLonPoint(cameraPosition.target.latitude, cameraPosition.target.longitude));
                }
                if (!isSearchData) {
                    isSearchData = true;
                }
            }

            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }
        });

        //???????????????????????????
        mAMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                isSearchData = true;
            }
        });

        //Poi???????????????
        mOnPoiSearchListener = new onPoiSearchLintener();

        //????????????????????????
        mOnGeocodeSearchListener = new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                if (i == 1000) {
                    if (regeocodeResult != null) {
                        userSelectPoiItem = DataConversionUtils.changeToPoiItem(regeocodeResult);
                        if (null != mList) {
                            mList.clear();
                        }
                        mList.addAll(regeocodeResult.getRegeocodeAddress().getPois());
                        if (null != userSelectPoiItem) {
                            mList.add(0, userSelectPoiItem);
                        }
                        mAddressAdapter.setList(mList);
                        mRecyclerView.smoothScrollToPosition(0);
                    }
                }

            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        };

        //gps???????????????
        mAMapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation loc) {
                try {
                    if (null != loc) {
                        stopLocation();
                        if (loc.getErrorCode() == 0) {//??????????????????amapLocation?????????????????????
                            location = loc;
                     //       SPUtils.putString(MainMapActivity.this, DatasKey.LOCATION_INFO, gson.toJson(location));
                            doWhenLocationSucess();
                        } else {
                            //???????????????????????????ErrCode????????????????????????????????????????????????errInfo???????????????????????????????????????
                            Log.e("AmapError", "location Error, ErrCode:"
                                    + loc.getErrorCode() + ", errInfo:"
                                    + loc.getErrorInfo());

                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };

        //recycleview???????????????
        mAddressAdapter.setOnItemClickLisenter(new OnItemClickLisenter() {
            @Override
            public void onItemClick(int position) {
                try {
                    isSearchData = false;
                    mIvLocation.setImageResource(R.mipmap.location_gps_black);
                    moveMapCamera(mList.get(position).getLatLonPoint().getLatitude(), mList.get(position).getLatLonPoint().getLongitude());
                    refleshSelectByListMark(mList.get(position).getLatLonPoint().getLatitude(), mList.get(position).getLatLonPoint().getLongitude());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //?????????????????????
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.ll_back:
                        finish();
                        break;
                    case R.id.iv_search:
//                        Toast.makeText(MainMapActivity.this, "??????", Toast.LENGTH_SHORT).show();
                     //   startActivityForResult(new Intent(MainMapActivity.this, SearchMapActivity.class), SEARCHREQUESTCODE);

                        startActivityForResult(new Intent(MainMapActivity.this, SearchResultActivity.class), SEARCHREQUESTCODE);
                        break;
                    case R.id.iv_location:
//                        Toast.makeText(MainMapActivity.this, "??????", Toast.LENGTH_SHORT).show();
                        mIvLocation.setImageResource(R.mipmap.location_gps_green);
                        if (null != mSelectByListMarker) {
                            mSelectByListMarker.setVisible(false);
                        }
                        if (null == location) {
                            startLocation();
                        } else {
                            doWhenLocationSucess();
                        }
                        break;
                    case R.id.bt_send:
                        if (null != mList && 0 < mList.size() && null != mAddressAdapter) {
                            int position = mAddressAdapter.getSelectPositon();
                            if (position < 0) {
                                position = 0;
                            } else if (position > mList.size()) {
                                position = mList.size();
                            }
                            PoiItem poiItem = mList.get(position);

                            String slocation=poiItem.getSnippet();

                            Intent intent = getIntent();
                            intent.putExtra("location",slocation);
                            //address,lat,lng
                            com.amap.api.maps.model.LatLng latLng = new com.amap.api.maps.model.LatLng(poiItem.getLatLonPoint().getLatitude(), poiItem.getLatLonPoint().getLongitude());
                            com.amap.api.maps.model.LatLng selectedLatLng = MapUtil.convertGcjCoordinateToBaiduCoordinate(latLng);
//                            PersonLocationBean plb = getPersonLocationBeanFromLatLngAddress(selectedLatLng.latitude,
//                                    selectedLatLng.longitude, poiItem.getSnippet());
                            PersonLocationBean plb = getPersonLocationBeanFromLatLngAddress(poiItem.getLatLonPoint().getLatitude(),
                                    poiItem.getLatLonPoint().getLongitude(), poiItem.toString());
                            intent.putExtra(Constants.SELECTED_MAP_LOCATION_KEY, plb);

                            MainMapActivity.this.setResult(10,intent);
                            MainMapActivity.this.finish();


                       //     Toast.makeText(MainMapActivity.this, "?????????" + poiItem.getTitle() + "  " + poiItem.getSnippet() + "  " + "?????????" + poiItem.getLatLonPoint().getLatitude() + "  " + "?????????" + poiItem.getLatLonPoint().getLongitude(), Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
            }
        };

        mIvBack.setOnClickListener(mOnClickListener);
        ll_back.setOnClickListener(mOnClickListener);
        mIvSearch.setOnClickListener(mOnClickListener);
        mIvLocation.setOnClickListener(mOnClickListener);
        mBtSend.setOnClickListener(mOnClickListener);

    }

    private PersonLocationBean getPersonLocationBeanFromLatLngAddress(double latitude, double longitude, String address){
        PersonLocationBean plb = new PersonLocationBean();
        plb.setLat(Util.getCanonicalLongitudeOrLatitude(String.valueOf(latitude)));
        plb.setLng(Util.getCanonicalLongitudeOrLatitude(String.valueOf(longitude)));
        plb.setAddress(address);
        return plb;
    }



    private void initDatas(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);// ?????????????????????
        mAMap = mMapView.getMap();

        mUiSettings = mAMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(false);//???????????????????????????????????????
        mUiSettings.setMyLocationButtonEnabled(false); // ?????????????????????????????????
        mUiSettings.setScaleControlsEnabled(true);//????????????????????????
        mAMap.setMyLocationEnabled(true);// ???????????????????????????????????????

        mList = new ArrayList<>();
        mAddressAdapter = new AddressAdapter(this, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAddressAdapter);

        gson = new Gson();

        mTransAnimator = ObjectAnimator.ofFloat(mIvCenterLocation, "translationY", 0f, -80f, 0f);
        mTransAnimator.setDuration(800);
//        mTransAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    /**
     * ???????????????
     */
    private void initLocation() {
        if (null == locationClient) {
            //?????????client
            locationClient = new AMapLocationClient(this.getApplicationContext());
            //??????????????????
            locationClient.setLocationOption(getDefaultOption());
            // ??????????????????
            locationClient.setLocationListener(mAMapLocationListener);
        }
    }

    /**
     * ?????????????????????
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//????????????????????????????????????????????????????????????????????????????????????????????????????????????
        mOption.setGpsFirst(false);//?????????????????????gps??????????????????????????????????????????????????????
        mOption.setHttpTimeOut(30000);//???????????????????????????????????????????????????30?????????????????????????????????
        mOption.setInterval(2000);//???????????????????????????????????????2???
        mOption.setNeedAddress(true);//????????????????????????????????????????????????????????????true
        mOption.setOnceLocation(false);//?????????????????????????????????????????????false
        mOption.setOnceLocationLatest(false);//???????????????????????????wifi??????????????????false.???????????????true,?????????????????????????????????????????????????????????
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//????????? ????????????????????????????????????HTTP??????HTTPS????????????HTTP
        mOption.setSensorEnable(false);//????????????????????????????????????????????????false
        mOption.setWifiScan(true); //???????????????????????????wifi??????????????????true??????????????????false??????????????????????????????????????????????????????????????????????????????????????????????????????
        mOption.setMockEnable(true);//??????????????????????????????????????????setMockEnable(true);??????????????????????????????
        return mOption;
    }

    /**
     * ????????????
     */
    public void startLocation() {
        initLocation();
        // ??????????????????
        locationClient.setLocationOption(locationOption);
        // ????????????
        locationClient.startLocation();




    }

    /**
     * ????????????
     */
    private void stopLocation() {
        if (null != locationClient) {
            locationClient.stopLocation();
        }
    }

    /**
     * ?????????????????????????????????
     */
    private void doWhenLocationSucess() {
        isSearchData = false;
        String  lng= getIntent().getStringExtra("SocialDeatailLocatLng");
        String  Lat=getIntent().getStringExtra("SocialDeatailLocatLat");
//
        if (!lng.equals("null")&&!Lat.equals("null")&&!TextUtils.isEmpty(lng)&&!TextUtils.isEmpty(Lat)){
//            getAddressInfoByLatLong(Double.valueOf(Lat),  Double.valueOf(lng));
            doSearchQuery(true, "", location.getCity(), new LatLonPoint(Double.valueOf(Lat), Double.valueOf(lng)));
            moveMapCamera(Double.valueOf(Lat), Double.valueOf(lng));
            refleshLocationMark(Double.valueOf(Lat), Double.valueOf(lng));

        }else {

            userSelectPoiItem = DataConversionUtils.changeToPoiItem(location);
            doSearchQuery(true, "", location.getCity(), new LatLonPoint(location.getLatitude(), location.getLongitude()));
            moveMapCamera(location.getLatitude(), location.getLongitude());
            refleshLocationMark(location.getLatitude(), location.getLongitude());

        }




    }


    /**
     * ????????????
     */
    private void startTransAnimator() {
        if (null != mTransAnimator && !mTransAnimator.isRunning()) {
            mTransAnimator.start();
        }
    }

    /**
     * ????????????????????????????????????(??????moveCamera????????????????????????)
     *
     * @param latitude
     * @param longitude
     */
    private void moveMapCamera(double latitude, double longitude) {
        if (null != mAMap) {
            mAMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), zoom));
        }
    }

    /**
     * ???????????????????????????
     *
     * @param latitude
     * @param longitude
     */
    private void refleshMark(double latitude, double longitude) {
        if (mMarker == null) {
            mMarker = mAMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), android.R.color.transparent)))
                    .draggable(true));
        }
        mMarker.setPosition(new LatLng(latitude, longitude));
        mAMap.invalidate();

    }

    /**
     * ?????????????????????gps????????????
     *
     * @param latitude
     * @param longitude
     */
    private void refleshLocationMark(double latitude, double longitude) {
        if (mLocationGpsMarker == null) {
            mLocationGpsMarker = mAMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), R.mipmap.location_blue)))
                    .draggable(true));
        }
        mLocationGpsMarker.setPosition(new LatLng(latitude, longitude));
        mAMap.invalidate();

    }

    /**
     * ??????????????????????????????????????????
     *
     * @param latitude
     * @param longitude
     */
    private void refleshSelectByListMark(double latitude, double longitude) {
        if (mSelectByListMarker == null) {
            mSelectByListMarker = mAMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(), R.mipmap.location_red)))
                    .draggable(true));
        }
        mSelectByListMarker.setPosition(new LatLng(latitude, longitude));
        if (!mSelectByListMarker.isVisible()) {
            mSelectByListMarker.setVisible(true);
        }
        mAMap.invalidate();

    }

    /**
     * ????????????poi??????
     *
     * @param isReflsh ?????????????????????
     * @param keyWord
     * @param city
     * @param lpTemp
     */
    protected void doSearchQuery(boolean isReflsh, String keyWord, String city, LatLonPoint lpTemp) {
        mQuery = new PoiSearch.Query(keyWord, "", city);//????????????????????????????????????????????????????????????poi????????????????????????????????????poi??????????????????????????????????????????
        mQuery.setPageSize(30);// ?????????????????????????????????poiitem
        if (isReflsh) {
            searchNowPageNum = 0;
        } else {
            searchNowPageNum++;
        }
        if (searchNowPageNum > searchAllPageNum) {
            return;
        }
        mQuery.setPageNum(searchNowPageNum);// ??????????????????


        mPoiSearch = new PoiSearch(this, mQuery);
        mOnPoiSearchListener.IsReflsh(isReflsh);
        mPoiSearch.setOnPoiSearchListener(mOnPoiSearchListener);
        if (lpTemp != null) {
            mPoiSearch.setBound(new PoiSearch.SearchBound(lpTemp, 10000, true));//?????????????????????-----?????????????????????-----????????????????????????
        }
        mPoiSearch.searchPOIAsyn();// ????????????
    }


    /**
     * ???????????????????????????????????????????????????????????????
     *
     * @param latitude
     * @param longitude
     */
    private void getAddressInfoByLatLong(double latitude, double longitude) {
        GeocodeSearch geocodeSearch = new GeocodeSearch(this);
        /*
        point - ?????????????????????????????????????????????
        radius - ???????????????????????????1000???????????????1-3000???????????????
        latLonType - ?????????????????????????????????GPS???????????????????????? ????????????RegeocodeQuery.setLatLonType(String)
        */
        RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(latitude, longitude), 3000, GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(query);
        geocodeSearch.setOnGeocodeSearchListener(mOnGeocodeSearchListener);
    }


    //??????Poi????????????????????????????????????????????????????????????
    class onPoiSearchLintener implements PoiSearch.OnPoiSearchListener {
        private boolean isReflsh;//?????????????????????????????????????????????

        public void IsReflsh(boolean isReflsh) {
            this.isReflsh = isReflsh;
        }

        @Override
        public void onPoiSearched(PoiResult result, int i) {
            if (i == 1000) {
                if (result != null && result.getQuery() != null) {// ??????poi?????????
                    searchAllPageNum = result.getPageCount();
                    if (result.getQuery().equals(mQuery)) {// ??????????????????
                        if (isReflsh && null != mList) {
                            mList.clear();
                            if (null != userSelectPoiItem) {
                                mList.add(0, userSelectPoiItem);
                            }
                        }
                        mList.addAll(result.getPois());// ??????????????????poiitem????????????????????????0??????
                        if (null != mAddressAdapter) {
                            mAddressAdapter.setList(mList);
                            mRecyclerView.smoothScrollToPosition(0);
                        }
                    }
                }
            }
        }

        @Override
        public void onPoiItemSearched(PoiItem poiItem, int i) {

        }
    }

}
