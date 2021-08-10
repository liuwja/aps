<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/monthAssesment/update.do?navTabId=newMonthAssesmentNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">
		<tr>  
		   <th>班组名称</th>
		   <td><input type="text"  value="${group.groupName }" readonly="readonly">
		    </td> 
		   <th>月份</th>
		   <td>
		   <input type="hidden" name="maKey" value="${month.maKey }">
		   <input type="text" id="monthly"  name="monthly" value="${month.monthly }" readonly="true"/>
		   </td> 
		</tr>
		<tr>
		    <th>考核项目</th>
		    <td colspan="3">
		       <input type="text"  id="itemName" value="${item.itemName}"  readonly="readonly"/>
		    </td> 
		</tr>
		<tr>   
		   
		    <th>项目代码</th>
		    <td>
		       <input type="text"  id="itemCode" value="${item.itemCode}"  readonly="readonly"/>
		    </td>
		     <th>项目比例</th>
		    <td >
		       <input type="text" name="itemScale" id="itemScale" value="${month.itemScale}"  class="required number"/>
		    </td>
		</tr>
		<tr>   
		    <th>考核指标</th>
		    <td >
		       <input type="hidden" name="indexKey" id="indexKey" value="${index.indexKey}" />
		       <input type="text" name="indexName" id="indexName" value="${index.indexName}"  readonly="readonly"/>
		    </td>
		    <th>指标代码</th>
		    <td>
		       <input type="text" name="indexCode" id="indexCode" value="${index.indexCode}"  readonly="readonly"/>
		    </td>
		</tr>
		<tr>   
		    <th>是否关键指标</th>
		    <td>
		       <input type="text" name="indexMainkey" id="indexMainkey" value="${month.indexMainkey}" />
		    </td>
		    <th>指标比例</th>
		    <td >
		       <input type="text" name="indexScale" id="indexScale" value="${month.indexScale}" class="required number"/>
		    </td>
		</tr>
		<tr>   
		    <th>基准值</th>
		    <td>
		       <input type="text" name="baseValue" id="baseValue" value="${month.baseValue}" class="required number"/>
		    </td>
		    <th>目标值</th>
		    <td >
		       <input type="text" name="targetValue" id="targetValue" value="${month.targetValue}" class="required number"/>
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
