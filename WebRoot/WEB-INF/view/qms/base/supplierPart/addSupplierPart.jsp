<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" id="addSupplierRefForm" class="pageForm required-validate" action="<c:url value='base/supplierRef/saveSupplierPart.do?navTabId=supplierPartListTab&callbackType=closeCurrent'/>" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<table class="tableFormContent nowrap">
				<tr>
					<th>供应商编号：</th>
					<td><input name="supplierNumber" type="text" /></td>
				</tr>
				<tr>
					<th>供应商名称：</th>
					<td><input name="supplierName" type="text" /></td>
				</tr>
				<tr>
					<th>新供应商编号：</th>
					<td><input name="supplierNumberN" type="text" /></td>
				</tr>
				<tr>
					<th>物料编码：</th>
					<td><input name="partNumber" type="text" /></td>
				</tr>
				<tr>
					<th>物料名称：</th>
					<td><input name="partName" type="text" /></td>
				</tr>
				<tr>
					<th>供应商代号：</th>
					<td><input name="supplierCode" type="text" /></td>
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