package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.AccessFileAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.PersonnelInfoData;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultEmptyBean;
import com.jqsoft.fingertip_health.di.contract.AccessFileContract;
import com.jqsoft.fingertip_health.di.module.AccessFileModule;
import com.jqsoft.fingertip_health.di.presenter.AccessFilePresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.GlideUtils;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.RequestBody;

public class AccessFileActivity extends AbstractActivity implements AccessFileContract.View{



    @Inject
    AccessFilePresenter mPresenter;
    private String cardNo,personId,flag;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_file_name)
    TextView tv_file_name;

    @BindView(R.id.iv_file_sex)
    ImageView iv_file_sex;

    @BindView(R.id.tv_file_age)
    TextView tv_file_age;

    @BindView(R.id.tv_file_phone)
    TextView tv_file_phone;

    @BindView(R.id.tv_file_idcard)
    TextView tv_file_idcard;

    @BindView(R.id.tv_file_contact)
    TextView tv_file_contact;

    @BindView(R.id.tv_blood_type)
    TextView tv_blood_type;

    @BindView(R.id.tv_drug_allergy)
    TextView tv_drug_allergy;

    /*@BindView(R.id.tv_past_history)
    TextView tv_past_history;*/

    @BindView(R.id.tv_genetic_history)
    TextView tv_genetic_history;

    @BindView(R.id.tv_disease_info)
    TextView tv_disease_info;

    @BindView(R.id.tv_create_file)
    TextView tv_create_file;

    @BindView(R.id.tv_create_org)
    TextView tv_create_org;

    @BindView(R.id.tv_doctor_name)
    TextView tv_doctor_name;

    @BindView(R.id.lv_past_history)
    ListView lv_past_history;


    @BindView(R.id.tv_rh)
    TextView tv_rh;

    @BindView(R.id.ll_phone)
    LinearLayout ll_phone;


    @BindView(R.id.img_head)
    ImageView img_head;

    private AccessFileAdapter accessFileAdapter;

    private   PersonnelInfoData mPersonnelInfoData;
    private ArrayList<String> arrayPastList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_access_file;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);
    }

    @Override
    protected void loadData() {
        personId=(String) this.getIntent().getSerializableExtra("personId");
        flag=(String) this.getIntent().getSerializableExtra("flag");
      //  cardNo="342123195803094569";

        if(flag.equals("1")){

            Map<String, String> map = ParametersFactory.getPersonnelInfo(this, personId);
            RequestBody body = Util.getBodyFromMap1(map);

            mPresenter.main(body, false);
        }else{
            Map<String, String> map = ParametersFactory.getPersonnelInfo2(this, personId);
            RequestBody body = Util.getBodyFromMap1(map);

            mPresenter.main(body, false);
        }


    }


    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addAccessFile(new AccessFileModule(this))
                .inject(this);
    }

    @Override
    public void onLoadAccessFileSuccess(HttpResultBaseBean<PersonnelInfoData> bean) {
        Util.hideGifProgressDialog(this);
        if(bean!=null){
            mPersonnelInfoData =bean.getData();
            tv_file_name.setText(mPersonnelInfoData.getUserName());

            String sexName =mPersonnelInfoData.getSexName();
            if(sexName.equals("男")){
                iv_file_sex.setImageResource(R.mipmap.i_male);
            }else if(sexName.equals("女")){
                iv_file_sex.setImageResource(R.mipmap.i_female);
            }


            String headUrl = Util.trimString(mPersonnelInfoData.getPhotoUrl());
            String  imageUrl= Version.FILE_URL_BASE+headUrl;

            GlideUtils.loadImage(imageUrl,img_head);



            tv_file_age.setText(mPersonnelInfoData.getAge()+"岁");
            String sPhone =mPersonnelInfoData.getPhone();
            if(TextUtils.isEmpty(sPhone) ){
                ll_phone.setVisibility(View.GONE);
            }else{
                ll_phone.setVisibility(View.VISIBLE);
                tv_file_phone.setText(mPersonnelInfoData.getPhone());
            }


            tv_file_idcard.setText(mPersonnelInfoData.getCardNo());
            tv_file_contact.setText(mPersonnelInfoData.getContactName());
            tv_blood_type.setText(mPersonnelInfoData.getBloodName());
            tv_drug_allergy.setText(mPersonnelInfoData.getDrugName());

            String  sPastHistoryList = mPersonnelInfoData.getDiseaseName();
            if(sPastHistoryList.equals("null") || sPastHistoryList==null){

            }else {
                String[] temp = null;
                temp = sPastHistoryList.split(";");

                arrayPastList.clear();
                for (int i = 0; i < temp.length; i++) {
                    arrayPastList.add(temp[i]);
                }


                accessFileAdapter = new AccessFileAdapter(getApplicationContext(), arrayPastList);
                lv_past_history.setAdapter(accessFileAdapter);
                setListViewHeightBasedOnChildren(lv_past_history);

            }

            tv_rh.setText(mPersonnelInfoData.getRhBloodName());

            tv_genetic_history.setText(mPersonnelInfoData.getHerediratyDiseaseName());
            tv_disease_info.setText(mPersonnelInfoData.getPhysicalDiseaseName());//遗传情况

            tv_create_org.setText(mPersonnelInfoData.getCreateDept());
            tv_doctor_name.setText(mPersonnelInfoData.getDoctorName());

            String CreateDate = mPersonnelInfoData.getCreateTime();
            if(TextUtils.isEmpty(CreateDate) || CreateDate.equals("null") || CreateDate==null){
                tv_create_file.setText("");
            }else{
                if(CreateDate.length()<10){
                    tv_create_file.setText(mPersonnelInfoData.getCreateTime());
                }else{
                    tv_create_file.setText(mPersonnelInfoData.getCreateTime().substring(0,10));
                }
            }




        }

    }

    @Override
    public void onLoadAccessFileFailure(String message) {
        Util.hideGifProgressDialog(this);
        if(message!=null){

        }
    }

    @Override
    public void onLoadUpdatePeopleSuccess(HttpResultBaseBean<HttpResultEmptyBean> bean) {

    }

    @Override
    public void onLoadUpdatePeopleFailure(String message) {

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
