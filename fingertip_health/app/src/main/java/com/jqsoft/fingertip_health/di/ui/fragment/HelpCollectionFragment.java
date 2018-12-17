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
import com.jqsoft.fingertip_health.adapter.HelpListAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.HelpListBean;
import com.jqsoft.fingertip_health.di.ui.activity.PersonCollectionActivity;
import com.jqsoft.fingertip_health.di.ui.activity.ReliefItemActivity;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.adapter.PolityAdapter.TYPE_MULTIPLE_LINE;


@SuppressLint("ValidFragment")
public class HelpCollectionFragment extends Fragment {
private RecyclerView recyclerView;
@BindView(R.id.Collection)
    LinearLayout Collection;

    public static final int REQUEST_A = 1;
    View failureView;
    View view;
    private View rootView;
    private HelpListAdapter helpAdapter;
    List<HelpListBean> HelpList;
    public static HelpCollectionFragment getInstance(String title) {
        HelpCollectionFragment sf = new HelpCollectionFragment();
        return sf;
    }
    public void  RefreshInstance(List<HelpListBean>   HelpList1){


        helpAdapter.setNewData(HelpList1);
        helpAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void LoadData(){

        final BaseQuickAdapter<HelpListBean, BaseViewHolder> helpAdapter = new HelpListAdapter(new ArrayList<HelpListBean>(), TYPE_MULTIPLE_LINE);
        this.helpAdapter = (HelpListAdapter) helpAdapter;
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
        helpAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setAdapter(helpAdapter);
        HelpList =   PersonCollectionActivity.instance.gethelpdata();
      if (HelpList.size()==0){

          recyclerView.setVisibility(View.GONE);
          failureView.setVisibility(View.VISIBLE);
      }else {


          helpAdapter.setNewData(HelpList);
          helpAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
              @Override
              public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                  super.onNoDoubleItemClick(adapter, view, position);
                  HelpListBean pb = helpAdapter.getItem(position);
                  Util.setFromCollection("1");
                  Bundle bundle = new Bundle();
                  bundle.putSerializable(Constants.RELIEF_ITEM_ACTIVITY_KEY, pb.getCollectionUrl());

                  Intent i = new Intent(getActivity(), ReliefItemActivity.class);
                  i.putExtras(bundle);

                  startActivityForResult(i, REQUEST_A);
                  //  Util.gotoActivityWithBundle(getActivity(), ReliefItemActivity.class, bundle);
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
