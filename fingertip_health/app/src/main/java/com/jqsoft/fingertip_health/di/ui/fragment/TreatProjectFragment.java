package com.jqsoft.fingertip_health.di.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.TreatFragmentAdapter;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.RegisterResultbean;
import com.jqsoft.fingertip_health.bean.TreatdirectoryBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.TreatFragmentContract;
import com.jqsoft.fingertip_health.di.module.IndexFragmentModule;
import com.jqsoft.fingertip_health.di.module.TreatFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.TreatFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.OutpatientChargesActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/5.
 */

public class TreatProjectFragment extends AbstractFragment implements TreatFragmentContract.View {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.search_button)
    ImageView search_button;
    private String param;
    private ArrayList<TreatdirectoryBean> datalist = new ArrayList<>();
    private TreatFragmentAdapter adapter;
    @Inject
    TreatFragmentPresenter treatFragmentPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.treat_fragment_layout;
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initView() {

        LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutmanager);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                param = et_search.getText().toString();
                if (!TextUtils.isEmpty(param)) {
                    Map<String, String> map = ParametersFactory.TreatForFingertip(getActivity(), param);
                    treatFragmentPresenter.getTreat(map);
                }

            }
        });
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    param = et_search.getText().toString();
                    if (!TextUtils.isEmpty(param)) {
                        Map<String, String> map = ParametersFactory.TreatForFingertip(getActivity(), param);
                        treatFragmentPresenter.getTreat(map);
                    }else{
                        Util.showToast(getActivity().getApplicationContext(),"请输入名称或者助记码搜索");
                    }
                }
                return false;
            }
        });

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void initInject() {
        DaggerApplication.get(this.getActivity())
                .getAppComponent()
                .addTreatFragment(new TreatFragmentModule(this))
                .inject(this);


    }

    @Override
    public void onTreatSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        datalist.clear();
        String resultString = bean.getResult();
        com.alibaba.fastjson.JSONArray array = JSON.parseArray(resultString);
        for (int i = 0; i < array.size(); i++) {
            if ("".equals(array.get(i))) {
            } else {
                com.alibaba.fastjson.JSONObject object1 = array.getJSONObject(i);
                datalist.add(JSON.parseObject(object1.toString(), TreatdirectoryBean.class));
            }
        }
        adapter = new TreatFragmentAdapter(datalist,getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if(resultString.equals("[]")){
            Util.showToast(getActivity(),"未查到相关处方");
        }
    }

    @Override
    public void onTreatFailure(String msg) {
        Util.showToast(getActivity(),msg);

    }
}
