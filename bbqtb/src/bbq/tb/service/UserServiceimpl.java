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
			System.out.println("����ʧ��");
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
			System.out.println("��½ʧ��");
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
			System.out.print("������ͼƬ��ʧ ");
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ͼƬ����ʧ��");
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (Exception e) {
			System.out.print("��ͼƬ ");
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
			System.out.println("��ѯʧ��");
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
			System.out.println(relation.getUname() + "��ѯ��ǩ���ʧ��");
		}

		return null;
	}

	@Override
	public String apply(Bmanager bmanager) {
		// TODO Auto-generated method stub
		String str = "δ֪����";
		Bmanager bmanager2;
		try {
			bmanager2 = bmanagermapper.serch(bmanager);
			if (bmanager2 == null) {
				bmanagermapper.apply(bmanager);
				str = "�װ����û�������" + bmanager.getBname() + "�ɰ�������ɹ��������ĵȴ�BBQ���ɹ�������ˣ�";
			} else {
				if (bmanager2.getIs_exa() == 1) {
					str = "��ϲ����������" + bmanager.getBname() + "�ɰ�����";
				} else if (bmanager2.getIs_exa() == 0) {
					str = "����" + bmanager.getBname() + "�ɰ���������������С����������ĵȴ�BBQ���ɹ�������ˣ�";
				} else if (bmanager2.getIs_exa() == -1) {
					if (bmanager2.getAeDay() < 30) {
						str = "����" + bmanager.getBname() + "�ɰ������뱻���أ�����" + (30 - bmanager2.getAeDay())
								+ "����ٴ����롫�����������£�" + bmanager2.getExa_respon();
					} else {
						bmanagermapper.upapply(bmanager);
						str = "�װ����û�������" + bmanager.getBname() + "�ɰ����ٴ�����ɹ��������ĵȴ�BBQ���ɹ�������ˣ�";
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
		String str = "δ֪����";
		Bmanager bmanager2;
		try {
			bmanager2 = smanagermapper.serch(bmanager);
			if (bmanager2 == null) {
				smanagermapper.apply(bmanager);
				str = "�װ����û�������" + bmanager.getBname() + "��С��������ɹ��������ĵȴ�������ˣ�";
			} else {
				if (bmanager2.getIs_exa() == 1) {
					str = "��ϲ����������" + bmanager.getBname() + "��С������";
				} else if (bmanager2.getIs_exa() == 0) {
					str = "����" + bmanager.getBname() + "��С����������������С����������ĵȴ�������ˣ�";
				} else if (bmanager2.getIs_exa() == -1) {
					if (bmanager2.getAeDay() < 7) {
						str = "����" + bmanager.getBname() + "��С�������뱻" + bmanager2.getExa_name() + "���أ�����"
								+ (7 - bmanager2.getAeDay()) + "����ٴ����롫�����������£�" + bmanager2.getExa_respon();
					} else {
						smanagermapper.upapply(bmanager);
						str = "�װ����û�������" + bmanager.getBname() + "�ɰ����ٴ�����ɹ��������ĵȴ�������ˣ�";
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
	// System.out.println("��½ʧ��");
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
		String str = "δ֪����";
		Recharge recharge2;
		try {
			recharge2 = rechargeMapper.serchcard(recharge);
			if (recharge2 == null) {
				str = "������Ŀ��ܲ����ڣ�����ϸ���飡";
			} else if(recharge2.getUname()!=null){
				str = "������Ŀ�����ʹ�ã���";
			} else {
				recharge.setMoney_sum(recharge2.getMoney_sum());
				usermapper.addyue(recharge);
				rechargeMapper.upcard(recharge);
				str = "��ϲ�������ɹ���ֵ"+recharge.getMoney_sum()*100+"BBQ��!";
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block

		}

		return str;
	}

	@Override
	public String openSV(String username, String ytype) {
		// TODO Auto-generated method stub
		String str = "δ֪����";
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
				str = "����,��ǰ���̳ǳ�ֵ";
			}else {
				usermapper.changgeyue(username,-consu);
				usermapper.changgevip(username,day);
				str = "��ϲ�ɹ���ͨ"+day+"�쳬��VIP" ;
				str += " ������:" + usermapper.getsvp(username);
				str += " ���:"+usermapper.getyue(username);
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
		String str = "δ֪����";
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
				str = "����,��ǰ���̳ǳ�ֵ";
			}else {
				usermapper.changgeyue(vip.getUname(),-consu);
				vip.setVipday(day);
				relationmapper.changgevip(vip);
				str = "��ϲ�ɹ���ͨ"+day+"�쵥��VIP" ;
				str += " ������:" + relationmapper.getvip(vip);
				str += " ���:"+usermapper.getyue(vip.getUname());
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
		String exr = "δ֪����";
		int sum = 0;
		int k = -1;
		int k2 = -1;
		int count = 0;
		String issvp = "0";
		Relation[] relationlist = null;
		try {
			issvp = usermapper.getsvp(username);
			if("δ��ͨ".equals(issvp)||"�ѵ���".equals(issvp)){
				exr = "�����ǡ�������vip������ʹ��һ��ǩ��";
				return exr;
			}
			exr = "δ֪����1";
			relationlist = relationmapper.attentBaBy_user(username);			
			for (Relation relation : relationlist) {
				k=0;
				exr = "δ֪����2";
				
				try {
					k2 = relationmapper.isSign(relation);
				} catch (Exception e1) {
					k2=-1;
				}
				if (k2 == -1) {
					exr = "δ֪����3";
					relationmapper.signFirst(relation);
					k = 6;
					count++;
				} else if (k2 == 0) {
					k = 0;
				} else if (k2 == 1) {
					relationmapper.signCon(relation);
					exr = "δ֪����4";
					k = relationmapper.getconday(relation);
					if (k < 7) {
						k = 5 + k;
					} else {
						k = 12;
					}
					count++;
				} else if (k2 > 1) {
					exr = "δ֪����5";
					relationmapper.signIncon(relation);
					k = 6;
					count++;
				}
				sum+=k;
			}
			sum*=6;
			exr = "�𾴵ĳ���vip����Ϊ��һ��ǩ��"+count+"����~"+"�ܼƻ��"+sum+"����";
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
		String exr = "δ֪����";
		int card;
		int use;
		
		try {
			relation2 = relationmapper.getSign(relation);
			exr = "��ѯ��ǩ���ʧ��";
			
			use = Integer.parseInt(rep_card);
			
			card = usermapper.getcard(uname);
			if(use==0){
				exr = "��û��ʹ�ò�ǩ��!!!�����!!!";
				return exr;
			}else if(card<use){
				exr = "��ǩ����������!!!�����!!!";
				return exr;
			}else if(use>relation2.getBreak_day()){
				exr = "ʹ�ù��ಹǩ��!!!�����!!!";
				return exr;
			}else if(use == relation2.getBreak_day()) {
				usermapper.upcard(uname,0,-use);
				relationmapper.supsig(uname,bname,use);
				exr = "��ǩ�ɹ�����ʹ����"+use+"�Ų�ǩ������Ŀǰ������Ҫ��ǩ";
			}else {
				usermapper.upcard(uname,0,-use);
				relationmapper.supsig2(uname,bname,use);
				exr = "��ǩ�ɹ�����ʹ����"+use+"�Ų�ǩ����,���ɼ�����ǩ";
				
			}
//			relation2.getSign_total()      /ǩ������
//			relation2.getLast_sign_time()  /�ϴ�ǩ��ʱ��
//			relation2.getContinuty_day())  /����ǩ��
//			relation2.getBreak_day())      /��ǩ
//			relation2.getLast_con_day())   /�ϴ�����ǩ��


		} catch (Exception e) {
			
		}
		return exr;
	}
	
	@Override
	public String sup2(Relation relation) {
		// TODO Auto-generated method stub
		Relation relation2 = null;
		String exr = "δ֪����";
		int card;
		
		try {
			exr = "��ѯ��ǩ���ʧ��";
			relation2 = relationmapper.getSign(relation);
			
			exr = "��ȡ��ǩ��ʧ��";
			card = usermapper.getscard(relation.getUname());
			if(card==0){
				exr = "��û�г��ܲ�ǩ��!!!��ǰ����ͨ����VIP!!!";
				return exr;
			}else {
				usermapper.upcard(relation.getUname(),-1,0);
				if(relation2.getSign_total()==relation2.getContinuty_day()){
					relationmapper.upgrade(relation.getBname(),relation.getUname(),500);
					exr = "��ʹ����1�ų��ܲ�ǩ������Ϊ�����Ӹð�500����";
				}else{
					relationmapper.supsig3(relation);
					exr = "��ǩ�ɹ�����ʹ����1�ų��ܲ�ǩ����,�ٴ�ʹ�ÿ�������500����";
				
				}
			}
//			relation2.getSign_total()      /ǩ������
//			relation2.getLast_sign_time()  /�ϴ�ǩ��ʱ��
//			relation2.getContinuty_day())  /����ǩ��
//			relation2.getBreak_day())      /��ǩ
//			relation2.getLast_con_day())   /�ϴ�����ǩ��


		} catch (Exception e) {
			e.printStackTrace();
		}
		return exr;
	}

}
