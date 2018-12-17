package com.jqsoft.fingertip_health.bean.grassroots_civil_administration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 附件列表
 * Created by Administrator on 2017-12-27.
 */

public class UrbanLowFujianBean implements Serializable {
    private String itemId;
    private String itemName;

    public  List<Jiuzhuxiang> files = new ArrayList<>();

   public class Jiuzhuxiang{
       public String fileCode;
       public String fileCodeName;
       public String fileState;

       public    List<Shenqingxiang> details = new ArrayList<>();

       public  class Shenqingxiang{
            private String fileId;
            private String fileName;
            private String filePath;
           private String createTime;


           public String getFileId() {
               return fileId;
           }

           public void setFileId(String fileId) {
               this.fileId = fileId;
           }

           public String getFileName() {
               return fileName;
           }

           public void setFileName(String fileName) {
               this.fileName = fileName;
           }

           public String getFilePath() {
               return filePath;
           }

           public void setFilePath(String filePath) {
               this.filePath = filePath;
           }


           public String getCreateTime() {
               return createTime;
           }

           public void setCreateTime(String createTime) {
               this.createTime = createTime;
           }
       }

       public String getFileState() {
           return fileState;
       }

       public void setFileState(String fileState) {
           this.fileState = fileState;
       }

       public String getFileCodeName() {
           return fileCodeName;
       }

       public void setFileCodeName(String fileCodeName) {
           this.fileCodeName = fileCodeName;
       }

       public String getFileCode() {
           return fileCode;
       }

       public void setFileCode(String fileCode) {
           this.fileCode = fileCode;
       }

       public List<Shenqingxiang> getDetails() {
           return details;
       }

       public void setDetails(List<Shenqingxiang> details) {
           this.details = details;
       }
   }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public List<Jiuzhuxiang> getFiles() {
        return files;
    }

    public void setFiles(List<Jiuzhuxiang> files) {
        this.files = files;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
