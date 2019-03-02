package bbq.tb.controller;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bbq.tb.entity.Bmanager;
import bbq.tb.entity.Relation;
import bbq.tb.service.BaService;

//8713453443s
@Controller
public class BaController {
	@Autowired
	BaService baService;
	
	@RequestMapping(value ="creatBa.do",method=RequestMethod.POST)
	@ResponseBody
	public String creatBa(String bname){
		if(baService.save(bname)>0){
			return "true";
		}
		
		return "false";
	}
	@RequestMapping(value ="attentBa.do",method=RequestMethod.POST)
	@ResponseBody
	public String attentBa(Relation relation){
		int k = baService.attent(relation);
		if(k==0){
			return "false";
		}
		return k+"";
	}
	
	@RequestMapping(value ="selectBa.do",method=RequestMethod.POST)
	@ResponseBody
	public String selectBa(String bname){
		return baService.selectBa(bname);
	}
	
	@RequestMapping(value ="selectGrade.do",method=RequestMethod.POST)
	@ResponseBody
	public String selectGrade(Relation relation){
		return baService.selectGrade(relation);
	}
	
	@RequestMapping(value ="attentBaBy_user.do",method=RequestMethod.POST)
	@ResponseBody
	public String attentBaBy_user(String username){
		return baService.attentBaBy_user(username);
	}
	
	@RequestMapping(value ="baThreeCount.do")
	@ResponseBody
	public String baThreeCount(String bname,String uname){
		return baService.baThreeCount(bname,uname);
	}
	
	@RequestMapping(value ="downimage2.do")
	public void downimage(String url,HttpServletResponse response){
		baService.downimage(url,response);
	}
	@RequestMapping(value ="serchSb.do",method=RequestMethod.POST)
	@ResponseBody
	public String serchSb(String bname){
		return baService.serchSb(bname);
		
	}
	@RequestMapping(value ="serchDb.do",method=RequestMethod.POST)
	@ResponseBody
	public String serchDb(String bname){
		return baService.serchDb(bname);
		
	}
}