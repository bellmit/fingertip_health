package com.jqsoft.fingertip_health.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/8.
 */
public class AreasEntity {
    public ArrayList<AreasList> areas;
    public ArrayList<DictList> dicts;
    public ArrayList<ItemsList> items;
    public ArrayList<Imaglist> data;

    public static class Imaglist implements Serializable {
        private String base64;
        private String fileName;
        private String fileCodeName;
        private String fileId;

        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        public String getFileCodeName() {
            return fileCodeName;
        }

        public void setFileCodeName(String fileCodeName) {
            this.fileCodeName = fileCodeName;
        }

        public String getBase64() {
            return base64;
        }

        public void setBase64(String base64) {
            this.base64 = base64;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

    public static class DictList implements Serializable, IPickerViewData {
        private String code;
        private String id;
        private String pcode;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String getPickerViewText() {
            return name;
        }
    }

    public static class ItemsList implements Serializable, IPickerViewData {
        private String parentId;
        private String id;
        private String name;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        @Override
        public String getPickerViewText() {
            return name;
        }
    }

    public static class AreasList implements Serializable, IPickerViewData {
        private String areaLevel;
        private String areaPid;
        private String id;
        private String name;

        public String getAreaLevel() {
            return areaLevel;
        }

        public void setAreaLevel(String areaLevel) {
            this.areaLevel = areaLevel;
        }

        public String getAreaPid() {
            return areaPid;
        }

        public void setAreaPid(String areaPid) {
            this.areaPid = areaPid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String getPickerViewText() {
            return name;
        }

    }


}
