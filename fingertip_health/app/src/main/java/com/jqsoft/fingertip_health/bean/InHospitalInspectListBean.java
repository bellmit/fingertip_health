package com.jqsoft.fingertip_health.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/13.
 */
public class InHospitalInspectListBean {
    ArrayList<InHospitalInspectBean> inspectList;

    public ArrayList<InHospitalInspectBean> getInspectList() {
        return inspectList;
    }

    public void setInspectList(ArrayList<InHospitalInspectBean> inspectList) {
        this.inspectList = inspectList;
    }

    public class InHospitalInspectBean {
        private String name;
        private String disease;
        private String hospitalName;
        private String fee;
        private boolean isLocked;

        public InHospitalInspectBean(String name, String disease, String hospitalName, String fee, boolean isLocked) {
            this.name = name;
            this.disease = disease;
            this.hospitalName = hospitalName;
            this.fee = fee;
            this.isLocked = isLocked;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDisease() {
            return disease;
        }

        public void setDisease(String disease) {
            this.disease = disease;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public boolean isLocked() {
            return isLocked;
        }

        public void setLocked(boolean locked) {
            isLocked = locked;
        }
    }

}
