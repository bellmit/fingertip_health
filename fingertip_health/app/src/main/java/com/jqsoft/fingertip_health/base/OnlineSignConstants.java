package com.jqsoft.fingertip_health.base;

/**
 * Created by Administrator on 2017-07-06.
 */

public interface OnlineSignConstants {

    String sign = "sign";//	String	N	密钥（JQSOFT65350880）      用base64加密
    String key = "key";//	String	N	主键(xx-xx-xx-xx-xx) UUID  格式为(D08DBC77-2FC0-4D87-83D1-B36480ADE36C)
    String fdSigningDoctorMode = "fdSigningDoctorMode";//	String	N	（签约方式： 1：按年度签约  2：随到随签）
    String statusCode = "statusCode";//	String	N	1：起草中、2：已提交（已签约）、3：审核通过、4：审核不通过，5解约
    String serverPackageName = "serverPackageName";//	String	N	服务包名称，多个用分号隔开
    String isPersonality = "isPersonality";//	String	N	是否含个性化服务（1是、0否）
    String signDT = "signDT";//	String	N	签约时间
    String signMode = "signMode";//	String	N	签约形式（1、家庭、2、个人）
    String signHomeCode = "signHomeCode";//	String	N	户编号（一户生成一个GUID）
    String signDeptName = "signDeptName";//	String	N	签约机构名称(甲方)
    String signDeptCode = "signDeptCode";//	String	N	签约机构编码
    String signDeptPhone = "signDeptPhone";//	String	Y	机构的联系电话
    String teamName = "teamName";//	String	N	团队名称
    String teamCode = "teamCode";//	String	N	团队编码
    String signTeamHeaderName = "signTeamHeaderName";//	String	N	签约团队负责人姓名
    String signTeamHeaderCode = "signTeamHeaderCode";//	String	N	签约团队负责人编码
    String signTeamHeaderPhone = "signTeamHeaderPhone";//	String	N	签约团队负责人电话
    String doctorName = "doctorName";//	String	N	家庭医生姓名
    String doctorCode = "doctorCode";//	String	N	家庭医生编码
    String doctorPhone = "doctorPhone";//	String	N	家庭医生电话
    String userName = "userName";//	String	N	签约人姓名
    String sexCode = "sexCode";//	String	N	签约人性别编号
    String cardNo = "cardNo";//	String	y	签约人身份证号(isUseGuardian = 0  必填)
    String guardianCardNo = "guardianCardNo";//	String	y	监护人身份证号(isUseGuardian = 1  必填)
    String isUseGuardian = "isUseGuardian";//	String	N	是否启用监护人 0 代表本人身份证号码，1代表的是 监护人身份证号码
    String phone = "phone";//	String	N	签约人联系电话
    String agriculturalCardNo = "agriculturalCardNo";//	String	N	医保类型(0 新农合  1职工医保 3居民医保 4其他)
    String isHouseholder = "isHouseholder";//	String	Y	是否是户主
    String personID = "personID";//	String	N	个人健康档案唯一标识符
    String no = "no";//	String	N	健康档案号
    String isRelation = "isRelation";//	String	N	是否关联亲属（0未关联1关联)
    String filingStatue = "filingStatue";//	String	N	包是否完成 0 未完成，1已完成
    String recordMode = "recordMode";//	String	Y	录入方式（1手动、2农合卡、3医保卡、4身份证）
    String inputDeptCode = "inputDeptCode";//	String	N	行政机构编码
    String inputDeptName = "inputDeptName";//	String	N	行政机构名称
    String areaCode = "areaCode";//	String	N	地区编码
    String areaName = "areaName";//	String	N	地区名称
    String addUserCode = "addUserCode";//	String	N	创建人编码
    String addUserName = "addUserName";//	String	N	创建人名称
    String addOrgId = "addOrgId";//	String	N	添加机构ID
    String addDT = "addDT";//	String	N	录入时间
    String updateUserCode = "updateUserCode";//	String	Y	修改人编码
    String updateUserName = "updateUserName";//	String	Y	修改人名称
    String updateOrgId = "updateOrgId";//	String	Y	修改机构ID
    String updateDT = "updateDT";//	String	Y	修改时间
    String isFilingStatue = "isFilingStatue";//	String	N	是否关联健康档案（0未关联1已关联）
    String personMold = "personMold";//	String	N	人员类型 （详细解释在后面）
    String docOrganizationKey = "docOrganizationKey";//	String	N	家庭医生机构编码 (公卫中的机构）
    String docOrganizationName = "docOrganizationName";//	String	N	家庭医生机构名称
    String docLoginName = "docLoginName";//	String	N	签约医生公卫系统登录帐号
    String serviceContent = "serviceContent";//	String	N	服务内容
    String isExecute = "isExecute";//	String	N	是否执行(0执行1未执行)
    String docUserID = "docUserID";//	String	N	家庭医生UserID  注：为基本公共卫生里面的签约医生ID
    String isCharge = "isCharge";//	String	N	是否已收费（1是，0否）
    String actualPackageSumFee = "actualPackageSumFee";//	String	N	实收金额总计(not null)
    String packSumFee = "packSumFee";//	String	N	费用总计
    String newRuralCMSFee = "newRuralCMSFee";//	String	N	新农合补偿金额总计(not null)
    String otherReduceFee = "otherReduceFee";//	String	N	减免金额总计(not null)
    String shouldSelfFee = "shouldSelfFee";//	String	N	应自付金额总计(not null)
    String jbggwsState = "jbggwsState";//	String	N	是否是基本公共卫生(0 是  1 否)
    String signPageYear = "signPageYear";//	String	N	签约年份
    String startExecData = "startExecData";//	String	N	开始执行时间
    String endExecData = "endExecData";//	String	N	结束执行时间
    String death = "death";//	String	Y	死亡状态
    String familySysno = "familySysno";//	String	y	家庭编码
    String memberSysno = "memberSysno";//	String	y	人员编码
    String interfaceStatus = "interfaceStatus";//	String	N	1 代表关联农合成功 2 代表未关联农合成功
    String basicPubilcMoney = "basicPubilcMoney";//	String	N	公共卫生承担金额
    String isPrintyjj = "isPrintyjj";//	String	N	是否打印签约协议（1是0否）
    String idType = "idType";//	String	N	报补类型（农合和医报）

    String detailInVoList = "detailInVoList";//  集合    签约明细
    String detailInVoList_key = "key";//	String	N	主键(xx-xx-xx-xx-xx) UUID  格式为(D08DBC77-2FC0-4D87-83D1-B36480ADE36C)
    String detailInVoList_signKey = "signKey";//	String	N	签约主键
    String detailInVoList_signMode = "signMode";//	String	N	签约形式（1、家庭、2、个人）
    String detailInVoList_packageID = "packageID";//	String	N	服务包Id
    String detailInVoList_finished = "finished";//	String	N	完成状态（0未完成1已完成）
    String detailInVoList_hadRefusedItem = "hadRefusedItem";//	String	N	拒绝项目
    String detailInVoList_isPersonality = "isPersonality";//	String	N	是否含个性化服务（1是、0否）


    String shortMessageInVoList = "shortMessageInVoList";// 集合    签约提醒
    String shortMessageInVoList_signDetailID = "signDetailID";//	String	N	签约明细ID
    String shortMessageInVoList_key = "key";//	String	N	主键(xx-xx-xx-xx-xx) UUID  格式为(D08DBC77-2FC0-4D87-83D1-B36480ADE36C)
    String shortMessageInVoList_serviceClassKey = "serviceClassKey";//	String	N	服务类主键
    String shortMessageInVoList_serviceContentKey = "serviceContentKey";//	String	N	服务内容主键
    String shortMessageInVoList_serviceContentItemKey = "serviceContentItemKey";//	String	N	服务项目内容主键
    String shortMessageInVoList_remindDT = "remindDT";//	String	N 提醒时间
    String shortMessageInVoList_title = "title";//	String	N	标题
    String shortMessageInVoList_linkAddress = "linkAddress";//	String	y	链接地址
    String shortMessageInVoList_createUserID = "createUserID";//	String	N	创建人ID
    String shortMessageInVoList_createUserName = "createUserName";//	String	N	创建人名称
    String shortMessageInVoList_createDT = "createDT";//	String	N	创建时间
    String shortMessageInVoList_status = "status";//	String	N	0未通知1已通知2不做通知
    String shortMessageInVoList_docUserID = "docUserID";//	String	N	签约医生编码
    String shortMessageInVoList_townDeptCode = "townDeptCode";//	String	N	乡镇机构编码
    String shortMessageInVoList_itemsFinished = "itemsFinished";//	String	N	该项目是否执行完成


    String signServiceContentItemsList = "signServiceContentItemsList";// 集合    签约服务项目
    String signServiceContentItemsList_signDetailID = "signDetailID";//	String	N	签约明细ID
    String signServiceContentItemsList_key = "key";//	String	N	主键(xx-xx-xx-xx-xx) UUID  格式为(D08DBC77-2FC0-4D87-83D1-B36480ADE36C)
    String signServiceContentItemsList_serviceClassKey = "serviceClassKey";//	String	N	服务类主键
    String signServiceContentItemsList_serviceContentKey = "serviceContentKey";//	String	N	服务内容主键
    String signServiceContentItemsList_serviceContentItemKey = "serviceContentItemKey";//	String	N	服务项目内容主键
    String signServiceContentItemsList_serviceItemsName = "serviceItemsName";//	String	N	服务项目名称
    String signServiceContentItemsList_shouldExecTimes = "shouldExecTimes";//	String	N	应该执行次数
    String signServiceContentItemsList_hadExecTimes = "hadExecTimes";//	String	Y 执行次数
    String signServiceContentItemsList_isNeedExec = "isNeedExec";//	String	N	是否需要执行
    String signServiceContentItemsList_execOfficer = "execOfficer";//	String	N	执行单位
    String signServiceContentItemsList_execTimes = "execTimes";//	String	Y	执行次数

}
