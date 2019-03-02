package bbq.tb.service;

import javax.servlet.http.HttpServletResponse;

import bbq.tb.entity.Relation;

public interface BaService {

	int save(String bname);

	int attent(Relation relation);

	String selectBa(String bname);

	String selectGrade(Relation relation);

	String attentBaBy_user(String username);

	String baThreeCount(String bname, String uname);

	void downimage(String url, HttpServletResponse response);

	String serchSb(String bname);

	String serchDb(String bname);

}
