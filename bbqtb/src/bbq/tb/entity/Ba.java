package bbq.tb.entity;

public class Ba {
	private int bid;
	private String bname;
	private int tcount;
	private String url;
	 
	public Ba(int bid, String bname, int tcount, String url) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.tcount = tcount;
		this.url = url;
	}
	public Ba(int bid, String bname, int tcount) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.tcount = tcount;
	}
	public Ba(String bid, String bname, String tcount) {
		super();
		this.bid = Integer.parseInt(bid);
		this.bname = bname;
		this.tcount = Integer.parseInt(tcount);
	}
	public Ba() {
		super();
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getTcount() {
		return tcount;
	}
	public void setTcount(int tcount) {
		this.tcount = tcount;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
