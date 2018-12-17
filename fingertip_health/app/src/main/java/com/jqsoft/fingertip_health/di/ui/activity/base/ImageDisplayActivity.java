package com.jqsoft.fingertip_health.di.ui.activity.base;

import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jqsoft.fingertip_health.R;
import com.jqsoft.fingertip_health.base.Constants;
import com.jqsoft.fingertip_health.util.Util;
import com.jqsoft.fingertip_health.utils2.RegexUtil;
import com.jqsoft.fingertip_health.utils3.util.StringUtils;
import com.shizhefei.view.largeimage.LargeImageView;

import butterknife.BindView;
//import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
//import cn.jpush.im.android.api.content.ImageContent;
//import cn.jpush.im.android.api.model.Message;

/**
 * Created by Administrator on 2017-06-19.
 */

public class ImageDisplayActivity extends AbstractActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageView)
    LargeImageView largeImageView;

    private String filePath;
//    private Message msg=null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_display;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setToolBar(toolbar, Constants.EMPTY_STRING);

//        largeImageView.setEnabled(true);

//        Serializable serializable = getDeliveredSerializableByKey(Constants.MESSAGE_KEY);
//        if (serializable!=null){
//            msg=(Message)serializable;
//        }
        filePath = getDeliveredStringByKey(Constants.IMAGE_FILE_PATH_KEY);
        filePath = Util.trimString(filePath);
        if (!StringUtils.isBlank(filePath)) {
            if (RegexUtil.checkNetworkImageUrl(filePath)) {
                Glide.with(this)
                        .load(filePath)
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                                  @Override
                                  public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                                      // do something with the bitmap
                                      // for demonstration purposes, let's just set it to an ImageView
                                      largeImageView.setImage(bitmap);
                                  }
                              }
                        );
                //                Glide.with(this).load(filePath).crossFade().into(new Target<GlideDrawable>() {
//                    @Override
//                    public void onLoadStarted(Drawable placeholder) {
//                        showProgressDialog();
//                    }
//
//                    @Override
//                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                        hideProgressDialog();
//                        showToast(Constants.HINT_LOADING_IMAGE_FAILURE);
//                    }
//
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                        hideProgressDialog();
//                        largeImageView.setImage(resource);
//                    }
//
//                    @Override
//                    public void onLoadCleared(Drawable placeholder) {
//
//                    }
//
//                    @Override
//                    public void getSize(SizeReadyCallback cb) {
//
//                    }
//
//                    @Override
//                    public void setRequest(Request request) {
//
//                    }
//
//                    @Override
//                    public Request getRequest() {
//                        return null;
//                    }
//
//                    @Override
//                    public void onStart() {
//
//                    }
//
//                    @Override
//                    public void onStop() {
//
//                    }
//
//                    @Override
//                    public void onDestroy() {
//
//                    }
//                });
            } else {
//                if (msg!=null) {
//                    final ImageContent imgContent = (ImageContent) msg.getContent();
//                    if (imgContent!=null){
//                        imgContent.downloadOriginImage(msg, new DownloadCompletionCallback() {
//                            @Override
//                            public void onComplete(int i, String s, File file) {
//                                if (i==Constants.JMESSAGE_RECEIVE_SUCCESS_CODE){
//                                    largeImageView.setImage(new FileBitmapDecoderFactory(file.getAbsolutePath()));
//                                } else {
//                                    largeImageView.setImage(new FileBitmapDecoderFactory(filePath));
//                                }
//                            }
//                        });
//                    } else {
//                        largeImageView.setImage(new FileBitmapDecoderFactory(filePath));
//                    }
//                } else {
//                    largeImageView.setImage(new FileBitmapDecoderFactory(filePath));
//                }

            }
        }

    }

    public void showProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Util.showGifProgressDialog(ImageDisplayActivity.this);

            }
        });
    }

    public void hideProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Util.hideGifProgressDialog(ImageDisplayActivity.this);
            }
        });
    }

    public void showToast(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Util.showToast(ImageDisplayActivity.this, s);
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
