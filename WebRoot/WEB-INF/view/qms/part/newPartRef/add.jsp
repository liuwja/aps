<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
        //jQuery("#formID").validationEngine();
    });

</script>
<div class="pageContent">
	<form method="post" action="<c:url value='quality/newPartRef/save.do?navTabId=newPartRefTab&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">
		<tr >
			<th>旧物料编码</th>
			<td><input type="text" size="15" name="oldPartNumber" class="required">
		    </td>
	    </tr>
	    <tr>	    
		    <th>新物料编码</th>
		    <td>
		       <input type="text" name="newPartNumber" size="15" class="required">
		    </td>		
		</tr>
		<tr>   
		    <th>物料名称</th>
		    <td>
		       <input type="text" name="partName"  value="" class="required " size="15"/>
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

