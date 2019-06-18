  
$(function () {
	//查询
	selectList();
	
	initDict();
});

//initial query dict
function initDict() {
	
    //介质类型
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscMediumType.ajax",
        success: function (data) {
            var jsonAiscMediumType = eval(data.AiscMediumType);
            var Medium_TypeSelect = $("#mediumType");
            Medium_TypeSelect.empty();
            Medium_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscMediumType.length; i++) {
                Medium_TypeSelect.append("<option value='" + jsonAiscMediumType[i].itemno + "'>" + jsonAiscMediumType[i].itemname + "</option>");
            }
            Medium_TypeSelect.select2();
        }
    });
    
    //服务器加载
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscServer.ajax",
        success: function (data) {
            var jsonAiscServer = eval(data.AiscServer);
            var serverSelect = $("#serverId");
            serverSelect.empty();
            serverSelect.append("<option value='-1'>请选择服务器</option>");
            for (var i = 0; i < jsonAiscServer.length; i++) {
                serverSelect.append("<option value='" + jsonAiscServer[i].serverid + "'>" + jsonAiscServer[i].servername + "</option>");
            }
            serverSelect.select2();  
        }
    });
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject();
	$("#aiscmediumlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#aiscmediumlist";
    var pager_selector = "#aiscmedium_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/basecode/queryAiscMediumInfoList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['介质ID','介质名称','服务器名称','介质路径','初始大小','剩余大小','介质类型','是否已满','是否在线','是否可用','操作'],
        colModel: [
        	{name: 'mediumId', index: 'mediumId',width:60, sortable: false},
            {name: 'mediumName', index: 'mediumName',width:80, sortable: false},
            {name: 'serverName', index: 'serverName',width:80, sortable: false},
            {name: 'mediumPath', index: 'mediumPath',width:80,  sortable: false},
            {name: 'mediumSize', index: "mediumSize",width:80, sortable: false},
            {name: 'mediumReminesize', index: "mediumReminesize",width:80, sortable: false},
            {name: 'mediumTypeDesc', index: "mediumTypeDesc",width:80, sortable: false},
            {name: 'mediumIsfull', index: "mediumIsfull",width:80, sortable: false,formatter : function(value) {
					if ('1' == value){
						return "已满";
					}else if ('0' == value){
						return "未满";
					}						
				}},
            {name: 'mediumIsonline', index: "mediumIsonline",width:80, sortable: false,formatter : function(value) {
					if ('1' == value){
						return "在线";
					}else if ('0' == value){
						return "离线";
					}						
				}},
            {name: 'mediumEnabled', index: "mediumEnabled",width:80, sortable: false,formatter : function(value) {
					if ('1' == value){
						return "是";
					}else if ('0' == value){
						return "否";
					}						
				}
            },
            {name: 'mediumId', index: "mediumId", width:60, sortable: false
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
		title="查看介质信息";
		url="/basecode/aiscMediumInfoDetail.html?id="+id+"&type="+type;
		//所有控件不可用
	}else if(type=="update"){
		title = "修改介质信息";
		url="/basecode/aiscMediumInfoDetail.html?id="+id+"&type="+type;
	}else{
		title = "新增介质信息";
		url="/basecode/aiscMediumInfoDetail.html?id="+id+"&type="+type;
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
        url: GLOBAL.WEBROOT +"/basecode/deleteAisMediumInfo.ajax?id="+param,
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
