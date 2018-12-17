package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;

import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.ImageAndTextBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.GuideBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.datasource.DataSourceFactory;
import com.jqsoft.fingertip_health.di.contract.GuideActivityContract;
import com.jqsoft.fingertip_health.di.module.GuideActivityModule;
import com.jqsoft.fingertip_health.di.presenter.GuideActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;

    public class GuideActivity extends AbstractActivity implements GuideActivityContract.View{
    private LinearLayout saveobject_layout;
    private LinearLayout ll_back;
        private  int listSize;
        List<GuideBean> beans;
        String [] GuideTitleArray;

    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.online_consultation_title)
    TextView online_consultation_title;
   String  areacode =null;
        @Inject
        GuideActivityPresenter mPresenter;
        @BindView(R.id.lay_policy_load_failure)
        View failureView;
        TextView tvFailureView;
        @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initData() {
        areacode=(String) getDeliveredSerializableByKey(Constants.GUIDE_ITEM_ACTIVITY_KEY);

    }
        @Override
        protected void initInject() {
            DaggerApplication.get(this)
                    .getAppComponent()
                    .addGuideActivity(new GuideActivityModule(this))
                    .inject(this);
        }

        @Override
        protected void loadData() {
            onRefresh();

        }

        @Override
    protected void initView() {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setToolBar(toolbar, Constants.EMPTY_STRING);
        online_consultation_title.setText("办事指南");
//        addModuleListContent();
//        registerModuleClickEvent();
            tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
            tvFailureView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    super.onNoDoubleClick(v);
                    onRefresh();
                }
            });
    }



    private void addModuleListContent() {
//        ModuleListContentNew mlc = new ModuleListContentNew(getActivity());


        List<View> viewList = DataSourceFactory.getGuideOfficeModuleListView(this,GuideTitleArray);
        if (!ListUtils.isEmpty(viewList)) {
            for (int i = 0; i < viewList.size(); ++i) {
                llRoot.addView(viewList.get(i));
            }
        }
    }
    private void registerModuleClickEvent() {
        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.GUIDE_TYPE_DID_SELECT_MODULE, ImageAndTextBean.class)
                .subscribe(new Action1<ImageAndTextBean>() {
                    @Override
                    public void call(ImageAndTextBean imageAndTextBean) {

                        gotoModule(imageAndTextBean.getTitle());

//                        Util.showAlert(getActivity(), "提示", "您选择了功能" + imageAndTextBean.getId());0
                    }
                });


    }
    public void gotoModule(String modulename) {


        GuideBean bean= getbean(modulename);

        if (bean==null){

        }else {

            Bundle bundle = new Bundle();

            bundle.putSerializable(Constants.GUIDE_DETAIL_ACTIVITY_KEY, bean);
            Util.setAreaCode(areacode);
            Util.gotoActivityWithBundle(GuideActivity.this, PoliticsActivity.class, bundle);

        }

    }


        public void onRefresh() {

            Map<String, String> map = getRequestMap();
            mPresenter.main(map, false);
        }


        public Map<String, String> getRequestMap() {
            if (areacode==null){
                areacode=Identity.srcInfo.getAreaId();
            }

            String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
            Map<String, String> map = ParametersFactory.getGCAGuideListMap(this, name,areacode,
                    Constants.METHOD_NAME_RELIEF_INST);
            return map;
        }


        @Override
        public void onLoadListSuccess(GCAHttpResultBaseBean<List<GuideBean>> beanList) {
             listSize = getListSizeFromResult(beanList);
          //  listNew.clear();
            beans = getListFromResult(beanList);
           GuideTitleArray=new String[listSize];
            for (int i = 0; i < listSize; ++i) {
                GuideTitleArray[i]=beans.get(i).getName();
            }

            addModuleListContent();
            registerModuleClickEvent();





        }

        @Override
        public void onLoadMoreListSuccess(GCAHttpResultBaseBean<List<GuideBean>> bean) {

        }

        @Override
        public void onLoadListFailure(String message, boolean isLoadMore) {
              showRecyclerViewOrFailureView(false, true);

        }

        public List<GuideBean> getListFromResult(GCAHttpResultBaseBean<List<GuideBean>> beanList) {
            if (beanList != null) {
                List<GuideBean> list = beanList.getData();
                return list;
            } else {
                return null;
            }
        }

        public int getListSizeFromResult(GCAHttpResultBaseBean<List<GuideBean>> beanList) {
            if (beanList != null) {
                List<GuideBean> list = beanList.getData();
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
        public String[] getlistNameFromResult(List<GuideBean> beans){

            String[] name =new String[listSize];
            if (beans != null) {
                for (int i = 0; i < listSize; ++i) {
                    name[i]=beans.get(i).getName();

                }

            return name;
            } else {
                return null;
            }



        }


        public GuideBean getbean(String itemname) {

                for (int i = 0; i < listSize; ++i) {
                    if (beans.get(i).getName().equals(itemname)){

                        return beans.get(i);
                    }


                }
            return null;
        }
        private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
            if (success){
                if (isListEmpty){
                    llRoot.setVisibility(View.GONE);
                    failureView.setVisibility(View.VISIBLE);
                        tvFailureView.setText("暂无办事指南信息，点我刷新");

                } else {
                    llRoot.setVisibility(View.VISIBLE);
                    failureView.setVisibility(View.GONE);
                }
            } else {
                llRoot.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText("暂无办事指南信息,点我刷新");



            }
        }


        }

