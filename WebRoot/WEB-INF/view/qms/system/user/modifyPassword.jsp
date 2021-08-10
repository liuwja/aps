<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/user/modifyPwd.do?navTabId=editUser&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="36" >
	    <input type="hidden" name="id" value="${user.id}">
		<table class="tableFormContent nowrap">
		<tr >
			<th>原密码：</th>
			<td>
			<input name="password" type="password" class="required" minlength="6" maxlength="20"  value="" autocomplete="off" size="30" value="" />
			</td>
		</tr>
		<tr>
			<th>新密码：</th>
			<td><input name="newPassword" id="newPassword" type="password" class="required" minlength="6" maxlength="20"  value="" autocomplete="off" size="30" value="" /></td>
		</tr>
		<tr>
			<th>确认密码：</th>
			<td>
			<input type="password" size="30" name="confirmPassword" class="required" minlength="6" maxlength="20"  value="" autocomplete="off" equalto="#newPassword" value=""/>
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
