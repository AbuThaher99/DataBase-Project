package com.example.projectdatabase12;


public class Bio {

	private String BID;
	private String coachName;
	private String workingDays;
	private String workingTimes;
	private String trainingType;

	public Bio(String bID, String coachName, String workingDays, String workingTimes, String trainingType) {
		BID = bID;
		this.coachName = coachName;
		this.workingDays = workingDays;
		this.workingTimes = workingTimes;
		this.trainingType = trainingType;
	}

	public String getBID() {
		return BID;
	}

	public void setBID(String bID) {
		BID = bID;
	}

	public String getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(String workingDays) {
		this.workingDays = workingDays;
	}

	public String getWorkingTimes() {
		return workingTimes;
	}

	public void setWorkingTimes(String workingTimes) {
		this.workingTimes = workingTimes;
	}

	public String getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}


	public String getCoachName() {
		return coachName;
	}


	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

}
