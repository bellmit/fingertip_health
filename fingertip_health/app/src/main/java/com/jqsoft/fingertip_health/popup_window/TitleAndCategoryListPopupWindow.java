package com.jqsoft.fingertip_health.popup_window;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.TitleAndValueListTextAdapter;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.TitleAndValueBean;
import com.jqsoft.fingertip_health.helper.FullyLinearLayoutManager;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;

import java.util.List;

/**
 * Created by Administrator on 2018-01-19.
 */

public class TitleAndCategoryListPopupWindow extends PopupWindow {
    private Activity activity;
    private View anchorView;
    private TextView tvTitle;
    private RecyclerView recyclerView;
    private String title;
    private List<TitleAndValueBean> list;
    private TitleAndValueListTextAdapter adapter;
    private TitleAndCategoryItemClickListener listener;

    public TitleAndCategoryListPopupWindow(Activity activity, int width, int height, View anchorView, String title, List<TitleAndValueBean> list) {
        super(activity.getLayoutInflater().inflate(R.layout.layout_title_and_recyclerview_without_srl, null), width, height, true);
        this.activity = activity;
        this.anchorView = anchorView;
        this.title = title;
        this.list = list;

        initView();
    }

    private void initView(){
        tvTitle = (TextView) getContentView().findViewById(R.id.tv_title);
        tvTitle.setText(title);

        recyclerView = (RecyclerView) getContentView().findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new FullyLinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        adapter = new TitleAndValueListTextAdapter(list);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
                TitleAndValueBean bean = (TitleAndValueBean) adapter.getItem(position);
                if (listener != null){
                    listener.titleAndCategoryItemDidClick(bean);
                }
                hide();
            }
        });

        setAnimationStyle(R.style.popup_window_animation);
        setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setOutsideTouchable(true);


    }

    public void show(){
        showAsDropDown(anchorView, 0, 0);

    }

    public void hide(){
        dismiss();
    }

    public void setListener(TitleAndCategoryItemClickListener listener) {
        this.listener = listener;
    }

    public interface TitleAndCategoryItemClickListener {
        public void titleAndCategoryItemDidClick(TitleAndValueBean bean);
    }

}
