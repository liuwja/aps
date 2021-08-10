<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript" src="resources/js/ajaxfileupload.js"> </script>
<div class="pageContent">
<form id="supplierRefImportForm" method="post">
	<fieldset>
		<legend>供应商数据上传(Excel或者csv)</legend>
		<table id="supplierRefImportTB" class="tableFormContent nowrap" >
			<tr>
				<th>导入格式：</th>
				<td colspan="3">第一列为旧供应商编号，第二列为旧供应商名称，第三列为新供应商编号，第四列为新供应商名称</td>
			</tr>
			<tr>
				<th>数据上传：</th>
				<td><input id="excelFileInput" type="file" name="excelFile" accept="*.csv" style="cursor:hand"/></td>
				<td width="10%">
                	<img id="uploadingImg" src="resources/img/loading.gif" height="20" style="display: none;"/>
                	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="upload()">导入</button></div></div>
				</td>
				<td id="uploadResult" style="color: red"></td>
			</tr>
		</table>
	</fieldset>
</form>
</div>
<script type="text/javascript">
function upload() {
	uploading();
	$("#supplierRefImportForm", navTab.getCurrentPanel()).ajaxSubmit({
		url:"base/supplierRef/upload.do",
		type:"post",
		enctype:"multipart/form-data",
        dataType:"html",
        success: function(msg){
        	alertMsg.correct("导入成功！");
        	$("#uploadResult").html(msg);
        },
        error : function(XmlHttpRequest, textStatus, errorThrown){
        	alertMsg.error("导入失败！");
        	$("#uploadResult").html(errorThrown);
        }
	})
}

function uploading (){
	$("#uploadingImg").ajaxStart(function(){
		$(this).show();
	}).ajaxComplete(function(){
		$(this).hide();
	});
}
</script>