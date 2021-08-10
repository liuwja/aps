<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/locationRegion/saveMesh.do?navTabId=locationRegionListNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		
		<input name="keys" id="keys" value="${keys}" type="hidden"/>
		<div class="pageFormContent" layoutH="56">
		<table class="tableFormContent nowrap">
			<tr>
				<th>服务中心名称：</th>
				<td>
	                <input name="mergeRegion" id="mergeRegion" type="text" size="30" class="required" />
	            </td>
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
		var mergeRegion = $("#mergeRegion", $.pdialog.getCurrent()).val();
		$.ajax({
			type:"post",
			data:{"keys":keys,"mergeRegion":mergeRegion},
			url:"base/locationRegion/saveMesh.do",
			success:function(data){
				$.pdialog.closeCurrent();
				if(data=="success"){
					alertMsg.correct("合并成功！");
				}else{
					alertMsg.error("合并失败！");
				}
				$.pdialog.close("dlg_regionPage");
				navTab.reload("base/locationRegion/locationRegionList.do");
			}
		});
	}
</script>

