package com.jqsoft.fingertip_health.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.HospitalTypeFirstLevelAdapter;
import com.jqsoft.fingertip_health.adapter.HospitalTypeSecondLevelAdapter;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.HospitalTypeBean;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.HospitalTypeSelectListener;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-05-19.
 */

public class HospitalTypePopupWindow extends PopupWindow {
    private View anchorView;
    private RecyclerView firstLevelRecyclerView, secondLevelRecyclerView;

    private HospitalTypeFirstLevelAdapter firstLevelAdapter;
    private HospitalTypeSecondLevelAdapter secondLevelAdapter;

    private List<HospitalTypeBean> list;

    private HospitalTypeSelectListener listener;

    private Activity activity;

    public HospitalTypePopupWindow(Activity activity, int width, int height, View anchorView, List<HospitalTypeBean> list, HospitalTypeSelectListener listener) {
        super(activity.getLayoutInflater().inflate(R.layout.layout_hospital_type_popup, null), width, height, true);
        this.activity=activity;
        this.list=list;
        this.anchorView=anchorView;
        this.listener=listener;
        initView();
    }

    public void initView(){
        firstLevelRecyclerView=(RecyclerView)getContentView().findViewById(R.id.rv_first_level);
        secondLevelRecyclerView= (RecyclerView) getContentView().findViewById(R.id.rv_second_level);
        firstLevelRecyclerView.setLayoutManager(new FullyLinearLayoutManager(activity));
        secondLevelRecyclerView.setLayoutManager(new FullyLinearLayoutManager(activity));
        firstLevelAdapter=new HospitalTypeFirstLevelAdapter(list);
        secondLevelAdapter = new HospitalTypeSecondLevelAdapter(new ArrayList<HospitalTypeBean>());
        firstLevelRecyclerView.setAdapter(firstLevelAdapter);
        secondLevelRecyclerView.setAdapter(secondLevelAdapter);

        firstLevelRecyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        secondLevelRecyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));

        setAnimationStyle(R.style.popup_window_animation);
        setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setOutsideTouchable(true);

        showLeftView();
        update();
    }

    public void showLeftView(){
        firstLevelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HospitalTypeBean leftBean = list.get(position);
                List<HospitalTypeBean> leftChildren = leftBean.getChildren();
                if (ListUtils.isEmpty(leftChildren)){
                    callListenerCallback(leftBean);
//                    RxBus.getDefault().post(Constants.EVENT_TYPE_DID_SELECT_HOSPITAL_TYPE, leftBean);
                    dismiss();
                    showRightView(new ArrayList<HospitalTypeBean>());
                } else {
                   showRightView(leftChildren);
                }
            }
        });
        firstLevelAdapter.notifyDataSetChanged();
        if (ListUtils.getSize(list)>0){
            List<HospitalTypeBean> rightList = list.get(0).getChildren();
            showRightView(rightList);
        }
    }

    public void showRightView(final List<HospitalTypeBean> childrenList){
        List<HospitalTypeBean> lst = childrenList==null?new ArrayList<HospitalTypeBean>():childrenList;
        secondLevelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HospitalTypeBean item = childrenList.get(position);
                List<HospitalTypeBean> children = item.getChildren();
                if (ListUtils.isEmpty(children)){
                    callListenerCallback(item);
//                    RxBus.getDefault().post(Constants.EVENT_TYPE_DID_SELECT_HOSPITAL_TYPE, item);
                } else {
                    callListenerCallback(item);
//                    RxBus.getDefault().post(Constants.EVENT_TYPE_DID_SELECT_HOSPITAL_TYPE, item);
                }
                dismiss();
            }
        });
        secondLevelAdapter.setNewData(lst);
    }

    public void callListenerCallback(HospitalTypeBean bean){
        if (listener!=null){
            listener.didSelectHospitalType(bean);
        }
    }

    public void show(){
        showAsDropDown(anchorView, 0, Constants.POPUP_WINDOW_Y_OFFSET);

    }
}
