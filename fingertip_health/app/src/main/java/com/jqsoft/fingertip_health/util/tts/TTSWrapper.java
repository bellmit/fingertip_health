package com.jqsoft.fingertip_health.util.tts;

import android.content.Context;

import com.jqsoft.fingertip_health.util.Util;

/**
 * 语音合成封装
 * Created by Administrator on 2018-04-10.
 */

public class TTSWrapper {
    public static final int TTS_TYPE_IFLYTEK = 0;
    public static final int TTS_TYPE_SYSTEM = 1;

    public int ttsType = TTS_TYPE_IFLYTEK;
//    public int ttsType = TTS_TYPE_SYSTEM;
    public Context context;
    public static TTSWrapper instance = null;

    public TTSWrapper(Context context) {
        this.context = context;
    }

    public static TTSWrapper getInstance(Context context) {
        if (instance==null){
            synchronized (TTSWrapper.class){
                if (instance==null){
                    instance=new TTSWrapper(context);
                }
            }
        }
        return instance;
    }

    public void speak(String s){
        s= Util.trimString(s);
        if (TTS_TYPE_IFLYTEK == ttsType){
            TTSController.getInstance(context, s);
        } else if (TTS_TYPE_SYSTEM == ttsType){
            SpeechUtils.getInstance(context, s);
        }
    }

    public void stop(){
        if (TTS_TYPE_IFLYTEK == ttsType){
            TTSController.getInstance(context).stopSpeaking();
        } else if (TTS_TYPE_SYSTEM == ttsType){
            SpeechUtils.getInstance(context).stop();
        }

    }

    public void pause(){
        if (TTS_TYPE_IFLYTEK == ttsType){
            TTSController.getInstance(context).pauseSpeaking();
        } else if (TTS_TYPE_SYSTEM == ttsType){
            SpeechUtils.getInstance(context).stop();
        }

    }

    public void resume(){
        if (TTS_TYPE_IFLYTEK == ttsType){
            TTSController.getInstance(context).resumeSpeaking();
        } else if (TTS_TYPE_SYSTEM == ttsType){
//            SpeechUtils.getInstance(context);
        }

    }
}
