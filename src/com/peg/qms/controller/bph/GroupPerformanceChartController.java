package com.peg.qms.controller.bph;


import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.peg.beans.bph.PrimaryKeySelectBean;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.echarts.EChartObj;
import com.peg.echarts.EChartsType;
import com.peg.mes.highcharts.ChartObj;
import com.peg.model.CommonSelectedBox;
import com.peg.model.CommonVo;
import com.peg.model.EntityClass;
import com.peg.model.ShiftGroup;
import com.peg.model.bph.BphCommonVo;
import com.peg.model.bph.CheckIndex;
import com.peg.model.bph.Contrast;
import com.peg.model.bph.Group;
import com.peg.model.bph.GroupCategory;
import com.peg.model.bph.Index;
import com.peg.model.bph.IndexScroe;
import com.peg.model.bph.Item;
import com.peg.model.bph.ItemScore;
import com.peg.model.bph.MonthAssessment;
import com.peg.qms.controller.BaseController;
import com.peg.service.CommonSelectedBoxService;
import com.peg.service.ShiftGroupServiceI;
import com.peg.service.bph.BphCommonServiceI;
import com.peg.service.bph.CheckIndexServiceI;
import com.peg.service.bph.CheckItemServiceI;
import com.peg.service.bph.GroupCategorySerivceI;
import com.peg.service.bph.GroupServiceI;
import com.peg.service.bph.IndexServiceI;
import com.peg.service.bph.MonthlyAssessmentServiceI;
import com.peg.web.util.DateEditor;
import com.peg.web.util.MathUtil;
import com.peg.web.util.WebUtil;
/**
 * 班组绩效查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping("groupPerformanceChart")
public class GroupPerformanceChartController extends BaseController{
	//url
	static final String ASSEMBLEPRODUCE_URL ="base/commonselect/assemblyList.do";  //组装退次
	static final String FORMERFQCCHECH_URL = "base/commonselect/formerProcessList.do"; //FQC巡检
	static final String BATCHDEFECT_URL ="base/commonselect/batchDefectList.do";       //过程批量
	static final String STAMPINGDAYLY_URL ="base/commonselect/stampingDaliyList.do";   //冲压质量
	static final String PROCESSAUDIT_URL = "base/commonselect/processAuditList.do";    //过程审核
	static final String QUALITYINP_URL = "base/commonselect/qualityImpList.do";        //质量改善
	static final String BOXDEFECT_URL = "base/commonselect/boxDefectList.do";          //市场开箱
	static final String PAINTINGDAYLY_URL = "base/commonselect/paintingDailyList.do";  //喷涂质量
	static final String IPQCINSPECTS_URL = "base/commonselect/ipqcInspects.do" ;       //IPQC巡检
	static final String OQCCHECK_URL ="base/commonselect/oqcCheck.do";                 //OQC抽检
	static final String ASSEMBLYREPARIED_RUL ="base/commonselect/assemblyRepaired.do"; //组装维修
	static final String PAINTINGPRODUCE_RUL ="base/commonselect/paintingProductReturn.do"; //喷涂生产退次
	static final String FINISHINGDAILY_RUL ="base/commonselect/finishingDaliyList.do"; //精加工直通率
	
	//指标编号
	static final String ASSEMBLEPRODUCE_INDEX = "A1_B1_D1_C5_E1_H3";
	static final String FORMERFQCCHECH_INDEX = "E2";
	static final String BATCHDEFECT_INDEX = "A3_B2_D3_E3_E6_F1_H4_I4" ;
	static final String STAMPINGDAYLY_INDEX = "A4_E4" ;
	static final String PROCESSAUDIT_INDEX = "A7_A8_A9_A10_A11_B7_B8_B9_B10_B11_C7_C8_C9_C10_C11_D7_D8_D9_D10_D11_E7_E8_E9_E10_E11_F3_F4_F5_G3_G4_G5_H5_H6_H7_H8_H9_I5_I6_I7_I8_I9";
	static final String QUALITYINP_INDEX = "A12_B12_C12_D12_E12_F6_G6_H10_I10";
	static final String BOXDEFECT_INDEX = "C2_C3_G1_G2_H2_I2";
	static final String PAINTINGDAYLY_INDEX = "B4";
	static final String IPQCINSPECTS_INDEX = "A5_B3_B6_A6_D5_D6_E5";
	static final String OQCCHECK_INDEX = "C1_F2_H1_I1";
	static final String ASSEMBLYREPARIED_INDEX = "C4_I3";
	static final String PAINTINGPRODUCE_INDEX = "A2_D2";
	static final String FINISHINGDAILY_INDEX = "D4";
	
	@Autowired
	private BphCommonServiceI commonService;
	
	@Autowired
	private ShiftGroupServiceI shiftGroupService;
	
	@Autowired
	private CheckIndexServiceI checkIndexService;
	
	@Autowired
	private CheckItemServiceI checkItemService;
	
	@Autowired
	private CommonSelectedBoxService commonSelectedBoxService;
	
	@Autowired
	private MonthlyAssessmentServiceI monthlyAssessmentService;
	
	@Autowired
	private GroupServiceI groupService;
	
	@Autowired 
	private IndexServiceI indexService;
	
	@Autowired 
	private GroupCategorySerivceI groupCategorySerivce;

	/**
	 * 班组绩效排名统计单月
	 * @param model
	 * @return
	 */
	@RequestMapping("/singlePerformanceChar")
	private String singleChar(Model model,CommonVo cvo){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,-1);
		cvo.setQueryMonth( DateFormatUtils.format(cal.getTime(), "yyyy-MM"));
		cvo.setBaseFactory("电器一厂");
		LoadFAPG(model, cvo);
		List<ShiftGroup> list = shiftGroupService.getName();
		Map<String, String> partMap = new LinkedHashMap<String, String>();
		
			for (int i = 0; i < list.size(); i++) {
				partMap.put(list.get(i).getName(), list.get(i).getName());
			}
		
		JSONObject partJsonObject = JSONObject.fromObject( partMap );
		model.addAttribute("jsonParts", partJsonObject);
		model.addAttribute("vo",cvo );
		model.addAttribute("firstPress", "firstPress");
		return "qms/bph/groupPerformance/singleChart";
	}
	
	/**
	 * 班组绩效排名统计期间
	 * @param model
	 * @return
	 */
	@RequestMapping("/periodPerformanceChar")
	private String periodChar(Model model,CommonVo cvo){
		cvo.setStartTime( getPriviousMonth());
		cvo.setEndTime(getPriviousMonth());
		LoadFAPG(model, cvo);
		List<ShiftGroup> list = shiftGroupService.getName();
		Map<String, String> partMap = new LinkedHashMap<String, String>();
		
			for (int i = 0; i < list.size(); i++) {
				partMap.put(list.get(i).getName(), list.get(i).getName());
			}
		
		JSONObject partJsonObject = JSONObject.fromObject( partMap );
		model.addAttribute("jsonParts", partJsonObject);
		model.addAttribute("vo", cvo);
		return "qms/bph/groupPerformance/periodChart";
	}
	
	/**
	 * 班组绩效报表查询-排名
	 * @param model
	 * @return
	 */	
	@RequestMapping("/performanceSelectList")
	private String performanceSelectList(Model model){
		List<ShiftGroup> list = shiftGroupService.getName();		
		model.addAttribute("vo", list);	
		return "qms/bph/groupPerformance/performanceSelectList";
	}
	
	@RequestMapping("/performanceSelectListDo")
	private String performanceSelectListDo(Model model,CommonVo vo,PageParameter page){
		List<ShiftGroup> list = shiftGroupService.getName();
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("shiftGroupTxt", vo.getShiftGroupTxt());
		bs.put("factory", vo.getFactory());
		bs.put("startTime", vo.getStartTime());
		bs.put("endTime", vo.getEndTime());
		List<BphCommonVo> itemList = commonService.getShiftGroupPerformanceList(bs);
		page.setTotalCount(itemList.size());
		page.setTotalPage(1);
		model.addAttribute("vo", list);	
		model.addAttribute("list",itemList);
		model.addAttribute("page", page);
		return "qms/bph/groupPerformance/performanceSelectList";
	}
	/**
	 * 关键指标日考核值
	 * @param model
	 * @return
	 */
	@RequestMapping("/primaryKeySelectList")
	private String primaryKeySelectList(Model model,IndexScroe vo){
		LoadFAPG(model, vo);
		vo.setStartTime(DateEditor.formatDate(new Date(), "yyyy-MM-dd"));
		vo.setEndTime(DateEditor.formatDate(new Date(), "yyyy-MM-dd"));
		model.addAttribute("vo", vo);
		return "qms/bph/groupPerformance/primaryKeySelectList";
	}
	
	/**
	 * 班组绩效对比(展示)
	 * @param model
	 * @return
	 */
	@RequestMapping("/achievementscontrast")
	private String achievementscontrastList(Model model,IndexScroe vo,String grouplistselect,
			String itenlistselect,String indexlistselect,String monthly){
		LoadFAPG(model, vo);
		return "qms/bph/groupPerformance/achievementscontrastList";
	}
	
	/**
	 * 班组绩效对比(展示)
	 * @param model
	 * @return
	 */
	@RequestMapping("/achievementscontrastshow")
	private String achievementscontrastListshow(Model model,IndexScroe vo,String grouplistselect,
			String itenlistselect,String indexlistselect,String monthly){
		LoadFAPG(model, vo);
		List<String> group = new ArrayList<String>();//班组key
		List<String> item = new ArrayList<String>();//考核项目key
		List<String> index = new ArrayList<String>();//考核指标key
		if(grouplistselect!=""){
			String[] s=grouplistselect.split(";");
			Collections.addAll(group, s);
		}
		if(itenlistselect!=""){
			String[] s2=itenlistselect.split(";");
			Collections.addAll(item, s2);
		}
		if(indexlistselect!=""){
			String[] s3=indexlistselect.split(";");
			Collections.addAll(index, s3);
		}
		List<Group> gname=commonService.getGroupList2(group);
		List<String> glist=new ArrayList<String>();
		Map<String,List<List<String>>> map=new LinkedHashMap<String,List<List<String>>>();
		StringBuilder sb=new StringBuilder();
		for (int i = 0; i < gname.size(); i++) {
			glist.add(gname.get(i).getGroupName());
			sb.append(gname.get(i).getGroupName()+"|");
		}
		List<Item> itemlist=new ArrayList<Item>();
		List<Index> allindexlist=new ArrayList<Index>();
		if(item.size()>0){
			itemlist=commonService.getitemlistkey(item);
			if(index.size()<1){
				item = new ArrayList<String>(new HashSet<String>(item));
				allindexlist = commonService.getallindexlist(item);//考核指标
				for (int i = 0; i < allindexlist.size(); i++) {
					index.add(allindexlist.get(i).getIndexKey().toString());
				}
			}else {
				allindexlist=commonService.getindexlistkey(index);
			}
		}else {
			itemlist=commonService.getallitemlist(vo);
			for (int j = 0; j < itemlist.size(); j++) {
				item.add(itemlist.get(j).getItemKey().toString());
			}
			item = new ArrayList<String>(new HashSet<String>(item));
			allindexlist = commonService.getallindexlist(item);//考核指标
			for (int i = 0; i < allindexlist.size(); i++) {
				index.add(allindexlist.get(i).getIndexKey().toString());
			}
		}
		List<Contrast> list=commonService.getContrast(group, item, index, monthly);
		for (int i = 0; i < itemlist.size(); i++) {
			List<List<String>> listlist=new ArrayList<List<String>>();
			for (int j = 0; j < allindexlist.size(); j++) {
				String v=itemlist.get(i).getItemKey().toString();
				String v2=allindexlist.get(j).getItemKey().toString();
				List<String> listg=new ArrayList<String>();
				if(v.equals(v2)){
					listg.add(allindexlist.get(j).getIndexName());
					for (int k = 0; k < glist.size(); k++) {
						String gn=glist.get(k);
						if(list.size()>0){
							String indexket=allindexlist.get(j).getIndexKey().toString();
							for (int k2 = 0; k2 < list.size(); k2++) {
								int num=0;
								if(indexket.equals(list.get(k2).getIndexkey())){
									num=1;
									String name=list.get(k2).getShiftgroupname();
									if(gn.equals(name)){
										listg.add(list.get(k2).getIndexactvalue().toString());
										break;
									}if(!gn.equals(name) && k2==list.size()-1){
										listg.add(" ");
									}
								}
								if(!indexket.equals(list.get(k2).getIndexkey()) && k2==list.size()-1 && num==0){
									listg.add(" ");
								}
							}
						}else {
							listg.add(" ");
						}
					}
					if(listg.size()==1){
						int num=glist.size();
						for (int k = 0; k < num; k++) {
							listg.add(" ");
						}
					}
					StringBuilder sb2=new StringBuilder();
					sb2.append(sb);
					sb2.append("-"+allindexlist.get(j).getIndexKey());
					listg.add(sb2.toString());
					listlist.add(listg);
				}if(!v.equals(v2) && j==allindexlist.size()-1 && listlist.size()==0){
					int num=glist.size()+2;
					for (int k = 0; k < num; k++) {
						listg.add(" ");
					}
					listlist.add(listg);
				}
			}
			map.put(itemlist.get(i).getItemName(), listlist);
		}
		Map<String, String> m=new HashMap<String, String>();
		List<Group> grouplist=commonService.getGroupList(vo);
		for (int i = 0; i < grouplist.size(); i++) {
			m.put(grouplist.get(i).getGroupKey().toString(), grouplist.get(i).getGroupName());
		}
		List<String> GroupKey=new ArrayList<String>();
		for (int i = 0; i < gname.size(); i++) {
			GroupKey.add(gname.get(i).getGroupKey().toString());
		}	
		Map<String, String> m2=new HashMap<String, String>();
		List<Item> itemlistselect=commonService.getItemList(vo);
		for (int i = 0; i < itemlistselect.size(); i++) {
			m2.put(itemlistselect.get(i).getItemKey().toString(), itemlistselect.get(i).getItemName());
		}
		List<String> ItemKey=new ArrayList<String>();
		for (int i = 0; i < itemlist.size(); i++) {
			ItemKey.add(itemlist.get(i).getItemKey().toString());
		}
		ItemKey = new ArrayList<String>(new HashSet<String>(ItemKey));
		Map<String, String> m3=new HashMap<String, String>();
		List<Index> indexlist=commonService.getIndexList(item);
		for (int i = 0; i < indexlist.size(); i++) {
			m3.put(indexlist.get(i).getIndexKey().toString(), indexlist.get(i).getIndexName());
		}
		List<String> IndexKey=new ArrayList<String>(); 
		for (int i = 0; i < allindexlist.size(); i++) {
			IndexKey.add(allindexlist.get(i).getIndexKey().toString());
		}
		JSONObject groupm = JSONObject.fromObject(m);
		JSONObject itemm = JSONObject.fromObject(m2);
		JSONObject indexm = JSONObject.fromObject(m3);
		model.addAttribute("group",groupm.toString());
		model.addAttribute("groupsel",GroupKey.toString());
		model.addAttribute("item",itemm.toString());
		model.addAttribute("itemsel",ItemKey.toString());
		model.addAttribute("index", indexm.toString());
		model.addAttribute("indexsel", IndexKey.toString());	
		model.addAttribute("vo", vo);
		model.addAttribute("column", glist);
		model.addAttribute("map", map);
		System.out.println(GroupKey.toString());
		return "qms/bph/groupPerformance/achievementscontrastList";
	}
	@RequestMapping("/getgroup")
	private void findgroup(Model model,IndexScroe vo,HttpServletResponse respon){
		int result = 0;
		String msg = null ;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> m=new HashMap<String, String>();
		try {
			List<Group> list=commonService.getGroupList(vo);
			for (int i = 0; i < list.size(); i++) {
				m.put(list.get(i).getGroupKey().toString(), list.get(i).getGroupName());
			}
		} catch (Exception e) {
			result=1;
			msg=e.getMessage();
		}
		map.put("group", m);
		map.put("result", result);
		map.put("msg", msg);		
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	@RequestMapping("/getitem")
	private void finditem(Model model,IndexScroe vo,HttpServletResponse respon){
		int result = 0;
		String msg = null ;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> m=new HashMap<String, String>();
		try {
			List<Item> list=commonService.getItemList(vo);
			for (int i = 0; i < list.size(); i++) {
				m.put(list.get(i).getItemKey().toString(), list.get(i).getItemName());
			}
		} catch (Exception e) {
			result=1;
			msg=e.getMessage();
		}
		map.put("item", m);
		map.put("result", result);
		map.put("msg", msg);		
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	@RequestMapping("/getindex")
	private void findindex(HttpServletResponse respon,@RequestParam("Str")String str){
		String[] s=str.split(";");
		List<String> strlist = new ArrayList<String>();
		Collections.addAll(strlist, s);
		int result = 0;
		String msg = null ;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> m=new HashMap<String, String>();
		try {
			List<Index> list=commonService.getIndexList(strlist);
			for (int i = 0; i < list.size(); i++) {
				m.put(list.get(i).getIndexKey().toString(), list.get(i).getIndexName());
			}
		} catch (Exception e) {
			result=1;
			msg=e.getMessage();
		}
		map.put("index", m);
		map.put("result", result);
		map.put("msg", msg);		
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	@RequestMapping("/contrastTimeChar")
	private String findcontrastTimeChar(Model model,String str,String mon) throws Exception{
		String obj=decode(str);
		String[] strgroup=obj.split("-");
		String indexkey=strgroup[1];
		String[] strgroupname=strgroup[0].split("\\|");
		
		EChartObj echar=createRepairData(indexkey, strgroupname,mon);
		JSONObject resultObject = JSONObject.fromObject(echar);
		model.addAttribute("str", resultObject.toString());
		return "qms/bph/groupPerformance/contrastTime";
	}
	private EChartObj createRepairData(String indexkey, String[] seriesNames,String monthly){
		//获取并处理数据
		List<EntityClass> flist=commonService.getEntity(indexkey, seriesNames,monthly);
		EChartObj chart = new EChartObj();
		chart.setChartType(EChartsType.CHART_LINES);                      //图形类型
		//chart.setTitle("未来一周气温变化");                                //设置标题
		chart.setToolboxShow(true);                                       //开启工具配置项
		chart.setToolboxFeatureSaveAsImageShow(true);                     //开启图片
		chart.setToolboxFeatureMagicTypeShow(true);                       //开启动态转换
		chart.setToolboxFeatureDataViewShow(true);                        //配置toolBox数据不可编辑
		chart.setyAxisAxisLabelFormatter("{value}");                    //配置坐标单位  
		String[] strgroup={} ;
		List<String> typelist=new ArrayList<String>();
		for (int i = 0; i < seriesNames.length; i++) {
			typelist.add("line");
		}
		strgroup = typelist.toArray(new String[0]);
		chart.setSeriesType(strgroup);                					//设置系列类型
		List<String> xValue=new ArrayList<String>();
		try {
			xValue = datefand(monthly);
		} catch (Exception e1) {
			e1.printStackTrace();
		}								//x轴值
		Collections.reverse(xValue);
		List<List<Double>> ylist = new ArrayList<List<Double>>();		//y轴值
		for (int i = 0; i < seriesNames.length; i++) {
			List<EntityClass> listgroup=new ArrayList<EntityClass>();
			for (int i2 = 0; i2 < flist.size(); i2++) {
				if(seriesNames[i].equals(flist.get(i2).getShiftgroupname())){
					listgroup.add(flist.get(i2));
				}
			}
			if(listgroup.size()>0){
				List<Double> valuelist=new ArrayList<Double>();
				for (int j = 0; j < xValue.size(); j++) {
					String mon=xValue.get(j);
					for (int j2 = 0; j2 < listgroup.size(); j2++) {
						String entitymon=listgroup.get(j2).getSumdate().substring(0,7);
						if(!mon.equals(entitymon) && j2==listgroup.size()-1){
							valuelist.add(0.00);
							break;
						}
						if(mon.equals(entitymon)){
							valuelist.add(Double.valueOf(listgroup.get(j2).getIndexactvalue()));
							break;
						}
					}
				}
				ylist.add(valuelist);
			}
			
		}
		try{
			chart.setSeriesNames(seriesNames);
			chart.setxValue(xValue);
			chart.setyValues(ylist);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return chart;
	}
	@RequestMapping("/primaryKeySelectListDo")
	private String primaryKeySelectListDo(Model model,IndexScroe indexScore,PageParameter page){
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
	
		LoadFAPG(model, indexScore);
		List<Index> iList = new ArrayList<Index>();
		List<Group> groupList = groupService.getIndexAllByGroup(indexScore.getBaseFactory(), 
					indexScore.getBaseArea(), indexScore.getBaseGroup());
		String category = null;
		for(Group g : groupList){
			GroupCategory cate = g.getUigroupCategory();
			for(Item item : cate.getItem()){
				for(Index index : item.getUiindexs()){
					iList.add(index);
				}
			}
			if(g.getFactory().equals(indexScore.getBaseFactory()) && g.getGroupName().equals(indexScore.getBaseGroup())){
				category = g.getGroupCategory();
			}
		}
		PrimaryKeySelectBean bean = new PrimaryKeySelectBean();
		String sql = bean.primaryKeySql(indexScore);
		bs.put("sql", sql);
		Index index = indexService.findByIndexCode(indexScore.getBaseFactory(), 
				indexScore.getBaseArea(), category, indexScore.getCheckIndex());
		List<BphCommonVo> list  = commonService.getIndexScoreAllByPage(bs);
		for(BphCommonVo com : list){
			com.setIndexCode(indexScore.getCheckIndex());
			com.setIndexName(index.getIndexName());
		}
		
		model.addAttribute("list",list);
		model.addAttribute("index", iList);
		model.addAttribute("vo", indexScore);
		model.addAttribute("page",page);
		return "qms/bph/groupPerformance/primaryKeySelectList";
	}
	/**
	 * 月度班组绩效明细查询
	 * @param model
	 * @return
	 */
	@RequestMapping("/performanceDetailList")
	private String performanceDetailList(Model model,IndexScroe indexScore){
		LoadFAPG(model, indexScore);
		return "qms/bph/groupPerformance/performanceDetailList";
	}
	@RequestMapping("/performanceDetailListMonth")
	private String performanceDetailListMonthNew(Model model,IndexScroe indexScore,PageParameter page) throws ParseException, UnsupportedEncodingException {
		String url ="qms/bph/groupPerformance/performanceDetailList";
		if(indexScore.getMykey()!=null){
			String mykey = decode(indexScore.getMykey());
			String score = decode(indexScore.getHomeScore());
			String rank = decode(indexScore.getHomeRank());
			if(mykey.equals("home")){
				url = "qms/homepage/performanceDetailList";
				indexScore.setHomeScore(score);
				if(rank==null || rank.equals("")){
					BphCommonVo vo = new BphCommonVo();
					vo.setShiftGroupTxt(indexScore.getGroup());
					vo.setFactory(decode(indexScore.getFactory()));
					vo.setQueryMonth(indexScore.getDate());
					List<BphCommonVo> alist = commonService.selectGroupRank(vo);
					if(!alist.isEmpty()){
						indexScore.setHomeRank(alist.get(0).getRowId().toString());
					}					
				}
				else{
					indexScore.setHomeRank(rank);
				}
				
			}
		}
		String factory = null;
		String area = null;
		String date = null;
		String group = null;
		if(indexScore.getGroup() !=null && indexScore.getDate() != null){
			 date = decode(indexScore.getDate());
			 factory = decode(indexScore.getFactory());
			 group = decode(indexScore.getGroup());
			 List<CommonSelectedBox> facAreaList = commonSelectedBoxService.getFacAreaByGroup(factory,group);
			 area = facAreaList.get(0).getArea();
			 indexScore.setBaseFactory(factory);
			 indexScore.setBaseArea(area);
			 indexScore.setStartTime(date);
			 indexScore.setBaseGroup(group);
			 LoadFAPG(model, indexScore);
		}else{
			factory = indexScore.getBaseFactory();
			area = indexScore.getBaseArea();
			date = indexScore.getStartTime();
			group = indexScore.getBaseGroup();
			LoadFAPG(model, indexScore);
		}		
        List<Group> list = groupService.getGroupScoreByMonth(factory, area, group, date);
        List<Group> glist  = new ArrayList<Group>();
		if(list != null && !list.isEmpty() ){
			glist =	setUrlToMonth(list,factory,date);
		}else{
			glist = groupService.getIndexAllByGroup(factory, area, group);
		}
		
		model.addAttribute("list",glist);
		model.addAttribute("page",page);
		model.addAttribute("vo", indexScore);
		
		return url;
	}
	
	
	@RequestMapping("/performanceDetailYearList")
	private String performanceDetailYearList(Model model,IndexScroe indexScore){
		LoadFAPG(model, indexScore);
		indexScore.setStartTime(getPriviousMonth());
		indexScore.setEndTime(getPriviousMonth());
		model.addAttribute("vo", indexScore);
		return "qms/bph/groupPerformance/performanceDetailYearList";
	}
	@RequestMapping("/performanceDetailListYearYear")
	private String performanceDetailListYearYear(Model model,IndexScroe indexScore,PageParameter page) throws ParseException{
		BaseSearch bs = new BaseSearch();
		bs.setPage(page);
		bs.put("factory", indexScore.getBaseFactory());
		bs.put("area", indexScore.getBaseArea());
		bs.put("shiftGroupName", indexScore.getBaseGroup());
		bs.put("shiftGroupCategory", indexScore.getBaseGroup());
		bs.put("startTimeOne", indexScore.getStartTime());
		bs.put("endTime", indexScore.getEndTime());
		LoadFAPG(model, indexScore);
		ItemScore item = new ItemScore();
	    item.setFactory(indexScore.getBaseFactory());
	    item.setArea(indexScore.getBaseArea());
	    item.setShiftGroupName(indexScore.getBaseGroup());
	    item.setStartMonth(indexScore.getStartTime());
	    item.setEndMonth(indexScore.getEndTime());
	    List<ItemScore> sclist = commonService.sumItemScore(item);
		List<IndexScroe> idList = commonService.getIndexScoreByMonth(bs);
		List<CheckIndex> idxList = checkIndexService.getCheckIndexList(bs);
		for(IndexScroe  in : idList){
			for(ItemScore it : sclist){
				if(it.getShiftGroupName().equals(in.getShiftGroupName()) && it.getSumDate().equals(in.getSumDate())){
					in.setHomeScore(it.getItemScore().toString());
				}
			}
		}
		Map<Long,Map<String,IndexScroe>> map = new HashMap<Long, Map<String,IndexScroe>>();
		for(CheckIndex idx : idxList)
		{
			if(!map.containsKey(idx.getId()))
			{
				map.put(idx.getId(), new TreeMap<String,IndexScroe>(new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                    	String s1 = obj1.replace("-", "");
                    	String s2 = obj2.replace("-", "");
                    	int startMon = NumberUtils.toInt(s1);
                    	int endMon = NumberUtils.toInt(s2);
                    	return startMon > endMon ? 1 : (startMon < endMon ? -1 : 0);
                    }
                })
                );				
			}
		}
		for(IndexScroe idx : idList)
		{

			if(idx.getSumDate() != null && null != map.get(idx.getIndexId())){
				map.get(idx.getIndexId()).put(DateFormatUtils.format(idx.getSumDate(),"yyyy-MM"),idx);
			}	
		}
		addEmpty(indexScore, map);
		model.addAttribute("scoreMap",map);
		model.addAttribute("indexList", idxList );
		model.addAttribute("scoreList",sclist);
		model.addAttribute("page",page);
		model.addAttribute("vo", indexScore);
		return "qms/bph/groupPerformance/performanceDetailYearList";
	}
	
	@SuppressWarnings("deprecation")
	private void addEmpty(IndexScroe indexScore, Map<Long,Map<String,IndexScroe>> map)
	{
		try {
			int startMon = DateUtils.parseDate(indexScore.getStartTime(), new String[] { "yyyy-MM" }).getMonth();
			int endMon = DateUtils.parseDate(indexScore.getEndTime(), new String[] { "yyyy-MM" }).getMonth();
			String month = null;
			
			for(int i = startMon; i <= endMon;i++)
			{
				month = (i + 1) + "月";
				for(Map.Entry<Long,Map<String,IndexScroe>> en : map.entrySet())
				{
					if(!en.getValue().containsKey(month))
					{
						en.getValue().put(month, new IndexScroe());
					}
				}
			}
		} catch (ParseException e) {
			logger.error("日期转换错误", e);
		}
			
	}
	//获取单个班组一年每月绩效
	@RequestMapping("/oneGroupPerfanceYear")
	public String oneGroupPerfanceYear(Model model,BphCommonVo vo) throws Exception{
		vo.setBaseFactory(decode(vo.getFactory()));
		vo.setBaseGroup(decode(vo.getShiftGroupTxt()));
		vo.setStartTime(decode(vo.getStartTime()));
		vo.setBaseArea(decode(vo.getArea()));
		vo.setChartType(decode(vo.getChartType()));
		LoadFAPG(model, vo);
		if(decode(vo.getStartTime())==null || "".equals(decode(vo.getStartTime()))){
			vo.setStartTime(getPriviousMonth());
		}
		model.addAttribute("vo", vo);
		return "qms/bph/groupPerformance/oneGroupYearChart";
	}
	
	/**
	 * 获取单个班组一年每月绩效
	 * @param vo
	 * @param respon
	 */
	@RequestMapping("/getOneGroupPerfanceYear")
	public void getOneGroupPerfanceYear(BphCommonVo vo,HttpServletResponse respon){
		int result = 0;
		String msg = null ;
		Map<String, Object> map = new HashMap<String, Object>();
		ChartObj chartVo = null;
		try {
			//获取班组绩效	
			String group = vo.getShiftGroupTxt();
	        String year = vo.getStartTime();
            String startTime = WebUtil.rebackMonths(year, -11);
			List<BphCommonVo> shiftGroup = commonService.sumItemScoreByYear(vo); 
			List<String> monthList = WebUtil.getBackDateList(year, 11);
            List<BphCommonVo> glist = new ArrayList<BphCommonVo>();
			 for(int i=0; i<monthList.size(); i++){			   
			    	BphCommonVo com = new BphCommonVo();
			    	com.setBaseMonth(monthList.get(i));
			    	glist.add(com);
			    	}
			 for(BphCommonVo v : shiftGroup){
				 for(BphCommonVo c : glist){
					 if(v.getBaseMonth().equals(c.getBaseMonth())){
						 c.setShiftGroupScore(v.getShiftGroupScore());
					 }
				 }
			 }
			
			//构造图表
			chartVo = new ChartObj();
			
			chartVo.setChartHight(600);
			chartVo.setChartWidth(1200);
			chartVo.setChartType("line");
	          
			chartVo.setTitle(group+startTime+"~"+year+"年度绩效统计图");
			chartVo.setxTitle("月份");
			chartVo.setyLeftTitle("分数");
			chartVo.setyLeftUnit("分");
			
			
			List<String> xvalues = new ArrayList<String>();
			List<List<Double>> yValues = new ArrayList<List<Double>>();
			String[] seriesNames = {"分数"};
			
			List<Double> yvalue1 = new ArrayList<Double>();
			for (int i = 0; i < glist.size(); i++) {
				BphCommonVo tmpVo = glist.get(i);
				xvalues.add(tmpVo.getBaseMonth());
				yvalue1.add(tmpVo.getShiftGroupScore()+0.0);
				
			}
			yValues.add(yvalue1);	
			chartVo.setxValue(xvalues);
			chartVo.setSeriesNames(seriesNames);
			chartVo.setyValues(yValues);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("chartsInfo", chartVo);
		map.put("result", result);
		map.put("msg", msg);
		
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	
	
	/**
	 * 单月
	 * @param vo
	 * @param respon
	 */
	@RequestMapping("/getPerformanceSingleChar")
	private void getPerformanceSingleChar(BphCommonVo vo,HttpServletResponse respon){
		int result = 0;
		String msg = null ;
		Map<String, Object> map = new HashMap<String, Object>();
		ChartObj chartsInfo = null;
		try {
			chartsInfo = createPerformanceData(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("chartsInfo", chartsInfo);
		map.put("result", result);
		map.put("msg", msg);
		
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	/**
	 * 期间
	 * @param vo
	 * @param respon
	 */
	@RequestMapping("/getPerformancePeriodChart")
	private void getPerformancePeriodChart(BphCommonVo vo,HttpServletResponse respon){
		int result = 0;
		String msg = null ;
		Map<String, Object> map = new HashMap<String, Object>();
		ChartObj chartsInfo = null;
		try {
			chartsInfo = createPerformanceData(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = -1;
			msg = e.getMessage();
		}
		map.put("chartsInfo", chartsInfo);
		map.put("result", result);
		map.put("msg", msg);
		
		JSONObject resultObject = JSONObject.fromObject(map);
		logger.debug(resultObject);
		printResponContent(respon, resultObject.toString());
	}
	
	//各报表计算说明
	@RequestMapping("/indexDecription")
	public String chartDecription(Model model){
		Map<String ,String> indexDescMap = groupCategorySerivce.getIndexDescription();
		model.addAttribute("map", indexDescMap);
		return "qms/bph/groupPerformance/indexDecriptionlist";
	}
	

	private ChartObj createPerformanceData(BphCommonVo vo) throws Exception{
//		int xCount = 20;
		List<Group> groupList = new ArrayList<Group>();
		if(vo.getFactory()!= null && !"".equals(vo.getFactory())){
				groupList = groupService.getGroupByFa(vo.getFactory(), vo.getArea());
				
				String[] groups = null;
				if(!groupList.isEmpty()){
				    groups = new String[groupList.size()];
					for(int t =0; t<groupList.size(); t++){
						groups[t] = groupList.get(t).getGroupName();
					}
					vo.setShiftGroups(groups);
				}else{
				    groups = new String[1];
				    groups[0] = " ";
					vo.setShiftGroups(groups);
				}			
		}else{
			convertMultiSelectCondition(vo);
		}
		
		
		//获取班组绩效		
		List<BphCommonVo> shiftGrouplist = commonService.getShiftGroupPerFormanceByGroup(vo);

		
		//构造图表
		ChartObj chartVo = new ChartObj();
		String mon ="";
		if(vo.getRegion()!=null){
			mon = vo.getRegion();
		}
		
		chartVo.setChartHight(600);
		chartVo.setChartWidth(1200);
		chartVo.setChartType("column");
          
		chartVo.setTitle("班组绩效排名统计图"+mon);
		chartVo.setxTitle("班组名称");
		chartVo.setyLeftTitle("分数");
		chartVo.setyLeftUnit("分");
		return setData(shiftGrouplist,chartVo);
	}

	private ChartObj setData(List<BphCommonVo> reList,ChartObj chartVo){
		List<String> xvalues = new ArrayList<String>();
		List<List<Double>> yValues = new ArrayList<List<Double>>();
		String[] seriesNames = {"分数"};
		
		List<Double> yvalue1 = new ArrayList<Double>();
		for (int i = 0; i < reList.size(); i++) {
			BphCommonVo tmpVo = reList.get(i);
			xvalues.add(tmpVo.getShiftGroupTxt());
			yvalue1.add((tmpVo.getShiftGroupScore() +0.0)< 0 ? 0 : (tmpVo.getShiftGroupScore() +0.0));
			
		}
		yValues.add(yvalue1);	
		chartVo.setxValue(xvalues);
		chartVo.setSeriesNames(seriesNames);
		chartVo.setyValues(yValues);
		
		return chartVo;
		
	}
	
	
	@RequestMapping("/getprimaryKey")
	private void getprimaryKey(HttpServletResponse response,Group group){
		int result = 1;
		Map<String,Object> map = new HashMap<String, Object>();
		List<Index> iList = new ArrayList<Index>();
		try{
			List<Group> list = groupService.getIndexAllByGroup(group.getFactory(), group.getArea(), group.getGroupName());
			for(Group g : list){
				GroupCategory cate = g.getUigroupCategory();
				for(Item item : cate.getItem()){
					for(Index index : item.getUiindexs()){
						iList.add(index);
					}
				}
			}
		}catch(Exception e){
			result = -1;
		}
		map.put("result", result);
		map.put("list", iList);
		JSONObject json = JSONObject.fromObject(map);
//		System.out.println(json.toString());
		printResponContent(response, json.toString());
	}
	
	
	@RequestMapping("/loadShiftGroup")
	public String loadShiftGroup(Model model,CheckIndex checkIndex){
		String factory = checkIndex.getFactory();
		String area = checkIndex.getArea();
		List<ShiftGroup> list = null;
		if(area!=null && !"".equals(area)){
			list = getshiftGroupByFacAr(factory,area);
		}else{
			list = getShiftGroupByFac(factory);
		}

		Map<String, String> partMap = new LinkedHashMap<String, String>();
		
		for (ShiftGroup group : list){
			partMap.put(group.getName(), group.getName());
		}
	
	    JSONObject partJsonObject = JSONObject.fromObject( partMap );
	    model.addAttribute("jsonParts", partJsonObject);
	    return "qms/chart/groupPerformance/groupCondition";
	}
	
	
	private List<ShiftGroup> getshiftGroupByFacAr(String factory, String area){
		
		List<ShiftGroup> list = shiftGroupService.getShiftGroupByFoArea(factory, area);
        return list;
	}
	
	private List<ShiftGroup> getShiftGroupByFac(String factory){
		List<ShiftGroup> list = shiftGroupService.getShiftGroup(factory);
		return list;
	}
	
	private String getPriviousMonth(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,-1);
		return DateFormatUtils.format(cal.getTime(), "yyyy-MM");
	}
	
	private List<Group> setUrlToMonth(List<Group> list,String factory,String date){
		for(Group g : list){
			 g.setFlag("flag");
		     GroupCategory cate = g.getUigroupCategory();
		     double groupScore = 0;
		     for(Item item : cate.getItem()){
		    	 groupScore += item.getItemScore();
		    	 double itemScale = 0;
		    	 for(Index index : item.getUiindexs()){
		    		 for(MonthAssessment mon : index.getMonthAssessments()){
		    			 mon.setFlag(true);
		    			 itemScale = mon.getItemScale();
		    			 if(ASSEMBLEPRODUCE_INDEX.indexOf(index.getIndexCode())>=0){
								mon.setUrlStr(ASSEMBLEPRODUCE_URL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}else if(FORMERFQCCHECH_INDEX.indexOf(index.getIndexCode())>=0){
								mon.setUrlStr(FORMERFQCCHECH_URL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}else if(BATCHDEFECT_INDEX.indexOf(index.getIndexCode())>=0){
								mon.setUrlStr(BATCHDEFECT_URL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}else if(STAMPINGDAYLY_INDEX.indexOf(index.getIndexCode())>=0){
								mon.setUrlStr(STAMPINGDAYLY_URL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}else if(OQCCHECK_INDEX.indexOf(index.getIndexCode())>=0 ){
								mon.setUrlStr(OQCCHECK_URL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}else if(PROCESSAUDIT_INDEX.indexOf(index.getIndexCode())>=0 && !index.getIndexCode().substring(1, index.getIndexCode().length()).equals("1")){
								mon.setUrlStr(PROCESSAUDIT_URL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}else if(QUALITYINP_INDEX.indexOf(index.getIndexCode())>=0 && !index.getIndexCode().substring(1, index.getIndexCode().length()).equals("1")){
								mon.setUrlStr(QUALITYINP_URL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}else if(BOXDEFECT_INDEX.indexOf(index.getIndexCode())>=0){
								mon.setUrlStr(BOXDEFECT_URL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}else if(PAINTINGDAYLY_INDEX.indexOf(index.getIndexCode())>=0){
								mon.setUrlStr(PAINTINGDAYLY_URL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}else if(IPQCINSPECTS_INDEX.indexOf(index.getIndexCode())>=0){
								mon.setUrlStr(IPQCINSPECTS_URL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}else if(ASSEMBLYREPARIED_INDEX.indexOf(index.getIndexCode())>=0){
								mon.setUrlStr(ASSEMBLYREPARIED_RUL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}
							else if(PAINTINGPRODUCE_INDEX.indexOf(index.getIndexCode())>=0){
								mon.setUrlStr(PAINTINGPRODUCE_RUL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}
							else if(FINISHINGDAILY_INDEX.indexOf(index.getIndexCode())>=0){
								mon.setUrlStr(FINISHINGDAILY_RUL);
								mon.setQueryMonth(date);
								mon.setFactory(factory);
							}
		    		 }
		    	 }
		    	 item.setItemScale(itemScale);
		     }
		     g.setGroupScore(groupScore<0 ? 0 : MathUtil.divide(groupScore, 1, 2));
		}
		return list;
	}
	
	private void convertMultiSelectCondition(BphCommonVo vo)
	{
		
		if(StringUtils.isNotBlank(vo.getShiftGroupTxt())){
			vo.setShiftGroups(vo.getShiftGroupTxt().replaceAll("'", "").split(";"));
		}
		
	}
	//生成月份，格式：2016-08
	public List<String> datefand2(){
		List<String> list=new ArrayList<String>();
        Calendar curr = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date d=curr.getTime();
        list.add(sdf.format(d));
        for (int i = 0; i < 11; i++) {
        	curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)-1);
            Date date=curr.getTime();
            list.add(sdf.format(date));
		}
        return list;
	}
	//生成月份，格式：2016-08
	private List<String> datefand(String time) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//日期格式
		Date date = sdf.parse(time); //将字符串按格式转成date
		List<String> list=new ArrayList<String>();//返回的集合
        Calendar curr = Calendar.getInstance();//获取一个日历对象
        curr.setTime(date);//将时间设置进去日历对象中
        list.add(time);
        for (int i = 0; i < 11; i++) {
        	curr.set(Calendar.MONTH,curr.get(Calendar.MONTH)-1);//日期的月份减一
            list.add(sdf.format(curr.getTime()));
		}
        return list;
	}
}
