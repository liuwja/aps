<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<style type="text/css" media="screen">
	${demo.css}
	
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
<script type="text/javascript">
    //加载时间
    $("#fdselectDate",navTab.getCurrentPanel()).change(function(){
    	var dateT= $("#fddateTd",navTab.getCurrentPanel());
    	//alert(dateT.onclick);
    	dateT.empty();
    	var selectDate = $("#fdselectDate",navTab.getCurrentPanel()).val();
    	if(selectDate =='天'){
    		dateT.append("<input type='text' size='8' id='fddateT' name='dateT' placeholder='请输入日期' onclick='laydate()' readonly='true'/>");
    	}else if(selectDate =='周'){
    		dateT.append("<input type='text'  size='8' id='fddateT'  name='dateT' placeholder='请输入日期' onclick='laydate({week:true});' readonly='true'/>");
    	}else if(selectDate =='月'){
    		dateT.append("<input type='text'  size='8' id='fddateT' name='dateT' placeholder='请输入日期' onclick='laydate({isym:true, format: \"YYYY-MM\"})' readonly='true'/>");
    	}else if(selectDate =='年'){
    		dateT.append("<input type='text'  size='8' id='fddateT'  name='dateT' placeholder='请输入日期' onclick='laydate({isym:true, format: \"YYYY\"})' readonly='true'/>");
    	}
    });
    var feedselNameObj =[];
    var feedselValueObj =[];
    //加载图形
    function loadFeedDetailChart(type){
    	var factory = $("#factory",navTab.getCurrentPanel()).val();
    	var productType = $("#productType",navTab.getCurrentPanel()).val();
    	var supplierCode = $("#fdsupplierLookup_supplierCode",navTab.getCurrentPanel()).val();
    	var supplier = $("#fdsupplierLookup_supplierName",navTab.getCurrentPanel()).val();
    	var partType = $("#partType",navTab.getCurrentPanel()).val();
    	var partName = $("#fdpartlookup_partName",navTab.getCurrentPanel()).val();
    	var partNumber = $("#fdpartlookup_partCode",navTab.getCurrentPanel()).val();
    	var partClass = $("#partClass",navTab.getCurrentPanel()).val();
    	var partVersion = $("input[name='partVersion']:checked",navTab.getCurrentPanel()).val();
    	var dateType = $("#fdselectDate",navTab.getCurrentPanel()).val();
    	var dateT = $("#fddateT",navTab.getCurrentPanel()).val();
    	var isNew = $("select[name='isNew']",navTab.getCurrentPanel()).val();
    	var lotStartTime = $("#lotStartTime",navTab.getCurrentPanel()).val();
    	var lotEndTime = $("#lotEndTime",navTab.getCurrentPanel()).val();
    	var columnNum = $("#columnNum",navTab.getCurrentPanel()).val();
    	var analysisType = $("input[name='analysisType']:checked",navTab.getCurrentPanel()).val();
    	var sordType = $("#sordType",navTab.getCurrentPanel()).val();
    	var defectType ="";
    	if(dateType ==""){
    		alert("请选择时间维度！");
    		return ;
    	}
    	if(dateType !=""){
    		if(dateT == ''){
        		alert("请选择时间！");
        		return ;
        	}
    	}
    	if(columnNum ==''){
    		alert("请输入排列图数量！");
    		return ;
    	}
    	feedselNameObj = ["type","factory","productType","supplier","partType","partClass","partName","partVersion","dateType","dateT","isNew","lotStartTime","lotEndTime","columnNum","analysisType","defectType","partNumber","supplierCode"];
    	feedselValueObj = [type,factory,productType,supplier,partType,partClass,partName,partVersion,dateType,dateT,isNew,lotStartTime,lotEndTime,columnNum,analysisType,defectType,partNumber,supplierCode];
    	
    	var url = "<c:url value='quality/testInstance/loadFeedDetail.do' />";
    	$.post(url,{type:type,factory:factory,productType:productType,supplier:supplier,partType:partType,partClass:partClass,partName:partName,partVersion:partVersion,dateType:dateType,dateT:dateT,isNew:isNew,lotStartTime:lotStartTime,lotEndTime:lotEndTime,columnNum:columnNum,analysisType:analysisType,defectType:defectType,partNumber:partNumber,supplierCode:supplierCode,sordType:sordType},function(data){
                jinitHeight("");
            	if(data.result==0){
        			loadFeedDetail(data,type);
        			var buttonName;
        			if(type==1|| type==2|| type==3|| type==8){
        				buttonName ="排列图";
        			}else if(type==4|| type==5){
        				buttonName ="趋势图";
        			}else{
        				buttonName ="对比图";
        			}
        			$("<div style='z-index:900;position: absolute;top:5px;left:85%;'><button id='mytest' type='button' style='background: transparent;color: #A0A0A0;border: 0px;cursor:pointer;' onclick='getDataSourceByMenuName(&quot;物料质量统计分析&quot;, &quot;进料部分&quot;, &quot;进料质量明细&quot;, &quot;"+buttonName+"&quot;);'>数据来源</button></div>").appendTo($("#feedDetail",navTab.getCurrentPanel()));
            	}
        		if(data.result==-1){
        			alert("查询出错，请联系管理员！");
        		}
    	});
 
    }
   //清空物料选择
    function clearFdPart(){
    	$("#fdpartlookup_partName", navTab.getCurrentPanel()).val("");
    	$("#fdpartlookup_data", navTab.getCurrentPanel()).val("");
    	$("#fdpartlookup_partCode", navTab.getCurrentPanel()).val("");
    }
   //清空供应商选择
    function clearFdSupplier(){
    	$("#fdsupplierLookup_supplierName", navTab.getCurrentPanel()).val("");
    	$("#fdsupplierLookup_data", navTab.getCurrentPanel()).val("");
    	$("#fdsupplierLookup_supplierCode", navTab.getCurrentPanel()).val("");
    }
   //获取机型类别
    function getProductType(obj){
    	var productType = $("#productType",navTab.getCurrentPanel());
    	productType.empty();
    	productType.append("<option value=''>请选择</option>");
    	var factroy = $(obj).val();
   	    <c:forEach items="${productType }" var="o">
   	       var key = '${o.key}';
	       if(factroy == key){
	    	   <c:forEach items="${o.value}" var="pro">
	    	      productType.append("<option value='${pro.productType}'>${pro.productType}</option>");
	    	   </c:forEach>
	       }
	    </c:forEach>
    }
</script>

	<form id="testInstanceForm" onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div id="searchContentDiv" class="searchBar dropdownSearchBar" >
		<table class="searchContent">
			<tr>
			    <td>
					工厂：
				</td>
				<td>
					<select id="factory" name="factory" onchange="getProductType(this)">
					   <option value="">请选择</option>
					   <c:forEach items="${productType }" var="o">
					     <option value="${o.key }">${o.key }</option>
					   </c:forEach>
					</select>
				</td>
				<td>
					机型：
				</td>
				<td>
					<select id="productType" name="productType" >
					   <option value="">请选择</option>
					</select>
				</td>	
				<td>
					供应商：
				</td>
                <td>
              <!--    <div id="supplierList" class="dropdownlist"></div> -->   
                    <input style="float: left;" type="text" id="fdsupplierLookup_supplierName" name="supplier" value="${param.supplier}" size="10"/>
                    <input type="hidden" id="fdsupplierLookup_data" value="">
                    <input type="hidden" id="fdsupplierLookup_supplierCode" value="">
                    <a id="btn"  class="btnLook btn" href="quality/testInstance/supplierSelect.do?data=fdsupplierLookup" width="1150" height="550" lookupGroup="fdsupplierLookup">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearFdSupplier()"  title="清空"></a> 
                </td>  
		  </tr>
		  <tr>		
                <td>
					物料类别：
				</td>
                <td >
                   <div id="partTypeList" class="dropdownlist"></div>   
             <!--   <input style="float: left;" type="text" id="fdpartTypeLookup_partType" name="partType" value="${param.partType}" size="10"/>
                    <input type="hidden" id="fdpartTypeLookup_data" value="">
                    <a id="btn"  class="btnLook btn" href="quality/testInstance/partTypeSelect.do?data=fdpartTypeLookup" width="900" height="550" lookupGroup="fdpartTypeLookup">物料类别选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearFdPartType()"  title="清空"></a>   -->
                </td> 
                <td>
					物料级别：
				</td>
                <td>
                     <select id="partClass" name="partClass">
					   <option value="">请选择</option>
					   <option value="A">A</option>
					   <option value="B">B</option>
					   <option value="C">C</option>
					</select>
                </td> 
                 <td>
					物料号：
				</td>
                <td>
                    <input style="float: left;" type="text" id="fdpartlookup_partName" name="partName" value="${param.partName}" size="10"/>
                    <input type="hidden" id="fdpartlookup_data" value="">
                    <input type="hidden" id="fdpartlookup_partCode" name="partNumber"  value="">
                    <a id="btn"  class="btnLook btn" href="quality/testInstance/partSelect.do?data=fdpartlookup" width=950 height=500 lookupGroup="fdpartlookup">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearFdPart()"  title="清空"></a> 
                </td> 
         </tr>	
		 <tr>	
                 <td>
					是否带版本：
				</td>
                <td>
                    <label><input id="partVersion" name="partVersion" type="radio" value="是">是</label>
                    <label><input id="partVersion" name="partVersion" type="radio" value="否 " checked="checked">否</label>
                </td> 	
                
                <td>
					产品成熟度：
				</td>
                <td >
                  <select id="isNew" name="isNew">
                      <option value="">全部</option>
                      <option value="老品">老品</option>
                      <option value="新品">新品</option>
                      <option value="little">小批量</option>
                      <option value="large">非小批量</option>
                  </select>
                </td>  			
		        <td>
					时间维度：
				</td>
                <td >
                    <select id="fdselectDate" >
                       <option value=''>请选择</option>
                       <option value="天">天</option>
                       <option value="周">周</option>
                       <option value="月">月</option>
                       <option value="年">年</option>
                     </select>
                     <script type="text/javascript">
                        $("#fdselectDate",navTab.getCurrentPanel()).val("月");
                     </script>
                     <span id="fddateTd"><input type='text'  size='8' id='fddateT' name='dateT' placeholder='请输入日期' onclick='laydate({isym:true, format: "YYYY-MM"})' readonly='true'/></span>
                </td>
		 </tr>	
		 <tr>	
               
                <td>
					供应商批次：
				</td>
                <td>
                    <input type='text' size='8' id='lotStartTime' name='lotStartTime' placeholder='请输入日期' onclick='laydate()' readonly='true'/> 至
                    <input type='text' size='8' id='lotEndTime' name='lotEndTime' placeholder='请输入日期' onclick='laydate()' readonly='true'/>
                </td>
                <td>
					排列图数量：
				</td>
                <td >
                   <!-- <select id="columnNum" name="columnNum">
                       <option value="5">5</option>
                       <option value="10">10</option>
                       <option value="15">15</option>
                       <option value="20">20</option>
                     </select> -->
                     <input id="columnNum" name="columnNum" type="text" size='6' value="10" class="required number">
                </td>	
                <td>
                                  统计方式：
                </td>
                <td>                  
                 <label><input type="radio" name="analysisType" value="0" checked="checked">不良批次数/率</label>
                 <label><input type="radio" name="analysisType" value="1">不良数/率</label>
				</td>	
				<td>
				    <select id="sordType">
                          <option value="不良批/数">不良批/数-排序</option>
                          <option value="不良率">不良率-排序</option>
                     </select>
				</td>
			</tr>
			<tr>
			   <td colspan="6">
			    <div class="buttonActive"><div class="buttonContent"><button type="button" style="font-size: 8px;" onclick="loadFeedDetailChart('1');">供应商排列图</button></div></div>
			    <div class="buttonActive"><div class="buttonContent"><button type="button" style="font-size: 8px;" onclick="loadFeedDetailChart('2');">零部件排列图</button></div></div>
			    <div class="buttonActive"><div class="buttonContent"><button type="button" style="font-size: 8px;" onclick="loadFeedDetailChart('3');">不良现象排列图</button></div></div>
			    <div class="buttonActive"><div class="buttonContent"><button type="button" style="font-size: 8px;" onclick="loadFeedDetailChart('4');">供应商趋势图</button></div></div>
			    <div class="buttonActive"><div class="buttonContent"><button type="button" style="font-size: 8px;" onclick="loadFeedDetailChart('5');">零部件趋势图</button></div></div>
			    <div class="buttonActive"><div class="buttonContent"><button type="button" style="font-size: 8px;" onclick="loadFeedDetailChart('6');">供应商对比图</button></div></div>
			    <div class="buttonActive"><div class="buttonContent"><button type="button" style="font-size: 8px;" onclick="loadFeedDetailChart('7');">零部件对比图</button></div></div>
			    <div class="buttonActive"><div class="buttonContent"><button type="button" style="font-size: 8px;" onclick="loadFeedDetailChart('8');">原料退料排列图</button></div></div>
			   </td> 
			</tr>
		</table>
	</div>
	</form>

<div   class="pageContent"   style="overflow: auto; ">
	<div id="feedDetail" class="singleChartDiv" ></div>
</div>


<script type="text/javascript">
function loadFeedDetail(resulMap,type){
	var analysisT= $("input[name='analysisType']:checked",navTab.getCurrentPanel()).val(); 
	//设置导出按钮
	var myTool = {
        show : true,
        title : '导出',
        icon : 'M4.7,22.9L29.3,45.5L54.7,23.4M4.6,43.6L4.6,58L53.8,58L53.8,43.6M29.2,45.1L29.2,0',
        onclick : function (ecModel, api){
     	        var model = this.model;
     	        var title = model.get('name') || ecModel.get('title.0.text') || 'echarts';
     	        var $a = document.createElement('a');
     	        var _type = model.get('type', true) || 'png';
     	        $a.download = title + '.' + _type;
     	        $a.target = '_blank';
     	        var url = api.getConnectedDataURL({
     	            type: _type,
     	            backgroundColor: model.get('backgroundColor', true)
     	                || ecModel.get('backgroundColor') || '#fff',
     	            excludeComponents: model.get('excludeComponents'),
     	            pixelRatio: model.get('pixelRatio')
     	        });
     	        $a.href = url;
     	        // Chrome and Firefox
     	         if (typeof MouseEvent === 'function') {
     	            var evt = new MouseEvent('click', {
     	                view: window,
     	                bubbles: true,
     	                cancelable: false
     	            });
     	            $a.dispatchEvent(evt);
     	        } 
     	        // IE
     	         else {
     	            var lang = model.get('lang');
     	            var html = ''
     	                + '<body style="margin:0;">'
     	                + '<img src="' + url + '" style="max-width:100%;" title="' + ((lang && lang[0]) || '') + '" />'
     	                + '</body>';
     	            var tab = window.open();
     	            tab.document.write(html);
     	        } 
     	        if(type == 8){
     	        	return false;
     	        }
     	    	var url = "<c:url value='quality/testInstance/dowmLoadFeedDetail.do' />";
     	    	var form = document.createElement("form");
     	    	form.action = url;
     	    	form.method = "post";
     	    	form.target = '_blank';
     	    	for(var i in feedselNameObj){
     	    		var sinput = document.createElement("input");
     	    		sinput.name = feedselNameObj[i];
     	    		sinput.value = feedselValueObj[i];
     	    		form.appendChild(sinput);
     	    	}
     	    	document.body.appendChild(form);
     	    	form.submit();
        } 
    }
	 var colorList = [
			   			  '#3399FF','#C1232B','#333366','#333366','#C1232B',
			   			   '#333366','#C1232B','#333366','#C1232B','#333366',
			   			   '#C1232B','#333366','#C1232B','#333366','#C1232B',
			   			   '#333366','#C1232B','#333366','#C1232B','#333366'
			   			];
		var feedechart;
		var feedoption;
		var comList = resulMap.comList;
	    var	totalList = resulMap.totalList;
	    var codeMap = resulMap.codeMap;

		feedoption = resulMap.option;
	/*        var tooltip = {
		        trigger: 'axis',
		        formatter: function(params) {
		           // console.warn(params[0].dataIndex+1);
		            //console.warn((params[0].dataIndex+1)%2);
		            var sname ;
		            var svalue ;
		            if((params[0].dataIndex+1)%2===1){
		                if(params[1].value !=='-'){
		                	sname = params[0].name +'本月';
		                    svalue = params[1].seriesName + ' : ' + params[1].value+'%' ;
		                }else{
		                	sname = params[0].name +'本月';
		                    svalue = params[1].seriesName + ' : ' + params[2].value+'%' ;
		                }
		                
		            }
		             if((params[0].dataIndex+1)%2===0){
		                if(params[1].value !=='-'){
		                	sname = params[0].name +'上月';
		                    svalue = params[2].seriesName + ' : ' + params[1].value+'%' ;
		                }else{
		                	sname = params[0].name +'上月';
		                    svalue = params[2].seriesName + ' : ' + params[2].value+'%' ;
		                }
		            }
		            return sname +' : ' + params[0].value + '<br/>'
		                   + svalue ;
		        },
		    }; */
	  /*     var itemStyle = {
	       	normal: { 
	       		color: function(params) {
	       			return colorList[(params.dataIndex)%2] ;
	       			}
	       			} 
	       }; */
	     /*  if(type==6 || type==7){
	    	  feedoption.tooltip = tooltip; 
	    	  feedoption.series[0].itemStyle = itemStyle;
	      }else{
	    	  feedoption.color = colorList;
	      } */
	      var tooltip_r = {
	    		trigger: 'axis',
	    		formatter: function(params) {
			           // console.warn(params[0].dataIndex+1);
			            var sname = params[0].name ;
			            var svalue ="";
			            var lab = "";
			            if(analysisT ==0){
			            	lab ="(批)";
			            }else{
			            	lab ="(个)";
			            }
			            for(var i in params){
			            	  var pvalue = params[i].value;
			            	  if(pvalue==null){
			            		  pvalue = 0;
			            	  }
			            	  if(i == 0){
			            		  svalue += params[i].seriesName + ' : ' + pvalue+lab + '<br/>';
			            		  if(sname != '其他'){
			            			  var tovalue = totalList[sname];
			            			  if(tovalue == null){
			            				  tovalue = 0;
			            			  }
			            		      svalue += "到货总数：" +tovalue + lab +'<br/>';
			            		      if(codeMap[sname] != null){
			            		    	  sname = codeMap[sname] + "-"+ sname;
			            		      }
			            		  }
			            	  }else{
		                          svalue += params[i].seriesName + ' : ' + pvalue+'%' + '<br/>';
			            	  }
			            	      
			            	}
			        
			            return sname +' <br/>' + svalue ;
			        },
	      }
	      if(type == 1 || type==2 || type==3 || type ==4 || type ==5 ){
	    	  feedoption.tooltip = tooltip_r; 
	      }
	      feedoption.color = colorList;
	      feedoption.toolbox.feature.myTool=myTool;
	      
	      feedechart = echarts.init(document.getElementById('feedDetail'));
	      feedechart.setOption(feedoption);
	  	
	      feedechart.on('click', function (e) {
				var xAxisData = resulMap.option.xAxis[0].data[e.dataIndex];
				var factory = $("#factory",navTab.getCurrentPanel()).val();
		    	var productType = $("#productType",navTab.getCurrentPanel()).val();
		    	var supplierCode = $("#fdsupplierLookup_supplierCode",navTab.getCurrentPanel()).val();
		    	var supplier = $("#fdsupplierLookup_supplierName",navTab.getCurrentPanel()).val();
		    	var partType = $("#partType",navTab.getCurrentPanel()).val();
		    	var partNumber = $("#fdpartlookup_partCode",navTab.getCurrentPanel()).val();
		    	var partName = $("#fdpartlookup_partName",navTab.getCurrentPanel()).val();
		    	var partClass = $("#partClass",navTab.getCurrentPanel()).val();
		    	var partVersion = $("input[name='partVersion']:checked",navTab.getCurrentPanel()).val();
		    	var dateType = $("#fdselectDate",navTab.getCurrentPanel()).val();
		    	var dateT = $("#fddateT",navTab.getCurrentPanel()).val();
		    	var isNew = $("select[name='isNew']",navTab.getCurrentPanel()).val();
		     	var lotStartTime = $("#lotStartTime",navTab.getCurrentPanel()).val();
		    	var lotEndTime = $("#lotEndTime",navTab.getCurrentPanel()).val();
		    	var columnNum = $("#columnNum",navTab.getCurrentPanel()).val();
		    	var analysisType = $("input[name='analysisType']:checked",navTab.getCurrentPanel()).val(); 
		    	var defectType = "";
		    	var dayTime ="";
				if(type==1 || type==8 || type ==6){
					supplier = xAxisData;
					supplierCode = comList[e.dataIndex];
				}else if(type==2 || type == 7){
					partName = xAxisData;
					partNumber = comList[e.dataIndex];
				}else if(type==3){
					defectType = xAxisData;
				}else if(type==4||type==5){
					if(resulMap.dateList == null || resulMap.dateList==''){
					    dateT = e.name;
					}else{
						var dateList = resulMap.dateList;
						dateT = dateList[e.dataIndex];
					}
					//dateT = xAxisData;
				}
  			   /* else if(type==6){
					if(e.dataIndex%2 ==0){
						supplier = xAxisData;
						supplierCode = comList[e.dataIndex];
						dayTime = "本";
					}else if(e.dataIndex%2 ==1){
						supplier = xAxisData;
						supplierCode = comList[e.dataIndex];
						dayTime = "上";
					}
				}else if(type==7){
					if(e.dataIndex%2 ==0){
						partName = xAxisData;
						partNumber = comList[e.dataIndex];
						dayTime = "本";
					}else if(e.dataIndex%2 ==1){
						partName = xAxisData;
						partNumber = comList[e.dataIndex];
						dayTime = "上";
					}
				} */
				if(type==8){
					 navTab.openTab("erpPartReturnDetailTab", "quality/testInstance/erpPartReturnDetail.do", {title:"ERP原料退料明细界面",fresh:true,data:{factory:factory,productType:productType,supplier:supplier,partType:partType,partClass:partClass,partName:partName,partVersion:partVersion,dateType:dateType,dateT:dateT,isNew:isNew,lotStartTime:lotStartTime,lotEndTime:lotEndTime,columnNum:columnNum,analysisType:analysisType,defectType:defectType,dayTime:dayTime,supplierCode:supplierCode}});
				}else{
				   // navTab.openTab("testInstentDetailTab", "quality/testInstance/testInstentDetail.do", {title:"进料明细数据报表",fresh:true,data:{factory:factory,productType:productType,supplier:supplier,partType:partType,partClass:partClass,partName:partName,partVersion:partVersion,dateType:dateType,dateT:dateT,isNew:isNew,lotStartTime:lotStartTime,lotEndTime:lotEndTime,columnNum:columnNum,analysisType:analysisType,defectType:defectType,dayTime:dayTime,supplierCode:supplierCode,partNumber:partNumber,distinction:1}});
				  //  navTab.openTab("testInstentDetailTab", "quality/testInstance/testInstentDetail.do", {title:"进料明细数据报表",fresh:true,data:{dateType:dateType,dateT:dateT,defectType:defectType,supplierCode:supplierCode,partNumber:partNumber,distinction:1}});
				      navTab.openTab("testInstentDetailTab", "quality/testInstance/testInstentDetail.do", {title:"进料明细数据报表",fresh:true,data:{type:type,factory:factory,productType:productType,partType:partType,partClass:partClass,partVersion:partVersion,dateType:dateType,dateT:dateT,isNew:isNew,lotStartTime:lotStartTime,lotEndTime:lotEndTime,columnNum:columnNum,analysisType:analysisType,defectType:defectType,dayTime:dayTime,supplierCode:supplierCode,partNumber:partNumber,distinction:1}})
				}
	      	});
}

$(function(){	
	
	$('#partTypeList', navTab.getCurrentPanel()).dropdownlist({
	    id:'partType',
	    columns:2,
	    selectedtext:'',
	    listboxwidth:300,//下拉框宽
	    maxchecked:100,
	    checkbox:true,
	    listboxmaxheight:400,
	    width:120,
	    requiredvalue:[],
	    selected:[],
	    data:${partMap},//数据，格式：{value:name}
	    onchange:function(text,value){
	    }
	});						
 });
							
							

</script>
