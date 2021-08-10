package com.peg.qms.controller.system;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.smt.SmtLog;
import com.peg.service.system.SmtLogServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @createTime 2019-06-19 16:18
 */
@Controller
@RequestMapping("system/smtLog")
public class SmtLogController {
    
    @Autowired
    private SmtLogServiceI smtLogService;

    @RequestMapping("/list")
    public String list(Model model, PageParameter page, SmtLog smtLog) {
        List<SmtLog> list = smtLogService.getAllByPage(page, smtLog);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("smtLog", smtLog);
        return "/qms/system/smtLog/list";
    }
}
