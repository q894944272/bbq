package bbq.tb.mapper;

import bbq.tb.entity.R;
import bbq.tb.entity.Relation;
import bbq.tb.entity.Tie;

public interface RelationMapper {
	
	int save(Relation relation)throws Exception;
	
	int saveAttent(Relation relation)throws Exception;
	
	int removeAttent(Relation relation)throws Exception;
	
	int upcount(Tie tie)throws Exception;

	int upcount3(R r)throws Exception;

	String selectGrade(Relation relation)throws Exception;

	Relation[] attentBaBy_user(String username)throws Exception;

	String userCountByba(String bname);

	String tieCountBymine(String bname,String uname);
	
	String tieCountBymine2(String bname,String uname);

	String countByBa(String uname);

	int isSign(Relation relation)throws Exception;

	void signFirst(Relation relation)throws Exception;

	void signCon(Relation relation)throws Exception;

	void signIncon(Relation relation)throws Exception;

	int getconday(Relation relation)throws Exception;

	Relation getSign(Relation relation);

	void changgevip(Relation vip);

	String getvip(Relation vip);

	int getbei(Relation relation);

	void supsig(String uname, String bname, int use);

	void supsig2(String uname, String bname, int use);

	void supsig3(Relation relation);

	void upgrade(String bname, String uname, int i);
}
