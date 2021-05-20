package com.eduxy.demo.model;

import javax.persistence.Column;

public class Teacher {

    private int TeacherId;
	private String Subjects;
	private String Description;
	private String HigherQualification;
	private String IdProof;
	private String DegreePhoto;
	private String IdPhoto;
	private String feesCharged;
	public int getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
	}
	public String getSubjects() {
		return Subjects;
	}
	public void setSubjects(String subjects) {
		Subjects = subjects;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getHigherQualification() {
		return HigherQualification;
	}
	public void setHigherQualification(String higherQualification) {
		HigherQualification = higherQualification;
	}
	public String getIdProof() {
		return IdProof;
	}
	public void setIdProof(String idProof) {
		IdProof = idProof;
	}
	public String getDegreePhoto() {
		return DegreePhoto;
	}
	public void setDegreePhoto(String degreePhoto) {
		DegreePhoto = degreePhoto;
	}
	public String getIdPhoto() {
		return IdPhoto;
	}
	public void setIdPhoto(String idPhoto) {
		IdPhoto = idPhoto;
	}
	public String getFeesCharged() {
		return feesCharged;
	}
	public void setFeesCharged(String feesCharged) {
		this.feesCharged = feesCharged;
	}
	
	
}
