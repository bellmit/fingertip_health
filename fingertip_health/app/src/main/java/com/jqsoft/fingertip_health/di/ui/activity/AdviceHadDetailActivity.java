package com.jqsoft.fingertip_health.di.ui.activity;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.AdviceDetailAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.AdviceDetailBean;
import com.jqsoft.fingertip_health.bean.ConsultChildrenBean;
import com.jqsoft.fingertip_health.bean.OrganizationBean;
import com.jqsoft.fingertip_health.bean.RepliesBean;
import com.jqsoft.fingertip_health.bean.SaveWanttoAdvicebean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.HelpListBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.RepliesAndConsult;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.di.contract.AdviceDetailActivityContract;
import com.jqsoft.fingertip_health.di.module.AdviceDetailActivityModule;
import com.jqsoft.fingertip_health.di.presenter.AdviceDetailActivityPresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.util.NotInputDialog;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.jqsoft.fingertip_health.adapter.AdviceDetailAdapter.TYPE_MULTIPLE_LINE;

/**
 *
 * 咨询建议详情
 */

public class AdviceHadDetailActivity extends AbstractActivity  implements    AdviceDetailActivityContract.View{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.policy_title)
    TextView ReliefItem_title;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_isturn)
    TextView tv_isturn;
    @BindView(R.id.tv_ctitle)
    TextView tv_ctitle;
    @BindView(R.id.RReset)
    RelativeLayout RReset;
    //    @BindView(R.id.tv_datetime)
//    TextView tv_time;
//    @BindView(R.id.replyUnit)
//    TextView replyUnit;
//    @BindView(R.id.replyTime)
//    TextView replyTime;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.consultTime)
    TextView consultTime;
    @BindView(R.id.tv_reply)
    TextView tv_reply;
    @BindView(R.id.ed_reply)
    EditText ed_reply;
    @BindView(R.id.ll_line1)
    LinearLayout ll_line1;
    @BindView(R.id.ll_post)
    LinearLayout ll_post;
    //    @BindView(R.id.ll_reply)
//    LinearLayout ll_reply;
//    @BindView(R.id.title_reply)
//    LinearLayout title_reply;
    @BindView(R.id.tv_change)
    TextView tv_change;
    //    @BindView(R.id.ll_remove)
//    LinearLayout ll_remove;
    @BindView(R.id.tv_neirongcontent)
    TextView tv_neirongcontent;
    @BindView(R.id.Rpost)
    RelativeLayout Rpost;
    //    @BindView(R.id.replycontent)
//    TextView replycontent;
    @Inject
    AdviceDetailActivityPresenter mPresenter;
    private AdviceDetailAdapter mAdapter;
    private  String isreply;
    private  String conNo;
    String name;
    HelpListBean helpListBean;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;
    private  String replyUnitCode;
    private OptionsPickerView ophujileixing, opzhipin;
    private String unitCode;
    private String unitCode1;
    List<ConsultChildrenBean> consultChildren;
    List<RepliesBean> repliesBeanList;
    @Override
    protected void initInject() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addAdviceHadDetailActivity(new AdviceDetailActivityModule(this))
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advicedetail_layout;
    }

    @Override
    protected void initData() {

        isreply=(String)getDeliveredSerializableByKey(Constants.Adviceisreply_ACTIVITY_KEY);
        conNo=(String)getDeliveredSerializableByKey(Constants.Advice_ACTIVITY_KEY);


    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, Constants.EMPTY_STRING);
        name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        ReliefItem_title.setText("咨询建议详情");
        replyUnitCode=  Identity.srcInfo.getUnitCode();
        String    areaID = Identity.srcInfo.getAreaId();
        final List<OrganizationBean> areaIDlist = DataSupport.where(" areaId=? ", areaID).find(OrganizationBean.class);
        unitCode=areaIDlist.get(0).getCode();
        final List<OrganizationBean> areaIDlist1 = DataSupport.where(" parentCode=? ", unitCode).find(OrganizationBean.class);

        final BaseQuickAdapter<RepliesBean, BaseViewHolder> mAdapter = new AdviceDetailAdapter(new ArrayList<RepliesAndConsult>(), TYPE_MULTIPLE_LINE,this,isreply,replyUnitCode);
        this.mAdapter = (AdviceDetailAdapter) mAdapter;


        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(this));
//        Util.addDividerToRecyclerView(this, recyclerView, true);

        ButterKnife.bind(this);
        RReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_reply.setText("");
            }
        });

        String area_level=IdentityManager.getAreaLevel(getApplicationContext());
        if(area_level==null){
            if (("3").equals(isreply)){
                tv_change.setVisibility(View.GONE);
                tv_reply.setText("回复");
                ed_reply.setHint("请输入您的内容...");
//                tv_reply.setText("追问");
//                ed_reply.setHint("在这里输入您想提问的问题...");
                ll_line1.setVisibility(View.GONE);
                ll_post.setVisibility(View.GONE);

            }else {
                tv_change.setVisibility(View.VISIBLE);
                tv_reply.setText("回复");
                ed_reply.setHint("请输入您的内容...");
                ll_line1.setVisibility(View.VISIBLE);
                ll_post.setVisibility(View.VISIBLE);

            }
        }else {
            if(area_level.equals("area_1") || area_level.equals("area_2") ){
                ll_line1.setVisibility(View.GONE);
                ll_post.setVisibility(View.GONE);
                tv_change.setVisibility(View.GONE);
            }else {
                ll_line1.setVisibility(View.VISIBLE);
                ll_post.setVisibility(View.VISIBLE);
                tv_change.setVisibility(View.VISIBLE);

                if (("3").equals(isreply)){
                    tv_change.setVisibility(View.GONE);
                    tv_reply.setText("回复");
                    ed_reply.setHint("请输入您的内容...");
//                    tv_reply.setText("追问");
//                    ed_reply.setHint("在这里输入您想提问的问题...");
                    ll_line1.setVisibility(View.GONE);
                    ll_post.setVisibility(View.GONE);

                }else {
                    tv_change.setVisibility(View.VISIBLE);
                    tv_reply.setText("回复");
                    ed_reply.setHint("请输入您的内容...");
                    ll_line1.setVisibility(View.VISIBLE);
                    ll_post.setVisibility(View.VISIBLE);

                }
            }
        }



        //是否回复（（0未回复，3已回复））


//
//        ll_remove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /**撤销本单位的回复内容，对于已经转办到其他单位且其他单位已经处理，
//                 * 或者未转办但是民众继续追问该问题的，不予许撤销。**/
//                //（（0未回复，1转办，2是追问,3已回复））

//                Map<String, String> removemap = ParametersFactory.getGCAAdviceRemoe(AdviceDetailActivity.this,
//                        conNo,"34010300000001",conNo,
//                        name,
//                        "consult.cancalReply");
//                mPresenter.Adviceremove(removemap);
//
//            }
//        });


        tv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**对于本区县处理不了的问题或者是其他区县涉及到的问题，
                 * 可以转办给其他区县或者本区县的乡镇处理，
                 * 本区县默认添加一条（已转办给xxxx单位处理）的记录。**/

                List<SRCLoginDataDictionaryBean> list=new ArrayList<>();
                for (int i=0;i<areaIDlist1.size();i++){
                    SRCLoginDataDictionaryBean srcLoginDataDictionaryBean =new SRCLoginDataDictionaryBean();
                    srcLoginDataDictionaryBean.setName(areaIDlist1.get(i).getName());
                    srcLoginDataDictionaryBean.setCode(areaIDlist1.get(i).getCode());

                    list.add(srcLoginDataDictionaryBean);

                }
                inithujileixing(tv_change, list, "转办机构");
                ophujileixing.show();


            }
        });
        Rpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**咨询建议回复，可以针对民众的问题回复，
                 * 注意，只能回复本单位的数据，省市级用户没操作权限。
                 * 区县可以回复，可以转办到其他区县或者所在的乡镇，
                 * 然后此问题交给其他区县或者所在乡镇处理。
                 * 本级区县记录（该问题已转交给xxx单位处理）
                 * ，返回问题编号，然后根据问题编号，跳转到对应详情页面。**/
                if (("3").equals(isreply)){
                    consultAgain();
                }else {
                    replyConsult();
                }






            }
        });

    }
    private void inithujileixing(final TextView setting_jigou, final List<SRCLoginDataDictionaryBean> listleixing1, String mingcheng) {//条件选择器初始化

        ophujileixing = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing1.get(options1).getName();
                unitCode1 = listleixing1.get(options1).getCode();
                turnConsult();
//                setting_jigou.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/

            }
        })
                .setTitleText(mingcheng)
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.rgb(255, 255, 255))
                .setTitleBgColor(Color.rgb(238, 238, 238))

                .setCancelColor(Color.rgb(38, 174, 158))
                .setSubmitColor(Color.rgb(38, 174, 158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("", "", "")
                .build();
        ophujileixing.setPicker(listleixing1);//一级选择器*/



    }

    private void turnConsult() {
        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
//        Map<String, String> map1 = ParametersFactory.getGCAAdviceturnConsult(this,conNo,"34010300000001",
//                name,"34010300000001",
//                "consult.turnConsult");replyUnitCode
        Map<String, String> map1 = ParametersFactory.getGCAAdviceturnConsult(this,conNo,unitCode1,
                name,replyUnitCode,
                "consult.turnConsult");
        mPresenter.turnConsult(map1);


    }

    public  void  remove(String  unitcode){

        Map<String, String> removemap = ParametersFactory.getGCAAdviceRemoe(AdviceHadDetailActivity.this,
                "",unitcode,conNo,
                name,
                "consult.cancalReply");
        mPresenter.Adviceremove(removemap);
    }
    private void consultAgain() {
        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());
        Map<String, String> map1 = ParametersFactory.getGCAAdviceconsultAgain(this,conNo,ed_reply.getText().toString(),
                name,replyUnitCode,
                "consult.consultAgain");
        mPresenter.replyConsult(map1,"1");


    }

    private void replyConsult() {
        String name= IdentityManager.getLoginSuccessUsername(getApplicationContext());

//        if (consultChildren.size()==0){
//
//        }else {
//            String conNo= consultChildren.get(consultChildren.size()-1).getConNo();
        Map<String, String> map1 = ParametersFactory.getGCAAdvicereply(this,conNo,ed_reply.getText().toString(),
                name,replyUnitCode,
                "consult.replyConsult","1");
        mPresenter.replyConsult(map1,"0");


//        }



    }


    @Override
    protected void loadData() {
        onRefresh();
    }


    @Override
    public void onconsultAgainSuccess(GCAHttpResultBaseBean<AdviceDetailBean> bean) {
        Toast.makeText(this,"追问成功",Toast.LENGTH_SHORT).show();
        finish();


    }

    @Override
    public void onreplyConsultSuccess(GCAHttpResultBaseBean<AdviceDetailBean> bean) {
        Toast.makeText(this,"回复成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onLoadListSuccess(GCAHttpResultBaseBean<AdviceDetailBean> bean) {
        AdviceDetailBean adviceDetailBean=bean.getData();
        List<RepliesBean> replies= adviceDetailBean.getReplies();
        consultChildren=adviceDetailBean.getConsultChildren();
        List<RepliesAndConsult> repliesAndConsults=new ArrayList<>();
//       /**把 追问 和 回复 两个list整合 为一个 新的 list **/
//        if (replies.size()==0&&consultChildren.size()==0){
//            recyclerView.setVisibility(View.GONE);
//
//        }else if (replies.size()==1&&replies.size()==0){
//
//            if (!("3").equals(isreply)){
//                recyclerView.setVisibility(View.GONE);
//            }else {
//                recyclerView.setVisibility(View.VISIBLE);
//                 RepliesAndConsult repliesAndConsult=  new RepliesAndConsult();
//                try {
//                    repliesAndConsult.setReplyContent(replies.get(0).getReplyContent());
//                    repliesAndConsult.setReplyTime(replies.get(0).getReplyTime());
//                    repliesAndConsult.setReplyUnit(replies.get(0).getReplyUnit());
//                    repliesAndConsult.setReplyUnitCode(replies.get(0).getReplyUnitCode());
//                    repliesAndConsult.setIsTurn(replies.get(0).getIsTurn());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                repliesAndConsult.setReplysize("1");
//                repliesAndConsult.setConsulsize("0");
//                repliesAndConsults.add(repliesAndConsult);
//                mAdapter.setNewData(repliesAndConsults);
//
//
//            }
//
//        }else {
//
//            if (("3").equals(isreply)){
//                //replies  多一条
//                for (int i=0 ; i< consultChildren.size();i++){
//                    RepliesAndConsult repliesAndConsult=  new RepliesAndConsult();
//                    try {
//                        repliesAndConsult.setReplyContent(replies.get(i).getReplyContent());
//                        repliesAndConsult.setReplyTime(replies.get(i).getReplyTime());
//                        repliesAndConsult.setReplyUnit(replies.get(i).getReplyUnit());
//                        repliesAndConsult.setReplyUnitCode(replies.get(i).getReplyUnitCode());
//                        repliesAndConsult.setIsTurn(replies.get(i).getIsTurn());
//
//                        repliesAndConsult.setConsulsize(String.valueOf(consultChildren.size()));
//                        repliesAndConsult.setReplysize(String.valueOf(replies.size()));
//
//                        repliesAndConsult.setConsultconNo(consultChildren.get(i).getConNo());
//                        repliesAndConsult.setConsultconsultTime(consultChildren.get(i).getConsultTime());
//                        repliesAndConsult.setConsultcontent(consultChildren.get(i).getContent());
////                        repliesAndConsult.setConsultconNo(consultChildren.get(i).getReplis());
//                        repliesAndConsult.setConsultconNo(consultChildren.get(i).getConNo());
//                        repliesAndConsult.setConsultconNo(consultChildren.get(i).getConNo());
//                        repliesAndConsult.setConsultconNo(consultChildren.get(i).getConNo());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }else {
//
//
//            }
//
//        }
//
//
//
//






        replyUnitCode=  adviceDetailBean.getReplies().get(adviceDetailBean.getReplies().size()-1).getReplyUnitCode();

        tv_content.setText(adviceDetailBean.getUnitConsultName());
        tv_ctitle.setText(adviceDetailBean.getTitle());
        tv_isturn.setText(adviceDetailBean.getType());
        consultTime.setText(adviceDetailBean.getConsultTime().replace('T',' '));
        //            0未回复，1转办，2是追问，3已回复
//        switch (adviceDetailBean.getIsReply()){
//            case "0":tv_isturn.setText("未回复") ;
//
//                break;
//            case "1":tv_isturn.setText("转办") ;
//                break;
//            case "2":tv_isturn.setText("追问") ;
//                break;
//            case "3":tv_isturn.setText("已回复") ;
//                break;
//
//
//        }
        repliesBeanList  =new ArrayList<>();
        for (int i=0;i<adviceDetailBean.getReplies().size();i++){
            if (TextUtils.isEmpty(adviceDetailBean.getReplies().get(i).getReplyContent())){


            }else {
                repliesBeanList.add(adviceDetailBean.getReplies().get(i));


            }


        }

//      if ( repliesBeanList.get(adviceDetailBean.getReplies().size()).getIsTurn().equals("1"))
//      {
//          ll_line1.setVisibility(View.GONE);
//      }else {
//          ll_line1.setVisibility(View.VISIBLE);
//      }
        if (adviceDetailBean.getReplies().size()==1&&TextUtils.isEmpty(adviceDetailBean.getReplies().get(0).getReplyContent())){
            recyclerView.setVisibility(View.GONE);
//            tv_content.setText(adviceDetailBean.getReplies().get(0).getReplyUnit());



        }
        else {
//            switch (adviceDetailBean.getIsReply()){
//                case "0":tv_isturn.setText("未回复") ;
//
//                    break;
//                case "1":tv_isturn.setText("转办") ;
//                    break;
//                case "2":tv_isturn.setText("追问") ;
//                    break;
//                case "3":tv_isturn.setText("已回复") ;
//                    break;
//
//
//            }
            recyclerView.setVisibility(View.VISIBLE);
            mAdapter.setNewData(repliesBeanList);
            recyclerView.setAdapter(mAdapter);
        }

        //是否回复（（0未回复，3已回复））
        if (("3").equals(isreply)){
            try {
                consultTime.setText(adviceDetailBean.getConsultTime().replace('T',' '));
                tv_neirongcontent.setText(adviceDetailBean.getContent());
//                replyTime.setText(adviceDetailBean.getReplies().get(0).getReplyTime());
//                replyUnit.setText(adviceDetailBean.getReplies().get(0).getReplyUnit());
//                replycontent.setText(adviceDetailBean.getReplies().get(0).getReplyContent());

            }catch (Exception ex) {

            }
        }else {
            try {

//                consultTime.setText(adviceDetailBean.getConsultTime());
                tv_neirongcontent.setText(adviceDetailBean.getContent());


            }catch (Exception ex) {

            }
        }

    }

    @Override
    public void onLoadMoreListSuccess(GCAHttpResultBaseBean<AdviceDetailBean> bean) {
//        reliefItemBean = bean.getData();

    }

    @Override
    public void onreplyConsultFailure(String message) {
        Toast.makeText(this,"回复失败:"+message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onconsultAgainFailure(String message) {
        Toast.makeText(this,"追问失败:"+message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onLoadListFailure(String message) {

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
//        NotInputDialog inputDialog = new NotInputDialog(this).builder().setTitle("提示"+"\n\n"+"该消息尚未数据！" )
//                .setCancelable(false)
//                .setPositiveBtn("确定", new NotInputDialog.OnPositiveListener() {
//                    @Override
//                    public void onPositive(View view, String inputMsg) {
//                        finish();
//                    }
//                })
//                .setCanceledOnTouchOutside(false);
//
//
//        inputDialog.show();
//        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

//        showdiaog();
    }

    @Override
    public void onAdviceremoveSuccess(GCAHttpResultBaseBean<SaveWanttoAdvicebean> bean) {
        Toast.makeText(this,"撤销成功",Toast.LENGTH_SHORT).show();
        finish();
//        onRefresh();

    }

    @Override
    public void onturnConsultSuccess(GCAHttpResultBaseBean<AdviceDetailBean> bean) {
        Toast.makeText(this,"转办成功",Toast.LENGTH_SHORT).show();
        NotInputDialog inputDialog = new NotInputDialog(this).builder().setTitle("提醒"+"\n\n"+"请牢记所咨询问题的受理编号，方便咨询！"+"\n\n" +bean.getData().getConNo())
                .setCancelable(false)
                .setPositiveBtn("确定", new NotInputDialog.OnPositiveListener() {
                    @Override
                    public void onPositive(View view, String inputMsg) {
                        setResult(RESULT_SUCCESS);
                        finish();
                    }
                })
                .setCanceledOnTouchOutside(false);


        inputDialog.show();
    }


    public void onRefresh() {

        Map<String, String> map = ParametersFactory.getGCAAdviceDetail(this,conNo,
                "consult.consultDetail");
        mPresenter.main(map);

    }



    //    private  void  showdiaog(){
//        if (reliefItemBean.getAcceptCondition()==null){
//
//
//            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//
//
//            NotInputDialog inputDialog = new NotInputDialog(this).builder().setTitle("提示"+"\n\n"+"该救助指南项目尚未完善！" )
//                    .setCancelable(false)
//                    .setPositiveBtn("确定", new NotInputDialog.OnPositiveListener() {
//                        @Override
//                        public void onPositive(View view, String inputMsg) {
//                            finish();
//                        }
//                    })
//                    .setCanceledOnTouchOutside(false);
//
//
//            inputDialog.show();
//
//
//        }
//    }

    public String checkisturn(){
        String falge="false";
        if (repliesBeanList.size()>1){
          return "true";
        }
        return     falge;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {



        if (keyCode == KeyEvent.KEYCODE_BACK) {


            setResult(RESULT_FAILED);

            finish();


        }
        return super.onKeyDown(keyCode, event);

    }
}
