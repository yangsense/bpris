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
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/area.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/sysorg/orgmanage-show.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#showForm").find("input").attr("readonly",true).css("background-color","#0000");
            $("#showForm").find("select").attr("readonly",true).css("background-color","#0000");
            $("#showForm").find("textarea").attr("readonly",true).css("background-color","#0000");
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
            <form id="searchForm" action="#">
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

                <input type="hidden" id="orgId" name="bean.orgId" value="${bean.orgId}"/>
                <!-- 父组织机构等级 -->
                <input type="hidden" id="parentOrgLevel" name="bean.orgLevel" value="${bean.orgLevel}"/>
                <!-- 父组织机构ID -->
                <input type="hidden" id="parentOrgId" name="bean.parentOrgId" value="${bean.orgId}"/>
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="biaoge">
                    <tr>
                        <th width="1%">
                            机构名称<span>*</span>
                        </th>
                        <td align="left" width="8%">
                            ${bean.orgName}
                        </td>
                        <th width="1%">
                            机构代码<span style="colorred;">*</span>
                        </th>
                        <td align="left" width="8%">
                            ${bean.orgCode}
                        </td>
                    </tr>
                    <tr>
                        <th>
                            状态
                        </th>
                        <td align="left">
                            <c:if test="${bean.state=='1'}">
                                正常
                            </c:if>
                            <c:if test="${bean.state=='0'}">
                                失效
                            </c:if>
                        </td>
                        <th>
                            机构简称
                        </th>
                        <td align="left">
                            ${bean.orgShort}
                        </td>
                    </tr>

                    <tr>
                        <th>
                            成立时间
                        </th>
                        <td align="left">
                            ${bean.setupTime}
                        </td>
                        <th>
                            机构唯一码
                        </th>
                        <td align="left">${bean.duns}
                        </td>
                    </tr>
                    <tr>
                        <th nowrap>
                            所属区域
                        </th>
                        <td align="left" colspan="3" >

                            <c:if test="${not empty provinceName}">
                                ${provinceName}
                            </c:if>
                            <c:if test="${not empty cityName}">
                                ${cityName}
                            </c:if>
                            <c:if test="${not empty countyName}">
                                ${countyName}
                            </c:if>
                            <c:if test="${not empty townName}">
                                ${townName}
                            </c:if>
                            <c:if test="${not empty villageName}">
                                ${villageName}
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th nowrap>
                            联系地址
                        </th>
                        <td colspan="3">
                            ${bean.orgAddr}
                        </td>
                    </tr>
                    <tr>
                        <th nowrap>
                            机构负责人
                        </th>
                        <td >
                            ${bean.lawPersonName}
                        </td>
                        <th nowrap>
                            负责人电话
                        </th>
                        <td >
                            ${bean.lawPersonTel}
                        </td>
                    </tr>
                    <tr>
                        <th nowrap>
                            传真号
                        </th>
                        <td >
                            ${bean.faxNo}
                        </td>
                        <th >
                            机构联系人
                        </th>
                        <td >
                            ${bean.contactName}
                        </td>
                    </tr>
                    <tr>
                        <th nowrap>
                            联系人电话
                        </th>
                        <td align="left">
                            ${bean.contactTel}
                        </td>
                        <th>
                            是否分支机构
                        </th>
                        <td align="left">
                            <c:if test="${bean.branchMark=='1'}">
                                是
                            </c:if>
                            <c:if test="${bean.branchMark=='0'}">
                                否
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th nowrap>
                            邮编
                        </th>
                        <td >
                            ${bean.postcode}
                        </td>
                        <th nowrap>
                            邮箱
                        </th>
                        <td align="left">
                            ${bean.email}
                        </td>
                    </tr>
                    <tr>
                        <th nowrap>
                            机构网址
                        </th>
                        <td align="left">
                            ${bean.webUrl}
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
                            <select id="economicTypeCode" name="economicTypeCode" style="width: 150px;" class="input-select" >
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
                            <select  class="input-select" id="province1" style="margin-right:10px;">
                                <option value=" " >--请选择--</option>
                            </select>
                            <select  class="input-select" id="city1"  style="margin-right:10px;">
                                <option value=" " >--请选择--</option>
                            </select>
                            <select  class="input-select" id="county1" style="margin-right:10px;">
                                <option value=" " >--请选择--</option>
                            </select>
                            <select  class="input-select" id="town1" style="margin-right:10px;">
                                <option value=" " >--请选择--</option>
                            </select>
                            <select  id="village1" class="input-select">
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
                        <th nowrap>
                            法定代表人：
                        </th>
                        <td align="left">
                            ${bean.orgHead}
                        </td>
                        <th nowrap>
                            代表人电话：
                        </th>
                        <td align="left">
                            ${bean.orgHeadTelephone}
                        </td>
                    </tr>
                    <tr>
                        <th  >
                            机构简介
                        </th>
                        <td align="left" colspan="3">
                            ${bean.orgIntroduct}
                        </td>
                    </tr>
                    <tr>
                        <th  >
                            备注
                        </th>
                        <td align="left" colspan="3">
                            ${bean.remarks}
                        </td>
                    </tr>
                </table>
            </form>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th colspan="3" class="c n_b_b">
                        <a href="javascript:void(0)" onclick="addDocTag()" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-plus-sign"></span>添 加</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0)" onclick="updateDocTag();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>修 改</a>&nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="javascript:void(0)" onclick="delOne();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>删 除</a>
                    </th>
                </tr>
            </table>
        </div>
    </div>
</div>

</body>
</html>
