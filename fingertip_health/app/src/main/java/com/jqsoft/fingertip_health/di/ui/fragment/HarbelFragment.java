package com.jqsoft.fingertip_health.di.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.HarbelFragmentAdapter;
import com.jqsoft.fingertip_health.adapter.RecipeFragmentAdapter;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.DrugInfo;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.HarbelFragmentContract;
import com.jqsoft.fingertip_health.di.module.HarbelFragmentModule;
import com.jqsoft.fingertip_health.di.module.RecipeFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.HarbelFragmentPresenter;
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

public class HarbelFragment extends AbstractFragment implements HarbelFragmentContract.View {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.search_button)
    ImageView search_button;
    private ArrayList<DrugInfo> datalist = new ArrayList<>();
    private String param;
    private HarbelFragmentAdapter adapter;
    @Inject
    HarbelFragmentPresenter fragmentPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.harbel_fragment_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutmanager);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                param = et_search.getText().toString();
                if (!TextUtils.isEmpty(param)) {
                    Map<String, String> map = ParametersFactory.HarbleForFingertip(getActivity(), param);
                    fragmentPresenter.getHarbel(map);
                }

            }
        });
//        et_search.requestFocus();
//        et_search.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
//                    param = et_search.getText().toString();
//                    if (!TextUtils.isEmpty(param)) {
//                        Map<String, String> map = ParametersFactory.HarbleForFingertip(getActivity(), param);
//                        fragmentPresenter.getHarbel(map);
//                    }
//                } else {
//                    // do other things
//                }
//                return false;
//            }
//        });
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    param = et_search.getText().toString();
                    if (!TextUtils.isEmpty(param)) {
                        Map<String, String> map = ParametersFactory.HarbleForFingertip(getActivity(), param);
                        fragmentPresenter.getHarbel(map);
                    }else{
                        Util.showToast(getActivity().getApplicationContext(),"请输入名称或者助记码搜索");
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void initInject() {
        DaggerApplication.get(this.getActivity())
                .getAppComponent()
                .addharbelFragment(new HarbelFragmentModule(this))
                .inject(this);


    }

    @Override
    public void onHarbelSuccess(HttpResultBaseBeanForFingertip<String> bean) {
        datalist.clear();
        String resultString = bean.getResult();
        com.alibaba.fastjson.JSONArray array = JSON.parseArray(resultString);
        for (int i = 0; i < array.size(); i++) {
            if ("".equals(array.get(i))) {
            } else {
                com.alibaba.fastjson.JSONObject object1 = array.getJSONObject(i);
                datalist.add(JSON.parseObject(object1.toString(), DrugInfo.class));
            }
        }
        adapter = new HarbelFragmentAdapter(datalist, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if (resultString.equals("[]")) {
            Util.showToast(getActivity(), "未查到相关处方");
        }
    }

    @Override
    public void onHarbelFailure(String msg) {
        Util.showToast(getActivity(), msg);
    }
}
