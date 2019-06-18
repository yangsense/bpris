var orgCode = parent.org.orgCode;
var operatorName = parent.user.operatorName;
var operatorCode = parent.user.operatorCode;
var userStation = parent.userStation;

$(function () {
    $("select").select2();
    selectList();
});


function reloadGrid() {
    var data = $("#searchForm").serializeObject()
    $("#sysStation").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
    var grid_selector = "#sysStation";
    var pager_selector = "#sysStation_pager";
    var data = $("#searchForm").serializeObject();
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/sysmanage/sysstation/getStationList.ajax",
        type: "post",
        cache:false,
        datatype: "json",
        height: '100%',
        width: '100%',
        postData: data,
        colNames: ['岗位编码', '岗位名称', '备注','操作'],
        colModel: [
            {name: 'stationCode', index: 'stationCode', sortable: false,width:100},
            {
                name: 'stationName', index: 'stationName', sortable: false,align:'center',width:90
            },

            {name: 'remark', index: 'remark',sortable: false,width:80},
            {
                    name: 'stationCode', index: "stationCode", sortable: false,width:100,align:'center'
                    , formatter: function (cellvalue, options, rowObject) {

                        return '<a class="blue" href="javascript:void(0)" onclick="openEdit(\'' + cellvalue + '\')" title="编辑"><i class="icon-pencil bigger-130"></i></a>';
                }
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


function openEdit(stationCode) {
    var url;
    if(stationCode){
        url = GLOBAL.WEBROOT + "/sysmanage/sysstation/page/edit.html?id="+stationCode;
    }else{
        url = GLOBAL.WEBROOT + "/sysmanage/sysstation/page/edit.html";
    }
    layer.open({
        type: 2,
        title:"岗位新增",
        area: ['620px', '410px'],
        fix: false, //不固定
        maxmin: true,
        content: url,
        closeBtn: 0
    });
}

function deleteStation() {
    var ids = $('#sysStation').jqGrid('getGridParam', 'selarrrow');
    if (ids.length == 0) {
        info('温馨提示', "请选择至少一条记录删除");
        return;
    }
    layerConfirm('是否删除当前选中记录',function() {
        var stationCodes = "";
        for (var i = 0; i < ids.length; i++) {
            //选中行的时间
            var stationCode = $("#sysStation").getCell(ids[i], "stationCode");
            //建一个数组，把选中行的时间添加到这个数组中去。
            stationCodes  += "," +  stationCode;
        }
        stationCodes = stationCodes.substring(1);
        $.ajax({
            type: "GET",
            async: true,
            cache:false,
            url: GLOBAL.WEBROOT + "/sysmanage/sysstation/deleteStations?stationCodes="+stationCodes,
            dataType: 'json',
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