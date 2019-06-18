$(function () {
    initDict();
    $("#ordsubcategoryId").select2();
});
//initial query dict
function initDict() {
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
	        orgSelect.val(parent.$("#currentOrgId").val());
	        orgSelect.select2();
	        
	        selectList();
		}
	}); 
	
    //初始化字典值
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT + "/aiscItemMast/getQueryCondition",
        success: function (data) {
            var jsonService = eval(data.service);
            var serviceSelect = $("#itemmastSevice");
            serviceSelect.empty();
            serviceSelect.append("<option value='-1'>请选择</option>");
            for (var i = 0; i < jsonService.length; i++) {
                serviceSelect.append("<option value='" + jsonService[i].itemno + "'>" + jsonService[i].itemname + "</option>");
            }
			serviceSelect.select2();
            var jsonIsEnhanced = eval(data.isEnhanced);
            var isenhancedSelect = $("#itemmastIsenhanced");
            isenhancedSelect.empty();
            isenhancedSelect.append("<option value='-1'>请选择</option>");
            for (var i = 0; i < jsonIsEnhanced.length; i++) {
                isenhancedSelect.append("<option value='" + jsonIsEnhanced[i].itemno + "'>" + jsonIsEnhanced[i].itemname + "</option>");
            }
            isenhancedSelect.select2();
        }
    });
    
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

function reloadGrid() {
    var data = $("#searchForm").serializeObject();
    $("#aiscItemMast").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
    var grid_selector = "#aiscItemMast";
    var pager_selector = "#aiscItemMast_pager";
    var data = $("#searchForm").serializeObject();
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/aiscItemMast/getAiscItemMastList.ajax",
        type: "post",
        datatype: "json",
        height: '100%',
        width: '100%',
        postData: data,
        colNames: ['检查项目ID','检查大类','检查子类', '检查代码', '检查名称','检查名称拼音','项目（英文）名称', '项目费用','检查项目权重','曝光次数','服务标志','是否增强检查','机构名称','操作'],
        colModel: [
            {name: 'itemmastId', index: 'itemmastId',width:60, sortable: false},
            {name: 'ordcategoryDesc', index: 'ordcategoryDesc',width:100, sortable: false,align:'center'},
            {name: 'ordsubcategoryDesc', index: 'ordsubcategoryDesc',width:80, sortable: false},
            {name: 'itemmastCode', index: 'itemmastCode',width:80, sortable: false},
            {name: 'itemmastDesc', index: 'itemmastDesc',width:80,sortable: false},
            {name: 'pyInitial', index: 'pyInitial',width:80,sortable: false},
            {name: 'itemmastEndesc', index: 'itemmastEndesc',width:110,sortable: false},
            {name: 'itemmastPrice', index: 'itemmastPrice',width:80,sortable: false},
            {name: 'itemmastWeight', index: 'itemmastWeight',width:80,sortable: false},
            {name: 'itemmastExposurecount', index: 'itemmastExposurecount',width:80,sortable: false},
            {name: 'itemmastSevice', index: "itemmastSevice", sortable: false,formatter : function(value) {
					if ("1" == value){
						return "检查项目";
					}else if ('2' == value){
						return "材料（注射针筒等）";
					}else if ('3' == value){
						return "药品（造影剂等）";
					}else{
						return "";
					}						
				}
            },
            {name: 'itemmastIsenhanced', index: "itemmastIsenhanced", sortable: false,formatter : function(value) {
				if ('1' == value){
					return "是";
				}else if ('2' == value){
					return "否";
				}else{
					return "";
				}						
			  }
            },
            {name: 'orgName', index: 'orgName',width:80,sortable: false},
            {
                    name: 'itemmastId', index: "itemmastId",width:60, sortable: false
                    , formatter: function (cellvalue, options, rowObject) {
                    if(resources.containRes('deleteBtn')) {
                        return '<div class="action-buttons">' +
                            '<a class="blue" href="javascript:void(0)" onclick="edit(\'' + cellvalue + '\',\'view\')"  title="查看"><i class="icon-search bigger-120"></i></a>' +
                            '<a class="red" href="javascript:void(0)" onclick="edit(\'' + cellvalue + '\',\'update\')"  title="编辑"><i class="icon-edit bigger-120"></i></a>' +
                            '<a class="green" href="javascript:void(0)" onclick="deleteAiscItemMast(\'' + cellvalue + '\')" title="删除"><i class="icon-remove bigger-120"></i></a>' +
                            "</div>";
                    }else{
                        return '<div class="action-buttons">' +
                            '<a class="blue" href="javascript:void(0)" onclick="edit(\'' + cellvalue + '\',\'view\')"  title="查看"><i class="icon-search bigger-120"></i></a>' +
                            '<a class="red" href="javascript:void(0)" onclick="edit(\'' + cellvalue + '\',\'update\')"  title="编辑"><i class="icon-edit bigger-120"></i></a>' +
                            "</div>";
                    }
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

function edit(id,opType) {
    var url;
    if(id){
        url = GLOBAL.WEBROOT + "/aiscItemMast/page/edit?id="+id+"&opType="+opType;
    }else{
        url = GLOBAL.WEBROOT + "/aiscItemMast/page/edit?opType=add";
    }
    var index = layer.open({
        type: 2,
        title:"检查项目维护",
        area: ['550px', '500px'],
        fix: false, //不固定
        maxmin: true,
        content: url
    });
    layer.full(index);
}

function deleteAiscItemMast(id) {
    layerConfirm('是否删除当前选中记录',function() {
        var data = "id=" + id;
        $.ajax({
            type: "GET",
            async: true,
            url: GLOBAL.WEBROOT + "/aiscItemMast/deleteAiscItemMast",
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
function importItem() {
    var url = GLOBAL.WEBROOT + "/aiscItemMast/page/import";
    var index = layer.open({
        type: 2,
        title:"检查项目导入",
        area: ['650px', '300px'],
        fix: false, //不固定
        maxmin: false,
        content: url
    });
//    layer.full(index);
}