<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
        //jQuery("#formID").validationEngine();
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/user/resetPwd.do?navTabId=userlistNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${user.id}">
		<table class="tableFormContent nowrap">
		<tr>
			<th>密码</th>
			<td >
			<input type="password" size="40" id="password" name="password"  class="required alphanumeric" minlength="6" maxlength="20"  value="" autocomplete="off" />
			</td>	
		</tr><tr>
			<th>确认密码</th>
			<td >
			<input type="password" size="40" name="confirmPassword" class="required " equalto="#password" value="" autocomplete="off" />
			</td>			
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
