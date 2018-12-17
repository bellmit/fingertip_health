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

import com.jqsoft.fingertip_health.adapter.ReplyNoAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.AdviceBean;
import com.jqsoft.fingertip_health.di.ui.activity.AdviceActivity;
import com.jqsoft.fingertip_health.di.ui.activity.AdviceDetailActivity;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jqsoft.fingertip_health.adapter.PolityAdapter.TYPE_MULTIPLE_LINE;


@SuppressLint("ValidFragment")
public class NoReplyFragment extends Fragment implements   BaseQuickAdapter.RequestLoadMoreListener {
private RecyclerView recyclerView;
@BindView(R.id.Collection)
    LinearLayout Collection;

    public static final int REQUEST_A = 1;
    View failureView;
    View view;
    private View rootView;
    private ReplyNoAdapter replyAdapter;
    List<AdviceBean> NoReplyList;
    private static  NoReplyFragment instance=null;
    public static NoReplyFragment getInstance(String title) {
        if (instance == null) {
            instance = new NoReplyFragment();
        }
        return instance;

    }
    public ReplyNoAdapter  getadpater(){
    return  replyAdapter;

    }
    public void  addInstance(List<AdviceBean>   HelpList1){
        if (HelpList1==null){
//                replyAdapter.setNewData(null);
            recyclerView.setVisibility(View.GONE);
            failureView.setVisibility(View.VISIBLE);

        }else {
            replyAdapter.addData(HelpList1);

            recyclerView.setVisibility(View.VISIBLE);
            failureView.setVisibility(View.GONE);
            replyAdapter.notifyDataSetChanged();
        }



    }

    public void  RefreshInstance(List<AdviceBean>   HelpList1){
            if (HelpList1==null){
//                replyAdapter.setNewData(null);
                recyclerView.setVisibility(View.GONE);
                failureView.setVisibility(View.VISIBLE);

            }else {
                replyAdapter.setNewData(HelpList1);

                recyclerView.setVisibility(View.VISIBLE);
                failureView.setVisibility(View.GONE);
                replyAdapter.notifyDataSetChanged();
            }



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void LoadData(){

        final BaseQuickAdapter<AdviceBean, BaseViewHolder> replyAdapter = new ReplyNoAdapter(new ArrayList<AdviceBean>(), TYPE_MULTIPLE_LINE);
        this.replyAdapter = (ReplyNoAdapter) replyAdapter;
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
        replyAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        replyAdapter.setOnLoadMoreListener(this, recyclerView);
        recyclerView.setAdapter(replyAdapter);


        NoReplyList =   AdviceActivity.instance.getNoReplyList();
        replyAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
                AdviceBean pb = replyAdapter.getItem(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.Advice_ACTIVITY_KEY, pb.getConNo());
                bundle.putSerializable(Constants.Adviceisreply_ACTIVITY_KEY, pb.getIsReply());
             Intent i = new Intent(getActivity(), AdviceDetailActivity.class);
                i.putExtras(bundle);

                startActivityForResult(i, REQUEST_A);

//                Util.gotoActivityWithBundle(getActivity(), AdviceDetailActivity.class, bundle);
//                Util.gotoActivityWithBundle(getActivity(), AdviceDetailActivity.class, bundle);
            }
        });
if (null==NoReplyList){
    recyclerView.setVisibility(View.GONE);
    failureView.setVisibility(View.VISIBLE);
}else {

      if (NoReplyList.size()==0){

          recyclerView.setVisibility(View.GONE);
          failureView.setVisibility(View.VISIBLE);
      }else {


          replyAdapter.setNewData(NoReplyList);

      }}


        replyAdapter.setEnableLoadMore(true);
        replyAdapter.loadMoreComplete();
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
                AdviceActivity activity1 = (AdviceActivity) getActivity();
                activity1.onRefresh();
            }
        });
//        srl.setOnRefreshListener(this);
//        srl.setRefreshing(false);
        return view;

    }


    @Override
    public void onLoadMoreRequested() {
        AdviceActivity activity1 = (AdviceActivity) getActivity();
        activity1. noReplyloadmore();
    }
}
