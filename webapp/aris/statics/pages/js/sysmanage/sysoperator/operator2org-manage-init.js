
$(function () {
    selectList();
});

function reloadGrid() {
    var operatorCode = $("#operatorCode").val();
    var data ={"operatorCode":operatorCode};
    $("#operator2org").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
    var grid_selector = "#operator2org";
    var pager_selector = "#operator2org_pager";
    var operatorCode = $("#operatorCode").val();
    var data ={"operatorCode":operatorCode};
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/sysmanage/sysoperator/getStationList",
        type: "post",
        cache:false,
        datatype: "json",
        height: '100%',
        width: '100%',
        postData: data,
        colNames: ['组织名称', '岗位名称','hidden','hidden','hidden'],
        colModel: [
            {name: 'orgName', index: 'orgName', sortable: false,width:100},
            {
              name: 'stationName', index: 'stationName', sortable: false,align:'center',width:90
            },
            {
                name: 'operatorCode', index: 'operatorCode', sortable: false,align:'center',width:90,hidden:true
            },
            {
                name: 'stationCode', index: 'stationCode', sortable: false,align:'center',width:90,hidden:true
            },
            {
                name: 'orgId', index: 'orgId', sortable: false,align:'center',width:90,hidden:true
            }
        ],
        viewrecords: false,
        rowNum: 10,
        rowList: [10, 15, 20, 30],
        multiselect: true,
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
    }).jqGrid("navGrid",pager_selector,{refresh: true});
}

//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}


function addOperator2Org() {
    var operatorCode = $("#operatorCode").val();
    var url = GLOBAL.WEBROOT + "/sysmanage/sysoperator/page/operatorinfo-station.html?id="+operatorCode;

    layer.open({
        type: 2,
        title:"岗位新增",
        area: ['500px', '460px'],
        fix: false, //不固定
        maxmin: true,
        content: url,
        closeBtn: 1
    });
}

function deleteOperator2org() {
    var ids = $('#operator2org').jqGrid('getGridParam', 'selarrrow');
    if (ids.length == 0) {
        info('温馨提示', "请选择至少一条记录删除");
        return;
    }
    layerConfirm('是否删除当前选中记录',function() {
        var operatorCodes = "";
        for (var i = 0; i < ids.length; i++) {
            //选中行的时间
            var operatorCode = $("#operator2org").getCell(ids[i], "operatorCode");
            var stationCode = $("#operator2org").getCell(ids[i], "stationCode");
            var orgId = $("#operator2org").getCell(ids[i], "orgId");
            //建一个数组，把选中行的时间添加到这个数组中去。
            operatorCodes  += "," +  operatorCode+"-"+stationCode+"-"+orgId;
        }
        operatorCodes = operatorCodes.substring(1);
        $.ajax({
            type: "GET",
            async: true,
            cache:false,
            url: GLOBAL.WEBROOT + "/sysmanage/sysoperator/deleteOperator2org?operatorCodes="+operatorCodes,
            dataType: 'json',
            success: function (data) {
                if (data.result == "success") {
                    reloadGrid();
                    infoSuccess('温馨提示', '删除成功');
                }
                else {
                    info('温馨提示', data.result);
                }
            }
        });
    });
}