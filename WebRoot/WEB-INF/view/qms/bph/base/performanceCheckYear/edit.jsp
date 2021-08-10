<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/performanceCheckYear/update.do?navTabId=performanceCheckYearNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${vo.id}">
		<table class="tableFormContent nowrap">
		   <tr >
			<th>工厂</th>
			<td><select id="factory" name="factory" >
			      <option value="">请选择</option>
		          <option value="燃气工厂">燃气工厂</option>
		          <option value="电器工厂">电器工厂</option>
		          <option value="电器二厂">电器二厂</option>
		        </select>
		         <script type="text/javascript">
						$("#factory",$.pdialog.getCurrent()).val("${vo.factory}"); 
			   </script>  
		    </td>
		     <th>考核指标</th>
		    <td>
		       <input type="text" name="checkIndexName" size="20"  class="required" value="${vo.checkIndexName }">
		    </td>	
		</tr>
		<tr>   
		   <th>年度</th>
		    <td >
		         <input type="text" id="queryYear"  name="queryYear" value="<fmt:formatDate value="${vo.checkYear}" type="date" pattern="yyyy"/>" onclick="laydate({isym:true, format: 'YYYY'})"  readonly="true" class="required" />
		    </td>
		    <th>年度基准</th>
		    <td >
		         <input type="text" id="baseValueYear"  name="baseValueYear" size="20" class="required number" value="${vo.baseValueYear }"/>
		    </td>
		   
		</tr>
		<tr>   
		     <th>年度目标</th>
		    <td >
		         <input type="text" id="targetValueYear"  name="targetValueYear" size="20" class="required number" value="${vo.targetValueYear }"/>
		    </td>
		    <th>上半年目标</th>
		    <td >
		         <input type="text" id="targetValueHalfyear"  name="targetValueHalfyear" size="20" class="number" value="${vo.targetValueHalfyear }"/>
		    </td>
		</tr>
        <tr>   
		    <th>年度下降率</th>
		    <td colspan="3">
		         <input type="text" id="depressRateYear"  name="depressRateYear" size="20" class="number" value="${vo.depressRateYear }"/>
		    </td>	    
		</tr>
		
		<tr>   
		 <th>小于基准</th>
		    <td >
		         <input type="text" id="referencevalue"  name="referencevalue" size="20" class="required" />
		    </td>
		    <th>基准与目标之间</th>
		    <td >
		         <input type="text" id="median"  name="median" size="20" class="required number" />
		    </td>
		    <th>大于目标</th>
		    <td >
		         <input type="text" id="targetvalue"  name="targetvalue" size="20" class="required" />
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
