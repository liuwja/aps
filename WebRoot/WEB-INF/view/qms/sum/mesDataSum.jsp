<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	.explain{color:red;font-size:14px;font-family:"微软雅黑"}

</style>
<script type="text/javascript">
function sumMesDataMon(url)
{
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val();
	if( queryMonth == ''){
		alert('请选择汇总月份');		
		return;
	}
	if(queryMonth != ""){
		var start = new Date(queryMonth.replace('-','/').replace('-','/'));
		var end = new Date();
		if(start>end){
			alert('所选月份需小于当前月份');
			return ;
		}	
	}
	
   $.post(url,{queryMonth:queryMonth},function(data,status){
        if(0 == data.result){
           alert(data.showResult);
        }else{
            alert("操作失败");
        }
    });
 
}
function sumMesDataDay(url)
{
	var startTime = $("#startTime",navTab.getCurrentPanel()).val();
	var endTime = $("#endTime",navTab.getCurrentPanel()).val();
	if( startTime == ''){
		alert('请选择开始日期');
		return ;
	}
	if( endTime == ''){
		alert('请选择结束日期');
		return ;
	}
	if(startTime != "" && endTime != ""){
		var start = new Date(startTime.replace('-','/').replace('-','/'));
		var end = new Date(endTime.replace('-','/').replace('-','/'));
		if(start>end){
			alert('所选日期需小于当前日期');
			return ;
		}	
		if((end-start)/(24*60*60*1000)>31){
			alert('所选日期需小于一个月');
			return ;
		}
	}

    $.post(url,{startTime:startTime,endTime:endTime},function(data,status){
        if(0 == data.result){
           alert(data.showResult);
        }else{
            alert("操作失败");
        }
    });
 
}


</script>
<div class="pageHeader">
	<div class="searchBar">
		<table class="searchContent tableF">
			
			<tr> 			
				<th>汇总月份：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${list.queryMonth }" readonly="readonly"/>
    			</td>
    		</tr>
    		<tr><td  colspan="2"></td></tr>
    		<tr> 			
				<td>
					<shiro:hasPermission name="sys:mesSum:sumMesDataMon">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="sumMesDataMon('qms/importData/updateMesSumDataMonth.do');">MES月份汇总</button></div></div>
					</shiro:hasPermission>
				</td>
				<td class="explain">
					第一步：按月操作，如2015-04，操作过程为删除4月份计算结果，重新计算并记录4月份MES汇总数据
				</td>				
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr> 			
				<th>汇总日期：</th>
                <td>
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期"  onclick="laydate()" value="${list.queryDay }" readonly="readonly"/>至
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期"  onclick="laydate()" value="${list.queryDay }" readonly="readonly"/>
    			</td>
    		</tr>
    		<tr><td  colspan="2"></td></tr>
			<tr> 			
				<td >
					<shiro:hasPermission name="sys:mesSum:sumMesDataDay">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="sumMesDataDay('qms/importData/updateMesSumDataDay.do');">MES单日汇总</button></div></div>
					</shiro:hasPermission>
				</td>
				<td class="explain">
					第二步：按日期间操作，如2015-04-01~2015-04-30，操作过程为删除4月1号到4月30号的计算结果，重新计算并记录4月1号到4月30号MES汇总数据
				</td>				
			</tr>
			<tr><td colspan="2"></td></tr>
			
		</table>
	</div>
</div>