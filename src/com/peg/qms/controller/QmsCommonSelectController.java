package com.peg.qms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.FaultReason;
import com.peg.model.FaultType;
import com.peg.model.Location;
import com.peg.model.LocationRegion;
import com.peg.model.VoiceCategory;
import com.peg.service.CommonServiceI;
import com.peg.service.FaultReasonServiceI;
import com.peg.service.FaultTypeServiceI;
import com.peg.service.LocationRegionServiceI;
import com.peg.service.VoiceCategoryServiceI;
import com.peg.service.part.OnlineServiceITwo;
import com.peg.web.util.WebUtil;

@Controller
@RequestMapping("qms/commonSelect")
public class QmsCommonSelectController extends BaseController {

	@Autowired
	private FaultTypeServiceI faultTypeService;
	
	@Autowired
	private FaultReasonServiceI faultReasonService;
	
	@Autowired
	private LocationRegionServiceI locationRegionService;
	
	@Resource
	private OnlineServiceITwo onlineServiceITwo;
	
	@Autowired
	private CommonServiceI commonService;
	
	@Autowired
	private VoiceCategoryServiceI voiceCategoryService;

	/**
	 * 故障大类查询
	 */
	@RequestMapping("/faultTypeSelect")
	public String faultTypeSelect(FaultType faultType, Model model, PageParameter page) {
		setBaseData(model);
		faultType.setProductType(WebUtil.ISOToUTF8(faultType.getProductType()));
		faultType(faultType, model, page);
		model.addAttribute("firstLoad", "1");
		return "qms/select/faultSelect";
	}

	@RequestMapping("/faultTypeSelectResult")
	public String faultTypeSelectResult(FaultType faultType, Model model, PageParameter page) {
		setBaseData(model);
		faultType(faultType, model, page);
		model.addAttribute("firstLoad", "2");
		return "qms/select/faultSelectResult";
	}

	private void faultType(FaultType faultType, Model model, PageParameter page) {
		List<FaultType> list = faultTypeService.findAllByPage(faultType, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", faultType);
		model.addAttribute("data", faultType.getGroupName());
	}
	/**
	 * 客户之声分类查询
	 */
	@RequestMapping("/vocCategory")
	public String vocCategory(VoiceCategory voice,Model model,PageParameter page){
		List<VoiceCategory> list = voiceCategoryService.findAllByPage(voice, page);
		List<VoiceCategory> paterList = voiceCategoryService.findAllPater(voice);
		model.addAttribute("list", list);
		model.addAttribute("vocPaterList",paterList);
		model.addAttribute("page", page);
		model.addAttribute("vo", voice);
		model.addAttribute("data", voice.getGroupName());
		model.addAttribute("firstLoad", "1");
		return "qms/select/voiceCategorySelect";
	}	
	@RequestMapping("/vocCategorySelectResult")
	public String vocCategorySelectResult(VoiceCategory voice,Model model,PageParameter page){
		
		List<VoiceCategory> list = voiceCategoryService.findAll(voice);
		List<VoiceCategory> paterList = voiceCategoryService.findAllPater(voice);
		model.addAttribute("list", list);
		model.addAttribute("vocPaterList",paterList);
		model.addAttribute("page", page);
		model.addAttribute("vo", voice);
		model.addAttribute("data", voice.getGroupName());
		model.addAttribute("firstLoad", "2");
		return "qms/select/voiceCategorySelectResult";
	}

	/**
	 * 重新找开故障大类选择页面时，根据ID找到已选择的故障大类
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryFaultByid")
	public String queryFaultByid(FaultType vo, Model model) {
		try {
			String ids = vo.getKeys();
			if (StringUtils.isNotBlank(ids)) {
				String[] idsArr = ids.split(",");
				List<FaultType> list = new ArrayList<FaultType>();
				for (String u : idsArr) {
					list.add(faultTypeService.getfaultbykey(u));
				}
				model.addAttribute("list", list);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "qms/select/selectedFault";
	}
	/**
	 * 重新打开VOC分类选择页面时，根据ID找到已选择的voc分类
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryVocCategoryByid")
	public String queryVocCategoryByid(CommonVo vo, Model model) {
		try {
			String ids = vo.getKeys();
			if (StringUtils.isNotBlank(ids)) {
				String[] idsArr = ids.split(",");
				List<VoiceCategory> list = new ArrayList<VoiceCategory>();
				for (String u : idsArr) {
					list.add(voiceCategoryService.getVoiceCategorybykey(Long.parseLong(u)));
				}
				model.addAttribute("list", list);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "qms/select/selectedVoiceCategory";
	}

	/**
	 * 故障小类查询，支持翻页
	 */
	@RequestMapping("/faultReasonSelect")
	public String faultReasonSelect(FaultReason faultReason, Model model,PageParameter page, @RequestParam(value="faultReasonIds",required = false) String faultReasonIds) {
		setBaseData(model);
		faultReason.setProductType(WebUtil.ISOToUTF8(faultReason.getProductType()));
		faultReason(faultReason, model, page);
		model.addAttribute("faultReasonIds", faultReasonIds);
		model.addAttribute("firstLoad", "1");
		return "qms/select/faultReasonSelect";
	}

	@RequestMapping("/faultReasonSelectResult")
	public String faultReasonSelectResult(FaultReason faultReason, Model model, PageParameter page) {
		setBaseData(model);
		faultReason(faultReason, model, page);
		model.addAttribute("firstLoad", "2");
		return "qms/select/faultReasonSelectResult";
	}
	
	private void faultReason(FaultReason faultReason, Model model, PageParameter page) {
		List<FaultReason> list = faultReasonService.findAllByPage(faultReason, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", faultReason);
	}
	
	@RequestMapping("/queryFaultReasonByid")
	public String queryFaultReasonByid(FaultReason vo, Model model) {
		try {
			String ids = vo.getKeys();
			if (StringUtils.isNotBlank(ids)) {
				String[] idsArr = ids.split(",");
				List<FaultReason> list = new ArrayList<FaultReason>();
				for (String u : idsArr) {
					list.add(faultReasonService.selectByPrimaryKey(Long.parseLong(u)));
				}
				model.addAttribute("list", list);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "qms/select/selectedFaultReason";
	}
	
	/**
	 * 服务中心选择
	 * @param locationRegion
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("/regionSelect")
	public String regionSelect(LocationRegion locationRegion, Model model, PageParameter page) {
		setBaseData(model);
		List<LocationRegion> list = locationRegionService.findAllByPage(locationRegion, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo",locationRegion);
		return "qms/select/regionSelect";
	}
	
	@RequestMapping("/meshFaultReasonSelect")
	public String meshFaultReasonSelect(FaultReason faultReason, Model model) throws Exception {
		if (StringUtils.isNotEmpty(faultReason.getProductType())) {
			faultReason.setProductType(decode(faultReason.getProductType()));
		}
		setBaseData(model);
		List<FaultReason> list = faultReasonService.getMeshFaultReason(faultReason);
		model.addAttribute("list", list);
		model.addAttribute("vo", faultReason);
		return "qms/select/meshFaultReasonSelect";
	}
	
	@RequestMapping("/meshFaultReasonSelectResult")
	public String meshFaultReasonSelectResult(FaultReason faultReason, Model model) {
		setBaseData(model);
		List<FaultReason> list = faultReasonService.getMeshFaultReason(faultReason);
		model.addAttribute("list", list);
		model.addAttribute("vo", faultReason);
		return "qms/select/meshFaultReasonSelectResult";
	}
	
	/**
	 * 合并故障小类名称查询
	 */
	@RequestMapping("/meshFaultNameSelect")
	public String meshFaultNameSelect(FaultReason faultReason, Model model,
			PageParameter page) {
		List<FaultReason> list = faultReasonService.findAllMeshNamesByPage(faultReason, page);
		setBaseData(model);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", faultReason);
		return "qms/select/meshFaultNameSelect";
	}
	
	/**
	 * 合并故障小类名称查询-返回
	 */
	@RequestMapping("/meshFaultNameSelectResult")
	public String meshFaultNameSelectResult(FaultReason faultReason, Model model,
			PageParameter page) {
		setBaseData(model);
		List<FaultReason> list = faultReasonService.findAllMeshNamesByPage(faultReason, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", faultReason);
		return "qms/select/meshFaultNameSelectResult";
	}
	
	/**
	 * 故障小类查询，支持翻页
	 */
	@RequestMapping("/moreMeshFaultSelect")
	public String moreMeshFaultSelect(FaultReason faultReason, Model model,PageParameter page,
			@RequestParam(value="faultReasonIds",required = false) String faultReasonIds) {
		List<FaultReason> list = faultReasonService.findAllMeshNamesByPage(faultReason, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", faultReason);
		model.addAttribute("faultReasonIds", faultReasonIds);
		return "qms/select/moreMeshFaultSelect";
	}

	@RequestMapping("/moreMeshFaultSelectResult")
	public String moreMeshFaultSelectResult(FaultReason faultReason, Model model,
			PageParameter page) {
		List<FaultReason> list = faultReasonService.findAllMeshNamesByPage(faultReason, page);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", faultReason);
		return "qms/select/moreMeshFaultSelectResult";
	}
	
	//多选返回
	@RequestMapping("/moreMeshFaultSel")
	public String moreMeshFaultSel(FaultReason vo, Model model){
		try {
			String ids = vo.getKeys();
			if (StringUtils.isNotBlank(ids)) {
				String[] idsArr = ids.split(",");
				List<FaultReason> list = new ArrayList<FaultReason>();
				for (String u : idsArr) {
					list.add(faultReasonService.selectByPrimaryKey(Long.parseLong(u)));
				}
				model.addAttribute("list", list);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "qms/select/moreMeshFaultSel";
	}

	/**
	 * 机型查询
	 * @throws Exception 
	 */
	@RequestMapping("/partTypeSelect")
	public String partTypeSelect(CommonVo vo, Model model,
			@RequestParam(value="partTypeId",required = false) String partTypeId) throws Exception {
		vo.setProductType(decode(vo.getProductType()));
		setBaseData(model);
		getPartType(vo, model);
		model.addAttribute("partTypeId", partTypeId);
		return "qms/select/partTypeSelect";
	}

	/**
	 * 区域
	 */
	@RequestMapping("/findRegionSelect")
	public String findRegionSelect(Location mo,String strarr,Model model,PageParameter page) {
		List<Location> list=onlineServiceITwo.getLocal(mo,null, page);
		String result="No";
		if(strarr!=null && !strarr.equals("")){
			List<Location> list2=onlineServiceITwo.getLocal(mo,strarr, page);
			model.addAttribute("list2", list2);
			result="Yes";
		}
		model.addAttribute("result", result);
		model.addAttribute("list", list);
		model.addAttribute("mo", mo);
		model.addAttribute("page", page);
		return "qms/select/localSelect";
	}
	
	@RequestMapping("/findTypeSelectResult")
	public String findTypeSelectResult(Location mo,String strarr,Model model,PageParameter page) {
		List<Location> list=onlineServiceITwo.getLocal(mo,strarr, page);
		model.addAttribute("list", list);
		model.addAttribute("mo", mo);
		model.addAttribute("page", page);
		return "qms/select/localSelectResult";
	}
	private void getPartType(CommonVo vo, Model model) {
		List<CommonVo> list = commonService.getPartTypeListFromMes(vo);
		model.addAttribute("list", list);
		model.addAttribute("totalCount", list.size());
		model.addAttribute("vo", vo);
	}

	@RequestMapping("/partTypeSelectResult")
	public String partTypeSelectResult(CommonVo vo, Model model) {
		setBaseData(model);
		getPartType(vo, model);
		return "qms/select/partTypeSelectResult";
	}
}