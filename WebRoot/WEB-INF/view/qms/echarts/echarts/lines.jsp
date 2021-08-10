<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>


<style>
h1{font-size:16px;}
.test{overflow:hidden;width:100%;margin:0;padding:0;list-style:none;}
.test li{float:left;width:100px;height:100px;margin:0 5px;border:1px solid #ddd;background-color:#eee;text-align:center;transition:background-color .5s ease-in;}
.test li:nth-child(1):hover{background-color:#bbb;}
.test li:nth-child(2):hover{background-color:#999;}
.test li:nth-child(3):hover{background-color:#630;}
.test li:nth-child(4):hover{background-color:#090;}
.test li:nth-child(5):hover{background-color:#f00;}
.test1{
width: 100px;height:100px;border:1px solid #ddd;background-color:#eee;position: relative;top:20px;left:20px;transition:left 2s;
}
/**
#testTran:hover{
left:200px;
}
*/

</style>
<script type="text/javascript">
function changeWith(){
	$("#testTran").toggle(function(){
	//	$("#testTran").css("left","20px");
	})
}
</script>


<h1>请将鼠标移动到下面的矩形上：</h1>
<ul class="test">
	<li>背景色过渡</li>
	<li>背景色过渡</li>
	<li>背景色过渡</li>
	<li>背景色过渡</li>
	<li>背景色过渡</li>
</ul>
<button onclick="changeWith()">点我</button>

<div id="testTran" class="test1"></div>


