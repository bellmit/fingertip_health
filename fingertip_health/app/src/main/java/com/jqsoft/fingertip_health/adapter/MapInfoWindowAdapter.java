//package com.jqsoft.grassroots_civil_administration_platform.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.amap.api.maps.AMap;
//import com.amap.api.maps.model.Marker;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.bean.MedicalInstitutionDetailBean;
//import com.jqsoft.fingertip_health.di.ui.activity.MedicalInstitutionDetailActivity;
//
///**
// * Created by Administrator on 2017-05-23.
// */
//
//public class MapInfoWindowAdapter implements AMap.InfoWindowAdapter {
//    private Context context;
//    private MedicalInstitutionDetailBean bean;
//    private View infoWindow = null;
//
//    public MapInfoWindowAdapter(Context context, MedicalInstitutionDetailBean bean) {
//        this.context=context;
//        this.bean=bean;
//    }
//
//    @Override
//    public View getInfoWindow(Marker marker) {
//        if (infoWindow==null){
//            infoWindow= LayoutInflater.from(context).inflate(R.layout.layout_map_info_window, null, false);
//        }
//        render(marker, infoWindow);
//        return infoWindow;
//    }
//
//    public void render(final Marker marker, View view){
//        TextView tvHospitalName = (TextView)view.findViewById(R.id.tv_hospital_name);
//        TextView tvHospitalAddress = (TextView)view.findViewById(R.id.tv_hospital_address);
//        ImageView ibNavi = (ImageView)view.findViewById(R.id.ib_start_amap_app);
//        tvHospitalName.setText(bean.getName());
//        tvHospitalAddress.setText(bean.getAddress());
//        ibNavi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startAMapNavi(marker);
//            }
//        });
//    }
//
//    /**
//     * 点击一键导航按钮跳转到导航页面
//     *
//     * @param marker
//     */
//    private void startAMapNavi(Marker marker) {
//        MedicalInstitutionDetailActivity medicalInstitutionDetailActivity = null;
//        if (context instanceof MedicalInstitutionDetailActivity){
//            medicalInstitutionDetailActivity=(MedicalInstitutionDetailActivity)context;
//            medicalInstitutionDetailActivity.startNavi();
//        }
//    }
//
//    @Override
//    public View getInfoContents(Marker marker) {
//        return null;
//    }
//}
