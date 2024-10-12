package Model;

public class Guest {
	
	private int gId;
	private String gName;
	private String gEmail;
	private String gPhone;
	private String gAddress;
	public int getgId() {
		return gId;
	}
	public void setgId(int gId) {
		this.gId = gId;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getgEmail() {
		return gEmail;
	}
	public void setgEmail(String gEmail) {
		this.gEmail = gEmail;
	}
	public String getgPhone() {
		return gPhone;
	}
	public void setgPhone(String gPhone) {
		this.gPhone = gPhone;
	}
	public String getgAddress() {
		return gAddress;
	}
	public void setgAddress(String gAddress) {
		this.gAddress = gAddress;
	}
	public Guest(int gId, String gName, String gEmail, String gPhone, String gAddress) {
		super();
		this.gId = gId;
		this.gName = gName;
		this.gEmail = gEmail;
		this.gPhone = gPhone;
		this.gAddress = gAddress;
	}
	public Guest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Guest [gId=" + gId + ", gName=" + gName + ", gEmail=" + gEmail + ", gPhone=" + gPhone + ", gAddress="
				+ gAddress + "]";
	}
	
}
