<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" id="editMCPartForm" action="<c:url value='quality/MCPart/update.do?navTabId=mcPartTab&callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" id="id" value="${vo.id}">
			<table class="tableFormContent nowrap">
				<tr>
					<th>MES供应商编号：</th>
					<td><input name="mesPartNumber" type="text" value="${vo.mesPartNumber}" /></td>
				</tr>
				<tr>
					<th>MES供应商名称：</th>
					<td><input name="mesPartName" type="text" value="${vo.mesPartName}" /></td>
				</tr>
				<tr>
					<th>CRM供应商编号：</th>
					<td><input name="crmPartNumber" type="text" value="${vo.crmPartNumber}" /></td>
				</tr>
				<tr>
					<th>CRM供应商名称：</th>
					<td><input name="crmPartName" type="text" value="${vo.crmPartName}" /></td>
				</tr>
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>