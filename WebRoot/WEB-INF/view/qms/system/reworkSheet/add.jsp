<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/reworkSheet/save.do?navTabId=reworkSheetTab&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">	
		<tr>
			<th>工厂</th>
			<td>
				<select name="factory" id="factory" class="required" onchange="">
					<option value="">请选择</option>
					<c:forEach items="${factorys}" var="o">
						<option value="${o.factory }">${o.factory}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr> 
			<th>返工/停线单号</th>
		    <td >
		         <input type="text" id="rework_number"  name="rework_number" size="20" class="required"/>
		    </td>
		</tr>
		<tr>   
		    <th>工时</th>
		    <td >
		         <input type="text" id="workhour"  name="workhour" size="20" />
		    </td>
		</tr>
		<tr>   
		    <th>耗材费用</th>
		    <td >
		         <input type="text" id="supplies_expense"  name="supplies_expense" size="20" />
		    </td>
		</tr>
		<tr>   
		    <th>金额</th>
		    <td >
		         <input type="text" id="money"  name="money" size="20" class="required" />
		    </td>
		</tr>
		<tr>   
		    <th>状态</th>
		    <td >
		         <select name="status" id="status" onchange="">
		         	<option value="开启">开启</option>
					<option value="关闭">关闭</option>
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
<script>

</script>
