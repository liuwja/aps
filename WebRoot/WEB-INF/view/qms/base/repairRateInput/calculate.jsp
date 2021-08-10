<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="<c:url value='base/repairCountInput/updateCalculate.do?navTabId=repairRateInputNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<table class="tableFormContent nowrap" >
		<tr>
			<th>机型类别</th>
			<td colspan="3">
				<select name="productType" class="required" style="padding:4px 20px" >
						<option value="">请选择</option>
						<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }">${o.machineType}</option>
						</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>维修起始月份</th>
			<td>
				<input style="padding:4px" type="text" class="required" name="startMonth" placeholder="请输入日期" size="20" 
				onclick="laydate({isym:true, format: 'YYYY-MM'})" value="" readonly="readonly"/>
			</td>
			<th>维修截止月份</th>
			<td>
				<input style="padding:4px" type="text" class="required" name="endMonth" placeholder="请输入日期" size="20" 
				onclick="laydate({isym:true, format: 'YYYY-MM'})" value="" readonly="readonly"/>
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

