<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" id="addMCPartForm" class="pageForm required-validate" action="<c:url value='quality/MCPart/save.do?navTabId=mcPartTab&callbackType=closeCurrent'/>" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<table class="tableFormContent nowrap">
				<tr>
					<th>MES供应商编号：</th>
					<td><input name="mesPartNumber" type="text" /></td>
				</tr>
				<tr>
					<th>MES供应商名称：</th>
					<td><input name="mesPartName" type="text" /></td>
				</tr>
				<tr>
					<th>CRM供应商编号：</th>
					<td><input name="crmPartNumber" type="text" /></td>
				</tr>
				<tr>
					<th>CRM供应商名称：</th>
					<td><input name="crmPartName" type="text" /></td>
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