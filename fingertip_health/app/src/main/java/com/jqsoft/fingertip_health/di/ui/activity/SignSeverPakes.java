//package com.jqsoft.grassroots_civil_administration_platform.di.ui.activity;
//
//import android.content.Context;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.RadioButton;
//import android.widget.Toast;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.ParametersFactory;
//import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
//import com.jqsoft.fingertip_health.bean.base.HttpResultBaseBean;
//import com.jqsoft.fingertip_health.di.contract.SignSeverPakesContract;
//import com.jqsoft.fingertip_health.di.module.SignSeverPakesActivityModule;
//import com.jqsoft.fingertip_health.di.presenter.SignDoctorServerPresenter;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.PopWindowListAdapter;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.SignDoctorServerAdapter;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.SignDoctorServersAdapter;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.SignSeverPakesAdapter;
//import com.jqsoft.fingertip_health.di_app.DaggerApplication;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.view.CustomPopWindow;
//import com.jqsoft.fingertip_health.view.DividerItemDecoration;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import butterknife.BindView;
//import info.hoang8f.android.segmented.SegmentedGroup;
//import okhttp3.RequestBody;
//
///**
// * Created by Jerry on 2017/6/26.
// */
//
//public class SignSeverPakes extends AbstractActivity implements SignSeverPakesContract.View {
//    Context context;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.segmented2)
//    SegmentedGroup segmented;
//    @BindView(R.id.btn_basic)
//    RadioButton btn_basic;
//    @BindView(R.id.btn_primary)
//    RadioButton btn_primary;
//    @BindView(R.id.btn_mid)
//    RadioButton btn_mid;
//    @BindView(R.id.btn_high)
//    RadioButton btn_high;
//    @BindView(R.id.otherpake)
//    RadioButton btn_otherpake;
//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerView;
//    @BindView(R.id.bottom_lay)
//    LinearLayout bottom_btn;
//
//    @BindView(R.id.srl)
//    SwipeRefreshLayout srl;
//    @Inject
//    SignDoctorServerPresenter doctorServerPresenter;
//    private SignDoctorServerAdapter serverAdapter;
//    private SignDoctorServersAdapter myAdapter; //正式数据
//    private PopWindowListAdapter popWindowListAdapter;
//    private SignSeverPakesAdapter mAdapter;
//    private ArrayList<SignSeverPakesBeanList> datalist = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist1 = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist2 = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist3 = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist4 = new ArrayList<>();
//    private ArrayList<SignSeverPakesBeanList> datalist5 = new ArrayList<>();
//    private Map<String, ArrayList<SignSeverPakesBeanList>> maplist = new HashMap<>();
//    private int tabtype;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_signserverpake;
//
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initView() {
//        setToolBar(toolbar, Constants.EMPTY_STRING);
//
//        btn_basic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                serverAdapter = new SignDoctorServerAdapter(getApplicationContext(), datalist1);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerView.setAdapter(serverAdapter);
//                serverAdapter.notifyDataSetChanged();
//            }
//        });
//        btn_primary.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                serverAdapter = new SignDoctorServerAdapter(getApplicationContext(), datalist2);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerView.setAdapter(serverAdapter);
//                serverAdapter.notifyDataSetChanged();
//            }
//        });
//        btn_mid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                serverAdapter = new SignDoctorServerAdapter(getApplicationContext(), datalist3);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerView.setAdapter(serverAdapter);
//                serverAdapter.notifyDataSetChanged();
//            }
//        });
//
//        btn_high.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                serverAdapter = new SignDoctorServerAdapter(getApplicationContext(), datalist4);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerView.setAdapter(serverAdapter);
//                serverAdapter.notifyDataSetChanged();
//            }
//        });
//        btn_otherpake.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                serverAdapter = new SignDoctorServerAdapter(getApplicationContext(), datalist5);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerView.setAdapter(serverAdapter);
//                serverAdapter.notifyDataSetChanged();
//            }
//        });
//    }
//
//    @Override
//    protected void loadData() {
////        List<String> data=new ArrayList<>();
////        // String[] data = new String[20];
////        for (int i = 0; i < 20; i++) {
////            //data[i] = "总金额:"+100+"元; 减免金额:"+100+"元; 医保补偿金额:"+100+"元; 自付金额:"+44+"元" + i;
////            data.add("总金额:"+100+"元; 减免金额:"+100+"元; 医保补偿金额:"+100+"元; 自付金额:"+44+"元" );
////        }
////        myAdapter = new SignDoctorServersAdapter(this,data);
////        //serverAdapter = new SignDoctorServerAdapter(this, data);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
////        recyclerView.setAdapter(myAdapter);
//        Map<String, String> map = ParametersFactory.getSignDoctorServerList(this);
//        RequestBody body = Util.getBodyFromMap(map);
//        doctorServerPresenter.main(body);
//        bottom_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                showPopListView();
//
//            }
//        });
//
//    }
//
//    public void dividedata() {
//        for (int i = 0; i < datalist.size(); i++) {
//            String nhcompensateProjName = Util.getDecodedBase64String(datalist.get(i).getNhcompensateProjName());
//            if (nhcompensateProjName.equals("1")) {
//                datalist1.add(datalist.get(i));
//            } else if (nhcompensateProjName.equals("2")) {
//                datalist2.add(datalist.get(i));
//            } else if (nhcompensateProjName.equals("3")) {
//                datalist3.add(datalist.get(i));
//            } else if (nhcompensateProjName.equals("4")) {
//                datalist4.add(datalist.get(i));
//            } else  {
//                datalist5.add(datalist.get(i));
//            }
//
//        }
//
//    }
//
//    private CustomPopWindow mListPopWindow;
//
//    private void showPopListView() {
//        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_list, null);
//        //处理popWindow 显示内容
//     //   handleListView(contentView);
//        //创建并显示popWindow
//        mListPopWindow = new CustomPopWindow.PopupWindowBuilder(this)
//                .enableBackgroundDark(false) //弹出popWindow时，背景是否变暗
//                .setBgDarkAlpha(0.6f) // 控制亮度
//                .setOnDissmissListener(new PopupWindow.OnDismissListener() {
//                    @Override
//                    public void onDismiss() {
//                        Log.e("TAG", "onDismiss");
//                    }
//                })
//                .setView(contentView)
//                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
//                .create()
//                .showAsDropDown(bottom_btn, 0, 3);
//    }
//
////    private void handleListView(View contentView) {
////        ListView listView = (ListView) contentView.findViewById(R.id.poplistview);
////        popWindowListAdapter = new PopWindowListAdapter(getApplicationContext(), mockData());
////        listView.setAdapter(popWindowListAdapter);
////        popWindowListAdapter.notifyDataSetChanged();
////
////
////    }
//
//    private List<String> mockData() {
//        List<String> data = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            data.add("Item:" + i);
//        }
//
//        return data;
//    }
//
//    @Override
//    protected void initInject() {
//        DaggerApplication.get(this)
//                .getAppComponent()
//                .addserverpakes(new SignSeverPakesActivityModule(this))
//                .inject(this);
//    }
//
//    @Override
//    public void onLoadListSuccess(HttpResultBaseBean<List<SignSeverPakesBeanList>> bean) {
//        datalist.addAll(bean.getData());
//        dividedata();
////        String[] data = new String[20];
////        for (int i = 0; i < 20; i++) {
////            data[i] = "item" + i;
////        }
//        serverAdapter = new SignDoctorServerAdapter(this, datalist1);
//       // myAdapter = new SignDoctorServersAdapter(this, datalist1);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(serverAdapter);
//
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
//        myAdapter.setOnItemClickListener(new SignDoctorServersAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, int position) {
//                Toast.makeText(getApplicationContext(), "111", Toast.LENGTH_SHORT).show();
//                //  view.setBackgroundColor(Color.parseColor("#F5F5DC"));
//                //  view.findViewById(R.id.select_btn).setBackgroundColor(Color.parseColor("#F5F5DC"));
//            }
//
//            @Override
//            public void OnItemLongClick(View view, int position) {
//                Toast.makeText(getApplicationContext(), "676767", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
//
//    @Override
//    public void onLoadMoreListSuccess(HttpResultBaseBean<List<SignSeverPakesBeanList>> bean) {
//
//    }
//
//    @Override
//    public void onLoadListFailure(String message) {
//
//    }
//}
