package com.jqsoft.fingertip_health.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**高血压管理的人 bean 和糖尿病患者
 * @author gujunqi
 * date：2014-1-7 
 */
public class HBPGuanLi_PersonInfo implements Serializable, Parcelable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sPersonID;
	private String sNo;//档案编号
	private String sPersonName;//姓名
	private String sSexName;//性别
	private String dBirthday;//出生日期（年龄需要计算）
	private String sCardNo;//证件号码

	private String sGradeName;//患病级别
	private String sDiagnosedHospital;//确诊医院
	private String dDiagnosedTime;//确诊时间
	private String sInputDeptName; //录入单位
	private String VisitingCount;//随访总次数
	private String HealthyCheckUpCount;//健康体检总次数
	private String iState;//是否管理（-1否    1是） 
	private String sAreaCode;
	private String sphoto;
	private String sphoto1;

	private boolean isCheck=false;

	public HBPGuanLi_PersonInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HBPGuanLi_PersonInfo(String sPersonID, String sNo,
                                String sPersonName, String sSexName, String dBirthday,
                                String sCardNo, String sGradeName, String sDiagnosedHospital,
                                String dDiagnosedTime, String sInputDeptName, String visitingCount,
                                String healthyCheckUpCount, String iState, String sAreaCode, String sphoto ) {
		super();
		this.sPersonID = sPersonID;
		this.sNo = sNo;
		this.sPersonName = sPersonName;
		this.sSexName = sSexName;
		this.dBirthday = dBirthday;
		this.sCardNo = sCardNo;
		this.sGradeName = sGradeName;
		this.sDiagnosedHospital = sDiagnosedHospital;
		this.dDiagnosedTime = dDiagnosedTime;
		this.sInputDeptName = sInputDeptName;
		VisitingCount = visitingCount;
		HealthyCheckUpCount = healthyCheckUpCount;
		this.iState = iState;
		this.sAreaCode = sAreaCode;
		this.sphoto = sphoto;

	}

	protected HBPGuanLi_PersonInfo(Parcel in) {
		sPersonID = in.readString();
		sNo = in.readString();
		sPersonName = in.readString();
		sSexName = in.readString();
		dBirthday = in.readString();
		sCardNo = in.readString();
		sGradeName = in.readString();
		sDiagnosedHospital = in.readString();
		dDiagnosedTime = in.readString();
		sInputDeptName = in.readString();
		VisitingCount = in.readString();
		HealthyCheckUpCount = in.readString();
		iState = in.readString();
		sAreaCode = in.readString();
		sphoto = in.readString();
		sphoto1 = in.readString();
		isCheck = in.readByte() != 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(sPersonID);
		dest.writeString(sNo);
		dest.writeString(sPersonName);
		dest.writeString(sSexName);
		dest.writeString(dBirthday);
		dest.writeString(sCardNo);
		dest.writeString(sGradeName);
		dest.writeString(sDiagnosedHospital);
		dest.writeString(dDiagnosedTime);
		dest.writeString(sInputDeptName);
		dest.writeString(VisitingCount);
		dest.writeString(HealthyCheckUpCount);
		dest.writeString(iState);
		dest.writeString(sAreaCode);
		dest.writeString(sphoto);
		dest.writeString(sphoto1);
		dest.writeByte((byte) (isCheck ? 1 : 0));
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<HBPGuanLi_PersonInfo> CREATOR = new Creator<HBPGuanLi_PersonInfo>() {
		@Override
		public HBPGuanLi_PersonInfo createFromParcel(Parcel in) {
			return new HBPGuanLi_PersonInfo(in);
		}

		@Override
		public HBPGuanLi_PersonInfo[] newArray(int size) {
			return new HBPGuanLi_PersonInfo[size];
		}
	};

	public String getsPersonID() {
		return sPersonID;
	}

	public void setsPersonID(String sPersonID) {
		this.sPersonID = sPersonID;
	}

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getsPersonName() {
		return sPersonName;
	}

	public void setsPersonName(String sPersonName) {
		this.sPersonName = sPersonName;
	}

	public String getsSexName() {
		return sSexName;
	}

	public void setsSexName(String sSexName) {
		this.sSexName = sSexName;
	}

	public String getdBirthday() {
		return dBirthday;
	}

	public void setdBirthday(String dBirthday) {
		this.dBirthday = dBirthday;
	}

	public String getsCardNo() {
		return sCardNo;
	}

	public void setsCardNo(String sCardNo) {
		this.sCardNo = sCardNo;
	}

	public String getsGradeName() {
		return sGradeName;
	}

	public void setsGradeName(String sGradeName) {
		this.sGradeName = sGradeName;
	}

	public String getsDiagnosedHospital() {
		return sDiagnosedHospital;
	}

	public void setsDiagnosedHospital(String sDiagnosedHospital) {
		this.sDiagnosedHospital = sDiagnosedHospital;
	}

	public String getdDiagnosedTime() {
		return dDiagnosedTime;
	}

	public void setdDiagnosedTime(String dDiagnosedTime) {
		this.dDiagnosedTime = dDiagnosedTime;
	}

	public String getsInputDeptName() {
		return sInputDeptName;
	}

	public void setsInputDeptName(String sInputDeptName) {
		this.sInputDeptName = sInputDeptName;
	}

	public String getVisitingCount() {
		return VisitingCount;
	}

	public void setVisitingCount(String visitingCount) {
		VisitingCount = visitingCount;
	}

	public String getHealthyCheckUpCount() {
		return HealthyCheckUpCount;
	}

	public void setHealthyCheckUpCount(String healthyCheckUpCount) {
		HealthyCheckUpCount = healthyCheckUpCount;
	}

	public String getiState() {
		return iState;
	}

	public void setiState(String iState) {
		this.iState = iState;
	}
	public String getsAreaCode() {
		return sAreaCode;
	}

	public void setsAreaCode(String sAreaCode) {
		this.sAreaCode = sAreaCode;
	}
	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public String getSphoto() {
		return sphoto;
	}

	public void setSphoto(String sphoto) {
		this.sphoto = sphoto;
	}
	public String getSphoto1() {
		return sphoto1;
	}

	public void setSphoto1(String sphoto1) {
		this.sphoto1 = sphoto1;
	}

}
