package bbq.tb.service;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbq.tb.entity.Bmanager;
import bbq.tb.entity.Recharge;
import bbq.tb.entity.Relation;
import bbq.tb.entity.User;

public interface UserService {

	int save(User user);

	String login(User user);

	void downimage(String username, HttpServletResponse response);

	boolean uploadimage(String username, HttpServletRequest request) ;

	String userCountList(String style, String username);

	int isSign(Relation relation);

	int sign(Relation relation);

	String getSign(Relation relation);

	String apply(Bmanager bmanager);
	
	String applyS(Bmanager bmanager);

	String yue(String username);

	String rechc(Recharge recharge);

	String openSV(String username, String ytype);

	String openSV(Relation vip);

	String getvip(Relation relation);

	String signall(String username);

	String getcard(String username);

	String sup1(String uname, String bname, String rep_card);

	String sup2(Relation relation);
	
//	String userinfo(User user);


}
