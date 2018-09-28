package cn.zjstu.photograph.domain;

public class User {
	private int id;
	private String nickname;
	private String password;
	private String conPassword;
	private String mobile;
	private String portrait;

	// 用于service层和controller层数据交换
	private boolean flag;
	private String tem_msg;
	
	//保存session信息
	private String session_id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getConPassword() {
		return conPassword;
	}

	public void setConPassword(String conPassword) {
		this.conPassword = conPassword;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getTem_msg() {
		return tem_msg;
	}

	public void setTem_msg(String tem_msg) {
		this.tem_msg = tem_msg;
	}
	
	
	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", password=" + password + ", conPassword=" + conPassword
				+ ", mobile=" + mobile + ", portrait=" + portrait + ", flag=" + flag + ", tem_msg=" + tem_msg + "]";
	}
	

}
