<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>检查大类</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscordcategory/list.js"></script>
</head>
<body>
<div class="wrap100 quickrecharge">
	<!--导航start-->
	<div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>

        <ul class="breadcrumb" style="margin-top: 8px;">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="javascript:void(0)">检查项目</a>
            </li>
            <li class="active">检查大类</li>
        </ul>
    </div>
	<!--导航end-->
	<div class="q_r_list">
<input type="hidden" id="cityCode2" value="${cityCode}"/>
<input type="hidden" id="countyCode2" value="${countyCode}"/>
<input type="hidden" id="orgId2" value="${orgId}"/>
	<!--查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                	<!-- <tr>
                    	<th class="r" width="11%">城市 ：</th>
                        <td width="18%">
							<input type="hidden" id="selectType" name="selectType"/>
                            <select id="cityCode" name="cityCode" class="inputW160" onchange="getCountySelect()">
                                <option value="-1">请选择城市</option>
                            </select> 
                        </td> 
                        <th class="r" width="11%">区县 ：</th>
                        <td width="18%">
                            <select id="countyCode" name="countyCode" class="inputW160" onchange="getOrgSelects()">
                                <option value="-1">请选择区县</option>
                            </select> 
                        </td> 
                        <th class="r" width="11%">机构名称 ：</th>
                        <td width="18%">
                            <select id="orgId" name="orgId" class="inputW160">
                                <option value="-1">请选择机构</option>
                            </select> 
                        </td> 
                    </tr>
                    <tr align="left">
                    <th class="r" width="13%">大类代码：</th>
                        <td width="18%">
                            <input name="ordcategoryCode" id="ordcategoryCode" class="inputW160"/>
                        </td> 
                        <th class="r" width="13%">检查大类名称：</th>
                        <td width="18%">
                            <input name="ordcategoryDesc" id="ordcategoryDesc" class="inputW160"/>
                        </td>                         
                         <th width="13%" class="r">英文描述：</th>
                        <td width="18%">
                            <input name="ordcategoryEndesc" id="ordcategoryEndesc" class="inputW160"/>
                        </td>
                        <td></td> 
                    </tr> -->
                    <tr>
                    	<td>
                    		城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市&nbsp;：
                    		<input type="hidden" id="selectType" name="selectType"/>
                            <select id="cityCode" name="cityCode" class="inputW160" onchange="getCountySelect()">
                                <option value="-1">请选择城市</option>
                            </select>
                    	</td>
                    	<td>
                    		区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;县&nbsp;&nbsp;&nbsp;：
                    		<select id="countyCode" name="countyCode" class="inputW160" onchange="getOrgSelects()">
                                <option value="-1">请选择区县</option>
                            </select> 
                    	</td>
                    	<td>
                    		机构名称：
                    		<select id="orgId" name="orgId" class="inputW160">
                                <option value="-1">请选择机构</option>
                            </select>
                    	</td>
                    </tr>
                    <tr>
                    	<td>
                    		大类代码：
                    		<input name="ordcategoryCode" id="ordcategoryCode" class="inputW160"/>
                    	</td>
                    	<td>
                    		检查大类名称：
                    		<input name="ordcategoryDesc" id="ordcategoryDesc" class="inputW160"/>
                    	</td>
                    	<td>
                    		英文描述：
                    		<input name="ordcategoryEndesc" id="ordcategoryEndesc" class="inputW160"/>
                    	</td>
                    </tr>
                </table>
            </form>
           	<div>
               	<span>
               		<a href="javascript:void(0)" onclick="edit()" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-plus-sign"></span>新 增
                    </a>
               	</span>
               	<span style="float:right;">
               		<a href="javascript:void(0)" onclick="reloadGrid();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询
                    </a>
                    <a href="javascript:void(0)" onclick="reset()"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置
                    </a>
               	</span>
            </div>
            <!-- <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="r n_b_b">&nbsp;</th>
                    <td class="n_b_b">&nbsp;</td>
                    <th class="r n_b_b">&nbsp;</th>
                    <th colspan="3" class="r n_b_b">
                        <a href="javascript:void(0)" onclick="edit()" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-plus-sign"></span>新 增</a>
                        <a href="javascript:void(0)" onclick="reloadGrid();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                        <a href="javascript:void(0)" onclick="reset()"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
                    </th>
                </tr>
            </table> -->
        </div>
        <!--查询条件 end-->
		<div>
			<table id="aiscOrdCate" width="100%" border="0" cellspacing="0" cellpadding="0">
		   
			</table>
			<div id="aiscOrdCate_pager"></div>
		</div>
    </div>
</div>

</body>
</html>
