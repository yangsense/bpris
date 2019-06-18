 $(function () {
	initDict();
});
function initDict() {
	//城市加载
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/getCitys.ajax',
		dataType:'json',
		success:function(data){
			 var jsonCitys = eval(data.cityCode);
	         var source_cityCode = $("#cityCode");
	         source_cityCode.empty();
	         source_cityCode.append("<option value='-1'>请选择城市</option>");
	         for (var i = 0; i < jsonCitys.length; i++) {
	        	 source_cityCode.append("<option value='" + jsonCitys[i].itemno + "'>" + jsonCitys[i].itemname + "</option>");
	         }
	         source_cityCode.val($("#cityCodeValue").val()==""?"-1":$("#cityCodeValue").val());
	         source_cityCode.select2();
	         getCountySelect();
		}
	}); 
	
	//类型
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscSourceType.ajax",
        success: function (data) {
            var jsonAiscSourceType = eval(data.AiscSourceType);
            var source_TypeSelect = $("#patientTypecode");
            source_TypeSelect.empty();
            source_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscSourceType.length; i++) {
            	source_TypeSelect.append("<option value='" + jsonAiscSourceType[i].itemno + "'>" + jsonAiscSourceType[i].itemname + "</option>");
            }
            source_TypeSelect.select2();
        }
    });
}
function getCountySelect(){
	//区县加载
	var cityCode = $("#cityCode").val();
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/getCountys.ajax?cityCode='+cityCode,
		dataType:'json',
		success:function(data){
			 var jsonCountys = eval(data.countyMsg);
	         var source_countyCode = $("#countyCode");
	         source_countyCode.empty();
	         source_countyCode.append("<option value='-1'>请选择区县</option>");
	         for (var i = 0; i < jsonCountys.length; i++) {
	        	 source_countyCode.append("<option value='" + jsonCountys[i].itemno + "'>" + jsonCountys[i].itemname + "</option>");
	         }
	         source_countyCode.val($("#countyCodeValue").val()==""?"-1":$("#countyCodeValue").val());
	         source_countyCode.select2();
	         getOrgSelects();
		}
	}); 
}
function getOrgSelects(){
	var cityCode = $("#cityCode").val();
	var countyCode = $("#countyCode").val();
	//机构列表加载
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/getOrgSelects.ajax?cityCode='+cityCode+"&countyCode="+countyCode,
		dataType:'json',
		success:function(data){
			var jsonOrgMsg = eval(data.orgMsg);
	        var source_orgMsg = $("#orgId");
	        source_orgMsg.empty();
	        source_orgMsg.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < jsonOrgMsg.length; i++) {
	        	source_orgMsg.append("<option value='" + jsonOrgMsg[i].orgid + "'>" + jsonOrgMsg[i].orgname + "</option>");
	        }
	        source_orgMsg.val($("#orgIdValue").val()==""?"-1":$("#orgIdValue").val());
	        source_orgMsg.select2();
	        selectList();
		}
	}); 
}

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
        url: GLOBAL.WEBROOT +"/basecode/getAiscHisDatasourceList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['机构名称','类型','数据源地址','用户名','密码','操作'],
        colModel: [
        	{name: 'orgName', index: 'orgName',width:80, sortable: false},
        	{name: 'patientTypecode', index: 'patientTypecode',width:80, sortable: false,
        		formatter: function (param1, param2, recode) {
                	if(param1==undefined){
                		return '公用';
                	}else{
                		return param1;
                	}
                }
        	},
            {name: 'sourceUrl', index: 'sourceUrl', sortable: false},
            {name: 'sourceUser', index: 'sourceUser',  sortable: false},
            {name: 'sourcePassword', index: 'sourcePassword', sortable: false},
            {name: 'sourceId', index: "sourceId", width:60, sortable: false
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
		title="查看数据源配置信息";
		url="/basecode/aiscHisDatasourceDetail.html?id="+id+"&type="+type;
		//所有控件不可用
	}else if(type=="update"){
		title = "修改数据源配置信息";
		url="/basecode/aiscHisDatasourceDetail.html?id="+id+"&type="+type;
	}else{
		title = "新增数据源配置信息";
		url="/basecode/aiscHisDatasourceDetail.html?id="+id+"&type="+type;
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
        url: GLOBAL.WEBROOT +"/basecode/deleteAiscHisDatasource.ajax?id="+param,
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
//重置
function reset(){
	$("#cityCode").select2("val","-1");
	$("#countyCode").select2("val","-1");
	$("#orgId").select2("val","-1");
	$("#patientTypecode").select2("val","-1");
}
