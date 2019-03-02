package bbq.tb.entity;

public class Attention {
	private String noticer;
	private String concerned;
	public Attention(String noticer, String concerned) {
		super();
		this.noticer = noticer;
		this.concerned = concerned;
	}

	public Attention() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNoticer() {
		return noticer;
	}
	public void setNoticer(String noticer) {
		this.noticer = noticer;
	}
	public String getConcerned() {
		return concerned;
	}
	public void setConcerned(String concerned) {
		this.concerned = concerned;
	}
	
}
