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
import com.jqsoft.fingertip_health.datasource.DataSourceFactory;
import com.jqsoft.fingertip_health.di.contract.ModuleListFragmentContract;
import com.jqsoft.fingertip_health.di.presenter.ModuleListFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.AccessFileActivity;
import com.jqsoft.fingertip_health.di.ui.activity.DeviceListActivity;
import com.jqsoft.fingertip_health.di.ui.activity.GuideActivity;
import com.jqsoft.fingertip_health.di.ui.activity.HouseholdSurveysActivity;
import com.jqsoft.fingertip_health.di.ui.activity.IgGuideActivity;
import com.jqsoft.fingertip_health.di.ui.activity.ListGuideActivity;
import com.jqsoft.fingertip_health.di.ui.activity.PendExecuActivity;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionActivity;
import com.jqsoft.fingertip_health.di.ui.activity.SignServiceAssessActivity;
import com.jqsoft.fingertip_health.di.ui.activity.UrbanLowInsuranceActivity;
import com.jqsoft.fingertip_health.di.ui.activity.WorkbenchActivity;
import com.jqsoft.fingertip_health.di.ui.activity.webview.MainActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.listener.onBlueToothsClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.test_video_upload.CameraActivity;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

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

public class HandleBussiNewFragment extends AbstractFragment implements WorkbenchActivity.OnSendidcardclickListner,
        ModuleListFragmentContract.View {
    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    @Inject
    ModuleListFragmentPresenter mPresenter;

    private static HandleBussiNewFragment instance = null;

    private CompositeSubscription compositeSubscription;

    InHospitalInspectBeanList ib;

    private DaggerApplication application;

    public static HandleBussiNewFragment getModuleistFragment() {
        if (instance == null) {
            instance = new HandleBussiNewFragment();
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_handle_line_layout;
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
            application = (DaggerApplication)getActivity().getApplicationContext();
            registerModuleClickEvent();
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
       /* DaggerApplication.get(getActivity())
                .getAppComponent()
                .addModuleListFragment(new ModuleListFragmentModule(this))
                .inject(this);*/
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
        List<View> viewList = DataSourceFactory.getHandleModuleViewList(getActivity());
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
            case Constants.MODULE_ID_FIND:
                Util.gotoActivity(getActivity(), SignServiceAssessActivity.class);
                break;
            case Constants.MODULE_ID_CHENGXIANG_DIBAO:
//                Intent intent = new Intent();
//                intent.putExtra("ItemId", "ITEM_DIBAO_CZ");
//                intent.putExtra("titils", "城市低保");
//                intent.setClass(getActivity(), UrbanLowInsuranceActivity.class);
//                startActivity(intent);

                Intent intent = new Intent();
                intent.putExtra("ItemId", "02");
                intent.setClass(getActivity(), MainActivity.class);
                startActivity(intent);
                break;

            case Constants.MODUBLE_ID_TEKUN:

                Intent intent1 = new Intent();
                intent1.putExtra("ItemId", "03");
                intent1.setClass(getActivity(), MainActivity.class);
                startActivity(intent1);
//                test();
//                String systemFileName=IdentityManager.getSystemFileName(getActivity());
//                if(systemFileName==null){
//                    Intent intent1 = new Intent();
//                    intent1.putExtra("ItemId", "ITEM_TEKUN");
//                    intent1.putExtra("titils", "特困人员供养");
//                    intent1.setClass(getActivity(), UrbanLowInsuranceActivity.class);
//                    startActivity(intent1);
//                }else {
//                    if(systemFileName.equals("luyang")){
//                        Intent intent1 = new Intent();
//                        intent1.putExtra("ItemId", "ITEM_WBGY");
//                        intent1.putExtra("titils", "特困人员供养");
//                        intent1.setClass(getActivity(), UrbanLowInsuranceActivity.class);
//                        startActivity(intent1);
//                    }else {
//                        Intent intent1 = new Intent();
//                        intent1.putExtra("ItemId", "ITEM_TEKUN");
//                        intent1.putExtra("titils", "特困人员供养");
//                        intent1.setClass(getActivity(), UrbanLowInsuranceActivity.class);
//                        startActivity(intent1);
//                    }
//                }


                break;
            case Constants.MODUBLE_ID_YILIAO_JIUZHU:

                Intent intent2 = new Intent();
                intent2.putExtra("ItemId", "01");
                intent2.setClass(getActivity(), MainActivity.class);
                startActivity(intent2);
                //Util.gotoActivity(getActivity(), HandleProgress.class);
//                Intent intent2 = new Intent();
//                intent2.putExtra("ItemId", "ITEM_DIBAO_NC");
//                intent2.putExtra("titils", "农村低保");
//                intent2.setClass(getActivity(), UrbanLowInsuranceActivity.class);
//                startActivity(intent2);
                break;
            case Constants.MODUBLE_ID_LINSHI_JIUZHU:
                //Util.gotoActivity(getActivity(), HandleProgress.class);
                Intent intent3 = new Intent();
                intent3.putExtra("ItemId", "ITEM_OTHER");
                intent3.putExtra("titils", "其他救助");
                intent3.setClass(getActivity(), UrbanLowInsuranceActivity.class);
                startActivity(intent3);
                break;
//            case Constants.MODULE_ID_SOUZAI_JIUZHU:
//                Intent intent4 = new Intent();
//                intent4.putExtra("ItemId", "ITEM_JJN");
//                intent4.putExtra("titils", "受灾救助");
//                intent4.setClass(getActivity(), UrbanLowInsuranceActivity.class);
//                startActivity(intent4);
//                break;
            case Constants.MODULE_ID_RUHU_DIAOCHA:
                application.setTableType("1");
                Intent intent5 = new Intent();
                intent5.setClass(getActivity(), HouseholdSurveysActivity.class);
                startActivity(intent5);
                break;

            case Constants.MODULE_ID_MINZHU_PINYI:
                application.setTableType("2");
                Intent intent6 = new Intent();
                intent6.setClass(getActivity(), HouseholdSurveysActivity.class);
                startActivity(intent6);

                //test upload video
//                testUploadVideo();
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

                String sAreaLeavel=Util.choicArea(getActivity());

                if(IdentityManager.getAreaLevel(getActivity()).equals("area_1")  ){
                    Util.gotoActivity(getActivity(), ListGuideActivity.class);
                }else  {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.GUIDE_ITEM_ACTIVITY_KEY,  Identity.srcInfo.getAreaId());
                    Util.gotoActivity(getActivity(), GuideActivity.class);
                }



//                Util.gotoActivity(getActivity(), WorkGuideActivity.class);
//                Identity.shouldReadIdCard = false;
//                RxBus.getDefault().post(Constants.EVENT_TYPE_WOULD_SCROLL_TO_WORKBENCH_INDEX, Constants.WORKBENCH_QUERY);
                break;
            case Constants.MODULE_ID_IG_GUIDE:
                Util.gotoActivity(getActivity(), IgGuideActivity.class);
                break;
            case Constants.MODULE_ID_Reception:
                Util.setReception("JgTypSlzx");
                Util.gotoActivity(getActivity(), ReceptionActivity.class);
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

    private void testUploadVideo(){
        Util.gotoActivity(getActivity(), CameraActivity.class);
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
//                DoctorTeamInfo info = new DoctorTeamInfo("","","医生"+i, "15209999999", "", "0");
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
                        // Util.showAlert(getActivity(), "提示", "您选择了功能" + imageAndTextBean.getId());
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
        dialog = builder.create();// 获取dialog
        dialog.show();// 显示对话框
        dialog = builder.create();// 获取dialog
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

                //  Toast.makeText(getApplicationContext(),"拍照",Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // 应用没有读取手机外部存储的权限
                    // 申请WRITE_EXTERNAL_STORAGE权限
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
                .showCamera(true) // 是否显示相机. 默认为显示
//                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                .single() // 单选模式
//                .multi() // 多选模式, 默认模式;
//                .origin(ArrayList<String>) // 默认已选择图片. 只有在选择模式为多选时有效
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
