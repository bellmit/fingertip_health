package com.jqsoft.fingertip_health.base;

import com.blankj.utilcode.utils.EncryptUtils;
import com.jqsoft.fingertip_health.bean.AreaBean;
import com.jqsoft.fingertip_health.bean.fingertip.DoctorBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.LoginResultForFingertip;
import com.jqsoft.fingertip_health.bean.parameter.LoginParameters;
import com.jqsoft.fingertip_health.bean.parameter.base.CommonParameters;
import com.jqsoft.fingertip_health.bean.request.LoginRequestBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginBean;
import com.jqsoft.fingertip_health.bean.response.LoginResultBean;
import com.jqsoft.fingertip_health.bean.response_new.LoginResultBean2;
import com.jqsoft.fingertip_health.util.HttpParameterAnnotationProcessor;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by Administrator on 2017-05-17.
 */

public class Identity {
    /*
 ------------------------------------------------
 登录后返回的用户信息
 */
    public static String uid;                    // string	用户ID
    public static String username;        //	string	登录名
    public static String realname;        //	string	真实姓名
    public static String token = "";               //	string	用户令牌
    public static List<AreaBean> area;                //	string	地区，如：“340000,340001”
    public static String role;                //	string	管理员
    // ------------------------------------------------

    public static AreaBean currentArea;  //当前选中的area
//    public static UserInfo imUserInfo;      //jmessage登录成功bean
//    public static UserInfo targetUserInfo;  //当前与本登录用户聊天的bean


//    public static String loginSuccessUsername;
//    public static String loginSuccessPassword;
    public static LoginResultBean2 info;//登录成功后返回的数据

    public static SRCLoginBean srcInfo;//签约居民端登录成功后返回的数据

    public static LoginResultForFingertip loginResultForFingertip;

    public static boolean shouldReadIdCard = true;
    public static boolean isDestroyedBySystem = false;

    private static Identity identity;

    public static Identity getInstance() {
        if (identity == null) {
            synchronized (Identity.class) {
                if (identity == null) {
                    identity = new Identity();
                }
            }
        }
        return identity;
    }


    public static String getDeviceForFingertip(){
        if (loginResultForFingertip == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(loginResultForFingertip.getDevice());
        }

    }

    public static String getTokenForFingertip(){
        if (loginResultForFingertip == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(loginResultForFingertip.getToken());
        }

    }

    public static String getUsernameForFingertip(){
        if (loginResultForFingertip == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(loginResultForFingertip.getUsername());
        }

    }

    public static String getOfficeForFingertip(){
        if (loginResultForFingertip == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(loginResultForFingertip.getOffice());
        }

    }

    public static String getOrgCodeForFingertip(){
        if (loginResultForFingertip == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(loginResultForFingertip.getOrgCode());
        }

    }

    public static String getAreaIdForFingertip(){
        if (loginResultForFingertip == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(loginResultForFingertip.getAreaId());
        }

    }

    public static String getOrgNameForFingertip(){
        if (loginResultForFingertip == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(loginResultForFingertip.getOrgName());
        }

    }

    public static String getUserLevelForFingertip(){
        if (loginResultForFingertip == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(loginResultForFingertip.getUserLevel());
        }

    }

    public static String getRealNameForFingertip(){
        if (loginResultForFingertip == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(loginResultForFingertip.getRealName());
        }

    }

    public static List<DoctorBeanForFingertip> getDoctorForFingertip(){
        if (loginResultForFingertip == null) {
            return null;
        } else {
            return loginResultForFingertip.getDoctor();
        }

    }


//    public static String getId(){
//        if (srcInfo==null){
//            return Constants.EMPTY_STRING;
//        } else {
//            String result = Util.trimString(srcInfo.getId());
//            return result;
//        }
//    }

//    public static String getPersonID(){
//        if (srcInfo==null){
//            return Constants.EMPTY_STRING;
//        } else {
//            String result = Util.trimString(srcInfo.getPersonID());
//            return result;
//        }
//    }

//    public static String getPhoneNumber(){
//        if (srcInfo==null){
//            return Constants.EMPTY_STRING;
//        } else {
//            String result = Util.trimString(srcInfo.getPhone());
//            return result;
//        }
//    }

//    public static String getCardNo(){
//        if (srcInfo==null){
//            return Constants.EMPTY_STRING;
//        } else {
//            String result = Util.trimString(srcInfo.getCardNo());
//            return result;
//        }
//    }

    public static String getIsEnableInput(){
        if (info == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(info.getIsEnableInput());
        }

    }

//    public static String getLoginSuccessUsername() {
//        String result = Util.trimString(loginSuccessUsername);
//        return result;
//    }

//    public static String getLoginSuccessPassword() {
//        String result = Util.trimString(loginSuccessPassword);
//        return result;
//    }

    public static String getUserId() {
        if (info == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(info.getGuserid());
        }
    }

    public static String getOrganizationKey() {
        if (info == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(info.getSorganizationkey());
        }
    }

    public static String getOrganizationName() {
        if (info == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(info.getSorganizationname());
        }
    }

    public static String getUsername() {
        if (info == null) {
            return Constants.EMPTY_STRING;
        } else {
            return Util.trimString(info.getSusername());
        }
    }

//    public static String getCurrentAppKey() {
//        if (imUserInfo == null) {
//            return Constants.EMPTY_STRING;
//        } else {
//            String result = imUserInfo.getAppKey();
//            return result;
//        }
//    }
//
//    public static String getJMessageId() {
//        if (imUserInfo == null) {
//            return Constants.EMPTY_STRING;
//        } else {
//            long id = imUserInfo.getUserID();
//            String s = String.valueOf(id);
//            return s;
//        }
//    }
//
//    public static String getDisplayTitle() {
//        if (imUserInfo == null) {
//            return realname;
//        } else {
//            String title = imUserInfo.getNotename();
//            if (TextUtils.isEmpty(title)) {
//                title = imUserInfo.getNickname();
//                if (TextUtils.isEmpty(title)) {
//                    title = imUserInfo.getUserName();
//                }
//            }
//            return title;
//        }
//
//    }
//
//    public static String getTargetDisplayTitle(UserInfo mUserInfo) {
//        if (mUserInfo == null) {
//            return Constants.EMPTY_STRING;
//        } else {
//            String title = mUserInfo.getNotename();
//            if (TextUtils.isEmpty(title)) {
//                title = mUserInfo.getNickname();
//                if (TextUtils.isEmpty(title)) {
//                    title = mUserInfo.getUserName();
//                }
//            }
//            return title;
//        }
//
//    }

    public static AreaBean getCurrentArea() {
        if (currentArea != null) {
            return currentArea;
        } else {
            if (!ListUtils.isEmpty(area)) {
                currentArea = area.get(0);
                return currentArea;
            } else {
                return currentArea;
            }
        }
    }

    public static void setCurrentArea(AreaBean ab) {
        currentArea = ab;
    }

    public static String getCurrentAreaCode() {
        AreaBean ab = getCurrentArea();
        String result = Constants.EMPTY_STRING;
        if (ab != null) {
            result = ab.getCode();
        } else {
            result = Constants.EMPTY_STRING;
        }
        result = Util.trimString(result);
        return result;
    }

    public static String getCurrentAreaName() {
        AreaBean ab = getCurrentArea();
        String result = Constants.EMPTY_STRING;
        if (ab != null) {
            result = ab.getName();
        } else {
            result = Constants.EMPTY_STRING;
        }
        result = Util.trimString(result);
        return result;
    }

    public static void initWithData(LoginResultBean lrb) {
        if (lrb != null) {
            Identity.uid = lrb.getUid();
            Identity.username = lrb.getUsername();
            Identity.realname = lrb.getRealname();
            Identity.token = lrb.getToken();
            Identity.area = lrb.getArea();
            Identity.role = lrb.getRole();
        }
    }

    public Map<String, String> getLoginMapFromUsernameAndPassword(String username, String password) {
        Identity identity = Identity.getInstance();
        CommonParameters cp = identity.getCommonParametersObject();
        LoginParameters lp = new LoginParameters(cp.getAppkey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV(),
                username, password);
//        Map<String , String> map = getParametersKeysAndValuesExcludeSignature(lp);
        Map<String, String> map = getParametersKeysAndValuesExcludeSignatureAndToken(lp);
        String signature = getSignatureForMap(map);
        if (signature != null) {
            signature = signature.toLowerCase();
        }
        map.put(Version.SIG_KEY, signature);
        LogUtil.i("login map:" + map.toString());
        return map;
    }

    public LoginRequestBean getLoginBeanFromUsernameAndPassword(String username, String password) {
        Identity identity = Identity.getInstance();
        CommonParameters cp = identity.getCommonParametersObject();
        LoginParameters lp = new LoginParameters(cp.getAppkey(), cp.getTimestamp(), cp.getToken(), cp.getSig(), cp.getV(),
                username, password);
//        Map<String , String> map = getParametersKeysAndValuesExcludeSignature(lp);
        Map<String, String> map = getParametersKeysAndValuesExcludeSignatureAndToken(lp);
        String signature = getSignatureForMap(map);
        if (signature != null) {
            signature = signature.toLowerCase();
        }
        map.put(Version.SIG_KEY, signature);
        LogUtil.i("login map:" + map.toString());

        LoginRequestBean result = new LoginRequestBean();
        result.setAppkey(lp.getAppkey());
        result.setPassword(lp.getPassword());
        result.setTimestamp(lp.getTimestamp());
        result.setToken(lp.getToken());
        result.setV(lp.getV());
        result.setSig(signature);
        result.setUsername(lp.getUsername());
        return result;
    }

    public Map<String, String> getParametersKeysAndValuesExcludeSignature(Object object) {
//        String[] keyArray = Util.getFiledNameArray(object);
//        String s = Util.getStringFromStringArray(keyArray);
//        LogUtil.i("keyArray:"+s);
        Map<String, String> result = HttpParameterAnnotationProcessor.getFieldNameAndValueMap(object);
        result.remove(Version.SIG_KEY);
        return result;
    }

    public Map<String, String> getParametersKeysAndValuesExcludeSignatureAndToken(Object object) {
//        String[] keyArray = Util.getFiledNameArray(object);
//        String s = Util.getStringFromStringArray(keyArray);
//        LogUtil.i("keyArray:"+s);
        Map<String, String> result = HttpParameterAnnotationProcessor.getFieldNameAndValueMap(object);
        result.remove(Version.SIG_KEY);
        result.remove(Version.TOKEN_KEY);
        return result;
    }

    public String getSignatureForMap(Map<String, String> map) {
        if (map == null) {
            return Constants.EMPTY_STRING;
        } else {
            Set<String> keys = map.keySet();
            List<String> keyList = new ArrayList<>(keys);
            LogUtil.i("keyList except sig:" + ListUtils.join(keyList, Constants.COMMA_STRING));
            Collections.sort(keyList, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            LogUtil.i("sorted keyList except sig:" + ListUtils.join(keyList, Constants.COMMA_STRING));
            String combinedString = "";
            for (int i = 0; i < keyList.size(); ++i) {
                String key = keyList.get(i);
                String value = map.get(key);
//                String encodedValue = Util.encodeString(value);
                if (!StringUtils.isBlank(value)) {
                    combinedString += key;
//                    combinedString+= encodedValue;
                    combinedString += value;
                }
            }
            combinedString += Version.PRIVATE_KEY;
//            String sha1 = EncryptUtils.encryptSHA1ToString(combinedString);
            String md5 = EncryptUtils.encryptMD5ToString(combinedString);
            LogUtil.i("plain text:" + combinedString);
            LogUtil.i("cypher text:" + md5);
            return md5;
        }
    }


    public CommonParameters getCommonParametersObject() {
        CommonParameters cp = new CommonParameters();
        cp.setAppkey(Version.PUBLIC_KEY);
        cp.setTimestamp(getCurrentTimestamp());
        cp.setToken(Identity.token);
        cp.setSig(Constants.EMPTY_STRING);
        cp.setV(Version.VERSION);
        return cp;

    }

    public String getCurrentTimestamp() {
        long time = System.currentTimeMillis();
        return String.valueOf(time);
    }
}
