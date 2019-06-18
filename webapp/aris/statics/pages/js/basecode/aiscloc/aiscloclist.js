  
$(function () {
	initDict();
	
});
 
//initial query dict
function initDict() {
	
    //科室类型
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscLocType.ajax",
        success: function (data) {
            var jsonAiscLocType = eval(data.AiscLocType);
            var loc_TypeSelect = $("#locType");
            loc_TypeSelect.empty();
            loc_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscLocType.length; i++) {
                loc_TypeSelect.append("<option value='" + jsonAiscLocType[i].itemno + "'>" + jsonAiscLocType[i].itemname + "</option>");
            }
            loc_TypeSelect.select2();
        }
    });
    //机构列表加载
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/bpris/getOrgs.ajax?operatorCode='+parent.user.operatorCode+"&sysType=25",
		dataType:'json',
		success:function(data){
			var sysOrg = eval(data);
	        var orgSelect = $("#orgId");
	        orgSelect.empty(); 
	        //orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	        orgSelect.val(parent.$("#currentOrgId").val()==""?"-1":parent.$("#currentOrgId").val());
	        orgSelect.select2();  
	        	//查询
			selectList();
		}
	}); 
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject()
	$("#aiscloclist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#aiscloclist";
    var pager_selector = "#aiscloc_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/basecode/queryAiscLocList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['科室ID','科室代码','科室拼音检索','科室名称','科室英文描述名称','科室电话','科室地址','科室类型','存储服务器','hzlocId','orgId','所属机构','操作'],
        colModel: [
            {name: 'locId', index: 'locId', width:60,sortable: false},
            {name: 'locCode', index: 'locCode',width:70, sortable: false},
            {name: 'locIndex', index: 'locIndex',width:90,  sortable: false},
            {name: 'locDesc', index: 'locDesc',width:70, sortable: false},
            {name: 'locEndesc', index: 'locEndesc',width:100, sortable: false},
            {name: 'locPhone', index: 'locPhone',width:80,  sortable: false},
            {name: 'locAddress', index: 'locAddress',width:60, sortable: false},
            {name: 'locTypeDesc', index: "locTypeDesc",width:60, sortable: false},
            {name: 'serverName', index: "serverName",width:70, sortable: false},
            {name: 'hzlocId', index: "hzlocId",width:60, sortable: false,hidden:true},
            {name: 'orgId', index: "orgId",width:60, sortable: false,hidden:true},
            {name: 'orgName', index: "orgName",width:60, sortable: false},
            {name: 'locId', index: "locId",width:50,  sortable: false
            	,formatter: function (param1, param2, recode) {
                    if(resources.containRes('deleteBtn')) {
                        return '<div class="action-buttons">' +
                            '<a class="blue" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'query\')" title="查看"><i class="icon-search bigger-120"></i></a>' +
                            '<a class="red" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'update\')" title="修改"><i class="icon-edit bigger-120"></i></a>' +
                            '<a class="green" href="javascript:void(0)" onclick="deleData(\'' + param1 + '\',\'delete\')" title="删除"><i class="icon-remove bigger-120"></i></a>' +
                            "</div>";
                    }else{
                        return '<div class="action-buttons">' +
                            '<a class="blue" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'query\')" title="查看"><i class="icon-search bigger-120"></i></a>' +
                            '<a class="red" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'update\')" title="修改"><i class="icon-edit bigger-120"></i></a>' +
                            "</div>";
					}
                }
            }
        ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: true,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}

//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}

function huizhen(){
	var gr = $("#aiscloclist").jqGrid("getGridParam","selarrrow");
	if (gr.length == 0) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	if (gr.length > 1) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
//	if(ch_rowid == null || ch_rowid == ""){
//		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
//		return;
//	}
	var rowData = $("#aiscloclist").jqGrid('getRowData',gr[0]);
	var index = layer.open({
        type: 2,
        title:"会诊机构设置",
        area: ['800px', '500px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/basecode/consultationOrgSet.html?locId="+rowData.hzlocId+"&orgId="+rowData.orgId,
    });
	layer.full(index);
}

//操作
function view(id,type) {
	var url ;
	var title ;
	if(type=="query"){
		title="查看科室信息";
		url="/basecode/aiscLocDetail.html?id="+id+"&type="+type;
		//所有控件不可用
	}else if(type=="update"){
		title = "修改科室信息";
		url="/basecode/aiscLocDetail.html?id="+id+"&type="+type;
	}else{
		title = "新增科室信息";
		url="/basecode/aiscLocDetail.html?id="+id+"&type="+type;
	}
    var index = layer.open({
        type: 2,
        title:title,
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
    layer.full(index);
}
function deleData(param){
	layer.confirm('是否确定删除该信息？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
        type: "GET",
        async: true,
        url: GLOBAL.WEBROOT +"/basecode/deleteAisLoc.ajax?id="+param,
        dataType: 'json',
        success: function (data) {
			if (data.ERRCODE == "0") {
                layer.msg('删除成功', {icon: 1});
                reloadGrid();
            }
            else {
                layer.msg('删除失败', {icon: 1});
            }
        }
   		});
	}, function(){
	    //layer.msg('奇葩么么哒', {icon:2});
	});
}
function importLoc(){
	layer.confirm('是否确定导入科室？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			type : "POST",
			async : true,
			url : GLOBAL.WEBROOT + '/basecode/importLoc.ajax',
			success : function(data) {
				if (data.ERRCODE == "0") {
					info('温馨提示','导入成功');
					reloadGrid();
				}else if(data.ERRCODE == "-2"){
					info('导入失败',"没有查询到机构对应的his科室信息");
				}else {
					info('导入失败',"请检查数据源配置信息或重新登录");
				}
			},
			beforeSend:function(){
				$("#aImport").attr("disabled", true);
				$("#aImport").html("<span class=\"icon-arrow-down\"></span>正在导入...");
			},
			complete : function(responseText, statusText) {
				$("#aImport").attr("disabled", false);
				$("#aImport").html("<span class=\"icon-arrow-down\"></span>导 入");
			}
		});
	}, function(){
	});
}
function exportLoc(){
	var orgId = $("#orgId" , parent.document).val()
	if(orgId==null || orgId==""){
		alert("请选择机构");
		return;
	}
	var data = $("#searchForm").serialize();
	$.ajax({
		type : "POST",
		async : true,
		url : GLOBAL.WEBROOT + '/basecode/exportCheck.ajax',
		success : function(data) {
			if (data.ERRCODE == "0") {
				var url=GLOBAL.WEBROOT + '/basecode/exportLoc.ajax?'+data;
			    window.open(url);
			} else {
				info('导出失败',"请检查数据源配置信息或重新登录");
			}
		}
	});
	
    
}

