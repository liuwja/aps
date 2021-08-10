<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript" src="resources/js/ajaxfileupload.js"> </script>
<script type="text/javascript">
	function upload() {  
        var val=$('#dataImportTB input:radio[name="importType"]:checked', navTab.getCurrentPanel()).val();
        var startTime = $("#startTime",navTab.getCurrentPanel()).val();
        var endTime = $("#endTime",navTab.getCurrentPanel()).val();
        if(val == null)
       	{
            alert("请选择导入类型");
            return false;
       	}
        if ($("#excelFileInput", navTab.getCurrentPanel()).val().length == 0) {
           alert("请选择上传文件");
           return false;
        }
        if(val=='assembleReturn' || val =='partReturn'){
        	 if(startTime=='' || endTime==''){
             	alert("请输入期间");
             	return false;
             }
        }
        uploading();
        $("#dataImportForm",navTab.getCurrentPanel()).ajaxSubmit({  
        	url:"base/part/import/upload.do",
            type:"post",  
            enctype:"multipart/form-data",  
            dataType:"html",
            success: function(data){ 
            	if(data.indexOf("错误")>-1){
            		 alert("导入失败！");
            	}else{
            		 alert("导入成功！");
            	}
            	 $("#uploadResult").html(data);
            },  
            error : function(XmlHttpRequest, textStatus, errorThrown){
            	 alert("导入失败！");
            	 $("#uploadResult").html(errorThrown);
           	} 
       
        });  
/**	
        $.ajaxFileUpload({  
            url:'base/part/import/upload.do',  
            secureuri:false,  
            fileElementId:'excelFileInput',//file标签的id  
            dataType: 'json',//返回数据的类型  
            data:{importType : val},//一同上传的数据  
            success: function (data, status) {  
                       $("#uploadResult").html(data.message);
            },  
            error: function (data, status, e) {  
                $("#uploadResult").html(e);
            }  
        })
        */
}

function uploading (){
	$("#uploadingImg").ajaxStart(function(){
	$(this).show();
	}).ajaxComplete(function(){
	$(this).hide();
	});
}
</script>
<div class="pageContent" style="overflow:auto;height: 500px;">
<form id="dataImportForm"   method="post">
<fieldset>
 <legend>数据上传(Excel或者csv)</legend>
		<table id="dataImportTB" class="tableFormContent nowrap" >
            <tr>
            <th>
              期间
            </th>
            <td colspan="3">
              <input type='text' size='10' id='startTime' name='startTime' placeholder='请输入开始日期' onclick="laydate()" readonly='true'/> 至
             <input type='text' size='10' id='endTime' name='endTime' placeholder='请输入结束日期' onclick="laydate()" readonly='true'/>
            </td>
            </tr>
            <tr>
            <th>
            类型
            </th>
            <td colspan="3">
                <input  type="radio" name="importType" value="assembleReturn" id="radio_assembleReturn"/> 
                <label for="radio_assembleReturn"> ERP组装生产退次数</label>
                <input  type="radio" name="importType" value="partReturn" id="radio_partReturn"/> 
                <label for="radio_partReturn"> ERP原料退料数</label> 
                <input  type="radio" name="importType" value="wareHouse" id="radio_wareHouse"/> 
                <label for="radio_wareHouse"> 仓库机型类别对应关系</label> 
                <input  type="radio" name="importType" value="newPartRef" id="radio_newPartRef"/> 
                <label for="radio_newPartRef"> 新旧物料对应关系</label> 
                <input  type="radio" name="importType" value="supplierRef" id="radio_supplierRef"/> 
                <label for="radio_supplierRef"> 新旧供应商对应关系</label> 
            </td>
            </tr>		
			<tr>
			<th>
			数据上传 :
			</th>
			<td width="35%">
                <input id="excelFileInput" type="file" name="excelFile" accept="*.csv" style="cursor:hand"/>
			</td>
			<td width="10%">
                <img id="uploadingImg " src="resources/img/loading.gif" height="20" style="display: none;"/>
                <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="return upload();">导入</button></div></div>
			</td>
			<td id="uploadResult" style="color: red">
			
			</td>	
			</tr>
			
			<tr>
			 <th>ERP组装生产退次数</th>
			 <td colspan="3" style="color: red">
			 CSV格式。第一列为退次日期，第二列为物料编号，第三列为物料名称，第四列为供应商编号，第五列为供应商名称，第六列为退次数量，第七列为退次仓库
			 </td>
			</tr>
            <tr>
             <th>ERP原料退料数</th>
             <td colspan="3" style="color: red">
             CSV格式。第一列为退料日期，第二列为物料编号，第三列为物料名称，第四列为供应商编号，第五列为供应商名称，第六列为退料数量，第七列为退次仓库
             </td>
            </tr>
            <tr>
             <th>仓库机型类别对应关系</th>
             <td colspan="3" style="color: red">
             CSV格式。第一列为仓库编号，第二列为仓库名称，第三列为机型类别
             </td>
            </tr>            
            <tr>
             <th>新旧物料对应关系</th>
             <td colspan="3" style="color: red">
             CSV格式。第一列为旧物料编号，第二列为新物料编号，第三列为物料名称
             </td>
            </tr> 
            <tr>
             <th>新旧供应商对应关系</th>
             <td colspan="3" style="color: red">
             CSV格式。第一列为旧供应商编号，第二列为旧供应商名称，第三列为新供应商编号，第四列为新供应商名称
             </td>
            </tr> 
		</table>
</fieldset>	
</form>	
</div>
