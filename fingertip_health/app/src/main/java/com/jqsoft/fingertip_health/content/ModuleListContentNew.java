package com.jqsoft.fingertip_health.content;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.ImageAndTextBean;

import java.util.List;

/**
 * 全部模块(最新效果图)
 */

public class ModuleListContentNew {
    private Context context;
    private View view;
    public ModuleListContentNew(Context context) {
        this.context=context;
    }

    public void initView(List<ImageAndTextBean> list){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//        View   dialog = inflater.inflate(R.layout.layout_recyclerview_with_padding,(ViewGroup) getActivity().findViewById(R.id.root));
        View rootView = inflater.inflate(R.layout.layout_linear_vertical, null);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        view=rootView;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
