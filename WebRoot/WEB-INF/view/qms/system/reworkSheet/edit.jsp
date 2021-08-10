<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
jQuery(document).ready(function() {
	
});
</script>
<div class="pageContent">
<form method="post" id="formID" action="<c:url value='system/reworkSheet/update.do?navTabId=reworkSheetTab&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${reworkSheet.id}">
		<table class="tableFormContent nowrap">
			<tr>
				<th>工厂：</th>
				<td>
					<select name="factory" id="factory" class="required" onchange="">
						<option value="">请选择</option>
						<c:forEach items="${factorys}" var="o">
							<option value="${o.factory}" <c:if test="${reworkSheet.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr> 
			<th>返工/停线单号</th>
		    <td >
		         <input type="text" id="rework_number"  name="rework_number" size="20" class="required" value="${reworkSheet.rework_number}"/>
		    </td>
		</tr>
		<tr>   
		    <th>工时</th>
		    <td >
		         <input type="text" id="workhour"  name="workhour" size="20" value="${reworkSheet.workhour}"/>
		    </td>
		</tr>
		<tr>   
		    <th>耗材费用</th>
		    <td >
		         <input type="text" id="supplies_expense"  name="supplies_expense" size="20" value="${reworkSheet.supplies_expense}" />
		    </td>
		</tr>
		<tr>   
		    <th>金额</th>
		    <td >
		         <input type="text" id="money"  name="money" size="20" class="required" value="${reworkSheet.money}"/>
		    </td>
		</tr>
		<tr>   
		    <th>状态</th>
		    <td >
		         <select name="status" id="status" onchange="">
		         	<option value="开启" <c:if test="${reworkSheet.status eq '开启' }">selected="selected"</c:if>>开启</option>
					<option value="关闭" <c:if test="${reworkSheet.status eq '关闭' }">selected="selected"</c:if>>关闭</option>
				</select>
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
