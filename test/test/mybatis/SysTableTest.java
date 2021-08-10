package test.mybatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peg.dao.interceptor.BaseSearch;
import com.peg.dao.interceptor.PageParameter;
import com.peg.model.DownLineTotal;
import com.peg.model.system.SysOperateLog;
import com.peg.service.DownLineTotalServiceI;
import com.peg.service.FaultReasonServiceI;
//import com.peg.model.SysUser;
import com.peg.service.system.SysGroupServiceI;
import com.peg.service.system.SysOperateLogServiceI;
import com.peg.service.system.SysPrivilegeServiceI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{ "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class SysTableTest
{
	@Autowired
	private SysPrivilegeServiceI sysPrivilegeService;
	
	@Autowired
	private SysGroupServiceI sysGroupService;
	@Autowired
	private FaultReasonServiceI faultReasonService;
	@Autowired
	private DownLineTotalServiceI downLineSerivce;
	
	
//	
//	@Autowired
//	private SysUserServiceI sysUserService;
	
	@Autowired
	private SysOperateLogServiceI operatorLogService;
	@Autowired
	public void one(){
		DownLineTotal total = new DownLineTotal();
		int sum = downLineSerivce.sumDownLine(total);
		
		System.out.println(sum+"============");
		
	}
	

	@Test
	public void testAddPrivilege()
	{
		BaseSearch bs = new BaseSearch();
		bs.setPage(new PageParameter());
		//faultReasonService.getAllByPage(faultReason, page);
		//SysPrivilege plg = new SysPrivilege();
		/*plg.setDescription("desc11");
		plg.setMenuUrl("url");
		plg.setPrivilegeCode("tttt:ttyy");
		plg.setPrivilegeName("测试权限");
		plg.setPrivilegeType(ConstantInterface.PRIVILEGE_TYPE_OPERATION);*/
		
		//sysPrivilegeService.insert(plg);
	}
	
//	@Test
//	public void testUpdateUser()
//	{
//		SysUser user = new SysUser();
//		user.setId(400l);
//		user.setName("管理员哦");
//		user.setEmail("fff@126.com");
//		user.setPhone("12355454");
//		
//		sysUserService.updateByPrimaryKeySelective(user);
//	}
//	@Test
//	public void testAddUser()
//	{
//		SysUser user = new SysUser();
//		user.setUsername("admin");
//		user.setPassword("123456");
//		user.setEmail("fff@126.com");
//		user.setPhone("12355454");
//		sysUserService.insert(user);
//	}
//	
//	@Test
//	public void testAddManyUser()
//	{
//		for(int i=0; i<198; i++)
//		{
//			SysUser user = new SysUser();
//			user.setUsername("user" + i);
//			user.setPassword("123456");
//			user.setName("测试用户"+i);
//			user.setEmail("fff@126.com");
//			user.setPhone("12355454");			
//			sysUserService.insert(user);
//		}
//	}
	
	/*@Test
	public void testAddGroup()
	{
		SysGroup group = new SysGroup();
		group.setDescription("组描述");
		group.setName("组A");
		sysGroupService.insert(group);
	}
	@Test
	public void testAddGroupSel()
	{
		SysGroup group = new SysGroup();
		group.setDescription("组描述cde");
		group.setName("组Abc");
		sysGroupService.insertSelective(group);
	}*/
	
	@Test
	public void testAddOptLog()
	{
		SysOperateLog log = new SysOperateLog();
		log.setOperator("deno");
		log.setContent("删除了记录Abcd");
		log.setOpType((short)1);
//		operatorLogService.insertSelective(log);
		operatorLogService.insert(log);
	}
//	@Test
//	public void testGetUser()
//	{
//		SysUser user = sysUserService.selectByUsername("deno");
//		System.out.println(user);
//	}
	
	
	
	
}
