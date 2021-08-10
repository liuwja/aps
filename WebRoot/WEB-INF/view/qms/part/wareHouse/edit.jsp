<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" action="<c:url value='quality/wareHouse/update.do?navTabId=wareHouseTab&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="wareRefKey" value="${ware.wareRefKey}">
		<table class="tableFormContent nowrap">
		  <tr >
			<th>仓库编号</th>
			<td><input type="text" size="15" name="wareNumber" class="required" value="${ware.wareNumber }">
		    </td>
	    </tr>
	    <tr>	    
		    <th>仓库名称</th>
		    <td>
		       <input type="text" name="wareName" size="15" class="required" value="${ware.wareName} ">
		    </td>		
		</tr>
		<tr>   
		    <th>机型</th>
		    <td>
		       <input type="text" name="productName"  class="required " size="15" value="${ware.productName }"/>
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
