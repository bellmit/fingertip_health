package com.jqsoft.fingertip_health.util;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.jqsoft.fingertip_health.utils.LogUtil;

/**
 * Created by Administrator on 2018-03-06.
 */

public class GetCurrentLocationUtil {
    Context context;
    GetCurrentLocationCallback callback;

    public GetCurrentLocationUtil(Context context, GetCurrentLocationCallback callback) {
        super();
        this.context=context;
        this.callback=callback;

    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener(){
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息

                    //取出经纬度
                    LatLng latLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
//                    latLng = getGcjLatLng(latLng);

//                    //添加Marker显示定位位置
//                    if (locationMarker == null) {
//                        //如果是空的添加一个新的,icon方法就是设置定位图标，可以自定义
//                        locationMarker = aMap.addMarker(new MarkerOptions()
//                                .position(latLng)
//                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker)));
//                    } else {
//                        //已经添加过了，修改位置即可
//                        locationMarker.setPosition(latLng);
//                    }

//                    //然后可以移动到定位点,使用animateCamera就有动画效果
//                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.MAP_ZOOM_LEVEL));

                    if (callback!=null){
                        callback.onGetCurrentLocationSuccess(aMapLocation);
                    }
                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    LogUtil.i("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                    showError();
                    if (callback!=null){
                        callback.onGetCurrentLocationFailure();
                    }
                }
            } else {
                showError();
                if (callback!=null){
                    callback.onGetCurrentLocationFailure();
                }
            }
            stopGetCurrentLocation();
        }
    };

    public void showError(){
        Util.showToast(context, "获取当前位置失败");
    }

    public void startGetCurrentLocation(){
        initGetCurentLocation();

    }

    private void initGetCurentLocation(){
        if (mLocationClient!=null){
            mLocationClient.stopLocation();
            mLocationClient=null;
        }
        //初始化定位
        mLocationClient = new AMapLocationClient(context.getApplicationContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        if(null != mLocationClient){
            //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
//设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
            mLocationOption.setInterval(5000);
//设置是否返回地址信息（默认返回地址信息）
//            mLocationOption.setNeedAddress(true);
//单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
            mLocationOption.setHttpTimeOut(20000);

            mLocationClient.setLocationOption(mLocationOption);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }

    }


    private void stopGetCurrentLocation(){
        mLocationClient.stopLocation();
        mLocationClient.onDestroy();
    }

    public interface GetCurrentLocationCallback{
        void onGetCurrentLocationSuccess(AMapLocation aMapLocation);
        void onGetCurrentLocationFailure();
    }

}
