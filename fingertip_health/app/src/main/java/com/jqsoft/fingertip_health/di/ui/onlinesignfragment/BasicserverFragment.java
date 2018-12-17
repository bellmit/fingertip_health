//package com.jqsoft.grassroots_civil_administration_platform.di.ui.onlinesignfragment;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.view.View;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.bean.SignSeverPakesBeanList;
//import com.jqsoft.fingertip_health.di.ui.activity.DoctorServerDetails;
//import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.SignDoctorServerAdapter;
//import com.jqsoft.fingertip_health.di.ui.onlinesignadapter.SignDoctorServersAdapter;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//
//public class BasicserverFragment extends AbstractFragment {
//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerView;
//    private ArrayList<SignSeverPakesBeanList> datalist = new ArrayList<>();
//    public SignDoctorServerAdapter serverAdapter;
//
//
//    @Override
//    protected void loadData() {
//
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
////        if (context instanceof FragmentInteraction) {
////            listterner = (FragmentInteraction) context; // 2.2 获取到宿主activity并赋值
////        } else {
////            throw new IllegalArgumentException("activity must implements FragmentInteraction");
////        }
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.basicseverdoctorfragment;
//    }
//
//    @Override
//    protected void initData() {
//        serverAdapter = new SignDoctorServerAdapter(getActivity(), datalist);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        serverAdapter.setFragmentInteraction((SignDoctorServerAdapter.FragmentInteraction) getActivity());
//        recyclerView.setAdapter(serverAdapter);
//        serverAdapter.notifyDataSetChanged();
//        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
//        serverAdapter.setOnItemClickListener(new SignDoctorServersAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View view, int position) {
//                Intent intent = new Intent(getActivity(), DoctorServerDetails.class);
//                intent.putExtra(Constants.SEVER_KEY, datalist.get(position).getKey());
//                startActivity(intent);
//            }
//
//            @Override
//            public void OnItemLongClick(View view, int position) {
//
//            }
//        });
//    }
//
//    public ArrayList<SignSeverPakesBeanList> setDatalist(ArrayList<SignSeverPakesBeanList> list) {
//        if (list.size() > 0) {
//            for (int i = 0; i < list.size(); i++) {
//                if (TextUtils.isEmpty(list.get(i).getNhcompensateProjName())) {
//                    list.get(i).setNhcompensateProjName("1");
//                }
//            }
//            datalist.addAll(list);
//        }
//        serverAdapter.setNewData(datalist);
////        serverAdapter.notifyDataSetChanged();
//        return datalist;
//    }
//
//    @Override
//    protected void initView() {
//
//    }
//
//
//    @Override
//    protected void initInject() {
//
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        serverAdapter.unregisterDeleteAction();
//    }
//}
