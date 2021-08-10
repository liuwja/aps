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
<script>
	
</script>
<div class="pageContent">
	<form method="post" id="formID"
		action="<c:url value='base/repairRateInput/save.do?navTabId=repairRateInputNav&callbackType=closeCurrent'/>"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="58">
			<table id="alarmTb" width="100%">
				<tr>
					<th>机型类别</th>
					<c:forEach items="${monthList}" var="mon">
						<th>${mon }</th>
					</c:forEach>
					<th>单月百台维修率</th>
				</tr>

				<c:forEach items="${resultMap}" var="rm" varStatus="s">
					<tr>
						<td> ${rm.key }</td>
						<c:set var="len" value="${fn:length(rm.value) }"></c:set>
						<c:forEach items="${rm.value}" var="o" varStatus="s2" begin="0" end="${len-2 }">
							<td>
							<input type="hidden" name="type[${s.index * 12 + s2.index }].id" value="${o.id }"> 
							<input type="hidden" name="type[${s.index * 12 + s2.index }].typeCategory" value="${rm.key }"> 
							<input type="hidden" name="type[${s.index * 12+ s2.index  }].repairMonth" value="${o.repairMonth }"> 
							<input type="text" class="number" size="4" name="type[${s.index * 12+ s2.index }].repairRate" value="${o.repairRate == 0 ? '' : o.repairRate}">
							%
							</td>
						</c:forEach>
						<c:forEach items="${rm.value}" var="o" begin="${len-1 }" varStatus="s1">
							<td>
							<input type="hidden" name="total[${s.index }].id" value="${o.id }">
							<input type="hidden" name="total[${s.index }].typeCategory" value="${rm.key }"> 
							<input type="hidden" name="total[${s.index }].insertMonth" value="${o.repairMonth }">
							<input type="text" class="number" size="4" name="total[${s.index}].repairRate" value="${o.repairRate == 0 ? '' : o.repairRate}">
							   %
							</td>
						</c:forEach>
					</tr>
				</c:forEach>

			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
