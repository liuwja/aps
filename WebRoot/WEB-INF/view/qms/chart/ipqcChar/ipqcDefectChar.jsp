<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style>

#gtitle{
    margin-left: 30px;
    font-size: 20px;
    font-weight: bold;
    padding-top: 10px;
}

#gtitle2{
    padding-left: 30px;
    font-size: 15px;
    font-weight: bold;
    padding-top: 10px;
}

</style>


<script type="text/javascript">
jQuery(document).ready(function(){
	showIpqcDefectChar();
});

function showIpqcDefectChar(){
	var char_width = document.body.clientWidth - $("#sidebar").width()-80;
	var factory =$("#factory",navTab.getCurrentPanel()).val();
	var shiftGroupTxt =$("#shiftGroupTxt",navTab.getCurrentPanel()).val();
	var startTime = $("#startTime",navTab.getCurrentPanel()).val();
	var endTime = $("#endTime",navTab.getCurrentPanel()).val();
	var url = "<c:url value='base/ipqcChar/loadIpqcDefectChar.do'/>";
	$.post(url, {factory:factory,shiftGroupTxt:shiftGroupTxt,startTime:startTime,endTime:endTime,width:char_width}, function(data) {

		if (data.result == 0) {
			showChart("ipqcDefectChar", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}


</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="base/ipqcChar/loadIpqcDefectChar.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
	            <td>班组名称</td>
	            <td>
	                <input type="text" id="shiftGroupTxt" name="shiftGroupTxt" value="${vo.shiftGroupTxt }">
	            </td>
				<td>
					期间：
				</td>
				<td>				   	
				    <input type="hidden" id="factory" name="factory" value="${vo.factory }">			    
					<input type="text" id="startTime" name="startTime"  type="date" pattern="yyyy-MM-dd" onclick="laydate()" value="${vo.startTime }" readonly="readonly" />
					至
					<input type="text" id="endTime" name="endTime"  type="date" pattern="yyyy-MM-dd" onclick="laydate()" value="${vo.endTime }" readonly="readonly" />
				</td>				
				
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
	 
       

 <div style="width:100%;overflow:scroll;width:100%; height:600px;vertical-align:top">   
    <div style="vertical-align: top;">
                <div id="ipqcDefectChar" style="width:70%;height:500px;margin-left: 10px;margin-top: 10px;" >
    
                </div>
     </div>
    
</div>
