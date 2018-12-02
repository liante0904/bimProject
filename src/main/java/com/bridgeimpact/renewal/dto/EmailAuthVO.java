package com.bridgeimpact.renewal.dto;

import com.bridgeimpact.renewal.common.TempKey;

public class EmailAuthVO {
	private int idx;  
	private int userIdx;
	private String userId;
	private String userPassword;
	private String emailAuthKey; 
	private String authFl;



	public EmailAuthVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmailAuthVO(MemberVO inputMember) {
        String key = new TempKey().getKey(50, false);
		this.setUserId(inputMember.getId());
		this.setUserPassword(inputMember.getPassword());
		this.setUserIdx(inputMember.getIdx());
		this.setEmailAuthKey(key);
	}


	@Override
	public String toString() {
		return "EmailAuthVO{" +
				"idx=" + idx +
				", userIdx=" + userIdx +
				", userId='" + userId + '\'' +
				", userPassword='" + userPassword + '\'' +
				", emailAuthKey='" + emailAuthKey + '\'' +
				", authFl='" + authFl + '\'' +
				'}';
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

	public int getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
