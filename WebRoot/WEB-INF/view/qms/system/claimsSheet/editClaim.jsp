<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

<div class="pageContent" id="addDown1">
	<form method="post" id="formID" action="<c:url value='system/claimsSheet/save.do?navTabId=claimsSheetTab&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${claimsSheet.id}">
		<table class="tableFormContent nowrap">	
		<tr>
			<th>类别：</th>
			<td>
				<input type="text" readonly="readonly" size="20" name="claims_type" id="claims_type" class="required" value="${claimsSheet.claims_type}" />
			</td>
		 	<th>返工/停线单号：</th>
			<td>
				<input id="reworkGroup_reworkNumber" name="rework_number" value="${claimsSheet.rework_number}" type="text" size="20" class="required" readonly="true" />
				<input id="reworkGroup_reworkId" name="rework_id" value="${claimsSheet.rework_id}" type="hidden" size="20" readonly="true" />
				<a class="btnLook" target="dialog" href="system/claimsSheet/reworkList.do" width="800" height="600" lookupGroup="reworkGroup">返工/停线单选择</a>	
				<span class="info">(查找)</span>
			</td>
		</tr>
		<tr>   

		<th>责任部门：</th>
		    <td>
		       <input type="text" id="duty_depart" name="duty_depart" value="${claimsSheet.duty_depart}" size="20" />
		    </td>	
		<th>责任比例：</th>
		    <td >
		         <input type="text"  id="duty_proportion" value="${claimsSheet.duty_proportion}" name="duty_proportion" size="20" />
		    </td>
		</tr>
		<tr>
			<th>原因：</th>
			<td colspan="3" style="padding-bottom: 5px;padding-top: 5px"><textarea name="rework_reason" id="rework_reason" cols="70">${claimsSheet.rework_reason}</textarea></td>
		</tr>
		<th>实际返工数：</th>
		    <td>
		       <input type="text" id="rework_count" name="rework_count" size="20" value="${claimsSheet.rework_count}" />
			</td>	
		<th>产品线：</th>
			<td>
		    	<select name="product_category" id="product_category" class="required" onchange="">
					<option value="">请选择</option>
					<c:forEach items="${product_categorys}" var="o">
						<option value="${o }" <c:if test="${o eq claimsSheet.product_category }">selected="selected"</c:if> >${o}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>   
		<th>索赔/处罚单号：</th>
		    <td>
		       <input type="text" id="claims_number" name="claims_number" value="${claimsSheet.claims_number}" size="20"  class="required" />
		    </td>	
		<th>供应商：</th>
		    <td>
			<input id="supplierGroup_supplierName" name="claims_supplier" value="${claimsSheet.claims_supplier}" type="text" size="20" class="required" readonly="true" />
			<input id="supplierGroup_supplierNumbers" name="claims_supplierNumber" value="" type="hidden" size="20" readonly="true" />
			<a class="btnLook" target="dialog" href="system/claimsSheet/supplierList.do" width="800" height="600" lookupGroup="supplierGroup">供应商选择</a>	
			<span class="info">(查找)</span>
        	</td>
		</tr>
		<tr>
		<tr>
			<th>索赔/处罚原因：</th>
			<td colspan="3" style="padding-bottom: 5px;padding-top: 5px"><textarea name="claims_reason" id="claims_reason" cols="70">${claimsSheet.claims_reason}</textarea></td>
		</tr>
		<tr>   

		<th>金额：</th>
		    <td>
		       <input type="text" id="claims_amount" name="claims_amount" size="20" value="${claimsSheet.claims_amount}" class="required" />
		    </td>	
		<th>申请人：</th>
		    <td>
			<input id="applicantGroup_userDescription" name="claims_applicant" value="${claimsSheet.claims_applicant}" type="text" size="20" class="required" readonly="true" />
			<input id="applicantGroup_userName" name="claims_applicantName" value="" type="hidden" size="20"  readonly="true" />
			<a class="btnLook" target="dialog" href="system/claimsSheet/userList.do" width="650" height="500" lookupGroup="applicantGroup">人员选择</a>	
			<span class="info">(查找)</span>
        	</td>
		</tr>
		<th>登记人：</th>
		    <td>
		       <input type="text" id="registrar" name="registrar" value="${claimsSheet.registrar}" size="20" readonly="true" class="required" />
		    </td>	
		<th>是否回签：</th>
		    <td colspan="3">
				<input type="radio" value="是" group="isResponse" name="is_response" id="yes" class="required" <c:if test="${claimsSheet.is_response eq '是' }">checked="checked"</c:if> />是
				<input type="radio" value="否" group="isResponse" name="is_response" id="no" <c:if test="${claimsSheet.is_response eq '否' }">checked="checked"</c:if> />否
			</td>
		</tr>
		<tr>
			<th>未回签原因：</th>
			<td colspan="3" style="padding-bottom: 5px;padding-top: 5px"><textarea name="response_reason" id="response_reason" cols="70">${claimsSheet.response_reason}</textarea></td>
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
