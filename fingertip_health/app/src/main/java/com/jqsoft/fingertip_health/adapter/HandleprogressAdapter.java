package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.ProgressBean;

import java.util.List;

public class HandleprogressAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<ProgressBean> data;
    private int myposition;


    public HandleprogressAdapter(Context context, List<ProgressBean> data) {
        super();
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {

        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_handleprogress, null);
            viewHolder = new ViewHolder();

            viewHolder.username = (TextView) convertView.findViewById(R.id.username);
            viewHolder.idcard = (TextView) convertView.findViewById(R.id.idcard);
            viewHolder.shenqingdata = (TextView) convertView.findViewById(R.id.shenqingdata);
            viewHolder.jiuzhutype = (TextView) convertView.findViewById(R.id.jiuzhutype);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.username.setText(data.get(position).getName());
        viewHolder.idcard.setText(data.get(position).getCardNo());
        viewHolder.jiuzhutype.setText(data.get(position).getItemNames());
        viewHolder.shenqingdata.setText(data.get(position).getFinishTime());

	/*	if(data.get(position).getEvaluationState()==null){

			viewHolder.btn_execu.setText("待评价");
		}else{
			viewHolder.btn_execu.setText("已评价");
		}*/

/*
        viewHolder.btn_execu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SignClientServiceAssessResultBean data1 = new SignClientServiceAssessResultBean();


				Intent intent = new Intent(context, SignServiceEvalution.class);
				intent.putExtra("SignClientServiceAssessResultBean",  data.get(position));
				context.startActivity(intent);

			}
		});*/


        return convertView;
    }


    static class ViewHolder {
        public TextView username;
        public TextView idcard;
        public TextView shenqingdata;

        public TextView jiuzhutype;

    }


}
