package bbq.tb.entity;

public class Bmanager {
	private int bm_id;
	private String realName;
	private String idCard;
	private String cellPhone;
	private String apply_say;
	private String apply_time;
	private String uname;
	private String bname;
	private int is_exa;
	private String exa_time;
	private String exa_name;
	private String exa_respon;
	private int aeDay;
	public Bmanager(int bm_id, String realName, String idCard, String cellPhone, String apply_say, String apply_time,
			String uname, String bname, int is_exa, String exa_time, String respon) {
		super();
		this.bm_id = bm_id;
		this.realName = realName;
		this.idCard = idCard;
		this.cellPhone = cellPhone;
		this.apply_say = apply_say;
		this.apply_time = apply_time;
		this.uname = uname;
		this.bname = bname;
		this.is_exa = is_exa;
		this.exa_time = exa_time;
	}
	public Bmanager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getBm_id() {
		return bm_id;
	}
	public void setBm_id(int bm_id) {
		this.bm_id = bm_id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getApply_say() {
		return apply_say;
	}
	public void setApply_say(String apply_say) {
		this.apply_say = apply_say;
	}
	public String getApply_time() {
		return apply_time;
	}
	public void setApply_time(String apply_time) {
		this.apply_time = apply_time;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getIs_exa() {
		return is_exa;
	}
	public void setIs_exa(int is_exa) {
		this.is_exa = is_exa;
	}
	public String getExa_time() {
		return exa_time;
	}
	public void setExa_time(String exa_time) {
		this.exa_time = exa_time;
	}
	public String getExa_respon() {
		return exa_respon;
	}
	public void setExa_respon(String exa_respon) {
		this.exa_respon = exa_respon;
	}
	public int getAeDay() {
		return aeDay;
	}
	public void setAeDay(int aeDay) {
		this.aeDay = aeDay;
	}
	public String getExa_name() {
		return exa_name;
	}
	public void setExa_name(String exa_name) {
		this.exa_name = exa_name;
	}
	
}
