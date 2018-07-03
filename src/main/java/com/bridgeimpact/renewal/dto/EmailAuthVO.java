package com.bridgeimpact.renewal.dto;

public class EmailAuthVO {
	private int idx;  
	private String userId;
	private String emailAuthKey; 
	private String authFl;
	
	
	
	public EmailAuthVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "EmailAuthVO [idx=" + idx + ", userId=" + userId + ", emailAuthKey=" + emailAuthKey + ", authFl="
				+ authFl + ", getIdx()=" + getIdx() + ", getUserId()=" + getUserId() + ", getEmailAuthKey()="
				+ getEmailAuthKey() + ", getAuthFl()=" + getAuthFl() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmailAuthKey() {
		return emailAuthKey;
	}
	public void setEmailAuthKey(String emailAuthKey) {
		this.emailAuthKey = emailAuthKey;
	}
	public String getAuthFl() {
		return authFl;
	}
	public void setAuthFl(String authFl) {
		this.authFl = authFl;
	} 

	

}
