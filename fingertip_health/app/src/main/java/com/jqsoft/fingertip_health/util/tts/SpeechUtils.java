package com.jqsoft.fingertip_health.util.tts;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.Locale;

/**
 * Created by zhenqiang on 2016/12/9.
 */

public class SpeechUtils {
    private Context context;


    private static final String TAG = "SpeechUtils";
    private static SpeechUtils singleton;

    private TextToSpeech textToSpeech; // TTS对象

    private boolean isInitSuccess = false;

    public static SpeechUtils getInstance(Context context, String s) {
        if (singleton == null) {
            synchronized (SpeechUtils.class) {
                if (singleton == null) {
                    singleton = new SpeechUtils(context, s);
                } else {
                    singleton.checkInitStatusAndSpeak(s);
                }
            }
        } else {
            singleton.checkInitStatusAndSpeak(s);
        }
        return singleton;
    }

    public static SpeechUtils getInstance(Context context) {
        if (singleton == null) {
            synchronized (SpeechUtils.class) {
                if (singleton == null) {
                    singleton = new SpeechUtils(context);
                }
            }
        }
        return singleton;
    }

    public SpeechUtils(final Context context, final String s) {
        this.context = context;
        checkInitStatusAndSpeak(s);
    }

    public SpeechUtils(final Context context) {
        this.context = context;
    }

    private void checkInitStatusAndSpeak(final String s){
        if (!isInitSuccess) {
            textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int i) {
                    if (i == TextToSpeech.SUCCESS) {
                        isInitSuccess = true;
//                        textToSpeech.setLanguage(Locale.CHINA);
                        textToSpeech.setLanguage(Locale.US);
                        textToSpeech.setPitch(1.0f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                        textToSpeech.setSpeechRate(1.0f);

                        speakText(s);
                    } else {
                        Util.showToast(context, "语音合成引擎初始化失败");
                    }
                }
            });
        } else {
            speakText(s);
        }
    }

    public void speakText(String text) {
        if (!isInitSuccess) {
            return;
        }
        if (StringUtils.isBlank(text)) {
            return;
        }
        if (textToSpeech != null) {
            textToSpeech.speak(text,
                    TextToSpeech.QUEUE_FLUSH, null);
        }

    }

    public void stop(){
        if (textToSpeech != null) {
            textToSpeech.stop();
        }

    }

}