package bbq.tb.entity;

public class Relation {
	int xid;
	String bname;
	String uname;
	int grade;
	String url;
	int sign_total;
	String last_sign_time;
	int continuty_day;
	int break_day;
	int last_con_day;
	String vipdate;
	String ytype;
	int vipday;

	public Relation(int xid, String bname, String uname, int grade, String url) {
		super();
		this.xid = xid;
		this.bname = bname;
		this.uname = uname;
		this.grade = grade;
		this.url = url;
	}
	
	public Relation(int xid, String bname, String uname, int grade, String url, int sign_total, String last_sign_time,
			int continuty_day, int break_day, int last_con_day) {
		super();
		this.xid = xid;
		this.bname = bname;
		this.uname = uname;
		this.grade = grade;
		this.url = url;
		this.sign_total = sign_total;
		this.last_sign_time = last_sign_time;
		this.continuty_day = continuty_day;
		this.break_day = break_day;
		this.last_con_day = last_con_day;
	}

	public Relation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getXid() {
		return xid;
	}
	public void setXid(int xid) {
		this.xid = xid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getSign_total() {
		return sign_total;
	}
	public void setSign_total(int sign_total) {
		this.sign_total = sign_total;
	}
	public String getLast_sign_time() {
		return last_sign_time;
	}
	public void setLast_sign_time(String last_sign_time) {
		this.last_sign_time = last_sign_time;
	}
	public int getContinuty_day() {
		return continuty_day;
	}
	public void setContinuty_day(int continuty_day) {
		this.continuty_day = continuty_day;
	}
	public int getBreak_day() {
		return break_day;
	}
	public void setBreak_day(int break_day) {
		this.break_day = break_day;
	}
	public int getLast_con_day() {
		return last_con_day;
	}
	public void setLast_con_day(int last_con_day) {
		this.last_con_day = last_con_day;
	}

	public String getVipdate() {
		return vipdate;
	}

	public void setVipdate(String vipdate) {
		this.vipdate = vipdate;
	}

	public String getYtype() {
		return ytype;
	}

	public void setYtype(String ytype) {
		this.ytype = ytype;
	}

	public int getVipday() {
		return vipday;
	}

	public void setVipday(int vipday) {
		this.vipday = vipday;
	}

	
}
