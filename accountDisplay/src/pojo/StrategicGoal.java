package pojo;

import java.util.Calendar;

public class StrategicGoal {
	private String name;
	private String programc;
	private String parentProgramName;
	private Calendar startDate;
	private Calendar completedDate;
	private String goalStatus;
	private String goalNote;
	private String goalType;
	private String recordTypeId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProgramc() {
		return programc;
	}
	public void setProgramc(String programc) {
		this.programc = programc;
	}
	
	public String getParentProgramName() {
		return parentProgramName;
	}
	public void setParentProgramName(String parentProgramName) {
		this.parentProgramName = parentProgramName;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public Calendar getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Calendar completedDate) {
		this.completedDate = completedDate;
	}
	public String getGoalStatus() {
		return goalStatus;
	}
	public void setGoalStatus(String goalStatus) {
		this.goalStatus = goalStatus;
	}
	public String getGoalNote() {
		return goalNote;
	}
	public void setGoalNote(String goalNote) {
		this.goalNote = goalNote;
	}
	public String getGoalType() {
		return goalType;
	}
	public void setGoalType(String goalType) {
		this.goalType = goalType;
	}
	public String getRecordTypeId() {
		return recordTypeId;
	}
	public void setRecordTypeId(String recordTypeId) {
		this.recordTypeId = recordTypeId;
	}
	@Override
	public String toString() {
		return "StrategicGoal [name=" + name + ", programc=" + programc
				+ ", parentProgramName=" + parentProgramName + ", startDate="
				+ startDate + ", completedDate=" + completedDate
				+ ", goalStatus=" + goalStatus + ", goalNote=" + goalNote
				+ ", goalType=" + goalType + ", recordTypeId=" + recordTypeId
				+ "]";
	}
}
