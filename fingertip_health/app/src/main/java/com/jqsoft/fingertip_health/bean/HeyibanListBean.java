package com.jqsoft.fingertip_health.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/14.
 */

public class HeyibanListBean {
    ArrayList<HeyibanBean> list;

    public ArrayList<HeyibanBean> getList() {
        return list;
    }

    public void setList(ArrayList<HeyibanBean> list) {
        this.list = list;
    }

    public class HeyibanBean {
        private int doctorImageId;
        private String doctorName;
        private String departmentName;

        public HeyibanBean(int doctorImageId, String doctorName, String departmentName) {
            this.doctorImageId = doctorImageId;
            this.doctorName = doctorName;
            this.departmentName = departmentName;
        }

        public int getDoctorImageId() {
            return doctorImageId;
        }

        public void setDoctorImageId(int doctorImageId) {
            this.doctorImageId = doctorImageId;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }
    }
}
