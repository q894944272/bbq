package bbq.tb.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbq.tb.entity.Bmanager;
import bbq.tb.entity.Recharge;
import bbq.tb.entity.Relation;
import bbq.tb.entity.Tie;
import bbq.tb.entity.User;
import bbq.tb.mapper.BmanagerMapper;
import bbq.tb.mapper.RechargeMapper;
import bbq.tb.mapper.RelationMapper;
import bbq.tb.mapper.SmanagerMapper;
import bbq.tb.mapper.UserMapper;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserMapper usermapper;
	@Autowired
	RelationMapper relationmapper;
	@Autowired
	BmanagerMapper bmanagermapper;
	@Autowired
	SmanagerMapper smanagermapper;
	@Autowired
	RechargeMapper rechargeMapper;

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		int exr = 0;
		try {
			exr = usermapper.save(user);

		} catch (Exception e) {
			System.out.println("插入失败");
		}
		return exr;
	}

	@Override
	public String login(User user) {
		// TODO Auto-generated method stub
		User user2 = null;
		String exr = null;
		try {
			user2 = usermapper.login(user);
			StringBuffer sb = new StringBuffer();
			sb.append('{').append("\"username\":").append("\"" + user2.getUsername() + "\"").append(",");
			sb.append("\"password\":").append("\"" + user2.getPassword() + "\"").append(",");
			sb.append("\"sex\":").append("\"" + user2.getSex() + "\"").append(",");
			sb.append("\"say\":").append("\"" + user2.getSay() + "\"");
			sb.append('}');
			exr = sb.toString();
		} catch (Exception e) {
			System.out.println("登陆失败");
		}
		return exr;
	}

	@Override
	public void downimage(String username, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String image = null;
		image = usermapper.downimage(username);
		FileInputStream in;
		try {
			in = new FileInputStream(image);
			int a;
			OutputStream out2 = response.getOutputStream();
			while ((a = in.read()) != -1) {
				out2.write(a);
			}
			out2.close();
			in.close();
		} catch (FileNotFoundException e) {
			System.out.print("服务器图片丢失 ");
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (IOException e) {
			System.out.println("图片传送失败");
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (Exception e) {
			System.out.print("无图片 ");
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Override
	public boolean uploadimage(String username, HttpServletRequest request) {
		// TODO Auto-generated method stub

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		String tempdir = request.getSession().getServletContext().getRealPath("/WEB-INF");
		diskFileItemFactory.setRepository(new File(tempdir));

		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		upload.setHeaderEncoding("UTF-8");
		upload.setFileSizeMax(1024 * 1024 * 1024);
		upload.setSizeMax(1024 * 1024 * 1024 * 2);

		boolean bool = ServletFileUpload.isMultipartContent(request);
		if (bool) {
			String sPath = request.getSession().getServletContext().getRealPath("/WEB-INF");
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(new ServletRequestContext(request));
				for (FileItem item : items) {
					if (item.isFormField()) {

					} else {
						String fileName = item.getName();
						InputStream in = item.getInputStream();
						System.out.println(sPath + "/" + fileName);
						OutputStream out = new FileOutputStream(sPath + "/" + fileName);
						byte b[] = new byte[1024];
						int len = -1;
						while ((len = in.read(b)) != -1) {
							out.write(b, 0, len);
						}
						in.close();
						out.close();
						item.delete();

						int exr = 0;
						exr = usermapper.uploadimage(sPath + "/" + fileName, username);
						if (exr > 0) {
							return true;
						}

					}
				}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return false;
	}

	@Override
	public String userCountList(String style, String username) {
		// TODO Auto-generated method stub
		User[] userlist = null;
		Relation[] relations = null;
		String exr = null;
		try {
			if ("1".equals(style)) {
				userlist = usermapper.serchUserlist(username);
			} else if ("2".equals(style)) {
				userlist = usermapper.serchUserlist2(username);
			} else {
				relations = relationmapper.attentBaBy_user(username);
			}

			StringBuffer sb = new StringBuffer();
			sb.append('[');

			if ("1".equals(style)) {
				for (User user : userlist) {
					sb.append('{').append("\"url\":").append("\"" + user.getUrl() + "\"").append(",");
					sb.append("\"concerned\":").append("\"" + user.getConcerned() + "\"").append(",");
					sb.append("\"say\":").append("\"" + user.getSay() + "\"");
					sb.append('}').append(",");
				}
			} else if ("2".equals(style)) {
				for (User user : userlist) {
					sb.append('{').append("\"url\":").append("\"" + user.getUrl() + "\"").append(",");
					sb.append("\"concerned\":").append("\"" + user.getNoticer() + "\"").append(",");
					sb.append("\"say\":").append("\"" + user.getSay() + "\"");
					sb.append('}').append(",");
				}
			} else {
				for (Relation relation : relations) {
					sb.append('{').append("\"xid\":").append("\"" + relation.getXid() + "\"").append(",");
					sb.append("\"bname\":").append("\"" + relation.getBname() + "\"").append(",");
					sb.append("\"uname\":").append("\"" + relation.getUname() + "\"").append(",");
					sb.append("\"grade\":").append("\"" + relation.getGrade() + "\"");
					sb.append('}').append(",");
				}
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(']');
			return new String(sb);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询失败");
		}

		return exr;
	}

	@Override
	public int isSign(Relation relation) {
		// TODO Auto-generated method stub
		int k = -1;
		try {
			k = relationmapper.isSign(relation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return k;
	}

	@Override
	public int sign(Relation relation) {
		// TODO Auto-generated method stub
		int k = -1;
		int k2 = -1;
		try {
			k2 = relationmapper.isSign(relation);
		} catch (Exception e1) {
			// TODO Auto-generated catch block

		}
		try {
			if (k2 == -1) {
				relationmapper.signFirst(relation);
				k = 6;
			} else if (k2 == 0) {
				k = -3;
			} else if (k2 == 1) {
				relationmapper.signCon(relation);
				k = relationmapper.getconday(relation);
				if (k < 7) {
					k = 5 + k;
				} else {
					k = 12;
				}
			} else if (k2 > 1) {
				relationmapper.signIncon(relation);
				k = 6;
			}
			k*=relationmapper.getbei(relation);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}

	@Override
	public String getSign(Relation relation) {
		// TODO Auto-generated method stub
		Relation relation2 = null;
		String exr = null;
		try {
			relation2 = relationmapper.getSign(relation);
			StringBuffer sb = new StringBuffer();
			sb.append(relation2.getSign_total()).append("&");
			sb.append(relation2.getLast_sign_time()).append("&");
			sb.append(relation2.getContinuty_day()).append("&");
			sb.append(relation2.getBreak_day()).append("&");
			sb.append(relation2.getLast_con_day());

			return new String(sb);

		} catch (Exception e) {
			System.out.println(relation.getUname() + "查询补签情况失败");
		}

		return null;
	}

	@Override
	public String apply(Bmanager bmanager) {
		// TODO Auto-generated method stub
		String str = "未知错误";
		Bmanager bmanager2;
		try {
			bmanager2 = bmanagermapper.serch(bmanager);
			if (bmanager2 == null) {
				bmanagermapper.apply(bmanager);
				str = "亲爱的用户！您的" + bmanager.getBname() + "吧吧主申请成功！请耐心等待BBQ贴吧管理组审核！";
			} else {
				if (bmanager2.getIs_exa() == 1) {
					str = "恭喜您！您已是" + bmanager.getBname() + "吧吧主！";
				} else if (bmanager2.getIs_exa() == 0) {
					str = "您的" + bmanager.getBname() + "吧吧主申请正在审核中～～～请耐心等待BBQ贴吧管理组审核！";
				} else if (bmanager2.getIs_exa() == -1) {
					if (bmanager2.getAeDay() < 30) {
						str = "您的" + bmanager.getBname() + "吧吧主申请被驳回！请在" + (30 - bmanager2.getAeDay())
								+ "天后再次申请～驳回理由如下：" + bmanager2.getExa_respon();
					} else {
						bmanagermapper.upapply(bmanager);
						str = "亲爱的用户！您的" + bmanager.getBname() + "吧吧主再次申请成功！请耐心等待BBQ贴吧管理组审核！";
					}

				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block

		}

		return str;
	}

	@Override
	public String applyS(Bmanager bmanager) {
		// TODO Auto-generated method stub
		String str = "未知错误";
		Bmanager bmanager2;
		try {
			bmanager2 = smanagermapper.serch(bmanager);
			if (bmanager2 == null) {
				smanagermapper.apply(bmanager);
				str = "亲爱的用户！您的" + bmanager.getBname() + "吧小吧主申请成功！请耐心等待吧主审核！";
			} else {
				if (bmanager2.getIs_exa() == 1) {
					str = "恭喜您！您已是" + bmanager.getBname() + "吧小吧主！";
				} else if (bmanager2.getIs_exa() == 0) {
					str = "您的" + bmanager.getBname() + "吧小吧主申请正在审核中～～～请耐心等待吧主审核！";
				} else if (bmanager2.getIs_exa() == -1) {
					if (bmanager2.getAeDay() < 7) {
						str = "您的" + bmanager.getBname() + "吧小吧主申请被" + bmanager2.getExa_name() + "驳回！请在"
								+ (7 - bmanager2.getAeDay()) + "天后再次申请～驳回理由如下：" + bmanager2.getExa_respon();
					} else {
						smanagermapper.upapply(bmanager);
						str = "亲爱的用户！您的" + bmanager.getBname() + "吧吧主再次申请成功！请耐心等待吧主审核！";
					}

				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block

		}

		return str;
	}
	// @Override
	// public String userinfo(User user) {
	// // TODO Auto-generated method stub
	// User user2 = null;
	// String exr = null;
	// try {
	// user2 = usermapper.userinfo(user);
	// StringBuffer sb = new StringBuffer();
	// sb.append('{').append("\"username\":").append("\""+user2.getUsername()+"\"").append(",");
	// sb.append("\"password\":").append("\""+user2.getPassword()+"\"").append(",");
	// sb.append("\"sex\":").append("\""+user2.getSex()+"\"").append(",");
	// sb.append("\"say\":").append("\""+user2.getSay()+"\"");
	// sb.append('}');
	// exr = sb.toString();
	// } catch (Exception e) {
	// System.out.println("登陆失败");
	// }
	// return exr;
	// }

	@Override
	public String yue(String username) {
		// TODO Auto-generated method stub
//			User user = usermapper.serchUser(username);
			String yue = usermapper.getyue(username)+"";
			String svip = usermapper.getsvp(username);
			
		return  yue + "&" + svip;
	}

	@Override
	public String rechc(Recharge recharge) {
		// TODO Auto-generated method stub
		String str = "未知错误";
		Recharge recharge2;
		try {
			recharge2 = rechargeMapper.serchcard(recharge);
			if (recharge2 == null) {
				str = "您输入的卡密不存在！请仔细核验！";
			} else if(recharge2.getUname()!=null){
				str = "您输入的卡密已使用！！";
			} else {
				recharge.setMoney_sum(recharge2.getMoney_sum());
				usermapper.addyue(recharge);
				rechargeMapper.upcard(recharge);
				str = "恭喜您！您成功充值"+recharge.getMoney_sum()*100+"BBQ豆!";
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block

		}

		return str;
	}

	@Override
	public String openSV(String username, String ytype) {
		// TODO Auto-generated method stub
		String str = "未知错误";
		int yue = usermapper.getyue(username);
		int consu = 0;
		int day = 0;
		try {
			if("1".equals(ytype)){
				consu = 18000;
				day = 372;
			}else if("2".equals(ytype)){
				consu = 17000;
				day = 186;
			}else if("3".equals(ytype)){
				consu = 8800;
				day = 93;
			}else if("4".equals(ytype)){
				consu = 3000;
				day = 31;
			}
			if(consu>yue){
				str = "余额不足,请前往商城充值";
			}else {
				usermapper.changgeyue(username,-consu);
				usermapper.changgevip(username,day);
				str = "恭喜成功开通"+day+"天超级VIP" ;
				str += " 日期至:" + usermapper.getsvp(username);
				str += " 余额:"+usermapper.getyue(username);
				usermapper.upcard(username,1,10);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}

		
		
		return str;
	}

	@Override
	public String openSV(Relation vip) {
		// TODO Auto-generated method stub
		String str = "未知错误";
		int yue = usermapper.getyue(vip.getUname());
		int consu = 0;
		int day = 0;
		try {
			if("1".equals(vip.getYtype())){
				consu = 3600;
				day = 372;
			}else if("2".equals(vip.getYtype())){
				consu = 3300;
				day = 186;
			}else if("3".equals(vip.getYtype())){
				consu = 1700;
				day = 93;
			}else if("4".equals(vip.getYtype())){
				consu = 600;
				day = 31;
			}
			if(consu>yue){
				str = "余额不足,请前往商城充值";
			}else {
				usermapper.changgeyue(vip.getUname(),-consu);
				vip.setVipday(day);
				relationmapper.changgevip(vip);
				str = "恭喜成功开通"+day+"天单吧VIP" ;
				str += " 日期至:" + relationmapper.getvip(vip);
				str += " 余额:"+usermapper.getyue(vip.getUname());
				usermapper.upcard(vip.getUname(),0,6);
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}

		
		
		return str;
	}

	@Override
	public String getvip(Relation relation) {
		// TODO Auto-generated method stub
		return relationmapper.getvip(relation);
	}

	@Override
	public String signall(String username) {
		// TODO Auto-generated method stub
		String exr = "未知错误";
		int sum = 0;
		int k = -1;
		int k2 = -1;
		int count = 0;
		String issvp = "0";
		Relation[] relationlist = null;
		try {
			issvp = usermapper.getsvp(username);
			if("未开通".equals(issvp)||"已到期".equals(issvp)){
				exr = "您不是——超级vip～不能使用一键签到";
				return exr;
			}
			exr = "未知错误1";
			relationlist = relationmapper.attentBaBy_user(username);			
			for (Relation relation : relationlist) {
				k=0;
				exr = "未知错误2";
				
				try {
					k2 = relationmapper.isSign(relation);
				} catch (Exception e1) {
					k2=-1;
				}
				if (k2 == -1) {
					exr = "未知错误3";
					relationmapper.signFirst(relation);
					k = 6;
					count++;
				} else if (k2 == 0) {
					k = 0;
				} else if (k2 == 1) {
					relationmapper.signCon(relation);
					exr = "未知错误4";
					k = relationmapper.getconday(relation);
					if (k < 7) {
						k = 5 + k;
					} else {
						k = 12;
					}
					count++;
				} else if (k2 > 1) {
					exr = "未知错误5";
					relationmapper.signIncon(relation);
					k = 6;
					count++;
				}
				sum+=k;
			}
			sum*=6;
			exr = "尊敬的超级vip！已为您一键签到"+count+"个吧~"+"总计获得"+sum+"经验";
		} catch (Exception e1) {
			
		}

		return exr;
	}

	@Override
	public String getcard(String username) {
		// TODO Auto-generated method stub
		int card = usermapper.getcard(username);
		int scard = usermapper.getscard(username);
		return  card + "&" + scard;
	}

	@Override
	public String sup1(String uname, String bname, String rep_card) {
		// TODO Auto-generated method stub
		Relation relation = new Relation();
		relation.setBname(bname);
		relation.setUname(uname);
		
		Relation relation2 = null;
		String exr = "未知错误";
		int card;
		int use;
		
		try {
			relation2 = relationmapper.getSign(relation);
			exr = "查询补签情况失败";
			
			use = Integer.parseInt(rep_card);
			
			card = usermapper.getcard(uname);
			if(use==0){
				exr = "您没有使用补签卡!!!请调整!!!";
				return exr;
			}else if(card<use){
				exr = "补签卡数量不够!!!请调整!!!";
				return exr;
			}else if(use>relation2.getBreak_day()){
				exr = "使用过多补签卡!!!请调整!!!";
				return exr;
			}else if(use == relation2.getBreak_day()) {
				usermapper.upcard(uname,0,-use);
				relationmapper.supsig(uname,bname,use);
				exr = "补签成功！您使用了"+use+"张补签卡～，目前不再需要补签";
			}else {
				usermapper.upcard(uname,0,-use);
				relationmapper.supsig2(uname,bname,use);
				exr = "补签成功！您使用了"+use+"张补签卡～,还可继续补签";
				
			}
//			relation2.getSign_total()      /签到总数
//			relation2.getLast_sign_time()  /上次签到时间
//			relation2.getContinuty_day())  /连续签到
//			relation2.getBreak_day())      /断签
//			relation2.getLast_con_day())   /上次连续签到


		} catch (Exception e) {
			
		}
		return exr;
	}
	
	@Override
	public String sup2(Relation relation) {
		// TODO Auto-generated method stub
		Relation relation2 = null;
		String exr = "未知错误";
		int card;
		
		try {
			exr = "查询补签情况失败";
			relation2 = relationmapper.getSign(relation);
			
			exr = "获取补签卡失败";
			card = usermapper.getscard(relation.getUname());
			if(card==0){
				exr = "您没有超能补签卡!!!请前往开通超级VIP!!!";
				return exr;
			}else {
				usermapper.upcard(relation.getUname(),-1,0);
				if(relation2.getSign_total()==relation2.getContinuty_day()){
					relationmapper.upgrade(relation.getBname(),relation.getUname(),500);
					exr = "您使用了1张超能补签卡～已为您增加该吧500经验";
				}else{
					relationmapper.supsig3(relation);
					exr = "补签成功！您使用了1张超能补签卡～,再次使用可以提升500经验";
				
				}
			}
//			relation2.getSign_total()      /签到总数
//			relation2.getLast_sign_time()  /上次签到时间
//			relation2.getContinuty_day())  /连续签到
//			relation2.getBreak_day())      /断签
//			relation2.getLast_con_day())   /上次连续签到


		} catch (Exception e) {
			e.printStackTrace();
		}
		return exr;
	}

}
