$(function () {
    if($("#menuType").val()=='3'){
        $("#menuTypeTr").hide();
        $("#menuTypeTr1").hide();
        $("#cdljtemp").hide();
        $("#cdljte").hide();
        flag = "3";
    }
});
var flag;
function addNew() {
    if(flag != '3'){
        flag = $("#menuTypeTr").val();
    }
    if($("#cdmc").val() == ''){
        alert("菜单名称不能为空！");
        return;
    }
    if(flag != '3'){
        if($("#cdlj").val() == ''){
            alert("菜单链接不能为空！");
            return;
        }
    }
    $.ajax({
        type: "GET",
        url: GLOBAL.WEBROOT + "/menu/add.ajax?sysTypeName="+$("#ssxt").val()+"&parentMenuId="+$("#parentMenuId").val()
        +"&menuName="+encodeURI(encodeURI($("#cdmc").val()))+"&menuUrl="+encodeURI(encodeURI($("#cdlj").val()))
        +"&status="+$("#qyzt").val()+"&menuImage="+encodeURI(encodeURI($("#cdtp").val()))
        +"&menuDesc="+encodeURI(encodeURI($("#cdms").val()))+"&menuType="+flag,
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            if (data.result == true) {
                layer.msg('新增节点成功！');
                parent.initMenuTree();
                parent.reloadGrid();
                setTimeout(closeDiv,1500);
            }
            else {
                layer.msg('新增节点失败！', {icon: 1});
            }
        }
    });
}
function closeDiv(){
    parent.reloadGrid();
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}