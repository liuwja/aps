<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
</script>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/repairRate/updateMachine.do?navTabId=repairRateListNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	<input name="id" id="id" type="hidden" value="${repairRate.id}"/>
		<div class="pageFormContent" layoutH="56">
		<table class="tableFormContent nowrap">
			<tr>
				<th>机型类别：</th>
				<td>
					<!--  <input name="factoryNo" id="factoryNo" type="text" size="30" class="required" value="${factory.factoryNo}"/>-->
					<select style="width: 210px; ">
		        <option value="油烟机">油烟机</option>
		        <option value="灶具">灶具</option>
		        <option value="热水器">热水器</option>
		        <option value="消毒柜">消毒柜</option>
		        <option value="烤箱">烤箱</option>
		        <option value="蒸箱">蒸箱</option>
		        <option value="微波炉">微波炉</option>
		      </select>
				</td>			
	
			</tr>
			<tr>
				<th>维修截止日期：</th>
				<td>
	                <input name="yearMon" id="yearMon" type="text" size="30" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${repairRate.yearMon}"/>
	            </td>
			</tr>
			<tr>
				<th>累计百台维修率：</th>
				<td>
	                <input name="hundredRepairRate" id="hundredRepairRate" type="text" size="30" class="required number" max ="1" min="0" value="${repairRate.hundredRepairRate}"/>
	            </td>
			</tr>
<!-- 			<tr> -->
<!-- 				<th>基准百台维修率：</th> -->
<!-- 				<td><input name="referenctRepairRate" id="referenctRepairRate" type="text" size="30" class="required number" max ="1" min="0" value="${repairRate.referenctRepairRate}" /></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<th>目标百台维修率：</th> -->
<!-- 				<td><input name="targetRepairRate" id="targetRepairRate" type="text" size="30" class="required number" max ="1" min="0" value="${repairRate.targetRepairRate}" /></td> -->
<!-- 			</tr> -->
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

