<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
	$(function(){
		var json=(new Function("","return "+'${info}'))();
		loadtype([${infosel}],json);
	});
	function assembly_checkData(){
	
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();

		if(endTime<startTime){
			alert("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("")
			return false;
		}
		//ljy添加
		var thid=$("#tab").attr("hid");
		var chid=$("#columnline").attr("hid");
		var chid2=$("#lines").attr("hid");
		if(thid==0){
			$("#tab").attr("hid","1");
			$("#tab").css("display","");
			if(chid==1){
				$("#columnline").attr("hid","0");
				$("#columnline").css("display","none");
			}
			if(chid2==1){
				$("#lines").attr("hid","0");
				$("#lines").css("display","none");
			}
		}
		//
		$("#assembly").submit();
	}
/**	
	function getAssemblyArea() {
	var url = "<c:url value='commonSelected/getArea.do' />";
	$("#asbArea", navTab.getCurrentPanel()).load(url,{factory: $("#factoryS",navTab.getCurrentPanel()).val()});   
	}
	
	function getAssemblyGroup() {
	var url = "<c:url value='commonSelected/getShiftGroupByFactory.do' />";
	$("#asgroup", navTab.getCurrentPanel()).load(url,{factory: $("#factoryS",navTab.getCurrentPanel()).val()});   
	}
**/	
	function exportExcel(url){    
	    var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	    var endTime = $("#endTime", navTab.getCurrentPanel()).val();
	    if(startTime==''){
	    	alert("请选择开始时间！");
	    	return;
	    }
	    if(endTime==''){
	    	alert("请选择结束时间！");
	    	return;
	    }
	    
		var myForm = document.createElement("form");
		myForm.action= url;
		myForm.method="post"; 
		myForm.target="noexistForm"; 
		var myInput;
		
		var objs = $("#assembly input",navTab.getCurrentPanel());
		var objs2 = $("#assembly select",navTab.getCurrentPanel());
		
		for(var i = 0 ; i< objs.length+objs2.length ; i++){
			var $obj = null;
			if(i>=objs.length){
				$obj = $(objs2[i-objs.length]);	
			}else{
				$obj = $(objs[i]);			
			}
			var v = $obj.val();
			if(v==null || v==""){
				v="";
			}
			if($obj.attr("type")=="checkbox"){
				if(!$obj.attr("checked")){
					v="";
				}
			}
			
			myInput = document.createElement("input");
			myInput.setAttribute("name", $obj.attr("name"));
			myInput.setAttribute("value", v);
			myForm.appendChild(myInput);
		}
		document.body.appendChild(myForm);
		myForm.submit();
	}
	function unhealthy(){//不良现象；hid为1时显示，0为隐藏
		showOrhidden(0);
		var baseFactory = $('select[name="baseFactory"]', navTab.getCurrentPanel()).val();
		if(baseFactory==""){
	        alert("请选择工厂");
	        return false;
	    }
		var baseArea = $('select[name="baseArea"]', navTab.getCurrentPanel()).val();
		if(baseArea==""){
	        alert("请选择车间");
	        return false;
	    }
		var baseGroup = $('select[name="baseGroup"]', navTab.getCurrentPanel()).val();
		if(baseGroup==""){
	        alert("请选择班组");
	        return false;
	    }
		 var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		 var endTime = $("#endTime", navTab.getCurrentPanel()).val();
		 if(startTime==''){
		    alert("请选择开始时间！");
		    return;
		 }
		 if(endTime==''){
		    alert("请选择结束时间！");
		    return;
		 }
		var url = "<c:url value='base/commonselect/findunhealthy.do'/>";
		$.post(url, $("#assembly").serialize(), function(data) {
			if (data.result == 0) {
				columnlinechar("columnlinechar", data.info,"1");//hid为1时代表当前的这个页面调用
			} else {
				alertMsg.error("查询出错，请联系管理员");
				return ;
			}
		});
	}
	function unhealthyType(){//不良型号；hid为1时显示，0为隐藏
		showOrhidden(2);
		var baseFactory = $('select[name="baseFactory"]', navTab.getCurrentPanel()).val();
		if(baseFactory==""){
	        alert("请选择工厂");
	        return false;
	    }
		var baseArea = $('select[name="baseArea"]', navTab.getCurrentPanel()).val();
		if(baseArea==""){
	        alert("请选择车间");
	        return false;
	    }
		var baseGroup = $('select[name="baseGroup"]', navTab.getCurrentPanel()).val();
		if(baseGroup==""){
	        alert("请选择班组");
	        return false;
	    }
		 var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		 var endTime = $("#endTime", navTab.getCurrentPanel()).val();
		 if(startTime==''){
		    alert("请选择开始时间！");
		    return;
		 }
		 if(endTime==''){
		    alert("请选择结束时间！");
		    return;
		 }
		var url = "<c:url value='base/commonselect/findunhealthyType.do'/>";
		$.post(url, $("#assembly").serialize(), function(data) {
			if (data.result == 0) {
				columnlinechar("columnlineTypechar", data.info,"1");//hid为1时代表当前的这个页面调用
			} else {
				alertMsg.error("查询出错，请联系管理员");
				return ;
			}
		});
	}
	function arrange(){
		showOrhidden(1);
		var baseFactory = $('select[name="baseFactory"]', navTab.getCurrentPanel()).val();
		if(baseFactory==""){
	        alert("请选择工厂");
	        return false;
	    }
		var baseArea = $('select[name="baseArea"]', navTab.getCurrentPanel()).val();
		if(baseArea==""){
	        alert("请选择车间");
	        return false;
	    }
		var baseGroup = $('select[name="baseGroup"]', navTab.getCurrentPanel()).val();
		if(baseGroup==""){
	        alert("请选择班组");
	        return false;
	    }
		var url = "<c:url value='base/commonselect/findarrange.do'/>";
		$.post(url, $("#assembly").serialize(), function(data) {
			if (data.result == 0) {
				columnline("linechar",data.info,"zz");
			} else {
				alertMsg.error("查询出错，请联系管理员");
				return ;
			}
		});
		$("#bad_Reason","#assembly").val('');
	}
	function loadPtAnalyCondition(){
		var url = "<c:url value='base/commonselect/findModel.do' />";
		var productType = $('select[name="productTypeS"]', navTab.getCurrentPanel()).val();
		$.post(url, {productType:productType}, function(data) {
			if (data.result == 0) {
				loadtype('',data.info);
			} else {
				alertMsg.error("查询出错，请联系管理员");
				return ;
			}
		});
	}
	function loadtype(arr,json){
		$('#partTypeList', navTab.getCurrentPanel()).dropdownlist({
            id:'partTypeListTxt',
            columns:3,
            selectedtext:'',
            listboxwidth:450,//下拉框宽
            maxchecked:100,
            checkbox:true,
            listboxmaxheight:400,
            width:120,
            requiredvalue:[],
            selected:arr==""?[]:arr,//默认选中
            data:json,//数据，格式：{value:name}
            onchange:function(text,value){
            }
        }); 
	}
	function showOrhidden(obj){
		var arr=new Array("columnline","lines","typeunhealthy","tab")
		$.each(arr,function(i,v) {
            if(obj==i){
            	$("#"+v).css("display", "block")
            }else{
            	$("#"+v).css("display", "none")
            }
        });
	}
</script>
<div class="pageHeader" style="position:static">
	<form id="assembly" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/commonselect/assemblyList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			 <jsp:include page="../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="1" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
				</jsp:include>			  
			<tr>
				<td>
					责任判定： 
				</td>
				<td>
					<select name="dutyS" id="dutyS">
					
						<option value="">请选择</option>
						<option value="所有">所有</option>
						<option value="供应商">供应商</option>
						<option value="内部">内部</option>
					</select>
                    <script type="text/javascript">
                        $("#dutyS", navTab.getCurrentPanel()).val("${vo.dutyS}"); 
                    </script>
				</td>
				<!-- <td>
					产品型号： 
				</td>
				<td>
					<input type="text" name="productTypeS" id="productTypeS" value="${vo.productTypeS}"/>
				</td>
				<td>
					产品名称： 
				</td>
				<td>
					<input type="text" name="itemNameS" size="15" value="${vo.itemNameS}" size="15"/>
				</td> -->
				<th>机型类别：</th>
                <td>
					<select name="productTypeS" onchange="loadPtAnalyCondition();">
						<option value="">请选择</option>
						<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" <c:if test="${o.machineType==vo.productTypeS}">selected</c:if> >${o.machineType}</option>
						</c:forEach>
					</select>
    			</td> 	
    			<th>产品型号：</th>                       
                <td>
                	<div id="partTypeList" class="dropdownlist"></div>
                </td> 		
			</tr>
			<tr>
				<td>
					发生日期： 
				</td>
				<td>
					<input type="text" name="startTime" id="startTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" size="10" value="<fmt:formatDate value="${vo.startTime}" type="date" pattern="yyyy-MM-dd "/>"/>
					至 <input type="text" name="endTime" id="endTime" placeholder="请输入日期"  onclick="laydate()" readonly="true"  size="10" value="<fmt:formatDate value="${vo.endTime}" type="date" pattern="yyyy-MM-dd "/>"/>
				</td>
				<td>
					不良数： 
				</td>
				<td>
					<input type="text" name="startnum" id="startnum" size="5" value="${vo.startnum}"/>
					至 <input type="text" name="endnum" id="endnum" size="5" value="${vo.endnum}"/>
				</td>
				<td colspan="4">
					<input id="bad_Reason" name="bad_Reason" type="hidden">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="assembly_checkData()">查找</button></div></div>
					<a class="button" href="#" onclick="exportExcel('base/commonselect/assemblyExcelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="unhealthy()">不良现象排列图</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="unhealthyType()">不良型号排列图</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="arrange()">时间序列图</button></div></div>
				</td>
			</tr>	
		</table>
	</div>
	<input name="marking" type="hidden" value="组装退次率">
	<input name="type_s" type="hidden" value="组装">
	</form>
</div>
<div id="tab" hid="1" style="display: " class="pageContent">
	<div>
		<table class="table" width="100%" layoutH="140">
			<thead>
				<tr>
				    <th width="3%">选择</th>
					<th width="7%">发生日期</th>
					<th width="6%">工厂</th>
					<th width="6%">发生车间</th>
					<th width="6%">成品工单</th>
					<th width="6%">成品型号</th>
					<th width="6%">成品线</th>
					<th width="6%">零部件名称</th>
					<th width="6%">不良数量</th>
					<th width="7%">不良现象</th>
					<th width="5%">责任判定</th>
					<th width="7%">责任班组1</th>
					<th width="6%">责任班组2</th>
					<th width="6%">责任班组3</th>
					<th width="6%">品质判定人</th>
					<th width="6%">记录人</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="o">
					<tr>
						<%--需维护资料--%>
						<td>
							<input type="radio" group="code" name="key">
						</td>
						<td width="6%"><fmt:formatDate value="${o.dateT}" type="date" pattern="yyyy-MM-dd "/></td>
						<td>${o.factoryS}</td>
						<td>${o.areaS}</td>
						<td>${o.orderNumbers}</td>
						<td>${o.productTypeS}</td>
						<td>${o.lineS}</td>	
						<td>${o.itemNameS}</td>				
						<td >${o.defectQtyI}</td>
						<td>${o.defectS}</td>
						<td>${o.dutyS}</td>
						<td >${o.dutyGroup1S}</td>
						<td>${o.dutyGroup2S}</td>
						<td >${o.dutyGroup3S }</td>
						<td >${o.checkManS}</td>
						<td >${o.recordManS}</td>
	
					</tr>		
				</c:forEach>
			</tbody>
		</table>
	</div>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<div class="pageContent" id="columnline" hid="0" style="display: none">
	<div id="columnlinechar" class="singleChartDiv"></div>
</div>
<div class="pageContent" id="lines" hid="0" style="display: none">
	<div id="linechar" class="singleChartDiv"></div>
</div>
<div class="pageContent" id="typeunhealthy" hid="0" style="display: none">
	<div id="columnlineTypechar" class="singleChartDiv"></div>
</div>

