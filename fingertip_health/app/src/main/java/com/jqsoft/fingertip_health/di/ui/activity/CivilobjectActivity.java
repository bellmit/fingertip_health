package com.jqsoft.fingertip_health.di.ui.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.util.Util;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/27.
 */

public class CivilobjectActivity extends AbstractActivity {
    private LinearLayout saveobject_layout;
    private LinearLayout ll_back;
    @BindView(R.id.ll_yfdx)
    LinearLayout ll_yfdx;
    @BindView(R.id.ll_llk)
    LinearLayout ll_llk;
    @BindView(R.id.ll_lset)
    LinearLayout ll_lset;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_civiobject;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        saveobject_layout = (LinearLayout) findViewById(R.id.saveobject_layout);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        saveobject_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.gotoActivity(CivilobjectActivity.this, SocialAssistanceObject.class);
            }
        });
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ll_yfdx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.showToast(getApplicationContext(), "即将上线，敬请期待");
            }
        });
        ll_llk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.showToast(getApplicationContext(), "即将上线，敬请期待");
            }
        });
        ll_lset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.showToast(getApplicationContext(), "即将上线，敬请期待");
            }
        });

    }

    @Override
    protected void loadData() {

    }
}
