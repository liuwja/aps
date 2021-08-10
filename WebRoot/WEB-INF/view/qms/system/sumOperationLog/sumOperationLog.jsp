<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/sumOperationLog/sumOperationLog.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					汇总名称： 
				</td>
				<td>
					<input type="text" name="name" value="${param.name}"/>
				</td>				
				<td>
					汇总结果：
				</td>
				<td>
				<select id="rusult" name="rusult" style="width: 210px; ">
				<option value="">所有</option>
		        <option value="成功">成功</option>
		        <option value="失败">失败</option>
		        
		      </select>
					<script type="text/javascript">
                        getObjByIdInCurrentTab("rusult").val("${param.rusult}");
                    </script>
				</td>
				<td>
					汇总方式：
				</td>
				<td>
				<select id="operationType" name="operationType" style="width: 210px; ">
				<option value="">所有</option>
		        <option value="manual">手动</option>
		        <option value="auto">自动</option>
		        
		      </select>
					<script type="text/javascript">
                        getObjByIdInCurrentTab("operationType").val("${param.operationType}");
                    </script>
				</td>
				
				<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	
	<table class="table" width="100%" layoutH="98">
		<thead>
			<tr>
				<th >汇总名称</th>
				<th width="8%">汇总时间</th>
				<th >开始时间</th>
				<th >结束时间</th>
				<th >汇总结果</th>
				<th >汇总方式</th>
				<th >汇总类型</th>
				<th width="15%">汇总描述</th>
				<th >创建时间</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
				
					<%--需维护资料--%>
					
					<td>${o.name}</td>
					<td>${o.statisticsTime}</td>
					<td><fmt:formatDate value="${o.startTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${o.endTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.rusult}</td>
					<td>${o.operationType eq 'auto' ?'自动':'手动'}
					
					</td> 
					<td>${o.sumType}</td>
					<td>${o.remak}</td>
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

