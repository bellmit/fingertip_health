package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.LeftListAdapter;
import com.jqsoft.fingertip_health.adapter.MainSectionedAdapter;
import com.jqsoft.fingertip_health.adapter.ReceptionAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.ReceptionBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.di.contract.ReceptionActivityContract;
import com.jqsoft.fingertip_health.di.presenter.ReceptionActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.popup_window.CityChoosePopupWindow;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.view.PinnedHeaderListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Observer;

//受理中心

public class ReceptionNewActivity extends AbstractActivity implements
        ReceptionActivityContract.View, SwipeRefreshLayout.OnRefreshListener
      {

          @BindView(R.id.left_listview)
          ListView leftListview;

          @BindView(R.id.pinnedListView)
          PinnedHeaderListView pinnedListView;


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;

//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;

    @BindView(R.id.lay_policy_load_failure)
    View failureView;
    private  String code,titlename;
    TextView tvFailureView;

    @Inject
    ReceptionActivityPresenter mPresenter;
          SRCLoginAreaBean citylist;
          @BindView(R.id.tv_city)
          TextView tv_city;
    private boolean isRefresh = false;

    private int type;

    private ReceptionAdapter mAdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;


    private String keywordString;

    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;



/*
          ListView leftListview;
          PinnedHeaderListView pinnedListView;*/
          private boolean isScroll = true;
          private LeftListAdapter adapter;
          private String[] leftStr;
          //  private boolean[] flagArray = {true, false, false, false, false, false, false, false, false};
          private String[][] rightStr;
          private String[][] rightLevel;
          private String[][] rightimg;
          private   MainSectionedAdapter sectionedAdapter;
          private boolean[] flagArray;


    @Override
    protected void loadData() {

    }




    @Override
    protected int getLayoutId() {
        return R.layout.activity_reception_new;
    }

    @Override
    protected void initData() {



    }


















    @Override
    protected void initInject() {
      /*  DaggerApplication.get(this)
                .getAppComponent()
                .addReceptionActivity(new ReceptionActivityModule(this))
                .inject(this);*/
    }


    @Override
    protected void initView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);

        onRefresh();
        List<SRCLoginAreaBean>   allcityList0 = DataSupport.where(" areaLevel=?  and state=?","area_2","0" ).find(SRCLoginAreaBean.class);
        tv_city.setText("合肥");
        final CityChoosePopupWindow  pop= new CityChoosePopupWindow(this,allcityList0,300,900,tv_city);
     //   pop.show();
//        gdws_sys_area citylist=pop.getCitylist();

        pop.setDateRangeItemClickListener(new CityChoosePopupWindow.DateRangeItemClickListener() {
            @Override
            public void dateRangeItemDidClick(SRCLoginAreaBean citylist1) {
              /*  selectedDateRange = iDateRange;
                setDateRangeString();
                onRefresh();*/
                citylist=citylist1;
                tv_city.setText(citylist.getAreaName());
                initarea();


            }
        });

        RxView.clicks(tv_city)
                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {
                        pop.show();
                    }
                });


        initarea();


/*

        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
//        tvFailureView.setText(failureString);
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                onRefresh();
            }
        });

        srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
        srl.setOnRefreshListener(this);


        final BaseQuickAdapter<ReceptionBean, BaseViewHolder> mAdapter = new ReceptionAdapter(new ArrayList<ReceptionBean>(), TYPE_MULTIPLE_LINE);
        this.mAdapter = (ReceptionAdapter) mAdapter;
//        easyLoadMoreView = new EasyLoadMoreView();
//        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.setAutoLoadMoreSize(Constants.ADAPTER_AUTO_LOAD_MORE_SIZE);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mAdapter.setOnLoadMoreListener(this, recyclerView);
//        mAdapter.disableLoadMoreIfNotFullPage();
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
        Util.addDividerToRecyclerView(this, recyclerView, true);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
//               Util.showToast(PoliticsActivity.this, position+" is clicked");
                ReceptionBean pb = mAdapter.getItem(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.RECEPTION_ITEM_ACTIVITY_KEY,   pb);

                Util.gotoActivityWithBundle(ReceptionNewActivity.this, ReceptionListActivity.class, bundle);
            }
        });*/

    }

    @Override
    public void onRefresh() {
  /*      currentPage = Constants.DEFAULT_INITIAL_PAGE;
        isRefresh = true;
//        mAdapter.setEnableLoadMore(false);

//        TreatmentListRequestBean bean = getRequestBean();
//        mPresenter.main(bean, false);

        Map<String, String> map = getRequestMap();
        mPresenter.main(map, false);*/
    }


    public Map<String, String> getRequestMap() {
        Identity.getCurrentAreaCode();

//        String year = Util.getCurrentYearString();
//        Map<String, String> mif (guideBean==null){
        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        Map<String, String> map = ParametersFactory.getGCAPolityListMap(this,name,
                Identity.getCurrentAreaCode(),
                "receptionData.queryAreaListByPareaCode");

        return map;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
//    @Override
//    public void onLoadMoreRequested() {
//        ++currentPage;
//        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, true);
////        LogUtil.i("PolicyActivity onLoadMoreRequested:" + "currentPage/pageSize:" + currentPage + "/" + pageSize);
//        srl.setEnabled(false);
//    }

    public List<ReceptionBean> getListFromResult(GCAHttpResultBaseBean<List<ReceptionBean>> beanList) {
        if (beanList != null) {
            List<ReceptionBean> list = beanList.getData();
            return list;
        } else {
            return null;
        }
    }
    public int getListSizeFromResult(GCAHttpResultBaseBean<List<ReceptionBean>> beanList) {
        if (beanList != null) {
            List<ReceptionBean> list = beanList.getData();
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

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<List<ReceptionBean>> beanList) {


        int listSize = getListSizeFromResult(beanList);
        List<ReceptionBean> list = getListFromResult(beanList);

//        mAdapter.setNewData(list);

        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));

//        list_size.setText("共有"+listSize+"个服务项");
        srl.setRefreshing(false);
        setLoadMoreStatus(pageSize, listSize, true);
//        mAdapter.setEnableLoadMore(true);
        isRefresh = false;

    }


    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {
        showRecyclerViewOrFailureView(false, true);
        if (isLoadMore){
            if (currentPage>Constants.DEFAULT_INITIAL_PAGE) {
                --currentPage;
            }

        } else {

        }
        srl.setRefreshing(false);
        setLoadMoreStatus(0, 0, false);
////        Util.showToast(this, message);
        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
    }

    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
        if (success){
            if (isListEmpty){
                srl.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
            } else {
                srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
            }
        } else {
            srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());

        }
    }

    private String getListEmptyHint(){
        return getResources().getString(R.string.hint_no_policy_info_please_click_to_reload);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_policy_info_error_please_click_to_reload);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        onRefresh();

    }




    private void initarea(){

        List<SRCLoginAreaBean>   allcityList4=new ArrayList<>();
        List<SRCLoginAreaBean>   allcityList5=new ArrayList<>();
        List<SRCLoginAreaBean>   allcityList ;
        List<SRCLoginAreaBean>   allcityList2 ;
        List<SRCLoginAreaBean>   allcityList3;




        if(citylist==null){


            allcityList = DataSupport.where(" areaLevel=?  and areaName=?  and state=?","area_2","合肥市","0").find(SRCLoginAreaBean.class);
            allcityList2 = DataSupport.where(" areaLevel=? and state=?","area_3","0" ).find(SRCLoginAreaBean.class);
            allcityList3 = DataSupport.where(" areaLevel=? and state=?","area_4","0" ).find(SRCLoginAreaBean.class);

        }else {


            allcityList = DataSupport.where(" areaLevel=?  and areaName=?  and state=?","area_2", citylist.getAreaName(),"0").find(SRCLoginAreaBean.class);
            allcityList2 = DataSupport.where(" areaLevel=? and state=?","area_3","0" ).find(SRCLoginAreaBean.class);
            allcityList3 = DataSupport.where(" areaLevel=? and state=?","area_4","0" ).find(SRCLoginAreaBean.class);

        }






        allcityList4.clear();
        for(int i=0;i<allcityList2.size();i++){
            if(allcityList2.get(i).getAreaPid().equals(allcityList.get(0).getAreaCode())){
                allcityList4.add(allcityList2.get(i));
            }
        }


        leftStr = new String[allcityList4.size()];
        flagArray = new boolean[allcityList4.size()];
        for(int i=0;i<allcityList4.size();i++){
            leftStr[i]=allcityList4.get(i).getAreaName();
            if(i==0){
                flagArray[i]=true;
            }else{
                flagArray[i]=false;
            }
        }

        rightStr = new String[allcityList4.size()][];
        rightLevel = new String[allcityList4.size()][];
        rightimg = new String[allcityList4.size()][];

        allcityList5.clear();

        for (int i = 0;i < allcityList4.size();i++){
            //   IntelligencePeopleBean.Room room = intelligencePeopleBean.Data.get(i);

            for(int j=0;j<allcityList3.size();j++){
                if(allcityList4.get(i).getAreaCode().equals(allcityList3.get(j).getAreaPid())){
                    allcityList5.add(allcityList3.get(j));
                }
            }
        }

        for (int i = 0;i < allcityList4.size();i++){
            List<SRCLoginAreaBean>   allcityList6=new ArrayList<>();
            for (int j=0;j<allcityList5.size();j++){


                if(allcityList4.get(i).getAreaCode().equals(allcityList5.get(j).getAreaPid())){
                    allcityList6.add(allcityList5.get(j));
                }


            }

            rightStr[i] = new String[allcityList6.size()];
            rightLevel[i] = new String[allcityList6.size()];
            rightimg[i] = new String[allcityList6.size()];

            for(int k=0;k<allcityList6.size();k++){
                SRCLoginAreaBean srcLoginAreaBean = allcityList6.get(k);
                rightStr[i][k] = srcLoginAreaBean.getAreaName();
                rightLevel[i][k] = srcLoginAreaBean.getAreaCode();
                rightimg[i][k] = srcLoginAreaBean.getAreaPid();
            }


        }


        sectionedAdapter = new MainSectionedAdapter(this, leftStr, rightStr,rightLevel,rightimg);
        pinnedListView.setAdapter(sectionedAdapter);
        adapter = new LeftListAdapter(this, leftStr, flagArray);
        leftListview.setAdapter(adapter);
        leftListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                isScroll = false;

                for (int i = 0; i < leftStr.length; i++) {
                    if (i == position) {
                        flagArray[i] = true;
                    } else {
                        flagArray[i] = false;
                    }
                }
                adapter.notifyDataSetChanged();
                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += sectionedAdapter.getCountForSection(i) + 1;
                }
                pinnedListView.setSelection(rightSection);


            }



        });

        pinnedListView.setOnItemClickListener(new PinnedHeaderListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int section, int position, long id) {
//                Toast.makeText(getApplicationContext(),"点击了"+rightStr[section][position], Toast.LENGTH_SHORT).show();

                List<SRCLoginAreaBean>  checkcity = DataSupport.where(" areaName=? and state=?", rightStr[section][position] ,"0").find(SRCLoginAreaBean.class);




                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.RECEPTION_ITEM_ACTIVITY_KEY,     checkcity.get(0));

                Util.gotoActivityWithBundle(ReceptionNewActivity.this, ReceptionListActivity.class, bundle);

                if(rightStr[section][position].equals("无老人")){
                    //   Toast.makeText(context,"没有老人", Toast.LENGTH_SHORT).show();
                }else{

/*
                    peopleRoom = intelligencePeopleBean.Data.get(section).OldManList.get(position);
                    //  peopleRoom =  intelligencePeopleBean.Data.get(position).OldManList;

                    //    app.setsElderImg(peopleRoom.ElderPhotoPath);
                    Intent intent = new Intent(IntelligentSignActivity.this, DetailIntelligentSign.class);
                    intent.putExtra("wardRoundName", (Serializable) peopleRoom);



                    startActivity(intent);*/
                }



            }

            @Override
            public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {

            }
        });



        pinnedListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView arg0, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (pinnedListView.getLastVisiblePosition() == (pinnedListView.getCount() - 1)) {
                            leftListview.setSelection(ListView.FOCUS_DOWN);
                        }

                        // 判断滚动到顶部
                        if (pinnedListView.getFirstVisiblePosition() == 0) {
                            leftListview.setSelection(0);
                        }

                        break;
                }
            }

            int y = 0;
            int x = 0;
            int z = 0;

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScroll) {
                    for (int i = 0; i < rightStr.length; i++) {
                        if (i == sectionedAdapter.getSectionForPosition(pinnedListView.getFirstVisiblePosition())) {
                            flagArray[i] = true;
                            x = i;
                        } else {
                            flagArray[i] = false;
                        }
                    }
                    if (x != y) {
                        adapter.notifyDataSetChanged();
                        y = x;
                        if (y == leftListview.getLastVisiblePosition()) {
//                            z = z + 3;
                            leftListview.setSelection(z);
                        }
                        if (x == leftListview.getFirstVisiblePosition()) {
//                            z = z - 1;
                            leftListview.setSelection(z);
                        }
                        if (firstVisibleItem + visibleItemCount == totalItemCount - 1) {
                            leftListview.setSelection(ListView.FOCUS_DOWN);
                        }
                    }
                } else {
                    isScroll = true;
                }
            }
        });




    }



}
