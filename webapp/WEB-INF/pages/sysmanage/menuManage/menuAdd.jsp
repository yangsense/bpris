<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/additional-methods.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/messages_cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/menuManage/menuAdd.js"></script>
</head>
<body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="searchForm">
                    <input id="parentMenuId" value="${parentMenuId}" hidden="true"/>
                    <input id="menuType" value="${menuType}" hidden="true"/>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 所属节点： </label>
                        <div class="col-xs-3" style="width: 35%" >
                            <input  class="inputW160" type="text" id="ssxt" name="sysTypeName" value="${sysTypeName}" disabled="disabled"/>
                        </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 菜单名称： </label>
                        <div class="col-xs-3" style="width: 35%" >
                            <input  class="inputW160" type="text" id="cdmc" name="menuName" value="${menuName}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%" id="cdljtemp"> 菜单链接： </label>
                        <div class="col-xs-3" style="width: 35%" id="cdljte">
                            <input  class="inputW160" type="text" id="cdlj" name="menuUrl" value="${menuUrl}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 启用状态： </label>
                        <div class="col-xs-3" style="width: 35%" >
                            <select id="qyzt" name="status" class="inputW160">
                                <option value="1" checked="checked">启用</option>
                                <option value="0">禁用</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 菜单图片： </label>
                        <div class="col-xs-3" style="width: 35%" >
                            <input  class="inputW160" type="text" id="cdtp" name="menuImage" value="${menuImage}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 菜单描述： </label>
                        <div class="col-xs-3" style="width: 35%" >
                            <input  class="inputW160" type="text" id="cdms" name="menuDesc" value="${menuDesc}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%" id="menuTypeTr1"> 菜单类型： </label>
                        <div class="col-xs-3" style="width: 35%" >
                            <select id="menuTypeTr" name="status" class="inputW160">
                                <option value="1">菜单节点</option>
                                <option value="2">功能点</option>
                            </select>
                        </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-info" type="button" onclick="addNew()">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                <span id="showText">新 增</span>
                            </button>
                            <button class="btn btn-info" type="button" onclick="closeDiv()">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                取 消
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
