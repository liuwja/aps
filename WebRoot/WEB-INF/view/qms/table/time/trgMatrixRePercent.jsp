<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
	#listboxBody  tr:nth-of-type(odd) { background: #eee;}  
	#fixTableBody tr{transition:background 0.5s;-moz-transition:background 0.5s;
	      -webkit-transition:background 0.5s; -o-transition:background 0.5s; }
	#fixTableBody tr:hover{background:#A9E6EA;}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	//loadTimeChart();  
});

function checkTrgMatrixRePercent() {

	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
    if(productType==""){
    	alertMsg.info("请选择机型类别");
        return false;
    }
    
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val();
	if(queryMonth == ''){
		alertMsg.info('请选择维修截至月份');
		return false;
	}
	var curFormDom = $("#trgMatrixRePercent", navTab.getCurrentPanel());
	var statisData = $("input[name='statisData']:checked", navTab.getCurrentPanel()).val();
	//维修率跳转至维修数统计界面
	if(statisData == "repairCount"){
		curFormDom.attr("action","timeMatrixTable/trgMatrixReTotalCount.do");
	}
	curFormDom.submit();
	return true;
}		
function exportExcel(url){    
	
	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var objs = $("#trgMatrixRePercent input",navTab.getCurrentPanel());
	var objs_select = $("#trgMatrixRePercent select",navTab.getCurrentPanel());	
	var myInput;
	for(var i = 0 ; i< objs.length+objs_select.length ; i++){
		var $obj = null;
		if(i>=objs.length){
			$obj = $(objs_select[i-objs.length]);	
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
	if(checkTrgMatrixRePercent())
	{
		document.body.appendChild(myForm);
		myForm.submit();
	}	
}
</script>
<div class="pageHeader">

	<form onsubmit="return navTabSearch(this);" id="trgMatrixRePercent" rel="pagerForm" action="timeMatrixTable/trgMatrixRePercent.do" method="post">
	<div class="searchBar">
	
		<table class="searchContent">
			<tr>
                <th>机型类别：</th>
                <td>
					<select name="productType">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td> 			
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
    			</td> 	
    			<th>统计方式：</th>                       
                <td>
                    <select name="statisType">
                    	<option value="month" <c:if test="${'month' eq vo.statisType}">selected="selected"</c:if> >月份</option> 	
                    	<option value="quarter" <c:if test="${'quarter' eq vo.statisType}">selected="selected"</c:if> >季度</option> 	
                    	<option value="year" <c:if test="${'year' eq vo.statisType}">selected="selected"</c:if> >年份</option> 	
					</select>
                </td>
                <th>是否消耗配件：</th>                       
                <td>
                    <select name="isConsumedPart" id ="isConsumedPart">
                    	<option value="">全选</option> 	
                    	<option value="是" <c:if test="${'vo.isConsumedPart' eq '是'}">selected="selected"</c:if> >是</option> 	
                    	<option value="是" <c:if test="${'vo.isConsumedPart' eq '否'}">selected="selected"</c:if> >否</option> 	
					</select>
                </td>	    	    	
    			<th>统计数据：</th>   
                <td>
                	<label style="display:inline-block;width:50px;">
                		<input type="radio" name="statisData" value="repairCount" checked="checked"/>
                		维修数
                	</label>
                	<label style="display:inline-block;width:50px;">
                		<input type="radio" name="statisData" value="repairRate" <c:if test="${vo.statisData=='repairRate'}">checked="checked"</c:if>/>
                		维修率
                	</label>
                </td>          	
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="checkTrgMatrixRePercent();">查找</button></div></div>
				</td>	
				<td>
					<a class="button"  onclick="exportExcel('timeMatrixTable/excelOutput_trgMatrixRePercent.do');"    title="确定导出信息？"><span>导出EXCEL</span></a>					
				</td>				
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">

	<table id="fixTable" class="fixTable" width="1140">
		<thead>
			<tr>
				<th rowspan="5%">序号</th>
				<th rowspan="5%">操作</th>
				<th rowspan="2">生产月份</th>
				<th rowspan="2">生产台数</th>
				<th colspan="${fn:length(columnNo)}">
					<c:choose>
						<c:when test="${vo.statisType=='year'}">累计（单位：年）</c:when>
						<c:when test="${vo.statisType=='quarter'}">累计（单位：季度）</c:when>
						<c:otherwise>累计（单位：月）</c:otherwise>
					</c:choose>
				</th>
				<th rowspan="2">是否有效</th>
			</tr>
			<tr>
				<c:forEach items="${columnNo}" var="nober">
					<th>${nober}</th>
				</c:forEach>
			</tr>
		</thead>
		  <tbody id="fixTableBody">
			<c:forEach items="${list}" var="o" begin="0" varStatus="status">
<!--
				<tr>  
					<td>${status.index+1}</td>
					<td>
						<c:if test="${o.effective eq true}"> 
							<a href="timeMatrixTable/saveTimeMatrx.do?productionMonth=${o.baseMonth}&machineType=${vo.productType}"  
							target="ajaxTodo"  title="标记为无效">
							<span>有效</span></a>
						</c:if>
	                    <c:if test="${o.effective eq false}">
		                    <a href="timeMatrixTable/delete.do?productionMonth=${o.baseMonth}&machineType=${vo.productType}"  
		                    target="ajaxTodo"   title="取消标记为无效">
		                    <span>无效</span></a>
	                    </c:if>
					</td>
					<td>${o.baseMonth}</td>
					<td>${o.baseCount}</td>
					<c:forEach items="${o.repairTotalPercent}" var="num">
						<td>
						<c:if test="${ not empty num}">
						${num}
						</c:if>
						</td>
					</c:forEach>
					<td>
						<c:if test="${o.effective eq true}">
							是
						</c:if>
						<c:if test="${o.effective eq false}">
							否
						</c:if>
					</td>
				</tr>
-->

				<c:if test="${o.effective eq true}">
					<tr>
					<td>${status.index+1}</td>
					<td>
						<c:if test="${o.effective eq true}"> 
							<a href="timeMatrixTable/saveTimeMatrx.do?productionMonth=${o.baseMonth}&machineType=${vo.productType}&statisType=${vo.statisType}"  
							target="ajaxTodo"  title="标记为无效">
							<span>有效</span></a>
						</c:if>
					</td>
					<td>${o.baseMonth}</td>
					<td>${o.baseCount}</td>
					<c:forEach items="${o.repairTotalPercent}" var="num">
						<td>
						<c:if test="${ not empty num}">
						${num}
						</c:if>
						</td>
					</c:forEach>
					<td>
						<c:if test="${o.effective eq true}">
							是
						</c:if>
						<c:if test="${o.effective eq false}">
							否
						</c:if>
					</td>
					</tr>
				</c:if>
				<c:if test="${o.effective eq false}">
					<tr bgcolor="#666666">
					<td>${status.index+1}</td>
					<td>
						<a href="timeMatrixTable/delete.do?productionMonth=${o.baseMonth}&machineType=${vo.productType}&statisType=${vo.statisType}" target="ajaxTodo"   title="取消标记为无效">
		                <span>无效</span></a>
					</td>
					<td>${o.baseMonth}</td>
					<td>${o.baseCount}</td>
					<c:forEach items="${o.repairTotalPercent}" var="num">
						<td>
						<c:if test="${ not empty num}">
						${num}
						</c:if>
						</td>
					</c:forEach>
					<td>
						<c:if test="${o.effective eq true}">
							是
						</c:if>
						<c:if test="${o.effective eq false}">
							否
						</c:if>
					</td>
					</tr>
				</c:if>
				
			</c:forEach>
		</tbody>
	</table>
</div>	
<script>
		var tmpFixwidth = $(".pageContent", navTab.getCurrentPanel()).width()-16;
		$("#fixTable",navTab.getCurrentPanel()).attr("width",tmpFixwidth-26);
		var tmpFixHeigh = $("#navTab").height() - $(".searchBar", navTab.getCurrentPanel()).height() -47;
		$("#fixTable",navTab.getCurrentPanel()).fixTable({
			fixRow:2,//固定行数
			fixColumn: 2,//固定列数
	        width:tmpFixwidth,//显示宽度
	        height:tmpFixHeigh//显示高度
	    });
		
</script>