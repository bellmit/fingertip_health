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
import com.jqsoft.fingertip_health.adapter.RecipeFragmentAdapter;
import com.jqsoft.fingertip_health.adapter.TreatFragmentAdapter;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.DrugInfo;
import com.jqsoft.fingertip_health.bean.TreatdirectoryBean;
import com.jqsoft.fingertip_health.bean.fingertip.HttpResultBaseBeanForFingertip;
import com.jqsoft.fingertip_health.di.contract.RecipeFragmentContract;
import com.jqsoft.fingertip_health.di.module.RecipeFragmentModule;
import com.jqsoft.fingertip_health.di.module.TreatFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.RecipeFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/5.
 */

public class RecipeFragment extends AbstractFragment implements RecipeFragmentContract.View {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.search_button)
    ImageView search_button;
    private ArrayList<DrugInfo> datalist = new ArrayList<>();
    private String param;
    private RecipeFragmentAdapter adapter;
    @Inject
    RecipeFragmentPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.recipe_fragment_layout;
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
                    Map<String, String> map = ParametersFactory.RecipeForFingertip(getActivity(), param);
                    mPresenter.getRecipe(map);
                }else {
                    Util.showToast(getActivity().getApplicationContext(),"请输入名称或者助记码搜索");
                }

            }
        });
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    param = et_search.getText().toString();
                    if (!TextUtils.isEmpty(param)) {
                        Map<String, String> map = ParametersFactory.RecipeForFingertip(getActivity(), param);
                        mPresenter.getRecipe(map);
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
                .addRecipeFragment(new RecipeFragmentModule(this))
                .inject(this);


    }

    @Override
    public void onRecipeSuccess(HttpResultBaseBeanForFingertip<String> bean) {
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
        adapter = new RecipeFragmentAdapter(datalist,getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if(resultString.equals("[]")){
            Util.showToast(getActivity(),"未查到相关处方");
        }

    }

    @Override
    public void onRecipeFailure(String msg) {
        Util.showToast(getActivity(),msg);

    }
}
