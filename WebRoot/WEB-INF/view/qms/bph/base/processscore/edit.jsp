<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/processscore/update.do?navTabId=processScoreNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${vo.id}">
		<table class="tableFormContent nowrap">
		   <tr >
			<jsp:include page="../../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="0" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="0" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="1" name="thClass"/>
			</jsp:include>
		   	<td style="background: #F7F9FC;">指标名称:</td>
		    <td>
		        <input type="text" name="indexContent" id="indexContent" value="${vo.indexContent}" class="reqiured"/>
		    </td>  	
		</tr>
		<tr> 
		    <td style="background: #F7F9FC;">指标描述:</td>
		    <td>
		        <input type="text" name="content" id="content" value="${vo.content}" class="required"/>
		    </td>    
		    <td style="background: #F7F9FC;">指标编号:</td>
		    <td>
		       <input type="text" name="indexCode" id="indexCode" value="${vo.indexCode}" class="required"/>
		    </td>
		  
		</tr>
		<tr> 
		     <td style="background: #F7F9FC;">得分类型:</td>
		    <td>
		        <input type="radio" name="scoreType" id="scoreType" value="1" <c:if test="${vo.scoreType == 1 }">checked="checked"</c:if>/>加分&nbsp;
		        <input type="radio" name="scoreType" id="scoreType" value="0" <c:if test="${vo.scoreType == 0 }">checked="checked"</c:if>/>扣分
		    </td>  
		    <td style="background: #F7F9FC;">分数</td>
		    <td>
		       <input type="text" name="score" id="score" class="number" value="${vo.score}" class="required"/>
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
