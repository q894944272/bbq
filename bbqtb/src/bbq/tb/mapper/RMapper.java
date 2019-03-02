package bbq.tb.mapper;

import bbq.tb.entity.R;

public interface RMapper {

	R[] serchr(String uname)throws Exception;

	R[] serchr2(int tid, String bname)throws Exception;

	int save(R r)throws Exception;
	
	int deleter(int rid)throws Exception;

}
