<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
  <script>
    jQuery(document).ready(function(){
	    $('#testTipTxt').poshytip({
	        className: 'tip-yellowsimple',
	        showOn: 'focus',
	        alignTo: 'target',
	        alignX: 'center',
	        alignY: 'top',
	        offsetY:5       
	    
	    }); 
	    $('#baseCategory_${id_end}',$.pdialog.getCurrent()).change(function(){
   		 getItem();
   	 })
    });
    
    var index = 0;
    function addItem()
    {
    	var arr = new Array();
    	arr.push("<tr id='newArray' style='height:28px;'>");	
    	arr.push("<td width='15%' style='text-align:center;' ><input style='text-align:center;' name='uiindexs["+index+"].basevalue' id='basevalue'  type='text' size='25' class='required number'/></td>");
    	arr.push("<td width='15%' style='padding-left:10px;text-align:center;'>");
    	arr.push("<input  style='text-align:center;' id='targetvalue' name='uiindexs["+index+"].targetvalue'  type='text' size='20' class='required number' /></td>");
    	arr.push("<td width='5%' style='text-align:center;'><input type='button' id='delbtn'  value='删除' onclick='removeItem(this)' class='delButton' /></td>");
    	arr.push("</tr>");	  
    	var html = arr.join("");
    	$("#printConfigsList").append(html);
    	$("#printConfigsList tr").last().find(".btnLook").lookup(); 
    	index ++;

    }
    function removeItem(obj)
    {
        $(obj).parent().parent().remove();
    }
   
    function checkRepeat(){
     	var repeatArr = new Array();
     	var repeatFArr = new Array();
        $.each($("#newArray",$.pdialog.getCurrent()),function(){
        	  var basevalue = $(this).find("#basevalue").val();
      	      var targetvalue = $(this).find("#targetvalue").val();
    		  repeatArr.push(basevalue);
    		  repeatFArr.push(targetvalue);
        });
      for(var t=0; t<repeatArr.length; t++){
        	  for(var j=0; j<repeatArr.length; j++){
        		  if(t != j){
        			 if(repeatArr[t]==repeatArr[j]){
        				 alert("指标名称重复！请检查！");
        				 return false;
        			 }
        		  }
        	  }
        }
        for(var t=0; t<repeatFArr.length ;t++){
      	  for(var j=0; j<repeatFArr.length; j++){
      		  if(t != j){
      			 if(repeatFArr[t]==repeatFArr[j]){
      				 alert("指标代码！请检查！");
      				 return false;
      			 }
      		  }
      	  }
      }
       
        $("#formID",$.pdialog.getCurrent()).submit();
    }
    
</script> 

<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='per/index/save.do?navTabId=perIndexNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">	
		<tr>  
		   <th>部门</th>
		   <td><input type="text"  value="${vo.departmentName }" readonly="readonly">
		    </td> 
		   <th>绩效大类</th>
		   <td><input type="text" value="${vo.targetclass }" readonly="readonly"></td>
		   
		   <th>衡量指标内容</th>
		   <td colspan="3"><input type="text"  value="${vo.indexcontent }" readonly="readonly"></td>  
		</tr>
		
		<tr>  
		   <th>绩效类型</th>
		   <td><input type="text" value="${vo.performancecontent }" readonly="readonly"></td>
		   
		   <th>权重</th>
		   <td ><input type="text"  value="${vo.weight }" readonly="readonly">
		   </td>  
		   	
		    <th>上年实际值</th>
		    <td >
		    <input type="hidden" name="itemKey" value="${item.itemKey }" readonly="readonly">
		    <input type="text"  value="${item.upperactualvalue }" readonly="readonly">
		    </td>
		    </tr>
		    
		    <tr>
		    <th>上半年目标</th>
		    <td ><input type="text" name="upperhalftargetvalue" value="${item.upperhalftargetvalue }" readonly="readonly"></td>
		    <th>下半年目标</th>
		      <td ><input type="text" name="secondhalftargetvalue" value="${item.secondhalftargetvalue }" readonly="readonly"></td>  

		    <th>本年基准值</th>
		    <td ><input type="text" name="referencevalue" value="${item.referencevalue }" readonly="readonly"></td>
		    </tr>
		    <tr>  
		    <th>本年目标值</th>
		    <td ><input type="text" name="yeartargetvalue" value="${item.yeartargetvalue }" readonly="readonly"></td>    
	
		    <th>年度：</th>
		    <td colspan="3"><input type="text" class="required" name="month2" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true"/></td>
		</tr>
			
<!--		<tr>
		   <th >月份</th>
			  <td colspan="5" >
				   <input type="hidden" name="queryMonth" id="queryMonth">
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-01">一月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-02">二月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"> <input type="checkbox" id="month" name="month" value="-03">三月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-04">四月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-05">五月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-06">六月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-07">七月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-08">八月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-09">九月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-10">十月&nbsp;</label>
				       <label style="width:60px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-11">十一月&nbsp;</label>
				       <label style="width:60px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-12">十二月&nbsp;</label>
				   </td>				  
				</tr>
				<tr>
				   <th >月度目标</th>
				   <td colspan="5">
				        <input type="hidden" id="targetValue" name="values">
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:60px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:60px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				   </td>
				</tr> -->
		</table>
 		<div class="panelBar">
	        <ul class="toolBar">
	            <shiro:hasPermission name="per:index:ADD">
	            </shiro:hasPermission>  	                
	                <li><a class="add" href="###" onclick="addItem()"><span>新增</span></a></li>
	            <shiro:hasPermission name="per:index:ADD">
	            </shiro:hasPermission>  
	        </ul>
   		</div>
   		<div class="pageFormContent"  layoutH="250" id="content">
	        <table class="tableFormContent nowrap" >
	            <tr>
	            	<th width="15%" style="width:15%;text-align:center;">基准值</th>       
	                <th width="15%" style="width:15%;text-align:center;">目标值</th>
	                
	                <th width="5%" style="width:5%;text-align:center;">操作</th>
	            </tr>
	            <tbody id="printConfigsList">
	              <c:forEach items="${index }" var="o">
	                <tr id='newArray'>
	                <input type="hidden" name="indexKey" value="${setup.indexKey }">
	                   <td width="15%" style="text-align:center;">${o.basevalue }</td>
	                   <td width="15%" style='text-align:center;'>${o.targetvalue }</td>
	                   
	                   <td style="width:15%;text-align:center;"></td>
	                </tr>
	              </c:forEach>
				</tbody>

	        </table>
        </div>	
        
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