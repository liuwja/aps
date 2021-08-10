<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
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
	showGroupPeriodChar();
	var score = 0 ;
	score = $("#score",navTab.getCurrentPanel()).html();
	$("#sumItemscore",navTab.getCurrentPanel()).html(score);
});
function showGroupPeriodChar(){
	var char_width = document.body.clientWidth - $("#sidebar").width()-80;
	var shiftGroupText =$("#shiftGroupTxt",navTab.getCurrentPanel()).val();
	var startTime = $("#startTime",navTab.getCurrentPanel()).val();
	var url = "<c:url value='system/homePage/getGroupPerfermanceChar.do'/>";
	$.post(url, {shiftGroupTxt:shiftGroupText,startTime:startTime,width:char_width}, function(data) {

		if (data.result == 0) {
//		    data.chartsInfo.index = 0;
//			data.chartsInfo.type = 0;
			showChart("showGroupPeriodCharId", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

function checkSel4(){
	var startTime = $("#startTime",navTab.getCurrentPanel()).val();
    if(startTime == ""){
    	alert("请选择开始时间");
    	return false;
    }else{
    	$("#btid",navTab.getCurrentPanel()).submit();
    }  

}

	
function getUrl(obj){
//	alert(obj);
	var url = obj;
	if(url.indexOf(".do")>0){
//		var opt = {width:1100,height:600, mask:true};
//	    $.pdialog.open(url, "dispatchOederDlg", "查询页面", opt);
        var tabId = "";
        var title = "";
        if(url.indexOf("assemblyList")>0){
        	tabId = "assemblyListNav";
        	title = "生产退次表查询";
        	navTab.openTab(tabId, url,{ title:title, fresh:false});
        }if(url.indexOf("formerProcessList")>0){
        	tabId = "formerProcessListNav";
        	title = "前工序FQC查检表查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }if(url.indexOf("batchDefectList")>0){
        	tabId = "batchDefectListNav";
        	title = "5M1E不合格项记录表查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }if(url.indexOf("stampingDaliyList")>0){
        	tabId = "stampingDaliyListNav";
        	title = "冲压质量日报表查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }if(url.indexOf("processAuditList")>0){
        	tabId = "processAuditListNav";
        	title = "过程审核记录表查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }if(url.indexOf("qualityImpList")>0){
        	tabId = "qualityImpListNav";
        	title = "质量改善课题申报表查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }if(url.indexOf("boxDefectList")>0){
        	tabId = "boxDefectListNav";
        	title = "市场开箱不良记录单查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }if(url.indexOf("paintingDailyList")>0){
        	tabId = "paintingDailyListNav";
        	title = "喷涂质量日报表查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }if(url.indexOf("ipqcInspects")>0){
        	tabId = "ipqcInspectsListNav";
        	title = "IPQC巡检不良表查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }if(url.indexOf("oqcCheck")>0){
        	tabId = "oqcCheckListNav";
        	title = "OQC抽检不良表查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }if(url.indexOf("assemblyRepaired")>0){
        	tabId = "assemblyRepairedListNav";
        	title = "组装维修日报表查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }if(url.indexOf("paintingProductReturn")>0){
        	tabId = "paintingProductReturnNav";
        	title = "喷涂生产退次表查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }if(url.indexOf("finishingDaliyList")>0){
        	tabId = "finishingDaliyListNav";
        	title = "精加工直通率查询";
        	navTab.openTab(tabId, url, {title:title, fresh:false});
        }
        
	}	  
}
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="groupPerformanceChart/performanceDetailListMonth.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
	
				<td>
					月份：
				</td>
				<td>
				    <input type="hidden" id="shiftGroupTxt" name="group" value="${vo.baseGroup }">	
				    <input type="hidden" id="mykey" name="mykey" value="home">
				    <input type="hidden" id="factory" name="factory" value="${vo.factory }">			    
					<input type="text" id="startTime" name="date" placeholder="YYYY-MM" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime }" readonly="readonly" />
				</td>				
				
				<td><div class="buttonActive"><div class="buttonContent"><button id="btid" type="button" onclick="checkSel4();">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
	 
       

 <div style="width:100%;overflow:scroll;width:100%; height:600px;vertical-align:top">   
    <div style="vertical-align: top;">
                <div style="width:100%;height:40px;background:#66CCFF">
                  <div style="width:40%;height:40px;float: left;">
                    <p id="gtitle">${vo.baseGroup }</p>  
                  </div> 
                 <div style="width:60%;height:40px;background:#66CCFF;float: left;">
                    <div style="width:50%;height:40px;float: left;">
                         <p id="gtitle2"> ${vo.startTime }月分数为:<span  id="sumItemscore" style="color:#990033;font-size:15px;font-weight: bold;"></span> 分</p>
                    </div>
                    <div style="width:50%;height:40px;float: left;">
                          <p id="gtitle2">  班组排名为:第<span style="color:#990033;font-size:15px;font-weight: bold;">${vo.homeRank }</span>位</p>
                    </div>                
                </div> 
                </div>
               
                <div id="showGroupPeriodCharId" style="width:90%;height:300px;margin-left: 10px;margin-top: 10px;" >
    
                </div>
     </div>
    <div style="width:95%;hight:300px;margin-left: 10px;margin-top: 20px;">
	<table id="tab1" class="list" width="100%" layoutH="95">
		<thead>
			<tr id="pdthead">
			    <th>班组</th>
			    <th>项目代码</th>
			    <th>考核项目</th>
			    <th>项目权重</th>
			    <th>指标代码</th>
			    <th>考核指标</th>
			    <th>指标权重</th>
				<th >基准</th>
				<th >目标</th>
				<th >实绩</th>
				<th >指标得分</th>	
				<th >项目得分</th>	
				<th >班组总分</th>			
			</tr>
		</thead>
		<tbody>
		    <c:forEach items="${list}" var="o" varStatus="s">
		    <c:choose>
		      <c:when test="${o.flag =='flag' }">
		     
			<tr target="code_carrier" rel="${o.uigroupCategory.gcKey}" >
				<td  rowspan="${o.uigroupCategory.monthNum}">${o.groupName}</td>
				     <c:forEach items="${o.uigroupCategory.item}" var="vo" begin="0" end="0">
					   <td rowspan="${vo.monthNum}">${vo.itemCode}</td>
				       <td rowspan="${vo.monthNum}">${vo.itemName}</td>
				       <td rowspan="${vo.monthNum}">${vo.itemScale }</td>
		 			    <c:forEach items="${vo.uiindexs }" var="in" begin="0" end="0">
					      <td  rowspan="${in.monthNum}">${in.indexCode }</td>
					      <td  rowspan="${in.monthNum}">${in.indexName }</td>
					       <c:forEach items="${in.monthAssessments }" var="mon" begin="0" end="0">
					            <td>${mon.indexScale } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					            <td><a href="javascript:void(0)"  onclick="getUrl('${mon.urlStr}?shifS=${o.groupName }&timS=${mon.monthly }&facS=${o.factory }')" >${mon.indexActValue } </a></td>
					            <td>${mon.indexScore } </td>
					            <td  rowspan="${vo.monthNum}">${vo.itemScore}</td>
						        <td id="score" rowspan="${o.uigroupCategory.monthNum}">${o.groupScore }</td>
					       </c:forEach>
					       <c:if test="${in.monthNum ==0}">
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
					       </c:if>
					       <c:forEach items="${in.monthAssessments }" var="mon1" begin="1">
					          <tr> 
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					             <td> <a href="javascript:void(0)"  onclick="getUrl('${mon1.urlStr}?shifS=${o.groupName }&timS=${mon1.monthly }&facS=${o.factory }')" >${mon1.indexActValue } </a></td>
					            <td>${mon1.indexScore } </td>
					          </tr>
					       </c:forEach>
					      
					    </c:forEach>
					    
					    <c:if test="${vo.monthNum == 0}">
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
						<td> </td>
						<td> </td>
						<td> </td>
					    </c:if>
			 		   
					    <c:forEach items="${vo.uiindexs }" var="in2" begin="1">
					    <tr> 
					      <td  rowspan="${in2.monthNum}">${in2.indexCode }</td>
					      <td  rowspan="${in2.monthNum}">${in2.indexName }</td>
					      <c:forEach items="${in2.monthAssessments }" var="mon" begin="0" end="0">
					            <td>${mon.indexScale } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					             <td><a href="javascript:void(0)"  onclick="getUrl('${mon.urlStr}?shifS=${o.groupName }&timS=${mon.monthly }&facS=${o.factory }')" >${mon.indexActValue } </a> </td>
					            <td>${mon.indexScore } </td>
					      </c:forEach>
					      <c:if test="${in2.monthNum ==0}">
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
					       </c:if>
					       <c:forEach items="${in2.monthAssessments }" var="mon1" begin="1">
					           <tr>
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					             <td><a href="javascript:void(0)"  onclick="getUrl('${mon1.urlStr}?shifS=${o.groupName }&timS=${mon1.monthly }&facS=${o.factory }')" >${mon1.indexActValue } </a> </td>
					            <td>${mon1.indexScore } </td>
					           </tr>
					       </c:forEach>
					    </tr> 
					    </c:forEach>
					</c:forEach>	
					<c:if test="${o.uigroupCategory.monthNum == 0}">
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    </c:if>	 
						
			     </tr>		
			
				     <c:forEach items="${o.uigroupCategory.item}" var="it" begin="1" >
				        <tr> 
					       <td rowspan="${it.monthNum}">${it.itemCode}</td>
				           <td rowspan="${it.monthNum}">${it.itemName}</td>
					       <td rowspan="${it.monthNum}">${it.itemScale}</td>
						<c:forEach items="${it.uiindexs }" var="ind" begin="0" end="0">
					      <td  rowspan="${ind.monthNum}">${ind.indexCode }</td>
					      <td  rowspan="${ind.monthNum}">${ind.indexName }</td>
					          <c:forEach items="${ind.monthAssessments }" var="mon" begin="0" end="0">
					            <td>${mon.indexScale } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					             <td><a href="javascript:void(0)"  onclick="getUrl('${mon.urlStr}?shifS=${o.groupName }&timS=${mon.monthly }&facS=${o.factory }')" >${mon.indexActValue } </a>  </td>
					            <td>${mon.indexScore } </td>
					            <td  rowspan="${it.monthNum}">${it.itemScore }</td>
					         </c:forEach>
					       <c:if test="${ind.monthNum ==0}">
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
					       </c:if>
					       <c:forEach items="${ind.monthAssessments }" var="mon1" begin="1">
					           <tr>
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					             <td><a href="javascript:void(0)"  onclick="getUrl('${mon1.urlStr}?shifS=${o.groupName }&timS=${mon1.monthly }&facS=${o.factory }')" >${mon1.indexActValue } </a></td>
					            <td>${mon1.indexScore } </td>
					          </tr>
					       </c:forEach>
					    </c:forEach>
					    <c:if test="${it.monthNum == 0}">
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
						<td> </td>
						<td> </td>
						<td> </td>
					    </c:if>	
					    
					     <c:forEach items="${it.uiindexs }" var="ind2" begin="1">
					     <tr>
					      <td  rowspan="${ind2.monthNum}">${ind2.indexCode }</td>
					      <td  rowspan="${ind2.monthNum}">${ind2.indexName }</td>
					      <c:forEach items="${ind2.monthAssessments }" var="mon" begin="0" end="0">
					            
					            <td>${mon.indexScale } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					             <td><a href="javascript:void(0)"  onclick="getUrl('${mon.urlStr}?shifS=${o.groupName }&timS=${mon.monthly }&facS=${o.factory }')" >${mon.indexActValue }</a> </td>
					            <td>${mon.indexScore } </td>
					      </c:forEach>
					      <c:if test="${ind2.monthNum ==0}">
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							     <td> </td>
					       </c:if>
					       <c:forEach items="${ind2.monthAssessments }" var="mon1" begin="1">
					           <tr>
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					             <td><a href="javascript:void(0)"  onclick="getUrl('${mon1.urlStr}?shifS=${o.groupName }&timS=${mon1.monthly }&facS=${o.factory }')" >${mon1.indexActValue }</a>  </td>
					            <td>${mon1.indexScore } </td>
					           </tr>
					       </c:forEach>
					     </tr>
					    </c:forEach>
					    <c:if test="${o.uigroupCategory.monthNum == 0}">
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    </c:if>	
					   </tr>
					   
					</c:forEach>
					
				</c:when>
				<c:when test="${o.flag !='flag' }">
				<tr target="code_carrier" rel="${o.uigroupCategory.gcKey}" >
				<td  rowspan="${o.uigroupCategory.monthNum}">${o.groupName}</td>
				     <c:forEach items="${o.uigroupCategory.item}" var="vo" begin="0" end="0">
					   <td rowspan="${vo.monthNum}">${vo.itemCode}</td>
				       <td rowspan="${vo.monthNum}">${vo.itemName}</td>
				       <td rowspan="${vo.monthNum}">${vo.itemScale }</td>
		 			    <c:forEach items="${vo.uiindexs }" var="in" begin="0" end="0">
					      <td  rowspan="${in.monthNum}">${in.indexCode }</td>
					      <td  rowspan="${in.monthNum}">${in.indexName }</td>
					       <c:forEach items="${in.monthAssessments }" var="mon" begin="0" end="0">
					            <td>${mon.indexScale } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					            <td>${mon.indexActValue } </td>
					            <td>${mon.indexScore } </td>
					            <td  rowspan="${vo.monthNum}">${vo.itemScore}</td>
						        <td  rowspan="${o.uigroupCategory.monthNum}">${o.groupScore }</td>
					       </c:forEach>
					       <c:if test="${in.monthNum ==0}">
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
					       </c:if>
					       <c:forEach items="${in.monthAssessments }" var="mon1" begin="1">
					          <tr> 
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					             <td>${mon1.indexActValue } </td>
					            <td>${mon1.indexScore } </td>
					          </tr>
					       </c:forEach>
					      
					    </c:forEach>
					    
					    <c:if test="${vo.monthNum == 0}">
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
						<td> </td>
						<td> </td>
						<td> </td>
					    </c:if>
			 		   
					    <c:forEach items="${vo.uiindexs }" var="in2" begin="1">
					    <tr> 
					      <td  rowspan="${in2.monthNum}">${in2.indexCode }</td>
					      <td  rowspan="${in2.monthNum}">${in2.indexName }</td>
					      <c:forEach items="${in2.monthAssessments }" var="mon" begin="0" end="0">
					            <td>${mon.indexScale } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					             <td>${mon.indexActValue } </td>
					            <td>${mon.indexScore } </td>
					      </c:forEach>
					      <c:if test="${in2.monthNum ==0}">
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
					       </c:if>
					       <c:forEach items="${in2.monthAssessments }" var="mon1" begin="1">
					           <tr>
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					             <td>${mon1.indexActValue } </td>
					            <td>${mon1.indexScore } </td>
					           </tr>
					       </c:forEach>
					    </tr> 
					    </c:forEach>
					</c:forEach>	
					<c:if test="${o.uigroupCategory.monthNum == 0}">
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    </c:if>	 
						
			     </tr>		
			
				     <c:forEach items="${o.uigroupCategory.item}" var="it" begin="1" >
				        <tr> 
					       <td rowspan="${it.monthNum}">${it.itemCode}</td>
				           <td rowspan="${it.monthNum}">${it.itemName}</td>
					       <td rowspan="${it.monthNum}">${it.itemScale}</td>
						<c:forEach items="${it.uiindexs }" var="ind" begin="0" end="0">
					      <td  rowspan="${ind.monthNum}">${ind.indexCode }</td>
					      <td  rowspan="${ind.monthNum}">${ind.indexName }</td>
					          <c:forEach items="${ind.monthAssessments }" var="mon" begin="0" end="0">
					            <td>${mon.indexScale } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					             <td>${mon.indexActValue } </td>
					            <td>${mon.indexScore } </td>
					            <td  rowspan="${it.monthNum}">${it.itemScore }</td>
					         </c:forEach>
					       <c:if test="${ind.monthNum ==0}">
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
					       </c:if>
					       <c:forEach items="${ind.monthAssessments }" var="mon1" begin="1">
					           <tr>
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					             <td>${mon1.indexActValue } </td>
					            <td>${mon1.indexScore } </td>
					          </tr>
					       </c:forEach>
					    </c:forEach>
					    <c:if test="${it.monthNum == 0}">
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
					    <td> </td>
						<td> </td>
						<td> </td>
						<td> </td>
					    </c:if>	
					    
					     <c:forEach items="${it.uiindexs }" var="ind2" begin="1">
					     <tr>
					      <td  rowspan="${ind2.monthNum}">${ind2.indexCode }</td>
					      <td  rowspan="${ind2.monthNum}">${ind2.indexName }</td>
					      <c:forEach items="${ind2.monthAssessments }" var="mon" begin="0" end="0">
					            
					            <td>${mon.indexScale } </td>
					            <td>${mon.baseValue } </td>
					            <td>${mon.targetValue } </td>
					             <td>${mon.indexActValue } </td>
					            <td>${mon.indexScore } </td>
					      </c:forEach>
					      <c:if test="${ind2.monthNum ==0}">
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							    <td> </td>
							     <td> </td>
					       </c:if>
					       <c:forEach items="${ind2.monthAssessments }" var="mon1" begin="1">
					           <tr>
					            <td>${mon1.indexScale } </td>
					            <td>${mon1.baseValue } </td>
					            <td>${mon1.targetValue } </td>
					             <td>${mon1.indexActValue } </td>
					            <td>${mon1.indexScore } </td>
					           </tr>
					       </c:forEach>
					     </tr>
					    </c:forEach>
					    <c:if test="${o.uigroupCategory.monthNum == 0}">
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    <td></td>
					    </c:if>	
					   </tr>
					   
					</c:forEach>
				
				</c:when>
					 
		       </c:choose>	
		    </c:forEach>
		</tbody>
	</table>
   </div> 	
	
</div>
