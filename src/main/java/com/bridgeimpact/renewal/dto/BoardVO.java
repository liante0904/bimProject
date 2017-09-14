package com.bridgeimpact.renewal.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


public class BoardVO {

	private int idx;  
	private String id;  
	private String name;  
	private String delGb;  
	
	public BoardVO() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDelGb() {
		return delGb;
	}

	public void setDelGb(String delGb) {
		this.delGb = delGb;
	}

	@Override
	public String toString() {
		return "BoardVO [idx=" + idx + ", id=" + id + ", name=" + name + ", delGb=" + delGb + "]";
	}

 
	
	
}
