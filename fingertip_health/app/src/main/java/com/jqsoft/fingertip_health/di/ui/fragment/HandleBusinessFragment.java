package com.jqsoft.fingertip_health.di.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.di.presenter.InHospitalInspectFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.SignServiceAssessActivity;
import com.jqsoft.fingertip_health.di.ui.activity.SocialAssestansActivity;
import com.jqsoft.fingertip_health.di.ui.activity.WorkbenchActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.util.Util;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by quantan.liu on 2017/3/22.
 * 头条新闻会出现一些问题因为API不稳定。。。
 */

public class HandleBusinessFragment extends AbstractFragment {
    private static HandleBusinessFragment instance = null;


    @BindView(R.id.ll_zhudong)
    LinearLayout ll_zhudong;


    @BindView(R.id.socailayout)
    LinearLayout socailayout;


    @BindView(R.id.ll_fangzai)
    LinearLayout ll_fangzai;

    @BindView(R.id.ll_zhili)
    LinearLayout ll_zhili;

    @BindView(R.id.ll_fuwu)
    LinearLayout ll_fuwu;

    @BindView(R.id.ll_guofang)
    LinearLayout ll_guofang;

    @Inject
    InHospitalInspectFragmentPresenter mPresenter;


    @Override
    protected void loadData() {

    }

    public static HandleBusinessFragment getInHospitalInspectFragment() {
        if (instance == null) {
            instance = new HandleBusinessFragment();
        }
        return instance;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_handle_business;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }

    public void reassignToolbar() {
        WorkbenchActivity workbenchActivity = (WorkbenchActivity) getActivity();
        if (workbenchActivity != null) {
            Toolbar toolbar = (Toolbar) workbenchActivity.findViewById(R.id.toolbar2);
            //LogUtil.i("InHospitalInspectFragment initView toolbar:" + toolbar);
            workbenchActivity.setToolBarWithNoBackButtonAndNoTitle(toolbar);
        }

    }

    @Override
    protected void initView() {
        instance = this;

        setHasOptionsMenu(true);

        ll_zhudong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SignServiceAssessActivity.class);
                getActivity().startActivity(intent);
            }
        });

        socailayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), SocialAssestansActivity.class);
                getActivity().startActivity(intent);
            }
        });

        ll_fangzai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"即将上线,敬请期待!",Toast.LENGTH_SHORT).show();
            }
        });

        ll_zhili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"即将上线,敬请期待!",Toast.LENGTH_SHORT).show();
            }
        });

        ll_fuwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"即将上线,敬请期待!",Toast.LENGTH_SHORT).show();
            }
        });

        ll_guofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"即将上线,敬请期待!",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        menu.clear();


    }



    @Override
    protected void initInject() {
       /* DaggerApplication.get(this.getActivity())
                .getAppComponent()
                .addInHospitalInspectFragment(new InHospitalInspectFragmentModule(this))
                .inject(this);*/

    }

    public void simulateData() {


    }


    public Map<String, String> getRequestMap() {


        String orgId = Identity.getOrganizationKey();

        String docUserId = Identity.getUserId();
        String encodedorgId = Util.getBase64String(orgId);
        String encodeddocUserId = Util.getBase64String(docUserId);


        String cardNo = IdentityManager.getCardNo(getActivity());
        Map<String, String> map = ParametersFactory.getHospitalInspectListMap(getActivity(), cardNo);
        return map;
    }


    private String getListEmptyHint() {
        return getResources().getString(R.string.hint_list_empty_inhospital);
    }

    private String getFailureHint() {
        return getResources().getString(R.string.hint_load_failure);
    }


}
