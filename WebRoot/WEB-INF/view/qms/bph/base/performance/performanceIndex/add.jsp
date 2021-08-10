<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>



<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='ptm/performanceIndex/save.do?navTabId=performanceIndexNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">	
		<tr>
			<th>工厂</th>
			<td>
				<select name="factoryNumber" id="factoryNumber" onchange="loadDepartMent('pdialog')">
					<option value="">请选择</option>
					<c:forEach items="${factorys}" var="o">
						<option value="${o.factoryNumber }">${o.factory}</option>
					</c:forEach>
				</select>
			</td>
			<th>部门</th>
			<td>
				<select id="department" name="departmentNumber">
				</select>
			</td>
		 	<th>年度</th>
			<td colspan="3">
				<input type="text" class="required" name="checkYearString" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})" readonly="readonly"/>
			</td>
		</tr>
		<tr>   
			<th>绩效目标大类</th>
		    <td >
		         <input type="text" id="performanceTargetClass"  name="performanceTargetClass" size="20" class="required"/>
		    </td>
		   	<th>衡量指标内容</th>
		    <td>
		       <input type="text" id="indexContent" name="indexContent" size="20"  class="required">
		    </td>		
		    <th>
				绩效类型： 
			</th>
			<td>
				<select name="performanceType" id="performanceType">
					<option value="指标型">指标型</option>
					<option value="项目型">项目型</option>
					<option value="其它">其它</option>
				</select>
			</td>
		</tr>
		<tr> 
			<th>指标类型</th>
		    <td id="changeTargetType">
		         <select name="indexType" id="indexType">
					<option value="望大型">望大型</option>
					<option value="望小型">望小型</option>
					<option value="望目型">望目型</option>
				</select>
		    </td>  		    
		    <th>权重</th>
		    <td >
		         <input type="text" id="weight"  name="weight" size="20" class="required number" style="width:50px;display:inline;"/><span style="font-weight:bold;">%</span>
		    </td>
		    <!-- 新增一个单位字段 -->
		    <th>单位</th>
		    <td >
		         <input type="text" id="company"  name="company" size="20" class="required" />
		    </td>
		</tr>
		<tr>
			<th>计算公式</th>
		    <td >
		         <input type="text" id="formula"  name="formula" size="20" class="required" />
		    </td>
		</tr>					
		<tr id="isVisible">   
		 	<th>小于基准</th>
		    <td >
		         <input type="text" id="referenceValue"  name="referenceValue" size="20" class="required" />
		    </td>
		    <th>基准与目标之间</th>
		    <td >
		         <input type="text" id="middleValue"  name="middleValue" size="20" class="required" />
		    </td>
		    <th>大于目标</th>
		    <td >
		         <input type="text" id="targetValue"  name="targetValue" size="20" class="required" />
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
