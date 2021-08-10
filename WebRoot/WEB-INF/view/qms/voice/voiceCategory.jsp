<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<c:import url="../../../view/_frag/pager/pagerForm.jsp"></c:import>
<form  rel="pagerForm" action="voiceCategory/findAll.do" method="post">
</form>
<div class="searchContent dropdownSearchBar">
<div class="buttonActive">
<div class="buttonContent">
	<button onclick="updateVoiceCategory()">同步分类信息</button>
</div>
</div>
	<table id="treeTable" class="table" style="width: 100%" layoutH="120">
		<thead>
		
		<tr>
			<th>编号</th>
			<th>名称</th>
			<th>所属一级分类</th>		
		</tr>		
		</thead>
	<tbody>
		<c:forEach items="${list}" var="v">
			<tr>
				<td>${v.number}</td>
				<td>${v.name}</td>
				<td>${v.paterName != null?v.paterName:"一级分类"}</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	</div>
	<c:import url="../../../view/_frag/pager/panelBar.jsp"></c:import>
	<script type="text/javascript">
		function updateVoiceCategory(){
			var url = "voiceCategory/updateVoiceCategory.do";
			var data = "";
			var callback = function(data){
			if(data.result == 0){
			var success = "数据同步成功";
				if(data.add != null){
				success = success +"插入"+data.add+"条数据";
				}
				if(data.update != null){
				success = success +"更新"+data.add+"条数据";
				}
				if(data.delete != null){
				success = success +"删除"+data.add+"条数据";
				}
				alert(success);
			}else{
				alert("数据同步失败:"+data.msg);
			}
		}
	$.post(url,data,callback,"json");
	}
	</script>
