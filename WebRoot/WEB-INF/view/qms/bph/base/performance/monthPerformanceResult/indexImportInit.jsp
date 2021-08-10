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

.trbgcolor{
	color:red;
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
            	url:"ptm/monthPerformanceResult/getExcelData.do",
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
							if(i % 2 == 0){
								html += "<tr style='background-color:white;'>"
							}else{
								html += "<tr style='background-color:#F8F8F8;'>"
							}
							html += "<td>"+ (i+2) +"</td>"
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
        var url = "ptm/monthPerformanceResult/uploadData.do";
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
	<table class="list" width="150%" layoutH="82">
		<thead>	                 	                                   
			<tr>
				<th>序号</th>
				<th>年度</th>
				<th>工厂名称</th>
				<th>部门名称</th>
				<th>绩效目标大类</th>
				<th>绩效类型</th>
				<th>指标内容</th>
				<th>指标类型</th>	
				<th>权重</th>	
				<th>单位</th>	
				<th>计算公式</th>	
				<th>小于基准</th>
				<th>基准与目标之间</th>
				<th>大于目标</th>
				<th>上年度实际值</th>	
				<th>上半年基准值</th>	
				<th>本年度基准值</th>	
				<th>本年度目标值</th>	
				<th>上半年目标值</th>
				<th>下半年目标值</th>
				<th>月份</th>
				<th>当月目标值</th>
				<th>当月累计目标值</th>
				<th>当月挑战目标值</th>
				<th>当月实际值</th>
				<th>累计目标值</th>
				<th>考核结果</th>
				<th>状态</th>
				<th>创建人</th>
				<th>创建时间</th>
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