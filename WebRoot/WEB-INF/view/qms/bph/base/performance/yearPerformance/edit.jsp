<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='ptm/yearPerformanceSet/update.do?navTabId=yearPerformancelistNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${vo.id}">
		<input type="hidden" name="factoryNumber" value="${vo.factoryNumber}">
		<input type="hidden" name="departmentNumber" value="${vo.departmentNumber}">
		<table class="tableFormContent nowrap">	
		<tr>
			<th>工厂名称</th>
		    <td >
		         <input type="text" id="factoryName"  name="factoryName" value="${ vo.factoryName }" size="20" class="required" readonly="readonly"/>
		    </td>
			<th>部门名称</th>
		    <td >
		         <input type="text" id="departmentName"  name="departmentName" value="${ vo.departmentName }" size="20" class="required" readonly="readonly"/>
		    </td>
		    <th>年度</th>
		    <td >
		         <input type="text" id="checkYear"  name="" value="<fmt:formatDate value='${vo.checkYear}' pattern='yyyy'/>" size="20" class="required" readonly="readonly"/>
		    </td>
		</tr>
		<tr>   
			<th>绩效目标大类</th>
		    <td >
		         <input type="text" id="performanceTargetClass"  name="performanceTargetClass" value="${ vo.performanceTargetClass }" size="20" class="required" readonly="readonly"/>
		    </td>
		    <th>绩效类型</th>
		    <td >
		         <input type="text" id="performanceType"  name="performanceType" value="${ vo.performanceType }" size="20" class="required" readonly="readonly"/>
		    </td>
		   	<th>衡量指标内容</th>
		    <td>
		       <input type="text" id="indexContent" name="indexContent" value="${ vo.indexContent }" readonly="readonly" size="20"  class="required">
		    </td>	
		</tr>
		<tr>  
			<th>指标类型</th>
		    <td >
		         <input type="text" id="indexType"  name="indexType" value="${ vo.indexType }" readonly="readonly" size="20" class="required" />
		    </td> 		    
		    <th>权重</th>
		    <td >
		         <input type="text" id="weight"  name="weight" value="${ vo.weight }" readonly="readonly" size="20" class="required number"  style="width:50px;display:inline;"/><span style="font-weight:bold;">%</span>
		    </td>
		    <!-- 新增一个单位字段 -->
		    <th>单位</th>
		    <td >
		         <input type="text" id="company"  name="company" value="${ vo.company }" readonly="readonly" size="20" class="required" />
		    </td>
		</tr>					
		<tr>
			<th>计算公式</th>
		    <td >
		         <input type="text" id="formula"  name="formula" value="${ vo.formula }" readonly="readonly" size="20" class="required" />
		    </td> 
		</tr>
		<tr id="isVisible">
			<th>上年度实际值</th>
		    <td >
		         <input type="text" id="lastYearRealityValue"  name="lastYearRealityValue" value="${vo.yearPerformance.lastYearRealityValue }" size="20" class="required" />
		    </td>
		    <th>上半年基准值</th>
		    <td >
		         <input type="text" id="firstYearReferenceValue"  name="firstYearReferenceValue" value="${vo.yearPerformance.firstYearReferenceValue }" size="20" class="required"/>
		    </td>
		    <th>本年度基准值</th>
		    <td >
		         <input type="text" id="referenceValue"  name="referenceValue" value="${vo.yearPerformance.referenceValue }" size="20" class="required" />
		    </td>
		    
		</tr>
		<tr>		
			<th>上半年目标值</th>
		    <td >
		         <input type="text" id="firstYearTargetValue"  name="firstYearTargetValue" value="${vo.yearPerformance.firstYearTargetValue }" size="20" class="required"/>
		    </td>
		    <th>下半年目标值</th>
		    <td >
		         <input type="text" id="secondYearTargetValue"  name="secondYearTargetValue" value="${vo.yearPerformance.secondYearTargetValue }" size="20" class="required"/>
		    </td>
		    <th>本年度目标值</th>
		    <td >
		         <input type="text" id="targetValue"  name="targetValue" value="${ vo.yearPerformance.targetValue}" size="20" class="required" />
		    </td>
		</tr>
		<tr>
		    <th>修改原因</th>
		    <td >
		         <input type="text" id="updateReason"  name="updateReason" value="" size="20" class="required"/>
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
	var performanceType = $("#performanceType").val();
	if(performanceType == '项目型'){
		$("#isVisible").children().remove();
		$("#isVisible").append("<input type='hidden' name='lastYearRealityValue' value='/'/><input type='hidden' name='firstYearReferenceValue' value='/'/><input type='hidden' name='referenceValue' value='/'/>");
	}
});
</script>