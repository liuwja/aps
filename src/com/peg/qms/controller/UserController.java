package com.peg.qms.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.peg.dao.interceptor.PageParameter;
import com.peg.model.system.SysGroup;
import com.peg.model.system.SysMesUser;
import com.peg.service.system.SysGroupServiceI;
import com.peg.service.system.SysMesUserServiceI;


/**
 * TODO
 * <p>
 * @author Lin, 2014-8-8 下午04:21:37
 */
@Controller
@RequestMapping("system/user")
public class UserController extends BaseController 
{
	@Autowired
	private SysMesUserServiceI sysUserService;
	
	@Autowired
	private SysGroupServiceI sysGroupService;
	
	@RequestMapping("/list")
	public String list(Model model, SysMesUser user,PageParameter page)
	{
		
		List<SysMesUser> list = sysUserService.getAllByPage(page, user.getUserName(), user.getDescription());
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("vo", user);
		return "qms/system/user/list";
	}
	
	@RequestMapping("/userSetting")
	public String groupSetting(Model model, PageParameter page, 
		@RequestParam(value = "userName", required = false) String userName,
		@RequestParam(value = "name", required = false) String name,		
		@RequestParam(value = "groupKey") Long groupKey)
	{
		
		List<SysMesUser> allUsers = sysUserService.getAllByPage(page, userName, name);
		List<SysMesUser> groupUsers = sysUserService.findUsersByGroupKey(groupKey);
		SysGroup group = sysGroupService.selectByPrimaryKey(groupKey);
		
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("page", page);
		model.addAttribute("group", group);
		
		model.addAttribute("groupUsers", groupUsers);
		return "qms/system/user/userSetting";
	}
	
	@RequestMapping("/toUserGroup")
	public String toUserGroup(Model model, PageParameter page, SysGroup group,
			@RequestParam(value = "userKey") Long userKey)
	{
		List<SysGroup> allGroup = sysGroupService.findAllByPage(page, group.getGroupCode(), group.getGroupName());
		List<SysGroup> selectGroup = sysGroupService.findGroupsByUserKey(userKey);
			
		SysMesUser sysuser = sysUserService.selectByPrimaryKey(userKey);
		
		model.addAttribute("list", allGroup);
		model.addAttribute("page", page);
		model.addAttribute("sysuser", sysuser);
		
		model.addAttribute("selectGroups", selectGroup);
		return "qms/system/user/userToGroup";
	}
	
	@RequestMapping("/queryUsers")
	public String queryUsers(Model model, PageParameter page, 
		@RequestParam(value = "userName", required = false) String userName,
		@RequestParam(value = "name", required = false) String name)
	{
		List<SysMesUser> allUsers = sysUserService.getAllByPage(page, userName, name);
		
		model.addAttribute("allUsers", allUsers);
		model.addAttribute("page", page);
		return "qms/system/user/userQueryResult";
	}
	
	@RequestMapping("/mergeUser")
	public ModelAndView mergeUser(){
		try{
			sysUserService.mergeUser();
		}catch (Exception e) {
			// TODO: handle exception
			return ajaxDoneError("用户不存在"+e.getMessage());
		}
		return ajaxDoneSuccess("同步成功");
	}

	/*@RequestMapping("/add")
	public String add()
	{
		return "qms/system/user/register";
	}

	@RequestMapping("/debug")
	public String debug()
	{
		return "/debug";
	}
	
	@RequestMapping("/save")
	public ModelAndView save(SysMesUser user)
	{
			user.setPassword(MD5.md5( user.getPassword()));  
			int result = sysUserService.insert(user);
			return ajaxDoneSuccess("保存成功");
	}
	
	
	@RequestMapping("/edit")
	public String edit( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		SysMesUser user = sysUserService.selectByPrimaryKey(id);
		
		model.addAttribute("user", user);
		return "qms/system/user/edit";
	}
	
	
	@RequestMapping("/toResetPwd")
	public String toResetPwd( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		SysMesUser user = sysUserService.selectByPrimaryKey(id);
		
		model.addAttribute("user", user);
		return "qms/system/user/resetPassword";
	}
	
	@RequestMapping("/toModifyPwd")
	public String toModifyPwd( @RequestParam(value = "id", required = false) Long id,
		Model model)
	{
		return "qms/system/user/modifyPassword";
	}
	 
	@RequestMapping("/modifyPwd")
	public ModelAndView modifyPwd(SysMesUser user, Model model)
	{
		try
		{
			SysMesUser u = sysUserService.selectByPrimaryKey(user.getUserKey());
			if(u == null)
			{
				return ajaxDoneError("用户不存在");
			}
			
			user.setPassword(MD5.md5(user.getPassword()));
			
			if(!u.getPassword().equals(user.getPassword()))
			{
				return ajaxDoneError("原密码错误，请重新输入");
			}
			
			user.setNewPassword(MD5.md5(user.getNewPassword()));
			u.setPassword(user.getNewPassword());
			
			sysUserService.updateByPrimaryKeySelective(u);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return ajaxDoneError("保存错误");
		}
		return ajaxDoneSuccess("密码修改成功");
	}	
	
	
	@RequestMapping("/saveUserGroup")
	public ModelAndView saveUserGroup(@RequestParam(value = "userId", required = false) Long userId,
		@RequestParam(value = "groupId", required = false) List<Long> groupIds,Model model)
	{
		try
		{
			//sysGroupService.updateUserGroup(userId, groupIds);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			return ajaxDoneError("保存错误");
		}
		return ajaxDoneSuccess("保存成功");
	}
	
	@RequestMapping("/resetPwd")
	public ModelAndView resetPwd(  @RequestParam(value = "password", required = false) String password,
			
		@RequestParam(value = "confirmPassword", required = false) String confirmPassword,
		@RequestParam(value = "id", required = false) Long id, Model model)
	{
		if(TmStringUtils.isNotEmpty(password) || TmStringUtils.isNotEmpty(confirmPassword)
			|| !password.equals(confirmPassword))
		{
			return ajaxDoneError("密码和确认密码不一致");
		}
		SysMesUser user = new SysMesUser();
			password = MD5.md5(password);
			user.setPassword(password);
			sysUserService.updateByPrimaryKeySelective(user);
			return ajaxDoneSuccess("密码重置成功");
		
	}
	
	@RequestMapping("/update")
	public ModelAndView update(SysMesUser user)
	{
		sysUserService.updateByPrimaryKeySelective(user);
		
		return ajaxDoneSuccess("修改成功");
	}
	
	
	@RequestMapping("/delete")
	public ModelAndView delete(SysMesUser user)
	{
		sysUserService.deleteByPrimaryKey(Long.parseLong(user.getUserKey().toString()));
		return ajaxDoneSuccess("删除成功");
	}*/
}
