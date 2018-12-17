package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.QuestionAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.QuestionBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.QuestionActivityContract;
import com.jqsoft.fingertip_health.di.module.QuestionActivityModule;
import com.jqsoft.fingertip_health.di.presenter.QuestionActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.view.MaterialSearchViewNew;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.adapter.ReceptionListAdapter.TYPE_MULTIPLE_LINE;

//常见问题
public class QuestionActivity extends AbstractActivity implements
        QuestionActivityContract.View, SwipeRefreshLayout.OnRefreshListener,View.OnClickListener, BaseQuickAdapter.RequestLoadMoreListener
     {

         @BindView(R.id.tv_qustype)
         TextView tv_qustype;
         @BindView(R.id.tv_qustype1)
         TextView tv_qustype1;
         @BindView(R.id.tv_qustype2)
         TextView tv_qustype2;
         @BindView(R.id.tv_qustype3)
         TextView tv_qustype3;
         @BindView(R.id.rl_qustype)
         RelativeLayout rl_qustype;
         @BindView(R.id.rl_qustype1)
         RelativeLayout rl_qustype1;
         @BindView(R.id.rl_qustype2)
         RelativeLayout rl_qustype2;
         @BindView(R.id.rl_qustype3)
         RelativeLayout rl_qustype3;
    @BindView(R.id.view_search)
    MaterialSearchViewNew searchView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.qus_typename)
    TextView qus_typename;
    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;
    @BindView(R.id.policy_title)
    TextView tv_title;
    @BindView(R.id.query_btn)
         ImageView query_btn;
    @BindView(R.id.lay_policy_load_failure)
    View failureView;
    private  String code,titlename;
    TextView tvFailureView;
    @Inject
    QuestionActivityPresenter mPresenter;
    @BindView(R.id.et_search)
         EditText et_search;
    @BindView(R.id.bt_username_clear)
         Button bt_username_clear;
         @BindView(R.id.tv_more)
         TextView tv_more;
         private  int sublistsize=0;
    private String type;
        int adptersize=0;
    private QuestionAdapter mAdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;


    private String id;

    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
    private int pageSize = 12;
         private int qcurrentPage = Constants.DEFAULT_INITIAL_PAGE;
         private int qpageSize = 12;
         List<QuestionBean> alllist;
         List<SRCLoginDataDictionaryBean> srcLoginDataDictionaryBeans;
         private  String checktype="false";
        private String idString;


    @Override
    protected void loadData() {
        Map<String, String> map = ParametersFactory.getGCAQuestionMap1(this,String.valueOf(currentPage),String.valueOf(pageSize),
                "commonQuestion.commonQuestionList");

         mPresenter.main(map,false);
    }


         @Override
         protected void initInject() {
             super.initInject();
             DaggerApplication.get(this)
                     .getAppComponent()
                     .addQuestionActivity(new QuestionActivityModule(this))
                     .inject(this);
         }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_question;
    }

    @Override
    protected void initData() {
//        receptionBean=(gdws_sys_area)getDeliveredSerializableByKey(Constants.RECEPTION_ITEM_ACTIVITY_KEY);



    }

    @Override
    protected void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
//        bannerTitle.setText(receptionBean.getAreaName());

        tv_title.setText("常见问题");

        srcLoginDataDictionaryBeans = DataSupport.where(" pcode=? and state=?", "question_type","0").find(SRCLoginDataDictionaryBean.class);
      switch (srcLoginDataDictionaryBeans.size()) {
          case 1:
            tv_qustype.setText(srcLoginDataDictionaryBeans.get(0).getName());
              rl_qustype.setOnClickListener(this);
              break;
          case 2:
              tv_qustype.setText(srcLoginDataDictionaryBeans.get(0).getName());
              rl_qustype.setOnClickListener(this);
              tv_qustype1.setText(srcLoginDataDictionaryBeans.get(1).getName());
              rl_qustype1.setOnClickListener(this);
              break;
          case 3:
              tv_qustype.setText(srcLoginDataDictionaryBeans.get(0).getName());
              rl_qustype.setOnClickListener(this);
              tv_qustype1.setText(srcLoginDataDictionaryBeans.get(1).getName());
              rl_qustype1.setOnClickListener(this);
              tv_qustype2.setText(srcLoginDataDictionaryBeans.get(2).getName());
              rl_qustype2.setOnClickListener(this);
              break;
          case 4:
              tv_qustype.setText(srcLoginDataDictionaryBeans.get(0).getName());
              rl_qustype.setOnClickListener(this);
              tv_qustype1.setText(srcLoginDataDictionaryBeans.get(1).getName());
              rl_qustype1.setOnClickListener(this);
              tv_qustype2.setText(srcLoginDataDictionaryBeans.get(2).getName());
              rl_qustype2.setOnClickListener(this);
              tv_qustype3.setText(srcLoginDataDictionaryBeans.get(3).getName());
              rl_qustype3.setOnClickListener(this);
              break;

      }










        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
//        tvFailureView.setText(failureString);
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable.toString())){
                    bt_username_clear.setVisibility(View.VISIBLE);
                }
            }
        });
        query_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           String      tempString = et_search.getText().toString();

                if (!TextUtils.isEmpty(tempString)) {
                    Map<String, String> map1 = ParametersFactory.getGCASeachQuestionMap(QuestionActivity.this,tempString,
                            "commonQuestion.commonQuestionList");

                    mPresenter.main(map1,false);
                } else {
                    Util.showToast(QuestionActivity.this,"请输入关键字查询");
                }
            }
        });
        bt_username_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
            }
        });
        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srl.setOnRefreshListener(this);


        final BaseQuickAdapter<QuestionBean, BaseViewHolder> mAdapter = new QuestionAdapter(new ArrayList<QuestionBean>(), TYPE_MULTIPLE_LINE,this);
        this.mAdapter = (QuestionAdapter) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//               Util.showToast(PoliticsActivity.this, position+" is clicked");
                QuestionBean pb = mAdapter.getItem(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.RELIEF_Question_ACTIVITY_KEY,   pb.getId());
//
                Util.gotoActivityWithBundle(QuestionActivity.this, QuestionDetailActivity.class, bundle);
            }
        });
        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_search.setText("");
                if (checktype.equals("true")){
                    qcurrentPage=qcurrentPage+5;
                    Map<String, String> map = ParametersFactory.getGCAQuestiontypeMap(QuestionActivity.this,idString,String.valueOf(qcurrentPage),String.valueOf(qpageSize),type,
                            "commonQuestion.commonQuestionList");

                    mPresenter.getmore(map,false);

                }else {
                    currentPage=currentPage+5;
                    Map<String, String> map = ParametersFactory.getGCAQuestionMap(QuestionActivity.this,idString,String.valueOf(currentPage),String.valueOf(pageSize),
                            "commonQuestion.commonQuestionList");
                    mPresenter.getmore(map,true);
                }

       }
        });

    }


    @Override
    public void onRefresh() {
        adptersize=0;
        checktype="false";
        currentPage = Constants.DEFAULT_INITIAL_PAGE;
        qcurrentPage = Constants.DEFAULT_INITIAL_PAGE;
        qus_typename.setText("常见问题");
        loadData();

    }

         private  void  qustypeload(){
             Map<String, String> map = ParametersFactory.getGCAQuestiontypeMap1(this,String.valueOf(qcurrentPage),String.valueOf(qpageSize),type,
                     "commonQuestion.commonQuestionList");

             mPresenter.main(map,false);
         }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public List<QuestionBean> getListFromResult(GCAHttpResultBaseBean<List<QuestionBean>> beanList) {
        if (beanList != null) {
            List<QuestionBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }
    public int getListSizeFromResult(GCAHttpResultBaseBean<List<QuestionBean>> beanList) {
        if (beanList != null) {
            List<QuestionBean> list = beanList.getData();
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

    public void setLoadMoreStatus(int pageSize, int listSize, boolean isSuccessful) {
        if (isSuccessful) {
            if (listSize < pageSize) {
//                mAdapter.setEnableLoadMore(false);
                mAdapter.loadMoreEnd(true);
            } else {
                mAdapter.setEnableLoadMore(true);
                mAdapter.loadMoreComplete();
            }
        } else {
            mAdapter.setEnableLoadMore(true);
            mAdapter.loadMoreFail();
        }
    }
    public int getadaptersize(){

        adptersize=adptersize+1;
//        if (adptersize<alllist.size()){
//
//
//        }else {
//            adptersize=0;
//        }
        return adptersize;
    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<QuestionBean>> beanList) {


        int listSize = getListSizeFromResult(beanList);
        alllist = getListFromResult(beanList);
        for (int i=0;i<alllist.size();i++){
            if (i==0){
                idString= alllist.get(i).getId();

            }else {
                idString=idString+","+alllist.get(i).getId();
            }
        }




            showRecyclerViewOrFailureView(true, alllist.size()==0);

//        list_size.setText("共有"+listSize+"个受理中心");
            srl.setRefreshing(false);
//            setLoadMoreStatus(pageSize, listSize, true);
//        mAdapter.setEnableLoadMore(true);
        mAdapter.setNewData(alllist);

        setLoadMoreStatus(pageSize,listSize,true);




//            showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));

//        list_size.setText("共有"+listSize+"个受理中心");


//        mAdapter.setEnableLoadMore(true);




    }


    @Override
    public void onLoadListFailure(String message) {
        showRecyclerViewOrFailureView(false, true);
        Log.d("onLoadListFailure",message);

        srl.setRefreshing(false);
        setLoadMoreStatus(0, 0, false);
////        Util.showToast(this, message);


    }

         @Override
         public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<QuestionBean>> beanList) {

             int listSize = getListSizeFromResult(beanList);
             List<QuestionBean>  list = getListFromResult(beanList);

             for (int i=0;i<alllist.size();i++){
                 if (i==0){
                     idString= alllist.get(i).getId();

                 }else {
                     idString=idString+","+alllist.get(i).getId();
                 }
             }


//             mAdapter.setNewData(alllist);
             if (list.size()>0){
                 mAdapter.addData(list);
             }

             setLoadMoreStatus(pageSize,listSize,true);
//             showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));

             srl.setRefreshing(false);




         }

         @Override
         public void onLoadMoreListfail(String message) {
//        Toast.makeText(this,"没有更多可显示问题",Toast.LENGTH_SHORT).show();
         }

         private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
        if (success){
            if (isListEmpty){
                srl.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText("暂无常见问题信息，点我刷新");

                }else {
                srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);

                }


            } else {
            srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText("加载常见问题信息失败");
            }


    }


    @Override
    protected void onResume() {
        super.onResume();

//        onRefresh();

    }


         @Override
         public void onClick(View view) {
             switch (view.getId()){
                 case   R.id.rl_qustype:
                     qcurrentPage=0;
                     checktype="true";
                     type=srcLoginDataDictionaryBeans.get(0).getCode();
                     qus_typename.setText("常见问题-"+srcLoginDataDictionaryBeans.get(0).getName());
                     qustypeload();
                     break;
                 case   R.id.rl_qustype1:
                     qcurrentPage=0;
                     checktype="true";
                     type=srcLoginDataDictionaryBeans.get(1).getCode();
                     qus_typename.setText("常见问题-"+srcLoginDataDictionaryBeans.get(1).getName());
                     qustypeload();

                     break;
                 case   R.id.rl_qustype2:
                     qcurrentPage=0;
                     checktype="true";
                     type=srcLoginDataDictionaryBeans.get(2).getCode();
                     qus_typename.setText("常见问题-"+srcLoginDataDictionaryBeans.get(2).getName());
                     qustypeload();

                     break;
                 case   R.id.rl_qustype3:
                     qcurrentPage=0;
                     checktype="true";
                     type=srcLoginDataDictionaryBeans.get(3).getCode();
                     qus_typename.setText("常见问题-"+srcLoginDataDictionaryBeans.get(3).getName());
                     qustypeload();

                     break;


             }
         }

         @Override
         public void onLoadMoreRequested() {


                 currentPage=currentPage+1;
                 Map<String, String> map = ParametersFactory.getGCAQuestionMap1(this,String.valueOf(currentPage),String.valueOf(pageSize),
                         "commonQuestion.commonQuestionList");

                 mPresenter.main(map,true);

         }
     }
