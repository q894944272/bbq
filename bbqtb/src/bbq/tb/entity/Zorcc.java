package bbq.tb.entity;

public class Zorcc {
	private int tid;
	private String uname;
	private int zorc;
	public Zorcc(int tid, String uname, int zorc) {
		super();
		this.tid = tid;
		this.uname = uname;
		this.zorc = zorc;
	}
	public Zorcc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getZorc() {
		return zorc;
	}
	public void setZorc(int zorc) {
		this.zorc = zorc;
	}
	
}
