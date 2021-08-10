<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
	    
    });
</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/exceptionEnter/save.do?navTabId=exceptionEnterNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">	
		<jsp:include page="../../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="doubleLine" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="1" name="fagroup"/>
					<jsp:param value="true" name="isRequired"/>
					<jsp:param value="1" name="thClass"/>
					<jsp:param value="1" name="isColspan"/>
		</jsp:include>
		<tr>   
		     <td style="background: #F7F9FC;">异常类型:</td>
		    <td colspan="3">
		       <input type="text" name="exceptionType" size="30"  class="required">
		    </td>		
		</tr>
		<tr>
		    <td style="background: #F7F9FC;">异常内容:</td>
		    <td colspan="3">
		       <textarea  name="exceptionName"  cols="80"  rows="2" class="required"/>
		    </td>
		    		
		</tr>
		<tr>
		    <td style="background: #F7F9FC;">发生时间:</td>
		    <td colspan="3">
		        <input type="text" id="startTime"  name="startTime" placeholder="请输入日期" onclick="laydate()"  readonly="true" class="required"/>
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
