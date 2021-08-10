<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
</script>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/basicdata/updateStorehouse.do?navTabId=storehouseNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<input name="id" id="id" type="hidden" value="${storehouse.id}"/>
		<div class="pageFormContent" layoutH="56">
		<table class="tableFormContent nowrap">
			<tr>
				<th>原材料仓库编号：</th>
				<td>
					<input name="storehouseNo" id="storehouseNo" type="text" size="30" class="required" value="${storehouse.storehouseNo}"/>
				</td>			
	
			</tr>
			<tr>
				<th>原材料仓库名称：</th>
				<td>
	                <input name="storehouseName" id="storehouseName" type="text" size="30" class="required" value="${storehouse.storehouseName}"/>
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
