package bbq.tb.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbq.tb.entity.R;
import bbq.tb.mapper.RMapper;
import bbq.tb.mapper.RelationMapper;
import bbq.tb.mapper.TieMapper;

@Service
public class RServiceimpl implements RService {

	@Autowired
	RMapper rmapper;
	@Autowired
	TieMapper tiemapper;
	@Autowired
	RelationMapper relationMapper;
	
	@Override
	public String serchr(HttpServletRequest request) {
		// TODO Auto-generated method stub
		R[] rlist = null;

		String bname = request.getParameter("username");
		try {
			rlist = rmapper.serchr(bname);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sb = new StringBuffer();
		sb.append('[');
		try {
			for (R r : rlist) {
				String datastr = r.getRtime();
				Date data2;

				data2 = df.parse(datastr);

				Calendar calendar2 = Calendar.getInstance();
				calendar2.setTime(data2);
				if (calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)) {
					if (calendar.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)) {
						if (calendar.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY)) {
							if (calendar.get(Calendar.MINUTE) == calendar2.get(Calendar.MINUTE)) {
								datastr = "刚刚";
							} else {
								datastr = calendar.get(Calendar.MINUTE) - calendar2.get(Calendar.MINUTE) + "分钟前";
							}
						} else {
							datastr = calendar.get(Calendar.HOUR_OF_DAY) - calendar2.get(Calendar.HOUR_OF_DAY) + "小时前";
						}
					} else {
						datastr = datastr.substring(5, 10);
					}
				} else {
					datastr = datastr.substring(0, 10);
				}
				sb.append('{').append("\"uname\":").append("\"" + r.getUname() + "\"").append(",");
				sb.append("\"rtime\":").append("\"" + datastr + "\"").append(",");
				sb.append("\"rcontent\":").append("\"" + r.getR_content() + "\"").append(",");
				sb.append("\"tname\":").append("\"" + r.getTname() + "\"");
				sb.append('}').append(",");

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');

		return new String(sb);
	}

	@Override
	public String serchr2(HttpServletRequest request) {
		// TODO Auto-generated method stub
		R[] rlist = null;

		int tid = Integer.parseInt(request.getParameter("tid"));
		String bname = request.getParameter("bname");
		try {
			rlist = rmapper.serchr2(tid, bname);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sb = new StringBuffer();
		sb.append('[');
		try {

			for (R r : rlist) {
				String datastr = r.getRtime();
				Date data2 = df.parse(datastr);

				Calendar calendar2 = Calendar.getInstance();
				calendar2.setTime(data2);
				if (calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)) {
					if (calendar.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)) {
						if (calendar.get(Calendar.HOUR_OF_DAY) == calendar2.get(Calendar.HOUR_OF_DAY)) {
							if (calendar.get(Calendar.MINUTE) == calendar2.get(Calendar.MINUTE)) {
								datastr = "刚刚";
							} else {
								datastr = calendar.get(Calendar.MINUTE) - calendar2.get(Calendar.MINUTE) + "分钟前";
							}
						} else {
							datastr = calendar.get(Calendar.HOUR_OF_DAY) - calendar2.get(Calendar.HOUR_OF_DAY) + "小时前";
						}
					} else {
						datastr = datastr.substring(5, 10);
					}
				} else {
					datastr = datastr.substring(0, 10);
				}
				sb.append('{').append("\"rid\":").append("\"" + r.getRid() + "\"").append(",");
				sb.append("\"tid\":").append("\"" + r.getTid() + "\"").append(",");
				sb.append("\"r_content\":").append("\"" + r.getR_content() + "\"").append(",");
				sb.append("\"rtime\":").append("\"" + datastr + "\"").append(",");
				sb.append("\"uname\":").append("\"" + r.getUname() + "\"").append(",");
				sb.append("\"rlou\":").append("\"" + r.getRlou() + "\"").append(",");
				sb.append("\"isvip\":").append("\"" + r.getIsvip() + "\"").append(",");
				sb.append("\"grade\":").append("\"" + r.getGrade() + "\"");
				sb.append('}').append(",");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(']');

		return new String(sb);
	}


	@Override
	public int save(R r) {
		// TODO Auto-generated method stub
		int exr = 0;
		try {
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String rtime = df.format(new Date());
//			r.setRtime(rtime);

			exr = rmapper.save(r);
			exr += tiemapper.upcount(r);
			exr += tiemapper.upcount2(r);
			exr += relationMapper.upcount3(r);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("插入失败");
		}
		return exr;
	}

	@Override
	public int deleter(int rid) {
		// TODO Auto-generated method stub
		int exr = 0;
		try {
			exr = rmapper.deleter(rid);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("删除失败");
		}
		return exr;
	}

}
