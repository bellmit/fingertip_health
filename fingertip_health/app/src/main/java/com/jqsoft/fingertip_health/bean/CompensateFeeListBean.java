package com.jqsoft.fingertip_health.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-05-26.
 */

public class CompensateFeeListBean {
    public List<CompensateFeeBean> list = new ArrayList<>();

    public List<CompensateFeeBean> getList() {
        return list;
    }

    public void setList(List<CompensateFeeBean> list) {
        this.list = list;
    }

    public class CompensateFeeBean {
        private String title;
        private String fee;

        public CompensateFeeBean(String title, String fee) {
            this.title = title;
            this.fee = fee;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }
    }
}
