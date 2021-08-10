<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/supplierRef/updateSupplierPart.do?navTabId=supplierPartListTab&callbackType=closeCurrent'/>" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" name="id" id="id" value="${vo.id}">
			<table class="tableFormContent nowrap">
				<tr>
					<th>供应商编号：</th>
					<td><input name="supplierNumber" type="text" value="${vo.supplierNumber}" /></td>
				</tr>
				<tr>
					<th>供应商名称：</th>
					<td><input name="supplierName" type="text" value="${vo.supplierName}" /></td>
				</tr>
				<tr>
					<th>新供应商编号：</th>
					<td><input name="supplierNumberN" type="text" value="${vo.supplierNumberN}" /></td>
				</tr>
				<tr>
					<th>物料编码：</th>
					<td><input name="partNumber" type="text" value="${vo.partNumber}" /></td>
				</tr>
				<tr>
					<th>物料名称：</th>
					<td><input name="partName" type="text" value="${vo.partName}" /></td>
				</tr>
				<tr>
					<th>供应商代号：</th>
					<td><input name="supplierCode" type="text" value="${vo.supplierCode}" /></td>
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