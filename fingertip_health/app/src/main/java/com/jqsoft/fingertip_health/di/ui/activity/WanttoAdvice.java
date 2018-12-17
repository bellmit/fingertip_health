package com.jqsoft.fingertip_health.di.ui.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Identity;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.base.ParametersFactory;
import com.jqsoft.fingertip_health.bean.OrganizationBean;
import com.jqsoft.fingertip_health.bean.ProvinceBean;
import com.jqsoft.fingertip_health.bean.SaveWanttoAdvicebean;
import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.di.contract.WanttoAdviceContract;
import com.jqsoft.fingertip_health.di.module.WanttoAdviceModule;
import com.jqsoft.fingertip_health.di.presenter.WanttoAdvicePresenter;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.NotInputDialog;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.util.Validator;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/11.
 */

public class WanttoAdvice extends AbstractActivity implements WanttoAdviceContract.View {
    @BindView(R.id.sign_service_assess_title)
    TextView sign_service_assess_title;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView(R.id.setting_jigou)
    TextView setting_jigou;
    @BindView(R.id.setting_leixing)
    TextView setting_leixing;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.questintitle)
    EditText questintitle;
    @BindView(R.id.question_context)
    EditText question_context;
    @BindView(R.id.btn_save)
    RelativeLayout btn_save;
    @BindView(R.id.ll_shixian)
    LinearLayout ll_shixian;
    private String unitCode, question_type, addname, telphone, email, title, content;
    private String areaID;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;
    private ArrayList<OrganizationBean> organizationList = new ArrayList<>();
    @Inject
    WanttoAdvicePresenter mPresenter;
    List<SRCLoginDataDictionaryBean> list=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wanttoadvice;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sign_service_assess_title.setText("我要咨询");
        areaID = Identity.srcInfo.getAreaId();
//        final List<OrganizationBean> areaIDlist = DataSupport.where(" areaId=? and state=? ", areaID,"0").find(OrganizationBean.class);
//        final List<OrganizationBean> areaIDlist1 = DataSupport.where(" parentCode=? ",  areaIDlist.get(0).getParentCode()).find(OrganizationBean.class);

//      if (null==areaIDlist||areaIDlist.size()==0){
//          Toast.makeText(this,"没有找到当前机构code",Toast.LENGTH_SHORT).show();
//      }else {

        /*  String unticode = areaIDlist.get(0).getParentCode();

          final List<OrganizationBean> parentCodelist = DataSupport.where(" code=? and state=?", unticode,"0").find(OrganizationBean.class);
          if (parentCodelist==null){
              Toast.makeText(this,"没有找到当前机构的父级机构code",Toast.LENGTH_SHORT).show();
          }else {

              for (int i=0;i<parentCodelist.size();i++){
                  SRCLoginDataDictionaryBean srcLoginDataDictionaryBean =new SRCLoginDataDictionaryBean();
                  srcLoginDataDictionaryBean.setName(parentCodelist.get(i).getName());
                  srcLoginDataDictionaryBean.setCode(parentCodelist.get(i).getCode());

                  list.add(srcLoginDataDictionaryBean);

              }
          }
*/
//      }
        final List<SRCLoginDataDictionaryBean> myregpro = DataSupport.where(" pcode=?  and state=?", "consult_type","0").find(SRCLoginDataDictionaryBean.class);

        setting_jigou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//
//                initOptionData2();
//                initOptionPicker2();
//                if (pvOptions2 != null){
//                    pvOptions2.show(); //弹出条件选择器
//                }
              String  replyUnitCode=  Identity.srcInfo.getUnitCode();
                String  arealevel=  Identity.srcInfo.getAreaLevel();
                if(arealevel.equals("area_3")){
                    final List<OrganizationBean> myregpro = DataSupport.where(" code=?  and state=?",replyUnitCode,"0").find(OrganizationBean.class);
                    String idnew=myregpro.get(0).getAreaId();
                    final List<OrganizationBean> myregpronew = DataSupport.where(" areaid=?  and state=?",idnew,"0").find(OrganizationBean.class);

                    initjigou(setting_leixing, myregpronew, "咨询机构");
                    if(opjigou!=null){
                        opjigou.show();
                    }

                }else if(arealevel.equals("area_4")){
                    final List<OrganizationBean> myregpro = DataSupport.where("code=?  and state=?",replyUnitCode,"0").find(OrganizationBean.class);
                    String idnew=myregpro.get(0).getParentCode();
                    final List<OrganizationBean> myregpronew = DataSupport.where(" code=?  and state=?",idnew,"0").find(OrganizationBean.class);

                    final List<OrganizationBean> myregpronew1 = DataSupport.where(" areaid=?  and state=?",myregpronew.get(0).getAreaId(),"0").find(OrganizationBean.class);

                    initjigou(setting_leixing, myregpronew1, "咨询机构");
                    if(opjigou!=null){
                        opjigou.show();
                    }
                }else if(arealevel.equals("area_5")){
                    String  replyUnitCodenew=  Identity.srcInfo.getAreaId();

                    final List<SRCLoginAreaBean> myregpro = DataSupport.where("areacode=?  and state=?",replyUnitCodenew,"0").find(SRCLoginAreaBean.class);


                    String jiedao=myregpro.get(0).getAreaPid();
                    final List<SRCLoginAreaBean> myregprojiedao = DataSupport.where("areacode=?  and state=?",jiedao,"0").find(SRCLoginAreaBean.class);

                    final List<SRCLoginAreaBean> myxian = DataSupport.where(" areacode=?  and state=?",myregprojiedao.get(0).getAreaPid(),"0").find(SRCLoginAreaBean.class);

                    String idnew=myxian.get(0).getAreaCode();
                    final List<OrganizationBean> myregpronew = DataSupport.where(" areaid=?  and state=?",idnew,"0").find(OrganizationBean.class);

                    initjigou(setting_leixing, myregpronew, "咨询机构");
                    opjigou.show();

                }else {

                }




//
//                inithujileixing(setting_jigou, list, "所有机构");
//                ophujileixing.show();
            }
        });
        setting_leixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initzhipin(setting_leixing, myregpro, "所有问题");
                opzhipin.show();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addname = et_name.getText().toString();
                telphone = et_phone.getText().toString();
                email = et_email.getText().toString();
                title = questintitle.getText().toString();
                content = question_context.getText().toString();
                if (TextUtils.isEmpty(unitCode)) {
                    Util.showToast(getApplicationContext(), "咨询机构不能为空");
                    return;
                } else if (TextUtils.isEmpty(question_type)) {
                    Util.showToast(getApplicationContext(), "问题类型不能为空");
                    return;
                } else if (TextUtils.isEmpty(addname)) {
                    Util.showToast(getApplicationContext(), "您的姓名不能为空");
                    return;
                } else if (TextUtils.isEmpty(title)) {
                    Util.showToast(getApplicationContext(), "问题标题不能为空");
                    return;
                } else if (TextUtils.isEmpty(content)) {
                    Util.showToast(getApplicationContext(), "问题内容不能为空");
                    return;
                } else if (TextUtils.isEmpty(telphone)) {
                    Util.showToast(getApplicationContext(), "联系方式不能为空");
                    return;
                }else if ( !Validator.isMobileNO(telphone)) {
                    Util.showToast(getApplicationContext(), "手机号不正确");
                    return;
                }else {
                    String name = IdentityManager.getLoginSuccessUsername(getApplicationContext());
                    Map<String, String> map = ParametersFactory.saveAdviceData(WanttoAdvice.this,name, unitCode, question_type, addname, telphone, email, title, content);
                    mPresenter.main(map, false);
//
//                    Map<String, String> map = ParametersFactory.saveAdviceData(WanttoAdvice.this,name, "34032100000001", question_type, addname, telphone, email, title, content);
//                    mPresenter.main(map, false);
//                Map<String, String> map = ParametersFactory.saveAdviceData(WanttoAdvice.this, "34032100000001", "consult_type_01", name, "13546465456", "1111@qq.com", "123", "123");
//                    mPresenter.main(map, false);
                }

            }
        });

    }
    @Override
    protected void loadData() {


    }

    protected void initInject() {
        super.initInject();
        DaggerApplication.get(this)
                .getAppComponent()
                .addWanttoadvice(new WanttoAdviceModule(this))
                .inject(this);

    }

    @Override
    public void onLoadListSuccess(HttpResultBaseBean<SaveWanttoAdvicebean> bean) {
        Toast.makeText(this,"提交成功",Toast.LENGTH_SHORT).show();
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

    @Override
    public void onLoadListFailure(String message) {
        Toast.makeText(this,"提交失败",Toast.LENGTH_SHORT).show();
    }

    private OptionsPickerView ophujileixing, opzhipin;
    private OptionsPickerView opjigou,opkaihuhang;
    private String registerType;
    private void inithujileixing(final TextView setting_jigou, final List<SRCLoginDataDictionaryBean> listleixing1, String mingcheng) {//条件选择器初始化

        ophujileixing = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing1.get(options1).getName();
                setting_jigou.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                unitCode = listleixing1.get(options1).getCode();
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
//    private void inithujileixing(final TextView setting_jigou, final List<OrganizationBean> listleixing1, String mingcheng) {//条件选择器初始化
//
//        ophujileixing = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String tx = listleixing1.get(options1).getName();
//                setting_jigou.setText(tx);
//               /* sSavaHujileixing=tx;
//                sCodehujileix = listleixing.get(options1).getCode();*/
//                unitCode = listleixing1.get(options1).getCode();
//            }
//        })
//                .setTitleText(mingcheng)
//                .setContentTextSize(20)//设置滚轮文字大小
//                .setDividerColor(Color.rgb(255, 177, 177))//设置分割线的颜色
//                .setSelectOptions(0, 1)//默认选中项
//                .setBgColor(Color.rgb(255, 255, 255))
//                .setTitleBgColor(Color.rgb(238, 238, 238))
//
//                .setCancelColor(Color.rgb(38, 174, 158))
//                .setSubmitColor(Color.rgb(38, 174, 158))
//
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabels("", "", "")
//                .build();
//        ophujileixing.setPicker(listleixing1);//一级选择器*/
//
//
//    }

    private void initzhipin(final TextView huji, final List<SRCLoginDataDictionaryBean> listleixing, String mingcheng) {//条件选择器初始化

        opzhipin = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                question_type = listleixing.get(options1).getCode();
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

        opzhipin.setPicker(listleixing);//一级选择器*/


    }


    private void initjigou(final TextView huji, final List<OrganizationBean> listleixing, String mingcheng) {//条件选择器初始化

        opjigou = new OptionsPickerView.Builder(WanttoAdvice.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = listleixing.get(options1).getName();
//                huji.setText(tx);
               /* sSavaHujileixing=tx;
                sCodehujileix = listleixing.get(options1).getCode();*/
                unitCode = listleixing.get(options1).getCode();
                setting_jigou.setText(tx);
               // unitCode=getArrayListjiedao(options1,options2);
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

        opjigou.setPicker(listleixing);//一级选择器*/


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {



        if (keyCode == KeyEvent.KEYCODE_BACK) {


            setResult(RESULT_FAILED);

            finish();


        }
        return super.onKeyDown(keyCode, event);

    }


    ArrayList<ProvinceBean> options3Items = new ArrayList<>();
    ArrayList<ArrayList<String>> options4Items = new ArrayList<>();
    ArrayList<ArrayList<OrganizationBean>> jiedaoListOut;
    private void initOptionData2() {
      String Configvalue=   Identity.srcInfo.getConfigvalue();
        List<SRCLoginAreaBean> areaBeenShi= new ArrayList<>();
        areaBeenShi=DataSupport.where("areaLevel=? and state=?","area_2","0").find(SRCLoginAreaBean.class);
        List<OrganizationBean> alllist= new ArrayList<>();
        List<OrganizationBean> leftlist= new ArrayList<>();
        List<OrganizationBean> arealistjiedao= new ArrayList<>();
        alllist =DataSupport.findAll(OrganizationBean.class);
        for(int i=0;i<areaBeenShi.size();i++){
            for(int j=0;j<alllist.size();j++){
                if(areaBeenShi.get(i).getAreaCode().equals(alllist.get(j).getAreaId())){
                    leftlist.add(alllist.get(j));
                }
            }
        }
        for(int i=0;i<leftlist.size();i++){
            for(int j=0;j<alllist.size();j++){
                if(leftlist.get(i).getCode().equals(alllist.get(j).getParentCode())){
                    arealistjiedao.add(alllist.get(j));
                }
            }
        }

//        arealistqu.clear();
//        options3Items.clear();
//        options4Items.clear();
//

//        for(int i=0;i<user1.size();i++){
//            arealistqu.add(user1.get(i));
//        }

        options3Items.clear();
        options4Items.clear();

        for(int i=0;i<leftlist.size();i++){
            options3Items.add(new ProvinceBean(0,leftlist.get(i).getName(),"描述部分","其他数据"));
        }
        //街道显示名称
        ArrayList<ArrayList<String>> jiedaoNameArrayListOut = new ArrayList<>();

        jiedaoListOut = new ArrayList<>();
        jiedaoListOut.clear();
        for (int i= 0;i < leftlist.size();i ++){
            OrganizationBean area = leftlist.get(i);
            ArrayList<OrganizationBean> myJiedaoListIn = new ArrayList<>();
            ArrayList<String> jiedaoNameArrayListIn = new ArrayList<>();

            for (int j=0;j<arealistjiedao.size();j++) {
                OrganizationBean areaJiedao = arealistjiedao.get(j);
                if (area.getCode().equals(areaJiedao.getParentCode())){
                    myJiedaoListIn.add(areaJiedao);
                    jiedaoNameArrayListIn.add(areaJiedao.getName());
                }
            }
            jiedaoListOut.add(myJiedaoListIn);
            jiedaoNameArrayListOut.add(jiedaoNameArrayListIn);
        }

        options4Items=jiedaoNameArrayListOut;

        ArrayList<ArrayList<String>> options2Items_011 = new ArrayList<>();
        ArrayList<String> options2Items_0111 = new ArrayList<>();

        for(int i=0;i<leftlist.size();i++){
            for (int j=0;j<arealistjiedao.size();j++){
                if(leftlist.get(i).getCode().equals(arealistjiedao.get(j).getParentCode())){
                    // options2Items_011.add(arealistjiedao.get(j).getName());
                    options2Items_0111.add(arealistjiedao.get(j).getName());

                    //   options2Items_0111.clear();
                }


            }
            options4Items.add(options2Items_0111);
            options2Items_0111.clear();

        }


    }

    private OptionsPickerView pvOptions2;
    private void initOptionPicker2() {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        pvOptions2 = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                if(options4Items.get(0).size()==0){
                    String tx = options3Items.get(options1).getPickerViewText()
                            ;
                    setting_jigou.setText(tx);
//                    sCodequ =getArrayListqu(options1,options2);
//                    sCodejiedao =getArrayListjiedao(options1,options2);
                    unitCode=getArrayListjiedao(options1,options2);
                }else {
                    String tx = options3Items.get(options1).getPickerViewText()
                            + options4Items.get(options1).get(options2)
                            ;
                    setting_jigou.setText(tx);

                    //    sCodequ =getArrayListqu(options1,options2);
                    unitCode =getArrayListjiedao(options1,options2);
                }


            }
        })
                .setTitleText("城市选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.rgb(255,177,177))//设置分割线的颜色
                .setSelectOptions(0,1)//默认选中项
                .setBgColor(Color.rgb(255,255,255))
                .setTitleBgColor(Color.rgb(238,238,238))

                .setCancelColor(Color.rgb(38,174,158))
                .setSubmitColor(Color.rgb(38,174,158))

                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("","","")
                .build();

        //pvOptions.setSelectOptions(1,1);

        /*pvOptions.setPicker(options1Items);//一级选择器*/
        pvOptions2.setPicker(options3Items, options4Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/

    }


    private  String getArrayListjiedao(int leftIndex,int rightIndex){
        ArrayList<OrganizationBean> arrayList = jiedaoListOut.get(leftIndex);
        OrganizationBean areasListJiedao = arrayList.get(rightIndex);
        return areasListJiedao.getCode();
    }




    private void initkaihuhang(final TextView huji, final List<OrganizationBean> listleixing, String mingcheng) {//条件选择器初始化

        opkaihuhang = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = listleixing.get(options1).getName();
//                huji.setText(tx);
//               /* sSavaHujileixing=tx;
//                sCodehujileix = listleixing.get(options1).getCode();*/
//                bankName= listleixing.get(options1).getCode();
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
        opkaihuhang.setPicker(listleixing);//一级选择器*/


    }
}
