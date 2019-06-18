function checkForm(){
	var lDiv = document.getElementById("loadDiv");
	if(lDiv.style.display=='none'){
	    lDiv.style.display='block';
	    $("#saveImport").hide();
	}
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
function addTr(){
	$("#addbtnTr tr").parent().append("<tr><td colspan='2'><input type='file' name='file1'  onchange='checkAndSubmit(this)'></td></tr>"); 
//	$("#addbtnTr").find("tr:last");
}

function sureInfo(){
	var options = {
			dataType: 'json',
			type : "POST",
			url: GLOBAL.WEBROOT+"/patientReg/saveUploadPdf.ajax?studyInfoId="+$("#studyInfoId").val(),
			async:false,
			success: function (data) {
				if (data.ERRORCODE == "0") {
					if(data.RESULT=="0"){
						info("提示消息", "电子病例上传成功");
//						更新image状态
						updateStudinfoHavingimg($("#studyInfoId").val());
						setTimeout(parent.window.location.reload(),2500);
						$("#studyInfoId").val()
//						parent.$("#submitbtn").removeClass("disabled");
						
					}else if(data.RESULT=="-1"){
						info("提示消息", "电子病例上传失败");
					}else{
						info("提示消息", data.RESULT+"电子病例已上传,请重新上传提交");
					}
				} else {
					info("提示消息", "上传失败"+data.ERRINFO);
				}
			},
			error: function () {
				info("提示信息","操作失败！");
			},
			beforeSend:function(){
				$("#saveImport").attr("disabled", true);
				$("#saveImport").val("上传中...");
			},
			complete : function(responseText, statusText) {
				$("#saveImport").attr("disabled", false);
				$("#saveImport").val("上传");
			},
		};
		$("#uploadTemplateForm").ajaxSubmit(options);
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function updateStudinfoHavingimg(studyinfoId){
	$.ajax({
        type: "POST",
        async:false,
        url: GLOBAL.WEBROOT+"/patientReg/updateStudinfoHavingimg.ajax?studyinfoId="+studyinfoId ,
        success: function (data) {
	       
        }
    });
}

function checkAndSubmit(me){
	me.blur();
	var str=me.value;
	str=str.substr(str.lastIndexOf(".")+1); 
    if(!str.startWith('pdf')){
    	alert("只可导入pdf文件");
    	return;
    }
//    else{	
//		saveImport();
//	}
}

String.prototype.startWith=function(s){
  if(s==null||s==""||this.length==0||s.length>this.length)
   return false;
  if(this.substr(0,s.length)==s)
     return true;
  else
     return false;
  return true;
 }