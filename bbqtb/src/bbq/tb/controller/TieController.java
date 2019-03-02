package bbq.tb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bbq.tb.entity.Tie;
import bbq.tb.entity.Zorcc;
import bbq.tb.service.TieService;

@Controller
public class TieController {
	
	@Autowired
	TieService tieService;
	
	@RequestMapping(value ="tielist.do")
	@ResponseBody
	public String tielist(String style,HttpServletRequest request,Model model){
		return tieService.serchtie(style,request);
	}
	@RequestMapping(value ="subtie.do",method=RequestMethod.POST)
	@ResponseBody
	public String subtie(Tie tie,HttpServletRequest request){
		if(tieService.save(tie,request)>2){
			return "true";
		}
		
		return "false";
	}
	
	@RequestMapping(value ="zorc.do",method=RequestMethod.POST)
	@ResponseBody
	public String zorc(Zorcc zorcc){
		if("".equals(zorcc.getUname())||zorcc.getUname()==null){
			return "-2";
		}
		int result = tieService.zorc(zorcc);
		return result+"";
	}
}