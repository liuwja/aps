<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
     		<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadPlienChart();">查找</button></div></div>
					<script type="text/javascript">
						$(function(){
							$('#${plineDocId}').dropdownlist({
							    id:'plineListTxt',
							    columns:3,
							    selectedtext:'',
							    listboxwidth:450,//下拉框宽
							    maxchecked:100,
							    checkbox:true,
							    listboxmaxheight:400,
							    width:120,
							    requiredvalue:[],
							    selected:[],
							    data:${jsonLines},//数据，格式：{value:name}
							    onchange:function(text,value){
							    }
							});
							
						});
//-->
</script>            