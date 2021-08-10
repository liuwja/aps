<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">

//<!--

jQuery(document).ready(function() {
    loadSelectedPartType();
});
 function loadSelectedPartType()
 {
	 var seldata = "${data}";
     var data = $("#"+seldata+"_data").val();
	 if($.trim(data) != "")
	 {
        //alert(data);
		 var userRowArr = new Array();
		 var arr = data.split(";");
		 //alert(arr.toString());
		 for(var i=0 ;i<arr.length ;i++){
		  //  alert(arr[i].indexOf(","));
		    if(arr[i].indexOf(",")==0){
		    	arr[i]=arr[i].substring(1,arr[i].length);
		    //	alert(arr[i]);
		    }
			var arr2 = arr[i].split(",");
			if(arr2.length >1){
				var str = arr2[0]+","+arr2[1]+","+arr2[2]+";";
				var vv = "{id:'" +arr2[0]+"',partCode: '"+arr2[1]+"',partName:'"+arr2[2]+"',data:'"+str+"'}" ;
				userRowArr.push("<tr  target='id' id='" + arr2[0] +"'>");
				userRowArr.push("<td style='text-align: center;'>");
				userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'"/>');
				userRowArr.push("</td>");
				userRowArr.push("<td >");
				userRowArr.push(""+arr2[1]+"");
				userRowArr.push("</td>");
				userRowArr.push("<td >");
				userRowArr.push(""+arr2[2]+"");
				userRowArr.push("</td>");
				userRowArr.push("<td style='text-align: center;' width='10%''>");
				userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
				userRowArr.push("</td>");
				 
				userRowArr.push("</tr>");
			}
			
			}
		 $("#selectFaultReasonTbody",$.pdialog.getCurrent()).append(userRowArr.join(""));
	     $("#selectFaultReasonAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectFaultReasonTbody tr",$.pdialog.getCurrent()).length);
		}
		 
		 
	 }

 function delSelectedUser(obj)
 {
	 $(obj).parent().parent().remove();
	 $("#selectFaultReasonAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectFaultReasonTbody tr",$.pdialog.getCurrent()).length);
 }
function selectRow(row,keystr,name,meshName)
{
	var obj = $("#selectFaultReasonTbody",$.pdialog.getCurrent()).find("tr[id="+keystr+"]");
       
    if(obj.length == 0)
    {
	var cloneRow = $(row).clone(true);
//	alert(cloneRow.html());
	cloneRow.find("td:eq(0)").remove();
	 var str = keystr+","+name+","+meshName+";";
	 var userRowArr = new Array();
	 userRowArr.push("<tr  target='id' id='" + keystr +"'>");
	 userRowArr.push("<td style='text-align: center;'>");
	 var vv = "{id:'" +keystr+"',partCode: '"+name+"',partName:'"+meshName+"',data:'"+str+"'}" ;
	 
     userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'"/>');
	 userRowArr.push("</td>");
	 userRowArr.push(cloneRow.html());
	 userRowArr.push("<td style='text-align: center;' width='10%''>");
	 userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
	 userRowArr.push("</td>");
	 
	 userRowArr.push("</tr>");
	 //alert(userRowArr.join(""));
	 $("#selectFaultReasonTbody",$.pdialog.getCurrent()).append(userRowArr.join(""));
     $("#selectFaultReasonAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectFaultReasonTbody tr",$.pdialog.getCurrent()).length);
    }
    else
   	{
        alert("此物料已选择");	
   	}
}

    function removeSelectedItem()
    {
    	var seldata = "${data}";
        $("#"+seldata+"_data").val("");
        $("#"+seldata+"_partName").val("");
        $('#selectFaultReasonTbody tr').remove();
         
        $("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
    }
    function callFunc()
    {
    	alert($("#noticeGroup_noticeMen").val());
    }

//-->
</script>
<div class="pageContent">
<%-- 待选择列表  开始--%>
	<div style="float: left;width: 55%;border: 1px solid #ededed;" id="toSelectFaultReasonList">
		<div  class="pageHeader">
		待选列表
		</div>
    	<form onsubmit="return  divSearch(this, 'toSelectFaultReasonList')" id="pagerForm" action="quality/testInstance/partSelect.do" method="post">
			<div class="pageHeader">
			    <div class="searchBar">
			        <table class="searchContent">
			 		
			        <tr>
			                <td class="dateRange">
								物料号:  		
                             
			                </td>
			                <td >
								<input type="text" name="partNumber" id="partNumber" size="10" value="${partNumber}"/>
								<input type="hidden" name="pageNum" value="1" />
    							<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
    							<input type="hidden" name="direction" value="" />
    							<input type="hidden" name="flag" value="1" />		 
			                </td>   
			                <td class="dateRange">
								物料名称:
			                </td>                                      
			                <td>
								<input type="text" name="partName" id="partName" size="10" value="${partName}"/>
			                </td> 
			                <td>
			                	<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>               
			                </td>  
			        </tr>
			        </table>
			    </div>
			</div>
	
	        <table class="table" width="100%" layoutH="150">
	            <thead>
	                <tr>
	                    <th width="10%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
	                    <th width="20%">物料编号</th>
	                    <th width="30%">物料名称</th>
	                </tr>
	            </thead>
	            <tbody> 
	                <c:forEach items="${list}" var="o">
	                    <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.partNumber}','${o.partName}')">
	                        <td style="text-align: center;">
	                            <input type="checkbox" name="chkbox" >
	                        </td>
	                        <td>${o.partNumber}</td>
	                        <td>${o.partName}</td>
	                    </tr>       
	                </c:forEach>        
	            </tbody>
	        </table>
	        
		   <c:import url="../../../_frag/pager/panelBar.jsp">
		       <%-- 对话框分页时需要传此参数 --%>
		       <c:param name="targetName" value="dialog"/>
		       <c:param name="relDivId" value="toSelectFaultReasonList"/>    
	       
	       </c:import>   
    	</form>
</div>
<%-- 待选择列表  结束--%>

<%-- 已选择列表  开始--%>
<div style="float: right;width: 44%;border: 1px solid #ededed;">
    <div class="pageHeader">
    已选择列表
    </div>
    <div class="pageHeader">    
        <div class="searchBar">
            <table class="searchContent">         
                <tr>
                    <td>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="removeSelectedItem()">全部删除</button></div></div>
                    </td>  
                           
                </tr>
            </table>
        </div>  
    </div>    
    <div id="selectedFaultTypeDiv">
    <table class="list" width="100%" layoutH="125">
            <thead>
                <tr>
                    <th width="50"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
                    <th >物料号</th>
                    <th >物料名称</th>
                    <th >操作</th>
                </tr>
            </thead>
            <tbody id="selectFaultReasonTbody"> 
            </tbody>
     </table>
    <div class="panelBar">
        <div class="pages">
            <span id="selectFaultReasonAmount">
                           已选记录数：0
            </span>
        </div>
    </div>       
    </div>
</div>
<%-- 已选择列表  结束--%>      
    	<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button"  multLookup="id" >确定</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close" >取消</button></div></div>
				</li>
			</ul>
		</div>

    </div>