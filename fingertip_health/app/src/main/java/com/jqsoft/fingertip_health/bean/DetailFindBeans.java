package com.jqsoft.fingertip_health.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/1.
 */

public class DetailFindBeans {

    DiscoverListBean discover = new DiscoverListBean();

    List<FileListBean> fileList = new ArrayList<>();



    List<DiscoverManageBean> discoverLogList = new ArrayList<>();

    public DiscoverListBean getDiscover() {
        return discover;
    }

    public void setDiscover(DiscoverListBean discover) {
        this.discover = discover;
    }



    public List<FileListBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileListBean> fileList) {
        this.fileList = fileList;
    }

    public List<DiscoverManageBean> getDiscoverLogList() {
        return discoverLogList;
    }

    public void setDiscoverLogList(List<DiscoverManageBean> discoverLogList) {
        this.discoverLogList = discoverLogList;
    }
}
