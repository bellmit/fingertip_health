package com.jqsoft.fingertip_health.di.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.ReceptionAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.ReceptionBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.di.contract.ReceptionActivityContract;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import com.jqsoft.fingertip_health.view.IgGuideSelectAddressPop;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.adapter.PolityAdapter.TYPE_MULTIPLE_LINE;
import static com.jqsoft.fingertip_health.util.Util.showGifProgressDialog;

//办事指南

public class ListGuideActivity extends AbstractActivity implements
        ReceptionActivityContract.View, SwipeRefreshLayout.OnRefreshListener
      {

@BindView(R.id.online_consultation_title)
TextView title;


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
    @BindView(R.id.framelayout)
          View framelayout;
    @BindView(R.id.container)
    View container;
//    @Inject
//    ReceptionActivityPresenter mPresenter;

    private boolean isRefresh = false;

    private int type;

    private ReceptionAdapter mAdapter;
//    private ArrayList<TreatmentListBean.TreatmentBean> treatmentList;
//    private EasyLoadMoreView easyLoadMoreView;


    private String keywordString;

    private int currentPage = Constants.DEFAULT_INITIAL_PAGE;
    private int pageSize = Constants.DEFAULT_PAGE_SIZE;


          private String provinceCode ;
          private String cityCode;
          private String areaCode;
          private String Sform;
private int sAreaLeaveFlag= 0;

    @Override
    protected void loadData() {
        onRefresh();


    }




    @Override
    protected int getLayoutId() {
        return R.layout.activity_listguide;
    }

    @Override
    protected void initData() {

       Sform=(String) getDeliveredSerializableByKey(Constants.FROM_WHERE_ACTIVITY_KEY);
       if (Sform==null){
           Sform="BSZN";
           title.setText("办事指南");
       }else if ("BSZN".equals(Sform)){
           title.setText("办事指南");
       }else if ("ZNYD".equals(Sform)){
           title.setText("智能引导");

       }


    }
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(this)
//                .getAppComponent()
//                .addReceptionActivity(new ReceptionActivityModule(this))
//                .inject(this);
//    }

    @Override
    protected void initView() {
        showGifProgressDialog(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);

       // List<gdws_sys_area> Province = DataSupport.where(" areaLevel=? and areaPid=? ","area_3",Identity.srcInfo.getAreaId()).find(gdws_sys_area.class);


        String sAreaLeavel=Util.choicArea(this);

        if(IdentityManager.getAreaLevel(this).equals("area_1")  ){
            /**---------------------------------------三级联动（省市）---------------------------**/
//            List<gdws_sys_area>   list1 = DataSupport.where(" areaCode=? ",Identity.srcInfo.getAreaId() ).find(gdws_sys_area.class);
            if (Identity.srcInfo.getAreaId().equals("340000")){
                     //为省
                framelayout.setVisibility(View.GONE);
              container.setVisibility(View.VISIBLE);
                if ("ZNYD".equals(Sform)){

                    IgGuideSelectAddressPop pop1  = new IgGuideSelectAddressPop();
                    pop1.setAddress(provinceCode,cityCode,areaCode);

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction transaction = fragmentManager. beginTransaction();
                    transaction.replace(R.id.container, pop1);
                    transaction.commit();
                    Util.hideGifProgressDialog(this);


                }else {


//                    GuideSelectAddressPop pop1  = new GuideSelectAddressPop();
//                    pop1.setAddress(provinceCode,cityCode,areaCode);
//
//                    FragmentManager fragmentManager = getFragmentManager();
//                    FragmentTransaction transaction = fragmentManager. beginTransaction();
//                    transaction.replace(R.id.container, pop1);
//                    transaction.commit();
//                    Util.hideGifProgressDialog(this);
                }




            }else {



                //shi

//
//
//                container.setVisibility(View.GONE);
//                framelayout.setVisibility(View.VISIBLE);
                onRefresh();
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


                final BaseQuickAdapter<SRCLoginAreaBean, BaseViewHolder> mAdapter = new ReceptionAdapter(new ArrayList<SRCLoginAreaBean>(), TYPE_MULTIPLE_LINE);
                this.mAdapter = (ReceptionAdapter) mAdapter;
                List<SRCLoginAreaBean>  list = null;
                list = DataSupport.where(" areaPid=? and state=?",Identity.srcInfo.getAreaId(),"0" ).find(SRCLoginAreaBean.class);

                mAdapter.addData(list);
                mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

                recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
                Util.addDividerToRecyclerView(this, recyclerView, true);
                recyclerView.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
                    @Override
                    public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        super.onNoDoubleItemClick(adapter, view, position);
//               Util.showToast(PoliticsActivity.this, position+" is clicked");
                        SRCLoginAreaBean pb = mAdapter.getItem(position);

                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constants.GUIDE_ITEM_ACTIVITY_KEY,   pb.getAreaCode());
                        if (Sform.equals("ZNYD")){
//                            Util.setAreaCode( pb.getAreaCode());
                            Util.gotoActivityWithBundle(ListGuideActivity.this, IgGuideActivity.class, bundle);

                        }else {

                            Util.gotoActivityWithBundle(ListGuideActivity.this, GuideActivity.class, bundle);


                        }
                    }
                });




            }


            Util.hideGifProgressDialog(this);





        }







    }

    @Override
    public void onRefresh() {
        currentPage = Constants.DEFAULT_INITIAL_PAGE;
        isRefresh = true;


        Map<String, String> map = getRequestMap();
//        mPresenter.main(map, false);
    }


    public Map<String, String> getRequestMap() {
        Identity.getCurrentAreaCode();

        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        Map<String, String> map = ParametersFactory.getGCAPolityListMap(this,name,
                Identity.srcInfo.getAreaId(),
                "receptionData.queryAreaListByPareaCode");

        return map;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

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


    }
          public interface SelectAddresFinish{
              void finish(String provinceCode, String cityCode, String areaCode);
          }

}
