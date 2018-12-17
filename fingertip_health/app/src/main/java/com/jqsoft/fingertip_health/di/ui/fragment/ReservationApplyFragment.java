//package com.jqsoft.grassroots_civil_administration_platform.di.ui.fragment;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.TextView;
//
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.ReservationApplyingAdapter;
//import com.jqsoft.fingertip_health.bean.ReservationBeanList;
//import com.jqsoft.fingertip_health.di.ui.activity.ReservationServiceActivity;
//import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
//import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
//import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
//import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
//import com.jqsoft.fingertip_health.utils3.util.ListUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//
//public class ReservationApplyFragment extends AbstractFragment {
//    @BindView(R.id.recyclerview)
//    RecyclerView recyclerView;
//    private List<ReservationBeanList> mReservationBeanList = new ArrayList<>();
//    private ReservationApplyingAdapter mAdapter;
//
//    @BindView(R.id.lay_execution_projects_load_failure)
//    View failureView;
//
//    TextView tvFailureView;
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
//        return R.layout.fragment_apply_resavation;
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//
//    public void setPendbean(List<ReservationBeanList> pp ) {
//        mReservationBeanList = pp;
//
//        showSignInfoOverview(mReservationBeanList);
//
//
//    }
//
//
//    public void showSignInfoOverview(List<ReservationBeanList> list ) {
//        if (list != null) {
//            final List<ReservationBeanList> mApplyreservationBeanList= new ArrayList<>();
//
//            mApplyreservationBeanList.clear();
//
//            for(int i=0;i<list.size();i++){
//
//                if(list.get(i).getReservationState().equals("1")){
//                    mApplyreservationBeanList.add(list.get(i));
//                }
//
//            }
//
//
//
//       //  int listSize = getListSizeFromResult(beanList);
//        //    List<SignedResidentDirectoryResultBean> list = getListFromResult(beanList);
//            mAdapter.setNewData(mApplyreservationBeanList);
//            showRecyclerViewOrFailureView(true, ListUtils.isEmpty(mAdapter.getData()));
//  /*
//            mReservationApplyAdapter = new ReservationApplyingAdapter(getActivity(), mApplyreservationBeanList);
//            lv_pend_execu.setAdapter(mReservationApplyAdapter);*/
//
//
//        }
//    }
//
//    private void showRecyclerViewOrFailureView(boolean success, boolean isListEmpty){
//        if (success){
//            if (isListEmpty){
//             //   srl.setVisibility(View.GONE);
//                failureView.setVisibility(View.VISIBLE);
//                tvFailureView.setText(getListEmptyHint());
//            } else {
//             //   srl.setVisibility(View.VISIBLE);
//                failureView.setVisibility(View.GONE);
//            }
//        } else {
//       //     srl.setVisibility(View.GONE);
//            failureView.setVisibility(View.VISIBLE);
//            tvFailureView.setText(getFailureHint());
//
//        }
//    }
//
//    private String getListEmptyHint(){
//        return getResources().getString(R.string.hint_list_empty_reservation_apply);
//    }
//
//    private String getFailureHint(){
//        return getResources().getString(R.string.hint_load_failure);
//    }
//
//
//
//    @Override
//    protected void initView() {
//
//        tvFailureView=(TextView)failureView.findViewById(R.id.tv_load_failure_hint);
//        tvFailureView.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                //onRefresh();
//
//
//                ReservationServiceActivity parentActivity = (ReservationServiceActivity ) getActivity();
//                parentActivity.update();
//            }
//        });
//
//        mAdapter = new ReservationApplyingAdapter(getActivity(), new ArrayList<ReservationBeanList>());
////        easyLoadMoreView = new EasyLoadMoreView();
////        mAdapter.setLoadMoreView(easyLoadMoreView);
//        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//    //    mAdapter.setOnLoadMoreListener(this, recyclerView);
//        mAdapter.setEnableLoadMore(false);
////        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
////        Util.addDividerToRecyclerView(getActivity(), recyclerView, true);
//        recyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new NoDoubleItemClickListener() {
//            @Override
//            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                super.onNoDoubleItemClick(adapter, view, position);
//                ReservationBeanList bean = mAdapter.getItem(position);
////                if (bean!=null){
////                    String toId = Util.trimString(bean.getUid());
////                    getTargetUserInfoAndGotoChatActivity(toId, new IMProcessSuccessListener() {
////                        @Override
////                        public void onIMProcessSuccess(UserInfo userInfo) {
////                            gotoChatActivity(userInfo);
////                        }
////                    });
//////                    gotoChatActivity(toId);
////                } else {
////                    Util.showToast(getActivity(), Constants.HINT_THE_FRIEND_INFO_EMPTY);
////                }
//////                ActivityUtils.launchActivity(Constants.PACKAGE_NAME, Constants.CHAT_ACTIVITY_NAME);
//
//            }
//        });
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
//
//
//
//}
