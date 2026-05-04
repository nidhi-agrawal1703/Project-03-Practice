package in.co.rays.proj3.dto;

public class AllowDTO extends BaseDTO {
	
	private String allowCode;
	private String userName;
	private String source;
	private String status;
	
	public String getAllowCode() {
		return allowCode;
	}
	public void setAllowCode(String allowCode) {
		this.allowCode = allowCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return userName;
	}
	
	
}
