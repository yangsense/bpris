<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <title>机构管理</title>
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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/sysmanage/sysorg/orgManageInit.js"></script>
    <style type="text/css">
        .flexigrid div.fbutton span{
            padding: 8px 15px 8px 0;
        }
    </style>
</head>
<body class="easyui-layout">
<div region="west" split="true" title="&nbsp;" maximizable="false"
     style="width:250px;padding:5px;" id="orgTree" class="ztree" ></div>
<div region="center" id="rightQueryPart">
    <iframe id="orgDetailHide" src=""  align="center"  width="100%" height="100%" frameBorder='0' style="display:none;"></iframe>
    <div id="queryBox">
            <form id="searchForm" name="searchForm">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="xbox" >
                   <!--  <tr>
                        <th class="r" width="13%">机构名称:</th>
                        <td width="18%">
                            <input type="hidden" id="parentOrgId" name="parentOrgId"/>
                            <input type="text" id="orgName" name="orgName" class="inputW160"/>
                        </td>
                        <td align="center" colspan="2">
                            <a href="javascript:void(0)" onclick="doQuery();"
                               class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                    class="icon-search"></span>查 询</a>
                            <a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');doQuery()"
                               class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                    class="icon-retweet"></span>重 置</a>
                        </td>
                    </tr> -->
                    
                    <tr>
                    	<td>
                    		机构名称：
                    		<input type="hidden" id="parentOrgId" name="parentOrgId"/>
                            <input type="text" id="orgName" name="orgName" class="inputW160"/>
                    	</td>
                 
                    </tr>
                    
                    
                </table>
                
                 <div style="margin:10px auto;">
                   	<span>
                   		 <a href="javascript:void(0)"  onclick="addDocTag()"
                               class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                    class="icon-plus-sign"></span>新增</a>
                            <a href="javascript:void(0)" onclick="updateDocTagRightTable();"
                               class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                    class="icon-plus-sign"></span>修改</a>
                            <a href="javascript:void(0)" onclick="delOne();"
                               class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                    class="icon-plus-sign"></span>删除</a>
                   	</span>
                   	<span style="float:right;">
                   		 	<a href="javascript:void(0)" onclick="doQuery();"
                               class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                    class="icon-search"></span>查 询</a>
                            <a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');doQuery()"
                               class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                    class="icon-retweet"></span>重 置</a>
                   	</span>
                </div>
                
                <!-- <table width="100%" border="0" cellspacing="0" cellpadding="0" class="xbox">
                    <tr>
                        <td>
                            <a href="javascript:void(0)"  onclick="addDocTag()"
                               class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                    class="icon-plus-sign"></span>新增</a>
                            <a href="javascript:void(0)" onclick="updateDocTagRightTable();"
                               class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                    class="icon-plus-sign"></span>修改</a>
                            <a href="javascript:void(0)" onclick="delOne();"
                               class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                    class="icon-plus-sign"></span>删除</a>
                        </td>
                    </tr>
                </table> -->
            </form>
        </div>
    <div id="bottomDiv">
        <table id="docTagManageTable" width="100%" border="0" cellspacing="0" cellpadding="0">

        </table>
        <div id="aisccareprov_pager"></div>
    </div>
</div>
</body>

</html>
