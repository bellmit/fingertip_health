package com.jqsoft.fingertip_health.di.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.ExecuedProjectAdapter;
import com.jqsoft.fingertip_health.bean.PendExecuBeanList;
import com.jqsoft.fingertip_health.bean.PeopleBaseInfoBean;
import com.jqsoft.fingertip_health.di.ui.activity.HadReserrverServerActivity;
import com.jqsoft.fingertip_health.di.ui.activity.PendExecuActivity;
import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CancelReserverFragment extends AbstractFragment {
    @BindView(R.id.lv_execued)
    ListView lv_execued;
    private List<PendExecuBeanList> pendExecuBeanLists = new ArrayList<>();
    private ExecuedProjectAdapter mExecuProjectAdapter;
    private PeopleBaseInfoBean mpeopleBasebean;

    @BindView(R.id.lay_pend_load_failure)
    View failureView;

    TextView tvFailureView;

    @Override
    protected void loadData() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof FragmentInteraction) {
//            listterner = (FragmentInteraction) context; // 2.2 获取到宿主activity并赋值
//        } else {
//            throw new IllegalArgumentException("activity must implements FragmentInteraction");
//        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hadexecu;
    }

    @Override
    protected void initData() {
       /* serverAdapter = new SignDoctorServerAdapter(getActivity(), datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        serverAdapter.setFragmentInteraction((SignDoctorServerAdapter.FragmentInteraction) getActivity());
        recyclerView.setAdapter(serverAdapter);
        serverAdapter.notifyDataSetChanged();
        serverAdapter.setOnItemClickListener(new SignDoctorServersAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DoctorServerDetails.class);
                intent.putExtra(Constants.SEVER_KEY, datalist.get(position).getKey());
                startActivity(intent);
            }

            @Override
            public void OnItemLongClick(View view, int position) {

            }
        });*/
    }


    public void sethadbean1(List<PendExecuBeanList> pp,PeopleBaseInfoBean mpeopleBasebean) {
        pendExecuBeanLists.clear();
        pendExecuBeanLists = pp;

        showSignInfoOverview(pendExecuBeanLists,mpeopleBasebean);


    }


    public void showSignInfoOverview(final List<PendExecuBeanList> list, PeopleBaseInfoBean mypeopleBasebean) {
        if (list != null) {
            mpeopleBasebean=mypeopleBasebean;
         /*   mpeopleBasebean=mypeopleBasebean;
            final List<PendExecuBeanList> pendExecuBeanLists = new ArrayList<>();
            final  List<PendExecuBeanList> execuProjectBeanLists = new ArrayList<>();

            pendExecuBeanLists.clear();
            execuProjectBeanLists.clear();



            for(int i=0;i<list.size();i++){

                if(!TextUtils.isEmpty(list.get(i).getServicePlanID())){
                    execuProjectBeanLists.add(list.get(i));
                }

            }*/

            if(list.size()==0){
                lv_execued.setAdapter(null);
                showRecyclerViewOrFailureView(true, true);
            }else{
                showRecyclerViewOrFailureView(true, false);
                mExecuProjectAdapter = new ExecuedProjectAdapter(getActivity(), list);
                lv_execued.setAdapter(mExecuProjectAdapter);

            }





            lv_execued.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                   /* Intent intent = new Intent(getActivity(),MotifyExecuActivity.class);
                    intent.putExtra("mpeopleBasebean", (Serializable)mpeopleBasebean);
                    intent.putExtra("PendExecuBeanList", (Serializable)list.get(position));
                    startActivity(intent);*/

                    Intent intent = new Intent(getActivity(),HadReserrverServerActivity.class);
                    intent.putExtra("mpeopleBasebean", (Serializable)mpeopleBasebean);
                    intent.putExtra("PendExecuBeanList", (Serializable)list.get(position));
                    startActivity(intent);
                }


            });


        }
    }



    @Override
    protected void initView() {
        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                //onRefresh();


                PendExecuActivity parentActivity = (PendExecuActivity) getActivity();
                parentActivity.update();
            }
        });

    }


    @Override
    protected void initInject() {

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    public void deleteExecuInfo(){
        /*Map<String, Object> map = ParametersFactory.getdeleteSignInfo("","");
        RequestBody body = Util.getBodyFromMap1(map);
        pendexecuPresenter.maindelete(body);*/
    }


    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
        if (success){
            if (isListEmpty){
                //   srl.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);
                tvFailureView.setText(getListEmptyHint());
            } else {
                //   srl.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
            }
        } else {
            //     srl.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
            tvFailureView.setText(getFailureHint());

        }
    }

    private String getListEmptyHint(){
        return getResources().getString(R.string.hint_list_empty_cancel_reserva);
    }

    private String getFailureHint(){
        return getResources().getString(R.string.hint_load_failure);
    }

}
