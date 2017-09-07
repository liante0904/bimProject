package com.bridgeimpact.renewal.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


public class CommentVO {

	private String idx;
	private String parentIdx;
	private String contents;
	private String delGb;
	private String writeDt;
	private String writeId;

	
	public CommentVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "CommentVO [idx=" + idx + ", parentIdx=" + parentIdx + ", contents=" + contents + ", delGb=" + delGb
				+ ", writeDt=" + writeDt + ", writeId=" + writeId + "]";
	}


	public String getIdx() {
		return idx;
	}


	public void setIdx(String idx) {
		this.idx = idx;
	}


	public String getParentIdx() {
		return parentIdx;
	}


	public void setParentIdx(String parentIdx) {
		this.parentIdx = parentIdx;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public String getDelGb() {
		return delGb;
	}


	public void setDelGb(String delGb) {
		this.delGb = delGb;
	}


	public String getWriteDt() {
		return writeDt;
	}


	public void setWriteDt(String writeDt) {
		this.writeDt = writeDt;
	}


	public String getWriteId() {
		return writeId;
	}


	public void setWriteId(String writeId) {
		this.writeId = writeId;
	}


	

	
	
}
