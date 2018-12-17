package com.jqsoft.fingertip_health.di.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constant;
import com.jqsoft.fingertip_health.bean.DmPostBean;
import com.jqsoft.fingertip_health.bean.FiltrateBean;
import com.jqsoft.fingertip_health.bean.HBPGuanLi_PersonInfo;
import com.jqsoft.fingertip_health.bean.NameValueBeanWithNo;
import com.jqsoft.fingertip_health.bean.UseDrugInfo;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValueBean;
import com.jqsoft.fingertip_health.di.ui.activity.DiabetesMellitusActivity;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodActivity;
import com.jqsoft.fingertip_health.optionlayout.NecessityNameOptionsNewLayout;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.rx.RxBusBaseMessage;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.FlowPopWindow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


public class DMFragment2 extends Fragment {
    private View rootView;



    HBPGuanLi_PersonInfo personInfo;
    private FlowPopWindow flowPopWindow;
    String adduser;
    private List<FiltrateBean> dictList = new ArrayList<>();
    private ArrayList<UseDrugInfo> datalist = new ArrayList<UseDrugInfo>();
    private ArrayList<UseDrugInfo> alldatalist = new ArrayList<UseDrugInfo>();
    View failureView;
    public static final int REQUEST_A = 1;
    NecessityNameOptionsNewLayout  psyadjusts, behavior, drugcompliance,drugbad,lowSugarReaction;
    private LinearLayout line_followtype;
private View topview;

    ScrollView sv_content;
    private EditText edit_moke1, edit_smoke2, edit_drink1, edit_drink2, edit_port1,otherAdverseReaction,
            edit_minute1, edit_sport2, edit_minute2,id_stapleAim,id_staple,fpg,pbg,accessoryExaminationHba1;
    private String ghkey;
    private CompositeSubscription mInitializeSubscription;
    private TextView followtype,accessoryExaminationDate;
    private void registerInitializeSubscription() {
        Subscription subscription = RxBus.getDefault().toObservable(Constant.ENENT_BW, RxBusBaseMessage.class).subscribe(new Action1<RxBusBaseMessage>() {
            @Override
            public void call(RxBusBaseMessage indexAndOnlineSignInitialData) {
                if (indexAndOnlineSignInitialData != null) {

                    switch (indexAndOnlineSignInitialData.getCode()){
                        case 3:
                            otherAdverseReaction.setText("");
                            otherAdverseReaction.setVisibility(View.GONE);
//                            otherAdverseReaction.setHint("无需输入");
//                            otherAdverseReaction.setEnabled(false);

                            break;
                        case 4:
                            otherAdverseReaction.setVisibility(View.VISIBLE);
                            otherAdverseReaction.setHint("请输入药物不良反应");
                            otherAdverseReaction.setEnabled(true);
                            break;

                    }

                }

            }
        });
        if (mInitializeSubscription == null) {
            mInitializeSubscription = new CompositeSubscription();
        }
        mInitializeSubscription.add(subscription);
    }
    private void unregisterInitializeSubscription() {
        if (mInitializeSubscription != null && mInitializeSubscription.hasSubscriptions()) {
            mInitializeSubscription.unsubscribe();
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterInitializeSubscription();
    }
    public DmPostBean getdata() {
        DiabetesMellitusActivity activity = (DiabetesMellitusActivity) getActivity();

        final ViewPager viewPager = activity.getVpContent();
        if (TextUtils.isEmpty(edit_moke1.getText())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, edit_moke1.getTop());
            Toast.makeText(getActivity(), "请输入目前日吸烟量!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edit_smoke2.getText())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, edit_smoke2.getTop());
            Toast.makeText(getActivity(), "请输入下次目标日吸烟量!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(edit_drink1.getText())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, edit_drink1.getTop());
            Toast.makeText(getActivity(), "请输入目前日饮酒量", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edit_drink2.getText())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, edit_drink2.getTop());
            Toast.makeText(getActivity(), "请输入下次目标日饮酒量", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(edit_port1.getText())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, edit_port1.getTop());
            Toast.makeText(getActivity(), "请输入目前运动周次", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edit_minute1.getText())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, edit_minute1.getTop());
            Toast.makeText(getActivity(), "请输入下次目前运动分钟", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edit_sport2.getText())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, edit_sport2.getTop());
            Toast.makeText(getActivity(), "请输入下次随访运动周次", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(edit_minute2.getText())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, edit_minute2.getTop());
            Toast.makeText(getActivity(), "请输入下次随访运动分钟", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(id_staple.getText())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, id_staple.getTop());
            Toast.makeText(getActivity(), "请输入当前主食量", Toast.LENGTH_SHORT).show();
        }
//        else if (TextUtils.isEmpty(fpg.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, fpg.getTop());
//            Toast.makeText(getActivity(), "请输入空腹血糖值", Toast.LENGTH_SHORT).show();
//        }else if (TextUtils.isEmpty(pbg.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, pbg.getTop());
//            Toast.makeText(getActivity(), "请输入随机血糖值", Toast.LENGTH_SHORT).show();
//        }
        else if (TextUtils.isEmpty(id_stapleAim.getText())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, id_stapleAim.getTop());
            Toast.makeText(getActivity(), "请输入目标主食量", Toast.LENGTH_SHORT).show();
        }
//        else if (TextUtils.isEmpty(accessoryExaminationHba1.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, accessoryExaminationHba1.getTop());
//            Toast.makeText(getActivity(), "请输入糖化血糖蛋白", Toast.LENGTH_SHORT).show();
//        }else if (TextUtils.isEmpty(accessoryExaminationDate.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, accessoryExaminationDate.getTop());
//            Toast.makeText(getActivity(), "请选择糖化血糖蛋白检查日期", Toast.LENGTH_SHORT).show();
//        }
        else if (Constant.EMPTY_STRING.equals(psyadjusts.getSingleSelectName())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, psyadjusts.getTop());
            Toast.makeText(getActivity(), "请选择心理调整!", Toast.LENGTH_SHORT).show();
        }else if (Constant.EMPTY_STRING.equals(lowSugarReaction.getSingleSelectName())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, lowSugarReaction.getTop());
            Toast.makeText(getActivity(), "请选择低血糖反应!", Toast.LENGTH_SHORT).show();
        } else if (Constant.EMPTY_STRING.equals(behavior.getSingleSelectName())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, behavior.getTop());
            Toast.makeText(getActivity(), "请选择遵医行为!", Toast.LENGTH_SHORT).show();
        } else if (Constant.EMPTY_STRING.equals(drugcompliance.getSingleSelectName())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, drugcompliance.getTop());
            Toast.makeText(getActivity(), "请选择服药依从性!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(followtype.getText().toString())) {
            viewPager.setCurrentItem(1);
            sv_content.smoothScrollTo(0, followtype.getTop());
            Toast.makeText(getActivity(), "请选择本次随访分类", Toast.LENGTH_SHORT).show();
        }else {

            DmPostBean dmPostBean = new DmPostBean();
            dmPostBean.setDailySmoke(edit_moke1.getText().toString());
            dmPostBean.setDailySmokeTarget(edit_smoke2.getText().toString());
            dmPostBean.setDailyDrink(edit_drink1.getText().toString());
            dmPostBean.setDailyDrinkTarget(edit_drink2.getText().toString());
            dmPostBean.setExercise(edit_port1.getText().toString());
            dmPostBean.setNextExercise(edit_sport2.getText().toString());
            dmPostBean.setExerciseTimes(edit_minute1.getText().toString());
            dmPostBean.setNextExerciseTimes(edit_minute2.getText().toString());
            dmPostBean.setPsychic(psyadjusts.getSingleSelectValue());
            dmPostBean.setDrugCompliance(drugcompliance.getSingleSelectValue());
            dmPostBean.setAdverseReaction(drugbad.getSingleSelectValue());
            dmPostBean.setPatientCompliance(behavior.getSingleSelectValue());
            if (!followtype.getText().toString().equals("请选择随访分类>")){
                switch (followtype.getText().toString()){
                    case "控制满意":
                        dmPostBean.setSort("1");
                        break;
                    case "控制不满意":
                        dmPostBean.setSort("2");
                        break;
                    case "不良反应":
                        dmPostBean.setSort("3");
                        break;
                    case "并发症":
                        dmPostBean.setSort("4");
                        break;
                }

            }
            dmPostBean.setStaple(id_staple.getText().toString());
            dmPostBean.setStapleAim(id_stapleAim.getText().toString());
            dmPostBean.setFpg(fpg.getText().toString());
            dmPostBean.setPbg(pbg.getText().toString());
            dmPostBean.setAccessoryExaminationDate(accessoryExaminationDate.getText().toString());
            dmPostBean.setAccessoryExaminationHba1c(accessoryExaminationHba1.getText().toString());

            dmPostBean.setOtherAdverseReaction(otherAdverseReaction.getText().toString());
            dmPostBean.setLowSugarReaction(lowSugarReaction.getSingleSelectValue());


                return dmPostBean;
        }


        return null;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public  void  setSHZD(String smoke1,String smoke2,String drink1,
                          String drink2,String sport1,String minute1,
                          String sport2,String minute2){
        if (!TextUtils.isEmpty(smoke1)&&!(edit_moke1==null)){
            edit_moke1.setText(smoke1);
        }
        if (!TextUtils.isEmpty(smoke1)&&!(edit_smoke2==null)){
            edit_smoke2.setText(smoke2);
        }
        if (!TextUtils.isEmpty(drink1)&&!(edit_drink1==null)){
            edit_drink1.setText(drink1);
        }
        if (!TextUtils.isEmpty(drink2)&&!(edit_drink2==null)){
            edit_drink2.setText(drink2);
        }
        if (!TextUtils.isEmpty(sport1)&&!(edit_port1==null)){
            edit_port1.setText(sport1);
        }
        if (!TextUtils.isEmpty(minute1)&&!(edit_minute1==null)){
            edit_minute1.setText(minute1);
        }
        if (!TextUtils.isEmpty(minute1)&&!(edit_minute1==null)){
            edit_minute1.setText(minute1);
        }
        if (!TextUtils.isEmpty(sport2)&&!(edit_sport2==null)){
            edit_sport2.setText(sport2);
        }
        if (!TextUtils.isEmpty(minute2)&&!(edit_minute2==null)){
            edit_minute2.setText(minute2);
        }

    }

    public void ScrollView(View view) {
        sv_content.smoothScrollTo(0, view.getTop());
    }


    public ArrayList<UseDrugInfo> getDatalist() {
        return datalist;
    }

    public void initView() {
        DiabetesMellitusActivity activity = (DiabetesMellitusActivity) getActivity();
        final HBPGuanLi_PersonInfo hbpGuanLi_personInfo = activity.getHbpGuanLi_personInfo();
        final String gKey = UUID.randomUUID().toString();
        registerInitializeSubscription();

    }





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dm_two_layout, container, false);
        edit_moke1 = (EditText) view.findViewById(R.id.edit_moke1);
        edit_smoke2 = (EditText) view.findViewById(R.id.edit_smoke2);
        edit_drink1 = (EditText) view.findViewById(R.id.edit_drink1);
        edit_drink2 = (EditText) view.findViewById(R.id.edit_drink2);
        edit_port1 = (EditText) view.findViewById(R.id.edit_port1);
        edit_minute1 = (EditText) view.findViewById(R.id.edit_minute1);
        edit_sport2 = (EditText) view.findViewById(R.id.edit_sport2);
        edit_minute2 = (EditText) view.findViewById(R.id.edit_minute2);
        topview=(View)view.findViewById(R.id.topview);
        fpg=(EditText)view.findViewById(R.id.fpg);
        pbg=(EditText)view.findViewById(R.id.pbg);
        otherAdverseReaction=(EditText)view.findViewById(R.id.otherAdverseReaction);
        accessoryExaminationHba1=(EditText)view.findViewById(R.id.accessoryExaminationHba1);
        accessoryExaminationDate=(TextView)view.findViewById(R.id.accessoryExaminationDate);

        line_followtype=(LinearLayout)view.findViewById(R.id.line_followtype);
        id_stapleAim=(EditText)view.findViewById(R.id.id_stapleAim);
        id_staple=(EditText)view.findViewById(R.id.id_staple);

        edit_moke1.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_smoke2.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_drink1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        edit_drink2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_VARIATION_NORMAL);


        edit_port1.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_minute1.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_sport2.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_minute2.setInputType(InputType.TYPE_CLASS_NUMBER);

        drugbad=(NecessityNameOptionsNewLayout) view.findViewById(R.id.drugbad);

        lowSugarReaction= (NecessityNameOptionsNewLayout) view.findViewById(R.id.lowSugarReaction);
        psyadjusts = (NecessityNameOptionsNewLayout) view.findViewById(R.id.psyadjust);
        behavior = (NecessityNameOptionsNewLayout) view.findViewById(R.id.behavior);
        drugcompliance = (NecessityNameOptionsNewLayout) view.findViewById(R.id.drugcompliance);
//        drugbad = (HbOneWhithEidtextOptionsLayout) view.findViewById(R.id.drugbad);
        followtype = (TextView) view.findViewById(R.id.followtype);
        sv_content = (ScrollView) view.findViewById(R.id.sv_content);


        List<NameValueBean> genderListPsyadjust = new ArrayList<>();
        genderListPsyadjust.add(new NameValueBean("良好", "1",false));
        genderListPsyadjust.add(new NameValueBean("一般", "2", false));
        genderListPsyadjust.add(new NameValueBean("差", "3", false));
        setViewDataDictionaryDataByCode(genderListPsyadjust, psyadjusts);

        List<NameValueBean> genderListBehavior = new ArrayList<>();

        genderListBehavior.add(new NameValueBean("良好", "1", false));
        genderListBehavior.add(new NameValueBean("一般", "2", false));
        genderListBehavior.add(new NameValueBean("差", "3", false));
        setViewDataDictionaryDataByCode(genderListBehavior, behavior);
        List<NameValueBean> genderListDrugcompliance = new ArrayList<>();

        genderListDrugcompliance.add(new NameValueBean("规律", "1", false));
        genderListDrugcompliance.add(new NameValueBean("间断", "2", false));
        genderListDrugcompliance.add(new NameValueBean("不服药", "3", false));
        setViewDataDictionaryDataByCode(genderListDrugcompliance, drugcompliance);

        List<NameValueBean> genderListdrugbad = new ArrayList<>();

        genderListdrugbad.add(new NameValueBean(" 无 ", "1", false));
        genderListdrugbad.add(new NameValueBean(" 有 ", "2", false));
        setViewDataDictionaryDataByCode(genderListdrugbad, drugbad);
        List<NameValueBean> genderListlowSugarReaction = new ArrayList<>();

        genderListlowSugarReaction.add(new NameValueBean("无", "1", false));
        genderListlowSugarReaction.add(new NameValueBean("偶尔", "2", false));
        genderListlowSugarReaction.add(new NameValueBean("频繁", "3", false));
        setViewDataDictionaryDataByCode(genderListlowSugarReaction, lowSugarReaction);

        final DiabetesMellitusActivity activity=(DiabetesMellitusActivity)getActivity();
        line_followtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowPopWindow = new FlowPopWindow(getActivity(), dictList,true);
                flowPopWindow.showAsDropDown(activity.gettopview());
                flowPopWindow.setOnConfirmClickListener(new FlowPopWindow.OnConfirmClickListener() {
                    @Override
                    public void onConfirmClick() {
                        String value="";
                        for (FiltrateBean fb : dictList) {
                            List<FiltrateBean.Children> cdList = fb.getChildren();
                            for (int x = 0; x < cdList.size(); x++) {
                                FiltrateBean.Children children = cdList.get(x);
                                if (children.isSelected())
                                    if (TextUtils.isEmpty(value)){
                                        value=   children.getValue();
                                    }else {
                                        value=value+","+children.getValue();
                                    }

                            }
                        }
                        if (!TextUtils.isEmpty(value)){
                            followtype.setText(value);
                        }else {
                            followtype.setText("请选择随访分类>");
                        }

                    }
                });
            }
        });

//        List<NameValueBeanWithNo> genderListDrugbad = new ArrayList<>();
//        genderListDrugbad.clear();
//        genderListDrugbad.add(new NameValueBeanWithNo("无", "1", "", false));
//        genderListDrugbad.add(new NameValueBeanWithNo("有", "2", "", false));
//        drugbad.setDataList(genderListDrugbad);

//      setViewDataDictionaryDataByCode(genderListDrugbad, drugbad);


        initView();

//        List<NameValueBean> genderListpe = new ArrayList<>();
//        genderListpe.clear();
//        genderListpe.add(new NameValueBean("控制满意", "1", true));
//        genderListpe.add(new NameValueBean("控制不满意", "2", false));
//        genderListpe.add(new NameValueBean("不良反应", "3", false));
//        genderListpe.add(new NameValueBean("并发症", "4", false));
//        setViewDataDictionaryDataByCode(genderListpe, pe);

        initParam();
        Util.setViewListener(accessoryExaminationDate, new Runnable() {
            @Override
            public void run() {
                Calendar maxDate = Calendar.getInstance();
                maxDate.setTime(new Date());
                Util.showDateNewDialogWithMaxDate1(getActivity(), accessoryExaminationDate.getText().toString(), "nnilBirthday",
                        maxDate,
                        new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
                                accessoryExaminationDate.setText(s);
                            }
                        } ,true);
            }
        });



        return view;

    }

    //        List<NameValueBean> genderListFollowtype = new ArrayList<>();
//        genderListFollowtype.clear();
//        genderListFollowtype.add(new NameValueBean("控制满意", "1", false));
//        genderListFollowtype.add(new NameValueBean("控制不满意", "2", false));
//        genderListFollowtype.add(new NameValueBean("不良反应", "3", false));
//        genderListFollowtype.add(new NameValueBean("并发症", "4", false));
//        setViewDataDictionaryDataByCode(genderListFollowtype, followtype);
    private void initParam() {
        String[] colors = {"控制满意", "控制不满意", "不良反应", "并发症"};
        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("随访分类");
        List<FiltrateBean.Children> childrenList2 = new ArrayList<>();
        for (int x = 0; x < colors.length; x++) {
            FiltrateBean.Children cd = new FiltrateBean.Children();
            cd.setValue(colors[x]);
            childrenList2.add(cd);
        }
        fb2.setChildren(childrenList2);
        dictList.add(fb2);





    }

//    public void setfollowtype(int type) {
//
//
//
//
//        switch (type) {
//            case 1: {
//                if (followtype==null){
//
//                }else {
//                    followtype.setSingleSelectText("控制满意");
//                }
//
//                HighBloodActivity highBloodActivity = (HighBloodActivity) getActivity();
//                String dFollTime = highBloodActivity.getHbFragment1().gettime();
//
//                Date date = new Date();
//                Date dBefore = new Date();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    date = sdf.parse(dFollTime);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Calendar calendar = Calendar.getInstance(); //得到日历
//                calendar.setTime(date);//把当前时间赋给日历
//                calendar.add(Calendar.MONTH, 3);  //设置为前3月
//                dBefore = calendar.getTime();   //得到前3月的时间
//                SimpleDateFormat endsdf = new SimpleDateFormat("yyyy-MM-dd "); //设置时间格式
//                String defaultStartDate = endsdf.format(dBefore);    //格式化前3月的时间
//                highBloodActivity.getHbFragment3().geteditext().setText(defaultStartDate);
//            }
//
//            break;
//            case 2:
//                if (followtype==null){
//
//                }else {
//                    followtype.setSingleSelectText("控制不满意");
//                }
//
//                HighBloodActivity highBloodActivity = (HighBloodActivity) getActivity();
//                String dFollTime = highBloodActivity.getHbFragment1().gettime();
//                Date date = new Date();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                try {
//                    date = sdf.parse(dFollTime);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                Calendar ca = Calendar.getInstance();
//                ca.add(Calendar.DATE, 14);// num为增加的天数，可以改变的
//                date = ca.getTime();
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                String defaultStartDate = format.format(date); //
//                highBloodActivity.getHbFragment3().geteditext().setText(defaultStartDate);
//
//                break;
//            case 3:
//                if (followtype==null){
//
//                }else {
//                    followtype.setSingleSelectText("不良反应");
//                }
//
//                break;
//            case 4:
//                if (followtype==null){
//
//                }else {
//                    followtype.setSingleSelectText("并发症");
//                }
//
//                break;
//
//        }
//
//    }

    public void setViewDataDictionaryDataByCode(List<NameValueBean> genderList, NecessityNameOptionsNewLayout layout) {
        if (layout != null) {

            layout.setDataList(genderList);
        }
    }

//    private Handler myhandler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case Constant.UPDATE_NORMOL:
////                    for (int i = 0; i < datalist.size(); i++) {
////                        if (datalist.get(i).getsDrugName().equals("") && datalist.get(i).getiDrug_EveryDay().equals("") && datalist.get(i).getsDrug_EveryTime().equals("")) {
////                            adapter.remove(i);
////                            adapter.notifyDataSetChanged();
////                        }
////                    }
//                    if(sycList.get(sycList.size() - 1).getsMedicalList()==null){
//
//                        Util.showToast(getActivity(), "暂无用药");
//                        WeiboDialogUtils.closeDialog(mDialog);
//                    }else {
////                        datalist.clear();
//                        datalist.addAll(0, sycList.get(sycList.size() - 1).getsMedicalList());
//                        adapter.notifyDataSetChanged();
//                        Util.showToast(getActivity(), "获取用药成功");
//                        WeiboDialogUtils.closeDialog(mDialog);
//                    }
//
////                    datalist.add(sycList.get(sycList.size() - 1).getsMedicalList().get(0));
////                    adapter.setNewData(datalist);
////                    recyclerView.setAdapter(adapter);
//
//                    break;
//                case Constant.MSG_LOAD_ERROR:
//                    Util.showToast(getActivity(), "暂无数据");
//                    WeiboDialogUtils.closeDialog(mDialog);
//                    break;
//            }
//        }
//    };

}
