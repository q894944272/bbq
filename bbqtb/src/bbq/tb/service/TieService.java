package bbq.tb.service;

import javax.servlet.http.HttpServletRequest;

import bbq.tb.entity.Tie;
import bbq.tb.entity.Zorcc;

public interface TieService {

	String serchtie(String style, HttpServletRequest request);

	int save(Tie tie, HttpServletRequest request);

	int zorc(Zorcc zorcc);

}
