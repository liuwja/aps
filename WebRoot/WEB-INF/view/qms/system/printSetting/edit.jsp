<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
var delItemArr = new Array();
function addItem()
{
     var arr = new Array();
     arr.push("<tr>");
     arr.push("<td><input name='paramName' type='text' size='25'  /></td>");
     arr.push("<td><input name='paramValue' type='text' size='25'  /></td>");
     arr.push("<td><input type='button' id='delbtn'  value='删除' onclick='removeItem(this)' class='delButton' /></td>");
     arr.push("</tr>");
     var html = arr.join("");
     $("#printConfigsList").append(html);
}
function removeItem(obj)
{
    $(obj).parent().parent().remove();
    delItemArr.push($(obj).parent().parent().find("input[name=paramName]").val());
}
function constructData()
{
    try{
    var orderArr = new Array();
    $.each($("#printConfigsList tr"),function(){
           var paramName = $(this).find("input[name=paramName]").val();
           var paramValue = $(this).find("input[name=paramValue]").val();
           
           orderArr.push("{'paramName':'"+paramName+"'");
           orderArr.push("'paramValue':'"+paramValue+"'}");
    });
        
        var barcodeTemplate = $("#barcodeTemplate").val();
        var partDescription = $("#partDescription").val();
        var partNumber = $("#printConfigForm").find("#partNumber").val();
        var templateType = $("#printConfigForm").find("#templateType").val();
        
    var formDataArr = new Array();
        formDataArr.push("{'barcodeTemplate':'"+barcodeTemplate+"'");
        formDataArr.push("'partDescription':'"+partDescription+"'");
        formDataArr.push("'partNumber':'"+partNumber+"'");
        formDataArr.push("'templateType':'"+templateType+"'");
        formDataArr.push("'items':[" + orderArr.join(",") + "]}");
        
        $("#formJsonValue").val(formDataArr.join(","));
        
        var delItems = delItemArr.join("#$@");
        $("#delItemNames").val(delItems);
        }catch(e)
        {
            
        }
        return true;
}
</script>
<div class="pageContent">
    <form method="post" id="printConfigForm" action="<c:url value='system/printSetting.sp?method=saveConfig&navTabId=prtconfigNav&callbackType=closeCurrent'/>" 
    class="pageForm required-validate" onsubmit="return (constructData() && validateCallback(this, dialogAjaxDone));">
        <div class="pageFormContent" layoutH="286" >
        <table class="tableFormContent nowrap">
            <tr >
                <th>条码模板：</th>
                <td><input name="barcodeTemplate" id="barcodeTemplate" type="text" size="30" class="required" value="${config.barcodeTemplate }" />
                    <input type="hidden" id="key" name="key" value="${config.key}">
                    <input type="hidden" id="formJsonValue" name="formJsonValue">
                    <input type="hidden" id="delItemNames" name="delItemNames">
                </td>
                <th>产品名称：</th>
                <td><input name="partDescription" id="partDescription" type="text" size="30" class="required" value="${config.partDescription}" /></td>           
            </tr>
            
            <tr >
                <th>产品代码：</th>
                <td><input name="partNumber" id="partNumber" type="text" size="30" class="required" value="${config.partNumber}" /></td>
                <th>模板类型：</th>
                <td>
	               <select name="templateType" id="templateType">
		                <option value="主机条码" <c:if test="${config.templateType == '主机条码'}">selected="selected"</c:if> >主机条码</option>
		                <option value="包装铭牌" <c:if test="${config.templateType == '包装铭牌'}">selected="selected"</c:if>>包装铭牌</option>
		                <option value="物料条码" <c:if test="${config.templateType == '物料条码'}">selected="selected"</c:if> >物料条码</option>
		                <option value="喷涂件" <c:if test="${config.templateType == '喷涂件'}">selected="selected"</c:if>>喷涂件</option>		                
	               </select>
            </tr>           
        </table>
        </div>
        
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="###" onclick="addItem()"><span>新增</span></a></li>
        </ul>
    </div>  
    <div class="pageFormContent" layoutH="240" >
        <table class="tableFormContent nowrap">
            <tr >
                <th>模板参数</th>
                <th>参数值</th>
                <th>操作</th>
            </tr>
            <tbody id="printConfigsList">
	            <c:forEach items="${config.printParamMap}" var="item">
	                <tr>
	                    <td><input name="paramName" type="text" size="30"  value="${item.key }" readonly="readonly"/></td>
	                    <td><input name="paramValue" type="text" size="30" value="${item.value}" readonly="readonly"/></td>  
	                    <td><input type='button' id='delbtn'  value='删除' onclick='removeItem(this)' class='delButton' /></td>       
	                </tr>           
	            </c:forEach>
            </tbody>            
        </table>
                </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
                </li>
            </ul>
        </div>
    </form>
</div>
