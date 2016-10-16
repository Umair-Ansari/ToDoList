package com.umair.todolist.dblayer;
public class Task {
	private int 	LId;
	private String 	description;
	private boolean completionStatus;
	public int getLId() {
		return LId;
	}
	public void setLId(int lId) {
		LId = lId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isCompletionStatus() {
		return completionStatus;
	}
	public void setCompletionStatus(boolean completionStatus) {
		this.completionStatus = completionStatus;
	}	
}
