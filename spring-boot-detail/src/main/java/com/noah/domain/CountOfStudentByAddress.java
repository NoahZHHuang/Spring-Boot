package com.noah.domain;

public class CountOfStudentByAddress {

	private Integer addrId;
	
	private Integer studCount;

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public Integer getStudCount() {
		return studCount;
	}

	public void setStudCount(Integer studCount) {
		this.studCount = studCount;
	}

	@Override
	public String toString() {
		return "CountOfStudentByAddress [addrId=" + addrId + ", studCount=" + studCount + "]";
	}
	
	
	
}
