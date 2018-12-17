package com.jqsoft.fingertip_health.bean;

/**
 * Created by Administrator on 2018/9/10.
 */

public class DiagnosisInVo {
    private String no;//诊断序号	Integer	N	0主诊断，1、2、3、4、... 等等为次诊断并发症等
    private String icdCode;//疾病编码	String	N
    private String icdName;//	疾病名称	String	N

    public DiagnosisInVo() {
    }

    public DiagnosisInVo(String no, String icdCode, String icdName) {
        this.no = no;
        this.icdCode = icdCode;
        this.icdName = icdName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode = icdCode;
    }

    public String getIcdName() {
        return icdName;
    }

    public void setIcdName(String icdName) {
        this.icdName = icdName;
    }
}
