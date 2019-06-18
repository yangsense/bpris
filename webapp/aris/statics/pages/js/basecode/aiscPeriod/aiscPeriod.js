  
$(function () {
	selectList();
});


function reloadGrid(){
    var data = $("#searchForm").serializeObject()
	$("#aiscreportlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#aiscreportlist";
    var pager_selector = "#aiscreport_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/basecode/getAiscPeriodList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['时段ID','时段名称','开始时间','结束时间','操作'],
        colModel: [
        	{name: 'periodId', index: 'periodId',width:80, sortable: false},
            {name: 'periodDesc', index: 'periodDesc', sortable: false},
            {name: 'periodStarttime', index: 'periodStarttime',  sortable: false},
            {name: 'periodEndtime', index: 'periodEndtime', sortable: false},
            {name: 'id', index: "id", width:60, sortable: false
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
		title="查看时段信息";
		url="/basecode/aiscPeriodDetail.html?id="+id+"&type="+type;
		//所有控件不可用
	}else if(type=="update"){
		title = "修改时段信息";
		url="/basecode/aiscPeriodDetail.html?id="+id+"&type="+type;
	}else{
		title = "新增时段信息";
		url="/basecode/aiscPeriodDetail.html?id="+id+"&type="+type;
	}
    var index = layer.open({
        type: 2,
        title:title,
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
    
//    layer.full(index);
}
function deleData(param){
	layer.confirm('是否确定删除该信息？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
        type: "GET",
        async: true,
        url: GLOBAL.WEBROOT +"/basecode/deleteAiscPeriod.ajax?id="+param,
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
