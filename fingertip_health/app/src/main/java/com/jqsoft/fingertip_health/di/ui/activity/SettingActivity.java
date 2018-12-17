package com.jqsoft.fingertip_health.di.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
import com.jqsoft.fingertip_health.listener.NoDoubleClickListener;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.suke.widget.SwitchButton;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import butterknife.BindView;

/**
 * Created by Administrator on 2017-06-06.
 */

public class        SettingActivity extends AbstractActivity {

    @BindView(R.id.switch_push_enable_button)
    SwitchButton sbSwitchPushEnable;
    @BindView(R.id.tv_allowed_push_start_time)
    TextView tvAllowedPushStartTime;
    @BindView(R.id.tv_allowed_push_end_time)
    TextView tvAllowedPushEndTime;
    @BindView(R.id.tv_push_silence_start_time)
    TextView tvPushSilenceStartTime;
    @BindView(R.id.tv_push_silence_end_time)
    TextView tvPushSilenceEndTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar, "");

        initWhetherEnablePushToggle();
        initPushStartTimeAndEndTime();
        initPushSilenceStartAndEndTime();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void initWhetherEnablePushToggle() {
        boolean b = Util.getWhetherEnablePushFlag(this);
        sbSwitchPushEnable.setChecked(b);
        sbSwitchPushEnable.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                Util.saveWhetherEnablePushFlag(SettingActivity.this, isChecked);
            }
        });
    }

    public void initPushStartTimeAndEndTime() {
        String startTime = Util.getCanonicalAllowedPushStartTime(this);
        String endTime = Util.getCanonicalAllowedPushEndTime(this);
        tvAllowedPushStartTime.setText(startTime);
        tvAllowedPushEndTime.setText(endTime);

        tvAllowedPushStartTime.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                String s = Util.trimString(tvAllowedPushStartTime.getText().toString());
                s+= Constants.COLON_STRING+Constants.DOUBLE_ZERO_STRING;
                Util.showTimeSelectDialog(SettingActivity.this, s, "push_allowed_start_time_tag", new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
//                        String time = Util.getCanonicalHourString(hourOfDay);
                        String hour = String.valueOf(hourOfDay);
                        hour=Util.getCanonicalAllowedPushTimeHour(hour);
                        LogUtil.i("push_allowed_start_hour:" + hour);
                        tvAllowedPushStartTime.setText(hour);
//                        setPushTime();
                    }

                });

            }
        });

        tvAllowedPushEndTime.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                String s = Util.trimString(tvAllowedPushEndTime.getText().toString());
                s+= Constants.COLON_STRING+Constants.DOUBLE_ZERO_STRING;
                Util.showTimeSelectDialog(SettingActivity.this, s, "push_allowed_end_time_tag", new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
//                        String time = Util.getCanonicalHourString(hourOfDay);
                        String hour = String.valueOf(hourOfDay);
                        hour=Util.getCanonicalAllowedPushTimeHour(hour);
                        LogUtil.i("push_allowed_end_hour:" + hour);
                        tvAllowedPushEndTime.setText(hour);
//                        setPushTime();
                    }

                });

            }
        });
    }

    public void initPushSilenceStartAndEndTime(){
        String startTime = Util.getCanonicalPushSilenceStartTimeString(this);
        String endTime = Util.getCanonicalPushSilenceEndTimeString(this);
        tvPushSilenceStartTime.setText(startTime);
        tvPushSilenceEndTime.setText(endTime);

        tvPushSilenceStartTime.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                String s = Util.trimString(tvPushSilenceStartTime.getText().toString());
                Util.showTimeSelectDialog(SettingActivity.this, s, "push_silence_start_time_tag",
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                String hourString=Util.getCanonicalHourStringFromInteger(hourOfDay);
                                String minuteString = Util.getCanonicalMinuteStringFromInteger(minute);
                                String combinedString = hourString+Constants.COLON_STRING+minuteString;
                                LogUtil.i("push_silence_start_time:"+combinedString);
                                tvPushSilenceStartTime.setText(combinedString);
//                                setPushSilenceTime();
                            }
                        });
            }
        });

        tvPushSilenceEndTime.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                super.onNoDoubleClick(v);
                String s = Util.trimString(tvPushSilenceEndTime.getText().toString());
                Util.showTimeSelectDialog(SettingActivity.this, s, "push_silence_end_time_tag",
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                String hourString=Util.getCanonicalHourStringFromInteger(hourOfDay);
                                String minuteString = Util.getCanonicalMinuteStringFromInteger(minute);
                                String combinedString = hourString+Constants.COLON_STRING+minuteString;
                                LogUtil.i("push_silence_end_time:"+combinedString);
                                tvPushSilenceEndTime.setText(combinedString);
//                                setPushSilenceTime();
                            }
                        });
            }
        });
    }

    public String getPushStartTime() {
        return Util.trimString(tvAllowedPushStartTime.getText().toString());
    }

    public String getPushEndTime() {
        return Util.trimString(tvAllowedPushEndTime.getText().toString());
    }

    public String getPushSilenceStartTime() {
        return Util.trimString(tvPushSilenceStartTime.getText().toString());
    }

    public String getPushSilenceEndTime() {
        return Util.trimString(tvPushSilenceEndTime.getText().toString());
    }

//    public void setPushTime(){
//        String start = Util.trimString(tvAllowedPushStartTime.getText().toString());
//        String end = Util.trimString(tvAllowedPushEndTime.getText().toString());
//        Util.setAllowedPushTime(this, start, end);
//    }
//
//    public void setPushSilenceTime(){
//        String psst = getPushSilenceStartTime();
//        String pset = getPushSilenceEndTime();
//        String[] startTimeArray = Util.getCanonicalHourMinuteStringArray(psst);
//        String[] endTimeArray = Util.getCanonicalHourMinuteStringArray(pset);
//
//        String startHourString = Constants.DOUBLE_ZERO_STRING;
//        String startMinuteString = Constants.DOUBLE_ZERO_STRING;
//        String endHourString = Constants.DOUBLE_ZERO_STRING;
//        String endMinuteString = Constants.DOUBLE_ZERO_STRING;
//        if (startTimeArray!=null&&startTimeArray.length==2){
//            startHourString=startTimeArray[0];
//            startMinuteString=startTimeArray[1];
//        }
//        if (endTimeArray!=null&&endTimeArray.length==2){
//            endHourString=endTimeArray[0];
//            endMinuteString=endTimeArray[1];
//        }
//        Util.setPushSilenceTime(this, startHourString, startMinuteString, endHourString, endMinuteString);
//    }

}
