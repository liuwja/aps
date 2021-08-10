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
    	arr.push("<td width='15%' style='text-align:center;' ><input style='text-align:center;' name='uiindexs["+index+"].indexName' id='indexName'  type='text' size='25' class='required'/></td>");
    	arr.push("<td width='15%' style='padding-left:10px;text-align:center;'>");
    	arr.push("<input  style='text-align:center;' id='indexCode' name='uiindexs["+index+"].indexCode'  type='text' size='20' class='required' /></td>");
    	arr.push("<td width='20%' style='text-align:center;' ><input style='text-align:center;' name='uiindexs["+index+"].indexDescription' id='indexDescription'  type='text' size='30' /></td>");
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
        	  var indexName = $(this).find("#indexName").val();
      	      var indexCode = $(this).find("#indexCode").val();
    		  repeatArr.push(indexName);
    		  repeatFArr.push(indexCode);
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
	<form method="post" id="formID" action="<c:url value='base/index/save.do?navTabId=newIndexNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">	
		<tr>  
		   <th>工厂</th>
		   <td><input type="text"  value="${vo.factory }" readonly="readonly">
		    </td> 
		   <th>车间</th>
		   <td><input type="text" value="${vo.area }" readonly="readonly"></td> 
		</tr>
		<tr>
		    <th>班组类别</th>
		   <td colspan="3"><input type="text"  value="${vo.category }" readonly="readonly"></td> 
		</tr>
		<tr>
		    <th>考核项目</th>
		    <td >
		    <input type="hidden" name="itemKey" value="${item.itemKey }" readonly="readonly">
		    <input type="text" name="itemName" value="${item.itemName }" readonly="readonly">
		    </td>
		    <th>项目代码</th>
		    <td ><input type="text" name="itemCode" value="${item.itemCode }" readonly="readonly"></td>  
		</tr>
		</table>
		<div class="panelBar">
	        <ul class="toolBar">
	            <shiro:hasPermission name="base:index:ADD">
	            </shiro:hasPermission>  	                
	                <li><a class="add" href="###" onclick="addItem()"><span>新增</span></a></li>
	            <shiro:hasPermission name="base:index:ADD">
	            </shiro:hasPermission>  
	        </ul>
   		</div>
   		<div class="pageFormContent"  layoutH="250" id="content">
	        <table class="tableFormContent nowrap" >
	            <tr>
	            	<th width="15%" style="width:15%;text-align:center;">指标名称</th>       
	                <th width="15%" style="width:15%;text-align:center;">指标代码</th>
	                <th width="20%" style="width:15%;text-align:center;">指标得分公式</th>
	                <th width="5%" style="width:5%;text-align:center;">操作</th>
	            </tr>
	            <tbody id="printConfigsList">
	              <c:forEach items="${index }" var="o">
	                <tr id='newArray'>
	                   <td width="15%" style="text-align:center;">${o.indexName }</td>
	                   <td width="15%" style='text-align:center;'>${o.indexCode }</td>
	                   <td width="15%" style='text-align:center;'>${o.indexDescription }</td>
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
