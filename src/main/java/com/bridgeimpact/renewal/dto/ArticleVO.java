package com.bridgeimpact.renewal.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


public class ArticleVO {

	private int idx;  
	private int parentIdx;  
	private String title;  
	private String contents;  
	private int hitCnt;  
	private String delGb;  
	private String writeDt;  
	private String writeId;
	
	public ArticleVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	@Override
	public String toString() {
		return "BoardVO [idx=" + idx + ", parentIdx=" + parentIdx + ", title=" + title + ", contents=" + contents
				+ ", hitCnt=" + hitCnt + ", delGb=" + delGb + ", writeDt=" + writeDt + ", writeId=" + writeId + "]";
	}




	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


	public int getParentIdx() {
		return parentIdx;
	}


	public void setParentIdx(int parentIdx) {
		this.parentIdx = parentIdx;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public int getHitCnt() {
		return hitCnt;
	}


	public void setHitCnt(int hitCnt) {
		this.hitCnt = hitCnt;
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
