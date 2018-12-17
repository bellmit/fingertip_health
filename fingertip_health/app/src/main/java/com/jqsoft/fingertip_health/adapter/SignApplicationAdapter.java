//package com.jqsoft.grassroots_civil_administration_platform.adapter;
//
//import android.view.View;
//import android.widget.ImageView;
//
//import com.chad.library.adapter.base.BaseViewHolder;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.adapter.base.BaseQuickAdapterEx;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Version;
//import com.jqsoft.fingertip_health.bean.response_new.IndexAndOnlineSignInitialData;
//import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
//import com.jqsoft.fingertip_health.rx.RxBus;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.GlideUtils;
//import com.jqsoft.fingertip_health.utils2.AppUtils;
//import com.jqsoft.fingertip_health.utils3.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//public class SignApplicationAdapter extends BaseQuickAdapterEx<IndexAndOnlineSignInitialData, BaseViewHolder> {
//
//    public SignApplicationAdapter(List<IndexAndOnlineSignInitialData> data) {
//        super(R.layout.item_sign_application, data);
//    }
//
//    @Override
//    protected void convert(final BaseViewHolder helper, final IndexAndOnlineSignInitialData item) {
//        int position = helper.getAdapterPosition();
//        String photoUrl = Util.trimString(item.getPhotoUrl());
//        String imageUrl = Version.FILE_URL_BASE+photoUrl;
//
//        GlideUtils.loadImage(imageUrl, (ImageView) helper.getView(R.id.iv_head));
//        helper.setText(R.id.tv_name, Util.trimString(item.getPersonName()));
//        int genderImageId = getGenderImageIdFromType(Util.trimString(item.getSexCode()));
//        helper.setImageResource(R.id.iv_gender, genderImageId);
//        helper.setText(R.id.tv_age, Util.trimString(item.getAge())+Util.trimString(Constants.YEARS_OLD));
////        if (item.getIsDiabetes()){
////            helper.setVisible(R.id.iv_diabetes, true);
////        } else {
////            helper.setVisible(R.id.iv_diabetes, false);
////        }
////        if (item.getIsHypertension()){
////            helper.setVisible(R.id.iv_hypertension, true);
////        } else {
////            helper.setVisible(R.id.iv_hypertension, false);
////        }
////        if (item.getIsElderlyPeople()){
////            helper.setVisible(R.id.iv_elderly_people, true);
////        } else {
////            helper.setVisible(R.id.iv_elderly_people, false);
////        }
//        final View dialView = helper.getView(R.id.iv_dial);
//        dialView.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                String phoneNumber = Util.trimString(item.getPhone());
//                if (!StringUtils.isBlank(phoneNumber)){
//                    AppUtils.actionDial(dialView.getContext(), phoneNumber);
//                } else {
//                    Util.showToast(dialView.getContext(), Constants.HINT_PHONE_NUMBER_EMPTY);
//                }
//            }
//        });
////        RxView.clicks(dialView)
////                .throttleFirst(Constants.RXBINDING_THROTTLE, TimeUnit.SECONDS)
////                .subscribe(new Action1<Void>() {
////                    @Override
////                    public void call(Void aVoid) {
////                        Util.showToast(dialView.getContext(), "dial click");
////                    }
////                });
//
//        helper.setText(R.id.tv_id_number, Util.trimString(item.getCardNo()));
//        helper.setText(R.id.tv_archive_number, Util.trimString(item.getNo()));
//        String applicationTime = Util.trimString(item.getApplyTime());
//        String canonicalApplicationTime = Util.getYearMonthDayHourMinuteSecondFromFullString(applicationTime);
//        helper.setText(R.id.tv_application_time, canonicalApplicationTime);
//        helper.setText(R.id.tv_desired_service_package, Util.trimString(item.getServerPackageName()));
//        helper.setText(R.id.tv_home_address, Util.trimString(item.getAddress()));
//
//
//
//        View llSign = helper.getView(R.id.ll_sign);
//        llSign.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                RxBus.getDefault().post(Constants.EVENT_TYPE_SIGN_APPLICATION_DID_CLICK_SIGN_BUTTON, item);
////                MaterialDialog dialog = new MaterialDialog.Builder(v.getContext())
////                        .title(R.string.hint_suggestion)
////                        .content(R.string.hint_confirm_to_sign)
////                        .positiveText(R.string.confirm)
////                        .negativeText(R.string.cancel)
////                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                            @Override
////                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                dialog.dismiss();
////                            }
////                        }).build();
////                dialog.show();
//
//            }
//        });
//        View llCancelApplication = helper.getView(R.id.ll_cancel_application);
//        llCancelApplication.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                RxBus.getDefault().post(Constants.EVENT_TYPE_SIGN_APPLICATION_DID_CLICK_CANCEL_BUTTON, item);
////                MaterialDialog dialog = new MaterialDialog.Builder(v.getContext())
////                        .title(R.string.hint_suggestion)
////                        .content(R.string.hint_confirm_to_cancel_application)
////                        .positiveText(R.string.confirm)
////                        .negativeText(R.string.cancel)
////                        .onPositive(new MaterialDialog.SingleButtonCallback() {
////                            @Override
////                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
////                                dialog.dismiss();
////                            }
////                        }).build();
////                dialog.show();
//            }
//        });
////        helper.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                onItemClickListener.onItemClickListener(item.getDocid(), item.getImgsrc(),helper.getView(R.id.iv_item_top_news));
////            }
////        });
//
//        ImageView iv_tang =helper.getView(R.id.iv_tang);
//        ImageView iv_gao =  helper.getView(R.id.iv_gao);
//        ImageView iv_lao = helper.getView(R.id.iv_lao);
//        ImageView iv_jing =  helper.getView(R.id.iv_jing);
//        ImageView iv_mian =  helper.getView(R.id.iv_mian);
//        ImageView iv_pin =  helper.getView(R.id.iv_pin);
//        ImageView iv_tong =helper.getView(R.id.iv_tong);
//        ImageView iv_tuo =  helper.getView(R.id.iv_tuo);
//        ImageView iv_yun =  helper.getView(R.id.iv_yun);
//        String spersonMold =item.getPersonMold();
//
//        ArrayList<String> spersonMoldList = new ArrayList<>();
//
//        spersonMoldList.clear();
//        if(spersonMold.substring(0,1).equals("1")){
//            spersonMoldList.add("高");
//        }
//        if(spersonMold.substring(1,2).equals("1")){
//            spersonMoldList.add("糖");
//        }
//        if(spersonMold.substring(2,3).equals("1")){
//            spersonMoldList.add("精");
//        }
//        if(spersonMold.substring(3,4).equals("1")){
//            spersonMoldList.add("老");
//        }
//        if(spersonMold.substring(4,5).equals("1")){
//            spersonMoldList.add("孕");
//        }
//        if(spersonMold.substring(5,6).equals("1")){
//            spersonMoldList.add("童");
//        }
//        if(spersonMold.substring(6,7).equals("1")){
//            spersonMoldList.add("贫");
//        }
//        if(spersonMold.substring(6,7).equals("2")){
//            spersonMoldList.add("脱");
//        }
//        if(spersonMold.substring(6,7).equals("3")){
//            spersonMoldList.add("免");
//        }
//
//        String s="";
//        for(int i=0;i<spersonMoldList.size();i++){
//            s=s+spersonMoldList.get(i);
//        }
//        if(s.indexOf("糖")!=-1){
//            iv_tang.setImageResource(R.mipmap.ic_tang);
//            iv_tang.setVisibility(View.VISIBLE);
//        }
//
//        if(s.indexOf("高")!=-1) {
//            iv_gao.setImageResource(R.mipmap.ic_gao);
//            iv_gao.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("老")!=-1) {
//            iv_lao.setImageResource(R.mipmap.ic_lao);
//            iv_lao.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("精")!=-1) {
//            iv_jing.setImageResource(R.mipmap.ic_jing);
//            iv_jing.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("免")!=-1) {
//            iv_mian.setImageResource(R.mipmap.ic_mian);
//            iv_mian.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("贫")!=-1) {
//            iv_pin.setImageResource(R.mipmap.ic_pin);
//            iv_pin.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("童")!=-1) {
//            iv_tong.setImageResource(R.mipmap.ic_tong);
//            iv_tong.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("脱")!=-1) {
//            iv_tuo.setImageResource(R.mipmap.ic_tuo);
//            iv_tuo.setVisibility(View.VISIBLE);
//        }
//        if(s.indexOf("孕")!=-1) {
//            iv_yun.setImageResource(R.mipmap.ic_yun);
//            iv_yun.setVisibility(View.VISIBLE);
//        }
//
//
//    }
//
//    public int getGenderImageIdFromType(String type){
//        int result = R.mipmap.i_male;
//        if (Constants.GENDER_STRING_MALE.equals(type)){
//            result=R.mipmap.i_male;
//        } else if (Constants.GENDER_STRING_FEMALE.equals(type)){
//            result=R.mipmap.i_female;
//        }
//        return result;
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
