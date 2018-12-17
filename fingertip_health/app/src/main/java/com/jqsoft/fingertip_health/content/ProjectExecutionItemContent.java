package com.jqsoft.fingertip_health.content;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.response_new.ExecutionProjectsResultItemBean;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.rx.RxBus;
import com.jqsoft.fingertip_health.util.Util;

import net.qiujuer.genius.ui.widget.Button;

//最近7天执行项目,超时未执行项目中的一项
public class ProjectExecutionItemContent {
    private Context context;
    private View view;
    private ExecutionProjectsResultItemBean bean;
    public ProjectExecutionItemContent(Context context) {
        this.context=context;
    }

    public void initView(final ExecutionProjectsResultItemBean bean){
        this.bean=bean;
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
//        View   dialog = inflater.inflate(R.layout.layout_recyclerview_with_padding,(ViewGroup) getActivity().findViewById(R.id.root));
        View rootView = inflater.inflate(R.layout.layout_project_item, null);
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView tvProjectName = (TextView) rootView.findViewById(R.id.tv_project_name);
        TextView tvNextExecutionDate = (TextView) rootView.findViewById(R.id.tv_next_execution_date);
        Button btnExecute = (Button) rootView.findViewById(R.id.btn_execute);
        String projectName = getProjectName();
        String nextExecutionDate = getNextExecutionDate();
        String canonicalNextExecutionDate = Util.getYearMonthDayFromFullString(nextExecutionDate);
        nextExecutionDate=Constants.HINT_NEXT_EXECUTION_DATE+canonicalNextExecutionDate;
        tvProjectName.setText(projectName);
        tvNextExecutionDate.setText(nextExecutionDate);
        btnExecute.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                RxBus.getDefault().post(Constants.EVENT_TYPE_PROJECTS_EXECUTION_DID_CLICK_ONE_ITEM, bean);
            }
        });

        view=rootView;
    }

    public String getProjectName(){
        if (bean==null){
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(bean.getName());
        }
    }

    public String getNextExecutionDate(){
        if (bean==null){
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(bean.getNxetdate());
        }
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
