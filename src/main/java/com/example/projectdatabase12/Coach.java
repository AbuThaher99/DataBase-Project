package com.example.projectdatabase12;

public class Coach extends Employee {
	private String CID;
	private String coachName;
	private String PhoneNumber;

	public Coach(String CID, String name, String phoneNumber) {
		super(CID);
		this.coachName = name;
		PhoneNumber = phoneNumber;
	}

	public String getCID() {
		return CID;
	}

	public void setCID(String CID) {
		this.CID = CID;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String name) {
		this.coachName = name;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Coach [CID=" + CID + ", name=" + coachName + ", PhoneNumber=" + PhoneNumber + "]";
	}

}
