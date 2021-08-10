<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
</script>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/basicdata/updateMaterial.do?navTabId=factoryNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<input name="id" id="id" type="hidden" value="${material.id}"/>
		<div class="pageFormContent" layoutH="56">
		<table class="tableFormContent nowrap">
			<tr>
				<th>物料编号：</th>
				<td>
					<input name="materialNo" id="materialNo" type="text" size="30" class="required"/>
				</td>			
				<th> 物料名称：</th>
				<td>
	                <input name="materialName" id="materialName" type="text" size="30" class="required"/>
	            </td>
			</tr>
			<tr>
				<th>物料组：</th>
				<td>
	                <input name="materialGroup" id="materialGroup" type="text" size="30" class="required"/>
	            </td>
	            <th> 采购组织：</th>
				<td>
	                <input name="purchasing" id="purchasing" type="text" size="30" class="required"/>
	            </td>
			</tr>
			<tr>
				<th>库存仓库：</th>
				<td>
	                <input name="warehouse" id="warehouse" type="text" size="30" class="required"/>
	            </td>
	            <th> 库存单位：</th>
				<td>
	                <input name="stock" id="stock" type="text" size="30" class="required"/>
	            </td>
			</tr>
			<tr>
				<th>BOM消耗单位：</th>
				<td>
	                <input name="bom" id="bom" type="text" size="30" class="required"/>
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

