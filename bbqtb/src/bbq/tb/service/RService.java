package bbq.tb.service;

import javax.servlet.http.HttpServletRequest;

import bbq.tb.entity.R;

public interface RService {

	String serchr(HttpServletRequest request);

	String serchr2(HttpServletRequest request);
	
	int save(R r);

	int deleter(int rid);

}
