package in.co.rays.proj3.dto;

public class NFTAssetDTO extends BaseDTO {
	
	private String nftCode;
	private String assetName;
	private String ownerName;
	private String status;
	
	public String getNftCode() {
		return nftCode;
	}
	public void setNftCode(String nftCode) {
		this.nftCode = nftCode;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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
		return assetName;
	}
	
	

}
