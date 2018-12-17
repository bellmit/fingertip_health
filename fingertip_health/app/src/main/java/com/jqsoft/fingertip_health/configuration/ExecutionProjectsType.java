package com.jqsoft.fingertip_health.configuration;

import com.jqsoft.fingertip_health.base.Constants;

/**
 * Created by Administrator on 2017-06-22.
 */

public class ExecutionProjectsType {
    public enum ExecutionProjectsTypeEnum {
        Latest7Days(1),
        Timeout(2);

        private int value;

        ExecutionProjectsTypeEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
    private int type;
    public ExecutionProjectsType() {
        super();
    }

    public ExecutionProjectsType(int type) {
        this.type = type;
    }

    public String getStringRepresentation(){
        String result = Constants.EMPTY_STRING;
        final int latest7Days = ExecutionProjectsTypeEnum.Latest7Days.ordinal();
        int timeout = ExecutionProjectsTypeEnum.Timeout.ordinal();
        if (type==latest7Days){
            result="latest7Days";
        } else if (type==timeout){
            result="timeout";
        }
        return result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
