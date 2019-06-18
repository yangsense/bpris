<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="${ctx}/aris/statics/common/js/jquery-1.11.2.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.css" type="text/css">
    <link href="${ctx}/aris/statics/pages/css/index/css/login.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/public.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/aris/statics/pages/css/index/css/icon.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="${ctx}/aris/statics/pages/initUseRight.js"></script>


    <title>区域PACS系统</title>
    <script language="javascript">
        var GLOBAL = {
            WEBROOT: "${ctx}",
            SYSTEM : "${system}"
        };
    </script>
    <style>
        body, html {
            background: #eefdf9;
        }
    </style>
</head>
<body class="body">
    <input type="hidden" name="errorCode" id="errorCode" value="${errorCode}"/>
    <input type="hidden" name="errorMessage" id="errorMessage" value="${errorMessage}"/>
<form id="verifyForm" action="${ctx}/aris/updateExpirationDte.html" method="post" onsubmit="return saveSecreKey()">
    <div class="fl loginList" id="normal">
        <ul>
            <li>
                <textarea id="normalRegistName" cols="89" rows="7" isRich="true" name="secretKey"
                          class="ckeditor" placeholder="很抱歉，您的秘钥已过期，请在此处输入秘钥激活后使用"></textarea>
            </li>
        </ul>
        <ul class="prompt" id="alt_normal"></ul>
    </div>
    <div class="main-content">
        <div class="page-content min_style">
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12">
                        <div class="clearfix form-actions">
                            <div class="col-bt-center">
                                <button class="btn btn-info" type="submit" id="loginSubmit">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    <span id="showText">验证</span>
                                </button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button class="btn btn-info" type="reset" id="closeBtn" onclick="cleanAll()">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    重置
                                </button>
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
