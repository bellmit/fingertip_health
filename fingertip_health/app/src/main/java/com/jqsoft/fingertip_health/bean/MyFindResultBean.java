package com.jqsoft.fingertip_health.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-07-05.
 */
//签约服务评价
public class MyFindResultBean {
   private String hasMine;
   private List<MyFindDoDiscovertBean> doDiscist =new ArrayList();
   private List<MyFindDiscoverBean> discoverList =new ArrayList();

   public String getHasMine() {
      return hasMine;
   }

   public void setHasMine(String hasMine) {
      this.hasMine = hasMine;
   }

   public List<MyFindDoDiscovertBean> getDoDiscist() {
      return doDiscist;
   }

   public void setDoDiscist(List<MyFindDoDiscovertBean> doDiscist) {
      this.doDiscist = doDiscist;
   }

   public List<MyFindDiscoverBean> getDiscoverList() {
      return discoverList;
   }

   public void setDiscoverList(List<MyFindDiscoverBean> discoverList) {
      this.discoverList = discoverList;
   }
}
