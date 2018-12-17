package com.jqsoft.fingertip_health.di.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.roundview.RoundTextView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.HelpListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.ReliefItemBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.ReliefItemActivityContract;
import com.jqsoft.fingertip_health.di.module.ReliefItemActivityModule;
import com.jqsoft.fingertip_health.di.presenter.ReliefItemActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.NotInputDialog;
import com.jqsoft.fingertip_health.util.Util;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 办事指南详情
 */

public class ReliefItemActivity extends AbstractActivity  implements    ReliefItemActivityContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_datetime)
    TextView tvDatetime;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.policy_title)
    TextView ReliefItem_title;

    @BindView(R.id.overDay)
    TextView overDay;
    @BindView(R.id.itemName)
    TextView itemName;
    @BindView(R.id.areaName)
    TextView areaName;
    @BindView(R.id.policyBasis)
    TextView policyBasis;
    @BindView(R.id.acceptCondition)
    TextView acceptCondition;
    @BindView(R.id.applyMaterial)
    TextView applyMaterial;

    private String  img_Collection="0";
    @BindView(R.id.rl_Collection)
    RoundTextView rl_Collection;
    JSONObject obj;
    @Inject
    ReliefItemActivityPresenter mPresenter;
    private ReliefItemBean reliefItemBean;
    String CollectionId;
    private  String id;
    String name;
    HelpListBean helpListBean;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;
    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addReliefItemActivity(new ReliefItemActivityModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_relief_detail_layout;
    }

    @Override
    protected void initData() {

            id=(String)getDeliveredSerializableByKey(Constants.RELIEF_ITEM_ACTIVITY_KEY);


    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        ReliefItem_title.setText("指南详情");
        ButterKnife.bind(this);
        if (("1").equals(Util.getFromCollection())){
            img_Collection="1";
            rl_Collection.setText("取消收藏");
        }else {
            rl_Collection.setText("收藏");
            img_Collection="0";
        }

        rl_Collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (img_Collection.equals("0")){
                    img_Collection="1";
                    rl_Collection.setText("收藏");
                    collect();

                }
                else{
                    rl_Collection.setText("取消收藏");
                    removeCollection();
                    img_Collection="0";

                }
            }
        });


;
    }




    @Override
    protected void loadData() {
        onRefresh();
    }




    private String getpolicyBasisString(){
        if (reliefItemBean==null){
            return Constants.EMPTY_STRING;
        } else {
            String result = Util.trimString(reliefItemBean.getPolicyBasis());
            return result;
        }
    }
    private String getoverDayString(){
        if (reliefItemBean==null){
            return Constants.EMPTY_STRING;
        } else {
            String result = Util.trimString(reliefItemBean.getOverDay());
            return result;
        }
    }
    private String getacceptConditionString(){
        if (reliefItemBean==null){
            return Constants.EMPTY_STRING;
        } else {
            String result = Util.trimString(reliefItemBean.getAcceptCondition());
            return result;
        }
    }

    private String getapplyMaterialString(){
        if (reliefItemBean==null){
            return Constants.EMPTY_STRING;
        } else {
            String result = Util.trimString(reliefItemBean.getApplyMaterial());
            return result;
        }
    }

    private String getareanameString(){
        if (reliefItemBean==null){
            return Constants.EMPTY_STRING;
        } else {
            String result = Util.trimString(reliefItemBean.getAreaName());
            return result;
        }
    }

    private String getTitleString(){

        if (reliefItemBean==null){
            return Constants.EMPTY_STRING;
        } else {
            String result = Util.trimString(reliefItemBean.getItemName());
            return result;
        }
    }
    private String getAuthorString(){
        String result = "发布人 :";
        if (reliefItemBean==null){

        } else {
            result+=Util.trimString(reliefItemBean.getEditor());
        }
        return result;
    }

    private String getDatetimeString(){
        String result = "发布日期:";
        String editData=reliefItemBean.getEditDate();
        if (editData==null || editData.equals("null")||TextUtils.isEmpty(editData) ){

        } else {
            if(editData.length()<10){

            }else {
                result+=Util.trimString(reliefItemBean.getEditDate().substring(0,10));
            }


//            result+=Util.trimString(deleteCharString(reliefItemBean.getEditDate(),'T'));

        }
        return result;
    }

    private String getContentString(){
        if (reliefItemBean==null){
            return Constants.EMPTY_STRING;
        } else {
            String result = Util.trimString(reliefItemBean.getApplyMaterial());
            return result;
        }
    }

    @Override
    public void onLoadListSuccessfromCollection(GCAHttpResultBaseBean<List<ReliefItemBean>> bean) {
        if (bean!=null){
            reliefItemBean = bean.getData().get(0);
            CollectionId= reliefItemBean.getCollectionId();
            if (("1").equals(Util.getFromCollection())){
                img_Collection="1";


            }else {
                img_Collection="0";


            }

            if (reliefItemBean.getAcceptCondition()==null){


                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);


                NotInputDialog inputDialog = new NotInputDialog(this).builder().setTitle("提示"+"\n\n"+"该救助指南项目尚未完善！" )
                        .setCancelable(false)
                        .setPositiveBtn("确定", new NotInputDialog.OnPositiveListener() {
                            @Override
                            public void onPositive(View view, String inputMsg) {
                                finish();
                            }
                        })
                        .setCanceledOnTouchOutside(false);


                inputDialog.show();


            }

            tvTitle.setText(getTitleString());
            tvAuthor.setText(getAuthorString());
            tvDatetime.setText(getDatetimeString());
            overDay.setText(getoverDayString());
            itemName.setText(getTitleString());
            areaName.setText(getareanameString());
            policyBasis.setText(getpolicyBasisString());
            acceptCondition.setText(getacceptConditionString());
            applyMaterial.setText(getapplyMaterialString());
            String contentString = getContentString();

        }


    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<ReliefItemBean> bean) {
        if (bean!=null){
            reliefItemBean = bean.getData();
            CollectionId= reliefItemBean.getCollectionId();




                if (CollectionId==null){
                //0 未收藏  1收藏
                img_Collection="0";
                rl_Collection.setText("收藏");
            }else {
                img_Collection="1";
                rl_Collection.setText("取消收藏");

            }

            showdiaog();

            tvTitle.setText(getTitleString());
            tvAuthor.setText(getAuthorString());
            tvDatetime.setText(getDatetimeString());
            overDay.setText(getoverDayString());
            itemName.setText(getTitleString());
            areaName.setText(getareanameString());
            policyBasis.setText(getpolicyBasisString());
            acceptCondition.setText(getacceptConditionString());
            applyMaterial.setText(getapplyMaterialString());
            String contentString = getContentString();

        }
    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<ReliefItemBean> bean) {
        reliefItemBean = bean.getData();

    }

    @Override
    public void oncollectionSuccess(GCAHttpResultBaseBean<ReliefItemBean> bean) {
        CollectionId=bean.getData().getCollectioId();
       Toast.makeText(this,"收藏成功",Toast.LENGTH_SHORT).show();
        rl_Collection.setText("取消收藏");
    }

    @Override
    public void onremovecollectionSuccess(GCAHttpResultBaseBean<ReliefItemBean> bean) {
        Toast.makeText(this,"取消收藏成功",Toast.LENGTH_SHORT).show();
        rl_Collection.setText("收藏");
        setResult(RESULT_SUCCESS);
//        onRefresh();

    }

    @Override
    public void onremovecollectionFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void oncollectionFailure(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadListFailure(String message) {


        NotInputDialog inputDialog = new NotInputDialog(this).builder().setTitle("提示"+"\n\n"+"该救助指南项目尚未完善！" )
                .setCancelable(false)
                .setPositiveBtn("确定", new NotInputDialog.OnPositiveListener() {
                    @Override
                    public void onPositive(View view, String inputMsg) {
                        finish();
                    }
                })
                .setCanceledOnTouchOutside(false);


        inputDialog.show();
//        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

//        showdiaog();
    }
    public void removeCollection(){

            Map<String, String> map = ParametersFactory.getGCAReliefremovecollectionMap(this,
                    CollectionId,
                    "collectionData.removeCollection");
            mPresenter.removecollect(map);


    }
    public void collect(){
        if (("1").equals(Util.getFromCollection())){
            Map<String, String> map = ParametersFactory.getGCAReliefcollectionMap(this,name,
                    id,
                    "itemHelpData.collectItemConvenience");
            mPresenter.collect(map);
        }else{
            Map<String, String> map = ParametersFactory.getGCAReliefcollectionMap(this,name,
                    reliefItemBean.getCollectionUrl(),
                    "itemHelpData.collectItemConvenience");
            mPresenter.collect(map);
        }



    }

    public void onRefresh() {
        //判断是否从 我的收藏 进入  1 是 2 不是
        if (("1").equals(Util.getFromCollection())){

            Map<String, String> map = ParametersFactory.getGCAReliefInstListMapFormCollection(this,name,
                    Util.getAreaCode(),
                    id,
                    "collectionData.queryMyCollection");
            mPresenter.Collectionmain(map);
        }else {
            Map<String, String> map = ParametersFactory.getGCAReliefInstListMap(this,name,
                    Util.getAreaCode(),
                    id,
                    "itemHelpData.queryReliefItemConvenience");
            mPresenter.main(map);
        }



    }

    public String deleteCharString(String sourceString, char chElemData) {
        String deleteString = "";
        for (int i = 0; i < sourceString.length(); i++) {
            if (sourceString.charAt(i) != chElemData) {
                deleteString += sourceString.charAt(i);
            }else {

                deleteString +=" ";
            }
        }
        return deleteString;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {



        if (keyCode == KeyEvent.KEYCODE_BACK) {


                setResult(RESULT_FAILED);

                finish();


        }
        return super.onKeyDown(keyCode, event);

    }
    private  void  showdiaog(){
        if (reliefItemBean.getAcceptCondition()==null){


            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);


            NotInputDialog inputDialog = new NotInputDialog(this).builder().setTitle("提示"+"\n\n"+"该救助指南项目尚未完善！" )
                    .setCancelable(false)
                    .setPositiveBtn("确定", new NotInputDialog.OnPositiveListener() {
                        @Override
                        public void onPositive(View view, String inputMsg) {
                            finish();
                        }
                    })
                    .setCanceledOnTouchOutside(false);


            inputDialog.show();


        }
    }

}
