package com.peg.dao;
import java.util.List;
import com.peg.dao.interceptor.BaseSearch;
import com.peg.model.VoiceOfCustomer;;
public interface VoiceOfCustomerMapper {
	 List<VoiceOfCustomer> findALLByPage(BaseSearch bs);

	List<VoiceOfCustomer> findALL(BaseSearch bs);
}
