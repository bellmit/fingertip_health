package com.jqsoft.fingertip_health.http.httpmanafe;

import android.text.TextUtils;

import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SettingServerBean;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by zzwang on 2017/12/6.
 */

public class HttpManager {

    private static HttpManager mInstance;

//    @Inject
//    OkHttpClient mOkHttpClient;
    private OkHttpClient mOkHttpClient;

    public static final String HTTP_HEADER = "http://";
    /*private static final String HTTP_IP_PORTF = "192.168.44.113:8080";
    private static final String HTTP_IP_PORT = "192.168.44.113:8082";*/
//    private static final String HTTP_IP_PORTF = "192.168.44.113:8085";
//    private static final String HTTP_IP_PORT = "192.168.44.113:8085";


    private static final String HTTP_IP_PORTF = "192.168.44.14:8080";
    private static final String HTTP_IP_PORT = "192.168.44.14:8080";
 //   http://192.168.77.76:8080
//    private static final String HTTP_IP_PORTF = "192.168.44.19:8085";
//    private static final String HTTP_IP_PORT = "192.168.44.19:8085";
    private static String httpIpPort;//IP地址

    public static void setHttpIpPort(String httpIpPort) {
        HttpManager.httpIpPort = httpIpPort;
    }

    public String getHttpIpPort() {
        List<SettingServerBean> Serverlist = DataSupport.where("isUse=?","1").find(SettingServerBean.class);
        String surl=Serverlist.get(0).getIp();


        return surl;
       // return TextUtils.isEmpty(HttpManager.httpIpPort) ? HTTP_IP_PORT : httpIpPort;
    }
    public String getFeatureHttpIpPort() {

        return TextUtils.isEmpty(HttpManager.httpIpPort) ? HTTP_IP_PORTF : httpIpPort;
    }

    public String getHttpRequestOriginalUrl() {
        return String.format("%s%s",HTTP_HEADER,getHttpIpPort());
    }
    public String getFeatureHttpRequestOriginalUrl() {
        return String.format("%s%s",HTTP_HEADER,getFeatureHttpIpPort());
    }
    public static HttpManager getInstance(){
        if(null == mInstance){
            mInstance = new HttpManager();
        }
        return mInstance;
    }

    private HttpManager(){

        //如果不引入依赖注入,请反注释以下代码
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

    }

    /**
     * 人像比对
     * 传入用户姓名、身份证号和个人头像，
     * 返回人像与身份证照片相似度
     * 参数：用户照片BASE64
     * 返回：{"result":"00"
     * ,"result_time":"20171204102914"
     * ,"similarity":"91.970831"//相似度---》80及以上可断定照片与身份证号码符合
     * ,"biz_sequence_id":"gHYz6AYuLHXjINmJxys"}
     * @param name
     * @param id_num
     * @param img_base64
     *
     * @param callback
     */
    public void requestPhotoCheck(String json_web_token,String name, String id_num, String img_base64, Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("name", name)
                .add("id_num", id_num)
                .add("img_base64", img_base64)
                .build();
        Request request = new Request.Builder()
                .url(getHttpUrlByService("/peid/photo-check"))// .url(getHttpUrlByService("/fid/eilink/photoCheck"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);

    }


    /**
     * 人像比对
     * 传入用户姓名、身份证号和个人头像，
     * 返回人像与身份证照片相似度
     * 参数：用户照片BASE64
     * 返回：{"result":"00"
     * ,"result_time":"20171204102914"
     * ,"similarity":"91.970831"//相似度---》80及以上可断定照片与身份证号码符合
     * ,"biz_sequence_id":"gHYz6AYuLHXjINmJxys"}
     * @param name
     * @param id_num
     * @param img_base64
     *
     * @param callback
     */
    public void requestPhotoCheck2(String json_web_token,String name, String id_num, String head_base64,String img_base64,String password, Callback callback){
        RequestBody formBody = new FormBody.Builder()
//                .add("json_web_token", json_web_token)
                .add("name", name)
                .add("id_num", id_num)
                .add("head_base64", head_base64)
                .add("card_base64", img_base64)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(getHttpUrlByService("/peid/regist"))// .url(getHttpUrlByService("/fid/eilink/photoCheck"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);

    }


    /**
     * 人像比对
     * 传入用户姓名、身份证号和个人头像，
     * 返回人像与身份证照片相似度
     * 参数：用户照片BASE64
     * 返回：{"result":"00"
     * ,"result_time":"20171204102914"
     * ,"similarity":"91.970831"//相似度---》80及以上可断定照片与身份证号码符合
     * ,"biz_sequence_id":"gHYz6AYuLHXjINmJxys"}
     *
     * @param callback
     */
    public void requestPhotoCheck3(String json_web_token,String head_base64, Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("head_base64", head_base64)
                .build();
        Request request = new Request.Builder()
                .url(getHttpUrlByService("/sri/wechat/onlineApply!toApplyClassify.do"))// .url(getHttpUrlByService("/fid/eilink/photoCheck"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);

    }

    public void requestuser_list(String json_web_token,String head_base64, Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("name", head_base64)
                .build();
        Request request = new Request.Builder()
                .url(getHttpUrlByService("/peid/user_list"))// .url(getHttpUrlByService("/fid/eilink/photoCheck"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);

    }

    public void requestPhotoCheck4(String json_web_token,String id_num, String password,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("id_num", id_num)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(getHttpUrlByService("/peid/password_affirm"))// .url(getHttpUrlByService("/fid/eilink/photoCheck"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);

    }


    /**
     *两照比对
     * 传入两张人像照片，返回相似度
     * 参数：用户照片BASE64
     * 返回：{"result":"00"
     * ,"result_time":"20171204102914"
     * ,"similarity":"91.970831"//相似度---》80及以上可断定两张照片为同一人
     * ,"biz_sequence_id":"gHYz6AYuLHXjINmJxys"}
     */
    public void requestFaceCompCheck(String json_web_token,String img_base64_1,String img_base64_2,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("img_base64_1", img_base64_1)
                .add("img_base64_2", img_base64_2)
                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/face-compare"))//"/fid/eilink/facecompCheck"
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * * OCR简项
     * 传入用户身份证正面照、返回用户名称、身份证号码
     * 参数：用户照片BASE64
     * 返回：{"result":"00","idCard":"身份证号码","name":"姓名"}
     * @param img_base64_1
     * @param callback
     */
    public void requestOcrSimpleCheck(String json_web_token,String img_base64_1,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token",json_web_token)
                .add("img_base64", img_base64_1)
                .build();
        Request request = new Request.Builder()
                .url(getHttpUrlByService("/eilink/ocr-simple-check"))//"/fid/eilink/ocrSimpleCheck"
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    public void requestOcrMoreCheck(String img_base64_1,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("img_base64", img_base64_1)
                .build();
        Request request = new Request.Builder()
                .url(getHttpUrlByService("/fid/eilink/ocrMoreCheck"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    public void registerRequest(String json_web_token,String name,String code,String remark,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("name", name)
                .add("code", code)
                .add("remark", remark)
                .build();
        Request request = new Request.Builder()
                .url(getHttpUrlByService("/eilink/ocrMoreCheck"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }


    /**
     * * eid编码
     * 传入用户姓名、身份证号、返回EID编码。
     * 参数：姓名 身份证号码 string
     * 返回：{"result":"00","error_message":"错误信息。 当响应状态为：01、02、03、04时此项有值。","name":"姓名"}
     * @param
     * @param
     */

    public void requestediCode(String json_web_token,String name,String id_num,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("name", name)
                .add("id_num", id_num)
                .build();
        Request request = new Request.Builder()
                .url(getHttpUrlByService("/eilink/eid-code"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * * 人脸检测
     * 传入一张照片，返回是否包含人脸及人脸在照片中的坐标定位。
     * 参数：用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。","top":"人脸顶部坐标，仅respond为1时返回此项。",
     * "bottom":"人脸底部坐标","left":"人脸左侧坐标","right":"人脸右侧坐标"}
     * @param
     * @param
     */


    public void requestFacedetection(String json_web_token,String img_base64,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("img_base64", img_base64)
                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/detection"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * * 人脸比对
     * 传入一张照片，返回是否包含人脸及人脸在照片中的坐标定位。
     * 参数：用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。","simil_score":"人脸相似度,判断是同一个人的推荐阈值是：>=0.6。"
     *}
     * @param
     * @param
     */


    public void requestFaceCompare(String json_web_token,String img_base64_1,String img_base64_2,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("img_base64_1", img_base64_1)
                .add("img_base64_2", img_base64_2)
                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/face-compare"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
    /**
     * * 人脸集合创建
     * 传入一张照片，返回是否包含人脸及人脸在照片中的坐标定位。
     * 参数：用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。"}
     *
     * @param
     * @param
     */


    public void requestFacecreatefeatureset(String json_web_token,String name,String code,String remark,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("name", name)
                .add("code", code)
                .add("remark", remark)
                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/create-feature-set"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
    /**
     * * 人脸集合获取
     * 传入人脸集合编码，返回人脸集合的明细信息
     * 参数：用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。","code":"人脸集合编码","name":"人脸集合名称",
     * "remark":"人脸集合描述","featureCount":"集合中所包含的人脸数量"}
     *
     * @param
     * @param
     */


    public void requestFacegetfeatureset(String code,Callback callback){
//        RequestBody formBody = new FormBody.Builder()
//                .add("name", name)
//                .add("code", code)
//                .add("remark", remark)
//                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/get-feature-set"))
                .get()
                .addHeader("code",code)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
    /**
     * * 向人脸集合中添加照片
     * 向指定的人脸集合中添加一张人脸照片。
     * 参数：用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。"}
     *
     * @param
     * @param
     */


    public void requestFaceaddfeature(String json_web_token,String set_code,String biz_code,String img_base64,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("set_code", set_code)
                .add("biz_code", biz_code)
                .add("img_base64", img_base64)
                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/add-feature"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
    /**
     * * 人脸检索
     *传入一张人脸照片和人脸集合编码，检索出此人脸对应的业务属性。
     * 参数：	set_code人脸集合编码 用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。
     * "results":"
     检索结果集：
     [
     {
     "similScore":1.0 //相似度
     ,"biz_code":"1-A" //业务编码
     }
     ]
     如果检索出多条结果，按照相似度逆序排序。"
     * }
     *
     * @param
     * @param
     */


    public void requestFacesearch(String json_web_token,String set_code,String img_base64,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("set_code", set_code)
                .add("img_base64", img_base64)
                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/search"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
    private String getFeatureHttpUrlByService(String service){
        return getFeatureHttpRequestOriginalUrl() + service;
    }
    private String getHttpUrlByService(String service){
        return getHttpRequestOriginalUrl() + service;
    }
///////////////////////
    /**
     * * eid编码
     * 传入用户姓名、身份证号、返回EID编码。
     * 参数：姓名 身份证号码 string
     * 返回：{"result":"00","error_message":"错误信息。 当响应状态为：01、02、03、04时此项有值。","name":"姓名"}
     * @param
     * @param
     */

    public void requestediCode(String name,String id_num,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("name", name)
                .add("id_num", id_num)
                .build();
        Request request = new Request.Builder()
                .url(getHttpUrlByService("/eilink/eid-code"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * * 人脸检测
     * 传入一张照片，返回是否包含人脸及人脸在照片中的坐标定位。
     * 参数：用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。","top":"人脸顶部坐标，仅respond为1时返回此项。",
     * "bottom":"人脸底部坐标","left":"人脸左侧坐标","right":"人脸右侧坐标"}
     * @param
     * @param
     */


    public void requestFacedetection(String img_base64,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("img_base64", img_base64)
                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/detection"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * * 人脸比对
     * 传入一张照片，返回是否包含人脸及人脸在照片中的坐标定位。
     * 参数：用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。","simil_score":"人脸相似度,判断是同一个人的推荐阈值是：>=0.6。"
     *}
     * @param
     * @param
     */


    public void requestFaceCompare(String img_base64_1,String img_base64_2,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("img_base64_1", img_base64_1)
                .add("img_base64_2", img_base64_2)
                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/face-compare"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
    /**
     * * 人脸集合创建
     * 传入一张照片，返回是否包含人脸及人脸在照片中的坐标定位。
     * 参数：用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。"}
     *
     * @param
     * @param
     */


    public void requestFaceaddfeatur(String json_web_token,String set_code,String biz_code,String img_base64,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("json_web_token", json_web_token)
                .add("set_code", set_code)
                .add("biz_code", biz_code)
                .add("img_base64", img_base64)
                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/add-feature"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
    /**
     * * 人脸集合获取
     * 传入人脸集合编码，返回人脸集合的明细信息
     * 参数：用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。","code":"人脸集合编码","name":"人脸集合名称",
     * "remark":"人脸集合描述","featureCount":"集合中所包含的人脸数量"}
     *
     * @param
     * @param
     */


//    public void requestFacegetfeatureset(String code,Callback callback){
////        RequestBody formBody = new FormBody.Builder()
////                .add("name", name)
////                .add("code", code)
////                .add("remark", remark)
////                .build();
//        Request request = new Request.Builder()
//                .url(getHttpUrlByService("/fid/feature/get-feature-set"))
//                .get()
//                .addHeader("code",code)
//                .build();
//        mOkHttpClient.newCall(request).enqueue(callback);
//    }
    /**
     * * 向人脸集合中添加照片
     * 向指定的人脸集合中添加一张人脸照片。
     * 参数：用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。"}
     *
     * @param
     * @param
     */


    public void requestFaceaddfeature(String set_code,String biz_code,String img_base64,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("set_code", set_code)
                .add("biz_code", biz_code)
                .add("img_base64", img_base64)
                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/add-feature"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
    /**
     * * 人脸检索
     *传入一张人脸照片和人脸集合编码，检索出此人脸对应的业务属性。
     * 参数：	set_code人脸集合编码 用户照片BASE64
     * 返回：{"respond":"0","message":"失败信息。仅respond为0时返回此项。
     * "results":"
     检索结果集：
     [
     {
     "similScore":1.0 //相似度
     ,"biz_code":"1-A" //业务编码
     }
     ]
     如果检索出多条结果，按照相似度逆序排序。"
     * }
     *
     * @param
     * @param
     */


    public void requestFacesearch(String set_code,String img_base64,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("set_code", set_code)
                .add("img_base64", img_base64)
                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/feature/search"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }


    public void requestuserlist(String code,Callback callback){
//        RequestBody formBody = new FormBody.Builder()
//                .add("name", name)
//                .add("code", code)
//                .add("remark", remark)
//                .build();
        Request request = new Request.Builder()
                .url(getFeatureHttpUrlByService("/peid/user_list"))
                .get()
                .addHeader("name",code)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }



    public void requestPhotoChecknew(String json_web_token,String id_num, String password,Callback callback){
        RequestBody formBody = new FormBody.Builder()
                .add("phone", "18355377508")
                .add("code", "123456")
                .build();
        Request request = new Request.Builder()
                .url(getHttpUrlByService("/modile-boss/api/sms/sendVerificationCode"))// .url(getHttpUrlByService("/fid/eilink/photoCheck"))
                .post(formBody)
                .build();
        mOkHttpClient.newCall(request).enqueue(callback);

    }


}
