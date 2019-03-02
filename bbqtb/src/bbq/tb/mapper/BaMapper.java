package bbq.tb.mapper;

import bbq.tb.entity.Ba;
import bbq.tb.entity.Tie;

public interface BaMapper {

	int save(String bname)throws Exception;

	int ba_isexist(String bname)throws Exception;

	int upcount2(Tie tie)throws Exception;

	Ba[] selectBa(String bname)throws Exception;

	String tieCountByba(String bname);	
	
}
