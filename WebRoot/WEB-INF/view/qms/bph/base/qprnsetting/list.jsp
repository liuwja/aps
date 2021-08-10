<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">


function uploadSuccess(file, data, response)
{
	$("#uploadFileName").val(file.name);
	$("#queryByExcel").val("true");
}
function uploadFinish(queueData)
{
    if (queueData.uploadsErrored) {
        alertMsg.error("上传错误");
    } else {
        alertMsg.correct("上传完成");
    }
    $.pdialog.closeCurrent();
}

function uploadExcel()
{
    var url = "system/qprnsetting/toUpload.do";
    
    var opt = {width:550,height:300, mask:true};
    $.pdialog.open(url, "newOrderItemDlg", "上传Excel", opt);
}
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/qprnsetting/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
			    <td>
					QPRN：
				</td>
				<td>
					<input type="text" name="qprn" value="${param.qprn }"/>
				</td>	
				<td>
					得分类型：
				</td>
				<td>
					<input type="text" name="scoreType" value="${param.scoreType }"/>
				</td>					
				 <td>
	            	<input type="hidden" name="queryByExcel" id="queryByExcel"  value="${vo.queryByExcel}" />
	            	<div class="buttonActive"><div class="buttonContent"><button type="button"  onclick="uploadExcel()">上传Excel</button></div></div>
	            </td>
	            <td>
	                <input type="text" name="uploadFileName" id="uploadFileName" value="${vo.uploadFileName}" readonly="readonly"/>
	            </td> 
	            <td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				</td>	  		
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
<shiro:hasPermission name="base:qprnsetting:ADD">
		    <li><a class="add" href="system/qprnsetting/add.do"  mask="true" target="dialog" height="300" width="800"  title="新增-QPRN分数"><span>新增</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:qprnsetting:EDIT">
			<li><a class="edit" href="system/qprnsetting/edit.do?id={key}"  mask="true" target="dialog" height="300" width="800"   title="QPRN分数"><span>修改</span></a></li>
</shiro:hasPermission>
<shiro:hasPermission name="base:qprnsetting:DEL">
			<li><a class="delete" href="system/qprnsetting/delete.do?id={key}"  target="ajaxTodo"   title="确定删除该QPRN分数吗？"><span>删除</span></a></li>
</shiro:hasPermission>		
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th></th>
			    <th width="15%">QPRN</th>
				<th width="15%">得分类型</th>
				<th width="15%">是否满权重</th>
				<th width="15%">分数值</th>
				<th width="15%">创建人</th>
				<th width="15%">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<td>
						<input type="radio" group="code" name="key" value="${o.id}">
					</td>
		
					<td>${o.qprn}</td>
					<td>${o.scoreType eq 1 ? "得分" : "扣分"}</td>
					<td>${o.weight}</td>  
					<td>${o.score}</td> 
					<td>${o.createUser}</td>
					<td><fmt:formatDate value="${o.createTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../../_frag/pager/panelBar.jsp"></c:import>
</div>
