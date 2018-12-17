package com.jqsoft.fingertip_health.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.FlowPopListViewAdapter;
import com.jqsoft.fingertip_health.bean.FiltrateBean;

import java.util.ArrayList;
import java.util.List;


public class FlowPopWindow extends PopupWindow {

    private final Activity context;
    private final List<FiltrateBean> dictList;
    private CustomHeightListView mListView;
    private TextView tvReset, tvConfirm,type_name;
    private View nullView;
    private FlowPopListViewAdapter adapter;
    private OnConfirmClickListener onConfirmClickListener;
    private Boolean singleChoose;
    private Boolean saveClear=false;
    private ArrayList<Integer> ChoosePostion=new ArrayList<>();
    public FlowPopWindow(Activity context, List<FiltrateBean> dictList,Boolean singleChoose ) {
        this.context = context;
        this.dictList=dictList;
        this.singleChoose=singleChoose;
        initPop();
    }


    private void initPop() {
        View popView = View.inflate(context, R.layout.flow_pop_listview, null);
        //设置view
        this.setContentView(popView);
        //设置宽高（也可设置为LinearLayout.LayoutParams.MATCH_PARENT或者LinearLayout.LayoutParams.MATCH_PARENT）
        this.setWidth(-1);
        this.setHeight(-2);
        //设置PopupWindow的焦点
        this.setFocusable(true);
        //设置窗口以外的地方点击可关闭pop
        this.setOutsideTouchable(false);


        //设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(0x33000000));

        mListView = (CustomHeightListView) popView.findViewById(R.id.listview);
        tvReset = (TextView) popView.findViewById(R.id.tv_reset);
        tvConfirm = (TextView) popView.findViewById(R.id.tv_confirm);
        nullView = popView.findViewById(R.id.view_null);
        type_name= (TextView)popView.findViewById(R.id.type_name);
        adapter = new FlowPopListViewAdapter(context, dictList,singleChoose);
        mListView.setAdapter(adapter);
        if (!TextUtils.isEmpty(dictList.get(0).getTypeName())){
            type_name.setText(dictList.get(0).getTypeName());
        }


        for (int x = 0; x < dictList.size(); x++) {
            List<FiltrateBean.Children> childrenBeen = dictList.get(x).getChildren();
            for (int y=0;y<childrenBeen.size();y++){
                if (childrenBeen.get(y).isSelected())
                    ChoosePostion.add(y);
            }
        }

        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveClear=false;
                for (int x = 0; x < dictList.size(); x++) {
                    List<FiltrateBean.Children> childrenBeen = dictList.get(x).getChildren();
                    for (int y=0;y<childrenBeen.size();y++){
                        if (childrenBeen.get(y).isSelected())
                            childrenBeen.get(y).setSelected(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveClear=true;
                //自定义监听第三步
                onConfirmClickListener.onConfirmClick();
                dismiss();
            }
        });
        nullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

//
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!saveClear){
                    if (ChoosePostion!=null){



                        for (int x = 0; x < dictList.size(); x++) {
                            List<FiltrateBean.Children> childrenBeen = dictList.get(x).getChildren();
                            for (int y=0;y<childrenBeen.size();y++){
                                if (childrenBeen.get(y).isSelected())
                                    childrenBeen.get(y).setSelected(false);
                            }
                        }

                        for (int i=0;i<ChoosePostion.size();i++){

                            for (int x = 0; x < dictList.size(); x++) {
                                List<FiltrateBean.Children> childrenBeen = dictList.get(x).getChildren();

                                childrenBeen.get(ChoosePostion.get(i)).setSelected(true);

                            }
                            adapter.notifyDataSetChanged();

                        }
                    }
                }

            }
        });
    }

    //自定义监听第二步
    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener){
        this.onConfirmClickListener=onConfirmClickListener;
    }

    //自定义监听第一步
    public interface OnConfirmClickListener{
        void onConfirmClick();
    }

}
