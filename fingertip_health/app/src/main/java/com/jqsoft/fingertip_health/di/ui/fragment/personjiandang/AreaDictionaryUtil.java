package com.jqsoft.fingertip_health.di.ui.fragment.personjiandang;

import android.content.Context;


import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.IdentityManager;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_sys_area;
import com.jqsoft.fingertip_health.util.ListUtils;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-05-30.
 */

public class AreaDictionaryUtil {
//    public static String getLoginPersonAreaCode(Context context){
//        if (context==null){
//            return Constants.EMPTY_STRING;
//        } else {
//            String result = IdentityManager.getManagementDivisionCode(context);
//            return result;
//        }
//    }

//    public static String getLoginPersonAreaLevel(Context context){
//        String areaCode = getLoginPersonAreaCode(context);
//        String result = getAreaLevelFromAreaCode(areaCode);
//        return result;
//    }

    public static List<gdws_sys_area> getAreaDataBeanListFromAreaLevelAndParentAreaCode(String areaLevel, String parentAreaCode){
        areaLevel= Util.trimString(areaLevel);
        parentAreaCode=Util.trimString(parentAreaCode);
        if (StringUtils.isBlank(areaLevel) || StringUtils.isBlank(parentAreaCode)){
            List<gdws_sys_area> result = new ArrayList<>();
            return result;
        } else {
            List<gdws_sys_area> list = DataSupport.where("area_level = ? and p_code = ?", areaLevel, parentAreaCode)
                    .find(gdws_sys_area.class);
            if (list==null){
                list=new ArrayList<>();
            }
            return list;
        }
    }

    public static List<gdws_sys_area> getAreaDataBeanListFromAreaLevel(String areaLevel){
        areaLevel=Util.trimString(areaLevel);
        if (StringUtils.isBlank(areaLevel)){
            List<gdws_sys_area> result = new ArrayList<>();
            return result;
        } else {
            List<gdws_sys_area> list = DataSupport.where("area_level = ?", areaLevel).find(gdws_sys_area.class);
            if (list==null){
                list=new ArrayList<>();
            }
            return list;
        }
    }

    public static gdws_sys_area getParentAreaDataBeanFromAreaCode(String areaCode){
        String parentAreaCode = getParentAreaCodeFromAreaCode(areaCode);
        gdws_sys_area parentBean = getAreaDataBeanFromAreaCode(parentAreaCode);
        return parentBean;
    }

    public static String getParentAreaCodeFromAreaCode(String areaCode){
        gdws_sys_area areaData = getAreaDataBeanFromAreaCode(areaCode);
        if (areaData!=null){
            String result = Util.trimString(areaData.getP_code());
            return result;
        } else {
            return Constants.EMPTY_STRING;
        }
    }

    public static String getAreaNameFromAreaCode(String areaCode){
        gdws_sys_area areaData = getAreaDataBeanFromAreaCode(areaCode);
        if (areaData!=null){
            String result = Util.trimString(areaData.getName());
            return result;
        } else {
            return Constants.EMPTY_STRING;
        }
    }

    public static String getAreaLevelFromAreaCode(String areaCode){
        gdws_sys_area areaData = getAreaDataBeanFromAreaCode(areaCode);
        if (areaData!=null){
            String result = Util.trimString(areaData.getArea_level());
            return result;
        } else {
            return Constants.EMPTY_STRING;
        }
    }

    public static String getAreaLevelNameFromAreaCode(String areaCode){
        gdws_sys_area areaData = getAreaDataBeanFromAreaCode(areaCode);
        if (areaData!=null){
            String result = Util.trimString(areaData.getArea_level());
            return result;
        } else {
            return Constants.EMPTY_STRING;
        }
    }

    public static gdws_sys_area getAreaDataBeanFromAreaCode(String areaCode){
        areaCode= Util.trimString(areaCode);
        if (StringUtils.isBlank(areaCode)){
            return null;
        } else {
            List<gdws_sys_area> list = DataSupport.where("code = ?",areaCode).find(gdws_sys_area.class);
            if(!ListUtils.isEmpty(list)){
                gdws_sys_area areaData = list.get(0);
                return areaData;
            } else {
                return null;
            }
        }
    }
    public static gdws_sys_area getAreaDataBeanFromAreaCodeAndAreaLevel(String areaCode, String areaLevel){
        areaCode= Util.trimString(areaCode);
        areaLevel = Util.trimString(areaLevel);
        if (StringUtils.isBlank(areaCode) || StringUtils.isBlank(areaLevel)){
            return null;
        } else {
            List<gdws_sys_area> list = DataSupport.where("code = ? and area_level = ?",areaCode, areaLevel)
                    .find(gdws_sys_area.class);
            if(!ListUtils.isEmpty(list)){
                gdws_sys_area areaData = list.get(0);
                return areaData;
            } else {
                return null;
            }
        }
    }
}
