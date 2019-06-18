  
$(function () {
    initDict();
	//查询
	selectList();
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
            source_cityCode.select2();
        }
    });
    getCountySelect();
    getOrgSelects();
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
            source_countyCode.select2();
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
            source_orgMsg.select2();
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
        url: GLOBAL.WEBROOT +"/basecode/areaInstitution/queryPageList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['机构ID','机构编码','机构名称','城市编码','城市描述','区县编码','区县描述','是否上线','操作'],
        colModel: [
            {name: 'orgId', index: 'org_Id',width:80, sortable: false},
            {name: 'orgCode', index: 'org_Code',width:80, sortable: false},
            {name: 'orgName', index: 'org_Name',width:80, sortable: false},
            {name: 'cityCode', index: 'city_Code',width:80,  sortable: false},
            {name: 'cityDesc', index: "city_Desc",width:80, sortable: false},
            {name: 'districtCode', index: "district_Code",width:80, sortable: false},
            {name: 'districtDesc', index: "district_Desc", width:80,sortable: false},
            {name: 'haspacs', index: "haspacs_Desc",width:80, sortable: false,formatter : function(value) {
					if ('1' == value){
						return "是";
					}else if ('0' == value){
						return "否";
					}else{
						return "否";
					}						
				}
            },
            {name: 'orgId', index: "orgId", width:40, sortable: false
            	,formatter: function (param1, param2, recode) {
                    return '<div class="action-buttons">' +
                        '<a class="red" href="javascript:void(0)" onclick="updateHasPacs(\'' + param1 + '\',\''+recode.haspacs+'\')" title="修改"><i class="icon-edit bigger-120"></i></a>' +
                        // '<a class="green" href="javascript:void(0)" onclick="deleData(\'' + param1 + '\',\'delete\')" title="删除"><i class="icon-remove bigger-120"></i></a>' +
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
//
// //操作
// function view(id,type) {
// 	var url ;
// 	var title ;
// 	if(type=="query"){
// 		title="查看信息";
// 		url="/basecode/areaInstitution/viewDetail.html.html?id="+id+"&type="+type;
// 		//所有控件不可用
// 	}else if(type=="update"){
// 		title = "修改信息";
// 		url="/basecode/areaInstitution/viewDetail.html?id="+id+"&type="+type;
// 	}else{
// 		title = "新增信息";
// 		url="/basecode/areaInstitution/viewDetail.html?id="+id+"&type="+type;
// 	}
//     var index = layer.open({
//         type: 2,
//         title:title,
//         area: ['800px', '300px'],
//         fix: false, //不固定
//         maxmin: true,
//         content: GLOBAL.WEBROOT + url
//     });
//     layer.full(index);
// }

//
//
// function deleData(param){
// 	layer.confirm('是否确定删除该信息？', {
// 	    btn: ['确定','取消'] //按钮
// 	}, function(){
// 		$.ajax({
//         type: "GET",
//         async: true,
//         url: GLOBAL.WEBROOT +"/basecode/areaInstitution/delete.ajax?id="+param,
//         dataType: 'json',
//         success: function (data) {
// 			if (data.ERRCODE == "0") {
//                 layer.msg('删除成功', {icon: 1});
//                 reloadGrid();
//             }
//             else {
//                 layer.msg('删除失败', {icon: 1});
//             }
//         }
//    		});
// 	}, function(){
// 	    //layer.msg('奇葩么么哒', {icon:2});
// 	});
// }


function importOrgToArea(){
    layer.confirm('是否确定导入机构信息？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            type: "GET",
            async: true,
            url: GLOBAL.WEBROOT +"/basecode/areaInstitution/importOrgToArea.ajax",
            dataType: 'json',
            success: function (data) {
                if (data.ERRCODE == "0") {
                    layer.msg('修改成功', {icon: 1});
                    reloadGrid();
                }
                else {
                    layer.msg('修改失败', {icon: 1});
                }
            }
        });
    }, function(){
        //layer.msg('奇葩么么哒', {icon:2});
    });
}

function updateHasPacs(conId,hasStatus){
    layer.confirm('是否确定修改上线状态？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            type: "GET",
            async: true,
            url: GLOBAL.WEBROOT +"/basecode/areaInstitution/updateHasPacs.ajax?id="+conId+"&status="+hasStatus,
            dataType: 'json',
            success: function (data) {
                if (data.ERRCODE == "0") {
                    layer.msg('修改成功', {icon: 1});
                    reloadGrid();
                }
                else {
                    layer.msg('修改失败', {icon: 1});
                }
            }
        });
    }, function(){
        //layer.msg('奇葩么么哒', {icon:2});
    });
}
