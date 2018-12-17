package com.jqsoft.fingertip_health.di.ui.activity.map_navi;

import android.Manifest;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.HeatmapTileProvider;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.navi.model.NaviLatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.InfoWindowAdapter;
import com.jqsoft.fingertip_health.adapter.PersonLocationAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.HeatmapBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.LngLatCount;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonLocationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.TitleAndValueBean;
import com.jqsoft.fingertip_health.di.RouteNaviActivity;
import com.jqsoft.fingertip_health.di.presenter.MapServiceActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.AreaSelectActivity;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.youtuIdentify.BitMapUtils;
import com.jqsoft.fingertip_health.di.youtuIdentify.IdentifyResult;
import com.jqsoft.fingertip_health.di.youtuIdentify.TecentHttpUtil;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.popup_window.TitleAndCategoryListPopupWindow;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.MapUtil;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.jqsoft.fingertip_health.view.map.TouchToDrawCircleView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * 受理中心导航界面
 */

public class ReceptionMapServiceActivity extends AbstractActivity {
    public int NETWORK_REQUEST_TYPE_AMBIENT = 1;
    public int NETWORK_REQUEST_TYPE_DRAW_TO_SEARCH = 2;
    public int NETWORK_REQUEST_TYPE_REGION = 3;
    public int NETWORK_REQUEST_TYPE_KIND = 4;
    public int NETWORK_REQUEST_TYPE_SEARCH = 5;
    public int NETWORK_REQUEST_TYPE_HOT_PICTURE = 6;
    PersonLocationBean pb;
    private int START_AREA_SELECT_ACTIVITY_REQUEST_CODE = 10000;
    private int SELECT_IMAGE_TO_PARSE_FOR_SEARCH_REQUEST_CODE = 10001;
    private int READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 10002;

    private final String INFO_NULL_CANT_NAVIGATE = "标记信息为空,无法导航";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mv_map)
    MapView mvMap;
    @BindView(R.id.ttdcv_draw)
    TouchToDrawCircleView ttdcvDraw;
    @BindView(R.id.ll_icon_text)
    LinearLayout llIconText;
//    @BindView(R.id.itl_ambient)
//    ImageTextLayout itlAmbient;
//    @BindView(R.id.itl_draw_to_search)
//    ImageTextLayout itlDrawToSearch;
//    @BindView(R.id.itl_region)
//    ImageTextLayout itlRegion;
//    @BindView(R.id.itl_more)
//    ImageTextLayout itlMore;
//    @BindView(R.id.ll_kind_search_hot)
//    LinearLayout llKindSearchHot;
//    @BindView(R.id.itl_kind)
//    ImageTextLayout itlKind;
//    @BindView(R.id.itl_search)
//    ImageTextLayout itlSearch;
//    @BindView(R.id.itl_hot_picture)
//    ImageTextLayout itlHotPicture;
//    @BindView(R.id.tv_data_type)
//    TextView tvDataType;
//    @BindView(R.id.iv_show_or_hide_list)
//    ImageView ivShowOrHideList;
    @BindView(R.id.iv_goto_my_position)
    ImageView ivGotoMyPosition;

    @BindView(R.id.framelayout)
    FrameLayout flLeft;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_map_service_load_failure)
    View failureView;

    TextView tvFailureView;


    private PersonLocationAdapter adapter;

//    int dataTypeIndex = Constants.DATA_TYPE_ALL;
    int dataTypeIndex = Constants.DATA_TYPE_DIFFICULTY_PEOPLE;
    String dataType;

    int networkRequestType = NETWORK_REQUEST_TYPE_AMBIENT;


    AMap aMap;
    MyLocationStyle myLocationStyle;
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

                    //然后可以移动到定位点,使用animateCamera就有动画效果
                    aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.MAP_ZOOM_LEVEL));

                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    LogUtil.i("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
            stopGotoMyPosition();
        }
    };

    //周边
    float currentLongitude, currentLatitude;
    String currentRadius = "300";

    //画图搜索
    float drawToSearchLongitude, drawToSearchLatitude;
    String drawToSearchRadius = "0";

    //区域搜索
    String ultimateAreaCode;
    String provinceAreaCode, cityAreaCode, countyAreaCode, streetAreaCode, villageAreaCode;

    //分类
    String familyType;

    //关键字搜索
    Dialog keywordSearchDialog;
    EditText etKeyword;
    Button btnKeywordClear;
    ImageView ivTakePhoto;
    Button btnSearch;

//    //热力图
//    Thread loadHeatmapThread;

    List<Marker> markerList = new ArrayList<>();
    List<Circle> circleList = new ArrayList<>();
    List<TileOverlay> heatmapOverlayList = new ArrayList<>();

    List<SRCLoginDataDictionaryBean> familyTypeList;

    TitleAndCategoryListPopupWindow ambientPopupWindow;

    TitleAndCategoryListPopupWindow categoryPopupWindow;

    @Inject
    MapServiceActivityPresenter mPresenter;

    CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvMap.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mvMap.getMap();
        }
        initLocationPoint();
        setWhetherMovingToRealtimePosition(false, true);
    }

    private void handleDrawToSearch(double[] doubles) {
        showTouchToDrawCircleView(false);
        LatLng centerLatLng = MapUtil.getLatLngFromScreenPoint(aMap, (float) doubles[0], (float) doubles[1]);
        float radius = MapUtil.getDistanceBetweenTwoScreenPoint(aMap, (float) doubles[0], (float) doubles[1],
                (float) doubles[2], (float) doubles[3]);
        drawToSearchLongitude = (float) centerLatLng.longitude;
        drawToSearchLatitude = (float) centerLatLng.latitude;
        drawToSearchRadius = String.valueOf(radius);
        showDrawToSearchCircle(centerLatLng, radius);
        onRefresh();
    }

    private void registerRxBusSubscription() {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_MAP_TOUCH_TO_DRAW_CIRCLE_DID_FINISH,
                double[].class).subscribe(new Action1<double[]>() {
            @Override
            public void call(double[] doubles) {
                if (doubles != null && doubles.length == 4) {
                    handleDrawToSearch(doubles);
                } else {
                    Util.showToast(ReceptionMapServiceActivity.this, "画图搜索参数错误");
                }
            }
        });
        mCompositeSubscription.add(subscription);
    }

    private void unregisterRxBusEvent() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

//    private void initShowOrHideListButton() {
//        RxView.clicks(ivShowOrHideList)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
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
//                    public void onNext(Object o) {
//                        if (isShowingLeftRecyclerView()) {
//                            hideLeftRecyclerView();
//                        } else {
//                            showLeftRecyclerView();
//                        }
//                    }
//                });
//    }

    private void initGotoMyPositionButton() {
        RxView.clicks(ivGotoMyPosition)
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
                        initGotoMyPosition();
                    }
                });
    }

    private boolean isShowingLeftRecyclerView() {
        return flLeft.getVisibility() == View.VISIBLE;
    }

//    private void showShowOrHideImageView(boolean show){
//        if (show){
//            ivShowOrHideList.setVisibility(View.VISIBLE);
//        } else {
//            ivShowOrHideList.setVisibility(View.GONE);
//        }
//    }

    private float getLeftRecycllerViewWidth() {
        float rvWidth = getResources().getDimension(R.dimen.left_recycler_view_width);
        return rvWidth;
    }

    private void initRecyclerViewPosition() {
        float rvWidth = getLeftRecycllerViewWidth();
        flLeft.setTranslationX(-rvWidth);
    }

    private void showLeftRecyclerView() {
        flLeft.setVisibility(View.VISIBLE);
        float rvWidth = getLeftRecycllerViewWidth();
        ObjectAnimator animator = ObjectAnimator.ofFloat(flLeft, "translationX", -rvWidth, 0);
        animator.setDuration(Constants.ANIMATION_DURATION);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    private void hideLeftRecyclerView() {
        flLeft.setVisibility(View.VISIBLE);
        float rvWidth = getLeftRecycllerViewWidth();
        ObjectAnimator animator = ObjectAnimator.ofFloat(flLeft, "translationX", 0, -rvWidth);
        animator.setDuration(Constants.ANIMATION_DURATION);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                flLeft.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    public Map<String, String> getAmbientPersonListRequestMap() {
        Map<String, String> map = ParametersFactory.getAmbientPersonListMap(this, dataType, String.valueOf(currentLongitude),
                String.valueOf(currentLatitude), currentRadius, Constants.METHOD_NAME_GET_AMBIENT_PERSON_LIST);
        return map;
    }

    public Map<String, String> getDrawToSearchPersonListRequestMap() {
        Map<String, String> map = ParametersFactory.getDrawToSearchPersonListMap(this, dataType, String.valueOf(drawToSearchLongitude),
                String.valueOf(drawToSearchLatitude), drawToSearchRadius, Constants.METHOD_NAME_GET_DRAW_TO_SEARCH_PERSON_LIST);
        return map;
    }

    public Map<String, String> getRegionPersonListRequestMap() {
        Map<String, String> map = ParametersFactory.getRegionPersonListMap(this, dataType, ultimateAreaCode,
                Constants.METHOD_NAME_GET_REGION_PERSON_LIST);
        return map;
    }

    public Map<String, String> getCategoryPersonListRequestMap() {
        familyType = Util.trimString(familyType);
        Map<String, String> map = ParametersFactory.getCategorySearchPersonListMap(this, dataType, familyType, ultimateAreaCode,
                Constants.METHOD_NAME_GET_CATEGORY_PERSON_LIST);
        return map;
    }

    public Map<String, String> getKeywordSearchPersonListRequestMap() {
        String keyword = getKeyword();
        Map<String, String> map = ParametersFactory.getKeywordSearchPersonListMap(this, dataType, keyword,
                Constants.METHOD_NAME_GET_KEYWORD_PERSON_LIST);
        return map;
    }

    public Map<String, String> getHeatmapRequestMap() {
        Map<String, String> map = ParametersFactory.getHeatmapBeanMap(this, dataType,
                Constants.METHOD_NAME_GET_HEATMAP_BEAN);
        return map;
    }

    private String getKeyword() {
        return Util.trimString(etKeyword.getText().toString());
    }

    private void initGotoMyPosition(){
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
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

    private void stopGotoMyPosition(){
        mLocationClient.stopLocation();
    }

    private void initLocationPoint() {

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW) ;//定位一次
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE) ;//定位一次，且将视角移动到地图中心点。
        myLocationStyle.interval(Constants.AMAP_LOCATE_INTERVAL); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setCompassEnabled(true);
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if (location != null) {
                    currentLongitude = (float) location.getLongitude();
                    currentLatitude = (float) location.getLatitude();
                }
            }
        });
    }

    private void setWhetherMovingToRealtimePosition(boolean continuousLocate, boolean move) {
        if (continuousLocate) {
            myLocationStyle.showMyLocation(true);
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        } else {
            myLocationStyle.showMyLocation(true);
            if (move) {
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
            } else {
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
            }
        }
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
    }

    private boolean isLocationValid(float longitude, float latitude) {
        boolean isLongitudeValid = Util.isLongitudeValid(String.valueOf(longitude));
        boolean isLatitudeValid = Util.isLatitudeValid(String.valueOf(latitude));
        if (isLongitudeValid && isLatitudeValid) {
            return true;
        } else {
            return false;
        }
    }

    private void initAmbientPopupWindow() {
        List<TitleAndValueBean> list = new ArrayList<>();
        TitleAndValueBean threeHundredMeters = new TitleAndValueBean("300米", "300");
        TitleAndValueBean fiveHundredMeters = new TitleAndValueBean("500米", "500");
//        TitleAndValueBean oneThousandMeters = new TitleAndValueBean("1000米", "1000");
        TitleAndValueBean oneThousandMeters = new TitleAndValueBean("10000米", "10000");
        list.add(threeHundredMeters);
        list.add(fiveHundredMeters);
        list.add(oneThousandMeters);
//        ambientPopupWindow = new TitleAndCategoryListPopupWindow(this, Constants.POPUP_WINDOW_WIDTH_FOR_MAP,
//                Constants.POPUP_WINDOW_HEIGHT_FOR_MAP, itlAmbient, "搜索半径", list);
//        ambientPopupWindow.setListener(new TitleAndCategoryListPopupWindow.TitleAndCategoryItemClickListener() {
//            @Override
//            public void titleAndCategoryItemDidClick(TitleAndValueBean bean) {
////                ToastUtil.show(MapServiceActivity.this, "选中了"+bean.getTitle());
//                currentRadius = bean.getValue();
//                onRefresh();
//            }
//        });
//        ambientPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
////                itlAmbient.setNormalState();
//            }
//        });
    }

//    private void initCategoryPopupWindow() {
//        List<TitleAndValueBean> list = new ArrayList<>();
//        for (int i = 0; i < familyTypeList.size(); ++i) {
//            SRCLoginDataDictionaryBean ft = familyTypeList.get(i);
//            String title = ft.getName();
//            String value = ft.getCode();
//            TitleAndValueBean bean = new TitleAndValueBean(title, value);
//            list.add(bean);
//        }
//        categoryPopupWindow = new TitleAndCategoryListPopupWindow(this, Constants.POPUP_WINDOW_WIDTH_FOR_MAP,
//                Constants.POPUP_WINDOW_HEIGHT_FOR_MAP, itlKind, "家庭类别", list);
//        categoryPopupWindow.setListener(new TitleAndCategoryListPopupWindow.TitleAndCategoryItemClickListener() {
//            @Override
//            public void titleAndCategoryItemDidClick(TitleAndValueBean bean) {
////                ToastUtil.show(MapServiceActivity.this, "选中了"+bean.getTitle());
//                familyType = bean.getValue();
//                onRefresh();
//            }
//        });
//
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_receptionmap_service_layout;
    }

    @Override
    protected void initData() {
//        initFamilyTypeDataDictionary();
        pb=(PersonLocationBean) getDeliveredSerializableByKey(Constants.RECRPTION_NEWLIST_ACTIVITY_KEY);

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

        tvFailureView = (TextView) failureView.findViewById(R.id.tv_load_failure_hint);
//        tvFailureView.setText(failureString);
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });

//        initDataType();

        registerRxBusSubscription();

        initRecyclerView();
        initRecyclerViewPosition();

//        initShowOrHideListButton();
        initGotoMyPositionButton();

//        initIconTextClickListener();

//        initAmbientPopupWindow();
//        initCategoryPopupWindow();


        startNavi(pb);
    }

    private void initRecyclerView() {
        final BaseQuickAdapter<PersonLocationBean, BaseViewHolder> mAdapter = new PersonLocationAdapter(this, new ArrayList<PersonLocationBean>());
        this.adapter = (PersonLocationAdapter) mAdapter;
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, true);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//                Util.showToast(MapServiceActivity.this, position+" is clicked");
                PersonLocationBean pb = (PersonLocationBean) adapter.getItem(position);
                hilightPersonLocationBean(pb);
                movePositionToCenter(pb);
                hideLeftRecyclerView();
//                startNavi(pb);
            }
        });

    }

//    private void initDataType() {
////        initDataType(dataTypeIndex);
//        RxView.clicks(tvDataType)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
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
//                    public void onNext(Object o) {
//                        String[] typeArray = new String[]{"困难群众", "一门式", "全部"};
//                        List<String> typeList = Arrays.asList(typeArray);
//                        Util.showSingleChoiceStringListMaterialDialog(ReceptionMapServiceActivity.this, "请选择数据类型", Constants.EMPTY_STRING, typeList,
//                                dataTypeIndex, new MaterialDialog.ListCallbackSingleChoice() {
//                                    @Override
//                                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
//                                        initDataType(which);
//                                        return false;
//                                    }
//                                });
//                    }
//                });
//
//    }

//    private void initDataType(int index) {
//        String presentation = Constants.EMPTY_STRING;
//        String tempDataType = Constants.EMPTY_STRING;
//        if (Constants.DATA_TYPE_DIFFICULTY_PEOPLE == index) {
//            presentation = "困难群众";
//            tempDataType = Constants.DATA_TYPE_VALUE_DIFFICULTY_PEOPLE;
//        } else if (Constants.DATA_TYPE_YIMENSHI == index) {
//            presentation = "一门式";
//            tempDataType = Constants.DATA_TYPE_VALUE_YIMENSHI;
//        } else if (Constants.DATA_TYPE_ALL == index) {
//            presentation = "全部";
//            tempDataType = Constants.DATA_TYPE_VALUE_ALL;
//        }
//        tvDataType.setText(presentation);
//        dataType = tempDataType;
//        dataTypeIndex = index;
//    }

    private void setNetworkRequestType(int newType) {
        networkRequestType = newType;
    }

    private void showTouchToDrawCircleView(boolean show) {
        if (show) {
            ttdcvDraw.setVisibility(View.VISIBLE);
        } else {
            ttdcvDraw.setVisibility(View.GONE);
        }
    }
//
//    private void initIconTextClickListener() {
//        RxView.clicks(itlAmbient)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
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
//                    public void onNext(Object o) {
////                        if (itlAmbient.isHilighted()){
////                            return;
////                        } else {
//                        setNetworkRequestType(NETWORK_REQUEST_TYPE_AMBIENT);
//                        setWhetherMovingToRealtimePosition(false, true);
//                        clearIconTextState();
//                        itlAmbient.setHilightState();
//                        showShowOrHideImageView(true);
//                        ambientPopupWindow.show();
//                        removeAllCirclesFromMapAndClearCircleList();
//                        removeAllHeatmapOverlaysFromMapAndClearHeatmapOverlayList();
//                        showTouchToDrawCircleView(false);
////                        }
//                    }
//                });
//
//        RxView.clicks(itlDrawToSearch)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
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
//                    public void onNext(Object o) {
//                        setNetworkRequestType(NETWORK_REQUEST_TYPE_DRAW_TO_SEARCH);
//                        setWhetherMovingToRealtimePosition(false, false);
//                        clearIconTextState();
//                        itlDrawToSearch.setHilightState();
//                        showShowOrHideImageView(true);
//                        removeAllCirclesFromMapAndClearCircleList();
//                        removeAllHeatmapOverlaysFromMapAndClearHeatmapOverlayList();
//                        showTouchToDrawCircleView(true);
//                    }
//                });
//
//        RxView.clicks(itlRegion)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
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
//                    public void onNext(Object o) {
//                        setNetworkRequestType(NETWORK_REQUEST_TYPE_REGION);
//                        setWhetherMovingToRealtimePosition(false, false);
//                        clearIconTextState();
//                        itlRegion.setHilightState();
//                        showShowOrHideImageView(true);
//                        removeAllCirclesFromMapAndClearCircleList();
//                        removeAllHeatmapOverlaysFromMapAndClearHeatmapOverlayList();
//                        showTouchToDrawCircleView(false);
//                        startAreaSelectActivity();
//                    }
//                });
//
//        RxView.clicks(itlMore)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
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
//                    public void onNext(Object o) {
//                        toggleKindSearchHotLayoutVisibility();
//                    }
//                });
//
//        RxView.clicks(itlKind)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
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
//                    public void onNext(Object o) {
//                        setNetworkRequestType(NETWORK_REQUEST_TYPE_KIND);
//                        setWhetherMovingToRealtimePosition(false, false);
//                        clearIconTextState();
//                        itlKind.setHilightState();
//                        showShowOrHideImageView(true);
//                        categoryPopupWindow.show();
//                        removeAllCirclesFromMapAndClearCircleList();
//                        removeAllHeatmapOverlaysFromMapAndClearHeatmapOverlayList();
//                        showTouchToDrawCircleView(false);
//                    }
//                });
//
//        RxView.clicks(itlSearch)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
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
//                    public void onNext(Object o) {
//                        setNetworkRequestType(NETWORK_REQUEST_TYPE_SEARCH);
//                        setWhetherMovingToRealtimePosition(false, false);
//                        clearIconTextState();
//                        itlSearch.setHilightState();
//                        showShowOrHideImageView(true);
//                        removeAllCirclesFromMapAndClearCircleList();
//                        removeAllHeatmapOverlaysFromMapAndClearHeatmapOverlayList();
//                        showTouchToDrawCircleView(false);
//
//                        showSearchDialogByNameOrIdCardNumber();
//                    }
//                });
//
//        RxView.clicks(itlHotPicture)
//                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
//                .subscribe(new Observer<Object>() {
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
//                    public void onNext(Object o) {
//                        setNetworkRequestType(NETWORK_REQUEST_TYPE_HOT_PICTURE);
//                        setWhetherMovingToRealtimePosition(false, false);
//                        clearIconTextState();
//                        itlHotPicture.setHilightState();
//                        showShowOrHideImageView(false);
//                        removeAllCirclesFromMapAndClearCircleList();
//                        removeAllHeatmapOverlaysFromMapAndClearHeatmapOverlayList();
//                        showTouchToDrawCircleView(false);
//                        onRefresh();
//                    }
//                });
//    }

    private void showSearchDialogByNameOrIdCardNumber() {
        if (keywordSearchDialog == null) {
//            keywordSearchDialog = Util.showCustomViewMaterialDialogWithButtonText(this, Constants.EMPTY_STRING,
//                    Constants.EMPTY_STRING, R.layout.layout_keyword_search, Constants.EMPTY_STRING, Constants.EMPTY_STRING,
//                    true,
//                    new MaterialDialog.SingleButtonCallback() {
//                        @Override
//                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//                        }
//                    });
//            Util.setDialogLeftRightMarginZero(keywordSearchDialog);
            keywordSearchDialog = new Dialog(this, R.style.white_background_dialog);
            keywordSearchDialog.setContentView(R.layout.layout_keyword_search);
            Util.setDialogFillWidth(this, keywordSearchDialog);
            Util.placeDialogAtBottom(keywordSearchDialog);
            etKeyword = (EditText) keywordSearchDialog.findViewById(R.id.et_name_or_card_number);
            btnKeywordClear = (Button) keywordSearchDialog.findViewById(R.id.btn_keyword_clear);
            ivTakePhoto = (ImageView) keywordSearchDialog.findViewById(R.id.iv_take_photo);
            btnSearch = (Button) keywordSearchDialog.findViewById(R.id.btn_search);
            initSearchDialogWidgetListener();
        }
        keywordSearchDialog.show();
    }

    private void initSearchDialogWidgetListener() {
        RxTextView.textChanges(etKeyword)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CharSequence charSequence) {
                        String s = charSequence.toString();
                        if (!StringUtils.isBlank(s)) {
                            btnKeywordClear.setVisibility(View.VISIBLE);
                        } else {
                            btnKeywordClear.setVisibility(View.GONE);
                        }
                    }
                });
        RxView.clicks(btnKeywordClear)
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
                        etKeyword.setText(Constants.EMPTY_STRING);
                    }
                });
        RxView.clicks(ivTakePhoto)
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
                        handleTakePhotoButtonClicked();
                    }
                });
        RxView.clicks(btnSearch)
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
                        String keyword = getKeyword();
                        if (!StringUtils.isBlank(keyword)) {
                            keywordSearchDialog.dismiss();
                            onRefresh();
                        } else {
                            Util.showToast(ReceptionMapServiceActivity.this, "请输入关键字再搜索");
                        }
                    }
                });
    }

    private void handleTakePhotoButtonClicked() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 应用没有读取手机外部存储的权限
            // 申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
        } else {
            selectImage();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE) {
            if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//授权成功
                selectImage();
            } else {//授权失败
                Util.showToast(this, "获取读图片权限失败");
            }
        }
    }

    private void selectImage() {
        MultiImageSelector.create(this)
                .showCamera(true) // 是否显示相机. 默认为显示
//                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single() // 单选模式
//                .multi() // 多选模式, 默认模式;
//                .origin(ArrayList<String>) // 默认已选择图片. 只有在选择模式为多选时有效
                .start(this, SELECT_IMAGE_TO_PARSE_FOR_SEARCH_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == START_AREA_SELECT_ACTIVITY_REQUEST_CODE) {
                if (data != null) {
                    String selectedAreaCode = data.getStringExtra(Constants.SELECTED_AREA_CODE_KEY);
                    ultimateAreaCode = selectedAreaCode;
                    provinceAreaCode = data.getStringExtra(Constants.SELECTED_PROVINCE_AREA_CODE_KEY);
                    cityAreaCode = data.getStringExtra(Constants.SELECTED_CITY_AREA_CODE_KEY);
                    countyAreaCode = data.getStringExtra(Constants.SELECTED_COUNTY_AREA_CODE_KEY);
                    streetAreaCode = data.getStringExtra(Constants.SELECTED_STREET_AREA_CODE_KEY);
                    villageAreaCode = data.getStringExtra(Constants.SELECTED_VILLAGE_AREA_CODE_KEY);
                    onRefresh();
                }
            } else if (requestCode == SELECT_IMAGE_TO_PARSE_FOR_SEARCH_REQUEST_CODE) {
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
                if (path != null && path.size() > 0) {
                    String p = path.get(0);
//                    onSelected();
                    Bitmap bitmap = getImage(p);
                    //   imageView.setImageBitmap(bitmap);
                    Util.showGifProgressDialog(ReceptionMapServiceActivity.this);
                    TecentHttpUtil.uploadIdCard(BitMapUtils.bitmapToBase64(bitmap), "0", new TecentHttpUtil.SimpleCallBack() {
                        @Override
                        public void Succ(String res) {
                            IdentifyResult result = new Gson().fromJson(res, IdentifyResult.class);
                            if (result != null) {
                                if (result.getErrorcode() == 0) {
                                    // 识别成功
                                    Util.hideGifProgressDialog(ReceptionMapServiceActivity.this);

                                    etKeyword.setText(result.getId());
                                } else {
                                    Util.hideGifProgressDialog(ReceptionMapServiceActivity.this);
                                    Util.showToast(ReceptionMapServiceActivity.this, "识别失败，请拍照清楚后重新识别");
                                }
                            }
                        }

                        @Override
                        public void error() {
                            Util.showToast(ReceptionMapServiceActivity.this, "识别出错");
                        }
                    });


                }
            }
        }
    }

    private Bitmap getImage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;// 这里设置高度为800f
        float ww = 480f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }

    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            if (options < 0) {
                image.compress(Bitmap.CompressFormat.JPEG, 10, baos);// 这里压缩options%，把压缩后的数据存放到baos中
                break;
            } else {
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            }

            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    private void startAreaSelectActivity() {
        Intent intent = new Intent(this, AreaSelectActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        intent.putExtra(Constants.SELECTED_PROVINCE_AREA_CODE_KEY, provinceAreaCode);
        intent.putExtra(Constants.SELECTED_CITY_AREA_CODE_KEY, cityAreaCode);
        intent.putExtra(Constants.SELECTED_COUNTY_AREA_CODE_KEY, countyAreaCode);
        intent.putExtra(Constants.SELECTED_STREET_AREA_CODE_KEY, streetAreaCode);
        intent.putExtra(Constants.SELECTED_VILLAGE_AREA_CODE_KEY, villageAreaCode);
        startActivityForResult(intent, START_AREA_SELECT_ACTIVITY_REQUEST_CODE);
    }
//
//    private void clearIconTextState() {
////        ImageTextLayout[] array = new ImageTextLayout[]{itlAmbient, itlDrawToSearch, itlRegion, itlMore,
////        itlKind, itlSearch, itlHotPicture};
//        ImageTextLayout[] array = new ImageTextLayout[]{itlAmbient, itlDrawToSearch, itlRegion,
//                itlKind, itlSearch, itlHotPicture};
//        for (int i = 0; i < array.length; ++i) {
//            ImageTextLayout itl = array[i];
//            itl.setNormalState();
//        }
//    }
//
//    private void toggleKindSearchHotLayoutVisibility() {
//        int i = llKindSearchHot.getVisibility();
//        if (i == View.VISIBLE) {
//            llKindSearchHot.setVisibility(View.GONE);
//            itlMore.setNormalState();
//        } else {
//            llKindSearchHot.setVisibility(View.VISIBLE);
//            itlMore.setHilightState();
//        }
//    }

    @Override
    protected void loadData() {

    }


    private void onRefresh() {
        Map<String, String> map = getNetworkRequestMap();
        if (networkRequestType == NETWORK_REQUEST_TYPE_HOT_PICTURE) {
            mPresenter.getHeatmapBean(map);
        } else {
            mPresenter.main(map, false);
        }

    }

    private Map<String, String> getNetworkRequestMap() {
        Map<String, String> map = new HashMap<>();
        if (networkRequestType == NETWORK_REQUEST_TYPE_AMBIENT) {
            map = getAmbientPersonListRequestMap();
        } else if (networkRequestType == NETWORK_REQUEST_TYPE_DRAW_TO_SEARCH) {
            map = getDrawToSearchPersonListRequestMap();
        } else if (networkRequestType == NETWORK_REQUEST_TYPE_REGION) {
            map = getRegionPersonListRequestMap();
        } else if (networkRequestType == NETWORK_REQUEST_TYPE_KIND) {
            map = getCategoryPersonListRequestMap();
        } else if (networkRequestType == NETWORK_REQUEST_TYPE_SEARCH) {
            map = getKeywordSearchPersonListRequestMap();
        } else if (networkRequestType == NETWORK_REQUEST_TYPE_HOT_PICTURE) {
            map = getHeatmapRequestMap();
        }
        return map;
    }

    public List<PersonLocationBean> getListFromResult(GCAHttpResultBaseBean<List<PersonLocationBean>> beanList) {
        if (beanList != null) {
            List<PersonLocationBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

    public int getListSizeFromResult(GCAHttpResultBaseBean<List<PersonLocationBean>> beanList) {
        if (beanList != null) {
            List<PersonLocationBean> list = beanList.getData();
            if (list != null) {
                int size = ListUtils.getSize(list);
                return size;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
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

        if (mLocationClient!=null) {
            mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }

        mvMap.onDestroy();
        unregisterRxBusEvent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mvMap.onSaveInstanceState(outState);
    }

    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty) {
        if (success) {
            if (isListEmpty) {
                recyclerView.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
            }
        } else {
            recyclerView.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());

        }
    }

    private String getListEmptyHint() {
        return getResources().getString(R.string.hint_no_person_location_info_please_click_to_reload);
    }

    private String getFailureHint() {
        return getResources().getString(R.string.hint_load_person_location_info_error_please_click_to_reload);
    }
//
//    @Override
//    public void onLoadListSuccess(GCAHttpResultBaseBean<List<PersonLocationBean>> bean) {
//        int listSize = getListSizeFromResult(bean);
//
//        List<PersonLocationBean> list = getListFromResult(bean);
//        setGcjLatLngForPersonLocationBeanList(list);
//        showPersonLocationBeanListAndMarkers(list);
////        adapter.setNewData(list);
////        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(adapter.getData()));
//
//    }
//
//    @Override
//    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<PersonLocationBean>> bean) {
//        int listSize = getListSizeFromResult(bean);
//
//        List<PersonLocationBean> list = getListFromResult(bean);
//        setGcjLatLngForPersonLocationBeanList(list);
//
//        adapter.addData(list);
//        List<PersonLocationBean> allList = adapter.getData();
//        showPersonLocationBeanListAndMarkers(allList);
////        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(adapter.getData()));
//
//    }
//
//    @Override
//    public void onLoadListFailure(String message, boolean isLoadMore) {
//        showRecyclerViewOrFailureView(false, true);
//        Util.showToast(this, "数据查询失败");
//
////        simulate();
//    }
//
//    @Override
//    public void onLoadHeatmapSuccess(GCAHttpResultBaseBean<HeatmapBean> bean) {
//        showHeatmapData(bean);
////        simulateHeatmapData();
//    }
//
//    @Override
//    public void onLoadHeatmapFailure(String msg) {
//        Util.showToast(this, "加载热力图数据失败");
////        simulateHeatmapData();
//    }

    private void removeAllHeatmapOverlaysFromMapAndClearHeatmapOverlayList() {
        removeAllHeatmapOverlaysFromMap();
        clearHeatmapOverlaylList();
    }

    private void removeAllHeatmapOverlaysFromMap() {
        for (int i = 0; i < heatmapOverlayList.size(); ++i) {
            TileOverlay tileOverlay = heatmapOverlayList.get(i);
            tileOverlay.remove();
        }
    }

    private void clearHeatmapOverlaylList() {
        heatmapOverlayList.clear();
    }

    private void addHeatmapOverlayToList(TileOverlay tileOverlay) {
        heatmapOverlayList.add(tileOverlay);
    }



    private void showHeatmapData(GCAHttpResultBaseBean<HeatmapBean> bean) {
        removeAllCirclesFromMapAndClearCircleList();
        removeAllHeatmapOverlaysFromMapAndClearHeatmapOverlayList();
        removeAllMarkersFromMapAndClearMarkerList();

        String emptyHint = "热力图数据为空";
        if (bean != null) {
            HeatmapBean hb = bean.getData();
            if (hb != null) {
                List<LngLatCount> pointList = hb.getPoints();
                if (!ListUtils.isEmpty(pointList)) {
                    List<LatLng> llList = getLatLngListFromLngLatCountList(pointList);
                    // 构建热力图 HeatmapTileProvider
                    HeatmapTileProvider.Builder builder = new HeatmapTileProvider.Builder();
                    builder.data(llList); // 设置热力图绘制的数据
                    // 构造热力图对象
                    HeatmapTileProvider heatmapTileProvider = builder.build();
                    // 初始化 TileOverlayOptions
                    TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
                    tileOverlayOptions.tileProvider(heatmapTileProvider); // 设置瓦片图层的提供者
                    // 向地图上添加 TileOverlayOptions 类对象
                    TileOverlay tileOverlay = aMap.addTileOverlay(tileOverlayOptions);
                    addHeatmapOverlayToList(tileOverlay);

                    includeAllLatLng(llList);

                } else {
                    Util.showToast(this, emptyHint);
                }
            } else {
                Util.showToast(this, emptyHint);
            }
        } else {
            Util.showToast(this, emptyHint);
        }

    }

    private List<LatLng> getLatLngListFromLngLatCountList(List<LngLatCount> list) {
        List<LatLng> llList = new ArrayList<>();
        List<LatLng> gcjLLList = new ArrayList<>();
        if (ListUtils.isEmpty(list)) {
            return llList;
        } else {
            try {
                for (int i = 0; i < list.size(); ++i){
                    LngLatCount llc = list.get(i);
                    int count = Util.getIntFromString(llc.getCount());
                    double lat = Util.getDoubleValueFromString(llc.getLat());
                    double lon = Util.getDoubleValueFromString(llc.getLng());
                    for (int j = 0; j < count; ++j){
                        LatLng ll = new LatLng(lat, lon);
//                        LatLng gcjLL = getGcjLatLng(ll);
                        llList.add(ll);
                    }
                }
                gcjLLList = getGcjLatLngList(llList);
                return gcjLLList;
            } catch (Exception e) {
                e.printStackTrace();
                llList = new ArrayList<>();
            }
            return llList;
        }
    }

    private List<LatLng> getLatlngListFromPersonLocationBeanList(List<PersonLocationBean> list) {
        List<LatLng> llList = new ArrayList<>();
        if (ListUtils.isEmpty(list)) {
            return llList;
        } else {
            try {
                for (int i = 0; i < list.size(); ++i) {
                    PersonLocationBean plb = list.get(i);
                    LatLng latLng = getLatLngFromPersonLocationBean(plb);
                    llList.add(latLng);
                }
            } catch (Exception e) {
                e.printStackTrace();
                llList = new ArrayList<>();
            }
            return llList;
        }
    }

    @NonNull
    private LatLng getLatLngFromPersonLocationBean(PersonLocationBean plb) {
        String latString = plb.getLat();
        String lonString = plb.getLng();
        double latDouble = Util.getDoubleValueFromString(latString);
        double lonDouble = Util.getDoubleValueFromString(lonString);
        return new LatLng(latDouble, lonDouble);
    }

    private void includeAllPersonLocationBeanList(List<PersonLocationBean> list) {
        List<LatLng> latLngList = getLatlngListFromPersonLocationBeanList(list);
        includeAllLatLng(latLngList);
    }

    private void includeAllLatLng(List<LatLng> list) {
        if (ListUtils.isEmpty(list)) {
            return;
        }
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
        for (int i = 0; i < list.size(); ++i) {
            LatLng latLng = list.get(i);
            boundsBuilder.include(latLng);
        }
        LatLngBounds bounds = boundsBuilder.build();
        aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 150));
//        aMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 300));
    }

//    private LatLng getGcjLatLng(LatLng latLng){
//        return MapUtil.getGcjLatLngFromLatLngType(this, Constants.SERVER_MAP_COORDINATE_TYPE, latLng);
//    }

    private List<LatLng> getGcjLatLngList(List<LatLng> list){
        return MapUtil.getGcjLatLngListFromLatLngType(this, Constants.SERVER_MAP_COORDINATE_TYPE, list);
    }

    private void setGcjLatLngForPersonLocationBeanList(List<PersonLocationBean> list){
        MapUtil.setPersonLocationBeanListToGcjLatLng(this, Constants.SERVER_MAP_COORDINATE_TYPE, list);
    }

    public void showMarkerOnMap(final PersonLocationBean bean) {
        float longitude = Util.getFloatFromString(bean.getLng());
        float latitude = Util.getFloatFromString(bean.getLat());
        if (!Util.isLongitudeValid(String.valueOf(longitude))) {
            Util.showToast(this, Constants.HINT_LONGITUDE_INVALID);
            return;
        } else if (!Util.isLatitudeValid(String.valueOf(latitude))) {
            Util.showToast(this, Constants.HINT_LATITUDE_INVALID);
            return;
        }
        final LatLng latLng = new LatLng(latitude, longitude);
//        LatLng gcjLatLng = getGcjLatLng(latLng);
//        bean.setLng(String.valueOf(gcjLatLng.longitude));
//        bean.setLat(String.valueOf(gcjLatLng.latitude));
        Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(bean.getName()).snippet(bean.getAddress()));
        marker.setObject(bean);
        addMarkerToList(marker);
        View markerView = LayoutInflater.from(this).inflate(R.layout.layout_custom_marker, null, false);
        marker.setIcon(BitmapDescriptorFactory.fromView(markerView));
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

    private void hilightPersonLocationBean(PersonLocationBean bean) {
        Marker marker = getMarkerFromPersonLocationBean(bean);
        hilightMarker(marker);
    }

    private void movePositionToCenter(PersonLocationBean plb) {
        if (plb != null) {
            float longitude = Util.getFloatFromString(plb.getLng());
            float latitude = Util.getFloatFromString(plb.getLat());
            movePositionToCenter(longitude, latitude);
        }
    }

    private void movePositionToCenter(LatLng latLng) {
        aMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.MAP_ZOOM_LEVEL));
    }

    private void movePositionToCenter(float longitude, float latitude) {
        if (!Util.isLongitudeValid(String.valueOf(longitude))) {
            Util.showToast(this, "经度不合法");
            return;
        } else if (!Util.isLatitudeValid(String.valueOf(latitude))) {
            Util.showToast(this, "纬度不合法");
            return;
        }
        LatLng latLng = new LatLng(latitude, longitude);
//        latLng = getGcjLatLng(latLng);
        movePositionToCenter(latLng);
    }

    private Marker getMarkerFromPersonLocationBean(PersonLocationBean bean) {
        if (bean == null) {
            return null;
        } else {
//            List<Marker> screenMarkers = aMap.getMapScreenMarkers();
            List<Marker> markers = markerList;
            if (ListUtils.isEmpty(markers)) {
                return null;
            } else {
                for (int i = 0; i < markers.size(); ++i) {
                    Marker marker = markers.get(i);
                    PersonLocationBean plb = (PersonLocationBean) marker.getObject();
                    if (bean == plb) {
                        return marker;
                    }
                }
                return null;
            }
        }
    }

    public void startNavi(final PersonLocationBean plb) {
        if (plb == null) {
            Util.showToast(this, INFO_NULL_CANT_NAVIGATE);
            return;
        }
        if (!Util.isLongitudeValid(String.valueOf(currentLongitude))) {
            Util.showToast(this, "当前经度不合法");
            return;
        } else if (!Util.isLatitudeValid(String.valueOf(currentLatitude))) {
            Util.showToast(this, "当前纬度不合法");
            return;
        }

        Util.showMaterialDialog(this, Constants.HINT, Constants.HINT_CONFIRM_TO_NAVIGATE, Constants.CANCEL, new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        }, Constants.CONFIRM, new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                startNavigate(plb);
                dialog.dismiss();
            }
        }, true);

    }

    public void startNavi(Marker marker) {
        if (marker == null || marker.getObject() == null) {
            Util.showToast(this, INFO_NULL_CANT_NAVIGATE);
            return;
        }
        final PersonLocationBean plb = (PersonLocationBean) marker.getObject();
        startNavi(plb);

    }


    public void startNavigate(PersonLocationBean plb) {
        Intent intent = new Intent(this, RouteNaviActivity.class);
//        intent.putExtra("gps", false);
        intent.putExtra(Constants.GPS_KEY, true);
        intent.putExtra(Constants.START_KEY, new NaviLatLng(currentLatitude, currentLongitude));
        double dstLat = Util.getDoubleValueFromString(plb.getLat());
        double dstLon = Util.getDoubleValueFromString(plb.getLng());
        intent.putExtra(Constants.END_KEY, new NaviLatLng(dstLat, dstLon));
        startActivity(intent);

    }

    private void initMarkersListener() {
        aMap.setInfoWindowAdapter(new InfoWindowAdapter(ReceptionMapServiceActivity.this));
        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                marker.hideInfoWindow();
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

//        aMap.setOnMapLongClickListener(new AMap.OnMapLongClickListener() {
//            @Override
//            public void onMapLongClick(LatLng latLng) {
//                if (networkRequestType == NETWORK_REQUEST_TYPE_DRAW_TO_SEARCH){
//                    showTouchToDrawCircleView(true);
//                }
//            }
//        });

    }

    private void removeAllCirclesFromMapAndClearCircleList() {
        removeAllCirclesFromMap();
        clearCirclelList();
    }

    private void removeAllCirclesFromMap() {
        for (int i = 0; i < circleList.size(); ++i) {
            Circle circle = circleList.get(i);
            circle.remove();
        }
    }

    private void clearCirclelList() {
        circleList.clear();
    }

    private void addCircleToList(Circle circle) {
        circleList.add(circle);
    }

    public void showDrawToSearchCircle(LatLng centerLatLng, float radius) {
        removeAllCirclesFromMapAndClearCircleList();
        removeAllHeatmapOverlaysFromMapAndClearHeatmapOverlayList();
        removeAllMarkersFromMapAndClearMarkerList();
        LatLng latLng = centerLatLng;
        Circle circle = aMap.addCircle(new CircleOptions().
                center(latLng).
                radius(radius).
                fillColor(TouchToDrawCircleView.MAP_DRAW_TO_SEARCH_CIRCLE_COLOR).
                strokeWidth(0));
        addCircleToList(circle);
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
        adapter.setNewData(null);
//        adapter.notifyDataSetChanged();
    }

    private void addMarkerToList(Marker marker) {
        markerList.add(marker);
    }

    private void showMarkersFromBeanList(List<PersonLocationBean> list) {
        removeAllMarkersFromMap();
//        removeAllCirclesFromMapAndClearCircleList();
        if (ListUtils.isEmpty(list)) {
            return;
        } else {
            for (int i = 0; i < list.size(); ++i) {
                PersonLocationBean plb = list.get(i);
                showMarkerOnMap(plb);
            }
            initMarkersListener();
            includeAllPersonLocationBeanList(list);
        }
    }

    private void initFamilyTypeReadableRepresentationFromBeanList(List<PersonLocationBean> list) {
        if (!ListUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); ++i) {
                PersonLocationBean plb = list.get(i);
//                String representation = getFamilyTypeRepresentationFromCode(plb.getFamilyType());
                String familyType = plb.getFamilyType();
                plb.setFamilyTypeReadable(familyType);
//                plb.setFamilyTypeReadable(representation);
            }
        }
    }

//    private void initFamilyTypeDataDictionary() {
//        familyTypeList = DataQueryUtil.getDataDictionaryFromPCode(Constants.FAMILY_TYPE_PCODE);
//    }
//
//    private String getFamilyTypeRepresentationFromCode(String code) {
//        String representation = Constants.EMPTY_STRING;
//        if (StringUtils.isBlank(code)) {
//            return representation;
//        } else {
//            for (int i = 0; i < familyTypeList.size(); ++i) {
//                SRCLoginDataDictionaryBean bean = familyTypeList.get(i);
//                if (code.equals(bean.getCode())) {
//                    representation = bean.getName();
//                    break;
//                }
//            }
//            return representation;
//        }
//    }

//    private String getFamilyTypeCodeFromRepresentation(String representation) {
//        String code = Constants.EMPTY_STRING;
//        if (StringUtils.isBlank(representation)) {
//            return code;
//        } else {
//            for (int i = 0; i < familyTypeList.size(); ++i) {
//                SRCLoginDataDictionaryBean bean = familyTypeList.get(i);
//                if (representation.equals(bean.getName())) {
//                    code = bean.getCode();
//                    break;
//                }
//            }
//            return code;
//        }
//    }

    private void showPersonLocationBeanListAndMarkers(List<PersonLocationBean> list) {
        initFamilyTypeReadableRepresentationFromBeanList(list);
        adapter.setNewData(list);
        boolean isListEmpty = ListUtils.isEmpty(adapter.getData());
        if (isListEmpty) {
            Util.showToast(this, "获取数据为空");
        }
        showRecyclerViewOrFailureView(true, isListEmpty);

        showMarkersFromBeanList(list);

    }

//    private void simulate() {
//        List<PersonLocationBean> list = new ArrayList<>();
//        for (int i = 0; i < 5; ++i) {
//            PersonLocationBean bean = new PersonLocationBean("张三" + i, "111111111" + i, "34237388" + i, "合肥" + i, "城乡低保" + i,
//                    "117." + i, "31");
//            list.add(bean);
//        }
//
//        showPersonLocationBeanListAndMarkers(list);
//    }

//    private void simulateHeatmapData(){
//        GCAHttpResultBaseBean<HeatmapBean> bean = new GCAHttpResultBaseBean<>();
//        List<LngLatCount> pointArr = new ArrayList<>();
//        double x = 117;
//        double y = 31;
//        for (int i = 0; i < 50; ++i){
//            double x_ = Math.random()*0.5-0.25;
//            double y_ = Math.random()*0.5-0.25;
//            LngLatCount llc = new LngLatCount(String.valueOf((int)(50-Math.random()*30)), String.valueOf(x+x_), String.valueOf(y+y_));
//            pointArr.add(llc);
//        }
//        HeatmapBean hb = new HeatmapBean("100", pointArr);
//        bean.setData(hb);
//        showHeatmapData(bean);
//    }

}
