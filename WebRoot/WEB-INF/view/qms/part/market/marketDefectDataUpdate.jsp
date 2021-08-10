<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script>
function recordData(title) {
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	var endTime = $("#endTime", navTab.getCurrentPanel()).val();
	if (startTime != null && startTime != "" && startTime != undefined && endTime != null && endTime != "" && endTime != undefined) {
		var url = "quality/marketPart/updateData.do";
		$.post(url,{title:title, startTime:startTime, endTime:endTime},function(data,status){
	        if(0 == data.result){
	        	alertMsg.info(data.showResult);
	        }else{
	            alertMsg.info("操作失败");
	        }
	    });
	}
}
</script>
<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent tableF">
			<tr> 			
				<th>导入时间：</th>
                <td>
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期"  onclick="laydate()" value="${vo.startTime}" readonly="readonly"/>
					至
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期"  onclick="laydate()" value="${vo.endTime}" readonly="readonly"/>
    			</td>				
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr>		
				<td class="btn">
					<shiro:hasPermission name="sys:sum:importMes">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="recordData(1);">发货数据汇总</button></div></div>
					</shiro:hasPermission>
				</td>
				<td class="explain">选择发货数据汇总时，导入时间为下线时间。如导入时间选择为“2015-04-01”到“2015-05-01”则汇总时会选择下线时间为“2015-04-01”到“2015-05-01”</td>
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr>
				<td>
					<shiro:hasPermission name="sys:sum:importCRM">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="recordData(2);">维修数据汇总</button></div></div>
					</shiro:hasPermission>
				</td>
				<td class="explain">选择维修数据汇总时，导入时间为维修时间。如导入时间选择为“2015-04-01”到“2015-05-01”则汇总时会选择维修时间为“2015-04-01”到“2015-05-01”</td>
			</tr>
		</table>
	</div>
</div>