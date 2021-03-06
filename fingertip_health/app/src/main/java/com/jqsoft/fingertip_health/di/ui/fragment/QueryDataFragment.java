package com.jqsoft.fingertip_health.di.ui.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.SignDoctorNameAndPhoneAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.DoctorTeamInfo;
import com.jqsoft.fingertip_health.bean.ImageAndTextBean;
import com.jqsoft.fingertip_health.bean.InHospitalInspectBeanList;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.datasource.DataSourceFactory;
import com.jqsoft.fingertip_health.di.contract.ModuleListFragmentContract;
import com.jqsoft.fingertip_health.di.module.ModuleListFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.ModuleListFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.AccessFileActivity;
import com.jqsoft.fingertip_health.di.ui.activity.AdviceActivity;
import com.jqsoft.fingertip_health.di.ui.activity.DeviceListActivity;
import com.jqsoft.fingertip_health.di.ui.activity.GuideActivity;
import com.jqsoft.fingertip_health.di.ui.activity.HandleProgress;
import com.jqsoft.fingertip_health.di.ui.activity.IgGuideActivity;
import com.jqsoft.fingertip_health.di.ui.activity.ListGuideActivity;
import com.jqsoft.fingertip_health.di.ui.activity.NotificationActivity;
import com.jqsoft.fingertip_health.di.ui.activity.PendExecuActivity;
import com.jqsoft.fingertip_health.di.ui.activity.PolicyActivity;
import com.jqsoft.fingertip_health.di.ui.activity.QuestionActivity;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionActivity;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionListActivity;
import com.jqsoft.fingertip_health.di.ui.activity.SocialAssistanceObject;
import com.jqsoft.fingertip_health.di.ui.activity.SocialHistoryActivity;
import com.jqsoft.fingertip_health.di.ui.activity.WorkbenchActivity;
import com.jqsoft.fingertip_health.di.ui.activity.map_navi.MapServiceActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.listener.onBlueToothsClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import me.nereo.multi_image_selector.MultiImageSelector;
import okhttp3.RequestBody;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017-06-23.
 */

public class QueryDataFragment extends AbstractFragment implements WorkbenchActivity.OnSendidcardclickListner,
        ModuleListFragmentContract.View {
    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    @Inject
    ModuleListFragmentPresenter mPresenter;

    private static QueryDataFragment instance = null;

    private CompositeSubscription compositeSubscription;

    InHospitalInspectBeanList ib;

    public static QueryDataFragment getModuleistFragment() {
        if (instance == null) {
            instance = new QueryDataFragment();
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_empty_linear_layout;
    }

    @Override
    protected void initData() {
        btAdapt = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    protected void initView() {
        instance = this;
        addModuleListContent();
        if (compositeSubscription == null) {
            registerModuleClickEvent();
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addModuleListFragment(new ModuleListFragmentModule(this))
                .inject(this);
    }

    public void reassignToolbar() {
        WorkbenchActivity workbenchActivity = (WorkbenchActivity) getActivity();
        if (workbenchActivity != null) {
            Toolbar toolbar = (Toolbar) workbenchActivity.findViewById(R.id.toolbar4);
            //LogUtil.i("MineFragment initView toolbar:"+toolbar);
            workbenchActivity.setToolBarWithNoBackButtonAndNoTitle(toolbar);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void addModuleListContent() {
//        ModuleListContentNew mlc = new ModuleListContentNew(getActivity());
        List<View> viewList = DataSourceFactory.getModuleViewList(getActivity());
        if (!ListUtils.isEmpty(viewList)) {
            for (int i = 0; i < viewList.size(); ++i) {
                llRoot.addView(viewList.get(i));
            }
        }
    }

    private void test() {
        String s = null;
        int l = s.length();
    }

    public void gotoModule(int moduleId) {
        switch (moduleId) {
            case Constants.MODULE_ID_POLICY_NEWS:
                Util.gotoActivity(getActivity(), PolicyActivity.class);
                break;
            case Constants.MODULE_ID_NOTIFICATION:
                Util.gotoActivity(getActivity(), NotificationActivity.class);
                break;

            case Constants.MODUBLE_ID_CIVIL:
//                test();
                Util.gotoActivity(getActivity(), SocialAssistanceObject.class);
                break;
            case Constants.MODUBLE_ID_HANDLEPROGRESS:
                //Util.gotoActivity(getActivity(), HandleProgress.class);
                Intent intent = new Intent(getActivity().getApplicationContext(), HandleProgress.class);
                intent.putExtra("pageType", "1");
                startActivity(intent);
                break;
            case Constants.MODUBLE_ID_HANDLEHIS:
                //Util.gotoActivity(getActivity(), HandleProgress.class);
                Intent intenthis = new Intent(getActivity().getApplicationContext(), SocialHistoryActivity.class);
              //  intenthis.putExtra("pageType", "2");
                startActivity(intenthis);
                break;
            case Constants.MODULE_ID_MAP_SERVICE:
                Util.gotoActivity(getActivity(), MapServiceActivity.class);
                break;

            case Constants.MODULE_ID_SOCIAILORGNATION:
                Util.showToast(getActivity().getApplicationContext(), "????????????,????????????!");
                break;
            case Constants.MODULE_ID_QUERY:
                Util.showToast(getActivity().getApplicationContext(), "????????????,????????????!");
                break;
            case Constants.MODULE_ID_SOCIALSTATION:
//                showGifProgressDialog(getActivity());
                Util.setFromCollection("0");

                if(IdentityManager.getAreaLevel(getActivity()).equals("area_3")  ){
                    Util.setReception("JgTypeJzz");
                    SRCLoginAreaBean pb=new SRCLoginAreaBean();
                    String name= IdentityManager.getLoginSuccessUsername(getContext());
                    pb.setAreaName(Identity.srcInfo.getArea());
                    pb.setAreaCode( Identity.srcInfo.getAreaId());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.RECEPTION_ITEM_ACTIVITY_KEY,   pb);


                    Util.gotoActivityWithBundle(getActivity(), ReceptionListActivity.class, bundle);

                }else
                    if(IdentityManager.getAreaLevel(getActivity()).equals("area_4") ||IdentityManager.getAreaLevel(getActivity()).equals("area_5") ){

                Util.setReception("JgTypeJzz");


                SRCLoginAreaBean pb=new SRCLoginAreaBean();
                String name= IdentityManager.getLoginSuccessUsername(getContext());
                pb.setAreaName(Identity.srcInfo.getArea());
                pb.setAreaCode( Identity.srcInfo.getAreaId());
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.RECEPTION_ITEM_ACTIVITY_KEY,   pb);


                Util.gotoActivityWithBundle(getActivity(), ReceptionListActivity.class, bundle);

            }else  {
                    Util.choicArea(getActivity());
                    Util.setReception("JgTypeJzz");
                    Util.gotoActivity(getActivity(), ReceptionActivity.class);
                }

//                Util.showToast(getActivity().getApplicationContext(), "????????????,????????????!");
                break;

            case Constants.MODULE_ID_USUALLYTRUBLE:
                Util.gotoActivity(getActivity(), QuestionActivity.class);
//                Util.showToast(getActivity().getApplicationContext(), "????????????,????????????!");
                break;
            case Constants.MODULE_ID_REJESTION:
                Util.gotoActivity(getActivity(), AdviceActivity.class);
//                Util.showToast(getActivity().getApplicationContext(), "????????????,????????????!");
                break;

//            case Constants.MODULE_ID_MY_SIGN_INFO:
//                Identity.shouldReadIdCard = false;
//                RxBus.getDefault().post(Constants.EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_INDEX, Constants.WORKBENCH_TRANSACT);
//                break;
//            case Constants.MODULE_ID_MY_SIGN_AGREEMENT:
//                Util.gotoActivity(getActivity(), SignedAgreement.class);
//                break;
//            case Constants.MODULE_ID_SIGN_POLICY_TRANSLATE:
//                Util.gotoActivity(getActivity(), PolicyActivity.class);
//                break;
//            case Constants.MODULE_ID_SIGN_SERVICE_PACKAGE_TRANSLATE:
//                Util.gotoActivity(getActivity(), SignDoctorSeverPakes.class);
//                break;
//            case Constants.MODULE_ID_INTELLIGENT_HONOUR_AGREEMENT_REMIND:
//                Bundle bundle = new Bundle();
////                bundle.putString(Constants.CARD_NO_KEY, Identity.getCardNo());
//                bundle.putString(Constants.CARD_NO_KEY, IdentityManager.getCardNo(getActivity()));
//                bundle.putString(Constants.PERSON_ID_KEY, IdentityManager.getPersonID(getActivity()));
//                Util.gotoActivityWithBundle(getActivity(), SmartAlertActivity.class, bundle);
//                break;
//            case Constants.MODULE_ID_SIGN_SERVICE_ASSESS:
//                Util.gotoActivity(getActivity(), ClientSignServiceAssessActivity.class);
//                break;
//            case Constants.MODULE_ID_APPOINTMENT_SIGN_SERVICE:
//                initReservationSignService();
////                Util.gotoActivity(getActivity(), ReserrverServerActivity.class);
//                break;
//            case Constants.MODULE_ID_CALL_DOCTOR:
//                doCallDoctor();
////                String doctorPhoneNumber = Identity.getPhoneNumber();
////                Util.dial(getActivity(), doctorPhoneNumber);
//                break;
//            case Constants.MODULE_ID_ONLINE_CONSULTATION:
//                Identity.shouldReadIdCard = false;
//                RxBus.getDefault().post(Constants.EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_INDEX, Constants.WORKBENCH_QUERY);
//
//                break;
            case Constants.MODULE_ID_GUIDE:
                Util.setFromCollection("0");
                String sAreaLeavel=Util.choicArea(getActivity());

                if(IdentityManager.getAreaLevel(getActivity()).equals("area_1")  ){


                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.FROM_WHERE_ACTIVITY_KEY,  "BSZN");
                    Util.gotoActivityWithBundle(getActivity(), ListGuideActivity.class, bundle);

                }else  {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.GUIDE_ITEM_ACTIVITY_KEY,  Identity.srcInfo.getAreaId());
                    Util.gotoActivityWithBundle(getActivity(), GuideActivity.class, bundle);
                }



//                Util.gotoActivity(getActivity(), WorkGuideActivity.class);
//                Identity.shouldReadIdCard = false;
//                RxBus.getDefault().post(Constants.EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_INDEX, Constants.WORKBENCH_QUERY);
                break;
            case Constants.MODULE_ID_IG_GUIDE:
                String sAreaLeavel1=Util.choicArea(getActivity());
                if(IdentityManager.getAreaLevel(getActivity()).equals("area_1") || IdentityManager.getAreaLevel(getActivity()).equals("area_2")){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.FROM_WHERE_ACTIVITY_KEY,  "ZNYD");
                    Util.gotoActivityWithBundle(getActivity(), ListGuideActivity.class, bundle);
                }else  if(IdentityManager.getAreaLevel(getActivity()).equals("area_3")  ) {
                    Bundle bundle = new Bundle();
                    String code = Identity.srcInfo.getAreaId();
                    Util.setAreaCode(code);
                    bundle.putSerializable(Constants.GUIDE_ITEM_ACTIVITY_KEY, code);
                    Util.gotoActivityWithBundle(getActivity(), IgGuideActivity.class, bundle);

                }else  if(IdentityManager.getAreaLevel(getActivity()).equals("area_4")  ) {
                    List<SRCLoginAreaBean>   allcityList = DataSupport.where(" areaCode=? and state=?",Identity.srcInfo.getAreaId(),"0").find(SRCLoginAreaBean.class);

                    Bundle bundle = new Bundle();
                  String code = allcityList.get(0).getAreaPid();
                  Util.setAreaCode(code);
                    bundle.putSerializable(Constants.GUIDE_ITEM_ACTIVITY_KEY, code);
                    Util.gotoActivityWithBundle(getActivity(), IgGuideActivity.class, bundle);

                }else if(IdentityManager.getAreaLevel(getActivity()).equals("area_5")  ){

                    List<SRCLoginAreaBean>   allcityList = DataSupport.where(" areaCode=? and state=?",Identity.srcInfo.getAreaId(),"0").find(SRCLoginAreaBean.class);
                    String code = allcityList.get(0).getAreaPid();
                    List<SRCLoginAreaBean>   allcityList1 = DataSupport.where(" areaCode=? and state=?",code,"0").find(SRCLoginAreaBean.class);
                    String code1 = allcityList1.get(0).getAreaPid();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.GUIDE_ITEM_ACTIVITY_KEY, code1);
                    Util.setAreaCode(code1);
                    Util.gotoActivityWithBundle(getActivity(), IgGuideActivity.class, bundle);
                }





                break;
            case Constants.MODULE_ID_Reception:
                Util.setAreaCode(Identity.srcInfo.getArea());
                Util.setFromCollection("0");
                Util.setReception("JgTypSlzx");
                IdentityManager.getAreaLevel(getActivity());
                if(IdentityManager.getAreaLevel(getActivity()).equals("area_3") ){

                    SRCLoginAreaBean pb=new SRCLoginAreaBean();
                    String name= IdentityManager.getLoginSuccessUsername(getContext());
                    pb.setAreaName(Identity.srcInfo.getArea());
                    pb.setAreaCode( Identity.srcInfo.getAreaId());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.RECEPTION_ITEM_ACTIVITY_KEY,   pb);


                    Util.gotoActivityWithBundle(getActivity(), ReceptionListActivity.class, bundle);
                }else
                    if (IdentityManager.getAreaLevel(getActivity()).equals("area_4")||IdentityManager.getAreaLevel(getActivity()).equals("area_5")){
                    SRCLoginAreaBean pb=new SRCLoginAreaBean();
                    String name= IdentityManager.getLoginSuccessUsername(getContext());


                    pb.setAreaName(Identity.srcInfo.getArea());
                    pb.setAreaCode( Identity.srcInfo.getAreaId());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.RECEPTION_ITEM_ACTIVITY_KEY,   pb);
                    Util.gotoActivityWithBundle(getActivity(), ReceptionListActivity.class, bundle);
                }else {
                    Util.gotoActivity(getActivity(), ReceptionActivity.class);

                }

                break;

//            case Constants.MODULE_ID_SIGN_COMPLAINT:
//                Identity.shouldReadIdCard = false;
//                RxBus.getDefault().post(Constants.EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_INDEX, Constants.WORKBENCH_QUERY);
//                break;
//            case Constants.MODULE_ID_SETTING:
//                Util.gotoActivity(getActivity(), SettingActivityNew.class);
//                break;
            default:
                break;
        }
    }

    private void initReservationSignService() {
//        String cardNo = Identity.getCardNo();
        String cardNo = IdentityManager.getCardNo(getActivity());
        Map<String, String> map = ParametersFactory.getHospitalInspectListMap(getActivity(), cardNo);
        mPresenter.getPeopleSignInfoList(map, false);
    }

    private void doCallDoctor() {
//        String cardNo = Identity.getCardNo();
        String cardNo = IdentityManager.getCardNo(getActivity());
        Map<String, String> map = ParametersFactory.getDoctorTeamDataMap(getActivity(), cardNo);
        RequestBody body = Util.getBodyFromMap(map);
        mPresenter.main(body);
    }

    @Override
    public void onLoadPeopleSignInfoListSuccess(HttpResultBaseBean<List<InHospitalInspectBeanList>> bean) {
        if (bean != null) {
            List<InHospitalInspectBeanList> list = bean.getData();
            if (!ListUtils.isEmpty(list)) {
                ib = list.get(0);
                String sYear = Util.trimString(ib.getYear());
                String sSignKey = Util.trimString(ib.getKey());
                String sPersonModel = Util.trimString(ib.getPersonMold());
                String sPersonId = Util.trimString(ib.getPersonId());

                Map<String, String> map = ParametersFactory.getPeopleBaseInfo(getActivity(), sYear, sSignKey, sPersonModel, sPersonId);
                RequestBody body = Util.getBodyFromMap1(map);

                mPresenter.reservationSign(body, false);

            } else {
                Util.showToast(getActivity(), Constants.HINT_RESIDENT_SIGN_INFO_LIST_EMPTY);
            }
        } else {
            Util.showToast(getActivity(), Constants.HINT_RESIDENT_SIGN_INFO_LIST_EMPTY);
        }


    }

    @Override
    public void onLoadPeopleSignInfoMoreListSuccess(HttpResultBaseBean<List<InHospitalInspectBeanList>> bean) {

    }

    @Override
    public void onLoadPeopleSignInfoListFailure(String message, boolean isLoadMore) {
        Util.showToast(getActivity(), message);
    }

    @Override
    public void onLoadSignUserInfoSuccess(HttpResultBaseBean<PeopleBaseInfoBean> bean) {
        PeopleBaseInfoBean info = null;
        if (bean != null && (info = bean.getData()) != null) {
            Intent intent = new Intent(getActivity(), PendExecuActivity.class);

            String sYear = Util.trimString(ib.getYear());
            String sSignKey = Util.trimString(ib.getKey());

            intent.putExtra("mpeopleBasebean", (Serializable) info);
            intent.putExtra("sYear", sYear);
            intent.putExtra("sSignKey", sSignKey);
            startActivity(intent);

        } else {
            Util.showToast(getActivity(), Constants.HINT_SIGN_RESIDENT_INFO_EMPTY);
        }
    }

    @Override
    public void onLoadSignUserInfoMoreSuccess(HttpResultBaseBean<PeopleBaseInfoBean> bean) {

    }

    @Override
    public void onLoadSignUserInfoFailure(String message, boolean isLoadMore) {
        Util.showToast(getActivity(), message);
    }

    @Override
    public void onLoadDoctorListSuccess(HttpResultBaseBean<List<DoctorTeamInfo>> bean) {
        if (bean != null) {
            final List<DoctorTeamInfo> list = bean.getData();
//            for (int i = 0; i < 20; ++i){
//                DoctorTeamInfo info = new DoctorTeamInfo("","","??????"+i, "15209999999", "", "0");
//                list.add(info);
//            }
            if (!ListUtils.isEmpty(list)) {
//                showPhoneDialog(list);
                showPhoneDialog2(list);
            } else {
                showDoctorPhoneInfoResult(true);
            }
        } else {
            showDoctorPhoneInfoResult(true);
        }
    }

    @Override
    public void onLoadDoctorListFailure(String message) {
        Util.showToast(getActivity(), message);
    }

    private void showDoctorPhoneInfoResult(boolean isEmptyOrFailure) {
        if (isEmptyOrFailure) {
            Util.showToast(getActivity(), Constants.HINT_NO_SIGN_DOCTOR_PHONE_INFO);
        } else {
            Util.showToast(getActivity(), Constants.HINT_LOAD_SIGN_DOCTOR_PHONE_INFO_FAILURE);
        }
    }

    private List<String> getNameAndPhoneListFromBeanList(List<DoctorTeamInfo> beanList) {
        List<String> result = new ArrayList<>();
        if (!ListUtils.isEmpty(beanList)) {
            for (int i = 0; i < beanList.size(); ++i) {
                DoctorTeamInfo dti = beanList.get(i);
                String nameAndPhone = Util.trimString(dti.getDoctorName()) + "\t\t\t\t" + Util.trimString(dti.getDoctorPhone());
                result.add(nameAndPhone);
            }
        }
        return result;
    }

    private void showPhoneDialog(final List<DoctorTeamInfo> list) {
        List<String> nameAndPhoneList = getNameAndPhoneListFromBeanList(list);
        Util.showStringListMaterialDialog(getActivity(), Constants.HINT_SELECT_DOCTOR_AND_DIAL, Constants.EMPTY_STRING, nameAndPhoneList,
                new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        DoctorTeamInfo dti = list.get(position);
                        String phone = Util.trimString(dti.getDoctorPhone());
                        Util.dial(getActivity(), phone);
                    }
                });

    }

    private void showPhoneDialog2(final List<DoctorTeamInfo> list) {
        BaseQuickAdapter<DoctorTeamInfo, BaseViewHolder> mAdapter = new SignDoctorNameAndPhoneAdapter(list);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        final MaterialDialog dialog = Util.showLinearRecyclerViewMaterialDialog(getActivity(), null, mAdapter);
        mAdapter.setOnItemClickListener(new SignDoctorNameAndPhoneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DoctorTeamInfo dti = list.get(position);
                String phone = Util.trimString(dti.getDoctorPhone());
                Util.dial(getActivity(), phone);

                dialog.dismiss();
            }
        });
    }


    public void registerModuleClickEvent() {
        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_DID_SELECT_MODULE, ImageAndTextBean.class)
                .subscribe(new Action1<ImageAndTextBean>() {
                    @Override
                    public void call(ImageAndTextBean imageAndTextBean) {
                        gotoModule(imageAndTextBean.getId());
                        // Util.showAlert(getActivity(), "??????", "??????????????????" + imageAndTextBean.getId());
                    }
                });
        Subscription mGotoAppointmentSignPageSubscriptioni = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_GOTO_APPOINTMENT_SIGN_PAGE, Boolean.class).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                initReservationSignService();
            }
        });
        if (this.compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(mSubscription);
        compositeSubscription.add(mGotoAppointmentSignPageSubscriptioni);
    }

    public void unregisterModuleClickEvent() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterModuleClickEvent();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    TextView idcard, sure_btn, button_ok, pick_photo, useblurtooth, bluetoothsetting;
    TextView dialog_title1, dialog_title;
    LinearLayout layout_dialog;
    TextView dialogname, dialogsex, dialoguseridcard, readingtext;
    RecyclerView recyclerView;
    int pos;
    public onBlueToothsClickListener onBlueToothClickListener;

    public void setOnBlueToothClickListener(onBlueToothsClickListener listener) {
        onBlueToothClickListener = listener;
    }

    private AlertDialog dialog;
    private BluetoothAdapter btAdapt;
    private static final int SETTING_BT = 22;
    private static final int REQUEST_ENABLE_BT = 2;
    private final static int REQUEST_IMAGE = 100;

    private void showDialog() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.module_dialog, null);
        idcard = (EditText) view.findViewById(R.id.edit_cardid);
        sure_btn = (TextView) view.findViewById(R.id.tvsure);
        button_ok = (TextView) view.findViewById(R.id.tvsure2);
        pick_photo = (TextView) view.findViewById(R.id.pick_photo);
        useblurtooth = (TextView) view.findViewById(R.id.usebluetooth);
        bluetoothsetting = (TextView) view.findViewById(R.id.bluetoothsetting);
        readingtext = (TextView) view.findViewById(R.id.readingtext);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        dialog = builder.create();// ??????dialog
        dialog.show();// ???????????????
        dialog = builder.create();// ??????dialog
        onBlueToothClickListener = (onBlueToothsClickListener) getActivity();
        if (onBlueToothClickListener != null) {
            useblurtooth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBlueToothClickListener.netBlueTooth(3, 4);
                    readingtext.setVisibility(View.VISIBLE);
                }
            });

        }
        pick_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  Toast.makeText(getApplicationContext(),"??????",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // ?????????????????????????????????????????????
                    // ??????WRITE_EXTERNAL_STORAGE??????
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            100);
                } else {
                    selectImage();
                }
            }
        });
        bluetoothsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!btAdapt.isEnabled()) {
                    Intent enableIntent = new Intent(
                            BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
                }
                Intent serverIntent2 = new Intent(getActivity(), DeviceListActivity.class);
                startActivityForResult(serverIntent2, SETTING_BT);
            }
        });
        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(idcard.getText().toString())) {
                    Intent intent = new Intent(getActivity(), AccessFileActivity.class);
                    intent.putExtra("personId", idcard.getText().toString());
                    intent.putExtra("flag", "2");
                    startActivity(intent);

                }

            }
        });


    }

    /**
     * select picture
     */
    private void selectImage() {
        MultiImageSelector.create(getActivity())
                .showCamera(true) // ??????????????????. ???????????????
//                .count(1) // ????????????????????????, ?????????9. ???????????????????????????????????????
                .single() // ????????????
//                .multi() // ????????????, ????????????;
//                .origin(ArrayList<String>) // ?????????????????????. ???????????????????????????????????????
                .start(getActivity(), REQUEST_IMAGE);
    }

    @Override
    public void sendId(String cardId, String idname, int sex) {
        idcard.setText(cardId);
        readingtext.setVisibility(View.GONE);
    }

    @Override
    public void failnote() {
        readingtext.setVisibility(View.GONE);
    }

}
