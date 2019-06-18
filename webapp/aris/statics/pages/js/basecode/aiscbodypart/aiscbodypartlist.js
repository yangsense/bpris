var orgId = ""; 
$(function () {
	orgId = $("#orgId" , parent.document).val(); 	
	//查询
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
    $("#bodypartType").select2();
    
   //部位大类加载
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/getBodyparType.ajax?orgId='+parent.parent.$("#currentOrgId").val(),
		dataType:'json',
		success:function(data){
			var bodyparType = eval(data.BodyparType);
	        var pidSelect = $("#bodypartPid");
	        pidSelect.empty(); 
	        pidSelect.append("<option value='-1'>--请选择--</option>");
	        for (var i = 0; i < bodyparType.length; i++) {
	        	pidSelect.append("<option value='" + bodyparType[i].bodypartcode + "'>" + bodyparType[i].bodypartdesc + "</option>");
	        }
	        pidSelect.select2();  
		}
	}); 
});

function reloadGrid(){
    var data = $("#searchForm").serializeObject();
	$("#aiscbodypartlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#aiscbodypartlist";
    var pager_selector = "#aiscbodypart_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/basecode/queryAiscBodyPartList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['部位代码','部位名称(检查项目)','部位类型','所属上级部位','英文描述','部位排序','所属机构','操作'],
        colModel: [
        	{name: 'bodypartCode', index: 'bodypartCode',width:60, sortable: false},
            {name: 'bodypartDesc', index: 'bodypartDesc', width:60, sortable: false},
            {name: 'bodypartTypedesc', index: 'bodypartTypedesc', width:60, sortable: false
            },
            {name: 'lastBodypart', index: 'lastBodypart', width:60, sortable: false},
            {name: 'bodypartEndesc', index: 'bodypartEndesc', width:60, sortable: false},
            {name: 'bodypartOrder', index: 'bodypartOrder', sortable: false,width:60, formatter: function (param1, param2, recode) {
            	if(param1==0){
            		return "";
            	}else{
            		return param1;
            	}
            }},
            {name: 'orgName', index: 'orgName', width:60, sortable: false},
            {name: 'bodypartCode', index: "bodypartCode", width:60, sortable: false
            	,formatter: function (param1, param2, recode) {
                    return '<div class="action-buttons">' +
                        '<a class="blue" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'query\')" title="查看"><i class="icon-search bigger-120"></i></a>' +
                        '<a class="red" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'update\')" title="修改"><i class="icon-edit bigger-120"></i></a>' +
                        '<a class="green" href="javascript:void(0)" onclick="deleData(\'' + param1 + '\',\'delete\')" title="删除"><i class="icon-remove bigger-120"></i></a>' +
                        "</div>";
                }
            }
        ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: false,
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

//操作
function view(id,type) {
	var url ;
	var title ;
	if(type=="query"){
		title="查看检查部位信息";
		url="/basecode/aiscBodyPartDetail.html?id="+id+"&type="+type;
		//所有控件不可用
	}else if(type=="update"){
		title = "修改检查部位信息";
		url="/basecode/aiscBodyPartDetail.html?id="+id+"&type="+type;
	}else{
		title = "新增检查部位信息";
		url="/basecode/aiscBodyPartDetail.html?id="+id+"&type="+type;
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
        url: GLOBAL.WEBROOT +"/basecode/deleteAisBodyPart.ajax?id="+param,
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

function partimport(){
	layer.confirm('是否确定导入公共部位？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
			type : "POST",
			async : true,
			url : GLOBAL.WEBROOT + '/basecode/partimport.ajax',
			success : function(data) {
				if (data.ERRCODE == "0") {
					info('温馨提示','导入成功');
					reloadGrid();
				}else {
					info('导入失败',"请联系管理员");
				}
			},
			beforeSend:function(){
				$("#partImport").attr("disabled", true);
				$("#partImport").html("<span class=\"icon-arrow-down\"></span>正在导入...");
			},
			complete : function(responseText, statusText) {
				$("#partImport").attr("disabled", false);
				$("#partImport").html("<span class=\"icon-arrow-down\"></span>导 入");
			}
		});
	}, function(){
	});
}
