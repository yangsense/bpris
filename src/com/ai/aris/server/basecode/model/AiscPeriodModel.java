package com.ai.aris.server.basecode.model;

public class AiscPeriodModel {
	private Long id;
	private String periodId;
	private String periodDesc;
	private String periodStarttime;
	private String periodEndtime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPeriodId() {
		return periodId;
	}
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}
	public String getPeriodDesc() {
		return periodDesc;
	}
	public void setPeriodDesc(String periodDesc) {
		this.periodDesc = periodDesc;
	}
	public String getPeriodStarttime() {
		return periodStarttime;
	}
	public void setPeriodStarttime(String periodStarttime) {
		this.periodStarttime = periodStarttime;
	}
	public String getPeriodEndtime() {
		return periodEndtime;
	}
	public void setPeriodEndtime(String periodEndtime) {
		this.periodEndtime = periodEndtime;
	}
	
}
