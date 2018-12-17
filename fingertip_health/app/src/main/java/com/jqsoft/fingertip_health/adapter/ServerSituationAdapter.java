package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.bean.CoreIndexBeanList;
import com.jqsoft.fingertip_health.di.ui.activity.ExecuProjectActivity;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioGroup;

import java.util.List;

/**
 * Created by YLL on 2017-7-4.
 */
public class ServerSituationAdapter extends BaseAdapter implements SmoothRadioGroup.OnCheckedChangeListener {


    //定义常用的参数
    private Context ctx;
    private int resourceId;
  //  private List<ExecuProjectActivity.User> users;

    private List<CoreIndexBeanList> users;

    private LayoutInflater inflater;
    //为三种布局定义一个标识
    private final int TYPE1 = 0;
    private final int TYPE2 = 1;
    private final int TYPE3 = 2;
    private final int TYPE4 = 3;
    private final int TYPE5 = 4;
    private final int TYPE6 = 5;

    ViewHolder1 holder1 = null;
    ViewHolder2 holder2 = null;
    ViewHolder3 holder3 = null;
    ViewHolder4 holder4 = null;
    ViewHolder5 holder5 = null;
    ViewHolder6 holder6 = null;
    String abnormal1="",abnormal2="";
    String had1="",had2="";
    String plus1="",plus2="";
    String plusplus1="",plusplus2="",plusplus3="",plusplus4="";

   /* public ServerSituationAdapter(Context context, List<ExecuProjectActivity.User> objects) {
        this.ctx = context;
        this.users = objects;
        //别忘了初始化inflater
        inflater = LayoutInflater.from(ctx);
    }*/

    public ServerSituationAdapter(Context context, List<CoreIndexBeanList> objects) {
        this.ctx = context;
        this.users = objects;
        //别忘了初始化inflater
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public CoreIndexBeanList getItem(int position) {
        return users.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    //这个方法必须重写，它返回了有几种不同的布局
    @Override
    public int getViewTypeCount() {
        return 6;
    }

    // 每个convertView都会调用此方法，获得当前应该加载的布局样式
  /*  @Override
    public int getItemViewType(int position) {
        //获取当前布局的数据
        ExecuProjectActivity.User u = users.get(position);
        //哪个字段不为空就说明这是哪个布局
        //比如第一个布局只有item1_str这个字段，那么就判断这个字段是不是为空，
        //如果不为空就表明这是第一个布局的数据
        //根据字段是不是为空，判断当前应该加载的布局

    }*/


    @Override
    public int getItemViewType(int position) {
        //获取当前布局的数据
        CoreIndexBeanList u = users.get(position);
        //哪个字段不为空就说明这是哪个布局
        //比如第一个布局只有item1_str这个字段，那么就判断这个字段是不是为空，
        //如果不为空就表明这是第一个布局的数据
        //根据字段是不是为空，判断当前应该加载的布局

        if (u.getSrgs().equals("001")) {
            return TYPE1;
        } else if (u.getSrgs().equals("002")) {
            return TYPE2;
        } else if (u.getSrgs().equals("003")) {
            return TYPE3;
        }else if (u.getSrgs().equals("004") ){
            return TYPE4;
        }else if (u.getSrgs().equals("005")) {
            return TYPE5;
        }else {//如果前两个字段都为空，那就一定是加载第三个布局啦。
            return TYPE6;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //初始化每个holder


        int type = getItemViewType(position);

        if (convertView == null) {
            switch (type) {
                case TYPE1:
                    convertView = inflater.inflate(R.layout.item_server_situation1, null, false);
                    holder1 = new ViewHolder1(convertView, position);
                    holder1.item1_tv = (TextView) convertView.findViewById(R.id.item1_tv);

                    holder1.rg_had1 = (SmoothRadioGroup) convertView.findViewById(R.id.rg_had1);
                    holder1.rb_had1 = (SmoothRadioButton) convertView.findViewById(R.id.rb_had1);
                    holder1.rb_had2 = (SmoothRadioButton) convertView.findViewById(R.id.rb_had2);
                    holder1.rg_had1.setOnCheckedChangeListener(this);
                    holder1.rb_had1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            had1="1";
                            had2="";
                    //        Toast.makeText(ctx,"无:"+ had1, Toast.LENGTH_SHORT).show();
                            int position = (int) holder1.rb_had1.getTag();
                            ((ExecuProjectActivity)ctx).saveHad1(position, had1);
                        }
                    });

                    holder1.rb_had2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            had1="";
                            had2="2";
                    //        Toast.makeText(ctx,"有:"+ had2, Toast.LENGTH_SHORT).show();
                            int position = (int) holder1.rb_had2.getTag();
                            ((ExecuProjectActivity)ctx).saveHad1(position, had2);
                        }
                    });




                    convertView.setTag(holder1);
                    break;
                case TYPE2:
                    convertView = inflater.inflate(R.layout.item_server_situation2, null, false);
                    holder2 = new ViewHolder2(convertView, position);
                    holder2.item2_tv = (TextView) convertView.findViewById(R.id.item2_tv);
                    holder2.rg_abnormal = (SmoothRadioGroup) convertView.findViewById(R.id.rg_abnormal);
                    holder2.rb_abnormal1 = (SmoothRadioButton) convertView.findViewById(R.id.rb_abnormal1);
                    holder2.rb_abnormal2 = (SmoothRadioButton) convertView.findViewById(R.id.rb_abnormal2);
                    holder2.et_abnormalinfo = (EditText) convertView.findViewById(R.id.et_abnormalinfo);

                   /* holder2.rb_abnormal1.setOnCheckedChangeListener(this);
                    holder2.rb_abnormal2.setOnCheckedChangeListener(this);*/
                    holder2.rg_abnormal.setOnCheckedChangeListener(this);
                    holder2.rb_abnormal1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            abnormal1="1";
                            abnormal2="";
                            holder2.et_abnormalinfo.setVisibility(View.GONE);
                    //        Toast.makeText(ctx,"无异常:"+ abnormal1, Toast.LENGTH_SHORT).show();
                            int position = (int) holder2.rb_abnormal1.getTag();
                            ((ExecuProjectActivity)ctx).saveAbnormalData(position, abnormal1);
                        }
                    });

                    holder2.rb_abnormal2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            abnormal1="";
                            abnormal2="2";
                            holder2.et_abnormalinfo.setVisibility(View.VISIBLE);
                    //        Toast.makeText(ctx,"有异常:"+ abnormal2, Toast.LENGTH_SHORT).show();
                            int position = (int) holder2.rb_abnormal2.getTag();
                            ((ExecuProjectActivity)ctx).saveAbnormalData(position, abnormal2);
                        }
                    });

                    convertView.setTag(holder2);
                    break;
                case TYPE3:
                    convertView = inflater.inflate(R.layout.item_server_situation3, null, false);
                    holder3 = new ViewHolder3(convertView, position);
                    holder3.item3_tv = (TextView) convertView.findViewById(R.id.item3_tv);


                    holder3.rg_plus1 = (SmoothRadioGroup) convertView.findViewById(R.id.rg_plus1);
                    holder3.rb_plus1 = (SmoothRadioButton) convertView.findViewById(R.id.rb_plus1);
                    holder3.rb_plus2 = (SmoothRadioButton) convertView.findViewById(R.id.rb_plus2);
                    holder3.rg_plus1.setOnCheckedChangeListener(this);
                    holder3.rb_plus1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            plus1="1";
                            plus2="";
                     //       Toast.makeText(ctx,"-:"+ plus1, Toast.LENGTH_SHORT).show();
                            int position = (int) holder3.rb_plus1.getTag();
                            ((ExecuProjectActivity)ctx).savePlus3(position, plus1);
                        }
                    });

                    holder3.rb_plus2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            plus1="";
                            plus2="2";
                    //        Toast.makeText(ctx,"+:"+ plus2, Toast.LENGTH_SHORT).show();
                            int position = (int) holder3.rb_plus2.getTag();
                            ((ExecuProjectActivity)ctx).savePlus3(position, plus2);
                        }
                    });




                    convertView.setTag(holder3);
                    break;
                case TYPE4:
                    convertView = inflater.inflate(R.layout.item_server_situation4, null, false);
                    holder4 = new ViewHolder4(convertView, position);
                    holder4.item4_tv = (TextView) convertView.findViewById(R.id.item4_tv);


                    holder4.rg_plusplus1 = (SmoothRadioGroup) convertView.findViewById(R.id.rg_plusplus1);
                    holder4.rb_plusplus1 = (SmoothRadioButton) convertView.findViewById(R.id.rb_plusplus1);
                    holder4.rb_plusplus2 = (SmoothRadioButton) convertView.findViewById(R.id.rb_plusplus2);
                    holder4.rb_plusplus3 = (SmoothRadioButton) convertView.findViewById(R.id.rb_plusplus3);
                    holder4.rb_plusplus4 = (SmoothRadioButton) convertView.findViewById(R.id.rb_plusplus4);
                    holder4.rg_plusplus1.setOnCheckedChangeListener(this);
                    holder4.rb_plusplus1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            plusplus1="1";
                            plusplus2="";
                            plusplus3="";
                            plusplus4="";
                   //         Toast.makeText(ctx,"-:"+ plusplus4, Toast.LENGTH_SHORT).show();
                            int position = (int) holder4.rb_plusplus1.getTag();
                            ((ExecuProjectActivity)ctx).savePlusplus4(position, plusplus1);
                        }
                    });

                    holder4.rb_plusplus2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            plusplus1="";
                            plusplus2="2";
                            plusplus3="";
                            plusplus4="";
                    //        Toast.makeText(ctx,"+:"+ plusplus2, Toast.LENGTH_SHORT).show();
                            int position = (int) holder4.rb_plusplus2.getTag();
                            ((ExecuProjectActivity)ctx).savePlusplus4(position, plusplus2);
                        }
                    });
                    holder4.rb_plusplus3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            plusplus1="";
                            plusplus2="";
                            plusplus3="3";
                            plusplus4="";
                     //       Toast.makeText(ctx,"++:"+ plusplus3, Toast.LENGTH_SHORT).show();
                            int position = (int) holder4.rb_plusplus3.getTag();
                            ((ExecuProjectActivity)ctx).savePlusplus4(position, plusplus3);
                        }
                    });
                    holder4.rb_plusplus4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            plusplus1="";
                            plusplus2="";
                            plusplus3="";
                            plusplus4="4";
                        //    Toast.makeText(ctx,"+++:"+ plusplus4, Toast.LENGTH_SHORT).show();
                            int position = (int) holder4.rb_plusplus4.getTag();
                            ((ExecuProjectActivity)ctx).savePlusplus4(position, plusplus4);
                        }
                    });



                    convertView.setTag(holder4);
                    break;
                case TYPE5:
                    convertView = inflater.inflate(R.layout.item_server_situation5, null, false);
                    holder5 =new ViewHolder5(convertView, position);
                    holder5.item5_tv = (TextView) convertView.findViewById(R.id.item5_tv);

                    holder5.tv_unit5=(TextView)convertView.findViewById(R.id.tv_unit5);
                    holder5.et_info5=(EditText)convertView.findViewById(R.id.et_info5);

                    convertView.setTag(holder5);
                    break;
                case TYPE6:
                    convertView = inflater.inflate(R.layout.item_server_situation6, null, false);
                 //   holder6 = new ViewHolder6();
                    holder6 = new ViewHolder6(convertView, position);
                    holder6.item6_tv = (TextView) convertView.findViewById(R.id.item6_tv);
                    holder6.tv_unit=(TextView)convertView.findViewById(R.id.tv_unit);
                    holder6.et_info=(EditText)convertView.findViewById(R.id.et_info);
                    convertView.setTag(holder6);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case TYPE1:
                    holder1 = (ViewHolder1) convertView.getTag();
                    break;
                case TYPE2:
                    holder2 = (ViewHolder2) convertView.getTag();
                    break;
                case TYPE3:
                    holder3 = (ViewHolder3) convertView.getTag();
                    break;
                case TYPE4:
                    holder4 = (ViewHolder4) convertView.getTag();
                    break;
                case TYPE5:
                    holder5 = (ViewHolder5) convertView.getTag();
                    break;
                case TYPE6:
                    holder6 = (ViewHolder6) convertView.getTag();
                    break;
            }
        }
        //为布局设置数据
        switch (type) {
            case TYPE1:
                holder1.item1_tv.setText(users.get(position).getZbmc()+":");
                break;
            case TYPE2:
                holder2.item2_tv.setText(users.get(position).getZbmc()+":");
                break;
            case TYPE3:
                holder3.item3_tv.setText(users.get(position).getZbmc()+":");
                break;
            case TYPE4:
                holder4.item4_tv.setText(users.get(position).getZbmc()+":");
                break;
            case TYPE5:
                holder5.item5_tv.setText(users.get(position).getZbmc()+":");
                holder5.tv_unit5.setText(users.get(position).getUnit());
                break;
            case TYPE6:
                holder6.item6_tv.setText(users.get(position).getZbmc()+":");
                holder6.tv_unit.setText(users.get(position).getUnit());



                break;
        }



        return convertView;
    }

    public String  setServerInfo(){
      return  holder6.item6_tv.getText().toString();

    }

    @Override
    public void onCheckedChanged(SmoothRadioGroup smoothRadioGroup, int i) {
       /* switch (i) {
            case R.id.rb_abnormal1:
                abnormal1="1";
                abnormal2="";
                Toast.makeText(ctx,"无异常:"+ abnormal1, Toast.LENGTH_SHORT).show();
                holder2.et_abnormalinfo.setVisibility(View.GONE);
                break;
            case R.id.rb_abnormal2:
                abnormal1="";
                abnormal2="2";
                Toast.makeText(ctx,"有异常:"+ abnormal2, Toast.LENGTH_SHORT).show();
                holder2.et_abnormalinfo.setVisibility(View.VISIBLE);
                break;


            default:
                break;
        }*/
    }


    //为每种布局定义自己的ViewHolder
    public class ViewHolder1 {
        TextView item1_tv;
        SmoothRadioGroup rg_had1;
        SmoothRadioButton   rb_had1;
        SmoothRadioButton   rb_had2;
        public ViewHolder1(View view,int position) {


            rb_had1 = (SmoothRadioButton) view.findViewById(R.id.rb_had1);
            rb_had2 = (SmoothRadioButton) view.findViewById(R.id.rb_had2);

            rb_had1.setTag(position);
            rb_had2.setTag(position);


        }
    }

    public class ViewHolder2 {
        TextView item2_tv;
        SmoothRadioGroup rg_abnormal;
        SmoothRadioButton   rb_abnormal1;
        SmoothRadioButton   rb_abnormal2;
        EditText  et_abnormalinfo;
        public ViewHolder2(View view,int position) {

            et_abnormalinfo = (EditText) view.findViewById(R.id.et_abnormalinfo);
            rb_abnormal1 = (SmoothRadioButton) view.findViewById(R.id.rb_abnormal1);
            rb_abnormal2 = (SmoothRadioButton) view.findViewById(R.id.rb_abnormal2);
            et_abnormalinfo.setTag(position);
            rb_abnormal1.setTag(position);
            rb_abnormal2.setTag(position);

            et_abnormalinfo.addTextChangedListener(new TextSwitcher2(this));
        }
    }

    public class ViewHolder3 {
        TextView item3_tv;

        SmoothRadioGroup rg_plus1;
        SmoothRadioButton   rb_plus1;
        SmoothRadioButton   rb_plus2;
        public ViewHolder3(View view,int position) {


            rb_plus1 = (SmoothRadioButton) view.findViewById(R.id.rb_plus1);
            rb_plus2 = (SmoothRadioButton) view.findViewById(R.id.rb_plus2);

            rb_plus1.setTag(position);
            rb_plus2.setTag(position);


        }
    }

    public class ViewHolder4 {
        TextView item4_tv;

        SmoothRadioGroup rg_plusplus1;
        SmoothRadioButton   rb_plusplus1;
        SmoothRadioButton   rb_plusplus2;
        SmoothRadioButton   rb_plusplus3;
        SmoothRadioButton   rb_plusplus4;
        public ViewHolder4(View view,int position) {


            rb_plusplus1 = (SmoothRadioButton) view.findViewById(R.id.rb_plusplus1);
            rb_plusplus2 = (SmoothRadioButton) view.findViewById(R.id.rb_plusplus2);
            rb_plusplus3 = (SmoothRadioButton) view.findViewById(R.id.rb_plusplus3);
            rb_plusplus4 = (SmoothRadioButton) view.findViewById(R.id.rb_plusplus4);

            rb_plusplus1.setTag(position);
            rb_plusplus2.setTag(position);
            rb_plusplus3.setTag(position);
            rb_plusplus4.setTag(position);


        }
    }
    public class ViewHolder5 {
        TextView item5_tv;
        TextView tv_unit5;
        EditText et_info5;

        public ViewHolder5(View view,int position) {

            et_info5 = (EditText) view.findViewById(R.id.et_info5);
            et_info5.setTag(position);
            et_info5.addTextChangedListener(new TextSwitcher5(this));
        }
    }

    public class ViewHolder6 {
        TextView item6_tv;
        TextView tv_unit;
        EditText et_info;

    public ViewHolder6(View view,int position) {

            et_info = (EditText) view.findViewById(R.id.et_info);
            et_info.setTag(position);
            et_info.addTextChangedListener(new TextSwitcher6(this));
        }
    }

    class TextSwitcher6 implements TextWatcher {
        private ViewHolder6 mHolder;

        public TextSwitcher6(ViewHolder6 mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int position = (int) mHolder.et_info.getTag();
            ((ExecuProjectActivity)ctx).saveEditData(position, s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    class TextSwitcher2 implements TextWatcher {
        private ViewHolder2 mHolder;

        public TextSwitcher2(ViewHolder2 mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int position = (int) mHolder.et_abnormalinfo.getTag();
            ((ExecuProjectActivity)ctx).saveAbnormalOther(position, s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

    class TextSwitcher5 implements TextWatcher {
        private ViewHolder5 mHolder;

        public TextSwitcher5(ViewHolder5 mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int position = (int) mHolder.et_info5.getTag();
            ((ExecuProjectActivity)ctx).saveEditData5(position, s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }

}