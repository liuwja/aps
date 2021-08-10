<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

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

</style>
    <form onsubmit="return navTabSearch(this);" action="pm/repaired.sp?method=dayReport" method="post">
        <table class="searchContent" id="repairReportForm" style="width: 100%;background-color: #ebf0f5">
            <tr style="height: 28px;line-height: 28px">
                <td>
                                            产线：
                </td>                       
                <td>
                <div id='prodLineList' class="dropdownlist"></div>
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
                <div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
            </td>
       </tr>
<script type="text/javascript">
<!--
$(function(){


$('#repairMethodList').dropdownlist({
    id:'repairMethodListTxt',
    columns:1,
    selectedtext:'',
    listboxwidth:150,//下拉框宽
    maxchecked:100,
    checkbox:true,
    listboxmaxheight:400,
    width:150,
    requiredvalue:[],
    selected:[],
    data:{'1':'维修', '2':'更换'},//数据，格式：{value:name}
    onchange:function(text,value){
    }
});

$('#prodLineList').dropdownlist({
    id:'prodLineListTxt',
    columns:3,
    selectedtext:'',
    listboxwidth:450,//下拉框宽
    maxchecked:100,
    checkbox:true,
    listboxmaxheight:400,
    width:150,
    requiredvalue:[],
    selected:['2','3'],
    data:{'1':'产线1','2':'产线2','3':'产线3','4':'产线4','5':'产线5','6':'产线6'},//数据，格式：{value:name}
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
            <tr align="center">
                    <td>aa</td>
                    <td>bb</td>
                    <td>cc</td>
            </tr>
        </tbody>
    </table>
    <!-- 分页相关 -->
</div>
