package bbq.tb.mapper;

import bbq.tb.entity.R;
import bbq.tb.entity.Tie;
import bbq.tb.entity.Zorcc;

public interface TieMapper {

	Tie[] serchtie1(String ttime,String username)throws Exception;

	Tie[] serchtie2(String uname,String username)throws Exception;

//	Tie[] serchtie3(String bname,String username)throws Exception;

	Tie[] serchtie4(String uname,String username)throws Exception;

	int save(Tie tie)throws Exception;

	int upcount(R r)throws Exception;
	
	int upcount2(R r)throws Exception;

	void addz(Zorcc zorcc);
	
	void addc(Zorcc zorcc);
	
	void subz(Zorcc zorcc);
	
	void subc(Zorcc zorcc);

	Tie[] serchtie3(String bname, String username, String flag_time);

}
