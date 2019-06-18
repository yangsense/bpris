function reloadGrid() {
    var data = $("#searchForm").serializeObject();
    $("#aiscOrdSubCate").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}
$(function(){
	initCate();
	$("#cityCode").attr("readonly","true");
	$("#countyCode").attr("readonly","true");
	$("#orgId").attr("readonly","true");
})

function initCate() {	
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
	        source_orgMsg.val(parent.$("#currentOrgId").val()==""?"-1":parent.$("#currentOrgId").val());
	        source_orgMsg.select2();
	        initnewCate();
		}
	}); 
}

function initnewCate() {
    //检查大类下拉
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdCate/getAiscOrdCategory.ajax?orgId="+$("#orgId").val() ,
        success: function (data) {
            var jsonOrdCategory = eval(data.AiscOrdCategoryName);
            var ordCategorySelect = $("#ordcategoryId");
            ordCategorySelect.empty();
            ordCategorySelect.append("<option value='-1'>请选择大类</option>");
            for (var i = 0; i < jsonOrdCategory.length; i++) {
            	ordCategorySelect.append("<option value='" + jsonOrdCategory[i].ordcategoryid + "'>" + jsonOrdCategory[i].ordcategorydesc + "</option>");
            }
//            ordCategorySelect.val(ordcategoryid==""?"-1":ordcategoryid);
            ordCategorySelect.select2();
            initSubCate();
        }
    });
    
}

function initSubCate() {
    //检查子类下拉
    var ordcategoryId = $("#ordcategoryId").val() ;
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdSubCate/getAiscOrdSubCategory.ajax?ordcategoryId="+ordcategoryId+"&orgId="+$("#orgId").val()  ,
        success: function (data) {
            var jsonOrdSubCategory = eval(data.AiscOrdSubCategory);
            var ordSubCategorySelect = $("#ordsubcategoryId");
            ordSubCategorySelect.empty();
            ordSubCategorySelect.append("<option value='-1'>请选择子类</option>");
            for (var i = 0; i < jsonOrdSubCategory.length; i++) {
            	ordSubCategorySelect.append("<option value='" + jsonOrdSubCategory[i].ordsubcategory + "'>" + jsonOrdSubCategory[i].ordsubcategorydesc + "</option>");
            }
            ordSubCategorySelect.select2();
            selectList();
        }
    });
}

//查询列表
function selectList() {
    var grid_selector = "#aiscOrdSubCate";
    var pager_selector = "#aiscOrdSubCate_pager";
    var data = $("#searchForm").serializeObject();
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/aiscOrdSubCate/getAiscOrdSubCateList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['检查子类ID','检查大类名称', '检查子类名称', '英文描述', '注意事项','子类代码', '操作'],
        colModel: [
            {name: 'ordsubcategoryId', index: 'ordsubcategoryId',width:80, sortable: false},
            {name: 'ordcategoryDesc', index: 'ordcategoryDesc', sortable: false},
            {
                name: 'ordsubcategoryDesc', index: 'ordsubcategoryDesc', sortable: false,align:'center'
            },
            {
                name: 'ordsubcategoryEndesc', index: 'ordsubcategoryEndesc', sortable: false
            },
            {name: 'ordsubcategoryNote', index: 'ordsubcategoryNote', sortable: false},
            {name: 'ordsubcategoryCode', index: 'ordsubcategoryCode',sortable: false},
            {
                    name: 'ordsubcategoryId', index: "ordsubcategoryId",width:60, sortable: false
                    , formatter: function (cellvalue, options, rowObject) {
                    return '<div class="action-buttons">' +
                        '<a class="blue" href="javascript:void(0)" onclick="edit(\'' + cellvalue + '\',\'view\')"  title="查看"><i class="icon-search bigger-120"></i></a>' +
                        '<a class="red" href="javascript:void(0)" onclick="edit(\'' + cellvalue + '\',\'update\')"  title="修改"><i class="icon-edit bigger-120"></i></a>' +
                        '<a class="green" href="javascript:void(0)" onclick="deleteAiscOrdSubCate(\'' + cellvalue + '\')" title="删除"><i class="icon-remove bigger-120"></i></a>'
                    "</div>";
                }
            }
        ],
        viewrecords: false,
        rowNum: 10,
        rowList: [10, 15, 20, 30],
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
        autowidth: true,
        shrinkToFit:true
    });
}

//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}


function edit(id,opType) {
    var url;
    if(id){
        url = GLOBAL.WEBROOT + "/aiscOrdSubCate/page/edit?id="+id+"&opType="+opType;
    }else{
        url = GLOBAL.WEBROOT + "/aiscOrdSubCate/page/edit";
    }
    var index = layer.open({
        type: 2,
        title:"检查子类维护",
        area: ['800px', '400px'],
        fix: false, //不固定
        maxmin: true,
        content: url
    });
    layer.full(index);
}

function deleteAiscOrdSubCate(id) {
    layerConfirm('是否删除当前选中记录',function() {
        var data = "id=" + id;
        $.ajax({
            type: "GET",
            async: true,
            url: GLOBAL.WEBROOT + "/aiscOrdSubCate/deleteAiscOrdSubCate",
            dataType: 'json',
            data: data,
            success: function (data) {
                if (data.ERRCODE == "0") {
                    infoSuccess('温馨提示', '删除成功');
                    reloadGrid();
                }
                else {
                    info('温馨提示', data.ERRINFO);
                }
            }
        });
    });
}
//重置
function reset(){
	$("#ordcategoryId").select2("val","-1");
	$("#ordsubcategoryId").select2("val","-1");
	$("#ordsubcategoryDesc").val("");
	$("#ordsubcategoryCode").val("");
}
