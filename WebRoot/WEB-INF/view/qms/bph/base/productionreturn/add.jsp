<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
        //jQuery("#formID").validationEngine();
    });
    function getArea() {
	var url = "<c:url value='system/productionreturn/getarea.do' />";
	$("#productarea").load(url,{factory: $("#factory").val()});   
	}

    function getptCategory(){
    	var txt = "";
    	var select = $("#shiftgroupCategory");
    	select.empty();
    	select.append("<option value=''>请选择</option>"); 
    	var url = "<c:url value='system/checkIndex/getCategory.do' />";
    	var factory = $('#factory').val();
    	var area = $('#productarea').val();
    	$.post(url,{factory:factory,area : area},function(data){
    		var i = 0; 		
    		for(i =0;i<data.cateList.length;i++){
    			txt = txt+"<option value="+data.cateList[i].shiftGroupCategory +">"+data.cateList[i].shiftGroupCategory+"</option>";

    		}
    		//alert(data.cateList[0].shiftGroupCategory);
    		$("#shiftgroupCategory").append(txt);  		
    	});
    	
    }
</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/productionreturn/save.do?navTabId=productionReturnNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">
		<tr >
			<th>工厂</th>
			<td><select id="factory" name="factory" class="required" onchange="getArea()">
			    <option value="">请选择</option>
		        <option value="燃气工厂">燃气工厂</option>
		        <option value="电器工厂">电器工厂</option>
		        </select>
		    </td>
		    <th>车间</th>
		    <td >
		        <select id="productarea" name="area" class="required" onchange="getptCategory()">
		           <option value="">请选择</option>
		           
		        </select>
		    </td>   
		   	
		</tr>
		
		<tr> 
		     <th>班组类别</th>
		       <td>
		         <select id="shiftgroupCategory" name="shiftgroupCategory" class="required">
		         <option value="">请选择</option>
		        
		        </select>
		    </td>	  
		    <th>物料编号</th>
		    <td><input type="text" name="materialTag" class="required"/>
		    </td>
		    
		</tr>
		<tr>
		   <th>物料名称</th>
		    <td><input name="materialName" type="text"  class="required"/></td>
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
