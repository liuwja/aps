<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/fault/faultReason/saveMesh.do?navTabId=faultReasonNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input name="keys" id="keys" value="${keys}" type="hidden"/>
		<div class="pageFormContent" layoutH="56">
		<table class="tableFormContent nowrap">
			<tr>
				<th>合并故障代码：</th>
				<td><input name="meshFaultCode" id="meshFaultCode" type="text" size="30" class="required" onchange="selectMeshName()" /></td>
			</tr>
			<tr>
				<th>合并故障名称：</th>
				<td><input name="meshFaultName" id="meshFaultName" type="text" size="30" class="required" /></td>
			</tr>			
		</table>	
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="saveMesh();">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
function saveMesh(){
	var keys = $("#keys", $.pdialog.getCurrent()).val();
	var meshFaultCode = $("#meshFaultCode", $.pdialog.getCurrent()).val();
	var meshFaultName = $("#meshFaultName", $.pdialog.getCurrent()).val();
	$.ajax({
		type:"post",
		data:{"keys":keys, "meshFaultName":meshFaultName, "meshFaultCode":meshFaultCode},
		url:"base/fault/faultReason/saveMesh.do",
		success:function(data){
			$.pdialog.closeCurrent();
			if(data=="success"){
				alertMsg.correct("合并成功！");
			}else{
				alertMsg.error("合并失败！");
			}
			$.pdialog.close("dlg_page12");
			navTab.reload("base/fault/faultReason/faultReason.do");
		}
	});
}

function selectMeshName() {
	var meshFaultCode = $("#meshFaultCode", $.pdialog.getCurrent()).val();
	$.ajax({
		type : "POST",
		data : {"meshFaultCode":meshFaultCode},
		url : "base/fault/faultReason/getMeshName.do",
		success : function(data) {
			console.log(data);
			if (data != "null") {
				$("#meshFaultName").val(data);
			}
		}
	});
}
</script>