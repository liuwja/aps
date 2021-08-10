<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<style>
<!--
    #alarmTb { 
        width: 100%; 
        border-collapse: collapse; background:#fff;
    }
    /* Zebra striping */
    #alarmTb  tr:nth-of-type(odd) { 
        background: #eee; 
    }
    #alarmTb th { 
        background: #333; 
        color: white; 
        font-weight: bold; 
        white-space: nowrap;
        padding: 6px; 
        border: 1px solid #ccc; 
        text-align: left; 
        font: 14px/1.4 "Microsoft Yahei", Tahoma,Georgia, Serif;         
    }
    
    #alarmTb td { 
        padding: 6px; 
        border: 1px solid #ccc; 
        text-align: left; 
        font: 14px/1.4 "Microsoft Yahei", Tahoma,Georgia, Serif; 
    }    
    #page-wrap
    {
        padding: 6px
    }
-->
</style>
<div id="page-wrap">
<table id="alarmTb"  width="100%" >
  <tr>
    <th>机型类别</th>
    <th>${queryMonth}月百台维修率</th>
<c:forEach items="${monthList}" var="mon">
    <th>${mon }</th>
</c:forEach>
  </tr>

  <c:forEach items="${resultMap}" var="rm">
  <tr>
    <td>${rm.key }</td>  
    <c:forEach items="${rm.value}" var="o" begin="${fn:length(rm.value)-1}" end="${fn:length(rm.value)}">
        <c:set var="singleRepairRatePercent" value="${o.singleRepairRatePercent}"></c:set>
    </c:forEach>    
    <c:choose>
    	<c:when test="${empty singleRepairRatePercent}">
    		<td>0%</td>
    	</c:when>
    	<c:otherwise>
    		<td>${singleRepairRatePercent}</td>
    	</c:otherwise>
    </c:choose>
    <c:forEach items="${rm.value}" var="o">
	    <td  ${o.exceedRate ? "style='color: red'" : "" } >${o.totalRepairRatePercent}</td>
    </c:forEach>
  </tr>
  </c:forEach>
  
  
</table>
</div>