  
$(function () {

	$("select").select2();
	
	//查询
	
	
	initDict();
	 initCate() ; //初始化检查大类
	initSubCate() ;  //初始化检查子类
});

function initDict() {

//  //机构列表加载
//    $.ajax({
//        type: "POST",
//        url: GLOBAL.WEBROOT +"/basecode/getOrgListByOper.ajax",
//        success: function (data) {
//            var jsonData = eval(data);
//            var orgIdSelect = $("#orgId");
//            orgIdSelect.empty();
//            orgIdSelect.append("<option value='-1'>请选择机构</option>");
//            for (var i = 0; i < jsonData.length; i++) {
//                orgIdSelect.append("<option value='" + jsonData[i].orgId + "'>" + jsonData[i].orgName + "</option>");
//            }
//            orgIdSelect.select2();  
//        }
//    });
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

//    //机构列表加载
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
	        selectList();	
		}
	}); 
	
}
function initCate() {
	
    //检查大类下拉
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdCate/getAiscOrdCategory.ajax?orgId="+$("#orgId").val(),
        success: function (data) {
            var jsonOrdCategory = eval(data.AiscOrdCategoryName);
            var ordCategorySelect = $("#ordcategoryId");
            ordCategorySelect.empty();
            ordCategorySelect.append("<option value='-1'>请选择大类</option>");
            for (var i = 0; i < jsonOrdCategory.length; i++) {
            	ordCategorySelect.append("<option value='" + jsonOrdCategory[i].ordcategoryid + "'>" + jsonOrdCategory[i].ordcategorydesc + "</option>");
            }
            ordCategorySelect.select2();
        }
    });
}
function initSubCate() {
    //检查子类下拉
    var ordcategoryId = $("#ordcategoryId").val() ;
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdSubCate/getAiscOrdSubCategory.ajax?ordcategoryId="+ordcategoryId+"&orgId="+$("#orgId").val() ,
        success: function (data) {
            var jsonOrdSubCategory = eval(data.AiscOrdSubCategory);
            var ordSubCategorySelect = $("#ordsubcategoryId");
            ordSubCategorySelect.empty();
            ordSubCategorySelect.append("<option value='-1'>请选择子类</option>");
            for (var i = 0; i < jsonOrdSubCategory.length; i++) {
            	ordSubCategorySelect.append("<option value='" + jsonOrdSubCategory[i].ordsubcategoryid + "'>" + jsonOrdSubCategory[i].ordsubcategorydesc + "</option>");
            }
            ordSubCategorySelect.select2();
        }
    });
}
function callFuncForOrgNames(zTree) {
    $('#orgId').val(zTree.orgId);
    $('#orgName').val(zTree.orgName);
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject();
	$("#aiscbodypartitemlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#aiscbodypartitemlist";
    var pager_selector = "#aiscbodypartitem_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/basecode/queryAiscBodyPartItemList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['检查项目','部位名称','部位大类','部位类型','机构名称','操作'],
        colModel: [
            {name: 'itemmastDesc', index: 'itemmastDesc', sortable: false},
            {name: 'bodypartDesc', index: 'bodypartDesc', width:60,sortable: false},
            {name: 'bodypartPname', index: 'bodypart_Pname', width:60,sortable: false},
            {name: 'bodypartTypename', index: 'bodypart_Typename', width:60,sortable: false},
            {name: 'orgName', index: 'orgName', sortable: false},
            {name: 'bodypart2Id', index: "bodypart2Id", width:60, sortable: false
            	,formatter: function (param1, param2, recode) {
                    return '<div class="action-buttons">' +
//                        '<a class="blue" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'query\')" title="查看"><i class="icon-search bigger-120"></i></a>' +
//                        '<a class="red" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'update\')" title="修改"><i class="icon-edit bigger-120"></i></a>' +
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
		title="查看检查部位项目对应信息";
		url="/basecode/aiscBodyPartItemDetail.html?id="+id+"&type="+type;
		//所有控件不可用
	}else if(type=="update"){
		title = "修改检查部位项目对应信息";
		url="/basecode/aiscBodyPartItemDetail.html?id="+id+"&type="+type;
	}else{
		title = "新增检查部位项目对应信息";
		url="/basecode/aiscBodyPartItemDetail.html?id="+id+"&type="+type;
	}
    var index = layer.open({
        type: 2,
        title:title,
        area: ['1000px', '500px'],
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
        url: GLOBAL.WEBROOT +"/basecode/deleteAisBodyPartItem.ajax?id="+param,
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
