package com.jqsoft.fingertip_health.di.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.HbThreeAdapter;
import com.jqsoft.fingertip_health.base.Constant;
import com.jqsoft.fingertip_health.bean.FiltrateBean;
import com.jqsoft.fingertip_health.bean.HBPGuanLi_PersonInfo;
import com.jqsoft.fingertip_health.bean.HbPostBean;
import com.jqsoft.fingertip_health.bean.NameValueBeanWithNo;
import com.jqsoft.fingertip_health.bean.UseDrugInfo;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.NameValueBean;
import com.jqsoft.fingertip_health.di.ui.activity.HighBloodActivity;
import com.jqsoft.fingertip_health.optionlayout.NecessityNameOptionsNewLayout;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.rx.RxBusBaseMessage;
import com.jqsoft.fingertip_health.util.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.FlowPopWindow;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;


public class HBFragment2 extends Fragment {
    private View rootView;



    HBPGuanLi_PersonInfo personInfo;
    private FlowPopWindow flowPopWindow;
    String adduser;
    private List<FiltrateBean> dictList = new ArrayList<>();
    private ArrayList<UseDrugInfo> datalist = new ArrayList<UseDrugInfo>();
    private ArrayList<UseDrugInfo> alldatalist = new ArrayList<UseDrugInfo>();
    View failureView;
    public static final int REQUEST_A = 1;
    NecessityNameOptionsNewLayout salt, saltAfter, psyadjusts, behavior, drugcompliance,drugbad;
    private LinearLayout line_followtype;
private View topview;
    private CompositeSubscription mInitializeSubscription;
    ScrollView sv_content;
    private EditText edit_moke1, edit_smoke2, edit_drink1, edit_drink2, edit_port1, edit_minute1,
            edit_sport2, edit_minute2, edit_saccessoryexamination,otherAdverseReaction;
    private String ghkey;
    private TextView followtype;

    private void registerInitializeSubscription() {
        Subscription subscription = RxBus.getDefault().toObservable(Constant.ENENT_BW, RxBusBaseMessage.class).subscribe(new Action1<RxBusBaseMessage>() {
            @Override
            public void call(RxBusBaseMessage indexAndOnlineSignInitialData) {
                if (indexAndOnlineSignInitialData != null) {

                    switch (indexAndOnlineSignInitialData.getCode()){
                        case 3:
                            otherAdverseReaction.setVisibility(View.GONE);
//                            otherAdverseReaction.setHint("????????????");
//                            otherAdverseReaction.setEnabled(false);
                            otherAdverseReaction.setText("");
                            break;
                        case 4:
                            otherAdverseReaction.setVisibility(View.VISIBLE);
                            otherAdverseReaction.setHint("???????????????????????????");
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

    public HbPostBean getdata() {


        HighBloodActivity activity = (HighBloodActivity) getActivity();
        final ViewPager viewPager = activity.getVpContent();

//        if (TextUtils.isEmpty(edit_moke1.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, edit_moke1.getTop());
//            Toast.makeText(getActivity(), "???????????????????????????!", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(edit_smoke2.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, edit_smoke2.getTop());
//            Toast.makeText(getActivity(), "?????????????????????????????????!", Toast.LENGTH_SHORT).show();
//        }else if (TextUtils.isEmpty(edit_drink1.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, edit_drink1.getTop());
//            Toast.makeText(getActivity(), "???????????????????????????", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(edit_drink2.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, edit_drink2.getTop());
//            Toast.makeText(getActivity(), "?????????????????????????????????", Toast.LENGTH_SHORT).show();
//        }else if (TextUtils.isEmpty(edit_port1.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, edit_port1.getTop());
//            Toast.makeText(getActivity(), "???????????????????????????", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(edit_minute1.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, edit_minute1.getTop());
//            Toast.makeText(getActivity(), "?????????????????????????????????", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(edit_sport2.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, edit_sport2.getTop());
//            Toast.makeText(getActivity(), "?????????????????????????????????", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(edit_minute2.getText())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, edit_minute2.getTop());
//            Toast.makeText(getActivity(), "?????????????????????????????????", Toast.LENGTH_SHORT).show();
//        }else if(Constant.EMPTY_STRING.equals(salt.getSingleSelectName())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, salt.getTop());
//            Toast.makeText(getActivity(), "?????????????????????!", Toast.LENGTH_SHORT).show();
//        }else if (Constant.EMPTY_STRING.equals(psyadjusts.getSingleSelectName())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, psyadjusts.getTop());
//            Toast.makeText(getActivity(), "?????????????????????!", Toast.LENGTH_SHORT).show();
//        } else if (Constant.EMPTY_STRING.equals(behavior.getSingleSelectName())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, behavior.getTop());
//            Toast.makeText(getActivity(), "?????????????????????!", Toast.LENGTH_SHORT).show();
//        } else if (Constant.EMPTY_STRING.equals(drugcompliance.getSingleSelectName())) {
//            viewPager.setCurrentItem(1);
//            sv_content.smoothScrollTo(0, drugcompliance.getTop());
//            Toast.makeText(getActivity(), "????????????????????????!", Toast.LENGTH_SHORT).show();
//        }else {
///patientCompliance	???????????????1 ?????? 2 ?????? 3 ??????	Integer		1 ?????? 2 ?????? 3 ???
//            accessoryExamination	????????????	String
//            drugCompliance	??????????????????1 ?????? 2 ?????? 3 ????????????	Integer
//            adverseReaction	?????????????????????1??? 2???	Integer
//            otherAdverseReaction	??????????????????	String		1??? 2???
//            sort	??????????????????1 ???????????? 2 ???????????????3 ????????? 4????????? ???	Integer		1 ???????????? 2 ???????????????3 ????????? 4?????????
//            nextFlwDate	??????????????????	String
//            doctorCode	????????????????????????	String
//            name	????????????	String


            HbPostBean hbPostBean = new HbPostBean();
            hbPostBean.setPsychic(psyadjusts.getSingleSelectValue());
            hbPostBean.setAccessoryExamination(edit_saccessoryexamination.getText().toString());
            hbPostBean.setDrugCompliance(drugcompliance.getSingleSelectValue());
            hbPostBean.setAdverseReaction(drugbad.getSingleSelectValue());
            hbPostBean.setOtherAdverseReaction(otherAdverseReaction.getText().toString());

            if (!TextUtils.isEmpty(followtype.getText().toString())){
                switch (followtype.getText().toString()){
                    case "????????????":
                        hbPostBean.setSort("1");
                        break;
                    case "???????????????":
                        hbPostBean.setSort("2");
                        break;
                    case "????????????":
                        hbPostBean.setSort("3");
                        break;
                    case "?????????":
                        hbPostBean.setSort("4");
                        break;
                }

            }

            hbPostBean.setExercise(edit_port1.getText().toString());
            hbPostBean.setNextExercise(edit_sport2.getText().toString());
            hbPostBean.setExerciseTimes(edit_minute1.getText().toString());
            hbPostBean.setNextExerciseTimes(edit_minute2.getText().toString());
            hbPostBean.setDailySmoke(edit_moke1.getText().toString());
            hbPostBean.setDailySmokeTarget(edit_smoke2.getText().toString());
            hbPostBean.setDailyDrink(edit_drink1.getText().toString());
            hbPostBean.setDailyDrinkTarget(edit_drink2.getText().toString());
            hbPostBean.setSaltUptake(salt.getSingleSelectValue());
            hbPostBean.setSaltUptakeTarget(salt.getSingleSelectValue());
            hbPostBean.setPatientCompliance(behavior.getSingleSelectValue());




            return hbPostBean;
//
//        }
//
//
//        return null;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void ScrollView(View view) {
        sv_content.smoothScrollTo(0, view.getTop());
    }


    public ArrayList<UseDrugInfo> getDatalist() {
        return datalist;
    }

    public void initView() {
        registerInitializeSubscription();


    }





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hb_two_layout, container, false);
        edit_moke1 = (EditText) view.findViewById(R.id.edit_moke1);
        edit_smoke2 = (EditText) view.findViewById(R.id.edit_smoke2);
        edit_drink1 = (EditText) view.findViewById(R.id.edit_drink1);
        edit_drink2 = (EditText) view.findViewById(R.id.edit_drink2);
        edit_port1 = (EditText) view.findViewById(R.id.edit_port1);
        edit_minute1 = (EditText) view.findViewById(R.id.edit_minute1);
        edit_sport2 = (EditText) view.findViewById(R.id.edit_sport2);
        edit_minute2 = (EditText) view.findViewById(R.id.edit_minute2);
        otherAdverseReaction=(EditText)view.findViewById(R.id.otherAdverseReaction) ;
        topview=(View)view.findViewById(R.id.topview);
        edit_saccessoryexamination = (EditText) view.findViewById(R.id.edit_saccessoryexamination);
        line_followtype=(LinearLayout)view.findViewById(R.id.line_followtype);


        edit_moke1.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_smoke2.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_drink1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        edit_drink2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_VARIATION_NORMAL);


        edit_port1.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_minute1.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_sport2.setInputType(InputType.TYPE_CLASS_NUMBER);
        edit_minute2.setInputType(InputType.TYPE_CLASS_NUMBER);

        drugbad=(NecessityNameOptionsNewLayout) view.findViewById(R.id.drugbad);
        salt = (NecessityNameOptionsNewLayout) view.findViewById(R.id.salt);
        saltAfter = (NecessityNameOptionsNewLayout) view.findViewById(R.id.salt_after);
        psyadjusts = (NecessityNameOptionsNewLayout) view.findViewById(R.id.psyadjust);
        behavior = (NecessityNameOptionsNewLayout) view.findViewById(R.id.behavior);
        drugcompliance = (NecessityNameOptionsNewLayout) view.findViewById(R.id.drugcompliance);
//        drugbad = (HbOneWhithEidtextOptionsLayout) view.findViewById(R.id.drugbad);
        followtype = (TextView) view.findViewById(R.id.followtype);
        sv_content = (ScrollView) view.findViewById(R.id.sv_content);
        List<NameValueBean> genderListMeal = new ArrayList<>();
        genderListMeal.clear();
        genderListMeal.add(new NameValueBean("???", "1", false));
        genderListMeal.add(new NameValueBean("???", "2", false));
        genderListMeal.add(new NameValueBean("???", "3", false));
        setViewDataDictionaryDataByCode(genderListMeal, salt);

        List<NameValueBean> genderListAfter = new ArrayList<>();
        genderListAfter.clear();
        genderListAfter.add(new NameValueBean("???", "1", false));
        genderListAfter.add(new NameValueBean("???", "2", false));
        genderListAfter.add(new NameValueBean("???", "3", false));
        setViewDataDictionaryDataByCode(genderListAfter, saltAfter);

        List<NameValueBean> genderListPsyadjust = new ArrayList<>();
        genderListPsyadjust.clear();
        genderListPsyadjust.add(new NameValueBean("??????", "1", false));
        genderListPsyadjust.add(new NameValueBean("??????", "2", false));
        genderListPsyadjust.add(new NameValueBean("???", "3", false));
        setViewDataDictionaryDataByCode(genderListPsyadjust, psyadjusts);

        List<NameValueBean> genderListBehavior = new ArrayList<>();
        genderListBehavior.clear();
        genderListBehavior.add(new NameValueBean("??????", "1", false));
        genderListBehavior.add(new NameValueBean("??????", "2", false));
        genderListBehavior.add(new NameValueBean("???", "3", false));
        setViewDataDictionaryDataByCode(genderListBehavior, behavior);
        List<NameValueBean> genderListDrugcompliance = new ArrayList<>();
        genderListDrugcompliance.clear();
        genderListDrugcompliance.add(new NameValueBean("??????", "1", false));
        genderListDrugcompliance.add(new NameValueBean("??????", "2", false));
        genderListDrugcompliance.add(new NameValueBean("?????????", "3", false));
        setViewDataDictionaryDataByCode(genderListDrugcompliance, drugcompliance);

        List<NameValueBean> genderListdrugbad = new ArrayList<>();
        genderListdrugbad.clear();
        genderListdrugbad.add(new NameValueBean(" ??? ", "1", false));
        genderListdrugbad.add(new NameValueBean(" ??? ", "2", false));
        setViewDataDictionaryDataByCode(genderListdrugbad, drugbad);




        final HighBloodActivity activity=(HighBloodActivity)getActivity();
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
                            followtype.setText("?????????????????????>");
                        }

                    }
                });
            }
        });

//        List<NameValueBeanWithNo> genderListDrugbad = new ArrayList<>();
//        genderListDrugbad.clear();
//        genderListDrugbad.add(new NameValueBeanWithNo("???", "1", "", false));
//        genderListDrugbad.add(new NameValueBeanWithNo("???", "2", "", false));
//        drugbad.setDataList(genderListDrugbad);

//      setViewDataDictionaryDataByCode(genderListDrugbad, drugbad);


        initView();

//        List<NameValueBean> genderListpe = new ArrayList<>();
//        genderListpe.clear();
//        genderListpe.add(new NameValueBean("????????????", "1", true));
//        genderListpe.add(new NameValueBean("???????????????", "2", false));
//        genderListpe.add(new NameValueBean("????????????", "3", false));
//        genderListpe.add(new NameValueBean("?????????", "4", false));
//        setViewDataDictionaryDataByCode(genderListpe, pe);

        initParam();


        return view;

    }

    //        List<NameValueBean> genderListFollowtype = new ArrayList<>();
//        genderListFollowtype.clear();
//        genderListFollowtype.add(new NameValueBean("????????????", "1", false));
//        genderListFollowtype.add(new NameValueBean("???????????????", "2", false));
//        genderListFollowtype.add(new NameValueBean("????????????", "3", false));
//        genderListFollowtype.add(new NameValueBean("?????????", "4", false));
//        setViewDataDictionaryDataByCode(genderListFollowtype, followtype);
    private void initParam() {
        String[] colors = {"????????????", "???????????????", "????????????", "?????????"};
        FiltrateBean fb2 = new FiltrateBean();
        fb2.setTypeName("????????????");
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
//                    followtype.setSingleSelectText("????????????");
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
//                Calendar calendar = Calendar.getInstance(); //????????????
//                calendar.setTime(date);//???????????????????????????
//                calendar.add(Calendar.MONTH, 3);  //????????????3???
//                dBefore = calendar.getTime();   //?????????3????????????
//                SimpleDateFormat endsdf = new SimpleDateFormat("yyyy-MM-dd "); //??????????????????
//                String defaultStartDate = endsdf.format(dBefore);    //????????????3????????????
//                highBloodActivity.getHbFragment3().geteditext().setText(defaultStartDate);
//            }
//
//            break;
//            case 2:
//                if (followtype==null){
//
//                }else {
//                    followtype.setSingleSelectText("???????????????");
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
//                ca.add(Calendar.DATE, 14);// num????????????????????????????????????
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
//                    followtype.setSingleSelectText("????????????");
//                }
//
//                break;
//            case 4:
//                if (followtype==null){
//
//                }else {
//                    followtype.setSingleSelectText("?????????");
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
//                        Util.showToast(getActivity(), "????????????");
//                        WeiboDialogUtils.closeDialog(mDialog);
//                    }else {
////                        datalist.clear();
//                        datalist.addAll(0, sycList.get(sycList.size() - 1).getsMedicalList());
//                        adapter.notifyDataSetChanged();
//                        Util.showToast(getActivity(), "??????????????????");
//                        WeiboDialogUtils.closeDialog(mDialog);
//                    }
//
////                    datalist.add(sycList.get(sycList.size() - 1).getsMedicalList().get(0));
////                    adapter.setNewData(datalist);
////                    recyclerView.setAdapter(adapter);
//
//                    break;
//                case Constant.MSG_LOAD_ERROR:
//                    Util.showToast(getActivity(), "????????????");
//                    WeiboDialogUtils.closeDialog(mDialog);
//                    break;
//            }
//        }
//    };

}
