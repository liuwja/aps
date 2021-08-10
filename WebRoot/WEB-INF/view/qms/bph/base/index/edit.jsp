<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/index/update.do?navTabId=newIndexNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">
		<tr>  
		   <th>工厂</th>
		   <td><input type="text"  value="${category.factory }" readonly="readonly">
		    </td> 
		   <th>车间</th>
		   <td><input type="text"  value="${category.area }" readonly="readonly"></td> 
		</tr>
		<tr>
		    <th>班组类别</th>
		   <td ><input type="text"  value="${category.category }" readonly="readonly"></td> 
		  
		    <th>考核项目</th>
		    <td >
		       <input type="hidden" name="itemKey" id="itemKey" value="${item.itemKey}"  />
		       <input type="text" name="itemName" id="itemName" value="${item.itemName}"  readonly="readonly"/>
		    </td>
		</tr>
		<tr>     
		    <th>项目代码</th>
		    <td>
		       <input type="text" name="itemCode" id="itemCode" value="${item.itemCode}"  readonly="readonly"/>
		    </td>
		     <th>指标代码</th>
		    <td>
		       <input type="text" name="indexCode" id="indexCode" value="${index.indexCode}" class="required"/>
		    </td>
		</tr>
		<tr>   
		    <th>考核指标</th>
		    <td colspan="3">
		       <input type="hidden" name="indexKey" id="indexKey" value="${index.indexKey}" class="required"/>
		       <input type="text" size="95" name="indexName" id="indexName" value="${index.indexName}" class="required"/>
		    </td>
		   
		</tr>
		<tr>
		   <th>指标得分公式</th>
		   <td colspan="3">
		       <input type="text" size="95" name="indexDescription" id="indexDescription" value="${index.indexDescription }">
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
