//package test.mybatis;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.peg.dao.bph.GroupCategoryMapper;
//import com.peg.dao.bph.ItemMapper;
//import com.peg.dao.interceptor.BaseSearch;
//import com.peg.dao.interceptor.PageParameter;
//import com.peg.model.bph.GroupCategory;
//import com.peg.model.bph.Item;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring.xml", "classpath:spring-mybatis.xml"})
//public class TestCategory {
//
//	@Autowired
//	private GroupCategoryMapper groupCategoryMapper;
//	
//	
//	@Autowired
//	private ItemMapper itemMapper;
//	
//	@Test
//	public void testItem(){
//		PageParameter page = new PageParameter();
//		BaseSearch bs = new BaseSearch();
//		bs.setPage(page);
//		List<GroupCategory> list = groupCategoryMapper.getItemAllByPage(bs);
//		System.out.println(list.size());
//	}
//	
//	@Test
//	public void testIndex(){
//		PageParameter page = new PageParameter();
//		BaseSearch bs = new BaseSearch();
//		bs.setPage(page);
//		List<GroupCategory> list = groupCategoryMapper.getIndexAllByPage(bs);
//		System.out.println(list.size());
//	}
//	
//	@Test
//	public void testMonth(){
//		PageParameter page = new PageParameter();
//		BaseSearch bs = new BaseSearch();
//		bs.setPage(page);
//		List<GroupCategory> list = groupCategoryMapper.getMonthAllByPage(bs);
//		System.out.println(list.size());
//	}
//	
//	@Test
//	public void selteItem(){
//		Item item = itemMapper.selectByPrimaryKey(new Long(14));
//		System.out.println(item.getItemCode() +"  "+item.getItemName());
//	}
//	
//	@Test
//	public void getAllItem(){
//		List<Item> list = itemMapper.getAllItems("电器一厂", "喷涂车间", "喷涂班组");
//		for(Item item : list){
//			System.out.println(item.getItemName()+"--"+item.getItemCode());
//		}
//	}
//	
//	@Test
//	public void testMes(){
//		List<GroupCategory> list = groupCategoryMapper.getAllGroupCategoryFromMes();
//		System.out.println(list.size());
//	}
//}
