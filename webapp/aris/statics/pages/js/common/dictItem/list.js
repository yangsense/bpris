$(function () {
    $("select").select2();
    selectList();
});

function reloadGrid(){
    var data = $("#searchForm").serializeObject();
	$("#dictItem").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

function reset(){
	$("#itemName").val("");
	$("#itemDesc").val("");
	$("#itemState").val("1");
	$("#itemState").select2();
}
//查询列表
function selectList() {
	var grid_selector = "#dictItem";
    var pager_selector = "#dictItem_pager";
    var data = $("#searchForm").serializeObject();
   
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/dictItem/getDictItemList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['字典名称', '字典值','字典编号', '父节点', '排序', '状态', '备注','操作'],
        colModel: [
            {name: 'dictName', index: 'dictName',width:80, sortable: false},
            {name: 'itemName', index: 'itemName', width:80,sortable: false},
            {name: 'itemNo', index: 'itemNo', width:80, sortable: false,align:'center'},
            {name: 'parentItemNo', index: 'parentItemNo', width:80,sortable: false,align:'center'},
            {name: 'itemOrder', index: 'itemOrder',width:80, sortable: false},
            {name: 'itemState', index: 'itemState', width:80, sortable: false,align:'right',
                formatter: function (cellvalue, options, rowObject) {
                var itemState = cellvalue;
                if(itemState=="0"){
                        return "无效";
                    }else{
                        return "有效";
                    }
                }
            },
            {name: 'itemDesc', index: 'itemDesc',width:80, sortable: false,align:'right'},

            {name: 'dictName', index: "dictName", width:60, sortable: false
            	,formatter: function (cellvalue, options, rowObject) {
                    var itemNo = rowObject.itemNo;
                return '<div class="action-buttons">' +
                    '<a class="blue" href="javascript:void(0)" onclick="edit(\'' + cellvalue + '\',\'' + itemNo + '\',\'view\')" title="编辑"><i class="icon-search bigger-120"></i></a>' +
                    '<a class="red" href="javascript:void(0)" onclick="edit(\'' + cellvalue + '\',\'' + itemNo + '\',\'update\')" title="编辑"><i class="icon-edit bigger-120"></i></a>' +
                    '<a class="green" href="javascript:void(0)" onclick="deleteDictItem(\'' + cellvalue + '\',\'' + itemNo + '\')" title="删除"><i class="icon-remove bigger-120"></i></a>'
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

function edit(dictName,itemNo,opType) {
    var url;
    if(opType){
        url = GLOBAL.WEBROOT + "/dictItem/page/edit?dictName="+dictName+"&itemNo="+itemNo+"&opType="+opType;
    }else{
        url = GLOBAL.WEBROOT + "/dictItem/page/edit";
    }
    layer.open({
        type: 2,
        title:"数据字典维护",
        area: ['620px', '450px'],
        fix: false, //不固定
        maxmin: true,
        content: url
    });
}

function deleteDictItem(dictName,itemNo) {
    layerConfirm('是否删除当前选中记录',function() {
        var data = "dictName=" + dictName+"&itemNo="+itemNo;
        $.ajax({
            type: "GET",
            async: true,
            url: GLOBAL.WEBROOT + "/dictItem/deleteDictItem",
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