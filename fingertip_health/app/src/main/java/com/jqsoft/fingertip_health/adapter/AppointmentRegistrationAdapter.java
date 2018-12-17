//package com.jqsoft.grassroots_civil_administration_platform.adapter;
//
//import android.widget.ImageView;
//
//import com.amap.api.location.AMapLocation;
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.bean.response_new.AppointmentRegistrationResultBean;
//import com.jqsoft.fingertip_health.di.ui.activity.AppointmentRegistrationActivity;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.GlideUtils;
//
//import java.util.List;
//
//
//
//public class AppointmentRegistrationAdapter extends BaseQuickAdapterEx<AppointmentRegistrationResultBean, BaseViewHolder> {
//    AppointmentRegistrationActivity activity;
//    public AppointmentRegistrationAdapter(AppointmentRegistrationActivity activity, List<AppointmentRegistrationResultBean> data) {
//        super(R.layout.item_appointment_registration_hospital_layout, data);
//        this.activity=activity;
//    }
//
//    @Override
//    protected void convert(final BaseViewHolder helper, final AppointmentRegistrationResultBean item) {
//
//        GlideUtils.loadImage(item.getUrl(), (ImageView) helper.getView(R.id.iv_image));
//        helper.setText(R.id.tv_name, Util.trimString(item.getName()));
//        helper.setText(R.id.tv_level, Util.trimString(item.getLevel()));
//        helper.setText(R.id.tv_appointment_number, Util.trimString(item.getAppointmentNumber()));
//        String distanceString = Constants.EMPTY_STRING;
//        if (activity.hasGotCurrentLocation && activity.hasGotHttpResult && activity.mCurrentLocation!=null) {
//            AMapLocation mCurrentLocation = activity.mCurrentLocation;
//            float currentLatitude =  (float) mCurrentLocation.getLatitude();
//            float currentLongitude = (float) mCurrentLocation.getLongitude();
//            float hospitalLatitude = Util.getFloatFromString(Util.trimString(item.getLatitude()));
//            float hospitalLongitude = Util.getFloatFromString(Util.trimString(item.getLongitude()));
//            if (mCurrentLocation == null) {
//            } else if (!Util.isLongitudeValid(String.valueOf(currentLongitude))) {
//
//            } else if (!Util.isLatitudeValid(String.valueOf(currentLatitude))) {
//
//            } else if (!Util.isLongitudeValid(String.valueOf(hospitalLongitude))){
//
//            } else if (!Util.isLatitudeValid(String.valueOf(hospitalLatitude))){
//
//            } else {
//                distanceString = Util.getDecimalDistanceStringBetweenTwoCoordinates(currentLongitude,
//                        currentLatitude, hospitalLongitude, hospitalLatitude);
//            }
//        } else {
//
//        }
//        helper.setText(R.id.tv_distance, distanceString);
//
//
//
////        helper.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
////            }
////        });
//
//    }
//
////    OnItemClickListener onItemClickListener;
////
////    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
////        this.onItemClickListener = onItemClickListener;
////    }
////
////    public interface OnItemClickListener {
////        void onItemClickListener(String id, String imgUrl, View view);}
//
//}
