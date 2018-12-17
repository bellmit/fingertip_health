//package com.jqsoft.grassroots_civil_administration_platform.jmessage.messages;
//
//
//import android.Manifest;
//import android.content.Context;
//import android.content.Intent;
//import android.content.res.Resources;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.NonNull;
//import android.text.TextUtils;
//import android.text.format.DateFormat;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.target.Target;
//import com.jqsoft.fingertip_health.R;
//import com.jqsoft.fingertip_health.base.Constants;
//import com.jqsoft.fingertip_health.base.Identity;
//import com.jqsoft.fingertip_health.base.Version;
//import com.jqsoft.fingertip_health.bean.MessageListRefreshConfigurationBean;
//import com.jqsoft.fingertip_health.di.ui.activity.base.AbstractActivity;
//import com.jqsoft.fingertip_health.di.ui.activity.base.ImageDisplayActivity;
//import com.jqsoft.fingertip_health.jmessage.models.DefaultUser;
//import com.jqsoft.fingertip_health.jmessage.models.MyMessage;
//import com.jqsoft.fingertip_health.jmessage.views.ChatView;
//import com.jqsoft.fingertip_health.rx.RxBus;
//import com.jqsoft.fingertip_health.util.Util;
//import com.jqsoft.fingertip_health.utils.LogUtil;
//import com.jqsoft.fingertip_health.utils3.util.FileUtils;
//import com.jqsoft.fingertip_health.utils3.util.ListUtils;
//import com.jqsoft.fingertip_health.utils3.util.StringUtils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Locale;
//import java.util.Queue;
//
//import cn.jiguang.imui.chatinput.ChatInputView;
//import cn.jiguang.imui.chatinput.listener.OnCameraCallbackListener;
//import cn.jiguang.imui.chatinput.listener.OnMenuClickListener;
//import cn.jiguang.imui.chatinput.listener.RecordVoiceListener;
//import cn.jiguang.imui.chatinput.model.FileItem;
//import cn.jiguang.imui.commons.ImageLoader;
//import cn.jiguang.imui.commons.models.IMessage;
//import cn.jiguang.imui.messages.MsgListAdapter;
//import cn.jpush.im.android.api.JMessageClient;
//import cn.jpush.im.android.api.callback.DownloadCompletionCallback;
//import cn.jpush.im.android.api.content.FileContent;
//import cn.jpush.im.android.api.content.ImageContent;
//import cn.jpush.im.android.api.content.TextContent;
//import cn.jpush.im.android.api.content.VoiceContent;
//import cn.jpush.im.android.api.enums.ConversationType;
//import cn.jpush.im.android.api.enums.MessageDirect;
//import cn.jpush.im.android.api.event.MessageEvent;
//import cn.jpush.im.android.api.event.OfflineMessageEvent;
//import cn.jpush.im.android.api.exceptions.JMFileSizeExceedException;
//import cn.jpush.im.android.api.model.Conversation;
//import cn.jpush.im.android.api.model.Message;
//import cn.jpush.im.android.api.model.UserInfo;
//import cn.jpush.im.api.BasicCallback;
//import pub.devrel.easypermissions.AppSettingsDialog;
//import pub.devrel.easypermissions.EasyPermissions;
//import rx.Subscription;
//import rx.functions.Action1;
//import rx.subscriptions.CompositeSubscription;
//
//public class MessageListActivity extends AbstractActivity implements ChatView.OnKeyboardChangedListener,
//        ChatView.OnSizeChangedListener, View.OnTouchListener, EasyPermissions.PermissionCallbacks {
//
//    private final int RC_RECORD_VOICE = 0x0001;
//    private final int RC_CAMERA = 0x0002;
//    private final int RC_PHOTO = 0x0003;
//
//    private ChatView mChatView;
//    private MsgListAdapter<MyMessage> mAdapter;
//    private List<MyMessage> mData;
//
//    private String toolbarTitle;
//    private String targetId;
//    private String targetAppKey;
//
//    private UserInfo targetUserInfo;
//
//    private InputMethodManager mImm;
//    private Window mWindow;
//    private Conversation mConv;
//
//    //发送图片消息的队列
//    private Queue<Message> mMsgQueue = new LinkedList<Message>();
//
//    private CompositeSubscription mShouldRefreshSubscription;
//
//    private void registerShouldRefreshEvent(){
//        Subscription mSubscription = RxBus.getDefault().toObservable(Constants.EVENT_TYPE_SHOULD_REFRESH_CHAT_MESSAGE, MessageListRefreshConfigurationBean.class).subscribe(new Action1<MessageListRefreshConfigurationBean>() {
//            @Override
//            public void call(MessageListRefreshConfigurationBean messageListRefreshConfigurationBean) {
//                if (messageListRefreshConfigurationBean!=null){
//                    toolbarTitle=messageListRefreshConfigurationBean.getTitle();
//                    targetId=messageListRefreshConfigurationBean.getTargetId();
//                    targetAppKey=messageListRefreshConfigurationBean.getAppkey();
//                    initWithTitleAndIdAndAppkey(toolbarTitle, targetId, targetAppKey);
//                }
//            }
//
//        });
//        if (mShouldRefreshSubscription==null){
//            mShouldRefreshSubscription=new CompositeSubscription();
//        }
//        mShouldRefreshSubscription.add(mSubscription);
//    }
//
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_chat_jmessage;
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initView() {
//
//    }
//
//    @Override
//    protected void loadData() {
//        if (mShouldRefreshSubscription==null){
//            registerShouldRefreshEvent();
//        }
//
//    }
//
//    private void sendImageOrVideoList(List<Message> mMsgList) {
//        if (ListUtils.isEmpty(mMsgList)){
//            return;
//        }
//        showGifProgressDialog();
//        mMsgQueue.clear();
//        for (Message msg : mMsgList) {
////            if (msg.getStatus() == MessageStatus.created
////                    && msg.getContentType() == ContentType.image) {
//                mMsgQueue.offer(msg);
////            }
//        }
//
//        if (mMsgQueue.size() > 0) {
//            Message message = mMsgQueue.element();
//            if (mConv.getType() == ConversationType.single) {
//                UserInfo userInfo = (UserInfo) message.getTargetInfo();
//                sendNextImageOrVideoMsg(message);
////                if (userInfo.isFriend()) {
////                    sendNextImageOrVideoMsg(message);
////                } else {
////                    CustomContent customContent = new CustomContent();
////                    customContent.setBooleanValue("notFriend", true);
////                    Message customMsg = mConv.createSendMessage(customContent);
////                    addMsgToList(customMsg);
////                }
//            } else {
//                sendNextImageOrVideoMsg(message);
//            }
//
////            notifyDataSetChanged();
//        } else {
//            hideGifProgressDialog();
//        }
//    }
//
//    /**
//     * 从发送队列中出列，并发送图片
//     *
//     * @param msg 图片消息
//     */
//    private void sendNextImageOrVideoMsg(final Message msg) {
//        msg.setOnSendCompleteCallback(new BasicCallback() {
//            @Override
//            public void gotResult(int i, String s) {
//                //出列
//                mMsgQueue.poll();
//                //如果队列不为空，则继续发送下一张
//                if (!mMsgQueue.isEmpty()) {
//                    sendNextImageOrVideoMsg(mMsgQueue.element());
//                } else {
//                    hideGifProgressDialog();
//                }
//                MyMessage mm = getMyMessageFromMessage(msg);
//                addToStart(mm);
////                notifyDataSetChanged();
//            }
//        });
//        JMessageClient.sendMessage(msg);
//    }
//
//    public void showGifProgressDialog(){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Util.showGifProgressDialog(MessageListActivity.this);
//
//            }
//        });
//    }
//
//    public void hideGifProgressDialog(){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                Util.hideGifProgressDialog(MessageListActivity.this);
//
//            }
//        });
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_chat_jmessage);
//
////        registerEventReceiver();
//
//        init();
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        init();
//    }
//
//    public void addMessagesFromNewest(){
//        List<Message> newestList = mConv.getMessagesFromNewest(0, Constants.NEWEST_MESSAGE_LIST_SIZE);
//        Util.reverseList(newestList);
//        if (newestList!=null){
//            for (int i = 0; i < newestList.size(); ++i){
//                Message msg = newestList.get(i);
//                MyMessage mm = getMyMessageFromMessage(msg);
//                mData.add(0, mm);
//            }
//        }
//        addToEnd(mData);
//        refreshView();
//    }
//
//    public void initWithTitleAndIdAndAppkey(String title, String targetId, String targetAppKey){
//        this.toolbarTitle = title;
//        this.targetId=targetId;
//        this.targetAppKey=targetAppKey;
//
//        mConv = JMessageClient.getSingleConversation(targetId, targetAppKey);
////        mChatView.setChatTitle(mTitle);
//        if (mConv == null) {
//            mConv = Conversation.createSingleConversation(targetId, targetAppKey);
//        }
//        targetUserInfo = (UserInfo) mConv.getTargetInfo();
////        mChatAdapter = new MsgListAdapter(mContext, mConv, longClickListener);
//
//
//
//        this.mImm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        mWindow = getWindow();
//
//        mChatView = (ChatView) findViewById(R.id.chat_view);
//        mChatView.initModule();
//        mChatView.setTitle(toolbarTitle);
////        mData = getMessages();
////        initMsgAdapter();
//
//        mData=new ArrayList<>();
//        initMsgAdapter();
//
//        addMessagesFromNewest();
//
//
//        mChatView.setKeyboardChangedListener(this);
//        mChatView.setOnSizeChangedListener(this);
//        mChatView.setOnTouchListener(this);
//        mChatView.setMenuClickListener(new OnMenuClickListener() {
//            @Override
//            public boolean onSendTextMessage(CharSequence input) { // 输入框输入文字后，点击发送按钮事件
//                if (input.length() == 0) {
//                    return false;
//                }
//                String text = input.toString();
////                Message msg = JMessageClient.createSingleTextMessage(targetId, targetAppKey, text);
//                TextContent content = new TextContent(text);
//                Message msg = mConv.createSendMessage(content);
//
//                doSendMessage(msg);
//
////                MyMessage message = new MyMessage(input.toString(), IMessage.MessageType.SEND_TEXT);
////                message.setUserInfo(new DefaultUser("1", "Ironman", "R.mipmap.ironman"));
////                message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
////                mAdapter.addToStart(message, true);
//                return true;
//            }
//
//            @Override
//            public void onSendFiles(List<FileItem> list) {// 选中文件或者录制完视频后，点击发送按钮触发此事件
//                if (list == null || list.isEmpty()) {
//                    return;
//                }
//                FileItem.Type type = getFileTypeFromList(list);
//                List<Message> msgList = new ArrayList<Message>();
//                if (type== FileItem.Type.Image){
//                    msgList=getImageListMessage(list);
//                } else if (type== FileItem.Type.Video){
//                    msgList=getVideoListMessage(list);
//                }
//                sendImageOrVideoList(msgList);
//
////                    MyMessage message;
////                    for (FileItem item : list) {
////                    if (item.getType() == FileItem.Type.Image) {
////                        message = new MyMessage(null, IMessage.MessageType.SEND_IMAGE);
////
////                    } else if (item.getType() == FileItem.Type.Video) {
////                        message = new MyMessage(null, IMessage.MessageType.SEND_VIDEO);
////                        message.setDuration(((VideoItem) item).getDuration());
////
////                    } else {
////                        throw new RuntimeException("Invalid FileItem type. Must be Type.Image or Type.Video");
////                    }
////
////                    message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
////                    message.setMediaFilePath(item.getFilePath());
////                    message.setUserInfo(new DefaultUser("1", "Ironman", "R.mipmap.ironman"));
////
////                    final MyMessage fMsg = message;
////                    MessageListActivity.this.runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            mAdapter.addToStart(fMsg, true);
////                        }
////                    });
//            }
//
//            @Override
//            public void switchToMicrophoneMode() {// 点击语音按钮触发事件，显示录音界面前触发此事件
//                String[] perms = new String[]{
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                };
//
//                if (!EasyPermissions.hasPermissions(MessageListActivity.this, perms)) {
//                    EasyPermissions.requestPermissions(MessageListActivity.this,
//                            getResources().getString(R.string.rationale_record_voice),
//                            RC_RECORD_VOICE, perms);
//                }
//            }
//
//            @Override
//            public void switchToGalleryMode() {// 点击图片按钮触发事件，显示图片选择界面前触发此事件
//                String[] perms = new String[]{
//                        Manifest.permission.READ_EXTERNAL_STORAGE
//                };
//
//                if (!EasyPermissions.hasPermissions(MessageListActivity.this, perms)) {
//                    EasyPermissions.requestPermissions(MessageListActivity.this,
//                            getResources().getString(R.string.rationale_photo),
//                            RC_PHOTO, perms);
//                }
//            }
//
//            @Override
//            public void switchToCameraMode() {// 点击拍照按钮触发事件，显示拍照界面前触发此事件
//                String[] perms = new String[]{
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.RECORD_AUDIO
//                };
//
//                if (!EasyPermissions.hasPermissions(MessageListActivity.this, perms)) {
//                    EasyPermissions.requestPermissions(MessageListActivity.this,
//                            getResources().getString(R.string.rationale_camera),
//                            RC_CAMERA, perms);
//                } else {
//                    File rootDir = getFilesDir();
//                    String fileDir = rootDir.getAbsolutePath() + Version.PHOTO_VIDEO_SAVE_PATH;
//                    mChatView.setCameraCaptureFile(fileDir, Version.PHOTO_VIDEO_TEMP_NAME);
//                }
//            }
//        });
//
//        mChatView.setRecordVoiceListener(new RecordVoiceListener() {
//            @Override
//            public void onStartRecord() {
//                // Show record voice interface
//                File rootDir = getFilesDir();
//                String fileDir = rootDir.getAbsolutePath() + Version.VOICE_SAVE_PATH;
//                mChatView.setRecordVoiceFile(fileDir, DateFormat.format("yyyy_MMdd_hhmmss",
//                        Calendar.getInstance(Locale.CHINA)) + "");
//            }
//
//            @Override
//            public void onFinishRecord(File voiceFile, int duration) {//完成语音录制后将要发送时调用
//                Message msg = getVoiceMessage(voiceFile, duration);
//                doSendMessage(msg);
//
////                MyMessage message = new MyMessage(null, IMessage.MessageType.SEND_VOICE);
////                message.setUserInfo(new DefaultUser("1", "Ironman", "R.mipmap.ironman"));
////                message.setMediaFilePath(voiceFile.getPath());
////                message.setDuration(duration);
////                message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
////                mAdapter.addToStart(message, true);
//            }
//
//            @Override
//            public void onCancelRecord() {
//
//            }
//        });
//
//        mChatView.setOnCameraCallbackListener(new OnCameraCallbackListener() {
//            @Override
//            public void onTakePictureCompleted(String photoPath) {  //拍照后点击发送调用此方法
//                Message msg = getImageMessage(photoPath);
//                doSendMessage(msg);
//
////                final MyMessage message = new MyMessage(null, IMessage.MessageType.SEND_IMAGE);
////                message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
////                message.setMediaFilePath(photoPath);
////                message.setUserInfo(new DefaultUser("1", "Ironman", "R.mipmap.ironman"));
////                MessageListActivity.this.runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        mAdapter.addToStart(message, true);
////                    }
////                });
//            }
//
//            @Override
//            public void onStartVideoRecord() {
//
//            }
//
//            @Override
//            public void onFinishVideoRecord(String videoPath) {// 请注意，点击发送视频的事件会回调给 onSendFiles，这个是在录制完视频后触发的
//
//            }
//
//            @Override
//            public void onCancelVideoRecord() {
//
//            }
//        });
////        mChatView.setCameraCaptureFile();
//    }
//
//    public void init(){
//        toolbarTitle=getToolbarTitle();
//        targetId=getTargetId();
//        targetAppKey=getTargetAppKey();
//
//        initWithTitleAndIdAndAppkey(toolbarTitle, targetId, targetAppKey);
//    }
//
//    public FileItem.Type getFileTypeFromList(List<FileItem> list){
//        if (ListUtils.isEmpty(list)){
//            return FileItem.Type.Image;
//        } else {
//            FileItem fi = list.get(0);
//            FileItem.Type type = fi.getType();
//            return type;
//        }
//    }
//
//    public List<Message> getVideoListMessage(List<FileItem> list){
//        List<Message> result = new ArrayList<>();
//        if (ListUtils.isEmpty(list)){
//            return result;
//        } else {
//            for (int i = 0; i < list.size(); ++i){
//                FileItem fi = list.get(i);
//                if(fi.getType() == FileItem.Type.Video){
//                    String photoPath = fi.getFilePath();
//                    if (!FileUtils.isFileExist(photoPath)){
//                        continue;
//                    }
//                    File file = new File(photoPath);
//                    String fileName = file.getName();
//                    Message msg = null;
//                    try {
//                        msg = mConv.createSendFileMessage(file, fileName);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                        msg=null;
//                        continue;
//                    } catch (JMFileSizeExceedException e) {
//                        e.printStackTrace();
//                        msg=null;
//                    }
//                    if (msg!=null) {
//                        result.add(msg);
//                    }
//
//                }
//            }
//        }
//        return result;
//    }
//    public List<Message> getImageListMessage(List<FileItem> list){
//        List<Message> result = new ArrayList<>();
//        if (ListUtils.isEmpty(list)){
//            return result;
//        } else {
//            for (int i = 0; i < list.size(); ++i){
//                FileItem fi = list.get(i);
//                if(fi.getType() == FileItem.Type.Image){
//                    String photoPath = fi.getFilePath();
//                    if (!FileUtils.isFileExist(photoPath)){
//                        continue;
//                    }
//                    File file = new File(photoPath);
//                    Message msg = null;
//                    try {
//                        msg = mConv.createSendImageMessage(file);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                        msg=null;
//                        continue;
//                    }
//                    if (msg!=null) {
//                        result.add(msg);
//                    }
//
//                }
//            }
//        }
//        return result;
//    }
//
//    public int getVoiceDuration(File file) {
//        if (file==null||!file.exists()) {
//            return 0;
//        }
//
//        MediaPlayer mp = new MediaPlayer();
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            mp.setDataSource(fis.getFD());
//            mp.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        int duration = 0;
//        //某些手机会限制录音，如果用户拒接使用录音，则需判断mp是否存在
//        if (mp != null) {
//            duration = mp.getDuration() / 1000;//即为时长 是s
//            if (duration < Constants.VOICE_MIN_DURATION) {
//                duration = Constants.VOICE_MIN_DURATION;
//            } else if (duration > Constants.VOICE_MAX_DURATION) {
//                duration = Constants.VOICE_MAX_DURATION;
//            }
//
//        } else {
//            return 0;
//        }
//        return duration;
//    }
//
//    public Message getVoiceMessage(File file, int duration){
//        if (file==null||!file.exists()) {
//            return null;
//        }
//        Message msg = null;
////        int duration = getVoiceDuration(file);
//        try {
//            msg=mConv.createSendVoiceMessage(file, duration);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            msg=null;
//        }
//        return msg;
//    }
//
//    public Message getImageMessage(String photoPath){
//        if (!FileUtils.isFileExist(photoPath)){
//            return null;
//        }
//        File file = new File(photoPath);
//        Message msg = null;
//        try {
//            msg = mConv.createSendImageMessage(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            msg=null;
//        }
//        return msg;
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        JMessageClient.enterSingleConversation(targetId, targetAppKey);
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        JMessageClient.exitConversation();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
////        unregisterEventReceiver();
//        if (mShouldRefreshSubscription!=null&&mShouldRefreshSubscription.hasSubscriptions()){
//            mShouldRefreshSubscription.unsubscribe();
//        }
//
//    }
//
//    public void doSendMessage(final Message msg){
//        if (msg!=null){
//            showGifProgressDialog();
//            msg.setOnSendCompleteCallback(new BasicCallback() {
//                @Override
//                public void gotResult(int i, String s) {
//                    hideGifProgressDialog();
//                    if (Constants.JMESSAGE_SEND_SUCCESS_CODE==i){
//                        MyMessage mm = getMyMessageFromMessage(msg);
////                        mAdapter.addToStart(mm, true);
//                        addToStart(mm);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Util.showToast(MessageListActivity.this, Constants.HINT_SEND_MESSAGE_SUCCESS);
//
//                            }
//                        });
//                    } else {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Util.showToast(MessageListActivity.this, Constants.HINT_SEND_MESSAGE_FAILURE);
//
//                            }
//                        });
//                    }
//                }
//            });
//            JMessageClient.sendMessage(msg);
//        } else {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Util.showToast(MessageListActivity.this, Constants.HINT_MESSAGE_IS_NULL);
//
//                }
//            });
//        }
//    }
//
//    public DefaultUser getUser(Message msg){
//        if (msg==null){
//            return null;
//        } else {
//            MessageDirect direction = msg.getDirect();
//            DefaultUser du = null;
//            if (direction==MessageDirect.send) {
//                du = getCurrentUser();
//            } else {
//                du=getTargetUser();
//            }
//            return du;
//        }
//
//    }
//
//    public DefaultUser getCurrentUser(){
//        String id = getCurrentId();
//        String ct = Identity.getDisplayTitle();
//        String rs = getCurrentResourceString();
//        DefaultUser du = new DefaultUser(id, ct, rs);
//        return du;
//    }
//
//    public DefaultUser getTargetUser(){
//        String id = targetId;
//        String rs = getTargetResourceString();
//        DefaultUser du = new DefaultUser(id, toolbarTitle, rs);
//        return du;
//    }
//
//    public String getHourMinuteString(){
//        String result = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
//        return result;
//    }
//
//    public String getCurrentResourceString(){
//        return "R.mipmap.person";
//    }
//
//    public String getTargetResourceString(){
//        return "R.mipmap.person";
//    }
//
//    public String getCurrentId(){
//        return Identity.getJMessageId();
//    }
//
//    public String getTargetId(){
//        Intent intent = getIntent();
//        if (intent!=null){
//            String result = intent.getStringExtra(Constants.TARGET_ID);
//            return result;
//        } else {
//            return Constants.EMPTY_STRING;
//        }
//
//    }
//
//    public String getTargetAppKey(){
//        Intent intent = getIntent();
//        if (intent!=null){
//            String result = intent.getStringExtra(Constants.TARGET_APP_KEY);
//            return result;
//        } else {
//            return Constants.EMPTY_STRING;
//        }
//    }
//
//    public String getToolbarTitle(){
//        Intent intent = getIntent();
//        if (intent!=null){
//            String result = intent.getStringExtra(Constants.CONV_TITLE);
//            return result;
//        } else {
//            return Constants.EMPTY_STRING;
//        }
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> perms) {
//
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            new AppSettingsDialog.Builder(this).build().show();
//        }
//    }
//
//    public MyMessage getLastMsg(){
//        List<MyMessage> list = mAdapter.getMessageList();
//        if (!ListUtils.isEmpty(list)) {
//            return list.get(list.size()-1);
//        } else {
//            return null;
//        }
//    }
//
//    public MyMessage getMyMessageFromMessage(Message msg){
//        if (msg==null){
//            return null;
//        } else {
//            final MyMessage myMessage = new MyMessage();
//            myMessage.setMsg(msg);
//            MessageDirect direction = msg.getDirect();
//            DefaultUser defaultUser = getUser(msg);
//            myMessage.setUserInfo(defaultUser);
//            long ms = msg.getCreateTime();
////            String time = Util.formatMillisecondTime(ms);
//            String time = Util.getJMessageDisplayTime(this, ms);
//            myMessage.setTimeString(time);
//            switch (msg.getContentType()) {
//                case text:
//                    //处理文字消息
//                    TextContent textContent = (TextContent) msg.getContent();
//                    String text = textContent.getText();
//                    myMessage.setText(text);
//                    IMessage.MessageType mt;
//                    if (direction==MessageDirect.send){
//                        myMessage.setType(IMessage.MessageType.SEND_TEXT);
//                    } else {
//                        myMessage.setType(IMessage.MessageType.RECEIVE_TEXT);
//                    }
////                    myMessage.setTimeString(getHourMinuteString());
//
//
//                    break;
//                case image:
//                    //处理图片消息
//                    ImageContent imageContent = (ImageContent) msg.getContent();
//                    String imageLocalPath = imageContent.getLocalPath();//图片本地地址
//                    final String imageLocalThumbnailPath = imageContent.getLocalThumbnailPath();//图片对应缩略图的本地地址
//                    if (direction==MessageDirect.send) {
//                        myMessage.setType(IMessage.MessageType.SEND_IMAGE);
//                    } else {
//                        myMessage.setType(IMessage.MessageType.RECEIVE_IMAGE);
//                    }
////                    if (msg.getStatus() == MessageStatus.receive_fail) {
//                    if (StringUtils.isBlank(imageLocalThumbnailPath)){
//                        imageContent.downloadThumbnailImage(msg, new DownloadCompletionCallback() {
//                            @Override
//                            public void onComplete(int code, String desc, File file) {
//                                if (code == Constants.JMESSAGE_RECEIVE_SUCCESS_CODE) {
//                                    // 下载成功
////                                    setPictureScale(imageLocalThumbnailPath, holder.picture);
////                                    Picasso.with(mContext).load(file).into(holder.picture);
//                                    myMessage.setMediaFilePath(file.getAbsolutePath());
//                                    refreshView();
//                                } else {
//                                    // 下载失败
//                                }
//                            }
//                        });
//                    }
////                    myMessage.setTimeString(getHourMinuteString());
//                    myMessage.setMediaFilePath(imageLocalThumbnailPath);
//
//
//                    break;
//                case video:
//                    FileContent videoContent = (FileContent)msg.getContent();
//                    String videoLocalPath =videoContent.getLocalPath();
//                    if (direction==MessageDirect.send){
//                        myMessage.setType(IMessage.MessageType.SEND_VIDEO);
//                    } else {
//                        myMessage.setType(IMessage.MessageType.RECEIVE_VIDEO);
//                    }
////                    myMessage.setTimeString(getHourMinuteString());
//                    myMessage.setMediaFilePath(videoLocalPath);
////                    myMessage.setDuration(((VideoItem) item).getDuration());
//
//
//                    break;
//                case file:
//                    FileContent fileContent = (FileContent)msg.getContent();
//                    String fileLocalPath = fileContent.getLocalPath();
//                    if (direction==MessageDirect.send){
//                        myMessage.setType(IMessage.MessageType.SEND_VIDEO);
//                    } else {
//                        myMessage.setType(IMessage.MessageType.RECEIVE_VIDEO);
//                    }
////                    myMessage.setTimeString(getHourMinuteString());
//                    myMessage.setMediaFilePath(fileLocalPath);
////                    myMessage.setDuration(((VideoItem) item).getDuration());
//
//
//                    break;
//                case voice:
//                    //处理语音消息
//                    VoiceContent voiceContent = (VoiceContent) msg.getContent();
//                    String voiceLocalPath = voiceContent.getLocalPath();//语音文件本地地址
//                    int voiceDuration = voiceContent.getDuration();//语音文件时长
//                    if (direction==MessageDirect.send){
//                        myMessage.setType(IMessage.MessageType.SEND_VOICE);
//                    } else {
//                        myMessage.setType(IMessage.MessageType.RECEIVE_VOICE);
//                    }
////                    if (msg.getStatus() == MessageStatus.receive_fail) {
//                    if (StringUtils.isBlank(voiceLocalPath)) {
//                        voiceContent.downloadVoiceFile(msg, new DownloadCompletionCallback() {
//                            @Override
//                            public void onComplete(int i, String s, File file) {
//                                if (i == Constants.JMESSAGE_RECEIVE_SUCCESS_CODE) {
//                                    // 下载成功
//                                    myMessage.setMediaFilePath(file.getAbsolutePath());
//                                    refreshView();
//                                } else {
//                                    // 下载失败
//                                }
//
//                            }
//                        });
//                    }
//
////                    myMessage.setTimeString(getHourMinuteString());
//                    myMessage.setMediaFilePath(voiceLocalPath);
//                    myMessage.setDuration(voiceDuration);
//                    break;
////                case custom:
////                    //处理自定义消息
////                    CustomContent customContent = (CustomContent) msg.getContent();
////                    customContent.getNumberValue("custom_num"); //获取自定义的值
////                    customContent.getBooleanValue("custom_boolean");
////                    customContent.getStringValue("custom_string");
////                    break;
////                case eventNotification:
////                    //处理事件提醒消息
////                    EventNotificationContent eventNotificationContent = (EventNotificationContent)msg.getContent();
////                    switch (eventNotificationContent.getEventNotificationType()){
////                        case group_member_added:
////                            //群成员加群事件
////                            break;
////                        case group_member_removed:
////                            //群成员被踢事件
////                            break;
////                        case group_member_exit:
////                            //群成员退群事件
////                            break;
////                    }
////                    break;
//            }
////            mAdapter.addToStart(myMessage, true);
//            return myMessage;
//        }
//    }
//
//    public List<MyMessage> getMyMessageListFromMessageList(List<Message> list){
//        List<MyMessage> resultList = new ArrayList<>();
//        if (!ListUtils.isEmpty(list)){
//            for (int i = 0; i < list.size(); ++i){
//                Message msg = list.get(i);
//                MyMessage myMessage = getMyMessageFromMessage(msg);
//                resultList.add(myMessage);
//            }
//        } else {
//
//        }
//        return resultList;
//    }
//
//    /**
//     类似MessageEvent事件的接收，上层在需要的地方增加OfflineMessageEvent事件的接收
//     即可实现离线消息的接收。
//     **/
//    public void onEvent(OfflineMessageEvent event) {
//        //获取事件发生的会话对象
//        Conversation conversation = event.getConversation();
//        List<Message> newMessageList = event.getOfflineMessageList();//获取此次离线期间会话收到的新消息列表
//        LogUtil.i(String.format(Locale.SIMPLIFIED_CHINESE, "收到%d条来自%s的离线消息。\n", newMessageList.size(), conversation.getTargetId()));
//        List<MyMessage> myMessageList = getMyMessageListFromMessageList(newMessageList);
//        for (int i = 0;  i < myMessageList.size(); ++i){
//            MyMessage mm = myMessageList.get(i);
//            addToStart(mm);
//        }
//    }
//
//    public void onEvent(MessageEvent event) {
//        final Message msg = event.getMessage();
//        if (msg.getTargetType() == ConversationType.single) {
//            UserInfo userInfo = (UserInfo) msg.getTargetInfo();
//            String targetId = userInfo.getUserName();
//            String appKey = userInfo.getAppKey();
//            //判断消息是否在当前会话中
////            if (mIsSingle && targetId.equals(mTargetId) && appKey.equals(mTargetAppKey)) {
//            if (targetId.equals(this.targetId) && appKey.equals(this.targetAppKey)) {
//                MyMessage lastMsg = getLastMsg();
//                int lastMsgId = Util.getIntFromString(lastMsg.getMsgId());
//                //收到的消息和Adapter中最后一条消息比较，如果最后一条为空或者不相同，则加入到MsgList
//                if (lastMsg == null || msg.getId() != lastMsgId) {
//                    MyMessage newMessage = getMyMessageFromMessage(msg);
//                    addToStart(newMessage);
//                } else {
//                    refreshView();
//                }
//            }
//        }
//    }
//
//    public void addToStart(final MyMessage mm){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mAdapter.addToStart(mm, true);
//            }
//        });
//    }
//
//    public void addToEnd(MyMessage mm){
//        final List<MyMessage> list = new ArrayList<>();
//        list.add(mm);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mAdapter.addToEnd(list);
//            }
//        });
//    }
//
//    public void addToEnd(final List<MyMessage> list){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mAdapter.addToEnd(list);
//            }
//        });
//    }
//
//    public void refreshView(){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                mAdapter.notifyDataSetChanged();
//            }
//        });
//    }
//
//    public void registerEventReceiver(){
//        JMessageClient.registerEventReceiver(this);
//    }
//
//    public void unregisterEventReceiver(){
//        JMessageClient.unRegisterEventReceiver(this);
//    }
//
//    private List<MyMessage> getMessages() {
//        List<MyMessage> list = new ArrayList<>();
//        Resources res = getResources();
//        String[] messages = res.getStringArray(R.array.messages_array);
//        for (int i = 0; i < messages.length; i++) {
//            MyMessage message;
//            if (i % 2 == 0) {
//                message = new MyMessage(messages[i], IMessage.MessageType.RECEIVE_TEXT);
//                message.setUserInfo(new DefaultUser("0", "DeadPool", "R.mipmap.icon"));
//            } else {
//                message = new MyMessage(messages[i], IMessage.MessageType.SEND_TEXT);
//                message.setUserInfo(new DefaultUser("1", "IronMan", "R.mipmap.icon"));
//            }
//            message.setTimeString(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()));
//            list.add(message);
//        }
//        Collections.reverse(list);
//        return list;
//    }
//
//    private void initMsgAdapter() {
//        ImageLoader imageLoader = new ImageLoader() {
//            @Override
//            public void loadAvatarImage(ImageView avatarImageView, String string) {
//                // You can use other image load libraries.
//                if (string.contains("R.drawable")) {
//                    Integer resId = getResources().getIdentifier(string.replace("R.drawable.", ""),
//                            "drawable", getPackageName());
//
//                    avatarImageView.setImageResource(resId);
//                } else if (string.contains("R.mipmap")) {
//                    Integer resId = getResources().getIdentifier(string.replace("R.mipmap.", ""),
//                            "mipmap", getPackageName());
//
//                    avatarImageView.setImageResource(resId);
//                } else {
////                    Glide.with(getApplicationContext())
//                    Glide.with(MessageListActivity.this)
//                            .load(string)
//                            .placeholder(R.drawable.aurora_headicon_default)
//                            .into(avatarImageView);
//                }
//            }
//
//            @Override
//            public void loadImage(ImageView imageView, String string) {
//                // You can use other image load libraries.
////                Glide.with(getApplicationContext())
//                Glide.with(MessageListActivity.this)
//                        .load(string)
//                        .fitCenter()
//                        .placeholder(R.drawable.aurora_picture_not_found)
//                        .override(400, Target.SIZE_ORIGINAL)
//                        .into(imageView);
//            }
//        };
//
//        // Use default layout
//        MsgListAdapter.HoldersConfig holdersConfig = new MsgListAdapter.HoldersConfig();
//        mAdapter = new MsgListAdapter<>(targetId, holdersConfig, imageLoader);
//
//        // If you want to customise your layout, try to create custom ViewHolder:
//        // holdersConfig.setSenderTxtMsg(CustomViewHolder.class, layoutRes);
//        // holdersConfig.setReceiverTxtMsg(CustomViewHolder.class, layoutRes);
//        // CustomViewHolder must extends ViewHolders defined in MsgListAdapter.
//        // Current ViewHolders are TxtViewHolder, VoiceViewHolder.
//
//        //点击消息触发
//        mAdapter.setOnMsgClickListener(new MsgListAdapter.OnMsgClickListener<MyMessage>() {
//            @Override
//            public void onMessageClick(MyMessage message) {
//                // do something
//                if (message.getType() == IMessage.MessageType.RECEIVE_VIDEO
//                        || message.getType() == IMessage.MessageType.SEND_VIDEO) {
//                    String fileMediaPath = message.getMediaFilePath();
//                    handleFileClick(message);
//                } else if (message.getType()== IMessage.MessageType.RECEIVE_IMAGE
//                        || message.getType()== IMessage.MessageType.SEND_IMAGE){
//                    handleImageClick(message);
//                }
//                else {
////                    Toast.makeText(getApplicationContext(),
////                            getApplicationContext().getString(R.string.message_click_hint),
////                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        mAdapter.setMsgLongClickListener(new MsgListAdapter.OnMsgLongClickListener<MyMessage>() {
//            @Override
//            public void onMessageLongClick(MyMessage message) {
////                Toast.makeText(getApplicationContext(),
////                        getApplicationContext().getString(R.string.message_long_click_hint),
////                        Toast.LENGTH_SHORT).show();
//                // do something
//            }
//        });
//
//        //点击头像触发
//        mAdapter.setOnAvatarClickListener(new MsgListAdapter.OnAvatarClickListener<MyMessage>() {
//            @Override
//            public void onAvatarClick(MyMessage message) {
//                DefaultUser userInfo = (DefaultUser) message.getFromUser();
////                Toast.makeText(getApplicationContext(),
////                        getApplicationContext().getString(R.string.avatar_click_hint),
////                        Toast.LENGTH_SHORT).show();
//                // do something
//            }
//        });
//
//        //点击重新发送按钮触发
//        mAdapter.setMsgResendListener(new MsgListAdapter.OnMsgResendListener<MyMessage>() {
//            @Override
//            public void onMessageResend(MyMessage message) {
//                // resend message here
//            }
//        });
//
////        MyMessage message = new MyMessage("Hello World", IMessage.MessageType.RECEIVE_TEXT);
////        message.setUserInfo(new DefaultUser("0", "Deadpool", "R.mipmap.icon"));
//
//        mAdapter.addToEnd(mData);
//        //滚动列表加载历史消息 设置监听 OnLoadMoreListener，当滚动列表时就会触发 onLoadMore 事件
//        mAdapter.setOnLoadMoreListener(new MsgListAdapter.OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(int page, int totalCount) {
////                if (totalCount < mData.size()) {
////                    loadNextPage();
////                }
//            }
//        });
//
//        mChatView.setAdapter(mAdapter);
//    }
//
//    public void handleFileClick(MyMessage mm){
//        Message msg = mm.getMsg();
//        FileContent content = (FileContent) msg.getContent();
//        final String mediaFilePath = mm.getMediaFilePath();
//        String localPath = content.getLocalPath();
//        if (!TextUtils.isEmpty(localPath)) {
//            final String fileName = content.getFileName();
//            final String path = content.getLocalPath();
//            showFileDisplayActivity(localPath);
////            if (msg.getDirect() == MessageDirect.send) {
////                browseDocument(fileName, path);
////            } else {
////                final String newPath = JChatDemoApplication.FILE_DIR + fileName;
////                File file = new File(newPath);
////                if (file.exists() && file.isFile()) {
////                    browseDocument(fileName, newPath);
////                } else {
////                    FileHelper.getInstance().copyFile(fileName, path, (Activity) mContext,
////                            new FileHelper.CopyFileCallback() {
////                                @Override
////                                public void copyCallback(Uri uri) {
////                                    Toast.makeText(mContext, mContext.getString(IdHelper
////                                                    .getString(mContext, "file_already_copy_hint")),
////                                            Toast.LENGTH_SHORT).show();
////                                    browseDocument(fileName, newPath);
////                                }
////                            });
////                }
////            }
//        } else {
//            if (content!=null) {
//                content.downloadFile(msg, new DownloadCompletionCallback() {
//                            @Override
//                            public void onComplete(int status, String desc, File file) {
//                                String str = mediaFilePath;
//                                if (status==Constants.JMESSAGE_RECEIVE_SUCCESS_CODE){
//                                    str=file.getAbsolutePath();
//                                } else {
//                                }
//                                showFileDisplayActivity(str);
//
//                            }
//                        });
//            }
////            if (msg.getDirect() == MessageDirect.receive) {
////                holder.contentLl.setBackgroundColor(Color.parseColor("#86222222"));
////                holder.progressTv.setText("0%");
////                holder.progressTv.setVisibility(View.VISIBLE);
////                msg.setOnContentDownloadProgressCallback(new ProgressUpdateCallback() {
////                    @Override
////                    public void onProgressUpdate(double v) {
////                        String progressStr = (int) (v * 100) + "%";
////                        holder.progressTv.setText(progressStr);
////                    }
////                });
////                content.downloadFile(msg, new DownloadCompletionCallback() {
////                    @Override
////                    public void onComplete(int status, String desc, File file) {
////                        holder.progressTv.setVisibility(View.GONE);
////                        holder.contentLl.setBackground(mContext.getDrawable(IdHelper
////                                .getDrawable(mContext, "jmui_receive_msg")));
////                        if (status != 0) {
////                            holder.resend.setVisibility(View.VISIBLE);
////                            Toast.makeText(mContext, IdHelper.getString(mContext,
////                                    "download_file_failed"),
////                                    Toast.LENGTH_SHORT).show();
////                        } else {
////                            Toast.makeText(mContext, IdHelper.getString(mContext,
////                                    "download_file_succeed"), Toast.LENGTH_SHORT).show();
////                        }
////                    }
////                });
////            } else {
////                Toast.makeText(mContext, IdHelper.getString(mContext, "jmui_file_not_found_toast"),
////                        Toast.LENGTH_SHORT).show();
////            }
//        }
//    }
//
//    public void handleImageClick(MyMessage mm){
//        Message msg = mm.getMsg();
//        final ImageContent imgContent = (ImageContent) msg.getContent();
//        final String imagePath = mm.getMediaFilePath();
//        String localPath = imgContent.getLocalPath();
//        if (!StringUtils.isBlank(localPath)){
//            showImageDisplayActivity(localPath);
//        } else {
//            if (imgContent!=null){
//                imgContent.downloadOriginImage(msg, new DownloadCompletionCallback() {
//                    @Override
//                    public void onComplete(int i, String s, File file) {
//                        String str = imagePath;
//                        if (i==Constants.JMESSAGE_RECEIVE_SUCCESS_CODE){
//                            str=file.getAbsolutePath();
//                        } else {
//                        }
//                        showImageDisplayActivity(str);
//                    }
//                });
//            }
//
//        }
//
//    }
//
//    public void showFileDisplayActivity(String fileMediaPath){
//        if (!StringUtils.isBlank(fileMediaPath)) {
//            Intent intent = new Intent(MessageListActivity.this, VideoActivity.class);
//            intent.putExtra(VideoActivity.VIDEO_PATH, fileMediaPath);
//            startActivity(intent);
//        }
//
//    }
//
//    public void showImageDisplayActivity(String filePath){
//        if (!TextUtils.isEmpty(filePath)){
//            Intent intent = new Intent(MessageListActivity.this, ImageDisplayActivity.class);
////                        intent.putExtra(Constants.MESSAGE_KEY, message.getMsg());
//            intent.putExtra(Constants.IMAGE_FILE_PATH_KEY, filePath);
//            startActivity(intent);
//        }
//
//    }
//
//    private void loadNextPage() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mAdapter.addToEnd(mData);
//            }
//        }, 1000);
//    }
//
//    @Override
//    public void onKeyBoardStateChanged(int state) {
//        switch (state) {
//            case ChatInputView.KEYBOARD_STATE_INIT:
//                ChatInputView chatInputView = mChatView.getChatInputView();
//                if (mImm != null) {
//                    mImm.isActive();
//                }
//                if (chatInputView.getMenuState() == View.INVISIBLE
//                        || (!chatInputView.getSoftInputState()
//                        && chatInputView.getMenuState() == View.GONE)) {
//
//                    mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
//                            | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    chatInputView.dismissMenuLayout();
//                }
//                break;
//        }
//    }
//
//    @Override
//    public void onSizeChanged(int w, int h, int oldw, int oldh) {
//        if (oldh - h > 300) {
//            mChatView.setMenuHeight(oldh - h);
//        }
//    }
//
//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        switch (motionEvent.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                ChatInputView chatInputView = mChatView.getChatInputView();
//
//                if (view.getId() == chatInputView.getInputView().getId()) {
//
//                    if (chatInputView.getMenuState() == View.VISIBLE
//                            && !chatInputView.getSoftInputState()) {
//                        chatInputView.dismissMenuAndResetSoftMode();
//                        return false;
//                    } else {
//                        return false;
//                    }
//                }
//                if (chatInputView.getMenuState() == View.VISIBLE) {
//                    chatInputView.dismissMenuLayout();
//                }
//                if (chatInputView.getSoftInputState()) {
//                    View v = getCurrentFocus();
//
//                    if (mImm != null && v != null) {
//                        mImm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                        mWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
//                                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//                        chatInputView.setSoftInputState(false);
//                    }
//                }
//                break;
//        }
//        return false;
//    }
//}
