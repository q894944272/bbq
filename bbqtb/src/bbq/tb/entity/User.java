package bbq.tb.entity;

public class User {
	private String username;
	private String password;
	private String sex;
	private String say;
	private String url;
	private String concerned;
	private String noticer;
	private int yue;
	private String supper_vip;
	private int sup_rep_card;
	private int rep_card;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public User(String username, String password, String sex, String say) {
		super();
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.say = say;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getSex() {
		return sex;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}



	public String getSay() {
		return say;
	}



	public void setSay(String say) {
		this.say = say;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getConcerned() {
		return concerned;
	}



	public void setConcerned(String concerned) {
		this.concerned = concerned;
	}



	public String getNoticer() {
		return noticer;
	}



	public void setNoticer(String noticer) {
		this.noticer = noticer;
	}



	public int getYue() {
		return yue;
	}



	public void setYue(int yue) {
		this.yue = yue;
	}



	public String getSupper_vip() {
		return supper_vip;
	}



	public void setSupper_vip(String supper_vip) {
		this.supper_vip = supper_vip;
	}



	public int getSup_rep_card() {
		return sup_rep_card;
	}



	public void setSup_rep_card(int sup_rep_card) {
		this.sup_rep_card = sup_rep_card;
	}



	public int getRep_card() {
		return rep_card;
	}



	public void setRep_card(int rep_card) {
		this.rep_card = rep_card;
	}
	
	
}
