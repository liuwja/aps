<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css" media="screen">
	${demo.css}
	
.my-uploadify-button {
    background:none;
    border: none;
    text-shadow: none;
    border-radius:0;
}

.uploadify:hover .my-uploadify-button {
    background:none;
    border: none;
}

.fileQueue {
    width: 400px;
    height: 150px;
    overflow: auto;
    border: 1px solid #E5E5E5;
    margin-bottom: 10px;
}

.child_content{
border:1px solid #E5E5E5;  width:55%;height: 500px;overflow: auto;position:absolute;top:15.5%; right:0%;display:none;background: #F8F8F8;
}
.chid_content font {
  padding: 0;
  margin: 0;
  font-size: 12px;
  line-height: 100%;
  font-family: Arial, sans-serif;
}
.child_header{
width:100%; height:155px;background:#F8F8F8;
}
.child_header table{
padding-left: 5px; padding-top: 5px;
}
.child_header table tr td{
  padding:2px;padding-left: 5px;
}
.child_header button{
    color: #FFF;
    border-radius: 0;
    background-color: #3FA7DC;
    margin-left: 10px;
    border: none;
    height: 20px;
    width: 50px;
}
.child_header button:hover{
    background-color: #277EAB;
}
</style>
<script type="text/javascript">
 
    $("#selectDate",navTab.getCurrentPanel()).change(function(){
    	var dateT= $("#dateTd",navTab.getCurrentPanel());
    	//alert(dateT.onclick);
    	dateT.empty();
    	var selectDate = $("#selectDate",navTab.getCurrentPanel()).val();
    	if(selectDate =='天'){
    		dateT.append("<input type='text' size='8' id='dateT' name='dateT' placeholder='请输入日期' onclick='laydate()' readonly='true'/>");
    	}else if(selectDate =='周'){
    		dateT.append("<input type='text'  size='8' id='dateT'  name='dateT' placeholder='请输入日期' onclick='laydate({week:true});' readonly='true'/>");
    	}else if(selectDate =='月'){
    		dateT.append("<input type='text'  size='8' id='dateT' name='dateT' placeholder='请输入日期' onclick='laydate({isym:true, format: \"YYYY-MM\"})' readonly='true'/>");
    	}else if(selectDate =='年'){
    		dateT.append("<input type='text'  size='8' id='dateT'  name='dateT' placeholder='请输入日期' onclick='laydate({isym:true, format: \"YYYY\"})' readonly='true'/>");
    	}
    });
  
    function getChildDate(){
    	var dateT= $("#childDateTd",navTab.getCurrentPanel());
    	//alert(dateT.onclick);
    	dateT.empty();
    	var selectDate = $("#childSelectDate",navTab.getCurrentPanel()).val();
    	if(selectDate =='天'){
    		dateT.append("<input type='text' size='6' id='childDateT' name='childDateT' placeholder='请输入日期' onclick='laydate()' readonly='true'/>");
    	}else if(selectDate =='周'){
    		dateT.append("<input type='text'  size='6' id='childDateT'  name='childDateT' placeholder='请输入日期' onclick='laydate({week:true});' readonly='true'/>");
    	}else if(selectDate =='月'){
    		dateT.append("<input type='text'  size='6' id='childDateT' name='childDateT' placeholder='请输入日期' onclick='laydate({isym:true, format: \"YYYY-MM\"})' readonly='true'/>");
    	}else if(selectDate =='年'){
    		dateT.append("<input type='text'  size='6' id='childDateT'  name='childDateT' placeholder='请输入日期' onclick='laydate({isym:true, format: \"YYYY\"})' readonly='true'/>");
    	}
    }
    var supselNameObj =[];
    var supselValueObj =[];
    function loadTestInstanceChart(child){
    	var factory = $("#factory",navTab.getCurrentPanel()).val();
    	var productType = $("#productType",navTab.getCurrentPanel()).val();
    	var supplierCode = $("#supplierLookup_supplierCode",navTab.getCurrentPanel()).val();
    	var supplier = $("#supplierLookup_supplierName",navTab.getCurrentPanel()).val();
    	var partType = $("#partType",navTab.getCurrentPanel()).val();
    	var partName = $("#supPartLookup_partName",navTab.getCurrentPanel()).val();
    	var partNumber = $("#supPartLookup_partCode",navTab.getCurrentPanel()).val();
    	var partClass = $("#partClass",navTab.getCurrentPanel()).val();
    	var partVersion = $("input[name='partVersion']:checked",navTab.getCurrentPanel()).val();
    	var dateType = $("#selectDate",navTab.getCurrentPanel()).val();
    	var dateT = $("#dateT",navTab.getCurrentPanel()).val();
    	var isNew = $("select[name='isNew']",navTab.getCurrentPanel()).val();
    	var lotStartTime = $("#lotStartTime",navTab.getCurrentPanel()).val();
    	var lotEndTime = $("#lotEndTime",navTab.getCurrentPanel()).val();
    	var columnNum = $("#columnNum",navTab.getCurrentPanel()).val();
    	var analysisType = $("input[name='analysisType']:checked",navTab.getCurrentPanel()).val();
    	var sordType = $("#sordType",navTab.getCurrentPanel()).val();
    	var defectType ="";
    	var distinction = "" ;
    	if(dateType ==""){
    		alert("请选择时间维度！");
    		return ;
    	}
    	if(dateType !=""){
    		if(dateT == ''){
        		alert("请选择时间！");
        		return ;
        	}
    	}
    	if(columnNum ==''){
    		alert("请输入排列图数量！");
    		return ;
    	}
    	if(child.type==1){
    		supplier = ""+child.name+"";
    		supplierCode = ""+child.code+"";
    	}else if(child.type==2){
    		partName = ""+child.name+"";
    		partNumber = ""+child.code+"";
    	}else if(child.type==3){
    		defectType = ""+child.name+"";
    	}
   	    if(child == 'false'){
	    	supselNameObj = ["factory","productType","supplier","partType","partClass","partName","partVersion","dateType","dateT","isNew","lotStartTime","lotEndTime","columnNum","analysisType","defectType","partNumber","supplierCode"];
	    	supselValueObj = [factory,productType,supplier,partType,partClass,partName,partVersion,dateType,dateT,isNew,lotStartTime,lotEndTime,columnNum,analysisType,defectType,partNumber,supplierCode];
   	    }else{
   	    	distinction =1;
   	    }
    	var url = "<c:url value='quality/testInstance/lodeSupplierDefect.do' />";
    	$.post(url,{factory:factory,productType:productType,supplier:supplier,partType:partType,partClass:partClass,partName:partName,partVersion:partVersion,dateType:dateType,dateT:dateT,isNew:isNew,lotStartTime:lotStartTime,lotEndTime:lotEndTime,columnNum:columnNum,analysisType:analysisType,defectType:defectType,partNumber:partNumber,supplierCode:supplierCode,sordType:sordType,distinction:distinction},function(data){
    //		alert(data.result);
    		if(data.result==0){
    			if(child=="false"){
    			  getSupContentHeight("supplierContent");
    			  loadChartOne_one(data);
    			}else {
    			  var childDateT = $("#childDateTd",navTab.getCurrentPanel());
    			  if(factory != ""){
    				  $("#childFactory",navTab.getCurrentPanel()).val(factory);
    			  }	
    			  if(productType != ""){
    					var chproductType = $("#childProductType",navTab.getCurrentPanel());
    					chproductType.empty();
    					chproductType.append("<option value=''>请选择</option>");
    			   	    <c:forEach items="${productType }" var="o">
    			   	       var key = '${o.key}';
    				       if(factory == key){
    				    	   <c:forEach items="${o.value}" var="pro">
    				    	      chproductType.append("<option value='${pro.productType}'>${pro.productType}</option>");
    				    	   </c:forEach>
    				       }
    				    </c:forEach>
    				    chproductType.val(productType);
    			  }
    			  if(supplierCode != ""){
    				  $("#childSupplierCode",navTab.getCurrentPanel()).val(supplierCode);
    			  }
    			  if(supplier != ""){
    				  $("#childSupplier",navTab.getCurrentPanel()).val(supplier);
    			  }
    			  if(partType != ""){
    				  $("#childPartType",navTab.getCurrentPanel()).val(partType);
    			  }
    			  if(partClass != ""){
    				  $("#childPartClass",navTab.getCurrentPanel()).val(partClass);
    			  }
    			  if(partNumber != ""){
    				  $("#childPartNumber",navTab.getCurrentPanel()).val(partNumber);
    			  }
    			  if(partName != ""){
    				  $("#childPartName",navTab.getCurrentPanel()).val(partName);
    			  }
    			  if(partVersion != "" && partVersion != null ){
    				  $("input:radio[name=childPartVersion][value="+partVersion+"]", navTab.getCurrentPanel()).attr("checked","checked");
    			  }
    			  if(dateType != ""){
    				  $("#childSelectDate",navTab.getCurrentPanel()).val(dateType);
    				  childDateT.empty();
    				  if(dateType=='天'){
    					  childDateT.append("<input type='text' size='6' id='childDateT' name='childDateT' placeholder='请输入日期' onclick='laydate()' readonly='true' value='"+dateT+"'/>");
    				  }else  if(dateType=='周'){
    					  childDateT.append("<input type='text'  size='6' id='childDateT'  name='childDateT' placeholder='请输入日期' onclick='laydate({week:true})' readonly='true' value='"+dateT+"'/>");
    				  }else  if(dateType=='月'){
    					  childDateT.append("<input type='text'  size='6' id='childDateT' name='childDateT' placeholder='请输入日期' onclick='laydate({isym:true, format: \"YYYY-MM\"})' readonly='true' value='"+dateT+"'/>");
    				  }else if(dateType=='年'){
    					  childDateT.append("<input type='text'  size='6' id='childDateT'  name='childDateT' placeholder='请输入日期' onclick='laydate({isym:true, format: \"YYYY\"})' readonly='true' value='"+dateT+"'/>");
    				  }
    			  }
    			  if(isNew != ""){
    				  $("#childIsNew",navTab.getCurrentPanel()).val(isNew);
    			  }
    			  if(lotStartTime != ""){
    				  $("#childLotStartTime",navTab.getCurrentPanel()).val(lotStartTime);
    				  $("#childLotEndTime",navTab.getCurrentPanel()).val(lotEndTime);
    			  }
    			  if(columnNum != ""){
    				  $("#childColumnNum",navTab.getCurrentPanel()).val(columnNum);
    			  }
    			  if(analysisType != "" && analysisType != null){
    				  $("#childAnalysisType",navTab.getCurrentPanel()).filter("[value="+analysisType+"]").attr("checked","checked");
    			  }
    			  getSupContentHeight("childContent");
     			  loadChartOne_two(data);
    			}
    		}
    		if(data.result==-1){
    			alert("查询出错，请联系管理员！");
    		}
    	});
 
    }
    function getSupContentHeight(obj){
    	 var contentHeight = $("div.navTab-panel ").innerHeight();
         var searchBarHeight = $("#testInstanceForm",navTab.getCurrentPanel()).innerHeight();
         var height = parseInt(contentHeight)- parseInt(searchBarHeight);
         if(obj == 'childContent'){
        	 var contentWidth = ($("div.navTab-panel ").innerWidth())/2;
        	 if(contentWidth < 400){
        		 contentWidth = 400;
        		 $("#"+obj,navTab.getCurrentPanel()).css({"height":height,"width" : contentWidth});
        	 }
        	 $("#"+obj,navTab.getCurrentPanel()).css({"height":height});
         }else{
            $("#"+obj,navTab.getCurrentPanel()).css({"height":height});
         }
    }
    function clearAll(){
    	$("#supPartLookup_partName", navTab.getCurrentPanel()).val("");
    	$("#supPartLookup_data", navTab.getCurrentPanel()).val("");
    	$("#supPartLookup_partCode", navTab.getCurrentPanel()).val("");
    	
    }
    function clearSupplierAll(){
    	$("#supplierLookup_supplierName", navTab.getCurrentPanel()).val("");
    	$("#supplierLookup_data", navTab.getCurrentPanel()).val("");
    	$("#supplierLookup_supplierCode", navTab.getCurrentPanel()).val("");
    	
    }
    function getProductType(obj){
    	var productType = $("#productType",navTab.getCurrentPanel());
    	productType.empty();
    	productType.append("<option value=''>请选择</option>");
    	var factroy = $(obj).val();
   	    <c:forEach items="${productType }" var="o">
   	       var key = '${o.key}';
	       if(factroy == key){
	    	   <c:forEach items="${o.value}" var="pro">
	    	      productType.append("<option value='${pro.productType}'>${pro.productType}</option>");
	    	   </c:forEach>
	       }
	    </c:forEach>
    }

    function getChildProductType(obj){
    	var productType = $("#childProductType",navTab.getCurrentPanel());
    	productType.empty();
    	productType.append("<option value=''>请选择</option>");
    	var factroy = $(obj).val();
   	    <c:forEach items="${productType }" var="o">
   	       var key = '${o.key}';
	       if(factroy == key){
	    	   <c:forEach items="${o.value}" var="pro">
	    	      productType.append("<option value='${pro.productType}'>${pro.productType}</option>");
	    	   </c:forEach>
	       }
	    </c:forEach>
    }
</script>

	<form id="testInstanceForm" onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div id="searchContentDiv" class="searchBar dropdownSearchBar" >
		<table class="searchContent">
			<tr>
			    <td>
					工厂：
				</td>
				<td>
					<select id="factory" name="factory" onchange="getProductType(this)">
					   <option value="">请选择</option>
					   <c:forEach items="${productType }" var="o">
					     <option value="${o.key }">${o.key }</option>
					   </c:forEach>
					</select>
				</td>
				<td>
					机型：
				</td>
				<td>
					<select id="productType" name="productType" >
					   <option value="">请选择</option>
					</select>
				</td>	
				<td>
					供应商：
				</td>
                <td>
              <!--    <div id="supplierList" class="dropdownlist"></div> -->   
                    <input style="float: left;" type="text" id="supplierLookup_supplierName" name="supplier" value="${param.supplier}" size="15"/>
                    <input type="hidden" id="supplierLookup_data" value="">
                    <input type="hidden" id="supplierLookup_supplierCode" value="">
                    <a id="btn"  class="btnLook btn" href="quality/testInstance/supplierSelect.do?data=supplierLookup" width="1150" height="550" lookupGroup="supplierLookup">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearSupplierAll()"  title="清空"></a> 
                </td>  
                
		  </tr>
		  <tr>		
                <td>
					物料类别：
				</td>
                <td >
                     <div id="partTypeList" class="dropdownlist"></div>
                </td> 
                <td>
					物料级别：
				</td>
                <td>
                     <select id="partClass" name="partClass">
					   <option value="">请选择</option>
					   <option value="A">A</option>
					   <option value="B">B</option>
					   <option value="C">C</option>
					</select>
                </td> 
                 <td>
					物料号：
				</td>
                <td>
                    <input style="float: left;" type="text" id="supPartLookup_partName" name="partName" value="${param.partName}" size="15"/>
                    <input type="hidden" id="supPartLookup_data" value="">
                     <input type="hidden" id="supPartLookup_partCode" value="">
                    <a id="btn"  class="btnLook btn" href="quality/testInstance/partSelect.do?data=supPartLookup" width=950 height=500 lookupGroup="supPartLookup">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearAll()"  title="清空"></a> 
                </td> 
		 </tr>	
		 <tr>	
                 <td>
					是否带版本：
				</td>
                <td>
                    <label><input id="partVersion" name="partVersion" type="radio" value="是">是</label>
                    <label><input id="partVersion" name="partVersion" type="radio" value="否" checked="checked">否</label>
                </td> 	
                
                <td>
					产品成熟度：
				</td>
                <td >
                  <select id="isNew" name="isNew">
                      <option value="">全部</option>
                      <option value="老品">老品</option>
                      <option value="新品">新品</option>
                      <option value="little">小批量</option>
                      <option value="large">非小批量</option>
                  </select>
                </td>  			
		        <td>
					时间维度：
				</td>
                <td >
                    <select id="selectDate" >
                       <option value=''>请选择</option>
                       <option value="天">天</option>
                       <option value="周">周</option>
                       <option value="月">月</option>
                       <option value="年">年</option>
                     </select>
                     <script type="text/javascript">
                        $("#selectDate",navTab.getCurrentPanel()).val("月");
                     </script>
                     <span id="dateTd"><input type='text'  size='8' id='dateT' name='dateT' placeholder='请输入日期' onclick='laydate({isym:true, format: "YYYY-MM"})' readonly='true'/></span>
                    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;
                     <select id="sordType">
                          <option value="不良批/数">不良批/数-排序</option>
                          <option value="不良率">不良率-排序</option>
                     </select>
                </td>
            </tr>
            <tr>   
                 <td>
					供应商批次：
				</td>
                <td>
                    <input type='text' size='10' id='lotStartTime' name='lotStartTime' placeholder='请输入日期' onclick="laydate()" readonly='true'/> 至
                    <input type='text' size='10' id='lotEndTime' name='lotEndTime' placeholder='请输入日期' onclick="laydate()" readonly='true'/>
                </td>
                <td>
					排列图数量：
				</td>
                <td >
                  <!--  <select id="columnNum" name="columnNum">
                       <option value="5">5</option>
                       <option value="10">10</option>
                       <option value="15">15</option>
                       <option value="20">20</option>
                   </select> -->
                   <input id="columnNum" name="columnNum" type="text" size='6' value="10" class="required number">
                </td>	
                <td>
                                  统计方式：
                </td>
                <td>                  
                 <label><input type="radio" name="analysisType" value="0" checked="checked">不良批次数/率</label>
                 <label><input type="radio" name="analysisType" value="1">不良数/率</label>
				 <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadTestInstanceChart('false');">查找</button></div></div>
				</td>	
			</tr>
		</table>
	</div>
	</form>

<div  class="pageContent"   style="overflow: auto; ">
    <div id="supplierContent" style="overflow: auto; ">
		<div id="chartOne_one" style="width: 95%;float:left;height: 300px; padding-top: 30px;"></div>
		<div  style="width: 100%;float:left;height: 10px; background:  #FFFF66"></div>
	    <div id="chartTwo_one" style="width: 95%;float:left;height: 300px; padding-top: 30px;"></div>
	    <div  style="width: 100%;float:left;height: 10px; background: #FFFF66"></div>
	    <div id="chartTwo_two" style="width: 95%;float:left;height: 300px; padding-top: 30px;"></div>
	    <div  style="width: 100%;float:left;height: 10px; background: #FFFF66"></div>
	    <div id="chartThree_one" style="width: 95%;float:left;height: 300px; padding-top: 30px;"></div>
	    <div  style="width: 100%;float:left;height: 10px; background: #FFFF66"></div>
	    <div id="chartThree_two" style="width: 95%;float:left;height: 300px; padding-top: 30px;"></div>
	    <div  style="width: 100%;float:left;height: 10px; background: #FFFF66"></div>
	    <div id="chartFour_one" style="width: 95%;float:left;height: 300px; padding-top: 30px;"></div>
	    <div  style="width: 100%;float:left;height: 10px; background: #FFFF66"></div>
	    <div id="chartFour_two" style="width: 95%;float:left;height: 300px; padding-top: 30px;padding-bottom: 20px;"></div>
      </div>
</div>

<div  id="childContent" class="child_content" >
     <div id="child_header" class="child_header" >
     <div style="position: fixed;z-index: 9999;padding-top: 165px;padding-left: 10px;"><button style="cursor: pointer;" type="button" onclick="loadChildChart();">收起</button></div>
     <div  >
		<table >
		 <tr>
			    <td >
					<label >工厂：<span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="childFactory" onchange="getChildProductType(this)">
					   <option value="">请选择</option>
					   <c:forEach items="${productType }" var="o">
					     <option value="${o.key }">${o.key }</option>
					   </c:forEach>
					</select></span></label>
				</td>
				
				<td>
					<label>机型：<span >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="childProductType" >
					   <option value="">请选择</option>
					   <c:forEach items="${productType }" var="o">
					       <c:if test="${o.key==vo.factory }">
					           <c:forEach items="${o.value}" var="pro">
					    	      <option value='${pro.productType}'>${pro.productType}</option>
					    	   </c:forEach>
					       </c:if>
	                     </c:forEach>
					</select>
					</span></label>
				</td>
		</tr>
		<tr>		
				<td >
					供应商：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input style="vertical-align:middle;" type="text" size='10' id="childSupplier"  /><input  type="hidden"  id="childSupplierCode"  /></label>
				</td>
				
                  
                <td >
					物料类别：&nbsp;&nbsp;&nbsp;&nbsp;<input  type="text" size='10' id="childPartType"  />
				</td>
				
		</tr>
		 <tr>		
				 <td>
					物料级别：&nbsp;&nbsp;&nbsp;&nbsp;
                     <select id="childPartClass" >
					   <option value="">请选择</option>
					   <option value="A">A</option>
					   <option value="B">B</option>
					   <option value="C">C</option>
					</select>
                </td> 
                <td>
					物料号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input style="vertical-align:middle;" type="text" size='10' id="childPartName"><input type="hidden"  id="childPartNumber">
				</td>
		 </tr>
		 <tr>
		        <td>
					时间维度：&nbsp;&nbsp;&nbsp;&nbsp;<select id="childSelectDate" onchange="getChildDate()"> 
                       <option value="天">天</option>
                       <option value="周">周</option>
                       <option value="月">月</option>
                       <option value="年">年</option></select>
                       <span id="childDateTd"></span>
				</td>
				<td>
					是否带版本：
                    <label><input id="childPartVersion"  type="radio" name="childPartVersion" value="是">是</label>
                    <label><input id="childPartVersion"  type="radio" name="childPartVersion" value="否">否</label>
                </td>
		 </tr>
		 <tr>		
		        <td>
					产品成熟度：
					<select id="childIsNew" name="childIsNew"> 
					  <option value="">全部</option>
                      <option value="老品">老品</option>
                      <option value="新品">新品</option>
                      <option value="little">小批量</option>
                      <option value="large">非小批量</option>
                      </select>
                </td> 
			
                <td>
					<label style="float: left;">供应商批次：<input type='text' size='8' id='childLotStartTime'  placeholder='请输入日期' onclick='laydate()' readonly='true'/>至
					<input type='text' size='8' id='childLotEndTime'  placeholder='请输入日期' onclick='laydate()' readonly='true'/></label>
				</td>
		</tr>
		 <tr>		
                <td>
                                  统计方式：&nbsp;&nbsp;&nbsp;&nbsp;
                 <label><input type="radio" id='childAnalysisType' name="childAnalysisType" name="childAnalysisType"  value="0" checked="checked">不良批次数/率</label>
                 <label><input type="radio" id='childAnalysisType' name="childAnalysisType" name="childAnalysisType"  value="1">不良数/率</label>
                </td>
				<td>
				<button type="button" style="cursor: pointer;" onclick="loadChildTestInstanceChart('false');">查找</button>
				</td>	
			</tr>
		</table>
		</div>
     </div>
     <div>
		 <div id="childOne_one" style="width: 95%;height: 300px;"></div>
		 <div style="width: 100%;height: 10px;background: #FFFF66"></div>
		 <div id="childOne_two" style="width: 95%;height: 300px;"></div>
		 <div style="width: 100%;height: 10px;background: #FFFF66"></div>
		 <div id="childOne_three" style="width: 95%;height: 300px;"></div>
		 <div style="width: 100%;height: 10px;background: #FFFF66"></div>
		 <div id="childOne_four" style="width: 95%;height: 300px;"></div>
		 <div style="width: 100%;height: 10px;background: #FFFF66"></div>
		 <div id="childOne_five" style="width: 95%;height: 300px;"></div>
		 <div style="width: 100%;height: 10px;background: #FFFF66"></div>
		 <div id="childOne_six" style="width: 95%;height: 300px;"></div>
		 <div style="width: 100%;height: 10px;background: #FFFF66"></div>
		 <div id="childOne_seven" style="width: 95%;height: 300px;"></div>
	 </div>
</div>

 <script type="text/javascript">
/**
 * 加载图形
 */
 function loadChartOne_one(resulMap){
	    var analysisT= $("input[name='analysisType']:checked",navTab.getCurrentPanel()).val(); 
	    var colorList = [
		   			  '#3399FF','#C1232B','#333366','#333366','#C1232B',
		   			   '#333366','#C1232B','#333366','#C1232B','#333366',
		   			   '#C1232B','#333366','#C1232B','#333366','#C1232B',
		   			   '#333366','#C1232B','#333366','#C1232B','#333366'
		   			];
		var echart1,echart2,echart3,echart4,echart5,echart6,echart7;
		var option1,option2,option3,option4,option5,option6,option7;

            option6 = resulMap.option6;
	        option7 = resulMap.option7;
	   var suppliserList = resulMap.supplierCodeList;
	   var partList = resulMap.partCodeList;
           /*  var tooltip = {
		        trigger: 'axis',
		        formatter: function(params) {
		           // console.warn(params[0].dataIndex+1);
		            //console.warn((params[0].dataIndex+1)%2);
		            var svalue ;
		            var sname ;
		            if((params[0].dataIndex+1)%2===1){
		                if(params[1].value !=='-'){
		                	sname = params[0].name +'本月';
		                    svalue = params[1].seriesName + ' : ' + params[1].value+'%' ;
		                }else{
		                	sname = params[0].name +'本月';
		                    svalue = params[1].seriesName + ' : ' + params[2].value+'%' ;
		                }
		                
		            }
		             if((params[0].dataIndex+1)%2===0){
		                if(params[1].value !=='-'){
		                	sname = params[0].name +'上月';
		                    svalue = params[2].seriesName + ' : ' + params[1].value+'%' ;
		                }else{
		                	sname = params[0].name +'上月';
		                    svalue = params[2].seriesName + ' : ' + params[2].value+'%' ;
		                }
		            }
		            return sname +' : ' + params[0].value + '<br/>'
		                   + svalue ;
		        },
		    }; */
           var itemStyle = {
            	normal: { 
            		color: function(params) {
            			return colorList[(params.dataIndex)%2] ;
            			}
            			} 
            };
         /*   function alertObj(obj){
	    		var output = "";
	    		for(var i in obj){  
	    			var property=obj[i];  
	    			output+=i+" = "+property+"\n"; 
	    		}  
	    		alert(output);
	    	} */
	       //导出工具栏
           var myTool = {
        		    selType : 1 ,
        	        show : true,
        	        title : '导出',
        	        icon : 'M4.7,22.9L29.3,45.5L54.7,23.4M4.6,43.6L4.6,58L53.8,58L53.8,43.6M29.2,45.1L29.2,0',
        	        onclick : function (ecModel, api){
        	     	        var model = this.model;
        	     	        var title = model.get('name') || ecModel.get('title.0.text') || 'echarts';
        	     	        var $a = document.createElement('a');
        	     	        var _type = model.get('type', true) || 'png';
        	     	        $a.download = title + '.' + _type;
        	     	        $a.target = '_blank';
        	     	        var url = api.getConnectedDataURL({
        	     	            type: _type,
        	     	            backgroundColor: model.get('backgroundColor', true)
        	     	                || ecModel.get('backgroundColor') || '#fff',
        	     	            excludeComponents: model.get('excludeComponents'),
        	     	            pixelRatio: model.get('pixelRatio')
        	     	        });
        	     	        $a.href = url;
        	     	        // Chrome and Firefox
        	     	         if (typeof MouseEvent === 'function') {
        	     	            var evt = new MouseEvent('click', {
        	     	                view: window,
        	     	                bubbles: true,
        	     	                cancelable: false
        	     	            });
        	     	            $a.dispatchEvent(evt);
        	     	        } 
        	     	        // IE
        	     	         else {
        	     	            var lang = model.get('lang');
        	     	            var html = ''
        	     	                + '<body style="margin:0;">'
        	     	                + '<img src="' + url + '" style="max-width:100%;" title="' + ((lang && lang[0]) || '') + '" />'
        	     	                + '</body>';
        	     	            var tab = window.open();
        	     	            tab.document.write(html);
        	     	        } 
        	     	    	var url = "<c:url value='quality/testInstance/dowmLoadFeedDetail.do' />";
        	     	    	var form = document.createElement("form");
        	     	    	form.action = url;
        	     	    	form.method = "post";
        	     	    	form.target = '_blank';
        	     	    	for(var i in supselNameObj){
        	     	    		var sinput = document.createElement("input");
        	     	    		sinput.name = supselNameObj[i];
        	     	    		sinput.value = supselValueObj[i];
        	     	    		form.appendChild(sinput);
        	     	    	}
        	     	    	//alertObj(model.option);
        	     	    	var sinput = document.createElement("input");
    	     	    		sinput.name = "type";
    	     	    		sinput.value = model.option.selType;
    	     	    		form.appendChild(sinput);
    	     	    		document.body.appendChild(form);
        	     	    	form.submit();
        	        } 
        	    };
	       var createTooltip = function(opts,code){
	    	   var tooltip_r = {
	      	    		trigger: 'axis',
	      	    		formatter: function(params) {
	      			           // console.warn(params[0].dataIndex+1);
	      			            var sname = params[0].name ;
	      			            var svalue ="";
	      			            var lab = "";
	      			            if(analysisT ==0){
	      			            	lab ="(批)";
	      			            }else{
	      			            	lab ="(个)";
	      			            }
	      			            for(var i in params){
	      			            	  if(i == 0){
	      			            		  svalue += params[i].seriesName + ' : ' + params[i].value+lab + '<br/>';
	      			            		  if(sname!= '其他'){
	      	   			            		  svalue += "到货总数："+ opts[sname] + lab + '<br/>';
	      	   			            		  if(code != null ){
	      	   			            			sname = code[sname]+"-" + sname;
	      	   			            		  }
	      			            		  }
	      			            	  }else{
	      		                          svalue += params[i].seriesName + ' : ' + params[i].value+'%' + '<br/>';
	      			            	  }
	      			            	}
	      			        
	      			            return sname +' <br/>' + svalue ;
	      			        },
	      	      };
	    	   
	    		return  $.extend(tooltip_r,opts,{});
	       }
          
            resulMap.option1.color = colorList;
            resulMap.option1.tooltip = createTooltip(resulMap.total1,resulMap.supCodeMap); 
            resulMap.option2.color = colorList;
            resulMap.option2.tooltip = createTooltip(resulMap.total2,resulMap.partCodeMap); 
            resulMap.option3.color = colorList;
            resulMap.option3.tooltip = createTooltip(resulMap.total3,null); 
            resulMap.option4.color = colorList;
            resulMap.option4.tooltip = createTooltip(resulMap.total4,null); 
            resulMap.option5.color = colorList;
            resulMap.option5.tooltip = createTooltip(resulMap.total5,null); 
            option6.color = colorList;
            option7.color = colorList;
            //option6.tooltip = tooltip;
           //	option6.series[0].itemStyle = itemStyle;
            //option7.tooltip = tooltip;
           //	option7.series[0].itemStyle = itemStyle;
           	//设置mytool
           	resulMap.option1.toolbox.feature.myTool=jQuery.extend(true, {}, myTool);
        	resulMap.option1.toolbox.feature.myTool.selType = 1;
         	resulMap.option2.toolbox.feature.myTool=jQuery.extend(true, {}, myTool);
        	resulMap.option2.toolbox.feature.myTool.selType = 2;
         	resulMap.option3.toolbox.feature.myTool=jQuery.extend(true, {}, myTool);
        	resulMap.option3.toolbox.feature.myTool.selType = 3;
         	resulMap.option4.toolbox.feature.myTool=jQuery.extend(true, {}, myTool);
        	resulMap.option4.toolbox.feature.myTool.selType = 4;
         	resulMap.option5.toolbox.feature.myTool=jQuery.extend(true, {}, myTool);
        	resulMap.option5.toolbox.feature.myTool.selType = 5;
         	option6.toolbox.feature.myTool=jQuery.extend(true, {}, myTool);
        	option6.toolbox.feature.myTool.selType =6;
         	option7.toolbox.feature.myTool=jQuery.extend(true, {}, myTool);
        	option7.toolbox.feature.myTool.selType =7;
         	
			echart1 = echarts.init(document.getElementById('chartOne_one'));
			echart1.setOption(resulMap.option1);
			echart2 = echarts.init(document.getElementById('chartTwo_one'));
			echart2.setOption(resulMap.option2);
			echart3 = echarts.init(document.getElementById('chartTwo_two'));
			echart3.setOption(resulMap.option3);
			echart4 = echarts.init(document.getElementById('chartThree_one'));
			echart4.setOption(resulMap.option4);
			echart5 = echarts.init(document.getElementById('chartThree_two'));
			echart5.setOption(resulMap.option5);
			echart6 = echarts.init(document.getElementById('chartFour_one'));
			echart6.setOption(option6);
			echart7 = echarts.init(document.getElementById('chartFour_two'));
			echart7.setOption(option7);	
			
			echart1.on('click', function (e) {
				var xAxisData = resulMap.option1.xAxis[0].data[e.dataIndex];
                var xAxisCode = suppliserList[e.dataIndex];
				var objs = new Object();
				objs.type = 1 ;
				objs.name = xAxisData;
				objs.code = xAxisCode;
				loadChildChart(objs);
		      	});
			echart2.on('click', function (e) {
				var xAxisData = resulMap.option2.xAxis[0].data[e.dataIndex];
                var xAxisCode = partList[e.dataIndex];
				var objs = new Object();
				objs.type = 2 ;
				objs.name = xAxisData;
				objs.code = xAxisCode;
				loadChildChart(objs);
		      	});
			echart3.on('click', function (e) {
				var legendData = resulMap.option3.xAxis[0].data[e.dataIndex];;
				var objs = new Object();
				objs.type = 3 ;
				objs.name = legendData;
				loadChildChart(objs);
		      	});
		}
 
 function loadChartOne_two(resulMap){
	    var analysisT= $("input[name='analysisType']:checked",navTab.getCurrentPanel()).val(); 
	    var colorList = [
		   			  '#3399FF','#C1232B','#333366','#333366','#C1232B',
		   			   '#333366','#C1232B','#333366','#C1232B','#333366',
		   			   '#C1232B','#333366','#C1232B','#333366','#C1232B',
		   			   '#333366','#C1232B','#333366','#C1232B','#333366'
		   			];
		var childChart1,childChart2,childChart3,childChart4,childChart5,childChart6,childChart7;
		var childOption1,childOption2,childOption3,childOption4,childOption5,childOption6,childOption7;

		var supplierCodeList = resulMap.supplierCodeList;
		var partCodeList = resulMap.partCodeList;
		var supDateList = resulMap.supDateList;
		var partDateList = resulMap.partDateList;
		childOption6 = resulMap.option6;
		childOption7 = resulMap.option7;
        var itemStyle = {
         	normal: { 
         		color: function(params) {
         			return colorList[(params.dataIndex)%2] ;
         			}
         			} 
         };
         
         var createTooltip = function(opts){
	    	   var tooltip_r = {
	      	    		trigger: 'axis',
	      	    		formatter: function(params) {
	      			           // console.warn(params[0].dataIndex+1);
	      			            var sname = params[0].name ;
	      			            var svalue ="";
	      			            var lab = "";
	      			            if(analysisT ==0){
	      			            	lab ="(批)";
	      			            }else{
	      			            	lab ="(个)";
	      			            }
	      			            for(var i in params){
	      			            	  if(i == 0){
	      			            		  svalue += params[i].seriesName + ' : ' + params[i].value+lab + '<br/>';
	      			            		  if(sname!= '其他'){
	      	   			            		  svalue += "到货总数："+ opts[sname] + lab + '<br/>';
	      			            		  }
	      			            	  }else{
	      		                          svalue += params[i].seriesName + ' : ' + params[i].value+'%' + '<br/>';
	      			            	  }
	      			            	}
	      			        
	      			            return sname +' <br/>' + svalue ;
	      			        },
	      	      };
	    	   
	    		return  $.extend(tooltip_r,opts,{});
	       }
        
        resulMap.option1.color = colorList;
        resulMap.option1.tooltip = createTooltip(resulMap.total1);
        resulMap.option2.color = colorList;
        resulMap.option2.tooltip = createTooltip(resulMap.total2);
        resulMap.option3.color = colorList;
        resulMap.option3.tooltip = createTooltip(resulMap.total3);
        resulMap.option4.color = colorList;
        resulMap.option4.tooltip = createTooltip(resulMap.total4);
        resulMap.option5.color = colorList;
        resulMap.option5.tooltip = createTooltip(resulMap.total5);
        childOption6.series[0].itemStyle = itemStyle;
        childOption7.series[0].itemStyle = itemStyle;
        childChart1 = echarts.init(document.getElementById('childOne_one'));
        childChart1.setOption(resulMap.option1);
        childChart2 = echarts.init(document.getElementById('childOne_two'));
        childChart2.setOption(resulMap.option2);
        childChart3 = echarts.init(document.getElementById('childOne_three'));
        childChart3.setOption(resulMap.option3);
        childChart4 = echarts.init(document.getElementById('childOne_four'));
        childChart4.setOption(resulMap.option4);
        childChart5 = echarts.init(document.getElementById('childOne_five'));
        childChart5.setOption(resulMap.option5);
        childChart6 = echarts.init(document.getElementById('childOne_six'));
        childChart6.setOption(childOption6);
        childChart7 = echarts.init(document.getElementById('childOne_seven'));
        childChart7.setOption(childOption7);	
		
        childChart1.on('click',function (e){
        	openCTestInstentDetail(resulMap.option1,e,1,supplierCodeList);
        } );
        childChart2.on('click', function (e){
        	openCTestInstentDetail(resulMap.option2,e,2,partCodeList);
        });
        childChart3.on('click', function (e){
        	openCTestInstentDetail(resulMap.option3,e,3,"");
        });
        childChart4.on('click', function (e){
        	openCTestInstentDetail(resulMap.option4,e,4,supDateList);
        });
        childChart5.on('click', function (e){
        	openCTestInstentDetail(resulMap.option5,e,5,partDateList);
        });
        childChart6.on('click', function (e){
        	openCTestInstentDetail(resulMap.option6,e,6,supplierCodeList);
        });
        childChart7.on('click', function (e){
        	openCTestInstentDetail(resulMap.option7,e,7,partCodeList);
        });
        
        function openCTestInstentDetail(obj,e,type,code){
        	var xAxisData = obj.xAxis[0].data[e.dataIndex];
			var factory = $("#childFactory",navTab.getCurrentPanel()).val();
	    	var productType = $("#childProductType",navTab.getCurrentPanel()).val();
	    	var supplierCode = $("#childSupplierCode",navTab.getCurrentPanel()).val();
	    	var supplier = $("#childSupplier",navTab.getCurrentPanel()).val();
	    	var partType = $("#childPartType",navTab.getCurrentPanel()).val();
	    	var partNumber = $("#childPartNumber",navTab.getCurrentPanel()).val();
	    	var partName = $("#childPartName",navTab.getCurrentPanel()).val();
	    	var partClass = $("#childPartClass",navTab.getCurrentPanel()).val();
	    	var partVersion = $("input[name='childPartVersion']:checked",navTab.getCurrentPanel()).val();
	    	var dateType = $("#childSelectDate",navTab.getCurrentPanel()).val();
	    	var dateT = $("#childDateT",navTab.getCurrentPanel()).val();
	    	var isNew = $("#childIsNew",navTab.getCurrentPanel()).val();
	    	var lotStartTime = $("#childLotStartTime",navTab.getCurrentPanel()).val();
	    	var lotEndTime = $("#childLotEndTime",navTab.getCurrentPanel()).val();
	    	var columnNum = 5;
	    	var analysisType = $("input[name='childAnalysisType']:checked",navTab.getCurrentPanel()).val(); 
	    	var defectType = "";
	    	var daytime ="";
			if(type==1 || type ==6){
				supplier = xAxisData;
				supplierCode = code[e.dataIndex];
			}else if(type==2 || type == 7){
				partName = xAxisData;
				partNumber = code[e.dataIndex];
			}else if(type==3){
				defectType = xAxisData;
			}else if(type==4||type==5){
				dateT = xAxisData.replace("周","");
			}
			 /*   else if(type==6){
				if(e.dataIndex%2==0){
					supplier = xAxisData;
					daytime = "本";
				}else if(e.dataIndex%2==1){
					supplier = xAxisData;
					daytime = "上";
				}
			}else if(type==7){
				if(e.dataIndex%2==0){
					partName = xAxisData;
					daytime = "本";
				}else if(e.dataIndex%2==1){
					partName = xAxisData;
					daytime = "上";
				}
			} */
			navTab.openTab("testInstentDetailTab", "quality/testInstance/testInstentDetail.do", {title:"进料明细数据报表",fresh:true,data:{type:type,factory:factory,productType:productType,supplier:supplier,partType:partType,partClass:partClass,partName:partName,partVersion:partVersion,dateType:dateType,dateT:dateT,isNew:isNew,lotStartTime:lotStartTime,lotEndTime:lotEndTime,columnNum:columnNum,analysisType:analysisType,defectType:defectType,dayTime:daytime,partNumber:partNumber,supplierCode:supplierCode,distinction:1}});
        }
		}
 
 function loadChildChart(e){
	 var height = $("#searchContentDiv",navTab.getCurrentPanel()).outerHeight(true)+2;
	 var childContent = $("#childContent",navTab.getCurrentPanel());
    if(e != null){
    	 if(childContent.css("display")=="none"){
        	 childContent.css("top",height);
        	 childContent.show(1000);
        	 setTimeout(function(){
		    	 loadTestInstanceChart(e);
		    	 clearChildForm();
		     },1000);
        	
         }else if(e.type != null){
    			 loadTestInstanceChart(e);
    			 clearChildForm();
    	 }
    	
    }else{
    	 if(childContent.css("display")=="none"){
        	 childContent.css("top",height);
        	 childContent.show(1000);
        	
         }else if(childContent.css("display")=="block"){
        	 childContent.hide(1000);
         }
    }
 }
 function  clearChildForm(){
	 $("#childContent",navTab.getCurrentPanel()).find("input[type!=radio]").val("");
 }
 function loadChildTestInstanceChart(obj){
	var factory = $("#childFactory",navTab.getCurrentPanel()).val();
 	var productType = $("#childProductType",navTab.getCurrentPanel()).val();
 	var supplierCode = $("#childSupplierCode",navTab.getCurrentPanel()).val();
 	var supplier = $("#childSupplier",navTab.getCurrentPanel()).val();
 	var partType = $("#childPartType",navTab.getCurrentPanel()).val();
	var partNumber = $("#childPartNumber",navTab.getCurrentPanel()).val();
 	var partName = $("#childPartName",navTab.getCurrentPanel()).val();
 	var partClass = $("#childPartClass",navTab.getCurrentPanel()).val();
 	var partVersion = $("input[name='childPartVersion']:checked",navTab.getCurrentPanel()).val();
 	var dateType = $("#childSelectDate",navTab.getCurrentPanel()).val();
 	var dateT = $("#childDateT",navTab.getCurrentPanel()).val();
 	var isNew = $("#childIsNew",navTab.getCurrentPanel()).val();
 	var lotStartTime = $("#childLotStartTime",navTab.getCurrentPanel()).val();
 	var lotEndTime = $("#childLotEndTime",navTab.getCurrentPanel()).val();
 	var columnNum = 5;
 	var analysisType = $("input[name='childAnalysisType']:checked",navTab.getCurrentPanel()).val();
 	var defectType ="";
 	if(dateType ==""){
 		alert("请选择时间维度！");
 		return ;
 	}
 	if(dateType !=""){
 		if(dateT == ''){
     		alert("请选择时间维度！");
     		return ;
     	}
 	}
 	var url = "<c:url value='quality/testInstance/lodeSupplierDefect.do' />";
	$.post(url,{factory:factory,productType:productType,supplier:supplier,partType:partType,partClass:partClass,partName:partName,partVersion:partVersion,dateType:dateType,dateT:dateT,isNew:isNew,lotStartTime:lotStartTime,lotEndTime:lotEndTime,columnNum:columnNum,analysisType:analysisType,defectType:defectType,partNumber:partNumber,supplierCode:supplierCode,distinction:1},function(data){
	    		if(data.result==0){
	     			  loadChartOne_two(data);
	    		}
	    		if(data.result==-1){
	    			alert("查询出错，请联系管理员！");
	    		}
	    	});
 }
	</script>
 
<div id="shiftGroupLoad">
<script type="text/javascript">
$(function(){	
	$('#partTypeList', navTab.getCurrentPanel()).dropdownlist({
	    id:'partType',
	    columns:2,
	    selectedtext:'',
	    listboxwidth:300,//下拉框宽
	    maxchecked:100,
	    checkbox:true,
	    listboxmaxheight:400,
	    width:120,
	    requiredvalue:[],
	    selected:[],
	    data:${partMap},//数据，格式：{value:name}
	    onchange:function(text,value){
	    }
	});						
});

</script>
</div>
