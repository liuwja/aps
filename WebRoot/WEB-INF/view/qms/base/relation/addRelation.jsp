<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
</script>

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/relation/saveRelation.do?navTabId=relationListNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<table class="tableFormContent nowrap">
			<tr>
				<th>区域（CRM服务中心）：</th>
				<td>
					<input name="crm" id="crm" type="text" size="30" class="required"/>
				</td>			
	
			</tr>
			<tr>
				<th>分公司仓库（MES）：</th>
				<td>
	                <input name="mes" id="mes" type="text" size="30" class="required" />
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

