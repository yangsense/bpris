<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>人员信息</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/validate/additional-methods.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/validate/messages_cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/area.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/sysorg/checkDuns.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/sysorg/orgmanage-edit.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#showForm").find("input").css("background-color","#0000");
            $("#showForm").find("select").css("background-color","#0000");
            $("#showForm").find("textarea").css("background-color","#0000");
        });
    </script>

    <style type="text/css">
        .biaoge { border-top:1px solid #cce5e5; border-left:1px solid #d3e9f8;  }
        .biaoge th{ background:#e5f5ff; padding:5px; text-align:right; color:#666; border-right:1px solid #d3e9f8; border-bottom:1px solid #d3e9f8;}
        .biaoge th span{color:#f00;}
        .biaoge td {padding: 5px; border-right:1px solid #d3e9f8; border-bottom:1px solid #d3e9f8; line-heigth:200%; }
/*
        .biaoge td span{color: #ff0000;font-weight: bold;font-size: 14px;padding-left: 5px;}
*/
    </style>
</head>
<body>
<div id="showForm" class="wrap100 quickrecharge">
    <div class="q_r_list">
        <div class="listTable">
            <form id="addForm" ame="addForm" action="#">
                <input type="hidden" id="provinceValue" value="${provinceValue }"/>
                <input type="hidden" id="cityValue" value="${cityValue }"/>
                <input type="hidden" id="countyValue" value="${countyValue }"/>
                <input type="hidden" id="townValue" value="${townValue }"/>
                <input type="hidden" id="villageValue" value="${villageValue }"/>
                <input type="hidden" id="provinceValue1" value="${provinceValue1 }"/>
                <input type="hidden" id="cityValue1" value="${cityValue1 }"/>
                <input type="hidden" id="countyValue1" value="${countyValue1 }"/>
                <input type="hidden" id="townValue1" value="${townValue1 }"/>
                <input type="hidden" id="villageValue1" value="${villageValue1 }"/>

                <input type="hidden" id="orgId" name="orgId" value="${bean.orgId}"/>
                <!-- 父组织机构等级 -->
                <input type="hidden" id="parentOrgLevel" name="orgLevel" value="${bean.orgLevel}"/>
                <!-- 父组织机构ID -->
                <input type="hidden" id="parentOrgId" name="parentOrgId" value="${bean.orgId}"/>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="biaoge">
                    <tr>
                        <th>
                            机构名称<span style="colorred;">*</span>
                        </th>
                        <td >
                            <input type="text" id="orgName" name="orgName" class="w150" value="${bean.orgName}"/>
                        </td>
                        <th>
                            机构代码<span style="colorred;">*</span>
                        </th>
                        <td >
                            <input type="text" id="orgCode" name="orgCode" class="w150" value="${bean.orgCode}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            状态
                        </th>
                        <td >
                            <select id="state" name="state" style="width:150px;" cssClass="input-select">
                                <option value="1" <c:if test="${bean.state == '1'}">selected</c:if> >正常</option>
                                <option value="0" <c:if test="${bean.state == '0'}">selected</c:if>>失效</option>
                            </select>
                        </td>
                        <th>
                            机构简称
                        </th>
                        <td >
                            <input type="text" id="orgShort" name="orgShort" class="w150" value="${bean.orgShort}"/>
                        </td>
                    </tr>

                    <tr>
                        <th>
                            成立时间
                        </th>
                        <td  align="left">
                            <input type="text" name="setupTime"  value="${bean.setupTime}"  id="setupTime"  class="Wdate w150"  style="width:153px" onfocus="WdatePicker({dateFmt:'yyyy'})" />
                        </td>
                        <th>
                            机构唯一码
                        </th>
                        <td >
                            <input type="text" id="duns" name="duns" value="${bean.duns}"  onchange="checkDunsUID(1);" class="w150"/>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            所属区域
                        </th>
                        <td  colspan="3" >
                           <%-- <select name="bean.provinceCode" class="input-select" id="province1" style="margin-right:10px;display:block;">--%>
                            <select name="provinceCode" class="input-select" id="province1" style="margin-right:10px;">
                                <option value=" ">--请选择--</option>
                            </select>
                            <select name="cityCode" class="input-select" id="city1" style="margin-right:10px;">
                                <option value=" " >--请选择--</option>
                            </select>
                            <select name="districtCode" class="input-select" id="county1" style="margin-right:10px;">
                                <option value=" " >--请选择--</option>
                            </select>
                            <select name="streetCode"  class="input-select" id="town1" style="margin-right:10px;">
                                <option value=" " >--请选择--</option>
                            </select>
                            <select name="villageCode" id="village1" class="input-select">
                                <option value=" " >--请选择--</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            联系地址
                        </th>
                        <td  colspan="3">
                            <input type="text" style="width:600px;" id="orgAddr" name="orgAddr" class="w150" value="${bean.orgAddr}"/>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            机构负责人
                        </th>
                        <td >
                            <input type="text" id="lawPersonName" name="lawPersonName" class="w150" value="${bean.lawPersonName}"/>
                        </td>
                        <th >
                            负责人电话
                        </th>
                        <td >
                            <input type="text" id="lawPersonTel" name="lawPersonTel" class="w150" value="${bean.lawPersonTel}"/>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            传真号
                        </th>
                        <td >
                            <input type="text" id="faxNo" name="faxNo" class="w150" value="${bean.faxNo}"/>
                        </td>
                        <th >
                            机构联系人
                        </th>
                        <td >
                            <input type="text" id="contactName" name="contactName" class="w150" value="${bean.contactName}"/>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            联系人电话
                        </th>
                        <td >
                            <input type="text" id="contactTel" name="contactTel" class="w150" value="${bean.contactTel}"/>
                        </td>
                        <th>
                            是否分支机构
                        </th>
                        <td >
                            <select id="branchMark" name="branchMark"
                                    style="width: 150px;" cssClass="input-select">
                                <option value="1" <c:if test="${bean.state == '1'}">selected</c:if> >是</option>
                                <option value="0" <c:if test="${bean.state == '0'}">selected</c:if>>否</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            邮编
                        </th>
                        <td >
                            <input type="text" id="postcode" name="postcode" class="w150" value="${bean.postcode}"/>
                        </td>
                        <th >
                            邮箱
                        </th>
                        <td >
                            <input type="text" id="email" name="email" class="w150" value="${bean.email}"/>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            机构网址
                        </th>
                        <td >
                            <input type="text" id="webUrl" name="webUrl" class="w150" value="${bean.webUrl}"/>
                        </td>
                        <th >
                            分类管理代码：
                        </th>
                        <td >
                            <select id="managementCode" name="managementCode" style="width: 150px;" cssClass="input-select" >
                                <c:forEach var="managementCodeBean" items="${searchModel.managementCodeBean}">
                                    <option value="${managementCodeBean.itemNo}"<c:if test="${bean.managementCode == managementCodeBean.itemNo}">selected</c:if>  >${managementCodeBean.itemName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            经济类型代码
                        </th>
                        <td >
                            <select id="economicTypeCode" name="economicTypeCode" style="width: 150px;" cssClass="input-select" >
                                <c:forEach var="economicTypeCodeBean" items="${searchModel.economicTypeCodeBean}">
                                    <option value="${economicTypeCodeBean.itemNo}"<c:if test="${bean.economicTypeCode == economicTypeCodeBean.itemNo}">selected</c:if>  >${economicTypeCodeBean.itemName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th >
                            主办单位类别
                        </th>
                        <td >
                            <select id="organizerTypeCode" name="organizerTypeCode" style="width: 150px;" cssClass="input-select" >
                                <c:forEach var="organizerTypeCodeBean" items="${searchModel.organizerTypeCodeBean}">
                                    <option value="${organizerTypeCodeBean.itemNo}"<c:if test="${bean.organizerTypeCode == organizerTypeCodeBean.itemNo}">selected</c:if>  >${organizerTypeCodeBean.itemName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            机构类型(一级)：
                        </th>
                        <td>
                            <select id="orgTypeFirst" name="orgTypeFirst" style="width: 150px;" cssClass="input-select" >
                                <c:forEach var="orgTypeFirstList" items="${searchModel.orgTypeFirstList}">
                                    <option value="${orgTypeFirstList.itemNo}"<c:if test="${bean.orgTypeFirst == orgTypeFirstList.itemNo}">selected</c:if>  >${orgTypeFirstList.itemName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th >
                            机构类型(二级)：
                        </th>
                        <td>
                            <select id="orgTypeSecond" name="orgTypeSecond" style="width: 150px;" cssClass="input-select" >
                                <c:forEach var="orgTypeSecondList" items="${searchModel.orgTypeSecondList}">
                                    <option value="${orgTypeSecondList.itemNo}"<c:if test="${bean.orgTypeSecond == orgTypeSecondList.itemNo}">selected</c:if>  >${orgTypeSecondList.itemName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            机构类型(三级)：
                        </th>
                        <td >
                            <select id="orgTypeThird" name="orgTypeThird" style="width: 150px;" cssClass="input-select" >
                                <c:forEach var="orgTypeThirdList" items="${searchModel.orgTypeThirdList}">
                                    <option value="${orgTypeThirdList.itemNo}"<c:if test="${bean.orgTypeThird == orgTypeThirdList.itemNo}">selected</c:if>  >${orgTypeThirdList.itemName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th >
                            政府办机隶属关系：<span style="colorred;">*</span>
                        </th>
                        <td >
                            <select id="subordinateRelationshipCode" name="subordinateRelationshipCode" style="width: 150px;" cssClass="input-select" >
                                <c:forEach var="subordinateRelationshipCodeBean" items="${searchModel.subordinateRelationshipCodeBean}">
                                    <option value="${subordinateRelationshipCodeBean.itemNo}"<c:if test="${bean.subordinateRelationshipCode == subordinateRelationshipCodeBean.itemNo}">selected</c:if>  >${subordinateRelationshipCodeBean.itemName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            政府办机构隶属行政区划：<span style="colorred;">*</span>
                        </th>
                        <td align="left" colspan="3">
<%--
                            <select name="searchModel.provinceCode" class="input-select" id="province" style="margin-right:10px;display:none;">
--%>
                            <select  class="input-select" id="province" style="margin-right:10px;">
                                <option value=" " >--请选择--</option>
                            </select>
                            <select  class="input-select" id="city"  style="margin-right:10px;">
                                <option value="    " >--请选择--</option>
                            </select>
                            <select  class="input-select" id="county" style="margin-right:10px;">
                                <option value=" " >--请选择--</option>
                            </select>
                            <select  class="input-select" id="town" style="margin-right:10px;">
                                <option value=" " >--请选择--</option>
                            </select>
                            <select  id="village" class="input-select">
                                <option value=" " >--请选择--</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <th >
                            医院等级(级)：<span style="colorred;">*</span>
                        </th>
                        <td >
                            <select id="orgClassLevel" name="orgClassLevel" style="width: 150px;" cssClass="input-select" >
                                <c:forEach var="orgClassLevelBean" items="${searchModel.orgClassLevelBean}">
                                    <option value="${orgClassLevelBean.itemNo}"<c:if test="${bean.orgClassLevel == orgClassLevelBean.itemNo}">selected</c:if>  >${orgClassLevelBean.itemName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th >
                            医院等级(等)：<span style="colorred;">*</span>
                        </th>
                        <td >
                            <select id="orgClassGrade" name="orgClassGrade" style="width: 150px;" cssClass="input-select" >
                                <c:forEach var="orgClassGradeBean" items="${searchModel.orgClassGradeBean}">
                                    <option value="${orgClassGradeBean.itemNo}"<c:if test="${bean.orgClassGrade == orgClassGradeBean.itemNo}">selected</c:if>  >${orgClassGradeBean.itemName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            法定代表人：
                        </th>
                        <td >
                            <input type="text" id="orgHead" name="orgHead" class="w150" value="${bean.orgHead}"/>
                        </td>
                        <th >
                            代表人电话：
                        </th>
                        <td >
                            <input type="text" id="orgHeadTelephone" name="orgHeadTelephone" class="w150" value="${bean.orgHeadTelephone}"/>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            机构简介
                        </th>
                        <td  colspan="3">
							<textarea id="orgIntroduct" name="orgIntroduct" rows="3"
                                      style="width: 620px">${bean.orgIntroduct}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            备注
                        </td>
                        <td  colspan="3">
							<textarea id="remarks" name="remarks" rows="3"
                                      style="width: 620px">${bean.remarks}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            上级机构
                        </th>
                        <td  colspan="3">
                            <input id="parentOrgName" style="width: 300px;color: black;" type="text"  value="${parentOrgName}" readonly="readonly"/>
                            <span><input type="button" id="addOrg" value="上级机构选择" onclick="addOp2BelongOrg()"/></span>
                        </td>
                    </tr>
                </table>
            </form>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th colspan="3" class="c n_b_b">
                        <a href="javascript:void(0)" id="saveBtn"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>保 存</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0)" onclick="closeDiv()"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>取 消</a>
                    </th>
                </tr>
            </table>
        </div>
    </div>
</div>

</body>
</html>
