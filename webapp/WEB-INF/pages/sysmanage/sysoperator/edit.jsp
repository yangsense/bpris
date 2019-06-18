<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="cache-control" content="no-cache">
    <%@ include file="/common/meta.jsp" %>

    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet"
          href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>

    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/sysoperator/edit.js?_dc=${staticVersion}"></script>

    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/validate/jquery.validate.css"
          type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/validate/additional-methods.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/validate/messages_cn.js"></script>
</head>

<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12" style="padding:6px;">
                <form class="form-horizontal" role="form" id="inputForm">
                    <div class="form-group">
                        <label class="col-xs-4 control-label no-padding-right"> 工号： </label>
                        <div class="col-xs-8">
                            <input id="operatorCode" name="operatorCode" class="inputW150" />
                            <button class="btn-warning" type="button" id="saveCheck">
                                <i class="ace-icon fa fa-check bigger-110 icon-search"></i>
                                检 测
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-4 control-label no-padding-right"> 姓名： </label>

                        <div class="col-xs-8">
                            <input id="operatorName" name="operatorName" class="inputW150"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-4 control-label no-padding-right"> 联系方式： </label>
                        <div class="col-xs-8">
                            <input id="telNo" name="telNo" class="inputW150" />
                        </div>
                    </div>
                    <div id="pswDiv">
                        <div class="form-group">
                            <label class="col-xs-4 control-label no-padding-right"> 密码： </label>

                            <div class="col-xs-8">
                                <input id="newPwd" name="newPwd" class="inputW150" type="password"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-4 control-label no-padding-right"> 确认密码： </label>
                            <div class="col-xs-8">
                                <input id="operatorPsw" name="operatorPsw" class="inputW150" type="password"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-4 control-label no-padding-right"> 用户状态： </label>

                        <div class="col-xs-8">
                            <select id="operatorState" name="operatorState" readonly="readonly">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-4 control-label no-padding-right"> 所属机构： </label>
                        <div class="col-xs-8">
                            <input id="orgName" name="orgName" class="inputW150" />
                            <input type="hidden" class="edit w200" name="orgId"  id="orgId"/>
                            <span><input type="button" id="addOrg" value="所属机构选择" onclick="addOp2BelongOrg()"/></span>
                        </div>
                    </div>
                  <%--  <div class="form-group">
                        <label class="col-xs-4 control-label no-padding-right"> 所属科室： </label>
                        <div class="col-xs-8">
                            <select  multiple="multiple" class="inputW150" id="officeName" name="officeId"></select>
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <label class="col-xs-4 control-label no-padding-right"> 备注： </label>

                        <div class="col-xs-8">
                            <input id="remarks" name="remarks" class="inputW150"/>
                        </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-primary" type="button" id="saveBtn">
                                <i class="ace-icon fa fa-check bigger-110 icon-save"></i>
                                确 认
                            </button>
                            <button class="btn btn-gray" type="button" id="closeBtn">
                                <i class="ace-icon fa fa-check bigger-110 icon-remove"></i>
                                关 闭
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<input type="hidden" name="operatorCodeId" id="operatorCodeId" value="${id}"/>
<div id="divExecute" class="executing">请等待...</div>
<div id="divBack" class="overlay"/>
</html>