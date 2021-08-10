<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
	#listboxBody  tr:nth-of-type(odd) { background: #eee;}  
	#fixTableBody .smrt_tr{transition:background 0.5s;-moz-transition:background 0.5s;
	      -webkit-transition:background 0.5s; -o-transition:background 0.5s; }
	#fixTableBody .smrt_tr:hover{background:#A9E6EA;}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	//loadTimeChart();  
});

function checkTimeMatrixForTotal() {

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

	$("#timeMatrixForTotal", navTab.getCurrentPanel()).submit();
	return true;
}	
function exportExcel(url){    
	
	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var objs = $("#timeMatrixForTotal input",navTab.getCurrentPanel());
	var objs_select = $("#timeMatrixForTotal select",navTab.getCurrentPanel());	
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
	if(checkTimeMatrixForTotal())
	{
		document.body.appendChild(myForm);
		myForm.submit();
	}
}
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" id="timeMatrixForTotal" rel="pagerForm" action="timeMatrixTable/sigleMonthReTotalCount.do" method="post">
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
				<th>生产年月：</th>
                <td>
                    <input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime}" readonly="readonly" size="10"/> 
                    	至 
                    <input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.endTime }" readonly="readonly" size="10"/>                  
                </td> 
                <th>是否消耗配件：</th>
                <td>
                	<select id="isConsumedPart" name = "isConsumedPart">
                		<option value="">全选</option>
                		<option value="是" <c:if test="${vo.isConsumedPart == '是' }">selected = "selected"</c:if>>是</option>
                		<option value="否" <c:if test="${vo.isConsumedPart == '否' }">selected = "selected"</c:if>>否</option>
                	</select>
                </td>  			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="checkTimeMatrixForTotal();">查找</button></div></div>
				</td>	
				<td>
					<a class="button"  onclick="exportExcel('timeMatrixTable/excelOutput_sigleMonthReTotalCount.do');"    title="确定导出信息？"><span>导出EXCEL</span></a>					
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<table id="fixTable" class="fixTable">
		<thead>
			<tr>
				<th rowspan="2" style="width:70px;">生产年月</th>
				<th rowspan="2" style="width:70px;">生产台数</th>
				<th colspan="24">累计（单位：个月）</th>
			</tr>
			<tr>
				<th>1</th>
				<th>2</th>
				<th>3</th>
				<th>4</th>
				<th>5</th>
				<th>6</th>
				<th>7</th>
				<th>8</th>
				<th>9</th>
				<th>10</th>
				<th>11</th>
				<th>12</th>
				<th>13</th>
				<th>14</th>
				<th>15</th>
				<th>16</th>
				<th>17</th>
				<th>18</th>
				<th>19</th>
				<th>20</th>
				<th>21</th>
				<th>22</th>
				<th>23</th>
				<th>24</th>
			</tr>
		</thead>
		<tbody id="fixTableBody">
			<c:forEach items="${list}" var="o" begin="0" varStatus="status">
				<tr class="smrt_tr">
					<td>${o.baseMonth}</td>
					<td>${o.baseCount}</td>
					<c:forEach items="${o.reTotalCount}" var="num">
						<td>${num}</td>
					</c:forEach>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
</div>	
<script>
		var smrtFixwidth = $(".pageContent", navTab.getCurrentPanel()).width()-16;
		var smrtFixHeigh = $("#navTab").height() - $(".searchBar", navTab.getCurrentPanel()).height() -47;
		$("#fixTable",navTab.getCurrentPanel()).attr("width",smrtFixwidth-26);
		$("#fixTable",navTab.getCurrentPanel()).fixTable({
			fixRow:2,//固定行数
			fixColumn: 2,//固定列数
	        width:smrtFixwidth,//显示宽度
	        height:smrtFixHeigh//显示高度
	    });
		
	</script>