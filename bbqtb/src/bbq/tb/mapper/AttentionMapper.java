package bbq.tb.mapper;

import bbq.tb.entity.Attention;

public interface AttentionMapper {

	int insert_atention(Attention attention)throws Exception;

	int delete_atention(Attention attention)throws Exception;

	String countByNoticer(String uname);

	String countByConcerned(String uname);

	String isCanAttention(String uname2, String uname);

}
