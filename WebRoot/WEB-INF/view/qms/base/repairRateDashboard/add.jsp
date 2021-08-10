<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta charset="UTF-8">

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/repairRateDashboard/save.do?navTabId=repairRateDashboardListNav&callbackType=closeCurrent'/>" 
		class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<table class="tableFormContent nowrap">
				<tr>
					<th>机型类别：</th>
					<td>
						<select name="productType" id="productType" style="width: 210px;">
							<c:forEach items="${productTypes}" var="o">
								<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
						</select>
					</td>			
				</tr>
				<tr>
					<th>维修日期：</th>
					<td>
		                <input name="month" id="month" type="text" size="30" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" />
		            </td>
				</tr>
				<tr>
					<th>现状：</th>
					<td>
		                <input name="hundredRepairRate" id="hundredRepairRate" type="text" size="30" class="required number" max ="1" min="0" />
		            </td>
				</tr>
				<tr>
					<th>基准：</th>
					<td><input name="referenctRepairRate" id="referenctRepairRate" type="text" size="30" class="required number" max ="1" min="0" /></td>
				</tr>
				<tr>
					<th>目标：</th>
					<td><input name="targetRepairRate" id="targetRepairRate" type="text" size="30" class="required number" max ="1" min="0" /></td>
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