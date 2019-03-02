package bbq.tb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbq.tb.entity.Attention;
import bbq.tb.entity.User;
import bbq.tb.mapper.AttentionMapper;
import bbq.tb.mapper.RelationMapper;
import bbq.tb.mapper.UserMapper;

@Service
public class AttentionServiceimpl implements AttentionService {

	@Autowired
	AttentionMapper attentionmapper;
	@Autowired
	RelationMapper relationmapper ;
	@Autowired
	UserMapper usermapper;

	@Override
	public boolean attention(String style, Attention attention) {
		// TODO Auto-generated method stub
	
		try {
			if ("0".equals(style)) {
				attentionmapper.insert_atention(attention);
				return true;
			} else if ("1".equals(style)) {
				attentionmapper.delete_atention(attention);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;		
	}

	@Override
	public String baThreeCount(String uname, String uname2) {
		// TODO Auto-generated method stub
		User user = usermapper.serchUser(uname);
		
		return  attentionmapper.countByNoticer(uname)+"&"+
				attentionmapper.countByConcerned(uname)+"&"+
				relationmapper.countByBa(uname)+"&"+
				user.getSex()+"&"+
				user.getSay()+"&"+
			 	attentionmapper.isCanAttention(uname2,uname);
	}

}
