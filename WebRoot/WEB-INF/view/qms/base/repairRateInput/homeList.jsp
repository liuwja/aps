<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<style>
<!--
#alarmTb {
	width: 100%;
	border-collapse: collapse;
	background: #fff;
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
	font: 14px/1.4 "Microsoft Yahei", Tahoma, Georgia, Serif;
}

#alarmTb td {
	padding: 6px;
	border: 1px solid #ccc;
	text-align: left;
	font: 14px/1.4 "Microsoft Yahei", Tahoma, Georgia, Serif;
}

#page-wrap {
	padding: 6px
}
-->
</style>
<div id="page-wrap">
	<table id="alarmTb" width="100%">
		<tr>
			<th>机型类别</th>
			<th>${queryMonth}月百台维修率</th>
			<c:forEach items="${monthList}" var="mon">
				<th>${mon}</th>
			</c:forEach>
		</tr>

		<c:forEach items="${list}" var="o" varStatus="s">
			<tr>
				<td> ${o.typeCategory}</td>
				<td>${o.repairRate}%</td>
				<c:forEach items="${o.rateResults}" var="tm">
					<td ${tm.flag==1 ? "style='color: red'" : "" } >
						${tm.repairTotalRate ==null ? "" : tm.repairTotalRate}
						${tm.repairTotalRate ==null ? "0" : "%"}
					</td>
				</c:forEach>
			</tr>
		</c:forEach>


	</table>
</div>