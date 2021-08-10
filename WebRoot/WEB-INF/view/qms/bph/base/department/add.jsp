<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='per/department/save.do?navTabId=perdtNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">	
			<tr>
				<th>工厂</th>
				<td>
					<select name="factoryNumber" id="factoryNumber">
						<option value="">请选择</option>
						<c:forEach items="${factorys}" var="o">
							<option value="${o.factoryNumber }" <c:if test="${dt.factoryNumber eq o.factoryNumber }">selected="selected"</c:if>>${o.factory}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
   			<tr>
   				<th>部门名称</th>
			    <td >
			         <input type="text" id="targetclass"  name="departmentName"  class="required"/>
			    </td>
		    </tr>
		    <tr>
		    	 <th>部门编码</th>
			    <td>
			       <input type="text" name="departmentNumber"  size="20"  class="required">
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
