<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<script src="<c:url value='/resources/js/dropdownlist.js?version=1.01' />" type="text/javascript"></script>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css" media="screen">
.my-uploadify-button {
    background:none;
    border: none;
    text-shadow: none;
    border-radius:0;
}

.uploadify:hover .my-uploadify-button {
    background:none;
    border: none;
}

.fileQueue {
    width: 400px;
    height: 150px;
    overflow: auto;
    border: 1px solid #E5E5E5;
    margin-bottom: 10px;
}
/*下拉框样式*/

.dropdownlist .ddl-select,.dropdownlist .hover,.dropdownlist .ddl-disabled
{
cursor:default;padding:0px;color:#000;
border:solid 1px #ccc;height:20px;line-height:20px;padding-right:20px;
background:#fff url(<c:url value='/resources/img/downarrow.jpg' />) no-repeat right; 
overflow:hidden}
/*鼠标经过下拉框的样式*/
.dropdownlist .hover,.dropdownlist .ddl-select:hover{border-color:#aaa;}
/*下拉选项样式*/
.dropdownlist table,.dropdownlist tbody tr,.dropdownlist tbody td{border:none;border-collapse: collapse;border-spacing: 0px 0px;}
/*下拉列表样式*/
.dropdownlist .ddl-listbox{border:solid 1px #ccc;color:#000;overflow:hiden;overflow-y:auto;}
/*禁用时的样式*/
.dropdownlist .ddl-disabled{color:#ccc;background:#fff url(downarrow.jpg) no-repeat right;}
/*下拉选项样式*/
.dropdownlist tbody td{color:#000;background:#fff;height:30px;}
/*下拉选项鼠标经过样式*/
.dropdownlist tbody td.hover,.dropdownlist tbody td:hover{color:#fff;background:#00f}
/*tfoot*/
.dropdownlist tfoot{background:#f0f0f0;}
/*信息样式*/
.dropdownlist tfoot span.info{font-size:12px;color:blue;}
/*按钮区样式*/
.dropdownlist tfoot span.btn-area{float:right;}
/*确定按钮样式*/
.dropdownlist tfoot .btn-ok {color:red;}
/*取消按钮样式*/
.dropdownlist tfoot .btn-cancel {color:#666;}
</style>
<script type="text/javascript">
<!--

function resetAllInput()
{
	$("#accembleForm :text").val("");
}
function loadCondition()
{
	var url = "<c:url value='/pm/repaired.sp?method=loadQueryCondiftionByFactory' />";
	$("#repairReportForm").load(url,{baseFactory: $("#baseFactory_dayreport").val()});
}
//-->
</script>
    <form onsubmit="return navTabSearch(this);" rel="pagerForm" action="pm/repaired.sp?method=dayReport" method="post">
        <table class="searchContent dropdownSearchBar" id="repairReportForm">
            <tr style="height: 28px;line-height: 28px">
                <td>
                                            工厂： 
                </td>                       
                <td>
                <select name="baseFactory" id="baseFactory_dayreport"  onchange="loadCondition()">
                        <c:forEach items="${common_factoryList}" var="o">
                        <option value="${o.name}">${o.name}</option>
                        </c:forEach>
                </select>
                <script type="text/javascript">
                    $("#baseFactory_dayreport").val("${vo.baseFactory}");
                </script>
                </td>
                <td>
                                            产线：
                </td>                       
                <td>
                <div id='prodLineList' class="dropdownlist"></div>
                </td>
                <td>产品型号：</td>
                <td>
                <div id='partclassList' class="dropdownlist"></div>   
                </td>
	            <td>
	            维修方式
	            </td>
	            <td>
	                <div id='repairMethodList' class="dropdownlist"></div>
	            </td>
            </tr>
            
            
            <%-- line --%>
            <tr style="height: 28px;line-height: 28px">
            <td>
            不良现象
            </td>  
            <td>
                <div id='defClassList' class="dropdownlist"></div>            
            </td>              
              <td>
              不良原因
            </td> 
            <td>
            <div id='defReasonList' class="dropdownlist"></div>   
            </td>             
                <td>责任单位：</td>
                <td>
                 <div id='respList' class="dropdownlist"></div>
                </td>                             
            <td>
                <div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
            </td>
       </tr>
       <tr>
      		<td>
				开始时间： 
			</td>
			<td>
				<input type="text" name="startTime" placeholder="请输入日期"  onclick="laydate()" readonly="readonly"  size="10"  value="${vo.startTime}"/>&nbsp;至&nbsp;<input type="text" name="endTime" placeholder="请输入日期"  onclick="laydate()" size="10" readonly="readonly" value="${vo.endTime}"/>
			</td>
       		<td>国内外：</td>
			<td><select name="foreign" id="foreignForMul">
				<option value="">请选择</option>
				<option value="国内">国内</option>
				<option value="国外">国外</option>
			</select>
				<script type="text/javascript">
					$("#foreignForMul").val("${vo.foreign}");
				</script>
			</td>
			<td>新品量产：</td>
			<td><select name="newProduct" id="newProductForMul">
				<option value="">请选择</option>
				<option value="新品">新品</option>
				<option value="量产">量产</option>
			</select>
				<script type="text/javascript">
					$("#newProductForMul").val("${vo.newProduct}");
				</script>
			</td>
			<td></td>
			<td></td>
       
       </tr>  
<script type="text/javascript">
<!--
$(function(){

$('#defClassList').dropdownlist({
    id:'defClassListTxt',
    columns:2,
    selectedtext:'',
    listboxwidth:500,//下拉框宽
    maxchecked:100,
    checkbox:true,
    listboxmaxheight:400,
    width:200,
    requiredvalue:[],
    selected:[${vo.convertDefClassListTxt}],
    data:${jsonDefobjs},//数据，格式：{value:name}
    onchange:function(text,value){
        //alert(text)
        //alert(value);
    }
});

$('#defReasonList').dropdownlist({
    id:'defReasonListTxt',
    columns:3,
    selectedtext:'',
    listboxwidth:700,//下拉框宽
    maxchecked:100,
    checkbox:true,
    listboxmaxheight:400,
    width:120,
    requiredvalue:[],
    selected:[${vo.convertDefReasonListTxt}],
    data:${tJsonObject},//数据，格式：{value:name}
    onchange:function(text,value){
        //alert(text)
        //alert(value);
    }
});

$('#partclassList').dropdownlist({
    id:'partclassListTxt',
    columns:2,
    selectedtext:'',
    listboxwidth:300,//下拉框宽
    maxchecked:100,
    checkbox:true,
    listboxmaxheight:400,
    width:200,
    requiredvalue:[],
    selected:[${vo.convertPartclassListTxt}],
    data:${classJsonObject},//数据，格式：{value:name}
    onchange:function(text,value){
        //alert(text)
        //alert(value);
    }
});

$('#respList').dropdownlist({
    id:'respListTxt',
    columns:1,
    selectedtext:'',
    listboxwidth:200,//下拉框宽
    maxchecked:100,
    checkbox:true,
    listboxmaxheight:200,
    width:200,
    requiredvalue:[],
    selected:[${vo.convertRespListTxt}],
    data:${respJsonObject},//数据，格式：{value:name}
    onchange:function(text,value){
        //alert(text)
        //alert(value);
    }
});

$('#repairMethodList').dropdownlist({
    id:'repairMethodListTxt',
    columns:1,
    selectedtext:'',
    listboxwidth:120,//下拉框宽
    maxchecked:100,
    checkbox:true,
    listboxmaxheight:400,
    width:120,
    requiredvalue:[],
    selected:[${vo.convertRepairMethodListTxt}],
    data:{'1':'维修', '2':'更换'},//数据，格式：{value:name}
    onchange:function(text,value){
    }
});

$('#prodLineList').dropdownlist({
    id:'prodLineListTxt',
    columns:1,
    selectedtext:'',
    listboxwidth:150,//下拉框宽
    maxchecked:100,
    checkbox:true,
    listboxmaxheight:400,
    width:120,
    requiredvalue:[],
    selected:[${vo.convertProdLineListTxt}],
    data:${prodJsonObject},//数据，格式：{value:name}
    onchange:function(text,value){
    }
});


});
//-->
</script>            
        </table>
    </form>
<div class="pageContent">
    <table class="table" width="100%" layoutH="130">
        <thead>
            <tr>
            	<th width="150" >日期</th>
                <th width="40" >生产数</th>
                <th width="100">不良数</th>
            </tr>
        </thead>
        <tbody>
        <!-- 文字在左，数字在右 -->
            <c:forEach items="${list}" var="o" varStatus="s">
            <tr align="center">
                    <td>${o.datas[0] }</td>
                    <td>${o.datas[1] }</td>
                    <td>${o.datas[2] }</td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
    <!-- 分页相关 -->
    <c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>
