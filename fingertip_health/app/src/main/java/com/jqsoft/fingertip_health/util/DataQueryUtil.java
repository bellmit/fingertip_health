package com.jqsoft.fingertip_health.util;

import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.bean.fingertip.gdws_sys_area;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.SRCLoginDataDictionaryBean;
import com.jqsoft.fingertip_health.bean.resident.SRCLoginAreaBean;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.ListUtils;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-01-25.
 */

public class DataQueryUtil {

    public static String getAreaLevelFromAreaCodeNew(String areaCode){
        if (StringUtils.isBlank(areaCode)){
            return Constants.EMPTY_STRING;
        } else {
            List<gdws_sys_area>  list = DataSupport.where("code=? ",areaCode ).find(gdws_sys_area.class);
            if (!ListUtils.isEmpty(list)){
                gdws_sys_area area = list.get(0);
                return area.getArea_level();
            } else {
                return Constants.EMPTY_STRING;
            }
        }
    }

    public static String getAreaLevelFromAreaCode(String areaCode){
        if (StringUtils.isBlank(areaCode)){
            return Constants.EMPTY_STRING;
        } else {
            List<SRCLoginAreaBean>  list = DataSupport.where("areaCode=? and state=?",areaCode ,"0").find(SRCLoginAreaBean.class);
            if (!ListUtils.isEmpty(list)){
                SRCLoginAreaBean area = list.get(0);
                return area.getAreaLevel();
            } else {
                return Constants.EMPTY_STRING;
            }
        }
    }

    public static String getSelectedAreaNameFromSelectedAreaCodeAndAreaLevel(String selectedAreaCode, String areaLevel) {
        String result = getDefaultAreaSelectTextFromAreaLevel(areaLevel);
        if (Constants.AREA_CODE_ALL.equals(selectedAreaCode)){
            return "全部";
        } else if (StringUtils.isBlank(selectedAreaCode) || StringUtils.isBlank(areaLevel)) {
            return result;
        } else {
            areaLevel=Util.trimString(areaLevel);
            selectedAreaCode=Util.trimString(selectedAreaCode);
            List<SRCLoginAreaBean> list = new ArrayList<>();
            list = DataSupport.where(" areaLevel=? and areaCode=? and state=?", areaLevel, selectedAreaCode,"0").find(SRCLoginAreaBean.class);
            if (ListUtils.isEmpty(list)) {
                return result;
            } else {
                SRCLoginAreaBean bean = list.get(0);
                return bean.getAreaName();
            }
        }
    }

    public static SRCLoginAreaBean getSelectedAreaBeanFromSelectedAreaCodeAndAreaLevel(String selectedAreaCode, String areaLevel) {
        SRCLoginAreaBean result = null;
        if (StringUtils.isBlank(selectedAreaCode) || StringUtils.isBlank(areaLevel)) {
            return result;
        } else {
            areaLevel=Util.trimString(areaLevel);
            selectedAreaCode=Util.trimString(selectedAreaCode);
            List<SRCLoginAreaBean> list = new ArrayList<>();
            list = DataSupport.where(" areaLevel=? and areaCode=? and state=?", areaLevel, selectedAreaCode,"0").find(SRCLoginAreaBean.class);
            if (ListUtils.isEmpty(list)) {
                return result;
            } else {
                SRCLoginAreaBean bean = list.get(0);
                return bean;
            }
        }
    }

    public static gdws_sys_area getSelectedAreaBeanFromSelectedAreaCodeAndAreaLevelnew(String selectedAreaCode, String areaLevel) {
        gdws_sys_area result = null;
        if (StringUtils.isBlank(selectedAreaCode) || StringUtils.isBlank(areaLevel)) {
            return result;
        } else {
            areaLevel=Util.trimString(areaLevel);
            selectedAreaCode=Util.trimString(selectedAreaCode);
            List<gdws_sys_area> list = new ArrayList<>();
            list = DataSupport.where(" area_level=? and code=? ", areaLevel, selectedAreaCode).find(gdws_sys_area.class);
            if (ListUtils.isEmpty(list)) {
                return result;
            } else {
                gdws_sys_area bean = list.get(0);
                return bean;
            }
        }
    }

    public static String getDefaultAreaSelectTextFromAreaLevel(String areaLevel){
        String result = Constants.EMPTY_STRING;
        if (Constants.AREA_LEVEL_NATION.equals(areaLevel)){
            result = "请选择国家";
        } else if (Constants.AREA_LEVEL_PROVINCE.equals(areaLevel)){
            result = "请选择省(直辖市)";
        } else if (Constants.AREA_LEVEL_CITY.equals(areaLevel)){
            result = "请选择市";
        } else if (Constants.AREA_LEVEL_COUNTY.equals(areaLevel)){
            result = "请选择区(县)";
        } else if (Constants.AREA_LEVEL_STREET.equals(areaLevel)){
            result = "请选择街道(乡镇)";
        } else if (Constants.AREA_LEVEL_VILLAGE.equals(areaLevel)){
            result = "请选择社区(村)";
        }
        return result;
    }

     public static int getSelectedPositionFromSelectedAreaCodeAndBeanList(String selectedAreaCode, List<SRCLoginAreaBean> areaList){
        if (Constants.AREA_CODE_ALL.equals(selectedAreaCode) || StringUtils.isBlank(selectedAreaCode) || ListUtils.isEmpty(areaList)){
            return 0;
        } else {
            int pos = 0;
            for (int i = 0; i < areaList.size(); ++i){
                SRCLoginAreaBean bean = areaList.get(i);
                if (selectedAreaCode.equals(bean.getAreaCode())){
                    pos=i;
                    break;
                }
            }
            return pos;
        }
    }

    public static String getAreaCodeFromAreaLevel(String areaLevel){
         long start = System.currentTimeMillis();
         areaLevel=Util.trimString(areaLevel);
         List<SRCLoginAreaBean> result = new ArrayList<>();
         result = DataSupport.where("areaLevel=? and state=?", areaLevel,"0").find(SRCLoginAreaBean.class);
//         int count = DataSupport.where("areaLevel=? and state=?", areaLevel,"0").count(gdws_sys_area.class);
        long end = System.currentTimeMillis();
        long duration = (end-start);
        LogUtil.i("getAreaCodeFromAreaLevel:"+areaLevel+" consumed "+duration/1000+"second");
         if (ListUtils.getSize(result)==1){
//         if (count==1){
             SRCLoginAreaBean first = result.get(0);
             return first.getAreaCode();
         } else {
             return Constants.EMPTY_STRING;
         }
    }

    //获取区划数据
    public static List<SRCLoginAreaBean> getAreaListFromAreaLevelAndNonNullableAreaPid(String areaLevel, String areaPid) {
        areaLevel=Util.trimString(areaLevel);
        areaPid=Util.trimString(areaPid);
        List<SRCLoginAreaBean> result = new ArrayList<>();
        List<SRCLoginAreaBean> list = new ArrayList<>();
        list = DataSupport.where(" areaLevel=? and areaPid=? and state=?", areaLevel, areaPid,"0").find(SRCLoginAreaBean.class);
        if (list == null) {
            return result;
        } else {
            return list;
        }

    }

    public static List<SRCLoginAreaBean> getAreaListFromAreaLevelAndNullableAreaPid(String areaLevel, String areaPid) {
        List<SRCLoginAreaBean> result = new ArrayList<>();
        List<SRCLoginAreaBean> list = new ArrayList<>();
        if (StringUtils.isBlank(areaLevel) && StringUtils.isBlank(areaPid)) {
            list = null;
        } else if (!StringUtils.isBlank(areaLevel) && StringUtils.isBlank(areaPid)) {
            list = DataSupport.where(" areaLevel=? and state=?", areaLevel,"0").find(SRCLoginAreaBean.class);
        } else if (StringUtils.isBlank(areaLevel) && !StringUtils.isBlank(areaPid)) {
            list = DataSupport.where(" areaPid=? and state=?", areaPid,"0").find(SRCLoginAreaBean.class);
        } else {
            list = DataSupport.where(" areaLevel=? and areaPid=? and state=?", areaLevel, areaPid,"0").find(SRCLoginAreaBean.class);
        }
        if (list == null) {
            return result;
        } else {
            return list;
        }

    }

    //从数据字典取pcode为传入参数值的列表
    public static List<SRCLoginDataDictionaryBean> getDataDictionaryFromPCode(String pcode) {
        List<SRCLoginDataDictionaryBean> result = new ArrayList<>();
        if (StringUtils.isBlank(pcode)) {
            return result;
        } else {
            List<SRCLoginDataDictionaryBean> list = DataSupport.where(" pcode=? and state=?", pcode,"0").find(SRCLoginDataDictionaryBean.class);
            if (list == null) {
                return result;
            } else {
                return list;
            }
        }
    }
}
