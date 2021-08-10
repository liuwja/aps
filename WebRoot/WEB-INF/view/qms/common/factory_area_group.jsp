<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>  
<script type="text/javascript">

function doAreaSelectBase(idEnd){
	var fvalue = $("#baseFactory_"+idEnd).find("option:selected").text();
	
	var select = $("#baseArea_"+idEnd);
	select.empty();
	select.append("<option value=''>请选择</option>"); 
	if(fvalue.indexOf("请选择")>-1){
    	return ;
	}
	<c:forEach items="${common_mapAreas}" var="fo">
		var fkey = '${fo.key}';
		if(fkey == fvalue){
			<c:forEach items="${fo.value}" var="area">
			select.append("<option value='${area.area}'>${area.area}</option>"); 
			</c:forEach>
			}
	</c:forEach>
//	doCategorySelectBase(idEnd);
//	doFagroupSelectBase(idEnd);
}

function doFcategorySelectBase(idEnd){
//	var type = '${param.fcategory}';
    var fcategorytype = $("#fcategory_"+idEnd).val();
	if(fcategorytype==1){
		var fvalue = $("#baseFactory_"+idEnd).find("option:selected").text();
		
		var select = $("#baseCategory_"+idEnd);
		select.empty();
		select.append("<option value=''>请选择</option>"); 
		if(fvalue.indexOf("请选择")>-1){
	    	return ;
		}
		<c:forEach items="${common_fmapCates}" var="fo">
			var fkey = '${fo.key}';
			if(fkey == fvalue){
				<c:forEach items="${fo.value}" var="cate">
				   select.append("<option value='${cate.shiftGroupCategory}'>${cate.shiftGroupCategory}</option>"); 
				</c:forEach>
				}
		</c:forEach>
	}
	
//	doCategorySelectBase(idEnd);
//	doFagroupSelectBase(idEnd);
}

function doFgroupSelectBase(idEnd){
//	var type = "${param.fgroup}";
    var fgrouptype =$("#fgroup_"+idEnd).val();
	if(fgrouptype==1){
		var fvalue = $("#baseFactory_"+idEnd).find("option:selected").text();
		
		var select = $("#baseGroup_"+idEnd);
		select.empty();
		select.append("<option value=''>请选择</option>"); 
		if(fvalue.indexOf("请选择")>-1){
	    	return ;
		}
		<c:forEach items="${common_fgroupMap}" var="fo">
			var fkey = '${fo.key}';
			if(fkey == fvalue){
				<c:forEach items="${fo.value}" var="group">
				select.append("<option value='${group.shiftGroup}'>${group.shiftGroup}</option>"); 
				</c:forEach>
				}
		</c:forEach>
	}
	
}

function doCategorySelectBase(idEnd){
	var fvalue = $("#baseFactory_"+idEnd).find("option:selected").text();
	var avalue = $("#baseArea_"+idEnd).find("option:selected").text();
	var favalue = fvalue+"-"+avalue;

	var select = $("#baseCategory_"+idEnd);
	select.empty();
	select.append("<option value=''>请选择</option>");
	if(favalue.indexOf("请选择")>-1){
   		return ;
	}
	
	<c:forEach items="${common_mapCates}" var="ao">
		var akey = '${ao.key}';
		if(akey == favalue){ 
			<c:forEach items="${ao.value}" var="cate">
			select.append("<option value='${cate.shiftGroupCategory}'>${cate.shiftGroupCategory}</option>");
			</c:forEach>
			}
	</c:forEach>
}


function doFagroupSelectBase(idEnd){
//	var type = "${param.fagroup}";
    var fagrouptype =$("#fagroup_"+idEnd).val();
	if(fagrouptype==1){
		var fvalue = $("#baseFactory_"+idEnd).find("option:selected").text();
		var avalue = $("#baseArea_"+idEnd).find("option:selected").text();
		var favalue = fvalue+"-"+avalue;
		var select = $("#baseGroup_"+idEnd);
		select.empty();
		select.append("<option value=''>请选择</option>");
		if(favalue.indexOf("请选择")>-1){
	   		return ;
		}
		
		<c:forEach items="${common_fagroupMap}" var="ao">
			var akey = '${ao.key}';
			if(akey == favalue){ 
				<c:forEach items="${ao.value}" var="group">
				select.append("<option value='${group.shiftGroup}'>${group.shiftGroup}</option>");
				</c:forEach>
				}
		</c:forEach>
	}
	
}

function doCheckItemSelectBase(idEnd){
	var fvalue = $("#baseFactory_"+idEnd).find("option:selected").text();
	var avalue = $("#baseArea_"+idEnd).find("option:selected").text();
	var cvalue = $("#baseCategory_"+idEnd).find("option:selected").text();
	var favalue = fvalue+"-"+avalue+"-"+cvalue;
	var select = $("#baseCheckItem_"+idEnd);
	select.empty();
	select.append("<option value=''>请选择</option>");
	if(favalue.indexOf("请选择")>-1){
   		return ;
	}
	
	<c:forEach items="${common_checkItemMap}" var="ao">
		var akey = '${ao.key}';
		if(akey == favalue){ 
			<c:forEach items="${ao.value}" var="check">
			   select.append("<option value='${check.checkItem}'>${check.checkItem}</option>");
			</c:forEach>
			}
	</c:forEach>
}
</script>
<c:set var="factory_length" value="${fn:length(common_factoryList)}"></c:set>
<!--1、dispalyType:doubleLine表示双行，在新增编辑页面用，inline表示单行，在列表用  -->
<!--2、 1表示显示，0表示不显示 -->
<!--3、 1表示value为编号，0表示value为 名称-->
<!--4、 isRequired表示必填与否，true、false，用于新增、编辑的时候-->
<!--5、 category表示通过工厂车间选择班组类别-->
<!--6、 fcategory表示通过工厂选择班组类别-->
<!--7、 checkItem表示通过工厂，车间，班组类别选择考核项目-->
<!--8、 isColspan表示是否合并单元格-->
<input type="hidden" id="fcategory_${id_end}" value="${param.fcategory }">
<input type="hidden" id="fgroup_${id_end}" value="${param.fgroup }">
<input type="hidden" id="fagroup_${id_end}" value="${param.fagroup }">
<c:if test="${param.dispalyType eq 'doubleLine'}">
<tr>
</c:if>				
				<c:if test="${param.factory eq '1'}">
                <td <c:if test="${param.thClass eq '1'}">style="background: #F7F9FC;"</c:if>>
					工厂：
				</td>						
				<td>
				<select name="baseFactory" id="baseFactory_${id_end}" onchange="doAreaSelectBase('${id_end}');doFcategorySelectBase('${id_end}');doFgroupSelectBase('${id_end}');" <c:if test="${param.isRequired}">class="required"</c:if>>
						<option value="">请选择</option>
						<c:forEach items="${common_factoryList}" var="o">
						<option value="${o.factory }">${o.factory}</option>
						</c:forEach>
				</select>
				<script type="text/javascript">
					$("#baseFactory_${id_end}").val("${vo.baseFactory}");
				</script>
				</td>
				</c:if>
				
				<c:if test="${param.area eq '1' and param.factory eq '1'}">
				<td <c:if test="${param.thClass eq '1'}">style="background: #F7F9FC;"</c:if>>
					车间：
				</td>						
				<td>
				<select name="baseArea" id="baseArea_${id_end}" onchange="doCategorySelectBase('${id_end}');doFagroupSelectBase('${id_end}');" <c:if test="${param.isRequired}">class="required"</c:if>>
						<option value="">请选择</option>
						<c:forEach items="${common_areaList}" var="o">
						<option value="${o.area}">${o.area}</option>
						</c:forEach>
				</select>
				<script type="text/javascript">
					$("#baseArea_${id_end}").val("${vo.baseArea}");
				</script>
				</td>
				</c:if>
				
<c:if test="${param.dispalyType eq 'doubleLine'}">	
</tr><tr>
</c:if>			
				<c:if test="${param.category eq '1' and param.area eq '1' and param.factory eq '1'}">		
				<td <c:if test="${param.thClass eq '1'}">style="background: #F7F9FC;"</c:if>>
					班组类别：
				</td>						
				<td <c:if test="${param.isColspan eq '1'}">colspan="3"</c:if>>
				<select name="baseCategory" id="baseCategory_${id_end}" onchange="doCheckItemSelectBase('${id_end}');" <c:if test="${param.isRequired}">class="required"</c:if>>
						<option value="">请选择</option>
						<c:forEach items="${common_cateList}" var="o">
						<option value="${o.shiftGroupCategory }">${o.shiftGroupCategory}</option>
						</c:forEach>
				</select>
				<script type="text/javascript">
					$("#baseCategory_${id_end}").val("${vo.baseCategory}");
				</script>
				</td>
				</c:if>
				
				<c:if test="${param.fcategory eq '1' and param.factory eq '1'}">		
				<td <c:if test="${param.thClass eq '1'}">style="background: #F7F9FC;"</c:if>>
					班组类别：
				</td>						
				<td <c:if test="${param.isColspan eq '1'}">colspan="3"</c:if>>
				<select name="baseCategory" id="baseCategory_${id_end}" <c:if test="${param.isRequired}">class="required"</c:if>>
						<option value="">请选择</option>
						<c:forEach items="${common_fcateList}" var="o">
						<option value="${o.shiftGroupCategory }">${o.shiftGroupCategory}</option>
						</c:forEach>
				</select>
				<script type="text/javascript">
					$("#baseCategory_${id_end}").val("${vo.baseCategory}");
				</script>
				</td>
				</c:if>
				
				<c:if test="${param.checkItem eq '1' and param.factory eq '1' and param.area eq '1' and param.category eq '1'}">		
				<td <c:if test="${param.thClass eq '1'}">style="background: #F7F9FC;"</c:if>>
					考核项目：
				</td>						
				<td>
				<select name="baseCheckItem" id="baseCheckItem_${id_end}" <c:if test="${param.isRequired}">class="required"</c:if>>
						<option value="">请选择</option>
						<c:forEach items="${checkItemList}" var="o">
						<option value="${o.checkItem }">${o.checkItem}</option>
						</c:forEach>
				</select>
				<script type="text/javascript">
					$("#baseCheckItem_${id_end}").val("${vo.baseCheckItem}");
				</script>
				</td>
				</c:if>
							
				<c:if test="${param.fgroup eq '1'}">
				<td <c:if test="${param.thClass eq '1'}">style="background: #F7F9FC;"</c:if>>
					班组名称：
				</td>						
				<td <c:if test="${param.isColspan eq '1'}">colspan="3" </c:if>>
				<select name="baseGroup" id="baseGroup_${id_end}" <c:if test="${param.isRequired}">class="required"</c:if>>
						<option value="">请选择</option>
						<c:forEach items="${fgroupList}" var="o">
						<option value="${o.shiftGroup }">${o.shiftGroup}</option>
						</c:forEach>
				</select>
				<script type="text/javascript">
					$("#baseGroup_${id_end}").val("${vo.baseGroup}");
				</script>
				</td>
				</c:if>
				<c:if test="${param.fagroup eq '1'}">
				<td <c:if test="${param.thClass eq '1'}">style="background: #F7F9FC;" </c:if>>
					班组名称：
				</td>						
				<td <c:if test="${param.isColspan eq '1'}">colspan="3" </c:if>>
				<select name="baseGroup" id="baseGroup_${id_end}" <c:if test="${param.isRequired}">class="required"</c:if>>
						<option value="">请选择</option>
						<c:forEach items="${fagroupList}" var="o">
						<option value="${o.shiftGroup }">${o.shiftGroup}</option>
						</c:forEach>
				</select>
				<script type="text/javascript">
					$("#baseGroup_${id_end}").val("${vo.baseGroup}");
				</script>
				</td>
				</c:if>

<c:if test="${param.dispalyType eq 'doubleLine'}">	
</tr>
</c:if>
