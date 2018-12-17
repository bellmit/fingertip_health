package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.IgGuideAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.IgGuideBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.IgGuidePostBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.IgGuideActivityContract;
import com.jqsoft.fingertip_health.di.module.IgGuideActivityModule;
import com.jqsoft.fingertip_health.di.presenter.IgGuideActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.adapter.IgGuideAdapter.TYPE_MULTIPLE_LINE;

/**
 *
 * 智能引导
 */

public class IgGuideActivity extends AbstractActivity  implements    IgGuideActivityContract.View,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;
    @BindView(R.id.policy_title)
    TextView title;
    @BindView(R.id.lay_policy_load_failure)
    View failureView;

    TextView tvFailureView;
    @BindView(R.id.Reset)
    TextView reset;
    @BindView(R.id.post)
    TextView post;
    @BindView(R.id.Rpost)
    RelativeLayout   Rpost  ;
    @BindView(R.id.RReset)
    RelativeLayout   RReset  ;
    List<IgGuideBean> list;
    HashMap<String, String> mSingData = new HashMap<String, String>();
    HashMap<String, List<String>> MuData = new HashMap<String, List<String>>();
    List<String> qusId=new ArrayList<>();
    String anid=null;
    @BindView(R.id.ll_post)
    LinearLayout ll_post;

    @Inject
    IgGuideActivityPresenter mPresenter;
    private IgGuideBean IgGuideBean;
    private IgGuideAdapter mAdapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    public static IgGuideActivity instance = null;
    private boolean isRefresh = false;
    List<IgGuidePostBean> IgGuidePostBeanlist;
    Boolean ischoose=false;
    String areacode;
    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addIgGuideActivity(new IgGuideActivityModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_iguide_layout;
    }

    @Override
    protected void initData() {
        areacode=(String)getDeliveredSerializableByKey(Constants.GUIDE_ITEM_ACTIVITY_KEY);
//        if (areacode==null){
//            areacode= Identity.srcInfo.getAreaId();
//        }

//        Util.setAreaCode(areacode);
    }

    @Override
    protected void initView() {
        if (areacode==null){
            areacode= Util.getAreaCode();
        }
        instance=this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        title.setText("智能引导");

        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });


        final BaseQuickAdapter<IgGuideBean, BaseViewHolder> mAdapter = new IgGuideAdapter(new ArrayList<IgGuideBean>(), TYPE_MULTIPLE_LINE,this);
        this.mAdapter = (IgGuideAdapter) mAdapter;
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, true);
        recyclerView.setAdapter(mAdapter);
        srl.setRefreshing(false);
        srl.setOnRefreshListener(this);
        RReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((IgGuideAdapter) mAdapter).restdata();
                qusId.clear();
                MuData.clear();
                mSingData.clear();
                ischoose=false;
                mAdapter.notifyDataSetChanged();
            }
        });
        Rpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ischoose1and2();
                if(ischoose){
                    postdata( MixChooseData());
                }else {
                    Toast.makeText(IgGuideActivity.this,"请选择必选项",Toast.LENGTH_SHORT).show();
                }

//                ischoose1and2();



            }
        });

        
    }




    @Override
    protected void loadData() {
        LoadData();
    }




    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<IgGuideBean>>beanList) {

        int listSize = getListSizeFromResult(beanList);
        list = getListFromResult(beanList);
        if(beanList==null ){
            showRecyclerViewOrFailureView(true,false);
        }else {
            if (listSize==0 ){showRecyclerViewOrFailureView(true,true);}
            mAdapter.setNewData(list);
            srl.setRefreshing(false);
            isRefresh = false;


        }
}

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        srl.setRefreshing(false);
        showRecyclerViewOrFailureView(false, true);
        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
    }

    @Override
    public void onPostSuccess(GCAHttpResultBaseBean<List<IgGuidePostBean>> beanList) {
        IgGuidePostBeanlist = beanList.getData();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.IGGUIDE_ITEM_ACTIVITY_KEY, (Serializable) IgGuidePostBeanlist);
//        bundle.putSerializable(Constants.IGGUIDE_ITEM_ACTIVITY_KEY, (Serializable) "11");
        ischoose1and2();
        if(ischoose){
//            Intent  i=new Intent(IgGuideActivity.this, IgGuidePostListActivity.class);
//            i.putExtra(Constants.IGGUIDE_ITEM_ACTIVITY_KEY,(Serializable) IgGuidePostBeanlist);
//                startActivity(i);
        Util.gotoActivityWithBundle(IgGuideActivity.this, IgGuidePostListActivity.class, bundle);
        }else {
            Toast.makeText(this,"请选择必选项",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPostFailure(String message) {
        srl.setRefreshing(false);
        showRecyclerViewOrFailureView(false, true);
        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
        Log.d("onPostFailure", "onPostFailure: "+message);
        ischoose1and2();
    }

    public void LoadData(){

        Map<String, String> map = ParametersFactory.getGCAIgGuideMap
                (this,areacode, "inteGuidData.queryQuesListItg");
        mPresenter.main(map, false);
        isRefresh = true;
        mAdapter.setEnableLoadMore(false);
        srl.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        LoadData();


    }
    public int getListSizeFromResult(GCAHttpResultBaseBean<List<IgGuideBean>> beanList) {
        if (beanList != null) {
            List<IgGuideBean> list = beanList.getData();
            if (list != null) {
                int size = ListUtils.getSize(list);
                return size;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
    public List<IgGuideBean> getListFromResult(GCAHttpResultBaseBean<List<IgGuideBean>> beanList) {
        if (beanList != null) {
            List<IgGuideBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }

    public void saveData(String qusid, String str) {
//        Toast.makeText(this,str+"----"+qusid,Toast.LENGTH_LONG).show();
        qusId.add(qusid);
        mSingData.put(qusid,str);
    }
    public void saveMUData(String qusid, String str) {
         List<String> AnswerIdList= MuData.get(qusid);
         if (AnswerIdList==null){
             AnswerIdList=new ArrayList<>();
         }
        AnswerIdList.add(str);

        MuData.put(qusid,AnswerIdList);
        qusId.add(qusid);

    }
    public void removeMuData(String qusid,String str){
        List<String> AnswerIdList= MuData.get(qusid);
        if (str==null){  }else {
            AnswerIdList.remove(str);}

    }
    public void removeSingleChooseData(String qusid,String str){
        mSingData.remove(qusid);
    }

    public String MixChooseData(){
        String qusStr=null;
        String ansStr=null;
        String DataStr=null;
        List<String> dataList=new ArrayList<>();
        //去重复
        HashSet h = new HashSet(qusId);
        qusId.clear();
        qusId.addAll(h);
        for (int i=0;i<qusId.size();i++){
                qusStr = qusId.get(i);
          if (foundSingleChooseList(qusStr)==null){
              if (foundMUChooseList(qusStr)==null){
              }else {
                  //是多选答案添加到答案list
                  DataStr=qusStr+"_"+foundMUChooseList(qusStr);
                  dataList.add(DataStr);

              }

          } else {
            //是单选答案添加到答案list
              DataStr=qusStr+"_"+ foundSingleChooseList(qusStr);
              dataList.add(DataStr);
          }


        }
        //去重复
        HashSet h1 = new HashSet(dataList);
        dataList.clear();
        dataList.addAll(h1);
        //最终  终级无敌 提交用String
        String FinallyStr=null;
        for (int i=0;i<dataList.size();i++){
            if (FinallyStr==null){
                FinallyStr=dataList.get(i);
            }else {

                FinallyStr=FinallyStr+";"+dataList.get(i);
            }

        }

//        Toast.makeText(this,FinallyStr,Toast.LENGTH_LONG).show();
        if (FinallyStr==null){}else {
            Log.d("FinallyStr", FinallyStr);
        }

        return FinallyStr;
    }

private String foundSingleChooseList(String qusid){
        if ( mSingData.get(qusid)==null){
            return null;
        }else {
          return   mSingData.get(qusid);
        }

}

    private String foundMUChooseList(String qusid){
        if ( MuData.get(qusid)==null){
            return null;
        }else {
            List<String> AnswerIdList= MuData.get(qusid);
            String ansStr=null;
            for (int i=0;i<AnswerIdList.size();i++){
                if (ansStr==null){
                    ansStr=AnswerIdList.get(i);
                }else {
                    ansStr=ansStr+"_"+AnswerIdList.get(i);
                }

            }
            return   ansStr;
        }

    }
    private  void postdata(String data){


        if (data==null){
            Toast.makeText(this,"请选择必选项",Toast.LENGTH_LONG).show();}
        Map<String, String> map = ParametersFactory.getGCAIgPostMap
                (this, areacode,"inteGuidData.queryItemListItg",data);

        mPresenter.post(map);



    }
    private void ischoose1and2() {

        for (int i = 0; i < qusId.size(); i++) {
            if (qusId.get(i).equals(list.get(0).getQuestionId())) {

                for (i = 0; i < qusId.size(); i++) {
                    if (qusId.get(i).equals(list.get(1).getQuestionId())) {
                        ischoose=true;
                    }
                }


            }
        }
    }
    public void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
        if (success){
            if (isListEmpty){
                srl.setVisibility(View.GONE);
                ll_post.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
            } else {
                ll_post.setVisibility(View.VISIBLE);
                srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
                mAdapter.setEnableLoadMore(false);
                mAdapter.loadMoreComplete();
            }
        } else {
            ll_post.setVisibility(View.GONE);
            srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());

        }
    }
    private String getListEmptyHint(){
        String s = getResources().getString(R.string.hint_no_wenjuan_assess_info_please_click_to_reload);
        return s;
//        return getResources().getString(R.string.hint_list_empty_please_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_failure);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
