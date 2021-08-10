<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<style>
lable{
   float: left;
   
}
</style>

<script type="text/javascript">
var index = 0;

function addItem()
{   
	var arr = new Array();
	arr.push("<tr id='newArray' style='height:28px;'>");		
	arr.push("<td style='padding-left:5px;padding-right:5px;text-align:center;width:30%'>");
	arr.push("<input name='queryMonths' id='queryMonth' placeholder='请输入日期' onclick=\"laydate({isym:true, format:'YYYY-MM'})\"  readonly='true' class='required'/>");
	arr.push("</td>");
	arr.push("<td style='padding-left:5px;padding-right:5px;text-align:center;'><input name='targetValues' id='targetValue' type='text' size='10'   class='required'/></td>");
	arr.push("<td style='padding-left:5px;padding-right:5px;text-align:center;width:10%;'><input type='button' id='delbtn'  value='删除' onclick='removeItem(this)' class='delButton' /></td>");
	arr.push("</tr>");	  
	var html = arr.join("");
	$("#printConfigsList").append(html);
	$("#printConfigsList tr").last().find(".btnLook").lookup(); 
	index ++;

}
function removeItem(obj)
{
    $(obj).parent().parent().remove();
}
function constructData()
{	
   	var orderArr = new Array();
    $.each($("#newArray",$.pdialog.getCurrent()),function(){
	    var queryMonth = $(this).find("input[name=queryMonths]").val();
	    var targetValue = $(this).find("input[name=targetValues]").val();
	    orderArr.push("{'queryMonth':'"+queryMonth+"'");
	    orderArr.push("'targetValue':'"+targetValue+"'}");
    });

    var factory = $('#factory',$.pdialog.getCurrent()).val();
    var checkIndexName = $('#checkIndexName',$.pdialog.getCurrent()).val();
    
    
	var formDataArr = new Array();
    formDataArr.push("{'factory':'"+factory+"'");
    formDataArr.push("'checkIndexName':'"+checkIndexName+"'");
    formDataArr.push("'items':[" + orderArr.join(",") + "]}");
    $("#formJsonValue").val(formDataArr.join(","));	    
	
    return true;
}

function getCheckIndex(){
	var darr = new Array();
	var rarr = new Array();
	var dearr = new Array();
	darr.push("<option value=''>请选择</option>");
	darr.push("<option value='油烟机开箱不良'>油烟机开箱不良</option>");
	darr.push("<option value='消毒柜开箱不良'>消毒柜开箱不良</option>");
	darr.push("<option value='油烟机OQC不良'>油烟机OQC不良</option>");
	darr.push("<option value='消毒柜OQC不良'>消毒柜OQC不良</option>");
	darr.push("<option value='组装一次合格率'>组装一次合格率</option>");
	darr.push("<option value='喷涂一次合格率'>喷涂一次合格率</option>");
	darr.push("<option value='精加工一次合格率'>精加工一次合格率</option>");
	darr.push("<option value='冲压一次合格率'>冲压一次合格率</option>");
	
	rarr.push("<option value=''>请选择</option>");
	rarr.push("<option value='灶具开箱不良'>灶具开箱不良</option>");
	rarr.push("<option value='热水器开箱不良'>热水器开箱不良</option>");
	rarr.push("<option value='灶具OQC不良'>灶具OQC不良</option>");
	rarr.push("<option value='热水器OQC不良'>热水器OQC不良</option>");
	rarr.push("<option value='灶具组装一次合格率'>灶具组装一次合格率</option>");
	rarr.push("<option value='热水器组装一次合格率'>热水器组装一次合格率</option>");
	rarr.push("<option value='精加工一次合格率'>精加工一次合格率</option>");
	rarr.push("<option value='冲压一次合格率'>冲压一次合格率</option>");

	dearr.push("<option value=''>请选择</option>");
	dearr.push("<option value='微蒸烤开箱不良'>微蒸烤开箱不良</option>");
	dearr.push("<option value='微蒸烤OQC不良'>微蒸烤OQC不良</option>");
	
	$("#checkIndexName",$.pdialog.getCurrent()).empty();
	var factory = $("#factory",$.pdialog.getCurrent()).val();
//	alert(factory);
	if(factory =="电器一厂"){

		var html = darr.join("");
		$("#checkIndexName",$.pdialog.getCurrent()).append(html);
	}else if(factory == "燃气工厂"){
		
		var html = rarr.join("");
		$("#checkIndexName",$.pdialog.getCurrent()).append(html);
	}else {

		var html = dearr.join("");
		$("#checkIndexName",$.pdialog.getCurrent()).append(html);
	}
	
}
function checkInput(){
	 var result = getMonth();
	 if(result==false){
	    	alert("请选择月份");
	    	return false;
	    }
	 var result1 = getTarget();
	 if(result1 ==false){
		 alert("请填写相应月度目标");
		 return false;
	 }
}
function getMonth() {
//	alert("ok");
	var year = $("#year",$.pdialog.getCurrent()).val();
	if(year == ""){
		alert("请选择年度！");
		return false;
	}
//	alert(year);
    var monthValArr = [];
    $("input[name='month']:checkbox").each(function(){ 
        if($(this).attr("checked")){
        	monthValArr.push(year+$(this).val());
//            alert(monthValArr.join(","));
        }
    });
    if(monthValArr==null || monthValArr.length==0){
		return false;
    }
    
    $("#queryMonth",$.pdialog.getCurrent()).val(monthValArr.join(",")); 
}
function getTarget(){
//	 alert("ok");
	 var targetValArr = [];
	 var index =1;
	    $("#target",$.pdialog.getCurrent()).each(function(){ 
	            if($(this).val()!=""){
		        	targetValArr.push(index+"-"+$(this).val());
//		            alert(targetValArr.join(","));               
	            }
	            index++;
	    });
	    if(targetValArr==null || targetValArr.length==0){
			return false;
	    }
	    
	    $("#targetValue",$.pdialog.getCurrent()).val(targetValArr.join(",")); 

}
</script>
<div class="pageContent">
	<!-- navTabId重新载入index对应的tab -->
	<form method="post" id="formID" action="<c:url value='system/countPerformanceMonth/save.do?navTabId=countPerformanceMonthNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate nowrap" onsubmit="return (constructData()&&validateCallback(this, dialogAjaxDone));">
		<div class="pageFormContent" layoutH="100">
			
			<table class="tableFormContent nowrap">
				<tr >
					<th>工厂</th>
					<td><select id="factory" name="factory" class="required" onchange="getCheckIndex()">
					    <option value="">请选择</option>
				        <option value="电器一厂">电器一厂</option>
				        <option value="燃气工厂">燃气工厂</option>
				        <option value="电器二厂">电器二厂</option>
				        </select>
				    </td>
				    <th >考核指标</th>
				    <td >
				       <select name="checkIndexName" id="checkIndexName" class="required">
				         <option>请选择</option>
				       </select>			      
				    </td>		
				</tr>
				<tr>
				   <th>年度</th>
				   <td colspan="3"><input  id='year' placeholder='请输入日期' onclick="laydate({isym:true, format:'YYYY'})"  readonly='true' class='required'/></td>
				</tr>
				<tr>
				   <th >月份</th>
				   <td colspan="3" >
				   <input type="hidden" name="queryMonth" id="queryMonth">
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-01">一月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-02">二月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"> <input type="checkbox" id="month" name="month" value="-03">三月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-04">四月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-05">五月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-06">六月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-07">七月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-08">八月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-09">九月&nbsp;</label>
				       <label style="width:50px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-10">十月&nbsp;</label>
				       <label style="width:60px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-11">十一月&nbsp;</label>
				       <label style="width:60px; margin-top:3px; padding:1px;"><input type="checkbox" id="month" name="month" value="-12">十二月&nbsp;</label>
				   </td>				  
				</tr>
				<tr>
				   <th >月度目标</th>
				   <td colspan="3">
				        <input type="hidden" id="targetValue" name="values">
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:50px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:60px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				        <lable style="width:60px; margin-top:5px; padding:1px;"><input type="text" id="target" class="number" size="2">&nbsp;&nbsp;</lable>
				   </td>
				</tr>
																	
			</table>
		</div>

		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button onclick="checkInput();">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
