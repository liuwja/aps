<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
</script>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/basicdata/saveMachine.do?navTabId=machineNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<table class="tableFormContent nowrap">
			<tr>
				<th>机型类别：</th>
				<td>
					<input name="machineType" id="machineType" type="text" size="30" class="required"/>
				</td>			
	
			</tr>
			<tr>
				<th>维修截止日期：</th>
				<td>
	                <input name="yearMon" id="yearMon" type="text" size="30" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true"/>
	            </td>
			</tr>
			<tr>
				<th>累计百台维修率：</th>
				<td>
	                <input name="hundredRepairRate" id="hundredRepairRate" type="text" size="30" class="required"/>
	            </td>
			</tr>
			<tr>
				<th>录入人：</th>
				<td>
	                <input name="userName" id="userName" type="text" size="30" class="required"/>
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

