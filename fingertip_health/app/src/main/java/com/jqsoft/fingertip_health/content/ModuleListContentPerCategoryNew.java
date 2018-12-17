package com.jqsoft.fingertip_health.content;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.ImageAndTextVerticalAdapterNew;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.ImageAndTextBean;
import com.jqsoft.fingertip_health.helper.FullyGridLayoutManagerSmoothScroll;
import com.jqsoft.fingertip_health.listener.NoDoubleItemClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.utils.LogUtil;

import java.util.List;

/**
 * 某一类模块列表
 */

public class ModuleListContentPerCategoryNew {
    private Context context;
    private View view;
    public ModuleListContentPerCategoryNew(Context context) {
        this.context=context;
    }

    public void initView(String category, List<ImageAndTextBean> list){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//        View   dialog = inflater.inflate(R.layout.layout_recyclerview_with_padding,(ViewGroup) getActivity().findViewById(R.id.root));
        View rootView = inflater.inflate(R.layout.layout_title_and_recyclerview_with_padding_without_srl_new, null);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView textView = (TextView) rootView.findViewById(R.id.tv_title);
        textView.setText(category);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
//        recyclerView.setTag(tag);
//        List<ImageAndTextBean> areaList = SimulateData.getSimulatedFunctionImageGroup();
        List<ImageAndTextBean> moduleList = list;
//        GridLayoutManager gridLayoutManager = new FullyGridLayoutManager((Activity)context, Constants.MODULE_LAYOUT_COL_NUMBER);
        GridLayoutManager gridLayoutManager = new FullyGridLayoutManagerSmoothScroll((Activity)context, Constants.MODULE_LAYOUT_COL_NUMBER);
        ImageAndTextVerticalAdapterNew adapter = new ImageAndTextVerticalAdapterNew(moduleList);
//        Util.addDividerToRecyclerView(context, recyclerView, true);
        adapter.setOnItemClickListener(new NoDoubleItemClickListener() {
            @Override
            public void onNoDoubleItemClick(BaseQuickAdapter adapter, View view, int position) {
                super.onNoDoubleItemClick(adapter, view, position);
                ImageAndTextBean item = (ImageAndTextBean) adapter.getItem(position);
                LogUtil.i("has selected item title:" + item.getTitle());
                RxBus.getDefault().post(Constants.EVENT_TYPE_STATISTICS_DID_SELECT_CONCRETE_MODULE, item);
            }
        });
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ImageAndTextBean item = (ImageAndTextBean) adapter.getItem(position);
//                LogUtil.i("has selected item name:" + item.getTitle());
//                RxBus.getDefault().post(Constants.EVENT_TYPE_DID_SELECT_MODULE, item);
//
//            }
//        });
        recyclerView.setLayoutManager(gridLayoutManager);
//        GridItemDecoration.Builder builder=new GridItemDecoration.Builder(context);
//        builder.color(R.color.colorLine);
//        builder.size(3);
//        builder.showLastDivider(false);
//        builder.isExistHead(true);
//        recyclerView.addItemDecoration(new GridItemDecoration(builder));
        recyclerView.setAdapter(adapter);

        view=rootView;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
