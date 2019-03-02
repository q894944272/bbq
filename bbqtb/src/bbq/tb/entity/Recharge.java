package bbq.tb.entity;

public class Recharge {
	private String card_id;
	private String card_m;
	private int money_sum;
	private String uname;
	private String act_time;
	public Recharge() {
		super();
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getCard_m() {
		return card_m;
	}
	public void setCard_m(String card_m) {
		this.card_m = card_m;
	}
	public int getMoney_sum() {
		return money_sum;
	}
	public void setMoney_sum(int money_sum) {
		this.money_sum = money_sum;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getAct_time() {
		return act_time;
	}
	public void setAct_time(String act_time) {
		this.act_time = act_time;
	}
	
}
