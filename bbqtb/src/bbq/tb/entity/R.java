package bbq.tb.entity;

public class R {
	private int rid;
	private int tid;
	private String r_content;
	private String rtime;
	private String uname;
	private int rlou;
	private String tname;
	private int grade;
	private int isvip;
	public R(int rid, int tid, String r_content, String rtime, String uname, int rlou) {
		super();
		this.rid = rid;
		this.tid = tid;
		this.r_content = r_content;
		this.rtime = rtime;
		this.uname = uname;
		this.rlou = rlou;
	}
	
	public R() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getRlou() {
		return rlou;
	}
	public void setRlou(int rlou) {
		this.rlou = rlou;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getIsvip() {
		return isvip;
	}

	public void setIsvip(int isvip) {
		this.isvip = isvip;
	}
	
}
