package bbq.tb.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbq.tb.entity.Ba;
import bbq.tb.entity.R;
import bbq.tb.entity.Relation;
import bbq.tb.entity.User;
import bbq.tb.mapper.BaMapper;
import bbq.tb.mapper.BmanagerMapper;
import bbq.tb.mapper.RelationMapper;
import bbq.tb.mapper.SmanagerMapper;

@Service
public class BaServiceimpl implements BaService {

	@Autowired
	BaMapper bamapper;
	@Autowired
	RelationMapper relationmapper;
	@Autowired
	SmanagerMapper smanagerMapper;
	@Autowired
	BmanagerMapper bmanagerMapper;

	@Override
	public int save(String bname) {
		// TODO Auto-generated method stub
		int exr = 0;
		try {
			if (bamapper.ba_isexist(bname) == 0) {

				exr = bamapper.save(bname);
				System.out.println("建吧成功...");
			}

		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("建吧失败");
		}
		return exr;
	}

	@Override
	public int attent(Relation relation) {
		// TODO Auto-generated method stub
		int exr = 0;

		try {
			if (relationmapper.tieCountBymine2(relation.getBname(), relation.getUname()).equals("0")) {
				relationmapper.save(relation);
				exr = 1;
				System.out.println("关注成功...");
			} else {
				if (relationmapper.tieCountBymine(relation.getBname(), relation.getUname()).equals("0")) {
					relationmapper.saveAttent(relation);
					exr = 1;
					System.out.println("关注成功...");
				} else {
					relationmapper.removeAttent(relation);
					exr = -1;
					System.out.println("取消关注成功...");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("关注贴吧失败");
		}
		return exr;
	}

	@Override
	public String selectBa(String bname) {
		// TODO Auto-generated method stub
		Ba[] balist = null;
		try {
			balist = bamapper.selectBa(bname);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer sb = new StringBuffer();
		sb.append('[');
		try {
			for (Ba ba : balist) {
				sb.append('{').append("\"bid\":").append("\"" + ba.getBid() + "\"").append(",");
				sb.append("\"bname\":").append("\"" + ba.getBname() + "\"").append(",");
				sb.append("\"url\":").append("\"" + ba.getUrl() + "\"");
				sb.append('}').append(",");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');

		return new String(sb);
	}

	@Override
	public String selectGrade(Relation relation) {
		// TODO Auto-generated method stub
		String grade = "0";
		try {
			grade = relationmapper.selectGrade(relation);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return grade;
	}

	@Override
	public String attentBaBy_user(String username) {
		// TODO Auto-generated method stub

		Relation[] relationlist = null;
		try {
			relationlist = relationmapper.attentBaBy_user(username);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		StringBuffer sb = new StringBuffer();
		sb.append('[');
		try {
			for (Relation relation : relationlist) {
				sb.append('{').append("\"xid\":").append("\"" + relation.getXid() + "\"").append(",");
				sb.append("\"bname\":").append("\"" + relation.getBname() + "\"").append(",");
				sb.append("\"uname\":").append("\"" + relation.getUname() + "\"").append(",");
				sb.append("\"grade\":").append("\"" + relation.getGrade() + "\"").append(",");
				sb.append("\"url\":").append("\"" + relation.getUrl() + "\"");
				sb.append('}').append(",");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');

		return new String(sb);
	}

	@Override
	public String baThreeCount(String bname, String uname) {
		// TODO Auto-generated method stub
		return relationmapper.userCountByba(bname) + "&" + bamapper.tieCountByba(bname) + "&"
				+ relationmapper.tieCountBymine(bname, uname);
	}

	@Override
	public void downimage(String url, HttpServletResponse response) {
		// TODO Auto-generated method stub
		FileInputStream in;
		try {
			in = new FileInputStream(url);
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
	public String serchSb(String bname) {
		// TODO Auto-generated method stub
		User[] userlist = null;
		String exr = null;
		try {
			userlist = smanagerMapper.serchSb(bname);
			StringBuffer sb = new StringBuffer();
			sb.append('[');
			for (User user : userlist) {
				sb.append('{').append("\"uname\":").append("\"" + user.getUsername() + "\"").append(",");
				sb.append("\"url\":").append("\"" + user.getUrl() + "\"");
				sb.append('}').append(",");
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
	public String serchDb(String bname) {
		// TODO Auto-generated method stub
		User[] userlist = null;
		String exr = null;
		try {
			userlist = bmanagerMapper.serchDb(bname);
			StringBuffer sb = new StringBuffer();
			for (User user : userlist) {
				sb.append(user.getUsername() + "&");
			}
			if (userlist.length>0) {
				sb.deleteCharAt(sb.length()-1);
			}
			return new String(sb);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询失败");
		}

		return exr;
	}

}
