package in.co.rays.proj3.dto;

import java.util.Date;

public class ReportDTO extends BaseDTO {
	
	private String reportName;
	private Date generatedDate;
	private String generatedBy;
	private String reportStatus;
	
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public Date getGeneratedDate() {
		return generatedDate;
	}
	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}
	public String getGeneratedBy() {
		return generatedBy;
	}
	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
	}
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	@Override
	public int compareTo(BaseDTO o) {
		return 0;
	}
	@Override
	public String getKey() {
		return id+"";
	}
	@Override
	public String getValue() {
		return reportName;
	}
	
	

}
