<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
			$(function(){	
				$('#shiftGroupList', navTab.getCurrentPanel()).dropdownlist({
					    id:'shiftGroup',
					    columns:3,
					    selectedtext:'',
					    listboxwidth:450,//下拉框宽
					    maxchecked:100,
					    checkbox:true,
					    listboxmaxheight:400,
					    width:150,
					    requiredvalue:[],
					    selected:[${vo.baseGroup}],
					    data:${jsonParts},//数据，格式：{value:name}
					    onchange:function(text,value){
					    }
					});
					
				});
//-->
</script>            