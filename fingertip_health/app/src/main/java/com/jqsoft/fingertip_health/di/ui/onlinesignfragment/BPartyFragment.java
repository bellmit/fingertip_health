//package com.jqsoft.grassroots_civil_administration_platform.di.ui.onlinesignfragment;
//
//import android.Manifest;
//import android.app.AlertDialog;
//import android.bluetooth.BluetoothAdapter;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.TextView;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Identity;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.bean.PatientBean;
//import com.jqsoft.fingertip_health.bean.PersonInfoList;
//import com.jqsoft.fingertip_health.bean.PersonnelInfoData;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.bean.response_new.IndexAndOnlineSignInitialData;
//import com.jqsoft.fingertip_health.di.contract.BpartFragmentContract;
//import com.jqsoft.fingertip_health.di.module.BFragmentModule;
//import com.jqsoft.fingertip_health.di.presenter.BPartFragmentPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.DeviceListActivity;
//import com.jqsoft.fingertip_health.di.ui.activity.WorkbenchActivity;
//import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.NHListAdapter;
//import com.jqsoft.fingertip_health.di.youtuIdentify.IdentifyResult;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.listener.onBlueToothsClickListener;
//import com.jqsoft.fingertip_health.listener.submitOnSuccessListener;
//import com.jqsoft.fingertip_health.rx.RxBus;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.view.IDCard;
//import com.mixiaoxiao.smoothcompoundbutton.SmoothCompoundButton;
//import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;
//import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioGroup;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import me.nereo.multi_image_selector.MultiImageSelector;
//import okhttp3.RequestBody;
//import rx.Subscription;
//import rx.functions.Action1;
//import rx.subscriptions.CompositeSubscription;
//
//import static com.jqsoft.fingertip_health.R.id.sr_button_nan;
//
//public class BPartyFragment extends AbstractFragment implements BpartFragmentContract.View, SmoothRadioGroup.OnCheckedChangeListener, SmoothRadioButton.OnCheckedChangeListener, WorkbenchActivity.OnSendidcardclickListner, WorkbenchActivity.onClickIdCardListener, submitOnSuccessListener {
//    private Context context;
//    @BindView(R.id.username)
//    EditText username;
//    @BindView(R.id.ll_read_id_card)
//    LinearLayout ll_read_id_card;
//    @BindView(R.id.smothradiogroup)
//    SmoothRadioGroup smoothRadioGroup;
//    @BindView(sr_button_nan)
//    SmoothRadioButton smoothRadioButtonnan;
//    @BindView(R.id.sr_button_nv)
//    SmoothRadioButton smoothRadioButtonnv;
//    @BindView(R.id.smothradiogroup2)
//    SmoothRadioGroup smoothRadioGroup2;
//    @BindView(R.id.smothradiogroup3)
//    SmoothRadioGroup smoothRadioGroup3;
//    @BindView(R.id.sr_button_xnh)
//    SmoothRadioButton smoothRadioButton_xnh;
//    @BindView(R.id.sr_button_zgyb)
//    SmoothRadioButton smoothRadioButton_zgyb;
//    @BindView(R.id.sr_button_jmyb)
//    SmoothRadioButton smoothRadioButton_jmyb;
//    @BindView(R.id.sr_button_qt)
//    SmoothRadioButton smoothRadioButton_qt;
//    //    @BindView(R.id.radiosex)
////    RadioGroup radioGroup_sex;
////    @BindView(R.id.sex_nan)
////    RadioButton radioButton_nan;
////    @BindView(R.id.sex_nv)
//    RadioButton radioButton_nv;
//    @BindView(R.id.idcard)
//    EditText useridCard;
//    @BindView(R.id.jianhurenidcard)
//    EditText helpidcard;
//    @BindView(R.id.jianhurentel)
//    EditText usertel;
//    @BindView(R.id.danganbianma)
//    EditText tv_no;
//    @BindView(R.id.leibie_layout)
//    LinearLayout leibie_layout;
//    @BindView(R.id.iv_tang)
//    ImageView iv_tang;
//    @BindView(R.id.ll_read_nonghe)
//    LinearLayout ll_read_nonghe;
//    @BindView(R.id.ll_read_archive)//调阅档案
//            LinearLayout ll_read_archive;
//    @BindView(R.id.iv_gao)
//    ImageView iv_gao;
//
//    @BindView(R.id.iv_lao)
//    ImageView iv_lao;
//
//    @BindView(R.id.iv_jing)
//    ImageView iv_jing;
//
//    @BindView(R.id.iv_mian)
//    ImageView iv_mian;
//
//    @BindView(R.id.iv_pin)
//    ImageView iv_pin;
//
//    @BindView(R.id.iv_tong)
//    ImageView iv_tong;
//
//    @BindView(R.id.iv_tuo)
//    ImageView iv_tuo;
//
//    @BindView(R.id.iv_yun)
//    ImageView iv_yun;
//
//    private String sex_code;
//    private String yibao_code;
//    private int btn_type, readidcard_type = 0;
//    private AlertDialog dialog;
//    private Map<Integer, Boolean> isSelected;
//    private final static int REQUEST_IMAGE = 100;
//    @Inject
//    BPartFragmentPresenter mPresenter;
//    private ArrayList<PersonInfoList> xhlistdata = new ArrayList<>();
//    private NHListAdapter nhListAdapter;
//    private static BPartyFragment instance = null;
//    public List<Boolean> isClicks = new ArrayList<>();
//    private String userIDCard;
//    private CompositeSubscription mInitializeSubscription;
//    private BluetoothAdapter btAdapt;
//    private static final int SETTING_BT = 22;
//    private static final int REQUEST_ENABLE_BT = 2;
//
//    private void registerInitializeSubscription() {
//        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_ONLINE_SIGN_PER_FRAGMENT_INITIALIZE, IndexAndOnlineSignInitialData.class).subscribe(new Action1<IndexAndOnlineSignInitialData>() {
//            @Override
//            public void call(IndexAndOnlineSignInitialData indexAndOnlineSignInitialData) {
//                username.setText(indexAndOnlineSignInitialData.getPersonName());
//                useridCard.setText(indexAndOnlineSignInitialData.getCardNo());
//                helpidcard.setText(indexAndOnlineSignInitialData.getGuardianCardNo());
//                usertel.setText(indexAndOnlineSignInitialData.getPhone());
//                tv_no.setText(indexAndOnlineSignInitialData.getNo());
//                if (indexAndOnlineSignInitialData.getSexCode().equals("1")) {
//                    smoothRadioButtonnan.setChecked(true);
//                    sex_code = "1";
//                } else {
//                    smoothRadioButtonnv.setChecked(true);
//                    sex_code = "2";
//                }
//
//
//            }
//        });
//        if (mInitializeSubscription == null) {
//            mInitializeSubscription = new CompositeSubscription();
//        }
//        mInitializeSubscription.add(subscription);
//    }
//
//    private void unregisterInitializeSubscription() {
//        if (mInitializeSubscription != null && mInitializeSubscription.hasSubscriptions()) {
//            mInitializeSubscription.unsubscribe();
//        }
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        unregisterInitializeSubscription();
//    }
//
//
//    public static BPartyFragment getBPartyFragment() {
//        if (instance == null) {
//            instance = new BPartyFragment();
//        }
//        return instance;
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_bparty_fragment;
//
//    }
//
//    @Override
//    protected void initData() {
//        context = getActivity();
//        btAdapt = BluetoothAdapter.getDefaultAdapter();
//    }
//
//    @Override
//    protected void initView() {
//        instance = this;
//        if (mInitializeSubscription == null) {
//            registerInitializeSubscription();
//        }
////        if (Identity.info.getIsEnableInput().equals("0")) {
//        if (Identity.getIsEnableInput().equals("0")) {
//            useridCard.setEnabled(false);
//            username.setEnabled(false);
//        }
//        smoothRadioGroup.setOnCheckedChangeListener(this);
//        smoothRadioGroup2.setOnCheckedChangeListener(this);
//        smoothRadioGroup3.setOnCheckedChangeListener(this);
//        smoothRadioButton_xnh.setOnCheckedChangeListener(this);
//        smoothRadioButton_zgyb.setOnCheckedChangeListener(this);
//        smoothRadioButton_jmyb.setOnCheckedChangeListener(this);
//        smoothRadioButton_qt.setOnCheckedChangeListener(this);
//        smoothRadioButtonnan.setOnCheckedChangeListener(this);
//        smoothRadioButtonnv.setOnCheckedChangeListener(this);
//        ll_read_id_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (Identity.info.getIsEnableReadCard().equals("1")) {
//                    btn_type = 3;
//                    readidcard_type = 3;
//                    showDialog();
//                } else {
//                    Util.showToast(getActivity(), "无权限使用读身份证功能");
//
//                }
//            }
//        });
//
//        ll_read_nonghe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {//isEnableAgricultura
//                if (Identity.info.getIsEnableAgricultura().equals("1")) {
//                    if (!TextUtils.isEmpty(useridCard.getText().toString())) {
//                        String mm = IDCard.IDCardValidate(useridCard.getText().toString());
//                        if (mm.equals("")) {
//                            Map<String, String> map = ParametersFactory.getPersonInfo(getActivity(), useridCard.getText().toString(), Identity.info.getSloginname());
//                            RequestBody body = Util.getBodyFromMap(map);
//                            mPresenter.getPersonInfo(body);
//                        } else {
//                            Util.showToast(getActivity(), mm);
//                        }
//                    } else {
//                        btn_type = 1;
//                        showDialog();
//                    }
//                } else {
//                    Util.showToast(getActivity(), "无权限调阅农合");
//                }
//            }
//        });
//        ll_read_archive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (Identity.info.getIsEnablePerson().equals("1")) {
//                    if (!TextUtils.isEmpty(useridCard.getText().toString())) {
//                        String mm = IDCard.IDCardValidate(useridCard.getText().toString());
//                        if (mm.equals("")) {
//                            Map<String, String> map = ParametersFactory.getPersonnelInfo2(getActivity(), useridCard.getText().toString());
//                            RequestBody body = Util.getBodyFromMap(map);
//                            mPresenter.getPersonnlInfo(body);
//                        }else{
//                            Util.showToast(getActivity(), mm);
//                        }
//                    } else {
//                        btn_type = 2;
//                        showDialog();
//                    }
//                } else {
//                    Util.showToast(getActivity(), "无权限调阅档案");
//                }
//
//            }
//        });
//    }
//
//    /**
//     * select picture
//     */
//    private void selectImage() {
//        MultiImageSelector.create(getActivity())
//                .showCamera(true) // 是否显示相机. 默认为显示
////                .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
//                .single() // 单选模式
////                .multi() // 多选模式, 默认模式;
////                .origin(ArrayList<String>) // 默认已选择图片. 只有在选择模式为多选时有效
//                .start(getActivity(), REQUEST_IMAGE);
//    }
//
//
//    public void setUserData() {
//        PatientBean.setUsername(username.getText().toString());
//        PatientBean.setSexCode(sex_code);
//        PatientBean.setCardNo(useridCard.getText().toString());
//        PatientBean.setGuardianCardNo(helpidcard.getText().toString());
//        PatientBean.setPhone(usertel.getText().toString());
//        if (!TextUtils.isEmpty(helpidcard.getText().toString())) {
//            PatientBean.setIsUseGuardian("1");
//            PatientBean.setCardNo(""); //如果填写了监护人的话 提交的时候自动把省份证清空
//        } else {
//            PatientBean.setIsUseGuardian("0");
//        }
//        PatientBean.setInterfaceStatus("2");
//        PatientBean.setAgriculturalCardNo(yibao_code);
//        PatientBean.setNo(tv_no.getText().toString());
//        PatientBean.setYibao_code(yibao_code);
//    }
//
//    @Override
//    protected void loadData() {
//
//    }
//
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(getActivity())
//                .getAppComponent()
//                .addBapartFragment(new BFragmentModule(this))
//                .inject(this);
//    }
//
//    @Override
//    public void onCheckedChanged(SmoothRadioGroup smoothRadioGroup, int i) {
//    }
//
//
//    /* @OnClick(R.id.ll_read_nonghe)
//     public void getPersonInfo() {
//         if (TextUtils.isEmpty(useridCard.getText().toString())) {
//
//         } else {
//             btn_type = 1;
//             showDialog();
//         }
//
//
//     }
//
//
//     @OnClick(R.id.ll_read_archive)//调阅档案
//     public void getPersonnelInfo() {
//         if (TextUtils.isEmpty(useridCard.getText().toString())) {
//
//         } else {
//             btn_type = 2;
//             showDialog();
//         }
//     }
// */
//    TextView idcard, sure_btn, button_ok, pick_photo, useblurtooth, bluetoothsetting;
//    TextView dialog_title1, dialog_title;
//    LinearLayout layout_dialog;
//    TextView dialogname, dialogsex, dialoguseridcard, readingtext;
//    RecyclerView recyclerView;
//    int pos;
//    public onBlueToothsClickListener onBlueToothClickListener;
//
//    public void setOnBlueToothClickListener(onBlueToothsClickListener listener) {
//        onBlueToothClickListener = listener;
//    }
//
//
//    private void showDialog() {
//        LayoutInflater inflater = LayoutInflater.from(getActivity());
//        View view = inflater.inflate(R.layout.fragment_dialog, null);
//        idcard = (TextView) view.findViewById(R.id.edit_cardid);
//        readingtext = (TextView) view.findViewById(R.id.readingtext);
//        dialog_title1 = (TextView) view.findViewById(R.id.dialog_title1);
//        dialog_title = (TextView) view.findViewById(R.id.dialog_title);
//        layout_dialog = (LinearLayout) view.findViewById(R.id.dialog_layout);
//        dialogsex = (TextView) view.findViewById(R.id.dialog_usersex);
//        dialogname = (TextView) view.findViewById(R.id.dialog_username);
//        dialoguseridcard = (TextView) view.findViewById(R.id.dialog_userid);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//        sure_btn = (TextView) view.findViewById(R.id.tvsure);
//        button_ok = (TextView) view.findViewById(R.id.tvsure2);
//        pick_photo = (TextView) view.findViewById(R.id.pick_photo);
//        useblurtooth = (TextView) view.findViewById(R.id.usebluetooth);
//        bluetoothsetting = (TextView) view.findViewById(R.id.bluetoothsetting);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setView(view);
//        dialog = builder.create();// 获取dialog
//        if (btn_type == 3) {
//            dialog_title1.setVisibility(View.GONE);
//            dialog_title.setVisibility(View.VISIBLE);
//        }
//        dialog.show();// 显示对话框
//        onBlueToothClickListener = (onBlueToothsClickListener) getActivity();
//        if (onBlueToothClickListener != null) {
//            useblurtooth.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    readidcard_type = 2;
//                    onBlueToothClickListener.netBlueTooth(3, 2);
//                    readingtext.setVisibility(View.VISIBLE);
//                }
//            });
//
//        }
//        pick_photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                readidcard_type = 1;
//                //  Toast.makeText(getApplicationContext(),"拍照",Toast.LENGTH_SHORT).show();
//                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                    // 应用没有读取手机外部存储的权限
//                    // 申请WRITE_EXTERNAL_STORAGE权限
//                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                            100);
//                } else {
//                    selectImage();
//                }
//            }
//        });
//        bluetoothsetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!btAdapt.isEnabled()) {
//                    Intent enableIntent = new Intent(
//                            BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                    startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
//                }
//                Intent serverIntent2 = new Intent(getActivity(), DeviceListActivity.class);
//                startActivityForResult(serverIntent2, SETTING_BT);
//            }
//        });
//        sure_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (btn_type == 3) {
//                    dialog.dismiss();
//                    if (readidcard_type == 2) {
//                        username.setText(paicardname);
//                        useridCard.setText(paicardid);
//                        if (paicardsex == 1) {
//                            smoothRadioButtonnan.setChecked(true);
//                            sex_code = "1";
//                        } else {
//                            smoothRadioButtonnv.setChecked(true);
//                            sex_code = "2";
//                        }
//
//                    } else {
//                        username.setText(identifyResult.getName());
//                        useridCard.setText(identifyResult.getId());
//                        if (identifyResult.getSex().equals("男")) {
//                            smoothRadioButtonnan.setChecked(true);
//                            sex_code = "1";
//                        } else if (identifyResult.getSex().equals("女")) {
//                            smoothRadioButtonnv.setChecked(true);
//                            sex_code = "2";
//                        }
//                    }
//                } else {
//                    if (!TextUtils.isEmpty(idcard.getText().toString())) {
//                        String ss = IDCard.IDCardValidate(idcard.getText().toString());
//                        if (ss.equals("")) {
//                            if (btn_type == 1) {//农合
//                                //  idcard.setText("341723200803097247");
//                                Map<String, String> map = ParametersFactory.getPersonInfo(getActivity(), idcard.getText().toString(), Identity.info.getSloginname());
//                                RequestBody body = Util.getBodyFromMap(map);
//                                mPresenter.getPersonInfo(body);
//                            } else {// 档案
//                                //  idcard.setText(" 34212319621130446x");
//                                Map<String, String> map = ParametersFactory.getPersonnelInfo2(getActivity(), idcard.getText().toString());
//                                RequestBody body = Util.getBodyFromMap(map);
//                                mPresenter.getPersonnlInfo(body);
//                            }
//                        } else {
//                            Util.showToast(getActivity(), ss);
//                        }
//                    } else {
//                        Util.showToast(getActivity(), "证件号不能为空");
//                    }
//                }
//            }
//        });
//
//
//    }
//
//    @Override
//    public void onLoadListSuccess(HttpResultBaseBean<List<PersonInfoList>> bean) {
//        idcard.setVisibility(View.GONE);
//        sure_btn.setVisibility(View.GONE);
//        recyclerView.setVisibility(View.VISIBLE);
//        button_ok.setVisibility(View.VISIBLE);
//        bean.getData();
//        Util.hideGifProgressDialog(getActivity());
//        //  Util.showToast(getActivity(), "调阅农合成功");
//        if (bean.getData().size() == 0) {
//            Util.showToast(getActivity(), "无任何数据");
//            idcard.setVisibility(View.VISIBLE);
//        }
//        xhlistdata.clear();
//        xhlistdata.addAll(bean.getData());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        //设置适配器
//        nhListAdapter = new NHListAdapter(context, xhlistdata, recyclerView);
//        recyclerView.setAdapter(nhListAdapter);
//        nhListAdapter.notifyDataSetChanged();
//        isSelected = new HashMap<Integer, Boolean>();
//        for (int i = 0; i < xhlistdata.size(); i++) {
//            isSelected.put(i, false);
//        }
//        nhListAdapter.setOnItemClickLitener(new NHListAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
////                Util.showToast(getActivity(), "成功" + position);
//                pos = position;
//
//            }
//        });
//        button_ok.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                if (xhlistdata.size() > 0) {
//
//                    username.setText(xhlistdata.get(pos).getName());
//                    if (xhlistdata.get(pos).getSexId().equals("1")) {
//                        smoothRadioButtonnan.setChecked(true);
//                        sex_code = "1";
//                    } else if (xhlistdata.get(pos).getSexId().equals("2")) {
//                        smoothRadioButtonnv.setChecked(true);
//                        sex_code = "2";
//                    }
//                    useridCard.setText(xhlistdata.get(pos).getIdcardNo());
//                    usertel.setText(xhlistdata.get(pos).getTel());
//                    smoothRadioButton_xnh.setChecked(true);
//                    PatientBean.setMemberNO(xhlistdata.get(pos).getMemberNO());
//                    PatientBean.setFamilySysno(xhlistdata.get(pos).getFamilySysno());
//                    PatientBean.setYibao_code("0");
//                    PatientBean.setInterfaceStatus("1");
//                }
//            }
//        });
//        // 341723200803097247
////        memberNO		        成员编码
////        name		            姓名
////        countryTeamCode	        辖区编码
////        familySysno	 	        家庭编码
////        sexId	 	            性别代码 （ 1：男性，2：女性）
////        idcardNo		            身份证
////        age		                年龄
////        birthday		            出生日期
////        bookNo		            医疗证号
////        cardNo		            医疗卡号
////        familyAddress	            家庭住址
////        tel		                电话号码
////        ideName		            个人身份属性名称
//
//    }
//
//    @Override
//    public void onLoginFailure(String message) {
//        //Util.showToast(getActivity(), message);
//    }
//
//    @Override
//    public void onPersonnelInfo(HttpResultBaseBean<PersonnelInfoData> bean) {
//        dialog.dismiss();
////        isHouseholder                  是否是户主
////        personID                      个人健康档案唯一标识符
////        interfaceStatus                 1 代表关联农合成功
////        2 代表未关联农合成功
////        state                          人员建档状态
//// （1 正常 2 移交  3终止 4封挡）
////        isHypertensionState              是否高血压患者
////        isType2DiabetesState             是否糖尿病患者
////        isPsychosisState                  是否精神病患者
////        isOldPeopleInfoState             是否老年人
////        isPoorState                      是否贫困人口
////        areaCode                        地区编码
////        costCode                         医保类型
////        costName                         医保名称
//        Util.hideGifProgressDialog(getActivity());
//        Util.showToast(getActivity(), bean.getMessage());
//        username.setText(bean.getData().getUserName());
//        if (bean.getData().getSexName().equals("男")) {
//            smoothRadioButtonnan.setChecked(true);
//            sex_code = "1";
//        } else if (bean.getData().getSexName().equals("女")) {
//            smoothRadioButtonnv.setChecked(true);
//            sex_code = "2";
//        }
//        useridCard.setText(bean.getData().getCardNo());
//        usertel.setText(bean.getData().getPhone());
//        helpidcard.setText(bean.getData().getGuardianCardNo());
//        tv_no.setText(bean.getData().getNo());
//        if (bean.getData().getIsHypertensionState().equals("1") || bean.getData().getIsType2DiabetesState().equals("1") || bean.getData().getIsPsychosisState().equals("1") || bean.getData().getIsOldPeopleInfoState().equals("1") || bean.getData().getIsPoorState().equals("1")) {
//
//            if (bean.getData().getIsHypertensionState().equals("1")) {
//                iv_gao.setImageResource(R.mipmap.ic_gao);
//                iv_gao.setVisibility(View.VISIBLE);
//            } else {
//                iv_gao.setVisibility(View.GONE);
//
//            }
//
//            if (bean.getData().getIsType2DiabetesState().equals("1")) {
//                iv_tang.setImageResource(R.mipmap.ic_tang);
//                iv_tang.setVisibility(View.VISIBLE);
//            } else {
//                iv_tang.setVisibility(View.GONE);
//            }
//
//            if (bean.getData().getIsPsychosisState().equals("1")) {
//                iv_jing.setImageResource(R.mipmap.ic_jing);
//                iv_jing.setVisibility(View.VISIBLE);
//            } else {
//                iv_jing.setVisibility(View.GONE);
//            }
//
//            if (bean.getData().getIsOldPeopleInfoState().equals("1")) {
//                iv_lao.setImageResource(R.mipmap.ic_lao);
//                iv_lao.setVisibility(View.VISIBLE);
//            } else {
//                iv_lao.setVisibility(View.GONE);
//            }
//
//            if (bean.getData().getIsPoorState().equals("1")) {
//                iv_pin.setImageResource(R.mipmap.ic_pin);
//                iv_pin.setVisibility(View.VISIBLE);
//            } else {
//                iv_pin.setVisibility(View.GONE);
//            }
//        }
//
////        if (bean.getData().getCostCode().equals("0")) {
////            smoothRadioButton_xnh.setChecked(true);
////            yibao_code = "0";
////        } else if (bean.getData().getCostCode().equals("1")) {
////            smoothRadioButton_zgyb.setChecked(true);
////            yibao_code = "1";
////        } else if (bean.getData().getCostCode().equals("3")) {
////            smoothRadioButton_jmyb.setChecked(true);
////            yibao_code = "3";
////        } else if (bean.getData().getCostCode().equals("4")) {
////            smoothRadioButton_qt.setChecked(true);
////            yibao_code = "4";
////        }
//        PatientBean.setIsHouseholder(bean.getData().getIsHouseholder());
//        PatientBean.setPersonID(bean.getData().getPersonID());
//        PatientBean.setInterfaceStatus("1");
//
//    }
//
//
//    @Override
//    public void onCheckedChanged(SmoothCompoundButton smoothCompoundButton, boolean b) {
//        switch (smoothCompoundButton.getId()) {
//            case R.id.sr_button_nan:
//                sex_code = "1";
//                //  Toast.makeText(getActivity(), "男", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.sr_button_nv:
//                sex_code = "2";
//                //  Toast.makeText(getActivity(), "2女", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.sr_button_xnh:
//                smoothRadioGroup3.clearCheck();
//                yibao_code = "0";
//                //  Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.sr_button_zgyb:
//                smoothRadioGroup3.clearCheck();
//                yibao_code = "1";
//                //  Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.sr_button_jmyb:
//                smoothRadioGroup3.clearCheck();
//                // smoothRadioButton_jmyb.setChecked(true);
//                yibao_code = "2";
//                // Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.sr_button_qt:
//                smoothRadioGroup2.clearCheck();
//                //  smoothRadioButton_qt.setChecked(true);
//                yibao_code = "9";
//                // Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//    }
//
//    String paicardid, paicardname;
//    int paicardsex;
//
//    public void sendId(String cardId, String idname, int sex) {
//        readingtext.setVisibility(View.GONE);
//        paicardid = cardId;
//        paicardname = idname;
//        paicardsex = sex;
//
//        if (btn_type == 3) {
//            idcard.setVisibility(View.GONE);
//            layout_dialog.setVisibility(View.VISIBLE);
//            dialogname.setText("姓名:" + idname);
//            dialoguseridcard.setText("身份证号:" + cardId);
//            Log.i("sex value :", sex + "");
//            if (sex == 1) {
//                smoothRadioButtonnan.setChecked(true);
//                sex_code = "1";
//                dialogsex.setText("性别:男");
//            } else {
//                smoothRadioButtonnv.setChecked(true);
//                sex_code = "2";
//                dialogsex.setText("性别:女");
//            }
//
//        } else {
//            userIDCard = cardId;
////            useridCard.setText(cardId);
////            username.setText(idname);
//            idcard.setText(userIDCard);
//        }
//
//    }
//
//    @Override
//    public void failnote() {
//        readingtext.setVisibility(View.GONE);
//    }
//
//    IdentifyResult identifyResult;
//
//    @Override
//    public void sendIdCardInfo(IdentifyResult result) {
//        identifyResult = result;
//        username.setText(result.getName());
//        useridCard.setText(result.getId());
//        idcard.setText(result.getId());
//        if (result.getSex().equals("男")) {
//            smoothRadioButtonnan.setChecked(true);
//            sex_code = "1";
//        } else if (result.getSex().equals("女")) {
//            smoothRadioButtonnv.setChecked(true);
//            sex_code = "2";
//        }
//        if (btn_type == 3) {
//            idcard.setVisibility(View.GONE);
//            layout_dialog.setVisibility(View.VISIBLE);
//            if (result.getSex().equals("男")) {
//                dialogsex.setText("性别:男");
//                sex_code = "1";
//            } else if (result.getSex().equals("女")) {
//                dialogsex.setText("性别:女");
//                sex_code = "2";
//            }
//            dialogname.setText("姓名:" + result.getName());
//            dialoguseridcard.setText("身份证号:" + result.getId());
//
//        }
//
//    }
//
//    @Override
//    public void sendSuccessinfo() {
//        username.setText("");
//        smoothRadioButtonnan.setChecked(false);
//        smoothRadioButtonnv.setChecked(false);
//        useridCard.setText("");
//        helpidcard.setText("");
//        usertel.setText("");
//        tv_no.setText("");
//        smoothRadioButton_xnh.setChecked(false);
//        smoothRadioButton_zgyb.setChecked(false);
//        smoothRadioButton_jmyb.setChecked(false);
//        smoothRadioButton_qt.setChecked(false);
//        PatientBean.setIsHouseholder("");
//        PatientBean.setPersonID("");
//        PatientBean.setInterfaceStatus("");
//        PatientBean.setMemberNO("");
//        PatientBean.setFamilySysno("");
//        PatientBean.setYibao_code("");
//        PatientBean.setInterfaceStatus("");
//    }
//}
