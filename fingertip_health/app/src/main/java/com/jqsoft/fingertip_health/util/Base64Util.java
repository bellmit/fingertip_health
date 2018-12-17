package com.jqsoft.fingertip_health.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zzwang on 2017/12/6.
 */

public class Base64Util {

    public static String bitmaptoString(Bitmap bitmap) {
        return bitmaptoString(bitmap,true);
    }

    public static String bitmaptoString(Bitmap bitmap,boolean needrecycle) {
        if(null == bitmap) return null;
        // 将Bitmap转换成字符串
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bStream);

        byte[] bytes = bStream.toByteArray();

        String str = Base64.encodeToString(bytes, Base64.NO_WRAP);
        if(needrecycle && bitmap !=null && !bitmap.isRecycled()){
            bitmap.recycle();
            bitmap = null;
        }
        return str;

    }

    public static String imageToBase64(String path){
        if(TextUtils.isEmpty(path)){
            return null;
        }
        InputStream is = null;
        byte[] data = null;
        String result = null;
        try{
            is = new FileInputStream(path);
            //创建一个字符流大小的数组。
            data = new byte[is.available()];
            //写入数组
            is.read(data);
            //用默认的编码格式进行编码
            result = Base64.encodeToString(data,Base64.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null !=is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }

    public static Bitmap base64ToBmp(String source){
        //将字符串转换成Bitmap类型
        Bitmap bitmap=null;
        try {
            byte[]bitmapArray;
            bitmapArray=Base64.decode(source, Base64.DEFAULT);
            bitmap= BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;

    }


}
