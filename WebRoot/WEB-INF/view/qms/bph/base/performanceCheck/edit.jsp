<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
jQuery(document).ready(function() {
	loadDepartMent("pdialog", "${vo.departmentNumber}");
});
</script>
<div class="pageContent">
<form method="post" id="formID" action="<c:url value='system/performanceCheck/update.do?navTabId=performanceCheckNav&callbackType=closeCurrent '/>" 
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
			</tr>
			<tr>
				<th>年度</th>
				<td>
					<!-- 原来的onclick="laydate({isym:true, format: 'YYYY-MM'})" -->
	         		<input type="text" id="chekyear"  name=chekyear value="<fmt:formatDate value="${vo.chekyear}"  pattern="yyyy-MM"/>" onclick="laydate({isym:true, format: 'YYYY-MM'})"  readonly="readonly" class="required" />
	         	</td>
			    <th>衡量指标内容</th>
			    <td>
			       <input type="text" name="indexcontent" size="20" value="${vo.indexcontent} "  class="required">
			    </td>
			</tr>
			<tr>
				<th>绩效目标大类</th>
			    <td >
			         <input type="text" id="targetclass"  name="targetclass" size="20" class="required" value="${vo.targetclass }"/>
			    </td>
			    <th>指标类型</th>
			    <td >
			         <input type="text" id="performancecontent"  name="performancecontent" size="20" class="required" value="${vo.performancecontent }" />
			    </td>
			</tr>
			<tr>
				<th>权重</th>
			    <td >
			         <input type="text" id="weight"  name="weight" size="20" class="number" class="required" value="${vo.weight }" />
			    </td>
		 		<th>小于基准</th>
			    <td >
			         <input type="text" id="referencevalue"  name="referencevalue" size="20" class="required" value="${vo.referencevalue }" />
			    </td>
			</tr>	
			<tr>
				<th>基准与目标之间</th>
			    <td >
			         <input type="text" id="median"  name="median" size="20" class="required" value="${vo.median }" />
			    </td>   
			 	<th>大于目标</th>
			    <td >
			         <input type="text" id="targetvalue"  name="targetvalue" size="20" class="required" value="${vo.targetvalue }" />
			    </td>		
			</tr>
			<tr>
				<th>单位</th>
			    <td >
			         <input type="text" id="company"  name="company" size="20" class="required" value="${vo.company }" />
			    </td>   
			    <th>计算公式</th>
			    <td >
			         <input type="text" id="formula"  name="formula" size="20" class="required" value="${vo.formula }" />
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
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
