package com.jqsoft.fingertip_health.util;

import com.jqsoft.fingertip_health.annotation.HttpParameter;
import com.jqsoft.fingertip_health.utils.LogUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpParameterAnnotationProcessor {

    public static Map<String, String> getFieldNameAndValueMap(Object o)  {
        Map<String, String> map = new HashMap<>();
        try {
            Class<?> clazz = o.getClass();
            Field[] selfFields = clazz.getDeclaredFields();
            List<Field> fields = new ArrayList<Field>();
            Class<?> superclass = clazz.getSuperclass();
            while (superclass!=null){

                Field[] parentFields = superclass.getDeclaredFields();
                fields.addAll(Arrays.asList(parentFields));
                superclass=superclass.getSuperclass();
            }
            fields.addAll(Arrays.asList(selfFields));
            if (fields!=null){
                for (Field field : fields){
                    HttpParameter hp = field.getAnnotation(HttpParameter.class);
                    if (hp!=null){
                        String fieldName = field.getName();
                        Object fieldObject = Util.getFieldValueByName(fieldName, o);
//                        String fieldValue = (String) fieldObject;
                        String fieldValue = String.valueOf(fieldObject);
                        map.put(fieldName, fieldValue);
//                        LogUtil.i("field which has annotation HttpParameter:"+fieldName+","+fieldValue);
                    } else {
//                        LogUtil.i("hp == null");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i("exception in getFieldNameAndValueMap:"+e.getMessage());
        }
        LogUtil.i("getFieldNameAndValueMap map:"+ map.toString());
//        LogUtil.i("map:"+ MapUtils.toJson(map));
        return map;
    }
}