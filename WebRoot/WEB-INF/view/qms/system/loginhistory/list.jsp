<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
$(function(){
	$("#resetBtn", navTab.getCurrentPanel()).click(function(){
	   $("form input", navTab.getCurrentPanel()).val("");
    });
})
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/sysLoginHistory/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户名：
				</td>
				<td>
					<input type="text" name="username" value="${param.username}" />
				</td>	
				<td>
					姓名：
				</td>
                <td>
                    <input type="text" name="userDesc" value="${param.userDesc}" />
                </td> 
             
                <td>
					登录时间：
				</td>
                <td>
                    <input type="text" name="startDate" id="startDate" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="${param.startDate}"/>
                </td>  				
				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				<div class="buttonActive"><div class="buttonContent"><button type="button" id="resetBtn">重置</button></div></div>
				</td>	
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="96">
		<thead>
			<tr>		
			    <th width="40" >序号</th>
			    <th  >用户名</th>
			    <th  >姓名</th>
			    <th  >登录IP</th>
			    <th  >登录时间</th>
			    <th  >登出时间</th>
			</tr>
		</thead>
		<tbody>
		<!-- 文字在左，数字在右 -->
		<c:forEach items="${list}" var="o" varStatus="s">
			<tr>
				<td align="center">
				${o.rowId }
				</td>
				<td>${o.userName}</td>
				<td>${o.userDescription}</td>
				<td>${o.loginIp}</td>
				<td><fmt:formatDate value="${o.loginTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${o.logoutTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>		
		</c:forEach>
		</tbody>
	</table>
	<!-- 分页相关 -->
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
