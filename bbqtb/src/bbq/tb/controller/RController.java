package bbq.tb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bbq.tb.entity.R;
import bbq.tb.entity.Tie;
import bbq.tb.service.RService;



@Controller
public class RController {
	@Autowired
	RService rService;
	
	@RequestMapping(value ="rlist.do",method=RequestMethod.POST)
	@ResponseBody
	public String rlist(HttpServletRequest request){
		return rService.serchr(request);
	}
	@RequestMapping(value ="rlist2.do",method=RequestMethod.POST)
	@ResponseBody
	public String rlist2(HttpServletRequest request){
		return rService.serchr2(request);
	}
	
	@RequestMapping(value ="subr.do",method=RequestMethod.POST)
	@ResponseBody
	public String subr(R r,HttpServletRequest request){
		r.setR_content(request.getParameter("rcontent"));
		if(rService.save(r)>2){
			return "true";
		}
		
		return "false";
	}
	@RequestMapping(value ="deleter.do",method=RequestMethod.POST)
	@ResponseBody
	public String deleter(int rid){
		if(rService.deleter(rid)>0){
			return "true";
		}
		
		return "false";
	}
}
