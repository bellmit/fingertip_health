//package com.jqsoft.grassroots_civil_administration_platform.di.ui.fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.SignDoctorNameAndPhoneAdapter;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.IdentityManager;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.base.Version;
//import com.jqsoft.fingertip_health.bean.DoctorTeamInfo;
//import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.di.contract.PeopleBaseFragmentContract;
//import com.jqsoft.fingertip_health.di.module.PeopleBaseInfoFragmentModule;
//import com.jqsoft.fingertip_health.di.presenter.PeopleBaseFragmentPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.DetailPeopleInfoActivity;
//import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.GlideUtils;
//import com.jqsoft.fingertip_health.utils3.util.ListUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import okhttp3.RequestBody;
//
///**
// * Created by YLL on 2017-6-30.
// */
//
//public class PeopleBaseFragment extends AbstractFragment implements
//        PeopleBaseFragmentContract.View{
//
//    @BindView(R.id.tv_base_name)
//    TextView tv_base_name;
//
//    @Inject
//    PeopleBaseFragmentPresenter mPresenter;
//
//    @BindView(R.id.iv_base_sex)
//    ImageView iv_base_sex;
//
//    @BindView(R.id.tv_base_age)
//    TextView tv_base_age;
//
//    @BindView(R.id.iv_phone)
//    ImageView iv_phone;
//
//    @BindView(R.id.tv_base_idcard)
//    TextView tv_base_idcard;
//
//    @BindView(R.id.tv_base_files)
//    TextView tv_base_files;
//
//    @BindView(R.id.tv_base_doctor)
//    TextView tv_base_doctor;
//
//    @BindView(R.id.tv_base_signdate)
//    TextView tv_base_signdate;
//
//    @BindView(R.id.tv_base_medical_type)
//    TextView tv_base_medical_type;
//
//    @BindView(R.id.tv_detailname)
//    TextView tv_detailname;
//
//    @BindView(R.id.tv_base_recentlydate)
//    TextView tv_base_recentlydate;
//
//    @BindView(R.id.tv_base_next)
//    TextView tv_base_next;
//
//    @BindView(R.id.tv_base_xzcount)
//    TextView tv_base_xzcount;
//
//    @BindView(R.id.tv_base_cscount)
//    TextView tv_base_cscount;
//
//    @BindView(R.id.tv_base_jbggcount)
//    TextView tv_base_jbggcount;
//
//    @BindView(R.id.pg_base_xznew)
//    ProgressBar pg_base_xz;
//
//    @BindView(R.id.pg_base_csnew)
//    ProgressBar pg_base_cs;
//
//    @BindView(R.id.pg_base_jbggnew)
//    ProgressBar pg_base_jbgg;
//
//    @BindView(R.id.iv_tang)
//    ImageView iv_tang;
//
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
//    @BindView(R.id.img_head)
//    ImageView img_head;
//
//    @BindView(R.id.ll_label)
//    LinearLayout ll_label;
//
//    private Bundle data = new Bundle();
//    private String sYear,sSignKey,sPersonModel,sPersonId;
//    private PeopleBaseInfoBean mpeopleBasebean;
//    private DetailPeopleInfoActivity activity;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_people_base;
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initView() {
//        /*data = getArguments();//获得从activity中传递过来的值
//        sYear = data.getString("year");
//        sSignKey = data.getString("signKey");
//        sPersonModel = data.getString("personModel");
//        sPersonId = data.getString("personId");*/
//
//        img_head.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            /*    Intent intent = new Intent(getActivity(),UpdatePeopleInfoActivity.class);
//                intent.putExtra("cardNo", mpeopleBasebean.getCardNo());
//                intent.putExtra("year", mpeopleBasebean.getYear());
//                intent.putExtra("personId", mpeopleBasebean.getPersonId());
//                startActivity(intent);*/
//            }
//        });
//
//    }
//
//    public void setPeopleBasebean(PeopleBaseInfoBean pp){
//        mpeopleBasebean=pp;
//        if(mpeopleBasebean!=null){
//            tv_base_name.setText(mpeopleBasebean.getUserName());
//
//            if(mpeopleBasebean.getSexCode().equals("1")){
//                iv_base_sex.setImageResource(R.mipmap.i_male);
//
//            }else{
//                iv_base_sex.setImageResource(R.mipmap.i_female);
//            }
//
//            //     helper.setText(R.id.tv_phone_number, Util.trimString(item.getPhoneNumber()));
//            iv_phone.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    doCallDoctor();
//                  /*  if (!StringUtils.isBlank(mpeopleBasebean.getPhone())){
//                        AppUtils.actionDial(getActivity(),
//                                mpeopleBasebean.getPhone());
//                    } else {
//                        Util.showToast(getActivity(),
//                                Constants.HINT_PHONE_NUMBER_EMPTY);
//                    }*/
//                   // Toast.makeText(getActivity(),mpeopleBasebean.getPhone(),Toast.LENGTH_SHORT).show();
//                }
//            });
//
//
//            tv_base_age.setText(mpeopleBasebean.getAge()+"岁");
//            tv_base_idcard.setText(mpeopleBasebean.getCardNo());
//            tv_base_files.setText(mpeopleBasebean.getNo());
//            tv_base_doctor.setText(mpeopleBasebean.getDoctorName()+" "+mpeopleBasebean.getDoctorPhone());
//            tv_base_signdate.setText(mpeopleBasebean.getSignDT());
//            tv_base_medical_type.setText(mpeopleBasebean.getAgriculturalCardNoName());
//            tv_detailname.setText(mpeopleBasebean.getStartExecData().substring(0,10)+"至"+mpeopleBasebean.getEndExecData().substring(0,10));
//            tv_base_recentlydate.setText(mpeopleBasebean.getServerDT());
//            tv_base_next.setText(mpeopleBasebean.getNextServerDT());
//
//            if(TextUtils.isEmpty(mpeopleBasebean.getShouldExecCountxz())){
//                tv_base_xzcount.setText("0/0");
//                pg_base_xz.setMax(0);
//                pg_base_xz.setProgress(0);
//            }else{
//                tv_base_xzcount.setText(mpeopleBasebean.getHadExecCountxz()+"/"+mpeopleBasebean.getShouldExecCountxz());
//                pg_base_xz.setMax(Integer.parseInt(mpeopleBasebean.getShouldExecCountxz()));
//                pg_base_xz.setProgress(Integer.parseInt(mpeopleBasebean.getHadExecCountxz()));
//            }
//
//            String sShouldcsCount,sHadcsCount;
//            if(TextUtils.isEmpty(mpeopleBasebean.getShouldExecCountcs())){
//                tv_base_cscount.setText("0/0");
//                pg_base_cs.setMax(0);
//                pg_base_cs.setProgress(0);
//            }else{
//                sShouldcsCount=mpeopleBasebean.getShouldExecCountcs();
//                sHadcsCount=mpeopleBasebean.getHadExecCountcs();
//                tv_base_cscount.setText(sHadcsCount+"/"+sShouldcsCount);
//                pg_base_cs.setMax(Integer.parseInt(sShouldcsCount));
//                pg_base_cs.setProgress(Integer.parseInt(sHadcsCount));
//            }
//
//
//            if(TextUtils.isEmpty(mpeopleBasebean.getSumjbggCount())){
//                tv_base_jbggcount.setText("0/0");
//                pg_base_jbgg.setMax(0);
//                pg_base_jbgg.setProgress(0);
//            }else{
//                tv_base_jbggcount.setText(mpeopleBasebean.getYwcCount()+"/"+mpeopleBasebean.getSumjbggCount());
//                pg_base_jbgg.setMax(Integer.parseInt(mpeopleBasebean.getSumjbggCount()));
//                pg_base_jbgg.setProgress(Integer.parseInt(mpeopleBasebean.getYwcCount()));
//            }
//
//            String headUrl = Util.trimString(mpeopleBasebean.getPhotoUrl());
//            String  imageUrl= Version.FILE_URL_BASE+headUrl;
//
//            GlideUtils.loadImage(imageUrl,img_head);
//
//
//
//            String spersonMold =mpeopleBasebean.getPersonMold();
//
//            ArrayList<String> spersonMoldList = new ArrayList<>();
//
//            spersonMoldList.clear();
//                if(spersonMold.substring(0,1).equals("1")){
//                    spersonMoldList.add("高");
//                }
//                if(spersonMold.substring(1,2).equals("1")){
//                    spersonMoldList.add("糖");
//                }
//                if(spersonMold.substring(2,3).equals("1")){
//                    spersonMoldList.add("精");
//                }
//                if(spersonMold.substring(3,4).equals("1")){
//                    spersonMoldList.add("老");
//                }
//                if(spersonMold.substring(4,5).equals("1")){
//                    spersonMoldList.add("孕");
//                }
//                if(spersonMold.substring(5,6).equals("1")){
//                    spersonMoldList.add("童");
//                }
//                if(spersonMold.substring(6,7).equals("1")){
//                    spersonMoldList.add("贫");
//                }
//                if(spersonMold.substring(6,7).equals("2")){
//                    spersonMoldList.add("脱");
//                }
//                if(spersonMold.substring(6,7).equals("3")){
//                    spersonMoldList.add("免");
//                }
//
//                String s="";
//                for(int i=0;i<spersonMoldList.size();i++){
//                    s=s+spersonMoldList.get(i);
//                }
//                if(spersonMoldList.size()==0){
//                    ll_label.setVisibility(View.GONE);
//                }else{
//                    ll_label.setVisibility(View.VISIBLE);
//                    if(s.indexOf("糖")!=-1){
//                        iv_tang.setImageResource(R.mipmap.ic_tang);
//                        iv_tang.setVisibility(View.VISIBLE);
//                    }
//
//                    if(s.indexOf("高")!=-1) {
//                        iv_gao.setImageResource(R.mipmap.ic_gao);
//                        iv_gao.setVisibility(View.VISIBLE);
//                    }
//                    if(s.indexOf("老")!=-1) {
//                        iv_lao.setImageResource(R.mipmap.ic_lao);
//                        iv_lao.setVisibility(View.VISIBLE);
//                    }
//                    if(s.indexOf("精")!=-1) {
//                        iv_jing.setImageResource(R.mipmap.ic_jing);
//                        iv_jing.setVisibility(View.VISIBLE);
//                    }
//                    if(s.indexOf("免")!=-1) {
//                        iv_mian.setImageResource(R.mipmap.ic_mian);
//                        iv_mian.setVisibility(View.VISIBLE);
//                    }
//                    if(s.indexOf("贫")!=-1) {
//                        iv_pin.setImageResource(R.mipmap.ic_pin);
//                        iv_pin.setVisibility(View.VISIBLE);
//                    }
//                    if(s.indexOf("童")!=-1) {
//                        iv_tong.setImageResource(R.mipmap.ic_tong);
//                        iv_tong.setVisibility(View.VISIBLE);
//                    }
//                    if(s.indexOf("脱")!=-1) {
//                        iv_tuo.setImageResource(R.mipmap.ic_tuo);
//                        iv_tuo.setVisibility(View.VISIBLE);
//                    }
//                    if(s.indexOf("孕")!=-1) {
//                        iv_yun.setImageResource(R.mipmap.ic_yun);
//                        iv_yun.setVisibility(View.VISIBLE);
//                    }
//
//
//                }
//
//        }
//
//    }
//
//
//    private void doCallDoctor() {
////        String cardNo = Identity.getCardNo();
//        String cardNo = IdentityManager.getCardNo(getActivity());
//        Map<String, String> map = ParametersFactory.getDoctorTeamDataMap(getActivity(), cardNo);
//        RequestBody body = Util.getBodyFromMap(map);
//        mPresenter.maincall(body);
//    }
//
//
//
//    @Override
//    protected void loadData() {
//
//
///*
//        Map<String, String> map = ParametersFactory.getPeopleBaseInfo(sYear,sSignKey,sPersonModel,sPersonId);
//        RequestBody body = Util.getBodyFromMap(map);
//
//        mPresenter.main(body, false);*/
//    }
//
//    @Override
//    public void onLoadListSuccess(HttpResultBaseBean<PeopleBaseInfoBean> bean) {
//        Util.hideGifProgressDialog(getActivity());
//      //  Util.showToast(getActivity(), "获取成功");
//
//
//    }
//
//    @Override
//    public void onLoadMoreListSuccess(HttpResultBaseBean<PeopleBaseInfoBean> bean) {
//
//    }
//
//    @Override
//    public void onLoadListFailure(String message, boolean isLoadMore) {
//
//    }
//
//    @Override
//    public void onLoadDoctorListSuccess(HttpResultBaseBean<List<DoctorTeamInfo>> bean) {
//        if (bean != null) {
//            final List<DoctorTeamInfo> list = bean.getData();
////            for (int i = 0; i < 20; ++i){
////                DoctorTeamInfo info = new DoctorTeamInfo("","","医生"+i, "15209999999", "", "0");
////                list.add(info);
////            }
//            if (!ListUtils.isEmpty(list)) {
////                showPhoneDialog(list);
//                showPhoneDialog2(list);
//            } else {
//                showDoctorPhoneInfoResult(true);
//            }
//        } else {
//            showDoctorPhoneInfoResult(true);
//        }
//    }
//
//    @Override
//    public void onLoadDoctorListFailure(String message) {
//        Util.showToast(getActivity(), message);
//    }
//
//
//    private void showPhoneDialog2(final List<DoctorTeamInfo> list) {
//        BaseQuickAdapter<DoctorTeamInfo, BaseViewHolder> mAdapter = new SignDoctorNameAndPhoneAdapter(list);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        final MaterialDialog dialog = Util.showLinearRecyclerViewMaterialDialog(getActivity(), null, mAdapter);
//        mAdapter.setOnItemClickListener(new SignDoctorNameAndPhoneAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                DoctorTeamInfo dti = list.get(position);
//                String phone = Util.trimString(dti.getDoctorPhone());
//                Util.dial(getActivity(), phone);
//
//                dialog.dismiss();
//            }
//        });
//    }
//
//    private void showDoctorPhoneInfoResult(boolean isEmptyOrFailure) {
//        if (isEmptyOrFailure) {
//            Util.showToast(getActivity(), Constants.HINT_NO_SIGN_DOCTOR_PHONE_INFO);
//        } else {
//            Util.showToast(getActivity(), Constants.HINT_LOAD_SIGN_DOCTOR_PHONE_INFO_FAILURE);
//        }
//    }
//
//
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(getActivity())
//                .getAppComponent()
//                .addPeopleBaseCallFragment(new PeopleBaseInfoFragmentModule(this))
//                .inject(this);
//
//    }
//}
