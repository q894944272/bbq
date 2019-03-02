package bbq.tb.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bbq.tb.entity.Attention;
import bbq.tb.entity.Bmanager;
import bbq.tb.entity.Recharge;
import bbq.tb.entity.Relation;
import bbq.tb.entity.User;
import bbq.tb.service.AttentionService;
import bbq.tb.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	AttentionService attentionService;
	
	@RequestMapping(value ="register.do",method=RequestMethod.POST)
	@ResponseBody
	public String insertUser(User user,Model model){
		if(userService.save(user) > 0){
			return "true";
		}
		return "false";
	}
	@RequestMapping(value ="login.do",method=RequestMethod.POST)
	@ResponseBody
	public String loginUser(User user,Model model){
		
		String loginmessage = userService.login(user);
		if(loginmessage != null){
			return loginmessage;
		}
		return "false";
	}
	
	
	@RequestMapping(value ="downimage.do")
	public void downimage(String username,Model model,HttpServletResponse response){
		
//		try {
			userService.downimage(username,response);
//		} catch (Exception e) {
			// TODO Auto-generated catch block
//			System.out.println("º”‘ÿÕº∆¨ ß∞‹");
//			e.printStackTrace();
//		}
			
	}
	@RequestMapping(value ="uploadimage.do")
	@ResponseBody
	public String uploadimage(String username,HttpServletRequest request,Model model){
		
		boolean issuccess = userService.uploadimage(username,request);
		if(issuccess){
			return "true";
		}
		return "false";
	}
	
//	@RequestMapping(value ="userinfo.do",method=RequestMethod.POST)
//	@ResponseBody
//	public String userinfo(User user){
//		
//		String userinfos = userService.userinfo(user);
//		if(userinfos != null){
//			return userinfos;
//		}
//		return "false";
//	}
	@RequestMapping(value ="attention.do",method=RequestMethod.POST)
	@ResponseBody
	public String attention(String style,Attention attention){
		
		boolean issuccess = attentionService.attention(style,attention);
		if(issuccess){
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value ="userThreeCount.do",method=RequestMethod.POST)
	@ResponseBody
	public String userThreeCount(String uname,String uname2){
		return attentionService.baThreeCount(uname,uname2);
	}
	
	@RequestMapping(value ="userCountList.do",method=RequestMethod.POST)
	@ResponseBody
	public String userCountList(String style,String username){
		return userService.userCountList(style,username);
	}
	
	@RequestMapping(value ="isSign.do",method=RequestMethod.POST)
	@ResponseBody
	public String isSign(Relation relation){
		int k = userService.isSign(relation);
		return k+"";
	}
	@RequestMapping(value ="sign.do",method=RequestMethod.POST)
	@ResponseBody
	public String sign(Relation relation){
		int k = userService.sign(relation);
		return k+"";
	}
	@RequestMapping(value ="getSign.do",method=RequestMethod.POST)
	@ResponseBody
	public String getSign(Relation relation){
		 return userService.getSign(relation);
	}
	@RequestMapping(value ="apply.do",method=RequestMethod.POST)
	@ResponseBody
	public String apply(Bmanager bmanager){
		return userService.apply(bmanager);
		
	}
	
	@RequestMapping(value ="applyS.do",method=RequestMethod.POST)
	@ResponseBody
	public String applyS(Bmanager bmanager){
		return userService.applyS(bmanager);
		
	}
	@RequestMapping(value ="yue.do",method=RequestMethod.POST)
	@ResponseBody
	public String yue(String username){
		return userService.yue(username);
		
	}
	@RequestMapping(value ="rechc.do",method=RequestMethod.POST)
	@ResponseBody
	public String rechc(Recharge recharge){
		return userService.rechc(recharge);
		
	}
	@RequestMapping(value ="openSV.do",method=RequestMethod.POST)
	@ResponseBody
	public String openSV(String username,String ytype){
		return userService.openSV(username,ytype);
		
	}
	@RequestMapping(value ="openV.do",method=RequestMethod.POST)
	@ResponseBody
	public String openV(Relation vip){
		return userService.openSV(vip);
		
	}
	@RequestMapping(value ="getvip.do",method=RequestMethod.POST)
	@ResponseBody
	public String getvip(Relation relation){
		return userService.getvip(relation);
		
	}
	
	@RequestMapping(value ="signall.do",method=RequestMethod.POST)
	@ResponseBody
	public String signall(String uname){
		return userService.signall(uname);
	}
	@RequestMapping(value ="getcard.do",method=RequestMethod.POST)
	@ResponseBody
	public String getcard(String username){
		return userService.getcard(username);
		
	}
	@RequestMapping(value ="sup1.do",method=RequestMethod.POST)
	@ResponseBody
	public String sup1(String uname,String bname,String rep_card){
		return userService.sup1(uname,bname,rep_card);
		
	}
	@RequestMapping(value ="sup2.do",method=RequestMethod.POST)
	@ResponseBody
	public String sup2(Relation relation){
		return userService.sup2(relation);
		
	}
}
