package bbq.tb.mapper;

import bbq.tb.entity.Recharge;
import bbq.tb.entity.User;

public interface UserMapper {

	int save(User user)throws Exception;//	DataAccessException
	
	User login(User user)throws Exception;

	String downimage(String username);

	int uploadimage(String url, String username)throws Exception;

	User serchUser(String uname);
	
	User[] serchUserlist(String uname);
	
	User[] serchUserlist2(String uname);

	int getyue(String username);

	String getsvp(String username);

	void addyue(Recharge recharge2);

	void changgeyue(String username,int consu);

	void changgevip(String username, int day);

	int getcard(String username);

	int getscard(String username);

	void upcard(String username, int i, int j);
	
//	User userinfo(User user)throws Exception;
}
