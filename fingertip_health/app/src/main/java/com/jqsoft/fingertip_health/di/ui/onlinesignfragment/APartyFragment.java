//package com.jqsoft.grassroots_civil_administration_platform.di.ui.onlinesignfragment;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.graphics.Color;
//import android.support.v7.widget.AppCompatEditText;
//import android.support.v7.widget.AppCompatTextView;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Identity;
//import com.jqsoft.fingertip_health.bean.SignTeamBean;
//import com.jqsoft.fingertip_health.bean.response_new.IndexAndOnlineSignInitialData;
//import com.jqsoft.fingertip_health.di.ui.fragment.HeyibanFragment;
//import com.jqsoft.fingertip_health.di.ui.fragment.base.AbstractFragment;
//import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
//import com.jqsoft.fingertip_health.listener.submitOnSuccessListener;
//import com.jqsoft.fingertip_health.rx.RxBus;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils3.util.StringUtils;
//import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
//
//import biz.kasual.materialnumberpicker.MaterialNumberPicker;
//import butterknife.BindView;
//import rx.Subscription;
//import rx.functions.Action1;
//import rx.subscriptions.CompositeSubscription;
//
//public class APartyFragment extends AbstractFragment implements HeyibanFragment.OnSuccessListener, submitOnSuccessListener {
//    @BindView(R.id.signtime_layout)
//    LinearLayout signtime_layout;
//    @BindView(R.id.acet_family_doctor_name)
//    AppCompatTextView doctorName;
//    @BindView(R.id.acet_sign_time)
//    AppCompatTextView acetSignTime;
//    @BindView(R.id.iv_sign_time)
//    ImageView ivSignTime;
//    @BindView(R.id.acet_sign_year)
//    AppCompatEditText acetSignYear;
//    @BindView(R.id.iv_sign_year)
//    ImageView ivSignYear;
//    @BindView(R.id.acet_service_cycle_start)
//    AppCompatTextView acetServiceCycleStart;
//    @BindView(R.id.iv_service_cycle_start)
//    ImageView ivServiceCycleStart;
//    @BindView(R.id.acet_service_cycle_end)
//    AppCompatTextView acetServiceCycleEnd;
//    @BindView(R.id.iv_service_cycle_end)
//    ImageView ivServiceCycleEnd;
//
//    public static String qianyuenianfen;
//    public static String startime;
//    public static String endtime;
//    public static String qianyuetime;
//    private Context context;
//    private static APartyFragment instance = null;
//
//    private CompositeSubscription mInitializeSubscription;
//
//    private void registerInitializeSubscription() {
//        Subscription subscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_ONLINE_SIGN_PER_FRAGMENT_INITIALIZE, IndexAndOnlineSignInitialData.class).subscribe(new Action1<IndexAndOnlineSignInitialData>() {
//            @Override
//            public void call(IndexAndOnlineSignInitialData indexAndOnlineSignInitialData) {
//                acetSignTime.setText(Util.trimString(indexAndOnlineSignInitialData.getApplyTime().substring(0, 11)));
//                int year = Integer.parseInt(Util.trimString(indexAndOnlineSignInitialData.getApplyTime().substring(0, 4)));
//                String arr[] = acetSignTime.getText().toString().split("-");
//                int month = Integer.parseInt(Util.trimString(arr[1]));
//                int day = Integer.parseInt(Util.trimString(arr[2]));
//                SignTime(year, month + 1, day);
//                qianyuetime = acetSignTime.getText().toString();
//                qianyuenianfen = acetSignYear.getText().toString();
//                startime = acetServiceCycleStart.getText().toString();
//                endtime = acetServiceCycleEnd.getText().toString();
//
//            }
//        });
//        if (mInitializeSubscription == null) {
//            mInitializeSubscription = new CompositeSubscription();
//        }
//        mInitializeSubscription.add(subscription);
//    }
//
//    private void unregisterInitializeSubscription() {
//        if (mInitializeSubscription != null && mInitializeSubscription.hasSubscriptions()) {
//            mInitializeSubscription.unsubscribe();
//        }
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        unregisterInitializeSubscription();
//    }
//
//    public static APartyFragment getAPartyFragment() {
//        if (instance == null) {
//            instance = new APartyFragment();
//        }
//        return instance;
//    }
//
//    @Override
//    protected void loadData() {
//        HeyibanFragment.getHeyibanFragment().setOnSuccessListener(this);
//
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_aparty_fragment;
//    }
//
//    @Override
//    protected void initData() {
//        context = getActivity();
//    }
//
//
//    @Override
//    protected void initView() {
//        instance = this;
//        if (mInitializeSubscription == null) {
//            registerInitializeSubscription();
//        }
//
//        signtime_layout.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                super.onNoDoubleClick(v);
//                String initial = getSignTimeString();
//                Util.showDateSelectDialog(getActivity(), initial, "a_party_fragment_sign_time", new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
//                        String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
//                        acetSignTime.setText(s);
//                        //  onSelectSignYear();
//                        SignTime(year, monthOfYear + 1, dayOfMonth);
//                        qianyuetime = acetSignTime.getText().toString();
//                        qianyuenianfen = acetSignYear.getText().toString();
//                        startime = acetServiceCycleStart.getText().toString();
//                        endtime = acetServiceCycleEnd.getText().toString();
//                    }
//                });
//            }
//        });
//
////        ivSignYear.setOnClickListener(new NoDoubleClickListener() {
////            @Override
////            public void onNoDoubleClick(View v) {
////                super.onNoDoubleClick(v);
////
////            }
////        });
//
////        ivServiceCycleStart.setOnClickListener(new NoDoubleClickListener() {
////            @Override
////            public void onNoDoubleClick(View v) {
////                super.onNoDoubleClick(v);
////                String initial = getServiceCycleStartString();
////                Util.showDateSelectDialog(getActivity(), initial, "a_party_fragment_service_cycle_start", new DatePickerDialog.OnDateSetListener() {
////                    @Override
////                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
////                        String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
////                        acetServiceCycleStart.setText(s);
////                    }
////                });
////
////            }
////        });
////
////        ivServiceCycleEnd.setOnClickListener(new NoDoubleClickListener() {
////            @Override
////            public void onNoDoubleClick(View v) {
////                super.onNoDoubleClick(v);
////                String initial = getServiceCycleEndString();
////                Util.showDateSelectDialog(getActivity(), initial, "a_party_fragment_service_cycle_end", new DatePickerDialog.OnDateSetListener() {
////                    @Override
////                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
////                        String s = Util.getCanonicalYearMonthDayString(year, monthOfYear + 1, dayOfMonth);
////                        acetServiceCycleEnd.setText(s);
////                    }
////                });
////            }
////        });
//    }
//
//
//    @Override
//    protected void initInject() {
//
//    }
//
//    private String getSignTimeString() {
//        String s = Util.trimString(acetSignTime.getText().toString());
//        return s;
//    }
//
//    private String getSignYearString() {
//        String s = Util.trimString(acetSignYear.getText().toString());
//        return s;
//    }
//
//    private String getServiceCycleStartString() {
//        String s = Util.trimString(acetServiceCycleStart.getText().toString());
//        return s;
//    }
//
//    private String getServiceCycleEndString() {
//        String s = Util.trimString(acetServiceCycleEnd.getText().toString());
//        return s;
//    }
//
//    private void onSelectSignYear() {
//        String title = Util.getResourceString(getActivity(), R.string.please_select_sign_year);
//        int currentYear = Util.getCurrentYearInt();
//        String yearString = getSignYearString();
//        if (!StringUtils.isBlank(yearString)) {
//            int yearInt = Util.getIntFromString(yearString);
//            currentYear = yearInt;
//        }
//        final MaterialNumberPicker numberPicker = new MaterialNumberPicker.Builder(getActivity())
//                .minValue(Constants.SIGN_YEAR_MIN_VALUE)
//                .maxValue(Constants.SIGN_YEAR_MAX_VALUE)
//                .defaultValue(currentYear)
//                .backgroundColor(Color.WHITE)
//                .separatorColor(Color.TRANSPARENT)
//                .textColor(Color.BLACK)
//                .textSize(20)
//                .enableFocusability(false)
//                .wrapSelectorWheel(true)
//                .build();
//        new AlertDialog.Builder(getActivity())
//                .setTitle(title)
//                .setView(numberPicker)
//                .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        int value = numberPicker.getValue();
//                        String valueString = String.valueOf(value);
//                        acetSignYear.setText(valueString);
////                        Util.showToast(getActivity(), valueString);
//                    }
//                })
//                .show();
//
//
//    }
////            (1: 服务时间及时生效)
////            (2: 服务时间下年生效)
////            (3: 服务时间当年生效)
////            (4: 服务时间上年生效)）
//
//    @Override
//    public void sendData(SignTeamBean signTeamBean) {
//        doctorName.setText(signTeamBean.getDoctorName() + "");
//    }
//
//    public void SignTime(int year, int month, int day) {
//        if (Identity.info.getFdSigningDoctorMode().equals("1")) {//年度签约
//            if (Identity.info.getFdIsDoctorServiceTime().equals("1")) {
//                acetSignYear.setText(year + "");
//                acetServiceCycleStart.setText(Util.getCanonicalYearMonthDayString(year, month, day));
//                if (day > 1) {
//                    acetServiceCycleEnd.setText(Util.getCanonicalYearMonthDayString(year + 1, month, day - 1));
//                } else {
//                    acetServiceCycleEnd.setText(Util.getCanonicalYearMonthDayString(year, 12, 31));
//                }
//
//            } else if (Identity.info.getFdIsDoctorServiceTime().equals("2")) {
//                acetSignYear.setText(year + "");
//                acetServiceCycleStart.setText(Util.getCanonicalYearMonthDayString(year + 1, 1, 1));
//                acetServiceCycleEnd.setText(Util.getCanonicalYearMonthDayString(year, 12, 31));
//
//            } else if (Identity.info.getFdIsDoctorServiceTime().equals("3")) {
//                acetSignYear.setText(year + "");
//                acetServiceCycleStart.setText(Util.getCanonicalYearMonthDayString(year, 1, 1));
//                acetServiceCycleEnd.setText(Util.getCanonicalYearMonthDayString(year, 12, 31));
//
//            } else if (Identity.info.getFdIsDoctorServiceTime().equals("4")) {
//                acetSignYear.setText(year + "");
//                acetServiceCycleStart.setText(Util.getCanonicalYearMonthDayString(year - 1, 1, 1));
//                acetServiceCycleEnd.setText(Util.getCanonicalYearMonthDayString(year - 1, 12, 31));
//
//            }
//        } else {
//            //随到随签约
//            if (month > 0 && month < 4) {
//                acetSignYear.setText(year + "");
//                acetServiceCycleStart.setText(Util.getCanonicalYearMonthDayString(year, 4, 1));
//                acetServiceCycleEnd.setText(Util.getCanonicalYearMonthDayString(year + 1, 3, 30));
//            } else if (month > 3 && month < 7) {
//                acetSignYear.setText(year + "");
//                acetServiceCycleStart.setText(Util.getCanonicalYearMonthDayString(year, 7, 1));
//                acetServiceCycleEnd.setText(Util.getCanonicalYearMonthDayString(year + 1, 6, 30));
//            } else if (month > 6 && month < 10) {
//                acetSignYear.setText(year + "");
//                acetServiceCycleStart.setText(Util.getCanonicalYearMonthDayString(year, 10, 1));
//                acetServiceCycleEnd.setText(Util.getCanonicalYearMonthDayString(year + 1, 9, 30));
//            } else {
//                acetSignYear.setText(year + "");
//                acetServiceCycleStart.setText(Util.getCanonicalYearMonthDayString(year + 1, 1, 1));
//                acetServiceCycleEnd.setText(Util.getCanonicalYearMonthDayString(year + 1, 12, 31));
//            }
//
//        }
//    }
//
//    @Override
//    public void sendSuccessinfo() {
//        acetSignTime.setText("");
//        acetSignYear.setText("");
//        acetServiceCycleStart.setText("");
//        acetServiceCycleEnd.setText("");
//    }
//}
