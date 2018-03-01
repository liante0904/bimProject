package com.bridgeimpact.renewal.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;


public class ArticleVO {

	private int idx;  
	private String boardName;  
	private String title;  
	private String contents;  
	private int hitCnt;  
	private String delGb;  
	private String writeDt;  
	private String writeId;
	private String boardDelGb;
	private MultipartFile files;
	
	public ArticleVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	@Override
	public String toString() {
		return "ArticleVO [idx=" + idx + ", boardName=" + boardName + ", title=" + title + ", contents=" + contents
				+ ", hitCnt=" + hitCnt + ", delGb=" + delGb + ", writeDt=" + writeDt + ", writeId=" + writeId
				+ ", boardDelGb=" + boardDelGb + ", files=" + files + ", getIdx()=" + getIdx() + ", getBoardName()="
				+ getBoardName() + ", getTitle()=" + getTitle() + ", getContents()=" + getContents() + ", getHitCnt()="
				+ getHitCnt() + ", getDelGb()=" + getDelGb() + ", getWriteDt()=" + getWriteDt() + ", getWriteId()="
				+ getWriteId() + ", getBoardDelGb()=" + getBoardDelGb() + ", getFiles()=" + getFiles() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}




	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
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

	public String getBoardDelGb() {
		return boardDelGb;
	}

	public void setBoardDelGb(String boardDelGb) {
		this.boardDelGb = boardDelGb;
	}




	public MultipartFile getFiles() {
		return files;
	}




	public void setFiles(MultipartFile files) {
		this.files = files;
	}


	




	
}
