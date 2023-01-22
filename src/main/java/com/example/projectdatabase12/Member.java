package com.example.projectdatabase12;

public class Member {

	private String GID;
	private String name;
	private int age;
	private double weight;
	private double height;
	private String address;
	private String phoneNum;
	private boolean lockerOption;
	private String membershipEndDate;
	  
	
	
	

	public Member(String GID, String name, int age, double weight, double height, String address, String phoneNum,
			boolean lockerOption, String membershipEndDate) {
		super();
		this.GID = GID;
		this.name = name;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.address = address;
		this.phoneNum = phoneNum;
		this.lockerOption = lockerOption;
		this.membershipEndDate = membershipEndDate;
	}

	

	public String getGID() {
		return GID;
	}



	public void setGID(String gID) {
		GID = gID;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public boolean isLockerOption() {
		return lockerOption;
	}

	public void setLockerOption(boolean lockerOption) {
		this.lockerOption = lockerOption;
	}

	public String getMembershipEndDate() {
		return membershipEndDate;
	}

	public void setMembershipEndDate(String membershipEndDate) {
		this.membershipEndDate = membershipEndDate;
	}

	@Override
	public String toString() {
		return "Member [gID=" + GID + ", name=" + name + ", address=" + address + ", phoneNum=" + phoneNum + ", age="
				+ age + ", weight=" + weight + ", height=" + height + ", lockerOption=" + lockerOption
				+ ", membershipEndDate=" + membershipEndDate + "]";
	}
	
	
	
//	create database Gym;
//	use Gym;
//	create table Members(
//	GID varchar(32) primary key,
//	Name varchar(32),
//	age int,
//	weight real , 
//	heigth real,
//	addrees varchar(32) ,
//	phoneNumber varchar(32) ,
//	lockerOp boolean , 
//	EndDate varchar(32)
//	);
//	insert into Members(GID,Name,age,weight,heigth,addrees,phoneNumber,lockerOp,EndDate) values('1','mohammad',15,45.5,125,'ramallah','0599782941',true,'2023-1-15');
//	insert into Members(GID,Name,age,weight,heigth,addrees,phoneNumber,lockerOp,EndDate) values('2','mohammad',15,45.5,125,'ramallah','0599782941',false,'2023-1-15');
//	select * from members;



}
