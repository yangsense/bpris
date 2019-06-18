  
$(function () {
	//查询
	selectList();
	
	initDict();
});

//initial query dict
function initDict() {
	
    //服务器类型
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscServerType.ajax",
        success: function (data) {
            var jsonAiscServerType = eval(data.AiscServerType);
            var Server_TypeSelect = $("#serverType");
            Server_TypeSelect.empty();
            Server_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscServerType.length; i++) {
                Server_TypeSelect.append("<option value='" + jsonAiscServerType[i].itemno + "'>" + jsonAiscServerType[i].itemname + "</option>");
            }
            Server_TypeSelect.select2();
        }
    });
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject();
	$("#aiscserverlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#aiscserverlist";
    var pager_selector = "#aiscserver_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/basecode/queryAiscServerInfoList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['服务器ID','服务器名称','服务器IP','服务器端口','服务器类型','登陆用户','登陆密码','是否可用','操作'],
        colModel: [
        	{name: 'serverId', index: 'serverId',width:80, sortable: false},
            {name: 'serverName', index: 'serverName',width:80, sortable: false},
            {name: 'serverIp', index: 'serverIp',width:80, sortable: false},
            {name: 'serverPort', index: 'serverPort',width:80,  sortable: false},
            {name: 'serverTypeDesc', index: "serverTypeDesc",width:80, sortable: false},
            {name: 'serverUser', index: "serverUser",width:80, sortable: false},
            {name: 'serverPassword', index: "serverPassword", width:80,sortable: false},
            {name: 'serverEnabled', index: "serverEnabled",width:80, sortable: false,formatter : function(value) {
					if ('1' == value){
						return "是";
					}else if ('0' == value){
						return "否";
					}						
				}
            },
            {name: 'serverId', index: "serverId", width:60, sortable: false
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
		title="查看服务器信息";
		url="/basecode/aiscServerInfoDetail.html?id="+id+"&type="+type;
		//所有控件不可用
	}else if(type=="update"){
		title = "修改服务器信息";
		url="/basecode/aiscServerInfoDetail.html?id="+id+"&type="+type;
	}else{
		title = "新增服务器信息";
		url="/basecode/aiscServerInfoDetail.html?id="+id+"&type="+type;
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
        url: GLOBAL.WEBROOT +"/basecode/deleteAisServerInfo.ajax?id="+param,
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
