package com.jqsoft.fingertip_health.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-05-25.
 */

public class CompensateListBean {
    private List<CompensateBean> list = new ArrayList<>();

    public List<CompensateBean> getList() {
        return list;
    }

    public void setList(List<CompensateBean> list) {
        this.list = list;
    }

    public class CompensateBean {
        private String id;
        private String name;
        private String money;

        public CompensateBean(String id, String name, String money) {
            this.id = id;
            this.name = name;
            this.money = money;
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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
