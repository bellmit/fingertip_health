package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.jqsoft.fingertip_health.R;

import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;

import butterknife.BindView;

//社会救助
public class SocialAssestansActivity extends AbstractActivity {
    private DaggerApplication application;
    @BindView(R.id.ll_dibao)
    LinearLayout ll_dibao;

    @BindView(R.id.ll_back)
    LinearLayout ll_back;


    @BindView(R.id.ll_ruhu)
    LinearLayout ll_ruhu;


    @BindView(R.id.ll_tekun)
    LinearLayout ll_tekun;

    @BindView(R.id.ll_yiliao)
    LinearLayout ll_yiliao;

    @BindView(R.id.ll_lishi)
    LinearLayout ll_lishi;

    @BindView(R.id.ll_souzai)
    LinearLayout ll_souzai;

    @BindView(R.id.ll_minzhu)
    LinearLayout ll_minzhu;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_spcial_aseerstans;
    }

    @Override
    protected void initData() {
        application = (DaggerApplication)this.getApplication();

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });
    }


    @Override
    protected void initView() {
        ll_dibao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("ItemId", "ITEM_DIBAO");
                intent.putExtra("titils", "城乡低保");
                intent.setClass(getApplicationContext(), UrbanLowInsuranceActivity.class);
                startActivity(intent);
            }
        });


        ll_tekun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("ItemId", "ITEM_TEKUN");
                intent.putExtra("titils", "特困人员供养");
                intent.setClass(getApplicationContext(), UrbanLowInsuranceActivity.class);
                startActivity(intent);
            }
        });

        ll_yiliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("ItemId", "ITEM_YILIAO");
                intent.putExtra("titils", "医疗救助");
                intent.setClass(getApplicationContext(), UrbanLowInsuranceActivity.class);
                startActivity(intent);
            }
        });

        ll_lishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("ItemId", "ITEM_LINSHI");
                intent.putExtra("titils", "临时救助");
                intent.setClass(getApplicationContext(), UrbanLowInsuranceActivity.class);
                startActivity(intent);
            }
        });

        ll_souzai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("ItemId", "ITEM_JJN");
                intent.putExtra("titils", "受灾救助");
                intent.setClass(getApplicationContext(), UrbanLowInsuranceActivity.class);
                startActivity(intent);
            }
        });



        ll_ruhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.setTableType("1");
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), HouseholdSurveysActivity.class);
                startActivity(intent);
            }
        });
        ll_minzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                application.setTableType("2");
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), HouseholdSurveysActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {

    }


}
