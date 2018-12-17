package com.jqsoft.fingertip_health.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.jqsoft.fingertip_health.bean.fingertip.DoctorBeanForFingertip;
import com.jqsoft.fingertip_health.bean.fingertip.LoginResultForFingertip;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginBean;
import com.jqsoft.fingertip_health.util.Util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by Administrator on 2017-09-11.
 * 当静态变量被系统回收并置为null时获取保存到SharedPreferences中的变量值
 */

public class IdentityManager {



    //指尖健康====================================
    public static String getDeviceForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getDevice());
        }
        return id;
    }

    public static String getTokenForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getToken());
        }
        return id;
    }

    public static String getUsernameForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getUsername());
        }
        return id;
    }

    public static String getPhoneForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getPhone());
        }
        return id;
    }

    public static String getOfficeForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getOffice());
        }
        return id;
    }

    public static String getOrgCodeForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getOrgCode());
        }
        return id;
    }

    public static String getAreaIdForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getAreaId());
        }
        return id;
    }

    public static String getOrgNameForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getOrgName());
        }
        return id;
    }

    public static String getUserLevelForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getUserLevel());
        }
        return id;
    }

    public static String getRealNameForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getRealName());
        }
        return id;
    }
    public static String getcmsTypeForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getCmsType());
        }
        return id;
    }
    public static String getCmsIcdAreaForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getCmsIcdArea());
        }
        return id;
    }
    public static List<DoctorBeanForFingertip> getDoctorForFingertip(Context context) {
        LoginResultForFingertip bean = getLoginResultBeanForFingertip(context);
        List<DoctorBeanForFingertip> result = null;
        if (bean != null) {
            result = bean.getDoctor();
        }
        return result;
    }


    //指尖健康====================================

    public static String getRoleId(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getRoleId());
        }
        return id;
    }

    public static String getAreaId(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getAreaId());
        }
        return id;
    }

    public static String getArea(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getArea());
        }
        return id;
    }

    public static String getAreaLevel(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getAreaLevel());
        }
        return id;
    }


    //登录成功的用户名
    public static String getLoginSuccessUsername(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getLoginSuccessUsername());
        }
        return id;
    }

    //登录成功的密码
    public static String getLoginSuccessPassword(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getLoginSuccessPassword());
        }
        return id;
    }

    //登录成功的密码
    public static String getSystemFileName(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getSystemFileName());
        }
        return id;
    }


    public static String getId(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString(bean.getId());
        }
        return id;
    }

    public static String getAge(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }



    public static String getPhotoUrl(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getAddress(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getCostName(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

//    public static String getAreaCode(Context context) {
//        SRCLoginBean bean = getSrcLoginResultBean(context);
//        String id = Constants.EMPTY_STRING;
//        if (bean!=null){
//            id= Util.trimString("");
//        }
//        return id;
//    }

    public static String getIsPregnantwomenInfoState(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getIsChildrenInfoState(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getIsPoorState(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getIsOldPeopleInfoState(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getIsPsychosisState(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getIsType2DiabetesState(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getIsHypertensionState(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getNo(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getSexName(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getPersonName(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String id = Constants.EMPTY_STRING;
        if (bean != null) {
            id = Util.trimString("");
        }
        return id;
    }

    public static String getPersonID(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String personID = Constants.EMPTY_STRING;
        if (bean != null) {
            personID = Util.trimString("");
        }
        return personID;
    }

    public static String getPhoneNumber(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String phoneNumber = Constants.EMPTY_STRING;
        if (bean != null) {
            phoneNumber = Util.trimString("");
        }
        return phoneNumber;
    }

    public static String getCardNo(Context context) {
        SRCLoginBean bean = getSrcLoginResultBean(context);
        String cardNo = Constants.EMPTY_STRING;
        if (bean != null) {
            cardNo = Util.trimString("");
        }
        return cardNo;
    }


    public static SRCLoginBean getSrcLoginResultBean(Context context) {
        SRCLoginBean bean = Identity.srcInfo;
        if (bean == null && context != null) {
            Object o = getObjectFromShare(context, Constants.SRC_LOGIN_RESULT_BEAN_KEY);
//            if (o!=null && o instanceof SRCLoginResultBean){
            if (o != null && o instanceof SRCLoginBean) {
                bean = (SRCLoginBean) o;
                Identity.srcInfo = bean;
            }
        }
        return bean;
    }

    public static LoginResultForFingertip getLoginResultBeanForFingertip(Context context) {
        LoginResultForFingertip bean = Identity.loginResultForFingertip;
        if (bean == null && context != null) {
            Object o = getObjectFromShare(context, Constants.LOGIN_RESULT_FOR_FINGERTIP_BEAN_KEY);
//            if (o!=null && o instanceof SRCLoginResultBean){
            if (o != null && o instanceof LoginResultForFingertip) {
                bean = (LoginResultForFingertip) o;
                Identity.loginResultForFingertip = bean;
            }
        }
        return bean;
    }



    public static boolean setObjectToShare(Context context, Object object,
                                           String key) {
        SharedPreferences share = PreferenceManager
                .getDefaultSharedPreferences(context);
        if (object == null) {
            SharedPreferences.Editor editor = share.edit().remove(key);
            return editor.commit();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
// 将对象放到OutputStream中
// 将对象转换成byte数组，并将其进行base64编码
        String objectStr = new String(Base64.encode(baos.toByteArray(),
                Base64.DEFAULT));
        try {
            baos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = share.edit();
// 将编码后的字符串写到base64.xml文件中
        editor.putString(key, objectStr);
        return editor.commit();
    }

    public static Object getObjectFromShare(Context context, String key) {
        SharedPreferences sharePre = PreferenceManager
                .getDefaultSharedPreferences(context);
        try {
            String wordBase64 = sharePre.getString(key, "");
// 将base64格式字符串还原成byte数组
            if (wordBase64 == null || wordBase64.equals("")) { // 不可少，否则在下面会报java.io.StreamCorruptedException
                return null;
            }
            byte[] objBytes = Base64.decode(wordBase64.getBytes(),
                    Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(objBytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
// 将byte数组转换成product对象
            Object obj = ois.readObject();
            bais.close();
            ois.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
