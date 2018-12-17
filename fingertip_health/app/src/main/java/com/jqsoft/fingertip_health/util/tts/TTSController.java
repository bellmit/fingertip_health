package com.jqsoft.fingertip_health.util.tts;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.autonavi.tbt.TrafficFacilityInfo;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.utils.LogUtil;

import java.io.File;

/**
 * 语音播报组件
 */
public class TTSController implements AMapNaviListener {

    private Context mContext;
    private static TTSController ttsManager;
    private SpeechSynthesizer mTts;

    private boolean isInitSuccess = false;


    /**
     * 初始化监听。
     */
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d("SHIXIN", "InitListener init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(mContext, "语音初始化失败,错误码：" + code, Toast.LENGTH_SHORT).show();
                ;
            } else {
                isInitSuccess=true;
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };

    private TTSController(Context context) {
        mContext = context;
    }


    private TTSController(Context context, final String s) {
        mContext = context;
        checkInitStatusAndSpeak(s);
    }

    private void checkInitStatusAndSpeak(final String s){
        if (isInitSuccess){
            startSpeaking(s);
        } else {
            initAppId();
            mTts = SpeechSynthesizer.createSynthesizer(mContext, new InitListener() {
                @Override
                public void onInit(int code) {
                    if (code != ErrorCode.SUCCESS) {
                        Toast.makeText(mContext, "语音初始化失败,错误码：" + code, Toast.LENGTH_SHORT).show();
                    } else {
                        isInitSuccess=true;
                        startSpeaking(s);
                    }

                }
            });
//        mTts = SpeechSynthesizer.createSynthesizer(mContext, null);
            initSpeechSynthesizer();

        }

    }

    public static TTSController getInstance(Context context) {
        if (ttsManager == null) {
            ttsManager = new TTSController(context);
        }
        return ttsManager;
    }

    public static TTSController getInstance(Context context, String s) {
        if (ttsManager == null) {
            ttsManager = new TTSController(context, s);
        } else {
            ttsManager.checkInitStatusAndSpeak(s);
        }
        return ttsManager;
    }

    public void init() {
        if (isInitSuccess){
            return;
        }

        initAppId();

        // 初始化合成对象.
        mTts = SpeechSynthesizer.createSynthesizer(mContext, mTtsInitListener);
//        mTts = SpeechSynthesizer.createSynthesizer(mContext, null);
        initSpeechSynthesizer();
    }

    private void initAppId(){
        String text = Version.AMAP_TTS_IFLY_ID;
//        String text = mContext.getString(R.string.xunfei_app_id);
//        if ("57b3c4a9".equals(text)) {
//            throw new IllegalArgumentException("你不应该用Demo中默认KEY,去讯飞官网申请吧!");
//        }
//        SpeechUtility su = SpeechUtility.createUtility(mContext, "appid=" + text);
        SpeechUtility su = SpeechUtility.createUtility(mContext, SpeechConstant.APPID+"=" + text);
        LogUtil.i("SpeechUtility:"+su);

    }
//    public void init() {
//        String text = Version.AMAP_TTS_IFLY_ID;
////        String text = mContext.getString(R.string.xunfei_app_id);
////        if ("57b3c4a9".equals(text)) {
////            throw new IllegalArgumentException("你不应该用Demo中默认KEY,去讯飞官网申请吧!");
////        }
////        SpeechUtility su = SpeechUtility.createUtility(mContext, "appid=" + text);
//        SpeechUtility su = SpeechUtility.createUtility(mContext, SpeechConstant.APPID+"=" + text);
//        LogUtil.i("SpeechUtility:"+su);
//
//        // 初始化合成对象.
//        mTts = SpeechSynthesizer.createSynthesizer(mContext, mTtsInitListener);
////        mTts = SpeechSynthesizer.createSynthesizer(mContext, null);
//        initSpeechSynthesizer();
//    }

    public void pauseSpeaking(){
        if (mTts!=null){
            mTts.pauseSpeaking();
        }
    }


    public void resumeSpeaking(){
        if (mTts!=null){
            mTts.resumeSpeaking();
        }
    }

    /**
     * 使用SpeechSynthesizer合成语音，不弹出合成Dialog.
     *
     * @param
     */
    public void startSpeaking(String playText) {
        // 进行语音合成.
        if (mTts != null)
            mTts.startSpeaking(playText, new SynthesizerListener() {

                @Override
                public void onSpeakResumed() {

                }

                @Override
                public void onSpeakProgress(int arg0, int arg1, int arg2) {

                }

                @Override
                public void onSpeakPaused() {

                }

                @Override
                public void onSpeakBegin() {

                }

                @Override
                public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {

                }

                @Override
                public void onCompleted(SpeechError arg0) {

                }

                @Override
                public void onBufferProgress(int arg0, int arg1, int arg2, String arg3) {

                }
            });

    }

    public void stopSpeaking() {
        if (mTts != null)
            mTts.stopSpeaking();
    }

    private void initSpeechSynthesizer() {
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置在线合成发音人
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, "50");
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, "50");
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + File.pathSeparator+ Version.TTS_AUDIO_FOLDER_NAME +"/msc/tts.wav");
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.wav");

    }

    public void destroy() {
        if (mTts != null) {
            mTts.stopSpeaking();
            mTts.destroy();
            ttsManager=null;
        }
    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onInitNaviSuccess() {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onGetNavigationText(int i, String s) {
        startSpeaking(s);
    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onEndEmulatorNavi() {

    }

    @Override
    public void onArriveDestination() {

    }

//    @Override
//    public void onCalculateRouteSuccess() {
//
//    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onGpsOpenStatus(boolean b) {

    }

    @Override
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

    }

    @Override
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {

    }

    @Override
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {

    }

    @Override
    public void hideCross() {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

//    @Override
//    public void onCalculateMultipleRoutesSuccess(int[] ints) {
//
//    }

    @Override
    public void notifyParallelRoad(int i) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

    }

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

    }

    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

    }

    @Override
    public void onPlayRing(int i) {

    }
}