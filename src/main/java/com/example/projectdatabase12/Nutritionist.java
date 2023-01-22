package com.example.projectdatabase12;


public class Nutritionist extends Employee{
	private String NID;
	private String nutritionistName;
	private String phoneNumber;
	private String officeAddress;

	public Nutritionist(String NID, String nutritionistName,  String phoneNum, String officeAddress ) {
		super(NID);
		this.nutritionistName = nutritionistName;
		this.phoneNumber = phoneNum;
		this.officeAddress = officeAddress;
	}

	public String getNID() {
		return NID;
	}

	public void setNID(String nID) {
		NID = nID;
	}

	public String getNutritionistName() {
		return nutritionistName;
	}

	public void setNutritionistName(String nutritionistName) {
		this.nutritionistName = nutritionistName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNum) {
		this.phoneNumber = phoneNum;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	@Override
	public String toString() {
		return this.NID + " " + this.nutritionistName + " " + this.phoneNumber + " " + this.officeAddress;
	}

}
