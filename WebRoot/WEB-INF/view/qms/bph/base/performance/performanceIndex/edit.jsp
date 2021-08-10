<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
jQuery(document).ready(function() {
	loadDepartMent("pdialog", "${vo.departmentNumber}");
});
</script>
<div class="pageContent">
<form method="post" id="formID" action="<c:url value='ptm/performanceIndex/update.do?navTabId=performanceIndexNav&callbackType=closeCurrent '/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${vo.id}">
		<table class="tableFormContent nowrap">			
			<tr>
				<th>工厂：</th>
				<td>
					<select name="factoryNumber" id="factoryNumber" onchange="loadDepartMent('pdialog')">
						<option value="">请选择</option>
						<c:forEach items="${factorys}" var="o">
							<option value="${o.factoryNumber }" <c:if test="${vo.factoryNumber eq o.factoryNumber }">selected="selected"</c:if>>${o.factory}</option>
						</c:forEach>
					</select>
				</td>
				<th>部门：</th>
				<td>
					<select id="department" name="departmentNumber">
					</select>
				</td>
				<th>年度</th>
				<td>
	         		<input type="text" id="checkYear"  name="checkYearString" value="<fmt:formatDate value="${vo.checkYear}"  pattern="yyyy"/>" onclick="laydate({isym:true, format: 'YYYY-MM'})"  readonly="readonly" class="required" />
	         	</td>
			</tr>
			<tr>
				<th>绩效目标大类</th>
			    <td >
			         <input type="text" id="performanceTargetClass"  name="performanceTargetClass" size="20" class="required" value="${vo.performanceTargetClass }"/>
			    </td>
			    <th>衡量指标内容</th>
			    <td>
			       <input type="text" name="indexContent" size="20" value="${vo.indexContent}"  class="required">
			    </td>
			    <th>
				绩效类型： 
				</th>
				<%--此处的“其它”判断条件是基于前面只有“指标型”，“项目型”两个，若增加下拉选项，则需要修改条件 --%>
				<td>
					<select name="performanceType" id="performanceType">
						<option value="指标型" <c:if test="${vo.performanceType eq '指标型' }">selected="selected"</c:if>>指标型</option>
						<option value="项目型" <c:if test="${vo.performanceType eq '项目型' }">selected="selected"</c:if>>项目型</option>
						<option value="其它" <c:if test="${vo.performanceType ne '指标型' and vo.performanceType ne '项目型' }">selected="selected"</c:if>>其它</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>指标类型</th>
			    <td id="changeTargetType">
			    	<c:if test="${vo.performanceType eq '指标型' }">
			    		<select name="indexType" id="indexType">
							<option value="望大型" <c:if test="${vo.indexType eq '望大型' }">selected="selected"</c:if>>望大型</option>
							<option value="望小型" <c:if test="${vo.indexType eq '望小型' }">selected="selected"</c:if>>望小型</option>
							<option value="望目型" <c:if test="${vo.indexType eq '望目型' }">selected="selected"</c:if>>望目型</option>
							<option value="其它" <c:if test="${vo.indexType ne '望大型' and vo.indexType ne '望小型' and vo.indexType ne '望目型' }">selected="selected"</c:if>>其它</option>
						</select>
			    	</c:if>
			    	<c:if test="${vo.performanceType eq '项目型' }">
			    		<select name="indexType" id="indexType">
							<option value="望目型" <c:if test="${vo.indexType eq '望目型' }">selected="selected"</c:if>>望目型</option>
							<option value="其它" <c:if test="${vo.indexType ne '望目型'}">selected="selected"</c:if>>其它</option>
						</select>
			    	</c:if>
			         <%-- <input type="text" id="indexType"  name="indexType" size="20" class="required" value="${vo.indexType }" /> --%>
			    </td>
				<th>权重</th>
			    <td >
			         <input type="text" id="weight"  name="weight" size="20" class="number" class="required" value="${vo.weight }"  style="width:50px;display:inline;"/><span style="font-weight:bold;">%</span>
			    </td>
			    <th>单位</th>
			    <td >
			         <input type="text" id="company"  name="company" size="20" class="required" value="${vo.company }" />
			    </td>   
			</tr>	
			<tr>
				<th>计算公式</th>
			    <td >
			         <input type="text" id="formula"  name="formula" size="20" class="required" value="${vo.formula }" />
			    </td> 
			</tr>
			<tr id="isVisible">
				<th>小于基准</th>
			    <td >
			         <input type="text" id="referenceValue"  name="referenceValue" size="20" class="required" value="${vo.referenceValue }" />
			    </td>
				<th>基准与目标之间</th>
			    <td >
			         <input type="text" id="middleValue"  name="middleValue" size="20" class="required" value="${vo.middleValue }" />
			    </td>   
			 	<th>大于目标</th>
			    <td >
			         <input type="text" id="targetValue"  name="targetValue" size="20" class="required" value="${vo.targetValue }" />
			    </td>		
			</tr>
			<tr>
				<th>修改原因</th>
			    <td>
			         <input type="text" id="updateReason"  name="updateReason" size="20" class="required" value="" />
			    </td>   
			</tr>
		</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">修改</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#performanceType").change(function(){
		var targetType = $(this).children('option:selected').val();
		console.log("改变了下拉框");
		//alert($(this).children('option:selected').val());
		if(targetType == "项目型"){
			$("#changeTargetType").children().remove();
			$("#changeTargetType").append("<select name='indexType' id='indexType'><option value='望目型'>望目型</option></select>");
			$("#isVisible").children().remove();
			$("#isVisible").append("<th>项目型</th><td colspan='3'>绩效权重*个人获得的评价等级对应的绩效百分比<br/>(A,100%; B+,85%; B,75%; C:0%)</td>");
			$("#isVisible").append("<input type='hidden' name='referenceValue' value='B'/><input type='hidden' name='middleValue' value='B+'/><input type='hidden' name='targetValue' value='A'/>");
		}else if(targetType == "指标型"){
			$("#changeTargetType").children().remove();
			$("#isVisible").children().remove();
			$("#changeTargetType").append("<select name='indexType' id='indexType'><option value='望大型'>望大型</option><option value='望小型'>望小型</option><option value='望目型'>望目型</option></select>");
			$("#isVisible").append("<th>小于基准</th><td><input type='text' id='referenceValue'  name='referenceValue' size='20' class='required' /></td><th>基准与目标之间</th><td ><input type='text' id='middleValue'  name='middleValue' size='20' class='required' /></td><th>大于目标</th><td ><input type='text' id='targetValue'  name='targetValue' size='20' class='required' /></td>");
		}else{
			console.log("暂时什么也不做！");
		}
	});
});
</script>