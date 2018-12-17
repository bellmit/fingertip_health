package com.jqsoft.fingertip_health.di.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
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
import com.jqsoft.fingertip_health.di.module.ReceptionActivityModule;
import com.jqsoft.fingertip_health.di.presenter.ReceptionActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.view.SelectAddressPop;
import com.jqsoft.fingertip_health.view.SelectAddressPopNew;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.adapter.PolityAdapter.TYPE_MULTIPLE_LINE;

//受理中心

public class ReceptionActivity extends AbstractActivity implements
        ReceptionActivityContract.View, SwipeRefreshLayout.OnRefreshListener
      {


@BindView(R.id.online_consultation_title)
TextView online_consultation_title;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.lay_content)
    SwipeRefreshLayout srl;
    @BindView(R.id.im_guide)
          ImageView im_guide;
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
    @Inject
    ReceptionActivityPresenter mPresenter;

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

private int sAreaLeaveFlag= 0;

    @Override
    protected void loadData() {

        String sAreaLeavel=Util.choicArea(this);
         if (IdentityManager.getAreaLevel(this).equals("area_3") ) {
            finish();
        }  else if (IdentityManager.getAreaLevel(this).equals("area_4") ) {
            finish();
        }  else if (IdentityManager.getAreaLevel(this).equals("area_5") ) {
            finish();
        }
        else {
            onRefresh();
        }



    }




    @Override
    protected int getLayoutId() {
        return R.layout.activity_reception;
    }

    @Override
    protected void initData() {


    }
    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addReceptionActivity(new ReceptionActivityModule(this))
                .inject(this);
    }

    @Override
    protected void initView() {
        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        if ("JgTypSlzx".equals(Util.getReception())){
            online_consultation_title.setText("受理中心");
            im_guide.setBackground(getResources().getDrawable(R.mipmap.ic_reception));
        }else {
            online_consultation_title.setText("救助站");
            im_guide.setBackground(getResources().getDrawable(R.mipmap.ic_jzz));
        }


       // List<gdws_sys_area> Province = DataSupport.where(" areaLevel=? and areaPid=? ","area_3",Identity.srcInfo.getAreaId()).find(gdws_sys_area.class);


        String sAreaLeavel=Util.choicArea(this);

        if(IdentityManager.getAreaLevel(this).equals("area_1")|| (IdentityManager.getAreaLevel(this).equals("area_2"))){
            /**---------------------------------------三级联动（省市）---------------------------**/
//            List<gdws_sys_area>   list = DataSupport.where(" areaCode=? ",Identity.srcInfo.getAreaId() ).find(gdws_sys_area.class);
            if (!Identity.srcInfo.getAreaId().equals("340000")){

//
                SelectAddressPopNew pop1  = new SelectAddressPopNew();
       //         SelectTwoAddressPop pop1  = new SelectTwoAddressPop();
                pop1.setSelectAddresFinish(new SelectAddresFinish(){

                    @Override
                    public void finish(String pCode, String cCode, String aCode) {
                        provinceCode = pCode;
                        cityCode = cCode;
                        areaCode = aCode;

                    }
                });
                pop1.setAddress(provinceCode,cityCode,areaCode);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager. beginTransaction();
                transaction.replace(R.id.container, pop1);
                transaction.commit();
//                Util.hideGifProgressDialog(this);

            }else {

//                SelectAddressPop pop  = new SelectAddressPop();
//                pop.setSelectAddresFinish(new SelectAddresFinish(){
//
//                    @Override
//                    public void finish(String pCode, String cCode, String aCode) {
//                        provinceCode = pCode;
//                        cityCode = cCode;
//                        areaCode = aCode;
//
//                    }
//                });
//                pop.setAddress(provinceCode,cityCode,areaCode);
//
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction transaction = fragmentManager. beginTransaction();
//                transaction.replace(R.id.container, pop);
//                transaction.commit();
//                Util.hideGifProgressDialog(this);
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

            srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
            srl.setOnRefreshListener(this);


            final BaseQuickAdapter<SRCLoginAreaBean, BaseViewHolder> mAdapter = new ReceptionAdapter(new ArrayList<SRCLoginAreaBean>(), TYPE_MULTIPLE_LINE);
            this.mAdapter = (ReceptionAdapter) mAdapter;

            mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

            recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
            Util.addDividerToRecyclerView(this, recyclerView, true);
            recyclerView.setAdapter(mAdapter);

//            Util.hideGifProgressDialog(this);

        }
        else if (IdentityManager.getAreaLevel(this).equals("area_3") ) {
                finish();
        }  else if (IdentityManager.getAreaLevel(this).equals("area_4") ) {
            finish();
        }  else if (IdentityManager.getAreaLevel(this).equals("area_5") ) {
            finish();
        }
        else {

            container.setVisibility(View.GONE);
            framelayout.setVisibility(View.VISIBLE);
            tvFailureView.setVisibility(View.GONE);
//            onRefresh();
            tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
//        tvFailureView.setText(failureString);
//            tvFailureView.setOnClickListener(new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View v) {
//                    super.onNoDoubleClick(v);
//                    onRefresh();
//                }
//            });

            srl.setColorSchemeColors(getResources().getColor(R.color.colorTheme));
            srl.setOnRefreshListener(this);


            final BaseQuickAdapter<SRCLoginAreaBean, BaseViewHolder> mAdapter = new ReceptionAdapter(new ArrayList<SRCLoginAreaBean>(), TYPE_MULTIPLE_LINE);
            this.mAdapter = (ReceptionAdapter) mAdapter;
            List<SRCLoginAreaBean>  list = null;
            if (sAreaLeavel.equals("area_2")){
                list = DataSupport.where(" areaPid=? and state=?",Identity.srcInfo.getAreaId(),"0" ).find(SRCLoginAreaBean.class);

            }else {
                list = DataSupport.where(" areaCode=? and state=?",Identity.srcInfo.getAreaId(),"0" ).find(SRCLoginAreaBean.class);

            }
            mAdapter.setNewData(list);
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
                    bundle.putSerializable(Constants.RECEPTION_ITEM_ACTIVITY_KEY,   pb);


                    Util.gotoActivityWithBundle(ReceptionActivity.this, ReceptionListActivity.class, bundle);
                }
            });
        }

//        Util.hideGifProgressDialog(this);






    }

    @Override
    public void onRefresh() {
        currentPage = Constants.DEFAULT_INITIAL_PAGE;
        isRefresh = true;


        Map<String, String> map = getRequestMap();
        mPresenter.main(map, false);
    }


    public Map<String, String> getRequestMap() {
        Identity.getCurrentAreaCode();

//        String year = Util.getCurrentYearString();
//        Map<String, String> mif (guideBean==null){
        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
//        Map<String, String> map = ParametersFactory.getGCARection(this,name,
//                Identity.srcInfo.getAreaId(),
//                "receptionData.queryAreaListByPareaCode");
        Map<String, String> map = ParametersFactory.getGCARection(this,name,
                areaCode,
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


//        int listSize = getListSizeFromResult(beanList);
//        List<ReceptionBean> list = getListFromResult(beanList);
//
//        mAdapter.setNewData(list);
//
//        showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//
////        list_size.setText("共有"+listSize+"个服务项");
//        srl.setRefreshing(false);
//        setLoadMoreStatus(pageSize, listSize, true);
////        mAdapter.setEnableLoadMore(true);
//        isRefresh = false;

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
//        Util.showToast(this, Constants.HINT_LOADING_DATA_FAILURE);
    }

    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
        if (success){
            if (isListEmpty){
                srl.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                if (Util.getReception().equals("JgTypSlzx")){
                    tvFailureView.setText(getListEmptyHint());
                }else {

                    tvFailureView.setText("暂无救助站信息，点我刷新");
            }


            } else {
                srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
            }
        } else {
            srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);

            if (Util.getReception().equals("JgTypSlzx")){
                tvFailureView.setText(getFailureHint());
            }else {

                tvFailureView.setText("暂无救助站信息，点我刷新");
            }


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

//        SelectAddressPop pop = new SelectAddressPop();
//        pop.setSelectAddresFinish(new SelectAddresFinish(){
//
//            @Override
//            public void finish(String pCode, String cCode, String aCode) {
//                //选中地址完成
//                provinceCode = pCode;
//                cityCode = cCode;
//                areaCode = aCode;
//
//            }
//        });
//        pop.setAddress(provinceCode,cityCode,areaCode);
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction transaction = fragmentManager. beginTransaction();
//        transaction.replace(R.id.container, pop);
//        transaction.commit();


        super.onResume();

//        onRefresh();

    }
          public interface SelectAddresFinish{
              void finish(String provinceCode, String cityCode, String areaCode);
          }

}
