function checkForm(){
    var lDiv = document.getElementById("loadDiv");
    if(lDiv.style.display=='none'){
        lDiv.style.display='block';
        $("#saveImport").hide();
    }
}

function fileDownLoad(fileName){
    window.location.href = GLOBAL.WEBROOT + "/studyReport/fileDownload?fileName="+fileName+"&fileInFolder="+($("#patientName").val()+"_"+$("#patientId").val()+"_"+$("#reportId").val());
}
function fileDelete(fileName,obj) {
    $.ajax({
        type: "POST",
        async:false,
        url: GLOBAL.WEBROOT + "/studyReport/fileDelete.ajax?fileName="+fileName+"&patientName="+$("#patientName").val()+"&patientId="+$("#patientId").val()+"&reportId="+$("#reportId").val(),
        success: function (data) {
            if(data.ERRCODE == "0"){
                layer.msg("删除成功", {icon: 6});
                $(obj).parent().parent().remove();
            }else{
                layer.msg(data.MESSAGE,{icon:5});
            }
        }
    });
}
function closeDiv(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
function addTr(){
    $("#addbtnTr tr").parent().append("<tr><td colspan='2'><input type='file' name='file1' ></td></tr>");
    //$("#addbtnTr tr").parent().append("<tr><td colspan='2'><input type='file' name='file1'  onchange='checkAndSubmit(this)'></td></tr>");
//	$("#addbtnTr").find("tr:last");
}

function sureInfo(){
    var options = {
        dataType: 'json',
        type : "POST",
        url: GLOBAL.WEBROOT+"/studyReport/saveUploadFiles.ajax?patientId="+$("#patientId").val()+"&reportId="+$("#reportId").val()+"&patientName="+encodeURI(encodeURI($("#patientName").val())),
        async:false,
        success: function (data) {
            if (data.ERRORCODE == "0") {
                if(data.RESULT=="0"){
                    var  fileNameArray = $("#file1").val().split("\\");
                    var fileName = fileNameArray[fileNameArray.length-1]
                    var appendHtml="<tr> <td><a href=\"javascript:void(0)\" onclick=\"fileDownLoad('"+fileName+"');return false;\">"+fileName+"</a></td>"+
                        "<td><input type=\"button\" value=\"下载\" onclick=\"fileDownLoad('"+fileName+"')\"></td>"+
                        "<td><input type=\"button\" value=\"删除\" onclick=\"fileDelete('"+$("#file1").val()+"',this)\"></td></tr>";
                    $('#addbtnTr').append(appendHtml);
                    $("#file1").val("")
                    layer.msg("文件上传成功",{icon:6});
                }else if(data.RESULT=="-1"){
                    info("提示消息", "文件上传失败");
                }else{
                    info("提示消息", data.RESULT+"文件已上传,请重新上传提交");
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