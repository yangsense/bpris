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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/roleManage/role-manage-detail.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.all-3.5.min.js"></script>
    <style type="text/css">
        .ztree {margin:0; padding:5px; color:#333}
        .center{
            position: fixed;
            left: 50%;
        }
    </style>
</head>
<body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal"  id="inputForm" name="inputForm"   >
                    <input type="hidden" name="roleId"  id="roleId" value="${searchModel.roleId}"/>
                    <input type="hidden" name="role2menuList"  id="role2menuList" value="${searchModel.role2menuList}"/>
                    <input type="hidden" name="sysRole2menuList"  id="sysRole2menuList"/>
                    <input type="hidden" name="dataType"  id="dataType" value="${dataType}"/>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> <span style="color:red;">*</span>角色名称： </label>
                        <div class="col-xs-3"  style="width:35%">
                            <input  class="inputW120" type="text" id="roleName" name="roleName" value="${searchModel.roleName}" />
                            <span id="btnCheck"><input type="button"    value="检测"/></span>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"><span style="color:red;">*</span>系统选择： </label>
                        <div class="col-xs-3"  style="width:35%">
                            <select id="sysType" name="sysType" class="inputW160">
                                <c:forEach var="sysMenuBeans" items="${sysMenuBeans}">
                                    <option value="${sysMenuBeans.sysType}" selected>${sysMenuBeans.menuName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 角色描述： </label>
                        <div class="col-xs-3"  style="width:35%">
                            <textarea rows="1" cols="105" name="roleDes" id="roleDes">${searchModel.roleDes}</textarea>
                        </div>
                    </div>
                    <legend class="fswitch" style="font-size: 17px;">权限分配</legend>
                    <fieldset id="queryBox">
                    <div id="menuTree" class="ztree" style="height: 450px;overflow: auto;width:500px;margin: 0 auto;">
                    </div>
                    </fieldset>
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-info" type="button" id="saveBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                <span id="showText">新 增</span>
                            </button>
                            <button class="btn btn-info" type="button" id="closeBtn">
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
