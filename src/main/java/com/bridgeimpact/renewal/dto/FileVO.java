package com.bridgeimpact.renewal.dto;


public class FileVO {

	private int articleIdx;
	private String originalFileName;
	private String storedFileName;
	private int fileSize;
	private String creaDtm;
	private String creaId;
	private String delGb;
	
	

	public FileVO() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "FileVO [articleIdx=" + articleIdx + ", originalFileName=" + originalFileName + ", storedFileName="
				+ storedFileName + ", fileSize=" + fileSize + ", creaDtm=" + creaDtm + ", creaId=" + creaId + ", delGb="
				+ delGb + "]";
	}



	public int getArticleIdx() {
		return articleIdx;
	}



	public void setArticleIdx(int articleIdx) {
		this.articleIdx = articleIdx;
	}



	public String getOriginalFileName() {
		return originalFileName;
	}



	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}



	public String getStoredFileName() {
		return storedFileName;
	}



	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}



	public int getFileSize() {
		return fileSize;
	}



	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}



	public String getCreaDtm() {
		return creaDtm;
	}



	public void setCreaDtm(String creaDtm) {
		this.creaDtm = creaDtm;
	}



	public String getCreaId() {
		return creaId;
	}



	public void setCreaId(String creaId) {
		this.creaId = creaId;
	}



	public String getDelGb() {
		return delGb;
	}



	public void setDelGb(String delGb) {
		this.delGb = delGb;
	}

	
	
}
