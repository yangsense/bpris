<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <title>菜单管理</title>
    <%@ include file="/common/meta.jsp" %>

    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css">
    <script language="javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/bt.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/menuManage/menuInit.js"></script>
</head>
<body class="easyui-layout">
<div region="west" split="true" title="&nbsp;" maximizable="false"
     style="width:250px;padding:5px;" id="menuTree" class="ztree" style="height: 100%;overflow: auto;"></div>
     
<form id="searchForm">
    <s:hidden name="searchModel.menuId" id="menuId"></s:hidden>
</form>
<div region="center" width="95%" >
    <fieldset id="queryBox">
        <legend class="fswitch">菜单管理</legend>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="xbox">
            <tr>
                <td>
                    <div class="tDiv100">
                        <div class="tDiv2">
                            <ul>
                                <a href="javascript:void(0)" id="addMenuDir"
                                   class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                        class="icon-plus-sign"></span>新增目录</a>
                                <a href="javascript:void(0)" id="addMenu"
                                   class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                        class="icon-plus-sign"></span>新增菜单</a>
                                <a href="javascript:void(0)" id="updateMenu"
                                   class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                        class="icon-plus-sign"></span>修改</a>
                                <a href="javascript:void(0)" id="delBt"
                                   class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                        class="icon-plus-sign"></span>删除</a>
                                <a href="javascript:void(0)" id="sortBt"
                                   class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                        class="icon-plus-sign"></span>排序</a>
                            </ul>
                        </div>
                    </div>

                    <br/>
                    <div style="color: red">注：请选择节点进行操作!</div>
                </td>

        </table>
    </fieldset>
    <div>
        <table id="aisccareprovlist" width="100%" border="0" cellspacing="0" cellpadding="0">

        </table>
        <div id="aisccareprov_pager"></div>
    </div>
</div>

</body>

</html>
<script type="text/javascript">

    // 初始化菜单收缩
    $("#menuTree_1_switch").attr("class", "button level1 switch root_open");
    $("menuTree_2_switch").attr("class", "button level1 switch center_open");

</script>
