<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/item/update.do?navTabId=newItemNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">
		<tr>  
		   <th>工厂</th>
		   <td><input type="text" name="factory" value="${category.factory }" readonly="readonly">
		       <input type="hidden" name="gcKey" value="${category.gcKey }">
		    </td> 
		   <th>车间</th>
		   <td><input type="text" name="area" value="${category.area }" readonly="readonly"></td> 
		</tr>
		<tr>
		    <th>班组类别</th>
		   <td colspan="3"><input type="text" name="category" value="${category.category }" readonly="readonly"></td> 
		</tr>
		<tr>   
		    <th>考核项目</th>
		    <td >
		       <input type="hidden" name="itemKey" id="itemKey" value="${item.itemKey}" class="required"/>
		       <input type="text" name="itemName" id="itemName" value="${item.itemName}" class="required"/>
		    </td>
		    <th>项目代码</th>
		    <td>
		       <input type="text" name="itemCode" id="itemCode" value="${item.itemCode}" class="required"/>
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
