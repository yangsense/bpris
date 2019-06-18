$(function () {
	$("input").attr("readonly","readonly");
	$("#saveBtn").click(function(){
		regCancel();
	});
});
function regCancel(){
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/patientReg/regCancel.ajax",
        data: {
        	studyinfoId:$("#studyinfoId").val()
        },
        success: function (data) {
            if (data.ERRCODE == "0") {
                alert("保存成功！");
                closeDiv();
            }
            else {
            	alert(data.ERRINFO);
            }
        }
    });
}
//关闭
function closeDiv(){
	parent.reloadGrid(0,null,"");
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);    
}