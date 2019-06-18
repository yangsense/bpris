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

    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/sysoperator/operator-pwd-change.js?_dc=${staticVersion}"></script>

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
                <form class="form-horizontal" role="form" id="editForm">
                    <div class="form-group">
                        <label class="col-xs-4 control-label no-padding-right"> 新密码： </label>
                        <div class="col-xs-8">
                            <input id="newPwd" name="newPwd" class="inputW150" type="password"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-4 control-label no-padding-right"> 确认新密码： </label>

                        <div class="col-xs-8">
                            <input id="newPwdConfirm" name="newPwdConfirm" class="inputW150" type="password"/>
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
<input type="hidden" name="operatorCode" id="operatorCode" value="${id}"/>
<div id="divExecute" class="executing">请等待...</div>
<div id="divBack" class="overlay"/>
</html>