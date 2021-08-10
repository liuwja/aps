<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
        <div class="pageFormContent" layoutH="56">
        <table class="searchContent nowrap" width="100%" >
	        <tr valign="top">
	            <td style="font-size: 18px;font-weight: bold;padding: 3px">请上传如下格式的Excel文件</td>
	            <td rowspan="2">
	                  <input id="testFileInput" type="file" name="image" 
	                    uploaderOption="{
	                        swf:'<c:url value='/jui/uploadify/scripts/uploadify.swf' />',
	                        uploader:'<c:url value='/system/qprnsetting/uploadExcel.do' />',
	                        buttonText:'请选择文件',
	                        fileSizeLimit:'2500KB',
	                        fileTypeDesc:'*.xls;',
	                        fileTypeExts:'*.xls;',
	                        height:30,
	                        width:150,
	                        auto:true,
	                        multi:false,
	                        onUploadSuccess:uploadSuccess,
	                        onQueueComplete:uploadFinish
	                }"
	            />
	            </td> 
	        </tr>
        <tr>
	            <td width="55%">
	            <img  src='<c:url value="/resources/img/excelSam.png"/>' border="1"/>
	            </td>
        </tr>
        </table>
        </div>
        <div class="formBar">
            <ul>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
                </li>
            </ul>
        </div>
        
</div>

