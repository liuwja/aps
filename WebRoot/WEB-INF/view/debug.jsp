<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    	
    });
    function updatePrivilege()
    {
    	alert('eee');
    }
</script>
<div class="pageContent">
<form method="post" id="formID" action="<c:url value='system/privilege/updateAllPrivilege.do'/>" 
class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	<div class="formBar">
	    <ul>
	        <li>
	            <div class="buttonActive"><div class="buttonContent"><button type="submit" >更新权限</button></div></div>
	        </li>
	    </ul>
	</div>  
</form>

</div>
