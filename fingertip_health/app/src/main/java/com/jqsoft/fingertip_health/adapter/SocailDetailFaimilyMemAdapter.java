package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.DetaiFamilMember;

import java.util.List;

public class SocailDetailFaimilyMemAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<DetaiFamilMember> familyMember;


    public SocailDetailFaimilyMemAdapter(Context context, List<DetaiFamilMember> familyMember) {
        super();
        this.context = context;
        this.familyMember = familyMember;

    }

    @Override
    public int getCount() {

        return familyMember.size();
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
            convertView = inflater.inflate(R.layout.contentview_familymember, null);
            viewHolder = new ViewHolder();

            viewHolder.basename = (TextView) convertView.findViewById(R.id.basename);
            viewHolder.basesex = (TextView) convertView.findViewById(R.id.basesex);
            viewHolder.baseidcard = (TextView) convertView.findViewById(R.id.baseidcard);
            viewHolder.baserelations = (TextView) convertView.findViewById(R.id.basehuji);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.basename.setText(familyMember.get(position).getName());
        if (familyMember.get(position).getSex().equals("sex_1")) {
            viewHolder.basesex.setText("男");
        } else {
            viewHolder.basesex.setText("女");
        }
        viewHolder.baseidcard.setText(familyMember.get(position).getCardNo());
        viewHolder.baserelations.setText(familyMember.get(position).getRelation());


        return convertView;
    }

    static class ViewHolder {
        public TextView basename;
        public TextView basesex;
        public TextView baseidcard;
        public TextView baserelations;

    }
}
