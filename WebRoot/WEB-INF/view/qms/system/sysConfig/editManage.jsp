<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/sysconfig/updateManage.do?navTabId=manageNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" id="id" value="${manage.id}">
		<table class="tableFormContent nowrap">
			<tr >
                <th>属性代码：</th>
                <td><input name="code" type="text" size="30" class="required" value="${manage.code}" /></td>
            </tr>
            <tr >
                <th>属性名称：</th>
                <td><input name="codeName" type="text" size="30" class="required" value="${manage.codeName}" /></td>
            </tr>
            <tr>
                <th>属性值：</th>
                <td><input name="codeValue" type="text" size="30" class="required" value="${manage.codeValue}" /></td>         
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
