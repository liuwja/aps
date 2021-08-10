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
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="base:repairRateInput:ADD">
					<li><a class="add" href="base/repairCountInput/create.do" target="dialog" width="600" height="400" rel="addCarrier" mask="true" title="新增—维修数"><span>新增发货维修数</span></a></li>
				   <!--  <li><a class="add" href="base/repairCountInput/calculate.do" target="dialog" width="600" height="400" rel="calculateCarrier" mask="true" title="计算—维修率"><span>计算维修率</span></a></li> -->
				    <!-- <li><a class="add" href="base/repairRateInput/add.do"  mask="true" target="navTab"  title="录入页面"><span>录入页面</span></a></li> -->
			</shiro:hasPermission>
		<!-- 
			<shiro:hasPermission name="sys:itemMgr:EDIT">
					<li><a class="edit" href="system/item/edit.do?id={key}"  mask="true" target="dialog" height="300" width="800"   title="修改-考核项目"><span>修改</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:itemMgr:DEL">
					<li><a class="delete" href="system/item/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该班组吗？"><span>删除</span></a></li>
			</shiro:hasPermission>	
		 -->			
		</ul>
	</div>
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