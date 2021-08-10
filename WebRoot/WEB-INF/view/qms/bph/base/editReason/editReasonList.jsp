<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function() {
	loadDepartMent("navTab", "${log.departmentNumber}");
});
function stampingDaliy_checkData(){
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	var endTime = $("#endTime", navTab.getCurrentPanel()).val();

	if(endTime<startTime){
		alert("查询结束时间不能小于查询开始时间");
		$("#endTime", navTab.getCurrentPanel()).val("");
		return false; //返回false不提交表单
	}
	/* //ljy添加
	var thid=$("#tab3").attr("hid");
	var chid=$("#columnline3").attr("hid");
	var chid2=$("#lines3").attr("hid");
	if(thid==0){
		$("#tab3").attr("hid","1");
		$("#tab3").css("display","");
		if(chid==1){
			$("#columnline3").attr("hid","0");
			$("#columnline3").css("display","none");
		}
		if(chid2==1){
			$("#lines3").attr("hid","0");
			$("#lines3").css("display","none");
		}
	} */
	//查询时间段验证通过后，则提交表单，否则不刷新页面
	$("#editReasonList").submit();
}
</script>
<div class="pageHeader">
<form id="editReasonList" onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/editReason/list.do" method="post">
<div class="searchBar">
	<table class="searchContent">
		<tr>
			<td>工厂：</td>
			<td>
				<select name="factoryNumber" id="factoryNumber" onchange="loadDepartMent()">
					<option value="">请选择</option>
					<c:forEach items="${factorys}" var="o">
						<option value="${o.factoryNumber }" <c:if test="${log.factoryNumber eq o.factoryNumber }">selected="selected"</c:if>>${o.factory}</option>
					</c:forEach>
				</select>
			</td>
			<td>部门：</td>
			<td>
				<select id="department" name="departmentNumber">
				</select>
			</td>
			<td>
				修改时间： 
			</td>
			<td>
				<input type="text" size="10" name="startTime" id="startTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${log.startTime}" type="date" pattern="yyyy-MM-dd "/>"/>
				至 <input type="text" size="10" name="endTime" id="endTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${log.endTime}" type="date" pattern="yyyy-MM-dd "/>"/>
			</td>
			
		</tr>
		<tr>
			<td>
				绩效目标大类： 
			</td>
			<td>
				<input type="search" name="targetClass" value="${param.targetClass }"/>
			</td>	
			<td>
				指标内容： 
			</td>
			<td>
				<input type="search" name="indexContent" value="${param.indexContent }"/>
			</td>
			<td>
				指标类型： 
			</td>
			<td>
				<input type="search" name="performanceContent" value="${param.performanceContent }"/>
			</td>
		</tr>
		<tr>
			<td>
				修改人： 
			</td>
			<td>
				<input type="search" name="lastUpdateUser" value="${param.lastUpdateUser }"/>
			</td>	
			<td>
				修改原因： 
			</td>
			<td>
				<input type="search" name="updateReason" value="${param.updateReason }"/>
			</td>	
			<td>
				修改类型： 
			</td>
			<td>
				<select name="updateType" id="updateType" onchange="">
					<option value="">请选择</option>
					<option value="绩效考核指标设定修改" <c:if test="${log.updateType eq '绩效考核指标设定修改' }">selected="selected"</c:if>>绩效考核指标设定修改</option>
					<option value="年度考核目标设定修改" <c:if test="${log.updateType eq '年度考核目标设定修改' }">selected="selected"</c:if>>年度考核目标设定修改</option>
					<option value="月度考核目标设定修改" <c:if test="${log.updateType eq '月度考核目标设定修改' }">selected="selected"</c:if>>月度考核目标设定修改</option>
					<option value="月度考核绩效结果修改" <c:if test="${log.updateType eq '月度考核绩效结果修改' }">selected="selected"</c:if>>月度考核绩效结果修改</option>
					<%-- <c:forEach items="${factorys}" var="o">
						<option value="${o.factoryNumber }" <c:if test="${log.factoryNumber eq o.factoryNumber }">selected="selected"</c:if>>${o.factory}</option>
					</c:forEach> --%>
				</select>
			</td>		
			<td>
				<div class="buttonActive">
					<div class="buttonContent">
						<!-- 注意这里的type类型为button,若是写成submit，当函数返回值为true时，会提交表单两次，
						若是假，那么也会提交一次表单。为避免不必要的动作，故type的类型要写成button-->
						<button type="button" onclick="stampingDaliy_checkData()">查找</button>
					</div>
				</div>
			</td>			
		</tr>
	</table>
</div>
</form>
</div>
<div class="pageContent">
<%-- 工具栏：增加、删除和修改
<div class="panelBar">
	<ul class="toolBar">
<shiro:hasPermission name="base:performanceCheck:ADD">
	    <li><a class="add" href="system/performanceCheck/add.do"  mask="true" target="dialog" height="300" width="800"  title="新增-年度绩效考核设定"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:performanceCheck:EDIT">
		<li><a class="edit" href="system/performanceCheck/edit.do?id={key}"  mask="true" target="dialog" height="500" width="800"   title="修改-考核设定"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:performanceCheck:DEL">
		<li><a class="delete" href="system/performanceCheck/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该信息吗？"><span>删除</span></a></li>
</shiro:hasPermission>		
	</ul>
</div> --%>

<table class="table" width="100%" layoutH="143">
	<thead>
		<tr>
			<!-- <th></th> -->
		    <th >工厂</th>
			<th >部门</th>
			<th >绩效目标大类</th>
			<th >指标内容</th>
			<th >指标类型</th>
			<th >修改人</th>
			<th >修改原因</th>
			<th >修改类型</th>
			<th >修改时间</th>
			<th >修改内容</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="o">
			<tr target="key" rel="${o.id}">
				<%-- <td>
					<input type="radio" group="code" name="key" value="${o.id}">
				</td> --%>
				<td>${o.factoryName}</td>
				<td>${o.departmentName}</td>
				<td>${o.targetClass}</td>
				<td>${o.indexContent}</td>
				<td>${o.performanceContent}</td>
				<td>${o.lastUpdateUser}</td>
				<td>${o.updateReason}</td>
				<td>${o.updateType}</td>
				<td><fmt:formatDate value="${o.lastUpdateTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${o.updateContent}</td>
			</tr>		
		</c:forEach>
	</tbody>
</table>
<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>