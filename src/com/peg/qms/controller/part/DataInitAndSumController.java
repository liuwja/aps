package com.peg.qms.controller.part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peg.qms.controller.BaseController;

/**
 * 物料数据初始化和汇总控制器
 * @author Administrator
 *
 */
@Controller("dataInitAndSumController")
@RequestMapping("quality/common")
public class DataInitAndSumController extends BaseController{

	@RequestMapping("/dataSum")
	public String dataSum(Model model){
		return "qms/part/common/dataSum";
	}
}
