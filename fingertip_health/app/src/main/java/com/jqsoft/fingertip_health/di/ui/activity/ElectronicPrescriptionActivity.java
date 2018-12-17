package com.jqsoft.fingertip_health.di.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.PopWindowListAdapter;
import com.jqsoft.fingertip_health.adapter.PopWindowListAdapter1;
import com.jqsoft.fingertip_health.adapter.PopWindowListAdapter2;
import com.jqsoft.fingertip_health.adapter.statistics.PopWindowListCaoyaoListAdapter;
import com.jqsoft.fingertip_health.adapter.statistics.PopWindowListDrugListAdapter;
import com.jqsoft.fingertip_health.adapter.statistics.PopWindowListTreatListAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.DrugInfo;
import com.jqsoft.fingertip_health.bean.TreatdirectoryBean;
import com.jqsoft.fingertip_health.bean.fingertip.OutpatientBeanForFingertip;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.RecipeFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.TreatProjectFragment;
import com.jqsoft.fingertip_health.di.ui.fragment.HarbelFragment;
import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.FragmentAdapters;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.view.CustomPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/9/5.
 */

public class ElectronicPrescriptionActivity extends AbstractActivity {
    @BindView(R.id.ll_back)
    LinearLayout ll_back;
    @BindView((R.id.viewpager))
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.bottom_lay)
    LinearLayout bottom_lay;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.sure_btn)
    LinearLayout sure_btn;
    @BindView(R.id.bgtb)
    TextView vt_bg;
    @BindView(R.id.allcount0)
    TextView allcount0;
    private int sum = 0;
    private ArrayList<DrugInfo> drugInfoArrayList = new ArrayList<>();
    private ArrayList<DrugInfo> caoyaoArrayList = new ArrayList<>();
    private ArrayList<TreatdirectoryBean> treatdirectoryList = new ArrayList<>();

    private FragmentAdapters fragmentAdapters;
    private String carNum, allmenony;
    private PopWindowListTreatListAdapter zhenduanAdaper;
    private PopWindowListCaoyaoListAdapter caoyaoAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.electronic_prescribing_layout;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initfragment();
//        carNum = getIntent().getStringExtra("carNum");
//        allmenony = getIntent().getStringExtra("allmenony");
//        int carnum = 0;
//        try {
//            carnum = Integer.valueOf(carNum);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }

        Bundle MarsBuddle = getIntent().getExtras();
        drugInfoArrayList = (ArrayList<DrugInfo>) MarsBuddle.getSerializable("drugInfoArrayList");
        treatdirectoryList = (ArrayList<TreatdirectoryBean>) MarsBuddle.getSerializable("treatdirectoryList");
        caoyaoArrayList = (ArrayList<DrugInfo>) MarsBuddle.getSerializable("caoyaoArrayList");
        Double A = 0.00;
        Double B = 0.00;
        Double C = 0.00;
        int numa = 0;
        int numb = 0;
        int numc = 0;
        if (drugInfoArrayList.size() > 0) {
            for (int i = 0; i < drugInfoArrayList.size(); i++) {
                double nn = Double.parseDouble(drugInfoArrayList.get(i).getPriceSale());
                double menony = nn * (drugInfoArrayList.get(i).getChargeFrequency());
                numa = numa + drugInfoArrayList.get(i).getChargeFrequency();
                A = A + menony;
            }
        }
        if (treatdirectoryList.size() > 0) {
            for (int i = 0; i < treatdirectoryList.size(); i++) {
                double nn = Double.parseDouble(treatdirectoryList.get(i).getFeeStandard());
                double menony = nn * (treatdirectoryList.get(i).getChargeFrequency());
                numb = numb + drugInfoArrayList.get(i).getChargeFrequency();
                B = B + menony;
            }
        }
        if (caoyaoArrayList.size() > 0) {
            for (int i = 0; i < caoyaoArrayList.size(); i++) {
                double nn = Double.parseDouble(caoyaoArrayList.get(i).getPrice());
                double menony = nn * (caoyaoArrayList.get(i).getChargeFrequency());
                numc = numc + drugInfoArrayList.get(i).getChargeFrequency();
                C = C + menony;
            }
        }

        if (numa + numb + numc > 0) {
            sum = sum + numa + numb + numc;
            bottom_lay.setVisibility(View.VISIBLE);
            tv_number.setText(sum + "");
            allcount0.setText(A + B + C + "");
        }

        sure_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   if (drugInfoArrayList.size() > 0 || treatdirectoryList.size() > 0) {
                Intent intent = getIntent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("drugInfoArrayList", drugInfoArrayList);
                bundle.putSerializable("treatdirectoryList", treatdirectoryList);
                bundle.putSerializable("caoyaoArrayList", caoyaoArrayList);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();


//                    Activity activity = Util.scanForActivity(ElectronicPrescriptionActivity.this);
//                    if (activity instanceof OutpatientChargesActivity) {
//                        OutpatientChargesActivity oca = (OutpatientChargesActivity) activity;
//                        oca.handleElectronicInfo(drugInfoArrayList,treatdirectoryList);
//                    }
//                //    finish();
                //   }

            }
        });
        bottom_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopListView();
                //showPopListView2();
                vt_bg.setVisibility(View.VISIBLE);
                vt_bg.getBackground().setAlpha(100);
            }
        });
    }

    @Override
    protected void loadData() {

    }

    public void initfragment() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        //初始化TabLayout的title

        mTabLayout.addTab(mTabLayout.newTab().setText("处方信息"));
        mTabLayout.addTab(mTabLayout.newTab().setText("诊疗项目"));
        mTabLayout.addTab(mTabLayout.newTab().setText("草药处方"));

        List<String> titles = new ArrayList<>();
        titles.add("处方信息");
        titles.add("诊疗项目");
        titles.add("草药处方");

        //初始化ViewPager的数据集
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecipeFragment());//
        fragments.add(new TreatProjectFragment());//
        fragments.add(new HarbelFragment());//


        //创建ViewPager的adapter
        mViewPager.setOffscreenPageLimit(Constants.VIEW_PAGER_OFF_SCREEN_NUMBER);
        fragmentAdapters = new FragmentAdapters(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(fragmentAdapters);
        //千万别忘了，关联TabLayout与ViewPager
        //同时也要覆写PagerAdapter的getPageTitle方法，否则Tab没有title
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(fragmentAdapters);

    }

    public void handleDrugInfo(DrugInfo bean) {
        if (bean != null) {
            bottom_lay.setVisibility(View.VISIBLE);
            sum++;
            tv_number.setText(sum + "");
            drugInfoArrayList.add(bean);
            try {
                if (bean.getMpu().equals("1")) {
                    double menony = Double.parseDouble(allcount0.getText().toString());
                    double nn = Double.parseDouble(bean.getPriceSale());
                    allcount0.setText(nn + menony + "");
                } else {
                    double menony = Double.parseDouble(allcount0.getText().toString());
                    double nn = Double.parseDouble(bean.getPrice());
                    allcount0.setText(nn + menony + "");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

    }

    public void PopWindowhandleDrugInfo(DrugInfo bean) {
        if (bean != null) {
            bottom_lay.setVisibility(View.VISIBLE);
            sum++;
            tv_number.setText(sum + "");
            try {
                if (bean.getMpu().equals("1")) {
                    double menony = Double.parseDouble(allcount0.getText().toString());
                    double nn = Double.parseDouble(bean.getPriceSale());
                    allcount0.setText(nn + menony + "");
                } else {
                    double menony = Double.parseDouble(allcount0.getText().toString());
                    double nn = Double.parseDouble(bean.getPrice());
                    allcount0.setText(nn + menony + "");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }

    }

    public void handleCaoyaoDrugInfo(DrugInfo bean) {
        if (bean != null) {
            bottom_lay.setVisibility(View.VISIBLE);
            sum++;
            tv_number.setText(sum + "");
            caoyaoArrayList.add(bean);
            try {
                if (bean.getMpu().equals("1")) {
                    double menony = Double.parseDouble(allcount0.getText().toString());
                    double nn = (Double.parseDouble(bean.getPriceSale()));
                    allcount0.setText(nn + menony + "");
                } else {
                    double menony = Double.parseDouble(allcount0.getText().toString());
                    double nn = (Double.parseDouble(bean.getPrice()));
                    allcount0.setText(nn + menony + "");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

    }

    public void PopwindhandleCaoyaoDrugInfo(DrugInfo bean) {
        if (bean != null) {
            bottom_lay.setVisibility(View.VISIBLE);
            sum++;
            tv_number.setText(sum + "");

            try {
                if (bean.getMpu().equals("1")) {
                    double menony = Double.parseDouble(allcount0.getText().toString());
                    double nn = Double.parseDouble(bean.getPriceSale());
                    allcount0.setText(nn + menony + "");
                } else {
                    double menony = Double.parseDouble(allcount0.getText().toString());
                    double nn = Double.parseDouble(bean.getPrice());
                    allcount0.setText(nn + menony + "");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

    }
//    public void detItem() {
//        tv_number.setText(sum + "");
//    }

    public void detItemcount(int position, int types, String price) {
        sum--;
        tv_number.setText(sum + "");
        if (types == 1) {
            drugInfoArrayList.remove(position);
            popWindowListAdapter.notifyDataSetChanged();
        } else if (types == 2) {
            treatdirectoryList.remove(position);
            zhenduanAdaper.notifyDataSetChanged();
        } else if (types == 3) {
            caoyaoArrayList.remove(position);
            caoyaoAdapter.notifyDataSetChanged();
        }
        try {
            double tv_price = Double.parseDouble(price);
            double count = Double.parseDouble(allcount0.getText().toString());
            allcount0.setText(count - tv_price + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (sum < 1) {
            setbottom();
        }

    }

    public void detItemcount1(ArrayList<TreatdirectoryBean> itemlist, int a) {
        sum--;
        tv_number.setText(sum + "");
        if (sum < 1) {
            setbottom();
        }


    }

    public void handleTreatProInfo(TreatdirectoryBean bean) {
        if (bean != null) {
            bottom_lay.setVisibility(View.VISIBLE);
            sum = sum + bean.getChargeFrequency();
            tv_number.setText(sum + "");
            treatdirectoryList.add(bean);
            try {
                double mm = bean.getChargeFrequency() * (Double.parseDouble(bean.getFeeStandard()));
                double menony = Double.parseDouble(allcount0.getText().toString());
                allcount0.setText(mm + menony + "");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public void PopwindowhandleTreatProInfo(TreatdirectoryBean bean) {
        if (bean != null) {
            bottom_lay.setVisibility(View.VISIBLE);
            sum++;
            tv_number.setText(sum + "");
            try {
                double mm = Double.parseDouble(bean.getFeeStandard());
                double menony = Double.parseDouble(allcount0.getText().toString());
                allcount0.setText(mm + menony + "");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private CustomPopWindow mListPopWindow;

    private void showPopListView() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_list, null);
        //处理popWindow 显示内容
        handleListView(contentView);
        //创建并显示popWindow

        if (Build.VERSION.SDK_INT >= 24) {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            int screenWidth = dm.widthPixels;
            int screenHeight = dm.heightPixels;


            if(screenWidth==720 && screenHeight==1360){
                    mListPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                            .enableBackgroundDark(false) //弹出popWindow时，背景是否变暗
                            .setBgDarkAlpha(0.6f) // 控制亮度
                            .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    vt_bg.setVisibility(View.GONE);
                                }
                            })
                            .setView(contentView)
                            .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                            .create()


                            .showAsDropDown1(bottom_lay);
                }else {
                    mListPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                            .enableBackgroundDark(false) //弹出popWindow时，背景是否变暗
                            .setBgDarkAlpha(0.6f) // 控制亮度
                            .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    vt_bg.setVisibility(View.GONE);
                                }
                            })
                            .setView(contentView)
                            .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                            .create()


                            .showAsDropDown(bottom_lay);
                }






        } else {
            mListPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
                    .enableBackgroundDark(false) //弹出popWindow时，背景是否变暗
                    .setBgDarkAlpha(0.6f) // 控制亮度
                    .setOnDissmissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            vt_bg.setVisibility(View.GONE);
                        }
                    })
                    .setView(contentView)
                    .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                    .create()
                    .showAsDropDown(bottom_lay, 0, 3);
        }


    }

    PopWindowListDrugListAdapter popWindowListAdapter;

    private void handleListView(View contentView) {

        RecyclerView listView = (RecyclerView) contentView.findViewById(R.id.poplistview);
        RecyclerView listView1 = (RecyclerView) contentView.findViewById(R.id.poplistview1);
        RecyclerView listView2 = (RecyclerView) contentView.findViewById(R.id.poplistview2);
        TextView detaddlist = (TextView) contentView.findViewById(R.id.detaddlist);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutmanager);
        LinearLayoutManager layoutmanager1 = new LinearLayoutManager(this);
        listView1.setLayoutManager(layoutmanager1);
        LinearLayoutManager layoutmanager2 = new LinearLayoutManager(this);
        listView2.setLayoutManager(layoutmanager2);


        popWindowListAdapter = new PopWindowListDrugListAdapter(drugInfoArrayList, this);
        zhenduanAdaper = new PopWindowListTreatListAdapter(treatdirectoryList, this);
        caoyaoAdapter = new PopWindowListCaoyaoListAdapter(caoyaoArrayList, this);
        listView.setAdapter(popWindowListAdapter);
        listView1.setAdapter(zhenduanAdaper);
        listView2.setAdapter(caoyaoAdapter);
        popWindowListAdapter.notifyDataSetChanged();
        zhenduanAdaper.notifyDataSetChanged();
        caoyaoAdapter.notifyDataSetChanged();
        //   setTotoalMenony(drugInfoArrayList, caoyaoArrayList, treatdirectoryList);
        detaddlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                List<String> listKey = new ArrayList<String>();
//                for(int i =0;i<drugInfoArrayList.size();i++){
//                    String deleteKey = Util.trimString(drugInfoArrayList.get(i).getId());
//                    listKey.add(deleteKey);
//                }
//                RxBus.getDefault().post(Constants.EVENT_TYPE_SIGN_DOCTOR_SERVER_PACKAGE_DELETE_MESSAGE,
//                        listKey);
                drugInfoArrayList.clear();
                treatdirectoryList.clear();
                caoyaoArrayList.clear();
                zhenduanAdaper.notifyDataSetChanged();
                caoyaoAdapter.notifyDataSetChanged();
                popWindowListAdapter.notifyDataSetChanged();
                sum = 0;
                allcount0.setText("0");
                setbottom();
            }
        });
    }

    public void setTotoalMenony(ArrayList<DrugInfo> drugInfoArrayList, ArrayList<DrugInfo> caoyaoArrayList, ArrayList<TreatdirectoryBean> treatdirectoryList) {
        double treatMenony = 0.00;
        for (int i = 0; i < treatdirectoryList.size(); i++) {
            double tempmenony = treatdirectoryList.get(i).getChargeFrequency() * (Double.parseDouble(treatdirectoryList.get(i).getFeeStandard()));
            treatMenony = treatMenony + treatMenony;
        }

    }

    public void setbottom() {
        mListPopWindow.dissmiss();
        bottom_lay.setVisibility(View.GONE);
    }

    public void itemdrug_caculate_jian(String price) {
        sum--;
        tv_number.setText(sum + "");
        try {
            double tv_price = Double.parseDouble(price);
            double count = Double.parseDouble(allcount0.getText().toString());
            allcount0.setText(count - tv_price + "");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
