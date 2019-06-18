
function downloadFile(fileName){
	 window.location.href = GLOBAL.WEBROOT + "/studyReport/downloadPdf?fileName="+fileName+"&oldName="+fileName.substr(0,32);
}
