package com.jqsoft.fingertip_health.helper.video_upload;

import android.app.Activity;
import android.os.Looper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.base.Version;
import com.jqsoft.fingertip_health.bean.VideoBackBean;
import com.jqsoft.fingertip_health.bean.grassroots_civil_administration.base.GCAHttpResultBaseBean;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 上传视频
 * Created by Administrator on 2018-05-08.
 */

public class UploadVideoManager {
    public static final String UPLOAD_VIDEO_FAILURE_MSG="上传视频失败";
    public  static UploadVideoManager manager;
    Activity activity;
    UploadVideoThread thread;
    UploadVideoCallback callback;

    public static UploadVideoManager getInstance(Activity activity){
        if (manager==null){
            synchronized (UploadVideoManager.class){
                if (manager==null){
                    manager=new UploadVideoManager(activity);
                }
            }
        }
        return manager;
    }

    public UploadVideoManager(Activity activity) {
        super();
        this.activity=activity;
    }

    public UploadVideoCallback getCallback() {
        return callback;
    }

    public void setCallback(UploadVideoCallback callback) {
        this.callback = callback;
    }

    public interface UploadVideoCallback {
        void onUploadSuccess(GCAHttpResultBaseBean<VideoBackBean> result, String fileName);
        void onUploadFailure(String msg);
    }

    public void cancelThread(){
        try {
            if (thread!=null && thread.isAlive()){
                thread.interrupt();
                thread=null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startUploadVideo(String filePath){
        try {
            filePath= Util.trimString(filePath);
            File file = new File(filePath);
            if (file!=null&&file.exists()){
                cancelThread();
                thread=new UploadVideoThread(filePath);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendFailureResult();
        }
    }

    public void sendSuccessResult(final GCAHttpResultBaseBean<VideoBackBean> result, final String fileName){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (callback!=null){
                    callback.onUploadSuccess(result, fileName);
                }
            }
        });
    }

    public void sendFailureResult() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sendFailureResult(UPLOAD_VIDEO_FAILURE_MSG);
            }
        });
    }

     private void sendFailureResult(String msg){
        if (callback!=null){
            msg=Util.trimString(msg);
            if (StringUtils.isBlank(msg)){
                msg=UPLOAD_VIDEO_FAILURE_MSG;
            }
            callback.onUploadFailure(msg);
        }
    }

    public class UploadVideoThread extends Thread {
        String filePath;

        public UploadVideoThread(String filePath) {
            super();
            this.filePath = filePath;
        }


        @Override
        public void run() {
            // TODO Auto-generated method stub
            Looper.prepare();
            super.run();
            uploadFile(filePath);
            Looper.loop();
        }

        private void uploadFile(String videoFilePath) {
//		String actionUrl = TEST_UPLOAD_VIDEO_URL;
            String actionUrl = Version.HTTP_UPLOAD_VIDEO_URL;
//		String actionUrl = "http://192.168.1.26:8080/test1/servlet/Videoservlet";
            try {
                URL url = new URL(actionUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setConnectTimeout(Constants.OKHTTP_CLIENT_CONNECT_TIMEOUT_SECONDS*1000);
                con.setReadTimeout(Constants.OKHTTP_CLIENT_READ_TIMEOUT_SECONDS*1000);

                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);

                con.setRequestMethod("POST");

                DataOutputStream ds = new DataOutputStream(con.getOutputStream());
                File file = new File(videoFilePath);
                String fileName = file.getName();

                FileInputStream fStream = new FileInputStream(file);
                int bufferSize = 1024 * 1024;
                byte[] buffer = new byte[bufferSize];

                int length = -1;

                while ((length = fStream.read(buffer)) != -1) {

                    ds.write(buffer, 0, length);
                }

                fStream.close();
                ds.flush();

                int responseCode = con.getResponseCode();
                if (responseCode==HttpURLConnection.HTTP_OK || responseCode==HttpURLConnection.HTTP_CREATED
                        || responseCode==HttpURLConnection.HTTP_ACCEPTED){
                    InputStream is = con.getInputStream();
                    String result = getInputStreamDataString(is);
//                    int ch;
//                    StringBuffer b = new StringBuffer();
//                    while ((ch = is.read()) != -1) {
//                        b.append((char) ch);
//                    }
//                    String result = b.toString();
                    LogUtil.i("video upload result:"+result);
                    GCAHttpResultBaseBean<VideoBackBean> beanResult = JSON.parseObject(result,
                            new TypeReference<GCAHttpResultBaseBean<VideoBackBean>>() {});

                    ds.close();
                    LogUtil.i("video upload success");
                    sendSuccessResult(beanResult, fileName);

                } else {
                    LogUtil.i("video upload failure");
                    sendFailureResult();

                }

            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.i("video upload failure");
                sendFailureResult();
            }
        }

        private String getInputStreamDataString(InputStream is) throws IOException {
            byte[] maxBuffer = new byte[1024 * 64];
//            int length = 0;
            int lengthTemp = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while (-1 != (lengthTemp = is.read(maxBuffer))) {
                baos.write(maxBuffer, 0, lengthTemp);
//                length += lengthTemp;
            }

            byte[] encodedData = baos.toByteArray();
            String result = new String(encodedData);
            is.close();

            return result;
        }

    }


}
