package com.jqsoft.fingertip_health.di.ui.fragment;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.CenterListAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.CenterListBean;
import com.jqsoft.fingertip_health.di.ui.activity.PersonCollectionActivity;
import com.jqsoft.fingertip_health.di.ui.activity.ReceptionDetailActivity;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;
import static com.jqsoft.fingertip_health.adapter.PolityAdapter.TYPE_MULTIPLE_LINE;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


@SuppressLint("ValidFragment")
public class CenterCollectionFragment extends Fragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.Collection)
    LinearLayout Collection;
    private View rootView;
    View failureView;
    public static final int REQUEST_A = 1;
    List<CenterListBean> CenterList;
    private CenterListAdapter centerAdapter;
    public static CenterCollectionFragment getInstance(String title) {
        CenterCollectionFragment sf = new CenterCollectionFragment();
        return sf;
    }
    public void  centerRefreshInstance(List<CenterListBean> CenterList1){

        centerAdapter.setNewData(CenterList1);
        centerAdapter.notifyDataSetChanged();

        if (CenterList1.size()==0){

            recyclerView.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void LoadData(){

        final BaseQuickAdapter<CenterListBean, BaseViewHolder> centerAdapter = new CenterListAdapter(new ArrayList<CenterListBean>(), TYPE_MULTIPLE_LINE);
        this.centerAdapter = (CenterListAdapter) centerAdapter;
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
        centerAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setAdapter(centerAdapter);
        CenterList =   PersonCollectionActivity.instance.getcenterdata();
        if (CenterList.size()==0){

            recyclerView.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);
        }else {
            centerAdapter.setNewData(CenterList);

            centerAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
                @Override
                public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                    super.onNoDoubleItemClick(adapter, view, position);
                    CenterListBean pb = centerAdapter.getItem(position);
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_centercollection_layout, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        failureView=(View)view.findViewById(R.id.lay_policy_load_failure);

        LoadData();

        failureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonCollectionActivity activity1 = (PersonCollectionActivity) getActivity();
                activity1.onRefresh();
            }
        });
        return view;

    }




}
