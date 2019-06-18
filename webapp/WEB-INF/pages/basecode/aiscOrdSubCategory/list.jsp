<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>检查子类</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscordsubcategory/list.js"></script>
</head>
<body>
<input type="hidden" id="cityCodeValue" value="${cityCode}"/>
<input type="hidden" id="countyCodeValue" value="${countyCode}"/>
<input type="hidden" id="orgIdValue" value="${orgId}"/>
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
            <li class="active">检查子类</li>
        </ul>
    </div>
	<!--导航end-->
	<div class="q_r_list">
	
	<!--查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                	<!-- <tr>
                    	<th class="r" width="11%">城市 ：</th>
                        <td width="18%">
                        	<input type="hidden" id="cityCode2" name="cityCode2"/>
							<input type="hidden" id="countyCode2" name="countyCode2"/>
							<input type="hidden" id="orgId2" name="orgId2"/>
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
                            <select id="orgId" name="orgId" class="inputW160" onchange="getBigSort()">
                                <option value="-1">请选择机构</option>
                            </select> 
                        </td> 
                    </tr>
                    <tr>
                                                
                        <th width="18%" class="r">检查大类：</th>
                        <td width="18%">
                            <select id="ordcategoryId" name="ordcategoryId"  class="inputW160" onchange="initSubCate()">
                               <option value="-1">请选择大类</option>
                            </select>
                        </td>
                        <th class="r" width="11%">检查子类 ：</th>
                        <td width="18%">
                            <select id="ordsubcategoryId" name="ordsubcategoryId" class="inputW160">
                                <option value="-1">请选择子类</option>
                            </select> 
                        </td> 
                         <th class="r" width="13%">检查子类名称：</th>
                        <td width="18%">
                           <input name="ordsubcategoryDesc" id="ordsubcategoryDesc" class="inputW160"/>                 
                           </td> 
                    </tr>
                    <tr>
                    <th class="r" width="13%">子类代码：</th>
                        <td width="18%" colspan="6">
                            <input name="ordsubcategoryCode" id="ordsubcategoryCode" class="inputW160"/>
                        </td> 
                    </tr> -->
                    <tr>
                    	<td>
                    		城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市&nbsp;&nbsp;：
                    		<input type="hidden" id="cityCode2" name="cityCode2"/>
							<input type="hidden" id="countyCode2" name="countyCode2"/>
							<input type="hidden" id="orgId2" name="orgId2"/>
                            <select id="cityCode" name="cityCode" class="inputW160" onchange="getCountySelect()">
                                <option value="-1">请选择城市</option>
                            </select>
                    	</td>
                    	<td>
                    		区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;县&nbsp;&nbsp;：
                    		<select id="countyCode" name="countyCode" class="inputW160" onchange="getOrgSelects()">
                                <option value="-1">请选择区县</option>
                            </select>
                    	</td>
                    	<td>
                    		机&nbsp;构&nbsp;名&nbsp;称&nbsp;&nbsp;&nbsp;&nbsp;：
                    		<select id="orgId" name="orgId" class="inputW160" onchange="getBigSort()">
                                <option value="-1">请选择机构</option>
                            </select>
                    	</td>
                    </tr>
                    <tr>
                    	<td>
                    		检查大类：
                    		<select id="ordcategoryId" name="ordcategoryId"  class="inputW160" onchange="initSubCate()">
                               <option value="-1">请选择大类</option>
                            </select>
                    	</td>
                    	<td>
                    		检查子类：
                    		<select id="ordsubcategoryId" name="ordsubcategoryId" class="inputW160">
                                <option value="-1">请选择子类</option>
                            </select>
                    	</td>
                    	<td>
                    		检查子类名称：
                    		<input name="ordsubcategoryDesc" id="ordsubcategoryDesc" class="inputW160"/>  
                    	</td>
                    </tr>
                    <tr>
                    	<td>
                    		子类代码：
                    		<input name="ordsubcategoryCode" id="ordsubcategoryCode" class="inputW160"/>
                    	</td>
                    </tr>
                </table>
            </form>
           	<div>
              	<span>
					<a href="javascript:void(0)" onclick="edit()" id="fbox_grid-table_search"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-plus-sign"></span>新 增</a>
              	</span>
              	
              	<span style="float:right;">
					<a href="javascript:void(0)" onclick="reloadGrid();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                    <a href="javascript:void(0)" onclick="reset()"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
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
			<table id="aiscOrdSubCate" width="100%" border="0" cellspacing="0" cellpadding="0">
		   
			</table>
			<div id="aiscOrdSubCate_pager"></div>
		</div>
    </div>
</div>

</body>
</html>
