<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	.explain{color:red;font-size:14px;font-family:"微软雅黑"}

</style>
<script type="text/javascript">
function updateTestinstance()
{
	var startTime = $("#startTime",navTab.getCurrentPanel()).val();
	var endTime = $("#endTime",navTab.getCurrentPanel()).val();
	if(startTime == '' || endTime ==''){
		alertMsg.info('请选择更新时间！');
		return ;
	}
	var url = "quality/testInstance/updateTestInstance.do";
    $.post(url,{startTime:startTime,endTime:endTime},function(data){
        if(0 == data.result){
           alert("更新成功");
        }else{
            alertMsg.info("操作失败");
        }
    });
}
function recordData(title) {
	var startTime = $("#marketStartTime", navTab.getCurrentPanel()).val();
	var endTime = $("#marketEndTime", navTab.getCurrentPanel()).val();
	if (startTime != null && startTime != "" && startTime != undefined && endTime != null && endTime != "" && endTime != undefined) {
		var url = "quality/marketPart/updateData.do";
		$.post(url,{title:title, startTime:startTime, endTime:endTime},function(data,status){
	        if(0 == data.result){
	        	alertMsg.info(data.showResult + "," + data.msg);
	        }else{
	            alertMsg.info("操作失败");
	        }
	    });
	} else {
		alertMsg.info("请输入更新时间");
	}
}



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
	var startTime = $("#replaceStartTime",navTab.getCurrentPanel()).val();
	var endTime = $("#replaceEndTime",navTab.getCurrentPanel()).val();
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

function updateSupplier(url) {
	$.post(url,{},function(data,status){
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
	    	<shiro:hasPermission name="part:dataSum:updateTestInstance">
				<tr> 			
					<th>更新时间：</th>
	                <td>
						<input type="text" id="startTime" name="startTime" placeholder="请输入日期"  onclick="laydate()" value="${vo.startTime }" readonly="readonly"/>
						至
						<input type="text" id="endTime" name="endTime" placeholder="请输入日期"  onclick="laydate()" value="${vo.endTime }" readonly="readonly"/>
	    			</td>				
				</tr>
				<tr><td colspan="2"></td></tr>
				<tr>		
					<td class="btn">
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="updateTestinstance();">进料-来料检验数据更新</button></div></div>
					</td>
					<td class="explain">第一步：按整月操作，如2015-04-01至2015-04-30，操作过程为删除原来4月份来料检验数据，第二步：重新导入4月份数据</td>
				</tr>
				<tr><td colspan="2"></td></tr>
				<tr>
	                <td colspan="3">
	                    <hr width="100%" size=1 color="red" align=center noshade> 
	                </td>
	            </tr>
            </shiro:hasPermission>
            <shiro:hasPermission name="part:dataSum:sumMesDataMon">
	            <tr> 			
					<th>汇总月份：</th>
	                <td>
						<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${list.queryMonth }" readonly="readonly"/>
	    			</td>
	    		</tr>
	    		<tr><td  colspan="2"></td></tr>
	    		<tr> 			
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="sumMesDataMon('base/online/onlineReplaceSumDataMonth.do');">在线-MES更换数据月份汇总</button></div></div>
					</td>
					<td class="explain">
						第一步：按月操作，如2015-04，操作过程为删除 QMS 4月份的更换数据，重新获取 MES 4月份的更换数据
					</td>				
				</tr>
				<tr><td colspan="2"></td></tr>
				<tr> 			
					<th>汇总日期：</th>
	                <td>
						<input type="text" id="replaceStartTime"  placeholder="请输入日期"  onclick="laydate()" value="${list.queryDay }" readonly="readonly"/>至
						<input type="text" id="replaceEndTime"  placeholder="请输入日期"  onclick="laydate()" value="${list.queryDay }" readonly="readonly"/>
	    			</td>
	    		</tr>
	    		<tr><td  colspan="2"></td></tr>
				<tr> 			
					<td >
						<shiro:hasPermission name="part:dataSum:sumMesDataDay">
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="sumMesDataDay('base/online/onlineReplaceSumDataDay.do');">在线-MES更换数据单日汇总</button></div></div>
						</shiro:hasPermission>
					</td>
					<td class="explain">
						第二步：按日期间操作，如2015-04-01~2015-04-30，操作过程为删除 QMS 4月1号到4月30号的更换数据，重新获取 MES 4月1号到4月30号的更换数据
					</td>				
				</tr>
				<tr><td colspan="2"></td></tr>
<!-- 				<tr> 			 -->
<!-- 					<td> -->
<!-- 						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="updateSupplier('base/online/onlineReplaceSumDataMonth.do');">在线-MES更换供应商数据更新</button></div></div> -->
<!-- 					</td> -->
<!-- 					<td class="explain"> -->
<!-- 						此操作不是必要操作，点击按钮时会将在线-MES更换中的供应商全部重新导入一次，如果数据没有出错则无需使用此功能 -->
<!-- 					</td>				 -->
<!-- 				</tr> -->
				<tr>
	                <td colspan="3">
	                    <hr width="100%" size=1 color="red" align=center noshade> 
	                </td>
	            </tr>
		    </shiro:hasPermission>
		    <shiro:hasPermission name="part:dataSum:importMes">
				<tr> 			
					<th>更新时间：</th>
	                <td>
						<input type="text" id="marketStartTime" name="startTime" placeholder="请输入日期"  onclick="laydate()" value="${vo.startTime }" readonly="readonly"/>
						至
						<input type="text" id="marketEndTime" name="endTime" placeholder="请输入日期"  onclick="laydate()" value="${vo.endTime }" readonly="readonly"/>
	    			</td>				
				</tr>
				<tr><td colspan="2"></td></tr>
				<tr>		
					<td class="btn">
						
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="recordData(1);">市场-发货数据汇总</button></div></div>
						
					</td>
					<td class="explain">选择发货数据汇总时，导入时间为下线时间。如导入时间选择为“2015-04-01”到“2015-05-01”则汇总时会选择下线时间为“2015-04-01”到“2015-05-01”</td>
				</tr>
				<tr><td colspan="2"></td></tr>
			
				<tr>
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="recordData(2);">市场-维修数据汇总</button></div></div>
					</td>
					<td class="explain">选择维修数据汇总时，导入时间为维修时间。如导入时间选择为“2015-04-01”到“2015-05-01”则汇总时会选择维修时间为“2015-04-01”到“2015-05-01”</td>
				</tr>
				<tr><td colspan="2"></td></tr>
				<tr> 			
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="updateSupplier('quality/marketPart/updateShipSupplier.do?title=1');">市场质量-发货供应商数据更新</button></div></div>
					</td>
					<td class="explain">
						此操作不是必要操作，点击按钮时会将市场-发货数据中的供应商全部重新导入一次，如果数据没有出错则无需使用此功能
					</td>				
				</tr>
				<tr><td colspan="2"></td></tr>
				<tr> 			
					<td>
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="updateSupplier('quality/marketPart/updateShipSupplier.do?title=2');">市场质量-维修供应商数据更新</button></div></div>
					</td>
					<td class="explain">
						此操作不是必要操作，点击按钮时会将市场-维修数据中的供应商全部重新导入一次，如果数据没有出错则无需使用此功能
					</td>				
				</tr>	
            </shiro:hasPermission>
		</table>
	</div>
</div>