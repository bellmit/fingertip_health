package com.jqsoft.fingertip_health.bean.resident;

import com.jqsoft.fingertip_health.base.Constants;

/**
 * Created by Administrator on 2017-08-23.
 * 家庭成员bean
 */

public class FamilyMemberBean {
    private String memberCardNo;;//家庭成员身份证号
    private String memberName;//                   家庭成员名称
    private String personInfoKey;//                  家庭成员建档主键
    private String relationship;//                     与家庭成员的关系
//（ 0 本人或户主
//1 配偶
//2 子
//3 女
//4 孙子
//5 孙女或外孙子、外孙女
//6 父母
//7 祖父母或外祖父母
//8 兄、弟、姐、妹
//9 其他
//11 儿媳
//）

    public String getRelationshipRepresentation(){
        String result = Constants.EMPTY_STRING;
        if (Constants.FAMILY_MEMBER_SELF_VALUE.equals(relationship)){
            result = Constants.FAMILY_MEMBER_SELF;
        } else if (Constants.FAMILY_MEMBER_SPOUSE_VALUE.equals(relationship)){
            result = Constants.FAMILY_MEMBER_SPOUSE;
        } else if (Constants.FAMILY_MEMBER_SON_VALUE.equals(relationship)){
            result = Constants.FAMILY_MEMBER_SON;
        } else if (Constants.FAMILY_MEMBER_DAUGHTER_VALUE.equals(relationship)){
            result = Constants.FAMILY_MEMBER_DAUGHTER;
        } else if (Constants.FAMILY_MEMBER_GRAND_SON_VALUE.equals(relationship)){
            result = Constants.FAMILY_MEMBER_GRAND_SON;
        } else if (Constants.FAMILY_MEMBER_GRAND_DAUGHTER_VALUE.equals(relationship)){
            result = Constants.FAMILY_MEMBER_GRAND_DAUGHTER;
        } else if (Constants.FAMILY_MEMBER_PARENT_VALUE.equals(relationship)){
            result = Constants.FAMILY_MEMBER_PARENT;
        } else if (Constants.FAMILY_MEMBER_GRAND_PARENT_VALUE.equals(relationship)){
            result = Constants.FAMILY_MEMBER_GRAND_PARENT;
        } else if (Constants.FAMILY_MEMBER_SIBLING_VALUE.equals(relationship)){
            result = Constants.FAMILY_MEMBER_SIBLING;
        } else if (Constants.FAMILY_MEMBER_OTHER_VALUE.equals(relationship)){
            result = Constants.FAMILY_MEMBER_OTHER;
        } else if (Constants.FAMILY_MEMBER_DAUGHTER_IN_LAW_VALUE.equals(relationship)){
            result = Constants.FAMILY_MEMBER_DAUGHTER_IN_LAW;
        }
        return result;
    }

    public FamilyMemberBean() {
        super();
    }

    public FamilyMemberBean(String memberCardNo, String memberName, String personInfoKey, String relationship) {
        this.memberCardNo = memberCardNo;
        this.memberName = memberName;
        this.personInfoKey = personInfoKey;
        this.relationship = relationship;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPersonInfoKey() {
        return personInfoKey;
    }

    public void setPersonInfoKey(String personInfoKey) {
        this.personInfoKey = personInfoKey;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
