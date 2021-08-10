<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<!-- navTabId重新载入index对应的tab -->
	<form method="post" id="formID" action="<c:url value='system/group/update.do?navTabId=grouplistNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="groupKey" value="${vo.groupKey}"/>
			<table class="tableFormContent nowrap">
		        <tr>
		            <th>用户组编号：</th>
		            <td>
		                <input name="groupCode" type="text" size="30" class="required" minlength="1" value="${vo.groupCode}"/>
		            </td>
		        </tr>
		        <tr>
		            <th>用户组名称：</th>
		            <td>
		            <input name="groupName" type="text" size="30" class="required" value="${vo.groupName}"/>
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
