package com.jqsoft.fingertip_health.di.ui.activity.map_navi;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.InfoWindowForSelectPointAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonLocationBean;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.util.MapUtil;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observer;

/**
 * 地图选点
 * Created by Administrator on 2018-02-05.
 */

public class SelectMapLocationActivity extends AbstractActivity {
    public float REGEOCODE_RADIUS = 200;
    int REGEOCODE_REQUEST_SUCCESS_CODE=1000;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mv_map)
    MapView mvMap;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    AMap aMap;
    MyLocationStyle myLocationStyle;

    List<Marker> markerList = new ArrayList<>();

    LatLng selectedLatLng;
    String address;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_map_location_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

        showHint();
    }

    private void showHint(){
        Util.showToast(this, "长按地图选取位置点");
    }

    private void showAddressTextView(boolean show){
        if (show){
            tvAddress.setVisibility(View.VISIBLE);
        } else {
            tvAddress.setVisibility(View.GONE);
        }
    }

    private void initAddressTextViewListener(){
        RxView.clicks(tvAddress)
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
                        String s = tvAddress.getText().toString();
                        if (StringUtils.isBlank(s)){
                            Util.showToast(SelectMapLocationActivity.this, "选定位置不成功");
                        } else {
                            selectedLatLng = MapUtil.convertGcjCoordinateToBaiduCoordinate(selectedLatLng);
                            PersonLocationBean plb = getPersonLocationBeanFromLatLngAddress(selectedLatLng.latitude,
                                    selectedLatLng.longitude, address);
                            Intent intent = new Intent();
                            intent.putExtra(Constants.SELECTED_MAP_LOCATION_KEY, plb);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                });

//        RxTextView.textChanges(tvAddress)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<CharSequence>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(CharSequence charSequence) {
//                        String s = charSequence.toString();
//                        if (StringUtils.isBlank(s)){
//                            tvAddress.setVisibility(View.GONE);
//                        } else {
//                            tvAddress.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });
    }

    private void canonicalizeLongitudeLatitude(LatLng latLng){
        if (latLng!=null){
            double lat = latLng.latitude;
            double lng = latLng.longitude;
            double canonicalizedLat = Util.getDoubleValueFromString(Util.getCanonicalLongitudeOrLatitude(String.valueOf(lat)));
            double canonicalizedLng = Util.getDoubleValueFromString(Util.getCanonicalLongitudeOrLatitude(String.valueOf(lng)));
            selectedLatLng = new LatLng(canonicalizedLat, canonicalizedLng);
        }
    }

//    private void convertGcjCoordinateToBaiduCoordinate(LatLng latLng){
//        if (latLng!=null){
//            double lat = latLng.latitude;
//            double lng = latLng.longitude;
//            double[] baiduCoordinate = CoordinateUtil.gcj02_To_Bd09(lat, lng);
//            double latBaidu = baiduCoordinate[0];
//            double lngBaidu = baiduCoordinate[1];
//            double canonicalizedLat = Util.getDoubleValueFromString(Util.getCanonicalLongitudeOrLatitude(String.valueOf(latBaidu)));
//            double canonicalizedLng = Util.getDoubleValueFromString(Util.getCanonicalLongitudeOrLatitude(String.valueOf(lngBaidu)));
//            selectedLatLng = new LatLng(canonicalizedLat, canonicalizedLng);
//        }
//
//    }

    public void showMarkerOnMap(PersonLocationBean plb) {
        removeAllMarkersFromMapAndClearMarkerList();

        final LatLng latLng = selectedLatLng;
        Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(Constants.EMPTY_STRING).snippet(Constants.EMPTY_STRING));
        marker.setObject(plb);
        addMarkerToList(marker);
        View markerView = LayoutInflater.from(this).inflate(R.layout.layout_custom_marker, null, false);
        marker.setIcon(BitmapDescriptorFactory.fromView(markerView));
        movePositionToCenter(selectedLatLng.longitude, selectedLatLng.latitude);
//        marker.showInfoWindow();
//        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.MAP_ZOOM_LEVEL));
    }


    private void hilightMarker(Marker marker) {
        if (marker == null) {
            return;
        }
        PersonLocationBean plb = (PersonLocationBean) marker.getObject();
        LatLng latLng1 = getLatLngFromPersonLocationBean(plb);
        Util.jumpPoint(marker, aMap, latLng1, null);
        marker.showInfoWindow();
    }

    @NonNull
    private LatLng getLatLngFromPersonLocationBean(PersonLocationBean plb) {
        String latString = plb.getLat();
        String lonString = plb.getLng();
        double latDouble = Util.getDoubleValueFromString(latString);
        double lonDouble = Util.getDoubleValueFromString(lonString);
        return new LatLng(latDouble, lonDouble);
    }



    private void movePositionToCenter(LatLng latLng) {
        aMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.MAP_ZOOM_LEVEL));
    }

    private void movePositionToCenter(double longitude, double latitude) {
        if (!Util.isLongitudeValid(String.valueOf(longitude))) {
            Util.showToast(this, "经度不合法");
            return;
        } else if (!Util.isLatitudeValid(String.valueOf(latitude))) {
            Util.showToast(this, "纬度不合法");
            return;
        }
        movePositionToCenter(new LatLng(latitude, longitude));
    }

    private void initMarkersListener() {
        aMap.setInfoWindowAdapter(new InfoWindowForSelectPointAdapter(this));
        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                marker.hideInfoWindow();
            }
        });

//        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//        canonicalizeLongitudeLatitude(latLng);
//                queryAddressFromLatLng(selectedLatLng);
//            }
//        });

        aMap.setOnMapLongClickListener(new AMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                canonicalizeLongitudeLatitude(latLng);
                queryAddressFromLatLng(selectedLatLng);
            }
        });

        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                hilightMarker(marker);
//                marker.showInfoWindow();
                return false;
            }
        });

    }

    private PersonLocationBean getPersonLocationBeanFromLatLngAddress(double latitude, double longitude, String address){
        PersonLocationBean plb = new PersonLocationBean();
        plb.setLat(Util.getCanonicalLongitudeOrLatitude(String.valueOf(latitude)));
        plb.setLng(Util.getCanonicalLongitudeOrLatitude(String.valueOf(longitude)));
        plb.setAddress(address);
        return plb;
    }

    private void queryAddressFromLatLng(LatLng latLng){
        if (latLng==null || !Util.isLatitudeValid(String.valueOf(latLng.latitude)) ||
                !Util.isLongitudeValid(String.valueOf(latLng.longitude))){
            Util.showToast(this, "经纬度不合法");
            return;
        }
        Util.showGifProgressDialog(this);
        GeocodeSearch geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                if (REGEOCODE_REQUEST_SUCCESS_CODE == i){
                    RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
                    address = addr.getFormatAddress();
                    PersonLocationBean plb = getPersonLocationBeanFromLatLngAddress(selectedLatLng.latitude, selectedLatLng.longitude,
                            address);
                    String s = address + "\n" + getLonLatString() + "\n" + "点击选取此地址";
                    tvAddress.setText(s);
                    if (StringUtils.isBlank(address)){
                        showAddressTextView(false);
                    } else {
                        showAddressTextView(true);
                    }
                    showMarkerOnMap(plb);
                } else {
                    tvAddress.setText(Constants.EMPTY_STRING);
                    showAddressTextView(false);
                    Util.showToast(SelectMapLocationActivity.this, "逆地理编码失败");
                }
                Util.hideGifProgressDialog(SelectMapLocationActivity.this);
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
                Util.hideGifProgressDialog(SelectMapLocationActivity.this);
            }
        });
        LatLonPoint point = new LatLonPoint(latLng.latitude, latLng.longitude);
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(point, REGEOCODE_RADIUS, GeocodeSearch.AMAP);

        geocoderSearch.getFromLocationAsyn(query);

    }

    private String getLonLatString(){
        return "[经度:"+Util.getCanonicalLongitudeOrLatitude(String.valueOf(selectedLatLng.longitude))+" 纬度:"+Util.getCanonicalLongitudeOrLatitude(String.valueOf(selectedLatLng.latitude))+"]";
    }

    private void removeAllMarkersFromMapAndClearMarkerList() {
        removeAllMarkersFromMap();
        clearMarkerList();
    }

    private void removeAllMarkersFromMap() {
        for (int i = 0; i < markerList.size(); ++i) {
            Marker marker = markerList.get(i);
            marker.remove();
        }
    }

    private void clearMarkerList() {
        markerList.clear();
    }

    private void addMarkerToList(Marker marker) {
        markerList.add(marker);
    }



    private void initLocationPoint() {
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW) ;//定位一次
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
        myLocationStyle.showMyLocation(true);
        myLocationStyle.interval(Constants.AMAP_LOCATE_INTERVAL); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if (location != null) {
                    double currentLongitude = location.getLongitude();
                    double currentLatitude = location.getLatitude();
                }
            }
        });
        aMap.animateCamera(CameraUpdateFactory.zoomTo(Constants.MAP_ZOOM_LEVEL));
    }

//    private void setWhetherMovingToRealtimePosition(boolean move) {
//        if (move) {
//            myLocationStyle.showMyLocation(true);
//            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
//        } else {
//            myLocationStyle.showMyLocation(true);
//            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
//        }
//        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvMap.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mvMap.getMap();
        }
        initLocationPoint();
//        setWhetherMovingToRealtimePosition(true);

        initMarkersListener();
        initAddressTextViewListener();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mvMap.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvMap.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mvMap.onSaveInstanceState(outState);
    }
}
