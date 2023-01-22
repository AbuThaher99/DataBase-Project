package com.example.projectdatabase12;

import javafx.scene.image.ImageView;

public class FoodPlan {
	
	private String PID;
	private String Plantype;
	private String DmPercentage;
	private String dfPercentage;
	private String Deadline;
	private ImageView meals;
	public FoodPlan(String pID, String plantype, String dmPercentage, String dfPercentage, String deadline,
			ImageView meals) {
		super();
		PID = pID;
		Plantype = plantype;
		DmPercentage = dmPercentage;
		this.dfPercentage = dfPercentage;
		Deadline = deadline;
		this.meals = meals;
	}
	public String getPID() {
		return PID;
	}
	public void setPID(String pID) {
		PID = pID;
	}
	public String getPlantype() {
		return Plantype;
	}
	public void setPlantype(String plantype) {
		Plantype = plantype;
	}
	public String getDmPercentage() {
		return DmPercentage;
	}
	public void setDmPercentage(String dmPercentage) {
		DmPercentage = dmPercentage;
	}
	public String getDfPercentage() {
		return dfPercentage;
	}
	public void setDfPercentage(String dfPercentage) {
		this.dfPercentage = dfPercentage;
	}
	public String getDeadline() {
		return Deadline;
	}
	public void setDeadline(String deadline) {
		Deadline = deadline;
	}
	public ImageView getMeals() {
		return meals;
	}
	public void setMeals(ImageView meals) {
		this.meals = meals;
	}
	@Override
	public String toString() {
		return "FoodPlan [PID=" + PID + ", Plantype=" + Plantype + ", DmPercentage=" + DmPercentage + ", dfPercentage="
				+ dfPercentage + ", Deadline=" + Deadline + ", meals=" + meals + "]";
	}
	
	
	
	
	
	
	
	

}
