<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });
     function getprarea() {
	var url = "<c:url value='system/productionreturn/getarea.do' />";
	$("#preditArea").load(url,{factory: $("#factory").val()});   
	} 
</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/productionreturn/update.do?navTabId=productionReturnNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${group.id}">
		<table class="tableFormContent nowrap">
          <tr >
			<th>工厂</th>
			<td><select id="factory" name="factory" class="required" onchange="getprarea()">
			     <option value="">--请选择--</option>
		         <option value="燃气工厂">燃气工厂</option>
		         <option value="电器工厂">电器工厂</option>
		        </select>
		       <script type="text/javascript">
						$("#factory",$.pdialog.getCurrent()).val("${group.factory}"); 
			   </script> 
		    </td>
		    <th>车间</th>
		    <td id="preditArea">
		        <select id="area" name="area" class="required">
		             <option value="" >--请选择--</option>
		           <c:forEach items="${area}" var="vo">
		               <option value="${vo.area}">${vo.area}</option>
		           </c:forEach>
		        </select>
		       <script type="text/javascript">
						$("#area",$.pdialog.getCurrent()).val("${group.area}"); 
			   </script> 
		    </td>   
		   	
		</tr>
		
		<tr> 
		     <th>班组类别</th>
		       <td>
		         <select id="shiftgroupCategory" name="shiftgroupCategory" class="required">
		         <option value="">--请选择--</option>
		         <option value="冲压班组">冲压班组</option>
		         <option value="组装班组">组装班组</option>
		         <option value="点焊">点焊</option>
		         <option value="喷涂班组">喷涂班组</option>
		         <option value="精加工">精加工</option>
		         <option value="WOMEN">IQC</option>
		         <option value="OQC">OQC</option>
		         </select>
		        <script type="text/javascript">
				   $("#shiftgroupCategory",$.pdialog.getCurrent()).val("${group.shiftgroupCategory}"); 
			    </script> 
		    </td>	  
		    <th>物料编号</th>
		    <td><input type="text" name="materialTag" value= "${group.materialTag}" class="required number"/>
		    </td>
		    
		</tr>
		<tr>
		   <th>物料名称</th>
		    <td><input name="materialName" type="text"  value= "${group.materialName}" class="required"/></td>
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
