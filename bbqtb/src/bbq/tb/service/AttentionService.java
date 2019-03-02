package bbq.tb.service;

import bbq.tb.entity.Attention;

public interface AttentionService {

	boolean attention(String style, Attention attention);

	String baThreeCount(String uname, String uname2);

}
