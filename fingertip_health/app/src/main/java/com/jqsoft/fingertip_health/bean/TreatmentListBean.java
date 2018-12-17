package com.jqsoft.fingertip_health.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/13.
 */
public class TreatmentListBean {
    ArrayList<TreatmentBean> treatmentList;

    public ArrayList<TreatmentBean> getTreatmentList() {
        return treatmentList;
    }

    public void setTreatmentList(ArrayList<TreatmentBean> treatmentList) {
        this.treatmentList = treatmentList;
    }

    public class TreatmentBean {
        private String disease;
        private String code;
        private boolean isLocked;

        public TreatmentBean(String disease, String code, boolean isLocked) {
            this.disease = disease;
            this.code = code;
            this.isLocked = isLocked;
        }

        public String getDisease() {
            return disease;
        }

        public void setDisease(String disease) {
            this.disease = disease;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public boolean isLocked() {
            return isLocked;
        }

        public void setLocked(boolean locked) {
            isLocked = locked;
        }
    }
}
