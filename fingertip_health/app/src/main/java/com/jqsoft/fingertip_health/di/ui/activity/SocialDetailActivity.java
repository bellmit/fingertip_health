package com.jqsoft.fingertip_health.di.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.SocailDetailFaimilyMemAdapter;
import com.jqsoft.fingertip_health.adapter.SocailDetailFlieMemAdapter;
import com.jqsoft.fingertip_health.adapter.SocailDetailHisAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.DetaiFamilMember;
import com.jqsoft.fingertip_health.bean.DetailHelpResult;
import com.jqsoft.fingertip_health.bean.DetailUplodeFile;
import com.jqsoft.fingertip_health.bean.SocialDetailBean;
import com.jqsoft.fingertip_health.bean.SubmitMapLocationResultBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.map_navi.PersonLocationBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.SocialDetailActivityContract;
import com.jqsoft.fingertip_health.di.module.SocialDetailActivityModule;
import com.jqsoft.fingertip_health.di.presenter.SocialDetailPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.MapUtil;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Jerry on 2017/12/28.
 */

public class SocialDetailActivity extends AbstractActivity implements SocialDetailActivityContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, View.OnClickListener {
    private String batchNo;
    private String userName;
    private String userSex;
    private String idCard;
    private String filePath;
    private TextView online_consultation_title;
    private LinearLayout ll_back;
    private List<String> father_List;// 父层数据
    private ArrayList<SocialDetailBean> sonList;
    private ArrayList<DetaiFamilMember> familyMember = new ArrayList<>();
    private ArrayList<DetailUplodeFile> UploadFile = new ArrayList<>();
    private ArrayList<DetailHelpResult> HelpResult = new ArrayList<>();
    private SocailDetailFaimilyMemAdapter faimilyMemAdapter;
    private SocailDetailFlieMemAdapter flieMemAdapter;
    private SocailDetailHisAdapter hisAdapter;
    @BindView(R.id.basename)
    TextView basename;
    @BindView(R.id.basesex)
    TextView basesex;
    @BindView(R.id.basenative)
    TextView basenative;
    @BindView(R.id.basebirthday)
    TextView basebirthday;
    @BindView(R.id.baseidcard)
    TextView baseidcard;
    @BindView(R.id.basehunfou)
    TextView basehunfou;
    @BindView(R.id.basefaimilytype)
    TextView basefaimilytype;
    @BindView(R.id.basehuji)
    TextView basehuji;
    @BindView(R.id.baseadd)
    TextView baseadd;
    @BindView(R.id.poornun)
    TextView poornun;
    @BindView(R.id.faimilylist)
    ListView faimilylist;
    @BindView(R.id.filelist)
    ListView filelist;
    @BindView(R.id.sociallist)
    ListView sociallist;

    private Boolean base_flag = false;
    private Boolean faimailymem_flag = true;
    private Boolean faimailyAddress_flag = false;
    private Boolean file_flag = false;
    private Boolean poor_flag = false;
    private Boolean his_flag = true;
    private Boolean isAnimating = false;
    @BindView(R.id.objectname)
    TextView objectname;
    @BindView(R.id.objectsex)
    ImageView objectsex;
    @BindView(R.id.objectidcard)
    TextView objectidcard;
    @BindView(R.id.sv_content)
    ScrollView sv_content;
    @BindView(R.id.title_baseinfo)
    RelativeLayout title_baseinfo;
    @BindView(R.id.title_basectx)
    LinearLayout title_basectx;
    @BindView(R.id.title_famailyadd)
    RelativeLayout title_famailyadd;
    @BindView(R.id.ctx_famailyadd)
    LinearLayout ctx_famailyadd;
    @BindView(R.id.title_famailymem)
    RelativeLayout title_famailymem;
    @BindView(R.id.lay_family_member)
    View famialymen;
    //    @BindView(R.id.ll_family_mem)
//    LinearLayout llFamilyMember;
    @BindView(R.id.title_file)
    RelativeLayout title_file;
    @BindView(R.id.fileview)
    LinearLayout fileview;
    @BindView(R.id.title_poornun)
    RelativeLayout title_poornun;
    @BindView(R.id.ctx_nun)
    LinearLayout ctx_nun;
    @BindView(R.id.title_his)
    RelativeLayout title_his;
    @BindView(R.id.lay_his)
    View content_his;
    @BindView(R.id.iv_title)
    SimpleDraweeView iv_title;
    @BindView(R.id.located)
    TextView located;
    @BindView(R.id.ll_xy)
    LinearLayout ll_xy;

    @BindView(R.id.ll_line)
    LinearLayout ll_line;
    //    @BindView(R.id.content_his)
//    LinearLayout content_his;
    private int mFoldedViewMeasureHeight_baseinfo;
    private int mFoldedViewMeasureHeight_add;
    private int mFoldedViewMeasureHeight_mem;
    private int mFoldedViewMeasureHeight_file;
    private int mFoldedViewMeasureHeight_poor;
    private int mFoldedViewMeasureHeight_his;

    private float mDensity;

    int GET_SELECTED_LOCATION_ON_MAP_REQUEST_CODE=1000;

    //定位后保存的经纬度及地址
    String locatedLongitude = Constants.EMPTY_STRING;
    String locatedLatitude = Constants.EMPTY_STRING;
    String locatedAddress = Constants.EMPTY_STRING;
    GCAHttpResultBaseBean<SocialDetailBean> socialDetailBeanGCAHttpResultBaseBean;
    @Inject
    SocialDetailPresenter mPresenter;
    private String mapId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_socialdetail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        online_consultation_title = (TextView) findViewById(R.id.online_consultation_title);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        online_consultation_title.setText("救助对象");
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title_baseinfo.setOnClickListener(SocialDetailActivity.this);
        title_famailyadd.setOnClickListener(SocialDetailActivity.this);
        title_famailymem.setOnClickListener(SocialDetailActivity.this);
        title_file.setOnClickListener(SocialDetailActivity.this);
        title_poornun.setOnClickListener(SocialDetailActivity.this);
        title_his.setOnClickListener(SocialDetailActivity.this);

        mDensity = getResources().getDisplayMetrics().density;
        located.setVisibility(View.GONE);
//        located.setVisibility(View.GONE);
//        baseadd.setVisibility(View.GONE);
        measureHeight();

//        llFamilyMember= (LinearLayout) famialymen.findViewById(R.id.famialymen);

        initFamilyAddressLocateAndSaveButtonListener();
    }

    private void measureHeight(){
        //获取布局的高度
//        int w = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,
//                View.MeasureSpec.AT_MOST);
//        int h = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,
//                View.MeasureSpec.AT_MOST);
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        title_basectx.measure(w, h);
//        baseadd.measure(w, h);
        ctx_famailyadd.measure(w, h);
        famialymen.measure(w, h);
        fileview.measure(w, h);
        ctx_nun.measure(w, h);
        content_his.measure(w, h);

    }

    private void initFamilyAddressHeight(){
//        ViewTreeObserver vto = ctx_famailyadd.getViewTreeObserver();
//        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                ctx_famailyadd.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                int h = ctx_famailyadd.getHeight();
//                int w = ctx_famailyadd.getWidth();
//                int mh = ctx_famailyadd.getMeasuredHeight();
//                int mw = ctx_famailyadd.getMeasuredWidth();
//            }
//        });
//        ctx_famailyadd.post(new Runnable() {
//            @Override
//            public void run() {
//                int h = ctx_famailyadd.getMeasuredHeight();
//                mFoldedViewMeasureHeight_add = h;
//                ViewGroup.LayoutParams lp = ctx_famailyadd.getLayoutParams();
//                lp.height = mFoldedViewMeasureHeight_add;
//                ctx_famailyadd.setLayoutParams(lp);
//
//            }
//        });
//        int height2 = ctx_famailyadd.getMeasuredHeight();
//        mFoldedViewMeasureHeight_add = (int) (height2);
//        ViewGroup.LayoutParams lp = ctx_famailyadd.getLayoutParams();
//        lp.height = height2;
//        ctx_famailyadd.setLayoutParams(lp);
    }

    private void initFamilyAddressLocateAndSaveButtonListener(){
        View locateView = ctx_famailyadd.findViewById(R.id.btn_locate);
        View saveView = ctx_famailyadd.findViewById(R.id.btn_save);
        RxView.clicks(locateView)
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
//                        Util.gotoActivity(SocialDetailActivity.this, SelectMapLocationActivity.class);
//                        Intent intent = new Intent(SocialDetailActivity.this, SelectMapLocationActivity.class);
                        Intent intent = new Intent(SocialDetailActivity.this, MainMapActivity.class);
                        if(socialDetailBeanGCAHttpResultBaseBean.getData().getMapMarker()==null){
                            intent.putExtra("SocialDeatailLocatLat","null");
                            intent.putExtra("SocialDeatailLocatLng","null");

                        }else {
                            intent.putExtra("SocialDeatailLocatLat",socialDetailBeanGCAHttpResultBaseBean.getData().getMapMarker().getLat());
                            intent.putExtra("SocialDeatailLocatLng",socialDetailBeanGCAHttpResultBaseBean.getData().getMapMarker().getLng());

                        }

                        startActivityForResult(intent, GET_SELECTED_LOCATION_ON_MAP_REQUEST_CODE);
                    }
                });
        RxView.clicks(saveView)
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
                        doSubmitMapLocation();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (10 == resultCode){
            if (GET_SELECTED_LOCATION_ON_MAP_REQUEST_CODE==requestCode){
                String emptyHint = "定位信息为空";
                if (data!=null){
                    PersonLocationBean plb = (PersonLocationBean) data.getSerializableExtra(Constants.SELECTED_MAP_LOCATION_KEY);
                    if (plb!=null){
//                        initFamilyAddressHeight();

                        locatedLongitude = plb.getLng();
                        locatedLatitude = plb.getLat();
                        locatedAddress = plb.getAddress();
                        ll_xy.setVisibility(View.VISIBLE);
                        double[] a=  MapUtil.bdToGaoDe(Double.valueOf(locatedLatitude),Double.valueOf(locatedLongitude));
                        located.setText(""+locatedLongitude+"        "+locatedLatitude);
                        located.setVisibility(View.VISIBLE);
                        baseadd.setText(locatedAddress);


//                        measureHeight();
//                        initFamilyAddressHeight();
                    } else {
                        Util.showToast(SocialDetailActivity.this, emptyHint);
                    }
                } else {
                    Util.showToast(SocialDetailActivity.this, emptyHint);
                }
            }
        }
    }

    @Override
    protected void initInject() {

        DaggerApplication.get(this)
                .getAppComponent()
                .addSocailDetail(new SocialDetailActivityModule(this))
                .inject(this);

    }

    public Map<String, String> getRequestMap() {
        // batchNo = "40170922121141940";
        Map<String, String> map = ParametersFactory.getSocialDetail(this, batchNo);
        return map;
    }

    public Map<String, String> getSubmitMapLocationRequestMap(){
        //todo mapId参数暂时传为空字符串
       // String mapId = Constants.EMPTY_STRING;
        Map<String, String> map = ParametersFactory.getSubmitMapLocationForSocialDetail(this, batchNo, locatedAddress,
                locatedLatitude, locatedLongitude, mapId);
        return map;
    }

    public void doSubmitMapLocation(){
        final String parameterUnsatisfiedHint = "将要上传的坐标信息不正确";
        Util.showGifProgressDialog(this);
        rx.Observable.just(locatedLongitude, locatedLatitude, locatedAddress)
//                .delay(3, TimeUnit.SECONDS)
                .all(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !StringUtils.isBlank(s);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                Util.hideGifProgressDialog(SocialDetailActivity.this);
            }

            @Override
            public void onError(Throwable e) {
                Util.hideGifProgressDialog(SocialDetailActivity.this);
                Util.showToast(SocialDetailActivity.this, parameterUnsatisfiedHint);
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean){
                    submitMapLocation();
                } else {
                    Util.showToast(SocialDetailActivity.this, parameterUnsatisfiedHint);
                }
            }
        });
    }

    public void submitMapLocation(){
        Map<String, String> map = getSubmitMapLocationRequestMap();
        mPresenter.submitMapLocation(map);

    }


    @Override
    protected void loadData() {

        Bundle bundle = getIntent().getExtras();
        batchNo = bundle.getString("batchNo");//读出数据
        userName = bundle.getString("userName");
        userSex = bundle.getString("userSex");
        idCard = bundle.getString("cardNo");
        filePath = bundle.getString("filePath");

        String imageUrl = Version.FIND_FILE_URL_BASE + filePath;
//         GlideUtils.loadImageNew(imageUrl, (ImageView) helper.getView(R.id.iv_title));
//        GlideUtils.load(context,imageUrl,(ImageView) helper.getView(R.id.iv_title));
        Uri uri = Uri.parse(imageUrl);
        iv_title.setImageURI(uri);

        Map<String, String> map = getRequestMap();
        mPresenter.getSocialDetails(map, false);

//        GCAHttpResultBaseBean<SocialDetailBean> simulatedBean = getSimulatedBean();
//        populateData(simulatedBean);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {

    }

//    private GCAHttpResultBaseBean<SocialDetailBean> getSimulatedBean(){
//        // DetailBaseInfo BaseInfo = new DetailBaseInfo("1","2","3","4","5","6","7","8","9","10");
//         List<DetaiFamilMember> FamilyMember = new ArrayList<>();
//         for (int i = 0; i < 3; ++i){
//             DetaiFamilMember dfm = new DetaiFamilMember("1","2","3","4");
//             FamilyMember.add(dfm);
//         }
//         List<DetailUplodeFile> UploadFile=new ArrayList<>();
//        for (int i = 0; i < 1; ++i){
//            DetailUplodeFile duf = new DetailUplodeFile("1","2");
//            UploadFile.add(duf);
//        }
//         DetailPoorBatch PoorBatch=new DetailPoorBatch("100");
//         List<DetailHelpResult> HelpResult=new ArrayList<>();
//         for (int i = 0; i < 5; ++i){
//             DetailHelpResult dhr = new DetailHelpResult("1","2","3","4");
//             HelpResult.add(dhr);
//         }
//
//         SocialDetailBean sdb = new SocialDetailBean(BaseInfo, FamilyMember, UploadFile, PoorBatch, HelpResult);
//        GCAHttpResultBaseBean<SocialDetailBean> bean = new GCAHttpResultBaseBean<>("1","success", sdb);
//        return bean;
//    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<SocialDetailBean> bean) {
        socialDetailBeanGCAHttpResultBaseBean=bean;
        populateData(bean);


    }

    private void populateData(GCAHttpResultBaseBean<SocialDetailBean> bean) {

        if(bean.getData().getMapMarker()==null ){

        }else {
            mapId = bean.getData().getMapMarker().getId();
        }

        basename.setText(bean.getData().getBaseInfo().getName());
        if (bean.getData().getBaseInfo().getSex().equals("sex_1")) {
            basesex.setText("男");
        } else {
            basesex.setText("女");
        }

        objectname.setText(bean.getData().getBaseInfo().getName());
        if (bean.getData().getBaseInfo().getSex().equals("sex_1")) {
            objectsex.setImageResource(R.mipmap.icon_sex_man);
        } else {
            objectsex.setImageResource(R.mipmap.icon_sex_woman);
        }
        objectidcard.setText(bean.getData().getBaseInfo().getCardNo());
        // Date date = bean.getData().getBaseInfo().getBirthday();
        //    Util.stringToDate(bean.getData().getBaseInfo().getBirthday());
        basebirthday.setText(bean.getData().getBaseInfo().getBirthday());
        baseidcard.setText(bean.getData().getBaseInfo().getCardNo());
        basefaimilytype.setText(bean.getData().getBaseInfo().getFamilyType());
        baseadd.setText(bean.getData().getBaseInfo().getFamilyAddress());
        poornun.setText(bean.getData().getPoorBatch().getPoorScore());
        familyMember.addAll(bean.getData().getFamilyMember());
        UploadFile.addAll(bean.getData().getUploadFile());
        HelpResult.addAll(bean.getData().getHelpResult());
        basenative.setText(bean.getData().getBaseInfo().getNation());
        located.setVisibility(View.VISIBLE);
        if(bean.getData().getMapMarker()==null){

        }else {
            double[] a=  MapUtil.bdToGaoDe(Double.valueOf(bean.getData().getMapMarker().getLat()),Double.valueOf(bean.getData().getMapMarker().getLng()));

            located.setText(""+bean.getData().getMapMarker().getLng()+"        "+""+bean.getData().getMapMarker().getLat());
        }

        String registerType = bean.getData().getBaseInfo().getRegisterType();
        String national = bean.getData().getBaseInfo().getNation();
        String marital = bean.getData().getBaseInfo().getMaritalStatus();
        final List<SRCLoginDataDictionaryBean> myregpro = DataSupport.where(" pcode=? and state=?", "reg_type","0").find(SRCLoginDataDictionaryBean.class);
        if (!TextUtils.isEmpty(registerType)) {
            for (int i = 0; i < myregpro.size(); i++) {
                if (registerType.equals(myregpro.get(i).getCode())) {
                    basehuji.setText(myregpro.get(i).getName());
                }
            }
        }

//        final List<SRCLoginDataDictionaryBean> myregpro2 = DataSupport.where(" pcode=? ", "nation").find(SRCLoginDataDictionaryBean.class);
//        if (!TextUtils.isEmpty(national)) {
//            for (int i = 0; i < myregpro2.size(); i++) {
//                if (national.equals(myregpro2.get(i).getCode())) {
//                    basenative.setText(myregpro2.get(i).getName());
//                }
//            }
//        }
        final List<SRCLoginDataDictionaryBean> myregpro3 = DataSupport.where(" pcode=? and state=?", "marital_status","0").find(SRCLoginDataDictionaryBean.class);
        if ( marital==null || marital.equals("null") ||  TextUtils.isEmpty(marital)) {
            basehunfou.setText(("无"));
        }else {
            for (int i = 0; i < myregpro3.size(); i++) {
                if (marital.equals(myregpro3.get(i).getCode())) {
                    basehunfou.setText(myregpro3.get(i).getName());
                }else{
                    basehunfou.setText(("无"));
                }

            }
        }

        faimilyMemAdapter = new SocailDetailFaimilyMemAdapter(this, familyMember);
        faimilylist.setAdapter(faimilyMemAdapter);
//        faimilyMemAdapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(faimilylist, faimilyMemAdapter);
        flieMemAdapter = new SocailDetailFlieMemAdapter(this, UploadFile);
        filelist.setAdapter(flieMemAdapter);
//        flieMemAdapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(filelist, flieMemAdapter);

        hisAdapter = new SocailDetailHisAdapter(this, HelpResult);
        sociallist.setAdapter(hisAdapter);
//        hisAdapter.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(sociallist, hisAdapter);

        int height1 = title_basectx.getMeasuredHeight();
        mFoldedViewMeasureHeight_baseinfo = (int) (height1);

        int height2 = ctx_famailyadd.getMeasuredHeight();
        mFoldedViewMeasureHeight_add = (int) (height2);

        int height3 = setListViewHeightBasedOnChildren(faimilylist, faimilyMemAdapter);
        mFoldedViewMeasureHeight_mem = (int) (height3);

        int height4 = setListViewHeightBasedOnChildren(filelist, flieMemAdapter);
        mFoldedViewMeasureHeight_file = (int) (height4);

        int height5 = ctx_nun.getMeasuredHeight();
        mFoldedViewMeasureHeight_poor = (int) (height5);

        int height6 = setListViewHeightBasedOnChildren(sociallist, hisAdapter);
        mFoldedViewMeasureHeight_his = (int) (height6);

        sv_content.postInvalidate();
    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<SocialDetailBean> bean) {

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        Util.showToast(getApplicationContext(), message);

    }

    @Override
    public void onSubmitMapLocationSuccess(SubmitMapLocationResultBean bean) {
        Util.showToast(this, "坐标位置提交成功");
        located.setText(""+locatedLongitude+"        "+locatedLatitude);


    }

    @Override
    public void onSubmitMapLocationFailure(String message) {
        Util.showToast(this, "坐标位置提交失败");
    }

    private void adjustListViewHeight(ListView listView, int dstHeight) {
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = dstHeight;
        listView.setLayoutParams(params);

//        int height3 = setListViewHeightBasedOnChildren(faimilylist, faimilyMemAdapter);
//
//        int height4 = setListViewHeightBasedOnChildren(filelist, flieMemAdapter);
//
//
//        int height6 = setListViewHeightBasedOnChildren(sociallist, hisAdapter);

    }

    public static int setListViewHeightBasedOnChildren(ListView listView, Adapter adapter) {
        //获得adapter
        // SocailDetailHisAdapter adapter = (SocailDetailHisAdapter) listView.getAdapter();
        adapter = listView.getAdapter();
        if (adapter == null) {
            return 0;
        }

        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//            listItem.measure(0, 0);
            //计算总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        int div = (listView.getDividerHeight() * (adapter.getCount() - 1));
        int ultimateHeight = totalHeight + div;
        //计算分割线高度
        params.height = ultimateHeight;
        //给listview设置高度
        listView.setLayoutParams(params);
        return ultimateHeight;
    }

    private ValueAnimator createDropAnimator(final View view, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
//        animator.setDuration(1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
                LogUtil.i("drop animator height:" + value);
            }
        });
        return animator;
    }

    private void animateClose(final View view, int calcHeight) {
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view, calcHeight, 0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                sv_content.postInvalidate();
                isAnimating = false;
            }
        });
        animator.start();
    }

    private void animateOpen(View view, int mFoldedViewMeasureHeight) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view, 0, mFoldedViewMeasureHeight);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimating = false;
            }
        });
        animator.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_baseinfo:
                if (mFoldedViewMeasureHeight_baseinfo > 0) {

                    if (isAnimating) return;
                    //如果动画没在执行,走到这一步就将isAnimating制为true , 防止这次动画还没有执行完毕的
                    //情况下,又要执行一次动画,当动画执行完毕后会将isAnimating制为false,这样下次动画又能执行
                    isAnimating = true;
                    if (title_basectx.getVisibility() == View.GONE) {

                        //打开动画
                        animateOpen(title_basectx, mFoldedViewMeasureHeight_baseinfo);
                    } else {
                        //关闭动画
                        animateClose(title_basectx, mFoldedViewMeasureHeight_baseinfo);
                    }
                    sv_content.postInvalidate();

                } else {
                    Util.showToast(getApplicationContext(), "没有基本信息");
                }
                break;
            case R.id.title_famailyadd:
                if (mFoldedViewMeasureHeight_add > 0) {
                    if (isAnimating) return;
                    //如果动画没在执行,走到这一步就将isAnimating制为true , 防止这次动画还没有执行完毕的
                    //情况下,又要执行一次动画,当动画执行完毕后会将isAnimating制为false,这样下次动画又能执行
                    isAnimating = true;
//                    int measuredHeight = ctx_famailyadd.getMeasuredHeight();
                    if (ctx_famailyadd.getVisibility() == View.GONE) {
                        //打开动画
                        animateOpen(ctx_famailyadd, mFoldedViewMeasureHeight_add);
//                        animateOpen(ctx_famailyadd, measuredHeight*2);
                    } else {
                        //关闭动画
                        animateClose(ctx_famailyadd, mFoldedViewMeasureHeight_add);
                    }
                    sv_content.postInvalidate();
                } else {
                    Util.showToast(getApplicationContext(), "没有家庭住址信息");
                }
                break;
            case R.id.title_famailymem:
                if (mFoldedViewMeasureHeight_mem > 0) {
                    if (isAnimating) return;
                    //如果动画没在执行,走到这一步就将isAnimating制为true , 防止这次动画还没有执行完毕的
                    //情况下,又要执行一次动画,当动画执行完毕后会将isAnimating制为false,这样下次动画又能执行
                    isAnimating = true;
                    if (famialymen.getVisibility() == View.GONE) {
                        //打开动画
                        animateOpen(famialymen, mFoldedViewMeasureHeight_mem);//
                    } else {
                        //关闭动画
                        animateClose(famialymen, mFoldedViewMeasureHeight_mem);
                    }

                    sv_content.postInvalidate();
                } else {
                    Util.showToast(getApplicationContext(), "没有家庭成员信息");
                }
                // toggleVisibility(famialymen);
                break;
            case R.id.title_file:
                if (mFoldedViewMeasureHeight_file > 0) {
                    if (isAnimating) return;
                    //如果动画没在执行,走到这一步就将isAnimating制为true , 防止这次动画还没有执行完毕的
                    //情况下,又要执行一次动画,当动画执行完毕后会将isAnimating制为false,这样下次动画又能执行
                    isAnimating = true;
                    if (fileview.getVisibility() == View.GONE) {
                        //打开动画
                        animateOpen(fileview, mFoldedViewMeasureHeight_file);
                    } else {
                        //关闭动画
                        animateClose(fileview, mFoldedViewMeasureHeight_file);
                    }
                    sv_content.postInvalidate();
                } else {
                    Util.showToast(getApplicationContext(), "没有附件信息");
                }
                break;
            case R.id.title_poornun:
                if (mFoldedViewMeasureHeight_poor > 0) {
                    if (isAnimating) return;
                    //如果动画没在执行,走到这一步就将isAnimating制为true , 防止这次动画还没有执行完毕的
                    //情况下,又要执行一次动画,当动画执行完毕后会将isAnimating制为false,这样下次动画又能执行
                    isAnimating = true;
                    if (ctx_nun.getVisibility() == View.GONE) {
                        //打开动画
                        animateOpen(ctx_nun, mFoldedViewMeasureHeight_poor);
                    } else {
                        //关闭动画
                        animateClose(ctx_nun, mFoldedViewMeasureHeight_poor);

                    }
                    sv_content.postInvalidate();
                } else {
                    Util.showToast(getApplicationContext(), "没有贫困指数信息");
                }
                break;
            case R.id.title_his:
                if (mFoldedViewMeasureHeight_his > 0) {
                    if (isAnimating) return;
                    //如果动画没在执行,走到这一步就将isAnimating制为true , 防止这次动画还没有执行完毕的
                    //情况下,又要执行一次动画,当动画执行完毕后会将isAnimating制为false,这样下次动画又能执行
                    isAnimating = true;
                    if (content_his.getVisibility() == View.GONE) {
                        //打开动画
                        animateOpen(content_his, mFoldedViewMeasureHeight_his);
                    } else {
                        //关闭动画
                        animateClose(content_his, mFoldedViewMeasureHeight_his);

                    }

                    sv_content.postInvalidate();
                } else {
                    Util.showToast(getApplicationContext(), "没有救助历史信息");
                }
                break;


        }

    }

    private void toggleVisibility(View view) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }

    }
}
