<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">


function getCheckIndex(){
	var darr = new Array();
	var rarr = new Array();
	var dearr = new Array();
	darr.push("<option value=''>请选择</option>");
	darr.push("<option value='油烟机开箱不良'>油烟机开箱不良</option>");
	darr.push("<option value='消毒柜开箱不良'>消毒柜开箱不良</option>");
	darr.push("<option value='油烟机OQC不良<'>油烟机OQC不良</option>");
	darr.push("<option value='消毒柜OQC不良'>消毒柜OQC不良</option>");
	darr.push("<option value='组装一次合格率'>组装一次合格率</option>");
	darr.push("<option value='喷涂一次合格率'>喷涂一次合格率</option>");
	darr.push("<option value='精加工一次合格率'>精加工一次合格率</option>");
	darr.push("<option value='冲压一次合格率'>冲压一次合格率</option>");
	
	rarr.push("<option value=''>请选择</option>");
	rarr.push("<option value='灶具开箱不良'>灶具开箱不良</option>");
	rarr.push("<option value='热水器开箱不良'>热水器开箱不良</option>");
	rarr.push("<option value='灶具OQC不良'>灶具OQC不良</option>");
	rarr.push("<option value='热水器OQC不良'>热水器OQC不良</option>");
	rarr.push("<option value='灶具组装一次合格率'>灶具组装一次合格率</option>");
	rarr.push("<option value='热水器组装一次合格率'>热水器组装一次合格率</option>");
	rarr.push("<option value='精加工一次合格率'>精加工一次合格率</option>");
	rarr.push("<option value='冲压一次合格率'>冲压一次合格率</option>");

	dearr.push("<option value=''>请选择</option>");
	dearr.push("<option value='微蒸烤开箱不良'>微蒸烤开箱不良</option>");
	dearr.push("<option value='微蒸烤OQC不良'>微蒸烤OQC不良</option>");
	
	$("#checkIndexName",$.pdialog.getCurrent()).empty();
	var factory = $("#factory",$.pdialog.getCurrent()).val();
//	alert(factory);
	if(factory =="电器一厂"){

		var html = darr.join("");
		$("#checkIndexName",$.pdialog.getCurrent()).append(html);
	}else if(factory == "燃气工厂"){
		
		var html = rarr.join("");
		$("#checkIndexName",$.pdialog.getCurrent()).append(html);
	}else {

		var html = dearr.join("");
		$("#checkIndexName",$.pdialog.getCurrent()).append(html);
	}
	
}
</script>
<div class="pageContent">
	<!-- navTabId重新载入index对应的tab -->
	<form method="post" id="formID" action="<c:url value='system/countPerformanceMonth/update.do?navTabId=countPerformanceMonthNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate nowrap" onsubmit="return (validateCallback(this, dialogAjaxDone));">
		<div class="pageFormContent" layoutH="100">
			<input type="hidden" name="id" value="${vo.id }"/>
			<table class="tableFormContent nowrap">
				<tr >
					<th>工厂</th>
					<td><select id="factory" name="factory" class="required" onchange="getCheckIndex()">
					    <option value="">请选择</option>
				        <option value="电器一厂">电器一厂</option>
				        <option value="燃气工厂">燃气工厂</option>
				        <option value="电器二厂">电器二厂</option>
				        </select>
				        <script type="text/javascript">
				          $("#factory",$.pdialog.getCurrent()).val('${vo.factory}')
				        </script>
				    </td>
				    <th>考核指标</th>
				    <td>
				       <select name="checkIndexName" id="checkIndexName" class="required">
				         <c:if test="${vo.factory == '电器一厂' }">
				            <option>请选择</option>
				            <option value='油烟机开箱不良'>油烟机开箱不良</option>
							<option value='消毒柜开箱不良'>消毒柜开箱不良</option>
							<option value='油烟机OQC不良'>油烟机OQC不良</option>
							<option value='消毒柜OQC不良'>消毒柜OQC不良</option>
							<option value='组装一次合格率'>组装一次合格率</option>
							<option value='喷涂一次合格率'>喷涂一次合格率</option>
							<option value='精加工一次合格率'>精加工一次合格率</option>
							<option value='冲压一次合格率'>冲压一次合格率</option>
				         </c:if>
				          <c:if test="${vo.factory == '燃气工厂' }">
				            <option>请选择</option>
				            <option value='灶具开箱不良'>灶具开箱不良</option>
							<option value='热水器开箱不良'>热水器开箱不良</option>
							<option value='灶具OQC不良'>灶具OQC不良</option>
							<option value='热水器OQC不良'>热水器OQC不良</option>
							<option value='灶具组装一次合格率'>灶具组装一次合格率</option>
							<option value='热水器组装一次合格率'>热水器组装一次合格率</option>
							<option value='精加工一次合格率'>精加工一次合格率</option>
							<option value='冲压一次合格率'>冲压一次合格率</option>
				         </c:if>
				         <c:if test="${vo.factory == '电器二厂' }">
				            <option value=''>请选择</option>");
						    <option value='微蒸烤开箱不良'>微蒸烤开箱不良</option>
							<option value='微蒸烤OQC不良'>微蒸烤OQC不良</option>
				         </c:if>
				       </select>
				       <script type="text/javascript">
				          $("#checkIndexName",$.pdialog.getCurrent()).val("${vo.checkIndexName}")
				        </script>			      
				    </td>		
				</tr>
				<tr>
				    <th>月份</th>
				    <td><input name='queryMonth' id='queryMonth' placeholder='请输入日期' onclick="laydate({isym:true, format:'YYYY-MM'})" value="<fmt:formatDate value="${vo.checkMonth}" type="date" pattern="yyyy-MM"/>" readonly='true' class='required'/></td>
				    <th>目标值</th>
				    <td><input type="text" name="targetValue" value="${vo.targetValue }" class="required number"/></td>
				</tr>													
			</table>
		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
