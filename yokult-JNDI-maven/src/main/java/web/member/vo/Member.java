package web.member.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {

	private static final long serialVersionUID = 1L;
	private String memID;
	private String memPassword;
	private String memEmail;
	private String memFirstName;
	private String memLastName;
	private Date memBirth;
	private String memCellPhone;
	private String memAddress;

	@Override
	public String toString() {
		return "Member [memID=" + memID + ", memFirstName=" + memFirstName + ", memLastName=" + memLastName + "]";
	}

	public Member() {

	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getMemPassword() {
		return memPassword;
	}

	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	
	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemFirstName() {
		return memFirstName;
	}

	public void setMemFirstName(String memFirstName) {
		this.memFirstName = memFirstName;
	}

	public String getMemLastName() {
		return memLastName;
	}

	public void setMemLastName(String memLastName) {
		this.memLastName = memLastName;
	}

	public Date getMemBirth() {
		return memBirth;
	}

	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}

	public String getMemCellPhone() {
		return memCellPhone;
	}

	public void setMemCellPhone(String memCellPhone) {
		this.memCellPhone = memCellPhone;
	}

	public String getMemAddress() {
		return memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}

}
