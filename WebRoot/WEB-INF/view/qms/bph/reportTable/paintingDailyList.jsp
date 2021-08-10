<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
	function paintingDaily_checkData(){
	
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();

		if(endTime<startTime){
			alert("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("")
			return false;
		}
		//ljy添加
		var thid=$("#tab3").attr("hid");
		var chid=$("#columnline3").attr("hid");
		var chid2=$("#lines3").attr("hid");
		if(thid==0){
			$("#tab3").attr("hid","1");
			$("#tab3").css("display","");
			if(chid==1){
				$("#columnline3").attr("hid","0");
				$("#columnline3").css("display","none");
			}
			if(chid2==1){
				$("#lines3").attr("hid","0");
				$("#lines3").css("display","none");
			}
		}
		//
		$("#paintingDaily").submit();
		
	}
/**	
	function getPaintingDailyArea() {
	var url = "<c:url value='commonSelected/getArea.do' />";
	$("#pdarea", navTab.getCurrentPanel()).load(url,{factory: $("#pdfactoryS", navTab.getCurrentPanel()).val()});   
	}
	
	function getPaintingDailyGroup() {
	var url = "<c:url value='commonSelected/getShiftGroupByFactory.do' />";
	$("#pdgroup", navTab.getCurrentPanel()).load(url,{factory: $("#pdfactoryS", navTab.getCurrentPanel()).val()});   
	}
**/	
	function exportExcel(url){    

		var myForm = document.createElement("form");
		myForm.action= url;
		myForm.method="post"; 
		myForm.target="noexistForm"; 
		var myInput;
		
		var objs = $("#paintingDaily input",navTab.getCurrentPanel());
		var objs2 = $("#paintingDaily select",navTab.getCurrentPanel());
		
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
		chenk();
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
	    var badnum=$("#badnum").val();
		var badnum2=$("#badnum2").val();
		if(badnum!='' && badnum2!=''){
			if(badnum>badnum2){
				alert("第一个数不能大于第二个数！");
				return false;
			}
		}
		var thid=$("#tab4").attr("hid");
		var chid=$("#columnline4").attr("hid");
		var chid2=$("#lines4").attr("hid");	
		if(chid==0){
			$("#columnline4").attr("hid","1");
			$("#columnline4").css("display","");
			if(thid==1){
				$("#tab4").attr("hid","0");
				$("#tab4").css("display","none");
			}
			if(chid2==1){
				$("#lines4").attr("hid","0");
				$("#lines4").css("display","none");
			}
		}
		chid=$("#columnline4").attr("hid");
		if(chid==1){			
			var url = "<c:url value='base/commonselect/findunhealthypt.do'/>";
			$.post(url, $("#paintingDaily").serialize(), function(data) {
				if (data.result == 0) {
					columnlinechar("columnlinechar4", data.info);
				} else {
					alertMsg.error("查询出错，请联系管理员");
					return ;
				}
			});
		}
	}
	function arrange(){	
		var thid=$("#tab4").attr("hid");
		var chid=$("#columnline4").attr("hid");
		var chid2=$("#lines4").attr("hid");
		if(chid2==0){
			$("#lines4").attr("hid","1");
			$("#lines4").css("display","");
			if(thid==1){
				$("#tab4").attr("hid","0");
				$("#tab4").css("display","none");
			}
			if(chid==1){
				$("#columnline4").attr("hid","0");
				$("#columnline4").css("display","none");
			}
		}
		chid2=$("#lines4").attr("hid");
		if(chid2==1){				
			var url = "<c:url value='base/commonselect/findarrangept.do'/>";
			$.post(url, $("#paintingDaily").serialize(), function(data) {
				if (data.result == 0) {
					columnline("linechar4",data.info,"pty");
				} else {
					alertMsg.error("查询出错，请联系管理员");
					return ;
				}
			});
		}
	}
	function chenk(){
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
	}
</script>
<div class="pageHeader">
	<form id="paintingDaily" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/commonselect/paintingDailyList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<jsp:include page="../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="1" name="fgroup"/>
					<jsp:param value="0" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
			</jsp:include>	
			<tr>
				
				<td>
					产品型号： 
				</td>
				<td>
					<input type="text" name="typeS" value="${param.typeS}"/>
				</td>
				<td>
					产品名称： 
				</td>
				<td>
					<input type="text" name="productNameS" value="${param.productNameS}"/>
				</td>	
				<td>
					不良现象： 
				</td>
				<td>
					<input type="text" name="defectS" value="${param.defectS}"/>
				</td>			
			</tr>
			<tr>
				<td>
					发生日期： 
				</td>
				<td>
					<input type="text" name="startTime" id="startTime" placeholder="请输入日期" size="15" onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.startTime}" type="date" pattern="yyyy-MM-dd "/>"/>
					至 <input type="text" name="endTime" id="endTime" placeholder="请输入日期" size="15" onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.endTime}" type="date" pattern="yyyy-MM-dd "/>"/>
				</td>
				<td>
					不良数： 
				</td>
				<td>
					<input type="text" name="badnum" id="badnum" size="5"/>
					至 <input type="text" name="badnum2" id="badnum" size="5"/>
				</td>
				<td colspan="4">
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="paintingDaily_checkData()">查找</button></div></div>
				<a class="button" href="#" onclick="exportExcel('base/commonselect/paintingDailyExcelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="arrange()">时间序列图</button></div></div>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="unhealthy()">不良现象排列图</button></div></div>
				</td>
				</tr>	
		</table>
	</div>
	</form>
</div>
<div class="pageContent" id="tab4" hid="1">
	
	<table class="table" width="100%" layoutH="140">
		<thead>
			<tr>
			    <th width="3%">选择</th>
				<th width="6%">发生日期</th>
				<th width="5%">工厂</th>
				<th width="5%">发生车间</th>
				<th width="6%">发生班组</th>
				<th width="6%">班长</th>
				<th width="6%">检验员</th>
				<th width="6%">工单</th>
				<th width="5%">产品编号</th>
				<th width="10%">产品名称</th>
				<th width="4%">总数</th>
				<th width="4%">一次检验数</th>
				<th width="4%">合格数</th>
				<th width="4%">不良数</th>
				<th width="8%">不良现象</th>
				<th width="5%">记录人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key">
					</td>
					<td ><fmt:formatDate value="${o.dateT}" type="date" pattern="yyyy-MM-dd "/></td>
					<td>${o.factoryS}</td>
					<td>${o.areaS}</td>
					<td>${o.groupS}</td>
					<td >${o.groupLeaderS}</td>				
					<td>${o.checkManS}</td>
					<td>${o.orderNumberS}</td>
					<td>${o.productNumberS}</td>
					<td>${o.productNameS}</td>
					<td>${o.totalQtyI}</td>
					<td>${o.checkQtyI}</td>
					<td >${o.qualityQtyI}</td>
					<td >${o.defectQtyI}</td>
					<td>${o.defectS}</td>
					<td >${o.recordManS}</td>

				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<div class="pageContent" id="columnline4" hid="0" style="display: none">
	<div id="columnlinechar4" class="singleChartDiv"></div>
</div>
<div class="pageContent" id="lines4" hid="0" style="display: none">
	<div id="linechar4" class="singleChartDiv"></div>
</div>
