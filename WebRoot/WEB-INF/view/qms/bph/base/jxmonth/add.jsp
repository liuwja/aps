<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    	 $('#baseCategory_${id_end}',$.pdialog.getCurrent()).change(function(){
    		 getIndex();
    	 })
    });

</script>
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
    	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='monList["+index+"].accumulatedmonth' id='accumulatedmonth'  type='text' size='5' class='required number'/></td>");
    	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='monList["+index+"].lastmonthactual' id='lastmonthactual'  type='text' size='5' class='required number'/></td>");
    	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='monList["+index+"].monthreality' id='monthreality'  type='text' size='5' class='required number'/></td>");
    	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='monList["+index+"].targetvaluemonth' id='targetvaluemonth'  type='text' size='5' class='required number'/></td>");
    	arr.push("<td width='5%' style='text-align:center;' ><input style='text-align:center;' name='monList["+index+"].accumumonth' id='accumumonth'  type='text' size='5' class='required number'/></td>");
    	arr.push("<td width='5%' style='padding-left:10px;text-align:center;'>");
    	arr.push("<input  style='text-align:center;' id='record' name='monList["+index+"].record'  type='text' size='5' class='required number' /></td>");
    	arr.push("<td width='5%' style='text-align:center;'><input type='button' id='delbtn'  value='??????' onclick='removeItem(this)' class='delButton' /></td>");
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
        	  var accumulatedmonth = $(this).find("#accumulatedmonth").val();
      	      var lastmonthactual = $(this).find("#lastmonthactual").val();
      	    var monthreality = $(this).find("#monthreality").val();
    	      var targetvaluemonth = $(this).find("#targetvaluemonth").val();
    	      var accumumonth = $(this).find("#accumumonth").val();
      	      var record = $(this).find("#record").val();
    		  repeatArr.push(accumulatedmonth);
    		  repeatFArr.push(lastmonthactual);
    		  repeatArr.push(monthreality);
    		  repeatFArr.push(targetvaluemonth);
    		  repeatArr.push(accumumonth);
    		  repeatFArr.push(record);
        });

       
        $("#formID",$.pdialog.getCurrent()).submit();
    }
    
</script> 
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='per/monthly/save.do?navTabId=perMonthlyNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">			
		<tr>  
		   <th>??????</th>
		   <td><input type="text"  name="department" value="${vo.departmentName }" readonly="readonly">
		    </td> 
		   <th>????????????</th>
		   <td><input type="text" value="${vo.targetclass }" readonly="readonly"></td>
		   
		   <th>??????????????????</th>
		   <td colspan="3"><input type="text"  value="${vo.indexcontent }" readonly="readonly"></td>  
		</tr>
		
		<tr>  
		   <th>????????????</th>
		   <td><input type="text" value="${vo.performancecontent }" readonly="readonly"></td>
		   		   		    		    
		    <th>???????????????</th>
		    <td >
		    <input type="text" name="referencevalue" value="${item.referencevalue }" readonly="readonly"></td>
  
		    <th>???????????????</th>
		    <td ><input type="text" name="yeartargetvalue" value="${item.yeartargetvalue}" readonly="readonly"></td>    
	</tr>
	<tr>
	 <th>????????????</th>
		    <td >
		    <input type="hidden" name="indexKey" value="${setup.indexKey }">
		    <input type="text" name="basevalue" value="${setup.basevalue}" readonly="readonly"></td>
  
		    <th>????????????</th>
		    <td ><input type="text" name="targetvalue"  value="${setup.targetvalue}" readonly="readonly"></td>
		    
		    <th>?????????</th>
		    <td ><input type="text" name="month"  value="${setup.month}" readonly="readonly"></td>
		</tr>
		
		</table>
		
		<!--??????????????????  -->
		<div class="panelBar">
	        <ul class="toolBar">	           	                       
	    <li><a class="add" href="###" ><span>????????????</span></a></li>	            
	        </ul>
   		</div>
   			           	      	         
	  <table class="tableFormContent nowrap">						
		<tr>   
		    <th>???????????????</th>
		    <td >
		         <input type="text" id="monthreality"  name="monList[0].monthreality" size="5" class="required number" />
		    </td>	

		   
		    <th>?????????????????????</th>
		    <td >
		         <input type="text" id="accumumonth"  name="monList[0].accumumonth" size="5" class="required number" />
		    </td>		    	    
		</tr>
		</table>  
			
				       	       	                      						
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">??????</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">??????</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<!--	 <div class="panelBar">
	        <ul class="toolBar">
	            <shiro:hasPermission name="per:month:ADD">
	            </shiro:hasPermission>  	                
	                <li><a class="add" href="###" onclick="addItem()"><span>??????</span></a></li>
	            <shiro:hasPermission name="per:month:ADD">
	            </shiro:hasPermission>  
	        </ul>
   		</div>
   		<div class="pageFormContent"  layoutH="250" id="content">
	        <table class="tableFormContent nowrap" >
	            <tr>
	            	<th width="15%" style="width:15%;text-align:center;">?????????????????????</th>       
	                <th width="15%" style="width:15%;text-align:center;">?????????????????????</th>
	                <th width="15%" style="width:15%;text-align:center;">???????????????</th>       
	                <th width="15%" style="width:15%;text-align:center;">?????????????????????</th>
	                <th width="15%" style="width:15%;text-align:center;">?????????????????????</th>       
	                <th width="15%" style="width:15%;text-align:center;">??????????????????</th>
	                <th width="5%" style="width:5%;text-align:center;">??????</th>
	            </tr>
	            <tbody id="printConfigsList">
	              <c:forEach items="${permonth}" var="o">
	                <tr id='newArray'>
	                   <td width="15%" style="text-align:center;">${o.accumulatedmonth }</td>
	                   <td width="15%" style='text-align:center;'>${o.lastmonthactual }</td>
	                   <td width="15%" style="text-align:center;">${o.monthreality }</td>
	                   <td width="15%" style='text-align:center;'>${o.targetvaluemonth }</td>
	                   <td width="15%" style="text-align:center;">${o.accumumonth }</td>
	                   <td width="15%" style='text-align:center;'>${o.record }</td>
	                   <td style="width:15%;text-align:center;"></td>
	                </tr>
	              </c:forEach>
				</tbody>

	        </table>
        </div>		
		
		
		
	</div>
	<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">??????</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">??????</button></div></div>
				</li>
			</ul>
	</div>
	</form>
</div> -->
