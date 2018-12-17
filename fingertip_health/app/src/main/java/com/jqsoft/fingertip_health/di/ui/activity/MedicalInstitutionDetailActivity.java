//package com.jqsoft.grassroots_civil_administration_platform.di.ui.activity;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.Toolbar;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ScrollView;
//import android.widget.TextView;
//
//import com.afollestad.materialdialogs.DialogAction;
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.amap.api.location.AMapLocation;
//import com.amap.api.location.AMapLocationClient;
//import com.amap.api.location.AMapLocationClientOption;
//import com.amap.api.location.AMapLocationListener;
//import com.amap.api.maps.AMap;
//import com.amap.api.maps.CameraUpdateFactory;
//import com.amap.api.maps.MapView;
//import com.amap.api.maps.model.BitmapDescriptorFactory;
//import com.amap.api.maps.model.LatLng;
//import com.amap.api.maps.model.Marker;
//import com.amap.api.maps.model.MarkerOptions;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.MapInfoWindowAdapter;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.bean.MedicalInstitutionDetailBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.di.contract.MedicalInstitutionDetailActivityContract;
//import com.jqsoft.fingertip_health.di.module.MedicalInstitutionDetailActivityModule;
//import com.jqsoft.fingertip_health.di.presenter.MedicalInstitutionDetailActivityPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
//import com.jqsoft.fingertip_health.simulate.SimulateData;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.LogUtil;
//import com.jqsoft.fingertip_health.utils2.AppUtils;
//import com.jqsoft.fingertip_health.utils3.util.ListUtils;
//import com.jqsoft.fingertip_health.utils3.util.StringUtils;
//
//import org.apmem.tools.layouts.FlowLayout;
//
//import java.util.List;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
///**
// * Created by Administrator on 2017-05-23.
// */
//
//public class MedicalInstitutionDetailActivity extends AbstractActivity implements MedicalInstitutionDetailActivityContract.View/*, AMapLocationListener*/ {
//    @BindView(R.id.sv_scroll_view)
//    ScrollView scrollView;
////    @BindView(R.id.map)
////    MapView mapView;
//    @BindView(R.id.tv_rating)
//    TextView tvRating;
//    @BindView(R.id.tv_score)
//    TextView tvScore;
//    @BindView(R.id.tv_evaluation_number)
//    TextView tvEvaluationNumber;
//    @BindView(R.id.ll_rating_and_evaluation)
//    LinearLayout llRatingAndEvaluation;
//    @BindView(R.id.fl_evaluation)
//    FlowLayout flEvaluation;
//    @BindView(R.id.tv_hospital_name)
//    TextView tvHospitalName;
//    @BindView(R.id.tv_hospital_address)
//    TextView tvHospitalAddress;
//    @BindView(R.id.tv_hospital_distance)
//    TextView tvHospitalDistance;
//    @BindView(R.id.iv_call_phone)
//    ImageView ivCallPhone;
//
//    boolean hasGotCurrentLocation = false;
//    boolean hasGotHttpResult = false;
//
//    @Inject
//    MedicalInstitutionDetailActivityPresenter mPresenter;
//
//    MedicalInstitutionDetailBean hospitalBean;
//
//    private AMapLocationClient mLocationClient;
//    private AMapLocationClientOption mLocationOption;
//    private AMapLocation mCurrentLocation;
//
////    @OnClick(R.id.ll_rating_and_evaluation)
//    public void onRatingAndEvaluationClick() {
//        Util.showToast(this, "评分和评价被点击");
//    }
//
////    @OnClick(R.id.iv_call_phone)
//    public void onCallPhone() {
//        if (hospitalBean == null) {
//            return;
//        }
//        MedicalInstitutionDetailBean bean = hospitalBean;
//        String phoneNumber = Util.trimString(bean.getPhoneNumber());
//        if (StringUtils.isBlank(phoneNumber)) {
//            Util.showToast(this, Constants.HINT_PHONE_NUMBER_EMPTY);
//        } else {
//            AppUtils.actionDial(this, phoneNumber);
//        }
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        scrollView.requestDisallowInterceptTouchEvent(true);
//        return super.dispatchTouchEvent(ev);
//    }
//
////    public void disallowScrollViewTouchEventWhenMovingInMapView() {
////        mapView.setOnTouchListener(new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////                switch (event.getAction()) {
////                    case MotionEvent.ACTION_MOVE:
////                        scrollView.requestDisallowInterceptTouchEvent(true);
////                        break;
////                    case MotionEvent.ACTION_UP:
////                    case MotionEvent.ACTION_CANCEL:
////                        scrollView.requestDisallowInterceptTouchEvent(false);
////                        break;
////                    default:
////                        break;
////                }
////                return true;
////            }
////        });
////    }
//
//    public void showDistance() {
//        MedicalInstitutionDetailBean bean = hospitalBean;
//        if (hasGotCurrentLocation && hasGotHttpResult) {
//            float latitude = bean.getLatitude();
//            float longitude = bean.getLongitude();
//            if (mCurrentLocation == null) {
//            } else if (!Util.isLongitudeValid(String.valueOf(longitude))) {
//
//            } else if (!Util.isLatitudeValid(String.valueOf(latitude))) {
//
//            } else {
//                String distanceString = Util.getDistanceStringBetweenTwoCoordinates((float) mCurrentLocation.getLongitude(),
//                        (float) mCurrentLocation.getLatitude(), longitude, latitude);
//                tvHospitalDistance.setText(distanceString);
//            }
//        }
//    }
//
//    public void showInstitutionInfo(MedicalInstitutionDetailBean bean) {
//        tvHospitalName.setText(bean.getName());
//        tvHospitalAddress.setText(bean.getAddress());
//        showDistance();
//    }
//
//    public void showEvaluationFlowLayout(MedicalInstitutionDetailBean bean) {
//        List<String> evaluationList = bean.getEvaluationList();
//        if (!ListUtils.isEmpty(evaluationList)) {
//            for (int i = 0; i < evaluationList.size(); ++i) {
//                String evaluation = evaluationList.get(i);
//                evaluation = Util.trimString(evaluation);
//                TextView textView = new TextView(this);
//                FlowLayout.LayoutParams layoutParams = new FlowLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
//                layoutParams.setMargins(6, 6, 6, 6);
//                textView.setLayoutParams(layoutParams);
//                textView.setPadding(6, 6, 6, 6);
//                textView.setGravity(Gravity.CENTER);
//                textView.setBackgroundResource(R.drawable.blue_border_background);
//                textView.setTextColor(getResources().getColor(R.color.deepskyblue));
//                textView.setText(evaluation);
//                flEvaluation.addView(textView, 0);
//            }
//
//        }
//    }
//
//    public void showRatingAndEvaluation(MedicalInstitutionDetailBean bean) {
//        float rating = bean.getRating();
//        String ratingString = Util.getRatingStringFromInt((int) rating);
//        tvRating.setText(ratingString);
//        tvScore.setText("" + rating + Constants.SCORE_FEN);
//        tvEvaluationNumber.setText("" + bean.getEvaluationNumber() + Constants.EVALUTION);
//    }
//
//
//    private void destroyLocation() {
//        if (mLocationClient != null) {
//            mLocationClient.unRegisterLocationListener(this);
//            mLocationClient.onDestroy();
//        }
//    }
//
//    /**
//     * 初始化定位
//     */
//    private void initLocation() {
//        mLocationOption = new AMapLocationClientOption();
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        mLocationOption.setOnceLocation(true);
//        mLocationClient = new AMapLocationClient(this.getApplicationContext());
//        mLocationClient.setLocationListener(this);
//        mLocationClient.startLocation();
//    }
//
//    @Override
//    public void onLocationChanged(AMapLocation aMapLocation) {
//        LogUtil.i("location error code:" + aMapLocation.getErrorCode());
//        if (aMapLocation == null || aMapLocation.getErrorCode() != AMapLocation.LOCATION_SUCCESS) {
//            return;
//        }
//        mCurrentLocation = aMapLocation;
//        hasGotCurrentLocation = true;
//        showDistance();
//        mLocationClient.stopLocation();
//        LatLng curLatLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
//    }
//
//    @OnClick(R.id.fl_medical_institution_detail_favorite)
//    public void onFavorite() {
//        Util.showToast(this, "收藏按钮被点击");
//    }
//
//    public void showMarkerOnMap(final MedicalInstitutionDetailBean bean) {
//        float longitude = bean.getLongitude();
//        float latitude = bean.getLatitude();
//        if (!Util.isLongitudeValid(String.valueOf(longitude))) {
//            Util.showToast(this, Constants.HINT_LONGITUDE_INVALID);
//            return;
//        } else if (!Util.isLatitudeValid(String.valueOf(latitude))) {
//            Util.showToast(this, Constants.HINT_LATITUDE_INVALID);
//            return;
//        }
//        final LatLng latLng = new LatLng(latitude, longitude);
//        final AMap aMap = mapView.getMap();
//        Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(bean.getName()).snippet(bean.getAddress()));
//        View markerView = LayoutInflater.from(this).inflate(R.layout.layout_custom_marker, null, false);
//        marker.setIcon(BitmapDescriptorFactory.fromView(markerView));
//        aMap.setInfoWindowAdapter(new MapInfoWindowAdapter(MedicalInstitutionDetailActivity.this, bean));
//        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
//            @Override
//            public void onInfoWindowClick(Marker marker) {
//                marker.hideInfoWindow();
//            }
//        });
//
//        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                Util.jumpPoint(marker, aMap, latLng);
////                marker.showInfoWindow();
//                return true;
//            }
//        });
//        marker.showInfoWindow();
//        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.MAP_ZOOM_LEVEL));
//    }
//
//    public void startNavi() {
//        if (mCurrentLocation == null) {
//            Util.showToast(this, "暂未获取当前位置");
//            return;
//        } else if (hospitalBean == null) {
//            Util.showToast(this, "当前医疗机构信息为空");
//            return;
//        }
//
//        Util.showMaterialDialog(this, Constants.HINT, Constants.HINT_CONFIRM_TO_NAVIGATE, Constants.CANCEL, new MaterialDialog.SingleButtonCallback() {
//            @Override
//            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                dialog.dismiss();
//            }
//        }, Constants.CONFIRM, new MaterialDialog.SingleButtonCallback() {
//            @Override
//            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                startNavigate();
//                dialog.dismiss();
//            }
//        }, true);
//
//
//    }
//
//
//    public void startNavigate() {
////        Intent intent = new Intent(this, RouteNaviActivity.class);
//////        intent.putExtra("gps", false);
////        intent.putExtra(Constants.GPS_KEY, true);
////        intent.putExtra(Constants.START_KEY, new NaviLatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude()));
////        intent.putExtra(Constants.END_KEY, new NaviLatLng(hospitalBean.getLatitude(), hospitalBean.getLongitude()));
////        startActivity(intent);
//
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mapView.onCreate(savedInstanceState);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_medical_institution_detail;
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initView() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setToolBar(toolbar, "");
////        disallowScrollViewTouchEventWhenMovingInMapView();
//        llRatingAndEvaluation.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                onRatingAndEvaluationClick();
//            }
//        });
//        initLocation();
//        ivCallPhone.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                onCallPhone();
//            }
//        });
//    }
//
//    @Override
//    protected void loadData() {
//        mPresenter.main(null);
//    }
//
//    @Override
//    protected void initInject() {
//        super.initInject();
//        DaggerApplication.get(this)
//                .getAppComponent()
//                .addMedicalInstitutionDetailActivity(new MedicalInstitutionDetailActivityModule(this))
//                .inject(this);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        destroyLocation();
//        mapView.onDestroy();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public void onLoadInfoSuccess(HttpResultBaseBean<MedicalInstitutionDetailBean> bean) {
//        MedicalInstitutionDetailBean detailBean = SimulateData.getSimulatedMedicalInstitutionDetailBean();
//        if (detailBean != null) {
//            this.hospitalBean = detailBean;
//            hasGotHttpResult = true;
//            showMarkerOnMap(detailBean);
//            showRatingAndEvaluation(detailBean);
//            showEvaluationFlowLayout(detailBean);
//            showInstitutionInfo(detailBean);
//        } else {
//            Util.showToast(this, "获取的医疗机构信息为空");
//        }
//    }
//
//    @Override
//    public void onLoadInfoFailure(String message) {
//
//    }
//}
