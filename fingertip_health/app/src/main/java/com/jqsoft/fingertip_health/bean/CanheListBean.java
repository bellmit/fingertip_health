package com.jqsoft.fingertip_health.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-05-25.
 */

public class CanheListBean {
    private List<CanheBean> list = new ArrayList<>();

    public List<CanheBean> getList() {
        return list;
    }

    public void setList(List<CanheBean> list) {
        this.list = list;
    }

    public class CanheBean {
        private String id;
        private String name;
        private int gender;
        private boolean hasCanhe;

        public CanheBean(String id, String name, int gender, boolean hasCanhe) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.hasCanhe = hasCanhe;
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

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public boolean isHasCanhe() {
            return hasCanhe;
        }

        public void setHasCanhe(boolean hasCanhe) {
            this.hasCanhe = hasCanhe;
        }
    }
}
