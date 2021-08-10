<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style type="text/css">
.file2 {
    position: relative;
    display: inline-block;
    background: #EBF3F4;
    border: 1px solid #5195B7;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #00007C;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
    font-size: 18px;
}

.file2:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}

</style>
<script>
	
    jQuery(document).ready(function(){
    	$("#monthAssessFileUpload").change(function () {
            var filepath = $("#monthAssessFileUpload").val();
            if(filepath==""){
            	return false;
            }	
            var extStart = filepath.lastIndexOf(".");
            var ext = filepath.substring(extStart, filepath.length).toUpperCase();
            if (ext != ".XLS" ) {
                alert("仅支持xls后缀的excel");
                $("#fileUpload").val("");
                return false;
            } 
            $("#monthAssessExcelFileInputForm").ajaxSubmit({  
            	url:"base/monthAssesment/getExcelData.do",
                type:"post",  
                enctype:"multipart/form-data",  
                dataType:"html",  
                success: function(data){ 
            		//alert(data);
            		if(data==null || data==""){
						alert("上传文件数据异常");
            		}else{
                		if(data.substring(0,2)=="错误"){
                			alert(data);
                			return;
                		}
                		$("#monthAssessData").val(data);
                		var html = "";
            			var arr = data.split("&");
						for(var i=0 ;i<arr.length ;i++){
							html += "<tr>"
							var arr2 = arr[i].split("@");
							/**
							if(arr2.length!=9){
								alert(arr[i]+"  "+arr2.length+"文件列格式异常");
								return false;
							}
							*/
							for(var j=0 ;j<arr2.length ;j++){
								html += "<td>"+arr2[j]+"</td>";
							}
							html += "</tr>"
						}
						//alert(html);
						$('#monthAssesstb').empty();
		        		$('#monthAssesstb').append(html);
            		}
            		 getTableSize();
                },  
                error : function(XmlHttpRequest, textStatus, errorThrown){
               		alert("error");
               	} 
           
            });  
            return true; 
        });
    	
    });
    
    function getTableSize(){
    	 var idx =0;
    	 $("#monthAssesstb",navTab.getCurrentPanel()).find("tr").each(function(){
    	        idx++;
    	    })
    	 $("#tableNum",navTab.getCurrentPanel()).html("共"+idx+"条");   
    }

    function uploadMonthAssessData(){
        var url = "base/monthAssesment/uploadData.do";
        var uploadData = $("#monthAssessData").val();
        if(uploadData==null || uploadData==""){
			alert("请先上传文件或文件数据异常");
			return;
        }
    	$.post(url,{uploadData:uploadData},function(data){
			if(data.result== "success")
            {	    
        		alert("导入成功！");
        		$('#tb').empty();
            }
           	else
           	{
				alert("导入失败："+data.result);
           	}
		});
    }

</script>
<div class="pageHeader">
	<div>	 
       	<div class="searchBar">
       	<form id="monthAssessExcelFileInputForm"  action="base/monthAssesment/uploadData"  method="post"> 
			<input type="hidden" id="monthAssessData" value="" name="uploadData" />
			<table class="searchContent">
				<tr>
					<td>		
						<input type="file" title="请选择文件" class="file2" name="fileUpload" id="monthAssessFileUpload"/>  		
					</td>
					<td>
						<shiro:hasPermission name="base:monthAssesment:ADD">
						<input type="button" onclick="uploadMonthAssessData();" value="文件上传">	
						</shiro:hasPermission>
					</td>	
				</tr>
			</table>
		</form>
		</div>  
	</div>
</div>
<div class="pageContent">
	<table class="list" width="100%" layoutH="82">
		<thead>	                 	                                   
			<tr>
				<th >月份</th>
				<th >工厂</th>
				<th >车间</th>
				<th >班组类别</th>
				<th >班组名称 </th>
				<th > 项目代码</th>
				<th > 考核项目</th>	
				<th >项目比例</th>	
				<th >指标代码</th>	
				<th >考核指标</th>	
				<th >指标比例</th>	
				<th > 是否关键指标 </th>	
				<th >基准值 </th>	
				<th > 目标值</th>	
			</tr>
		</thead>
		<tbody id="monthAssesstb">
			
		</tbody>
	</table>
  	<div class="panelBar">
		<div class="pages">
			
			<span>&nbsp;&nbsp;&nbsp;</span><span id="tableNum"> </span>
		</div>
   </div>
</div>