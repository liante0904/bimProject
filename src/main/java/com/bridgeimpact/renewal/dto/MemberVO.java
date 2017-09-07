package com.bridgeimpact.renewal.dto;

public class MemberVO {
	 
	private int idx;
	private String id;
	private String password;
	private String name;
	private String email;
	private String address;
	private String phone;
	private String church;
	private String type;
	
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Member [idx=" + idx + ", id=" + id + ", password=" + password + ", name=" + name + ", email=" + email
				+ ", address=" + address + ", phone=" + phone + ", church=" + church + ", type=" + type + "]";
	}


	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getChurch() {
		return church;
	}


	public void setChurch(String church) {
		this.church = church;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	
	
	
}
