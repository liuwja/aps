<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

<script type="text/javascript">
</script>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/fault/faultType/saveFaultType.do?navTabId=faultTypeNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<table class="tableFormContent nowrap">
			<tr>
				<th>机型类别：</th>
				<td>
					<select name="productType" style="width: 210px; ">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType}" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
							</c:forEach>
					</select>
				</td>			
	
			</tr>
			<tr>
				<th>故障代码：</th>
				<td>
					<input name="code" id="code" type="text" size="30" class="required"/>
				</td>			
	
			</tr>
			<tr>
				<th>故障名称：</th>
				<td>
	                <input name="name" id="name" type="text" size="30" class="required" />
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



