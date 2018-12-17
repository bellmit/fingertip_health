package com.jqsoft.fingertip_health.di.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;

import com.jqsoft.fingertip_health.adapter.JzzListAdapter;
import com.jqsoft.fingertip_health.base.Constants;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.JzzListBean;
import com.jqsoft.fingertip_health.di.ui.activity.PersonCollectionActivity;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionDetailActivity;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.adapter.PolityAdapter.TYPE_MULTIPLE_LINE;

//救助站
@SuppressLint("ValidFragment")
public class JzzCollectionFragment extends Fragment {
private RecyclerView recyclerView;
@BindView(R.id.Collection)
    LinearLayout Collection;

    public static final int REQUEST_A = 1;
    View failureView;
    View view;
    private View rootView;
    private JzzListAdapter jzzAdapter;
    List<JzzListBean> JzzList;
    public static JzzCollectionFragment getInstance(String title) {
        JzzCollectionFragment sf = new JzzCollectionFragment();
        return sf;
    }
    public void  RefreshInstance(List<JzzListBean>   Jzzist1){


        jzzAdapter.setNewData(Jzzist1);
        jzzAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void LoadData(){

        final BaseQuickAdapter<JzzListBean, BaseViewHolder> jzzAdapter = new JzzListAdapter(new ArrayList<JzzListBean>(), TYPE_MULTIPLE_LINE);
        this.jzzAdapter = (JzzListAdapter) jzzAdapter;
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
        jzzAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setAdapter(jzzAdapter);
        JzzList =   PersonCollectionActivity.instance.getjzzdata();
      if (JzzList.size()==0){

          recyclerView.setVisibility(View.GONE);
          failureView.setVisibility(View.VISIBLE);
      }else {


          jzzAdapter.setNewData(JzzList);
          jzzAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
              @Override
              public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                  super.onNoDoubleItemClick(adapter, view, position);
                  JzzListBean pb = jzzAdapter.getItem(position);
                  Util.setFromCollection("1");
                  Bundle bundle = new Bundle();
                  bundle.putSerializable(Constants.RELIEF_DETAIL_ACTIVITY_KEY, pb.getCollectionUrl());


                  Intent i = new Intent(getActivity(), ReceptionDetailActivity.class);
                  i.putExtras(bundle);

                  startActivityForResult(i, REQUEST_A);
                Util.gotoActivityWithBundle(getActivity(), ReceptionDetailActivity.class, bundle);
              }
          });
      }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_centercollection_layout, container, false);
        failureView=(View)view.findViewById(R.id.lay_policy_load_failure);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);


        LoadData();
        failureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonCollectionActivity activity1 = (PersonCollectionActivity) getActivity();
                activity1.onRefresh();
            }
        });
//        srl.setOnRefreshListener(this);
//        srl.setRefreshing(false);
        return view;

    }



}
