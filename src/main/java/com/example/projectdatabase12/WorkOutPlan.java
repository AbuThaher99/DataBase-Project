package com.example.projectdatabase12;

import javafx.scene.image.ImageView;

public class WorkOutPlan {
	private String WID;
	private String Plantype;
	private String DmPercentage;
	private String dfPercentage;
	private String Deadline;
	private ImageView exercises;
	public WorkOutPlan(String wID, String plantype, String dmPercentage, String dfPercentage, String deadline,
			ImageView exercises) {
		super();
		WID = wID;
		Plantype = plantype;
		DmPercentage = dmPercentage;
		this.dfPercentage = dfPercentage;
		Deadline = deadline;
		this.exercises = exercises;
	}
	public String getWID() {
		return WID;
	}
	public void setWID(String wID) {
		WID = wID;
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
	public ImageView getExercises() {
		return exercises;
	}
	public void setExercises(ImageView exercises) {
		this.exercises = exercises;
	}
	@Override
	public String toString() {
		return "WorkOutPlan [WID=" + WID + ", Plantype=" + Plantype + ", DmPercentage=" + DmPercentage
				+ ", dfPercentage=" + dfPercentage + ", Deadline=" + Deadline + ", exercises=" + exercises + "]";
	}
	
	
	
	

}
