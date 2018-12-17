package com.jqsoft.fingertip_health.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
import com.jqsoft.fingertip_health.bean.HighBloodListActivityBean;
import com.jqsoft.fingertip_health.bean.SignDoctorListBean;
import com.jqsoft.fingertip_health.bean.propMap;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.view.HorizontalListView;

import java.util.List;


public class SignDoctorListActivityAdapter extends BaseQuickAdapterEx<SignDoctorListBean, BaseViewHolder> {
    private Context context;
   private ListAdapter adapter;
    public SignDoctorListActivityAdapter(List<HighBloodListActivityBean> data, Context context) {
        super(R.layout.item_highbooldlist, data);

        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final SignDoctorListBean item) {
        HorizontalListView listView=helper.getView(R.id.listview);


        try {
            List<propMap> resultList = JSON.parseObject(item.getProperties(),
                    new TypeReference<List<propMap>>(){});
            if (!ListUtils.isEmpty(resultList)) {
                adapter = new HighBloodListItemAdapter(context,resultList);
                listView.setAdapter(adapter);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }





//        item.setPhotoUrl("http://192.168.88.36:8080/fdss-api/photo/0123456.jpg");
        //  String photoUrl = Util.trimString(item.getPhotoUrl());
        //    String imageUrl = Version.FILE_URL_BASE+photoUrl;

        //   GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.iv_head));

//        String photoUrl = Util.trimString(item.getFilePath());
//        String imageUrl = Version.FIND_FILE_URL_BASE + photoUrl;
//         GlideUtils.loadImageNew(imageUrl, (ImageView) helper.getView(R.id.iv_title));
//        GlideUtils.load(context,imageUrl,(ImageView) helper.getView(R.id.iv_title));
        //http://192.168.44.134:8080/sri/JingQi_Sri_File/upload/sriattach/wechat/EKZ6H7c7YIGX.jpg
//        Uri uri = Uri.parse(imageUrl);
        SimpleDraweeView draweeView = (SimpleDraweeView) helper.getView(R.id.iv_title);
//        draweeView.setImageURI(uri);
        if (!TextUtils.isEmpty(item.getName())){
            helper.setText(R.id.objectname, Util.trimString(item.getName()));
        }
        if (!TextUtils.isEmpty(item.getAreaFulladdress())){
            helper.setText(R.id.objectidcard, Util.trimString(item.getAreaFulladdress()));
        }

        ImageView iv_sex = helper.getView(R.id.objectsex);

        String sex = Util.trimString(item.getSexName());
        if (sex.equals("男")) {
            iv_sex.setImageResource(R.mipmap.icon_sex_man);
        } else {
            iv_sex.setImageResource(R.mipmap.icon_sex_woman);
        }


//        helper.setText(R.id.tv_message, Util.trimString(item.getPostMessage()));
//        String time = Util.trimString(item.getSetTime());
//        String canonicalTime = Util.getYearMonthDayFromFullString(time);
//        helper.setText(R.id.tv_time, canonicalTime);
//        ImageView iv_tang =helper.getView(R.id.iv_tang);


//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
//            }
//        });

    }

//    OnItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    public interface OnItemClickListener {
//        void onItemClickListener(String id, String imgUrl, View view);}

}
