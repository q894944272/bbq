package bbq.tb.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbq.tb.entity.Tie;
import bbq.tb.entity.Zorcc;
import bbq.tb.mapper.BaMapper;
import bbq.tb.mapper.RelationMapper;
import bbq.tb.mapper.TieMapper;
import bbq.tb.mapper.ZorcMapper;

@Service
public class TieServiceimpl implements TieService {

	@Autowired
	TieMapper tiemapper;
	@Autowired
	RelationMapper relationmapper;
	@Autowired
	BaMapper bamapper;
	@Autowired
	ZorcMapper zorcmapper;

	@Override
	public String serchtie(String style, HttpServletRequest request) {
		// TODO Auto-generated method stub

		Tie[] tielist = null;
		String exr = null;
		String username = "";
		username = request.getParameter("username2");
		try {
			if ("1".equals(style)) {
				Date date = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(calendar.DATE, -2);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String ttime = df.format(calendar.getTime());

				tielist = tiemapper.serchtie1(ttime, username);
			} else if ("2".equals(style)) {
				String uname = request.getParameter("username");
				tielist = tiemapper.serchtie2(uname, username);
			} else if ("3".equals(style)) {
				String bname = request.getParameter("bname");
				String flag_time = request.getParameter("flag_time");
				if("刷新".equals(flag_time)){
					flag_time = "null";
				}
//				tielist = tiemapper.serchtie3(bname, username);
				tielist = tiemapper.serchtie3(bname, username,flag_time);
			} else if ("4".equals(style)) {
				String uname = request.getParameter("username");
				tielist = tiemapper.serchtie4(uname, username);
			}

			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			StringBuffer sb = new StringBuffer();
			sb.append('[');

			for (Tie tie : tielist) {
				String datastr = tie.getTtime();
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
				sb.append('{').append("\"tid\":").append("\"" + tie.getTid() + "\"").append(",");
				sb.append("\"bname\":").append("\"" + tie.getBname() + "\"").append(",");
				sb.append("\"tname\":").append("\"" + tie.getTname() + "\"").append(",");
				sb.append("\"tcontent\":").append("\"" + tie.getTcontent() + "\"").append(",");
				sb.append("\"ttime\":").append("\"" + datastr + "\"").append(",");
				sb.append("\"last_time\":").append("\"" + tie.getLast_time() + "\"").append(",");
				sb.append("\"uname\":").append("\"" + tie.getUname() + "\"").append(",");
				sb.append("\"rlou\":").append("\"" + tie.getRlou() + "\"").append(",");
				sb.append("\"rcount\":").append("\"" + tie.getRcount() + "\"").append(",");
				sb.append("\"zcount\":").append("\"" + tie.getZcount() + "\"").append(",");
				sb.append("\"ccount\":").append("\"" + tie.getCcount() + "\"").append(",");
				sb.append("\"isvip\":").append("\"" + tie.getIsvip() + "\"").append(",");
				if (tie.getZflag() == 1) {
					sb.append("\"zflag\":").append("\"" + 1 + "\"").append(",");
					sb.append("\"cflag\":").append("\"" + 0 + "\"");
				}else if (tie.getZflag() == -1) {
					sb.append("\"zflag\":").append("\"" + 0 + "\"").append(",");
					sb.append("\"cflag\":").append("\"" + -1 + "\"");
				}else{
					sb.append("\"zflag\":").append("\"" + 0 + "\"").append(",");
					sb.append("\"cflag\":").append("\"" + 0 + "\"");
				}

				sb.append('}').append(",");

			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(']');
			return new String(sb);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询失败");
		}

		return null;
	}

	@Override
	public int save(Tie tie, HttpServletRequest request) {
		// TODO Auto-generated method stub
		int exr = 0;
		try {
			String bas = request.getParameter("bas");
			String baslist[] = bas.split(" ");

//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String ttime = df.format(new Date());
//			tie.setTtime(ttime);
			for (String bname : baslist) {
				tie.setBname(bname);
				exr += tiemapper.save(tie);
				exr += relationmapper.upcount(tie);
				exr += bamapper.upcount2(tie);
			}
			exr /= baslist.length;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("插入失败");
		}
		return exr;
	}

	@Override
	public int zorc(Zorcc zorcc) {
		// TODO Auto-generated method stub
		int result = zorcc.getZorc();
		int zorcnum = 0;
		try {
			zorcnum = zorcmapper.findzorcnum(zorcc);

			if (zorcc.getZorc() == zorcnum) {
				zorcmapper.deletezorc(zorcc);
				if (zorcc.getZorc() == 1) {
					tiemapper.subz(zorcc);
				} else {
					tiemapper.subc(zorcc);
				}
				result = 0;
			} else if (zorcc.getZorc() == -zorcnum) {
				zorcmapper.deletezorc(zorcc);
				zorcmapper.savezorcnum(zorcc);
				if (zorcc.getZorc() == 1) {
					tiemapper.addz(zorcc);
					tiemapper.subc(zorcc);
				} else {
					tiemapper.addc(zorcc);
					tiemapper.subz(zorcc);
				}
			} else {
				zorcmapper.savezorcnum(zorcc);
				if (zorcc.getZorc() == 1) {
					tiemapper.addz(zorcc);
				} else {
					tiemapper.addc(zorcc);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
