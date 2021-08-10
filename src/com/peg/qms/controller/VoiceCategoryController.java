package com.peg.qms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;






import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.CommonVo;
import com.peg.model.VoiceCategory;
import com.peg.service.VoiceCategoryServiceI;

@Controller
@RequestMapping("/voiceCategory")
public class VoiceCategoryController {
	
	@Autowired
	private VoiceCategoryServiceI voiceCategoryService;
	
	@RequestMapping("/findAll")
	private String findAll(Model model,CommonVo vo,PageParameter page){
		if(page == null){
			page = new PageParameter();
		}
		List<VoiceCategory> list = voiceCategoryService.findAllByPage(vo,page);
		Integer totalCount = voiceCategoryService.findAll(vo);
		page.setTotalCount(totalCount);
		model.addAttribute("vo",vo);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		return "qms/voice/voiceCategory";
	}
	
	@RequestMapping("/updateVoiceCategory")
	@ResponseBody
	private String updateVoiceCategory(Model model,CommonVo vo){	
		Map<String, Object> map = voiceCategoryService.updateVoiceCategory(vo);
		//findAll(model,vo);
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
