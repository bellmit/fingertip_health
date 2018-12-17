package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.jqsoft.fingertip_health.bean.SocialDetailBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 */

public class SocailDetailAdapter extends BaseExpandableListAdapter {
    private List<String> father_List;
    private SocialDetailBean sonData;
    private Context context;

    public SocailDetailAdapter(Context context, List<String> father_List, SocialDetailBean sonData) {
        this.context = context;
        this.sonData = sonData;
        this.father_List= father_List;

    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
