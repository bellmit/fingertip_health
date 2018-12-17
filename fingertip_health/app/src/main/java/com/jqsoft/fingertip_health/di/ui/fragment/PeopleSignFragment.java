package com.jqsoft.fingertip_health.di.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.GalleryAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.bean.PeopleSignInfoBean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.PeopleSignInfoFragmentContract;
import com.jqsoft.fingertip_health.di.module.PeopleSignInfoFragmentModule;
import com.jqsoft.fingertip_health.di.presenter.PeopleSignInfoFragmentPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.DoctorServerDetails;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by YLL on 2017-6-30.
 */

public class PeopleSignFragment extends AbstractFragment implements
        PeopleSignInfoFragmentContract.View{

   // private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<String> mDatas =new ArrayList<>();


    @BindView(R.id.id_recyclerview_horizontal)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_sign_name)
    TextView tv_sign_name;

    @Inject
    PeopleSignInfoFragmentPresenter mPresenter;

    @BindView(R.id.tv_sign_year)
    TextView tv_sign_year;

    @BindView(R.id.tv_sign_status)
    TextView tv_sign_status;

    @BindView(R.id.tv_sign_paystatus)
    TextView tv_sign_paystatus;

    @BindView(R.id.tv_sign_receivablemoney)
    TextView tv_sign_receivablemoney;

    @BindView(R.id.tv_sign_paymoney)
    TextView tv_sign_paymoney;

    @BindView(R.id.tv_sign_derademoney)
    TextView tv_sign_derademoney;

    @BindView(R.id.tv_sign_compensationmoney)
    TextView tv_sign_compensationmoney;

    @BindView(R.id.tv_sign_selfmoney)
    TextView tv_sign_selfmoney;

    private Bundle data = new Bundle();
    private String sYear,sSignKey;
    private PeopleBaseInfoBean mpeopleSignInfoBean;
    private List<String> mExecuServerPackList = new ArrayList<>();
    private List<String> mExecuServerID = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_people_sign;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    //    data = getArguments();//获得从activity中传递过来的值
    //    sYear = data.getString("year");
    //    sSignKey = data.getString("signKey");

        initDatas();
     //   mRecyclerView = (RecyclerView) rootView.findViewById(R.id.id_recyclerview_horizontal);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置适配器




    }

    public void setPeopleSignbean(PeopleBaseInfoBean pp){
        mpeopleSignInfoBean=pp;
        if(mpeopleSignInfoBean!=null) {
            tv_sign_name.setText(mpeopleSignInfoBean.getUserName()+"的签约信息");
            tv_sign_year.setText(mpeopleSignInfoBean.getYear());

            String sSignStatus =mpeopleSignInfoBean.getStatusCode();
            if(sSignStatus.equals("1")){
                tv_sign_status.setText("起草中");
            }else if(sSignStatus.equals("2")){
                tv_sign_status.setText("已签约");
            }else if(sSignStatus.equals("3")){
                tv_sign_status.setText("审核通过");
            }else if(sSignStatus.equals("4")){
                tv_sign_status.setText("审核不通过");
            }else {
                tv_sign_status.setText("解约");
            }
            String sIsCharge =mpeopleSignInfoBean.getIsCharge();

            if(sIsCharge.equals("0")){
                tv_sign_paystatus.setText("未缴费");
            }else{
                tv_sign_paystatus.setText("已缴费");
            }

            tv_sign_receivablemoney.setText(mpeopleSignInfoBean.getPackSumFee()+"元");
            tv_sign_paymoney.setText(mpeopleSignInfoBean.getActualPackageSumFee()+"元");
            tv_sign_derademoney.setText(mpeopleSignInfoBean.getOtherReduceFee()+"元");
            tv_sign_compensationmoney.setText(mpeopleSignInfoBean.getNewRuralCMSFee()+"元");
            tv_sign_selfmoney.setText(mpeopleSignInfoBean.getShouldSelfFee()+"元");
        }

        String sServerPack = mpeopleSignInfoBean.getServerPackageName();
        String [] temp = null;
        temp = sServerPack.split(" ");
        String s1 =temp[0];
        int d=temp.length;
        mExecuServerPackList.clear();
        for(int i=0;i<temp.length;i++){
            mExecuServerPackList.add(temp[i]);
        }


        String sServerID = mpeopleSignInfoBean.getPackageID();
        String [] temp1 = null;
        temp1 = sServerID.split(" ");

        mExecuServerID.clear();
        for(int i=0;i<temp1.length;i++){
            mExecuServerID.add(temp1[i]);
        }


        mAdapter = new GalleryAdapter(getActivity(), mExecuServerPackList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickLitener(new GalleryAdapter.OnItemClickLitener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
               /* Toast.makeText(getActivity(), position+mExecuServerID.get(position), Toast.LENGTH_SHORT)
                        .show();*/
                Intent intent = new Intent(getActivity(), DoctorServerDetails.class);
                intent.putExtra(Constants.SEVER_KEY, mExecuServerID.get(position));
                startActivity(intent);

            }
        });
    }


    @Override
    protected void loadData() {
      /*  Map<String, String> map = ParametersFactory.getPeopleSignInfo(sYear,sSignKey);
        RequestBody body = Util.getBodyFromMap(map);

        mPresenter.main(body, false);*/
    }


    private void initDatas()
    {
        mDatas.add("高血压中级包");
        mDatas.add("高血压中级包");
        mDatas.add("高血压中级包");
        mDatas.add("高血压中级包");
        mDatas.add("高血压中级包");
        mDatas.add("高血压中级包");
        mDatas.add("高血压中级包");
        mDatas.add("高血压中级包");
    }


    @Override
    protected void initInject() {
        DaggerApplication.get(getActivity())
                .getAppComponent()
                .addPeopleSignInfoFragment(new PeopleSignInfoFragmentModule(this))
                .inject(this);

    }


    @Override
    public void onLoadListSuccess(HttpResultBaseBean<PeopleSignInfoBean> bean) {
        Util.hideGifProgressDialog(getActivity());
        Util.showToast(getActivity(), "获取成功");

    }

    @Override
    public void onLoadMoreListSuccess(HttpResultBaseBean<PeopleSignInfoBean> bean) {

    }

    @Override
    public void onLoadListFailure(String message, boolean isLoadMore) {

    }
}
