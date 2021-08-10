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
	<form method="post" id="formID" action="<c:url value='per/month/save.do?navTabId=perMonthNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">			
		<tr>  
		   <th>部门</th>
		   <td><input type="text"  name="department" value="${vo.department }" readonly="readonly">
		    </td> 
		   <th>绩效大类</th>
		   <td><input type="text" value="${vo.targetclass }" readonly="readonly"></td>
		   
		   <th>衡量指标内容</th>
		   <td colspan="3"><input type="text"  value="${vo.indexcontent }" readonly="readonly"></td>  
		</tr>
		
		<tr>  
		   <th>绩效类型</th>
		   <td><input type="text" value="${vo.performancecontent }" readonly="readonly"></td>
		   		   		    		    
		    <th>本年基准值</th>
		    <td >
		    <input type="text" name="referencevalue" value="${item.referencevalue }" readonly="readonly"></td>
  
		    <th>本年目标值</th>
		    <td ><input type="text" name="yeartargetvalue" value="${item.yeartargetvalue}" readonly="readonly"></td>    
	</tr>
	<tr>
	 <th>月基准值</th>
		    <td >
		    <input type="hidden" name="indexKey" value="${setup.indexKey }">
		    <input type="text" name="basevalue" value="${setup.basevalue}" readonly="readonly"></td>
  
		    <th>月目标值</th>
		    <td ><input type="text" name="targetvalue"  value="${setup.targetvalue}" readonly="readonly"></td>
		    
		    <th>年度：</th>
		    <td colspan="3"><input type="text" class="required" name="monList[0].monthvalue" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true"/></td>
		</tr>
		
		</table>
		
		<!--新增考核方法  -->
		<div class="panelBar">
	        <ul class="toolBar">	           	                       
	    <li><a class="add" href="###" ><span>考核方法</span></a></li>	            
	        </ul>
   		</div>
   			           	      	         
	  <table class="tableFormContent nowrap">						
		<tr>   
		 <th>上月累计目标值</th>
		    <td >  
		         <input type="text" id="accumulatedmonth"  name="monList[0].accumulatedmonth" size="5" class="required number"/>
		    </td>
		    <th>上月累计实际值</th>
		    
		    <td >
		         <input type="text" id="lastmonthactual"  name="monList[0].lastmonthactual" size="5" class="required number" />
		    </td>
		    <th>当月实际值</th>
		    <td >
		         <input type="text" id="monthreality"  name="monList[0].monthreality" size="5" class="required number" />
		    </td>	
		    </tr>
		    <tr>
		    <th>当月累计目标值</th>
		    <td >
		         <input type="text" id="targetvaluemonth"  name="monList[0].targetvaluemonth" size="5" class="required number" />
		    </td>
		    <th>当月累计实际值</th>
		    <td >
		         <input type="text" id="accumumonth"  name="monList[0].accumumonth" size="5" class="required number" />
		    </td>
		    <th>下月挑战目标</th>
		    <td >
		         <input type="text" id="record"  name="monList[0].record" size="5" class="required number" />
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
<!--	 <div class="panelBar">
	        <ul class="toolBar">
	            <shiro:hasPermission name="per:month:ADD">
	            </shiro:hasPermission>  	                
	                <li><a class="add" href="###" onclick="addItem()"><span>新增</span></a></li>
	            <shiro:hasPermission name="per:month:ADD">
	            </shiro:hasPermission>  
	        </ul>
   		</div>
   		<div class="pageFormContent"  layoutH="250" id="content">
	        <table class="tableFormContent nowrap" >
	            <tr>
	            	<th width="15%" style="width:15%;text-align:center;">上月累计目标值</th>       
	                <th width="15%" style="width:15%;text-align:center;">上月累计实际值</th>
	                <th width="15%" style="width:15%;text-align:center;">当月实际值</th>       
	                <th width="15%" style="width:15%;text-align:center;">当月累计目标值</th>
	                <th width="15%" style="width:15%;text-align:center;">当月累计实际值</th>       
	                <th width="15%" style="width:15%;text-align:center;">下月挑战目标</th>
	                <th width="5%" style="width:5%;text-align:center;">操作</th>
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
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
	</div>
	</form>
</div> -->
