//package com.jqsoft.grassroots_civil_administration_platform.jmessage.models;
//
//import java.util.UUID;
//
//import cn.jiguang.imui.commons.models.IMessage;
//import cn.jiguang.imui.commons.models.IUser;
//import cn.jpush.im.android.api.model.Message;
//
//
//public class MyMessage implements IMessage {
//
//    private long id;
//    private String text;
//    private String timeString;
//    private MessageType type;
//    private IUser user;
//    private String mediaFilePath;
//    private long duration;
//    private String progress;
//
//    private Message msg;
//
//    public MyMessage() {
//    }
//
//    public MyMessage(String text, MessageType type) {
//        this.text = text;
//        this.type = type;
//        this.id = UUID.randomUUID().getLeastSignificantBits();
//    }
//
//    @Override
//    public String getMsgId() {
//        return String.valueOf(id);
//    }
//
//    @Override
//    public IUser getFromUser() {
//        if (user == null) {
//            return new DefaultUser("0", "user1", null);
//        }
//        return user;
//    }
//
//    public void setUserInfo(IUser user) {
//        this.user = user;
//    }
//
//    public void setMediaFilePath(String path) {
//        this.mediaFilePath = path;
//    }
//
//    public void setDuration(long duration) {
//        this.duration = duration;
//    }
//
//    @Override
//    public long getDuration() {
//        return duration;
//    }
//
//    public void setProgress(String progress) {
//        this.progress = progress;
//    }
//
//    @Override
//    public String getProgress() {
//        return progress;
//    }
//
//    public void setTimeString(String timeString) {
//        this.timeString = timeString;
//    }
//
//    @Override
//    public String getTimeString() {
//        return timeString;
//    }
//
//    @Override
//    public MessageType getType() {
//        return type;
//    }
//
//    @Override
//    public MessageStatus getMessageStatus() {
//        return MessageStatus.SEND_SUCCEED;
//    }
//
//    @Override
//    public String getText() {
//        return text;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public void setType(MessageType type) {
//        this.type = type;
//    }
//
//    public void setUser(IUser user) {
//        this.user = user;
//    }
//
//    @Override
//    public String getMediaFilePath() {
//        return mediaFilePath;
//    }
//
//    public Message getMsg() {
//        return msg;
//    }
//
//    public void setMsg(Message msg) {
//        this.msg = msg;
//    }
//}