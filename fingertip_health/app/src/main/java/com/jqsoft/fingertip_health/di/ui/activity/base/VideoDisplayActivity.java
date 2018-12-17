package com.jqsoft.fingertip_health.di.ui.activity.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.danikula.videocache.HttpProxyCacheServer;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.di_app.DaggerApplication;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils.LogUtil;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * 视频播放器
 * Created by Administrator on 2018-05-07.
 */

public class VideoDisplayActivity extends AbstractActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.jcvps_video_player)
    JCVideoPlayerStandard jcvpsVideoPlayer;

    String videoUrl;

    String demoUrl = "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_display;
    }

    @Override
    protected void initData() {
        videoUrl=getDeliveredStringByKey(Constants.VIDEO_URL_KEY);
        videoUrl= Util.trimString(videoUrl);
    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

//        videoUrl=demoUrl;

        if (!StringUtils.isBlank(videoUrl)) {
            LogUtil.i("video url:"+videoUrl);

            String ultimateUrl = videoUrl;
            if (Util.isUrlNetwork(videoUrl)){
                HttpProxyCacheServer proxy = DaggerApplication.getVideoCacheProxy(this);
                String proxyUrl = proxy.getProxyUrl(videoUrl);
                ultimateUrl=proxyUrl;
//            videoView.setVideoPath(proxyUrl);
            }

            LogUtil.i("ultimate video url:"+ultimateUrl);
            jcvpsVideoPlayer.setUp(ultimateUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, Constants.EMPTY_STRING);
//            jcvpsVideoPlayer.setUp(videoUrl, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, Constants.EMPTY_STRING);
            jcvpsVideoPlayer.startVideo();
        }

    }

    private void clearJcVideoPlayerCache(){
        JCVideoPlayer.clearSavedProgress(this, videoUrl);
    }



    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initInject() {
        super.initInject();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearJcVideoPlayerCache();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
