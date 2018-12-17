package com.jqsoft.fingertip_health.base;

/**
 * Created by Administrator on 2017-05-26.
 */

public class Version {
    /**
     * app/build.gradle中的配置覆盖了下面的值----------------------------------
     */
    //http请求url
    //内网
    // String HTTP_URL = "http://192.168.44.226:9092/fdss-api/";
//     String HTTP_URL = "http://192.168.44.226:9092/fdss-api/";
    //外网
   // public static String HTTP_URL = "http://60.173.247.168:9092/fdss-api/";

    public static String HTTP_URL = "http://192.168.44.226:9092/fdss-api/apis/";
    public static String HTTP_UPLOAD_VIDEO_URL = "";
    public static String HTTP_UPLOAD_VIDEO_URL_HOST_PREFIX = "";
    // ceshi liuchao
//    String HTTP_URL="http://192.168.88.36:8082/fdss-api/";
    //wangaimei
    //    String HTTP_URL="http://192.168.88.37:8080/fdss-api/";
    //  String HTTP_URL="http://60.173.247.168:9092/fdss-api/";
    // ceshi liuchao
    //  String HTTP_URL="http://192.168.88.36:8080/fdss-api/";


    //登录界面应用程序名称
    public static String LOGIN_APP_NAME = "合肥市高新区家庭医生签约";

    //public static String FILE_URL_BASE ="http://192.168.44.134:8080/sri";
    //public static String FIND_FILE_URL_BASE ="http://192.168.44.134:8080/sri";

    public static String FILE_URL_BASE ="http://60.173.247.168:8060/sri";
    public static String FIND_FILE_URL_BASE ="http://60.173.247.168:8060/sri";
//    String FILE_URL_BASE = "ip:port/fdss-api/photo/";
    // String FILE_URL_BASE = "ip:port/fdss-api/photo/";

    //bugly app id
    public static String BUGLY_APP_ID = "ca50ce3b12";
    /**
     * app/build.gradle中的配置覆盖了上面的值----------------------------------
     */


    //tts id
    public static String AMAP_TTS_IFLY_ID = "5a65a619";


    //极光推送
    public static boolean JPUSH_DEBUG_MODE = false;

    //jmessage
    public static boolean JMESSAGE_DEBUG_MODE = false;
    //是否启用消息漫游
    public static boolean JMESSAGE_ENABLE_ROAMING = false;

    //LeakCanary
    public static boolean ENABLE_LEAK_CANARY = false;

    //strict mode
    public static boolean ENABLE_STRICT_MODE = false;

    //是否调用Log.x方法打印日志
    public static boolean DEBUG_MODE = true;
    //BUGLY debug mode
    public static boolean BUGLY_DEBUG_MODE = false;


    public static String PUBLIC_KEY = "android";  //公钥
    public static String PRIVATE_KEY = "android"; //私钥
    public static String VERSION = "1.0";      //版本号


    public static String SIG_KEY = "sig";
    public static String TOKEN_KEY = "token";
    public static String DEVICE_MANUFACTURER_PASSWORD_STRING = "";

    public static String SIGN = "JQSOFT65350880";

    public static String APP_FOLDER_NAME = "fingertip_health";

    //在线语音合成时保存的语音文件的文件夹名称
    public static String TTS_AUDIO_FOLDER_NAME = "grassroots_civil_administration_platform_tts_audio";

    public static String PHOTO_VIDEO_SAVE_PATH = "/photo";
    public static String PHOTO_VIDEO_TEMP_NAME = "temp_photo";
    public static String VOICE_SAVE_PATH = "/voice";

    public static String JMESSAGE_PICTURE_FOLDER_PATH = "sdcard/" + Version.APP_FOLDER_NAME + "/pictures/";
    public static String JMESSAGE_VOICE_FOLDER_PATH = "sdcard/" + Version.APP_FOLDER_NAME + "/voice/";
    public static String JMESSAGE_FILE_FOLDER_PATH = "sdcard/" + Version.APP_FOLDER_NAME + "/recvFiles/";


    //================================================
    public static String LOGIN_METHOD_DEVICE_STRING = "A_Android_Gdws";
    public static String LOGIN_METHOD_DEVICE_VALUE_STRING = "b6ad5bae90951705230d9c5e9a1c30c0";
//    public static String NONLOGIN_METHOD_DEVICE_STRING = "";//由特定规则产生的客户端用户标识
//    public static String NONLOGIN_METHOD_TOKEN_STRING = "";//会话期密钥
}
