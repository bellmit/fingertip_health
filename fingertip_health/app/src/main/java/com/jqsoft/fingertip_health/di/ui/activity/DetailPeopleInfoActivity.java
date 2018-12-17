package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.GalleryAdapter;
import com.jqsoft.fingertip_health.adapter.HighBloodListItemAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.DeatailPeopleBean;
import com.jqsoft.fingertip_health.bean.DoctorTeamInfo;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.SignDoctorListBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.bean.propMap;
import com.jqsoft.fingertip_health.di.contract.DetailPeopleInfoContract;
import com.jqsoft.fingertip_health.di.contract.HighBloodListActivityContract;
import com.jqsoft.fingertip_health.di.contract.PeopleBaseFragmentContract;
import com.jqsoft.fingertip_health.di.module.DetailPeopleInfoActivityModule;
import com.jqsoft.fingertip_health.di.module.PeopleBaseInfoFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.DetailPeopleInfoActivityPresenter;
import com.jqsoft.fingertip_health.di.presenter.HighBloodListActivityPresenter;
import com.jqsoft.fingertip_health.di.presenter.PeopleBaseFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;

import com.jqsoft.fingertip_health.di.ui.activity.fingertip.PageDetailActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.PeopleSignFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.jqsoft.fingertip_health.utils3.view.HorizontalListView;
import com.jqsoft.fingertip_health.view.PagePointView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.RequestBody;

public class DetailPeopleInfoActivity extends AbstractActivity implements DetailPeopleInfoContract.View {
    DeatailPeopleBean deatailPeopleBean;
    SignDoctorListBean signDoctorListBean;
    LinearLayout ll_back,sinagreement;
    private ListAdapter adapter;
    @Inject
    DetailPeopleInfoActivityPresenter mPresenter;
    ImageView iv_base_sex,iv_phone;
    TextView tv_base_name,tv_pack,tv_base_age,
    tv_base_idcard,tv_phone,tv_base_files,tv_base_doctor,tv_address,tv_base_signdate;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_people_detail;
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            signDoctorListBean = (SignDoctorListBean) bundle.get("data");

        }
    }

    @Override
    protected void initView() {
        sinagreement=(LinearLayout) findViewById(R.id.sinagreement);
        ll_back=(LinearLayout)findViewById(R.id.ll_back);
        tv_base_files=(TextView)findViewById(R.id.tv_base_files);
        tv_pack=(TextView)findViewById(R.id.tv_pack);
        tv_base_idcard=(TextView)findViewById(R.id.tv_base_idcard);
        tv_base_name=(TextView)findViewById(R.id.tv_base_name);
        iv_base_sex=(ImageView)findViewById(R.id.iv_base_sex);
        tv_base_age=(TextView)findViewById(R.id.tv_base_age);
        tv_phone=(TextView)findViewById(R.id.tv_phone);
        tv_base_doctor=(TextView)findViewById(R.id.tv_base_doctor);
        tv_address=(TextView)findViewById(R.id.tv_address);
        tv_base_signdate=(TextView)findViewById(R.id.tv_base_signdate) ;
        iv_phone=(ImageView)findViewById(R.id.iv_phone);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        HorizontalListView listView=(HorizontalListView)findViewById(R.id.listview);
        try {
            List<propMap> resultList = JSON.parseObject(signDoctorListBean.getProperties(),
                    new TypeReference<List<propMap>>(){});
            if (!ListUtils.isEmpty(resultList)) {
                adapter = new HighBloodListItemAdapter(this,resultList);
                listView.setAdapter(adapter);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        if ("男".equals(Util.trimString(signDoctorListBean.getSexName()))){

        }else {
            iv_base_sex.setBackground(getResources().getDrawable(R.mipmap.ic_girl));
        }
        tv_base_age.setText(Util.trimString(signDoctorListBean.getAge())+"岁");
        tv_base_name.setText(Util.trimString(signDoctorListBean.getName()));
        tv_base_idcard.setText(Util.trimString(signDoctorListBean.getIdNo()));
        tv_base_files.setText(Util.trimString(signDoctorListBean.getNo()));
        tv_base_doctor.setText(Util.trimString(signDoctorListBean.getArchivingDoctorName()));
        tv_address.setText(Util.trimString(signDoctorListBean.getAreaFulladdress()));
        tv_base_signdate.setText(Util.trimString(signDoctorListBean.getSignTime()));

        sinagreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(DetailPeopleInfoActivity.this,    SignedAgreement.class);
                Intent intent=new Intent(DetailPeopleInfoActivity.this,    PageDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", (Serializable) signDoctorListBean);
                bundle.putSerializable("data2", (Serializable)deatailPeopleBean);
                intent.putExtras(bundle);

                startActivity(intent);

            }
        });
    }

    @Override
    protected void loadData() {
        String sinstate=signDoctorListBean.getSignState();
       String personid= signDoctorListBean.getId();
        Map<String, String> map = ParametersFactory.
                getDetailPeopleInfo(this,2018,"2",personid,sinstate);
        mPresenter.getdata(map);
    }




    public String getDeliveredStringByKey(String key) {
        if (StringUtils.isBlank(key)) {
            return Constants.EMPTY_STRING;
        } else {
            key = Util.trimString(key);
            Intent intent = getIntent();
            if (intent == null) {
                return Constants.EMPTY_STRING;
            } else {
                String result = intent.getStringExtra(key);
                return result;
            }
        }
    }

    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addDetailPeopleInfo(new DetailPeopleInfoActivityModule(this))
                .inject(this);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        warnExit();
    }
    private void showNotificationSuccessOrFailureView(boolean success, HttpResultBaseBeanForFingertip<String> bean){
        if (success) {
            if (bean!=null){
                String resultString = bean.getResult();
                try {
                   final DeatailPeopleBean resultList = JSON.parseObject(resultString,
                            new TypeReference<DeatailPeopleBean>(){});
                    if (resultList!=null) {
                        deatailPeopleBean=resultList;
                        tv_pack.setText(resultList.getServicePackageList().get(0).getPackageName());
//                            mAdapter.setNewData(resultList);

                        tv_phone.setText("tel:"+resultList.getSignDoctorPhone());
                        final String phone=resultList.getSignDoctorPhone();
                        iv_phone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                Uri data = Uri.parse("tel:" + phone);
                                intent.setData(data);
                                startActivity(intent);

                            }
                        });

                    } else {
                        Toast.makeText(this,"暂无服务包信息",Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this,"暂无服务包信息",Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,"暂无服务包信息",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"暂无服务包信息",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoadListSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        showNotificationSuccessOrFailureView(true,bean);
    }

    @Override
    public void onLoadListFailure(String message) {

    }
}
