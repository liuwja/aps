<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='per/monthly/update.do?navTabId=perMonthlyNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">
		<tr>  	
		   <th>部门</th>
		   <td>	
		   <input type="text"   name="factoryNumber" value="${group.department }" readonly="readonly"/>
		   </td> 
		   <th>绩效大类</th>
		   <td>
		   <input type="text" name="targetclass" value="${group.targetclass }" readonly="readonly"></td>
		   
		   <th>衡量指标内容</th>
		   <td >
		   <input type="text" name="indexcontent"  value="${group.indexcontent }" readonly="readonly"></td>  
		</tr>
		<tr>  	
		   <th>绩效类型</th>
		   <td>	
		   <input type="text"   name="performancecontent" value="${group.performancecontent }" readonly="readonly"/>
		   </td> 
		   <th>本年基准值</th>
		   <td>
		   <input type="text" name="referencevalue" value="${item.referencevalue }" readonly="readonly"></td>
		   
		   <th>本年目标值</th>
		   <td >
		   <input type="text" name="yeartargetvalue"  value="${item.yeartargetvalue }" readonly="readonly"></td>  
		</tr>
		
		<tr>
	 <th>月基准值</th>
		    <td >
		    <input type="text" name="basevalue" value="${index.basevalue}" readonly="readonly"></td>
  
		    <th>月目标值</th>
		    <td ><input type="text" name="targetvalue"  value="${index.targetvalue}" readonly="readonly"></td>
		    
		    <th>月份：</th>
		    <td ><input type="text" name="month"  value="${index.month}" readonly="readonly"></td>
		</tr>
		<tr>   
		   
		    <th>当月实际值</th>
		    <td>
		    	<input type="hidden" name="monthKey" value="${month.monthKey }">
		       <input type="text"  name="monthreality" value="${month.monthreality}" class="required number" />
		    </td>

		    <th>当月累计实际值</th>
		    <td >
		       <input type="text" name="accumumonth"  value="${month.accumumonth}"  class="required number"/>
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
