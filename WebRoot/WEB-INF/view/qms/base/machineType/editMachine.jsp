<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/machineType/updateMachineType.do?navTabId=machineTypeNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" id="id" value="${machineType.id}">
		<table class="tableFormContent nowrap">
			<tr >
                <th>机型类别：</th>
                <td><input name="machineType" type="text" size="30" class="required" value="${machineType.machineType}" /></td>
            </tr>
            
           
            
		</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>

