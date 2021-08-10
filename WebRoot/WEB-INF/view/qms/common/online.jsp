<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>  
	<tr id="setdate">
		<td>工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;厂：</td>
		<td>
			<select name="factoryS" id="factoryS_${id_end}" style="width: 130px" onchange="loadtype()">
				<option value="">请选择</option>
				<c:forEach items="${factorylist}" var="list">
					<option value="${list}">${list}</option>
				</c:forEach>
			</select>
		</td>
		<td>机型类别：</td>
        <td>
			<select name="type" id="type_${id_end}" onchange="" style="width: 130px">
				<option value="">请选择</option>
				<c:forEach items="${listtype}" var="list">
					<option value="${list}">${list}</option>
				</c:forEach>
			</select>
  		</td> 	
  		<td>时间维度：</td>                       
        <td>
           <select id="dimension_${id_end}" onchange="" name="dimension" style="width: 130px">
				<option value="">请选择</option>
				<!-- <option value="1">日</option> -->
				<option value="2">周</option>
				<option value="3">月</option>
				<option value="4">年</option>
			</select>
        </td>
        <td>&nbsp;截&nbsp;止&nbsp;日&nbsp;期：</td>                       
        <td>
        	<input type="text" name="endtime" id="endtime_${id_end}" placeholder="请输入日期" readonly="readonly" onclick="loadtime();setclass()" size="20" value="${vo.endtime }"/>
        </td> 	
	</tr>
	<tr>
		<c:if test="${param.charNumber eq '0'}"><!-- 0显示 -->
		<td>排列图数量：</td>
		<td>
			<input type="text" name="charNumber" id="charNumber_${id_end}" placeholder="" value="${vo.charNumber }"/>				
		</td>	
		</c:if>
		<td>供&nbsp;&nbsp;应&nbsp;&nbsp;商：</td>
		<td>
			<input type="text" id="accountjson_${id_end}" name="numberstxt" hidden="hidden" placeholder="" value="${vo.numberstxt }"  readonly="readonly"/>	
			<input type="text" id="accountjson_${id_end}" name="numberstxtstr" placeholder="" value="${vo.numberstxtstr }"  readonly="readonly"/>			
			<a class="btnLook"  href="qms/common/supplier.do?idend=${id_end}&data=accountjson" target="dialog" height="600" width="1200" lookupGroup="accountjson" mask="true" id="" rel="" title="查找-供应商"></a>	
			<a class="btnClear" href="javascript:void(0);" onclick="clearSupplierAll()"  title="清空"></a>					
		</td>	
		<td>物料分类：</td>
		<td>
			<input type="text" id="classificationtxt_${id_end}" name="classificationtxt" placeholder="" value="${vo.classificationtxt }"  readonly="readonly"/>		
			<a class="btnLook"  href="qms/common/classification.do" target="dialog" height="600" width="1200" lookupGroup="partClassjson" mask="true" id="" rel="" title="查找-物料分类"></a>					
		</td>
		<td>物料级别：</td>
		<td>
			<select id="level_${id_end}" name="level" style="width: 130px">
				<option value="">请选择</option>
				<option value="A">A</option>
				<option value="B">B</option>
				<option value="C">C</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>物料编号：</td>
		<td>
			<input type="text" id="partnumbertxt_${id_end}" placeholder="" name="partnumbertxt" value="${vo.partnumbertxt }" readonly="readonly"/>				
			<a class="btnLook"  href="qms/common/number.do?idend=${id_end}" target="dialog" height="600" width="1200" lookupGroup="partjson" mask="true" id="" rel="" title="查找-物料编号"></a>					
		</td>
		<td>是否带版本：</td>
		<td>
			<select id="isEdition_${id_end}" name="isEdition" onchange="" style="width: 130px">
				<option value="1">是</option>
				<option value="0">否</option>
			</select>						
		</td>
		<td>产品成熟度：</td>
		<td>
			<select id="maturity_${id_end}" name="maturity" onchange="" style="width: 130px">
				<option value="">全部</option>
				<option value="新品">新品</option>
				<option value="老品">老品</option>
				<option value="小批量">小批量</option>
			</select>						
		</td>
		<c:if test="${param.unit eq '0'}"><!-- 0显示 -->
		<td>统计类型：</td>
		<td>
			<div><label style="width: 60px"><input type="radio" name="radio" id="" checked="checked"/>关键件</label>
			<label><input type="radio" name="radio" id=""/>非关键件</label></div>
		</td>
		</c:if>
		<!-- <td>供应商生产批次：</td>
		<td>
			<input style="float:none;" type="text" name="" id="" size="5" value=""/>至 <input style="float:none;" type="text" name="" id="" size="5" value=""/>
		</td> -->
		<c:if test="${param.countType eq '0'}"><!-- 0显示 -->
		<td>统计类型：</td>
		<td colspan="2">
			<div><label style="width: 100px"><input type="radio" name="counttype" id="radio_${id_end}" checked="checked" value="one"/>不良批次数</label>
			<label><input type="radio" name="counttype" id="radio2_${id_end}" value="two"/>不良数/率</label></div>
		</td>
		</c:if>
	</tr>
	<script type="text/javascript">
		$(function(){//数据赋值
			var factoryS='${vo.factoryS}';
			var type='${vo.type}';
			var dimension='${vo.dimension}';
			var level='${vo.level}';
			var isEdition='${vo.isEdition}';
			var maturity='${vo.maturity}';
			$("#factoryS_${id_end}").val(factoryS);
			$("#type_${id_end}").val(type);
			$("#dimension_${id_end}").val(dimension);
			$("#level_${id_end}").val(level);
			$("#isEdition_${id_end}").val(isEdition);
			$("#maturity_${id_end}").val(maturity);
		});
		function loadtype(){
			$("#type_${id_end} option",navTab.getCurrentPanel()).not(":first").remove();
			var url = "<c:url value='base/quality/findtype.do' />";
			var factory=$("#factoryS_${id_end}").val();
			$.post(url, {factory:factory}, function(data) {
				if (data.result == 0) {
					var str=data.listtype;
					$(str).each(function(i,v){
						var htmltext="<option value='"+v+"'>"+v+"</option>";
						$("#type_${id_end}").append(htmltext);	
					 });
				} else {
					alertMsg.error("查询出错，请联系管理员");
					return ;
				}
			});
		} 
		function loadtime(){
			var val=$("#dimension_${id_end}").val();
			var mat='YYYY-MM';
			var mrod=true;
			if(val=="1"){
				mat='YYYY-MM-DD';
				mrod=false;
			}
			if(val=="2"){
				mat='YYYY-MM-DD';
				mrod=false;
			}
			if(val=="3"){
				mat='YYYY-MM';
				mrod=true;
			}
			if(val=="4"){
				mat='YYYY';
				mrod=true;
			}
			laydate({
				elem: '#endtime_${id_end}',
				max:laydate.now(),
				isym:mrod,
				format: mat, // 日期格式
				festival: false,    // 显示节日
				choose: function(datas){
				} // 选择日期完毕的回调
			});
		}
		function setclass(){
		    setTimeout('setweek()',1);
		}
		/*$("#laydate_MM a,#laydate_MM div span,#laydate_YY a,#laydate_YY li").on("click",function(){
			setclass();
		});*/
		function setweek(){
			var val=$("#dimension_${id_end}").val();
			if(val=="2"){//laydate_void//禁用样式
				$("#laydate_table tbody tr td").each(function(i,v){
					var result=i%7;
					if(result!=0){
						$(v).addClass("laydate_void");
					}
				});
			}
		}
		function clearSupplierAll(){
	    	$("#numberstxt_${id_end}", navTab.getCurrentPanel()).val("");
	    	$("#numberstxtstr_${id_end}", navTab.getCurrentPanel()).val("");
	    }
	</script>