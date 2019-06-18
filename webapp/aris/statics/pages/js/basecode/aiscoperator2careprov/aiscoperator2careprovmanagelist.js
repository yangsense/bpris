  
$(function () {
	//查询
	selectList();
});

function reloadGrid(){
    var data = $("#searchForm").serializeObject();
	$("#aiscoperator2careprovlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#aiscoperator2careprovlist";
    var pager_selector = "#aiscoperator2careprov_pager";
    var data = $("#searchForm").serializeObject();
    data.orgId = $("#orgId" , parent.document).val();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/basecode/queryAiscOperator2CareProvList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['操作员ID','操作员编码','医生ID','医生姓名','操作'],
        colModel: [
            {name: 'operatorId', index: 'operatorId',width:80, sortable: false},
            {name: 'operatorCode', index: 'operatorCode',width:80, sortable: false},
            {name: 'careprovId', index: "careprovId",width:80, sortable: false},
            {name: 'careprovName', index: "careprovName", width:80,sortable: false},
            {name: 'user2careprovId', index: "user2careprovId",width:60,  sortable: false
            	,formatter: function (param1, param2, recode) {
                    return '<div class="action-buttons">' +
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
		title="查看操作员与医护人员关联信息";
		url="/basecode/aiscOperator2CareProvdetail.html?id="+id+"&type="+type;
		//所有控件不可用
	}else if(type=="update"){
		title = "新增修改操作员与医护人员关联信息";
		url="/basecode/aiscOperator2CareProvdetail.html?id="+id+"&type="+type;
	}else{
		title = "新增修改操作员与医护人员关联信息";
		url="/basecode/aiscOperator2CareProvdetail.html?id="+id+"&type="+type;
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
        url: GLOBAL.WEBROOT +"/basecode/deleteAiscOperator2CareProv.ajax?id="+param,
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
