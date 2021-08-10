<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='per/month/update.do?navTabId=perMonthNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">
		<tr>  
	
		   <th>上月累计目标值</th>
		   <td>
		   <input type="hidden" name="monthKey" value="${month.monthKey }">
		   <input type="text"   name="accumulatedmonth" value="${month.accumulatedmonth }" class="required number"/>
		   </td> 
		    <th>上月累计实际值</th>
		    <td colspan="3">
		       <input type="text"  id="lastmonthactual" value="${month.lastmonthactual}"  class="required number"/>
		    </td> 
		</tr>
		<tr>   
		   
		    <th>当月实际值</th>
		    <td>
		       <input type="text"  id="monthreality" value="${month.monthreality}" class="required number" />
		    </td>
		     <th>当月累计目标值</th>
		    <td >
		       <input type="text" name="targetvaluemonth"  value="${month.targetvaluemonth}"  class="required number"/>
		    </td>
		</tr>
		<tr>   
		    <th>当月累计实际值</th>
		    <td >
		       <input type="text" name="accumumonth"  value="${month.accumumonth}"  class="required number"/>
		    </td>
		    <th>下月挑战目标</th>
		    <td>
		       <input type="text" name="record" id="record" value="${month.record}"  class="required number"/>
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
