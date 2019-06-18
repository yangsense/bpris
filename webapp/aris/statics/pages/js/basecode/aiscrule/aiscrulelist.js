  
$(function () {
	initDict();
});

//initial query dict
function initDict() {
	
    //服务器类型
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscRuleType.ajax",
        success: function (data) {
            var jsonAiscRuleType = eval(data.AiscRuleType);
            var Server_TypeSelect = $("#ruleType");
            Server_TypeSelect.empty();
            Server_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscRuleType.length; i++) {
                Server_TypeSelect.append("<option value='" + jsonAiscRuleType[i].itemno + "'>" + jsonAiscRuleType[i].itemname + "</option>");
            }
            Server_TypeSelect.select2();
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
	        orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	        orgSelect.val(parent.$("#currentOrgId").val()==""?"-1":parent.$("#currentOrgId").val());
	        orgSelect.select2();  
	        	//查询
			selectList();
		}
	}); 
    
    loadLoc();
}

function loadLoc(){
	//科室名称
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscLocName.ajax?orgId="+$("#orgId").val(),
        success: function (data) {
            var jsonaiscLocName = eval(data.AiscLocName);
            var loc_nameSelect = $("#locId");
            loc_nameSelect.empty();
            loc_nameSelect.append("<option value='-1'>--请选择--</option>");
            for (var i = 0; i < jsonaiscLocName.length; i++) {
                loc_nameSelect.append("<option value='" + jsonaiscLocName[i].locid + "'>" + jsonaiscLocName[i].locdesc + "</option>");
            }
            loc_nameSelect.select2();
        }
    });
}


function reloadGrid(){
    var data = $("#searchForm").serializeObject();
	$("#aiscrulelist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#aiscrulelist";
    var pager_selector = "#aiscrule_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/basecode/queryAiscRuleList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['规则ID','机构名称','科室名称','生成方式','前缀','开始序号','规则字段','长度','操作'],
        colModel: [
        	{name: 'ruleId', index: 'ruleId',width:60, sortable: false},
            {name: 'orgName', index: 'orgName',width:100, sortable: false},
            {name: 'locDesc', index: 'locDesc',width:80, sortable: false,formatter: function (param1, param2, recode) {
        		if(param1=='-1' || param1==null || param1=='undefined' || param1==''){
            		return '所有科室';
            	} else{
            		return param1;
            	}
        		}
            },
            {name: 'ruleTypeDesc', index: "ruleTypeDesc",width:80, sortable: false},
            {name: 'rulePrix', index: 'rulePrix', width:60,sortable: false},
            {name: 'ruleStartindex', index: 'ruleStartindex',width:60, sortable: false},
            {name: 'ruleField', index: 'ruleField',width:100, sortable: false},
            {name: 'ruleLen', index: 'ruleLen',width:60, sortable: false},
            {name: 'ruleId', index: "ruleId", width:60, sortable: false
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
		title="查看规则生成器信息";
		url="/basecode/aiscRuleDetail.html?id="+id+"&type="+type;
		//所有控件不可用
	}else if(type=="update"){
		title = "修改规则生成器信息";
		url="/basecode/aiscRuleDetail.html?id="+id+"&type="+type;
	}else{
		title = "新增规则生成器信息";
		url="/basecode/aiscRuleDetail.html?id="+id+"&type="+type;
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
        url: GLOBAL.WEBROOT +"/basecode/deleteAisRule.ajax?id="+param,
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
