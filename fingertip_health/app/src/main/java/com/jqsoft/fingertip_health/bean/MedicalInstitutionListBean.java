package com.jqsoft.fingertip_health.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-22.
 */

public class MedicalInstitutionListBean {
    ArrayList<MedicalInstitutionBean> list;

    public ArrayList<MedicalInstitutionBean> getList() {
        return list;
    }

    public void setList(ArrayList<MedicalInstitutionBean> list) {
        this.list = list;
    }

    public class MedicalInstitutionBean {
        private String id;
        private String name;
        private int imageId;
        private int rating;
        private String evalutionNumber;

        public MedicalInstitutionBean(String id, String name, int imageId, int rating, String evalutionNumber) {
            this.id = id;
            this.name = name;
            this.imageId = imageId;
            this.rating = rating;
            this.evalutionNumber = evalutionNumber;
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

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }

        public String getEvalutionNumber() {
            return evalutionNumber;
        }

        public void setEvalutionNumber(String evalutionNumber) {
            this.evalutionNumber = evalutionNumber;
        }
    }
}
