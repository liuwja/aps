<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	.explain{color:red;font-size:14px;font-family:"微软雅黑"}

</style>
<script type="text/javascript">
function sumData(url) {
	var statisticsMonth = $("#queryMonth", navTab.getCurrentPanel()).val();
	if(statisticsMonth == ''){
		alertMsg.info("请选择汇总月份");
		return ;
	}
    $.post(url,{statisticsMonth:statisticsMonth},function(data,status){
        if(0 == data.result){
           alert(data.showResult);
        }else{
            alertMsg.info("操作失败");
        }
    });
}

function updateData(url) {
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	var endTime = $("#endTime", navTab.getCurrentPanel()).val();
	if (isEmpty(startTime) || isEmpty(endTime)) {
		alertMsg.info("请选择导入月份");
        return false;
	}
	$.post(url,{startTime:startTime,endTime:endTime},function(data,status){
        if(0 == data.result){
        	alertMsg.info(data.showResult + "\n" + data.msg);
        }else{
        	alertMsg.error("操作失败");
        }
    });
}

function sumInstallData(index) {
    var startDate = $("#startDate", navTab.getCurrentPanel()).val();
    var endDate = $("#endDate", navTab.getCurrentPanel()).val();
    if(startDate == '' || endDate == ''){
        alertMsg.info("请选择汇总月份");
        return false;
    }
    var	url = "qms/importData/sumInstallTotal.do";
    $.post(url,{startDate:startDate, endDate:endDate},function(data,status){
        if(0 == data.result){
           alertMsg.info(data.showResult);
        }else{
            alertMsg.info("操作失败");
        }
    });
}
</script>
<div class="pageHeader" style="height: 550px">
	<div class="searchBar">
		<table class="searchContent tableF">
			<tr> 			
				<th>导入时间：</th>
                <td>
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期"  onclick="laydate()" value="${vo.startTime }" readonly="readonly"/>
					至
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期"  onclick="laydate()" value="${vo.endTime }" readonly="readonly"/>
    			</td>				
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr>		
				<td class="btn">
					<shiro:hasPermission name="sys:sum:importMes">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="updateData('qms/importData/importMesData.do');">MES导入</button></div></div>
					</shiro:hasPermission>
				</td>
				<td class="explain">第一步：按整月操作，如2015-04-01至2015-04-30，操作过程为删除原来4月份MES数据，重新导入4月份数据（开始时间慎选2015年6月1日之前）</td>
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr>
				<td>
					<shiro:hasPermission name="sys:sum:importCRM">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="updateData('qms/importData/importCRMData.do');">CRM导入</button></div></div>
					</shiro:hasPermission>
				</td>
				<td class="explain">第二步：按整月操作，如2015-04-01至2015-04-30，操作过程删除原来4月份CRM数据，重新导入4月份数据</td>
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr> 			
				<td >
					<shiro:hasPermission name="sys:sum:updateShipDate">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="updateData('qms/importData/updateShipDate.do');">更新发货时间</button></div></div>
					</shiro:hasPermission>
				</td>	
				<td class="explain">
					第三步：按整月操作，如2015-04-01至2015-04-30，操作过程为将发货时间为空的MES数据的发货时间，更新为生产库最新的发货时间
				</td>			
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr>
				<td colspan="3">
					<hr width="100%" size=1 color="red" align=center noshade> 
				</td>
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr> 			
				<th>汇总月份：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${queryMonth }" readonly="readonly"/>
    			</td>
    		</tr>
    		<tr><td  colspan="2"></td></tr>
    		<tr> 			
				<td>
					<shiro:hasPermission name="sys:sum:updateDownLineTotal">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="sumData('qms/importData/updateDownLineTotal.do');">下线汇总</button></div></div>
					</shiro:hasPermission>
				</td>
				<td class="explain">
					第四步：按月操作，如2015-04，操作过程为删除4月份计算结果，重新计算并记录4月份下线汇总数据
				</td>				
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr> 			
				<td >
					<shiro:hasPermission name="sys:sum:updateShipTotal">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="sumData('qms/importData/updateShipTotal.do');">发货汇总</button></div></div>
					</shiro:hasPermission>
				</td>
				<td class="explain">
					第五步：按月操作，如2015-04，操作过程为删除4月份计算结果，重新计算并记录4月份发货汇总数据
				</td>				
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr> 			
				<td >
					<shiro:hasPermission name="sys:sum:updateMarketRepairTotal">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="sumData('qms/importData/updateMarketRepairTotal.do');">维修汇总</button></div></div>
					</shiro:hasPermission>
				</td>	
				<td class="explain">
					第六步：按月操作，如2015-04，操作过程为删除4月份计算结果，重新计算并记录4月份维修汇总数据
				</td>			
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr> 			
				<td >
					<shiro:hasPermission name="sys:sum:updateTotalCount">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="sumData('qms/importData/updateTotalCount.do');">一键汇总</button></div></div>
					</shiro:hasPermission>
				</td>	
				<td class="explain">
					慎用！！！仅第一次导入数据的时候使用。（不用选择月份，操作过程为删除所有月份计算结果，重新计算并记录所有月份下线、发货、维修汇总数据）
				</td>
			</tr>
			<tr><td colspan="2"></td></tr>
			<tr>
				<td>
                	<shiro:hasPermission name="sys:sum:sumInstallReapair">
                		<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="sumData('qms/importData/sumInstallRepair.do');">安装维修汇总</button></div></div>
					</shiro:hasPermission>
                </td>
                <td class="explain">
					请选择需要汇总的起止月份，操作过程为删除起止区间月份的计算结果，重新计算并记录起止区间月份的安装数据，此数据为安装三角阵使用
               	</td>  	
			</tr>
            <tr>
                <td colspan="3">
                    <hr width="100%" size=1 color="red" align=center noshade> 
                </td>
            </tr>	
            <tr>            
                <th>安装月份：</th>
                <td>
                    <input type="text" id="startDate" name="startDate" placeholder="YYYY-MM"  onclick="laydate({isym:true, format: 'YYYY-MM'})" value="" readonly="readonly"/>
                    至
                    <input type="text" id="endDate" name="endDate" placeholder="YYYY-MM"  onclick="laydate({isym:true, format: 'YYYY-MM'})" value="" readonly="readonly"/>
                </td>
            </tr>            
            <tr>            
                <td>
                	<shiro:hasPermission name="sys:sum:sumInstall">
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="sumInstallData();">安装汇总</button></div></div>
                	</shiro:hasPermission>
                </td>   
                <td class="explain">
                    	请选择需要汇总的起止月份，操作过程为删除起止区间月份的计算结果，重新计算并记录起止区间月份的安装数据
                </td>
            </tr>            		
		</table>
	</div>
</div>