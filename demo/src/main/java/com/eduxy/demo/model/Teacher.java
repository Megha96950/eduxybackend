package com.eduxy.demo.model;

import org.springframework.web.multipart.MultipartFile;

public class Teacher {

    private int teacherId;
	private String subjects;
	private String description;
	private String higherQualification;
	private String idProof;
	private byte[] degreePhoto;
	private String idPhoto;
	private String feesCharged;
	private String emailId;
	private String displayImg;
	private String name;
	
	
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayImg() {
		return displayImg;
	}
	public void setDisplayImg(String displayImg) {
		this.displayImg = displayImg;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getSubjects() {
		return subjects;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHigherQualification() {
		return higherQualification;
	}
	public void setHigherQualification(String higherQualification) {
		this.higherQualification = higherQualification;
	}
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	
	
	
	public byte[] getDegreePhoto() {
		return degreePhoto;
	}
	public void setDegreePhoto(byte[] degreePhoto) {
		this.degreePhoto = degreePhoto;
	}
	
	
	public String getIdPhoto() {
		return idPhoto;
	}
	public void setIdPhoto(String idPhoto) {
		this.idPhoto = idPhoto;
	}
	public String getFeesCharged() {
		return feesCharged;
	}
	public void setFeesCharged(String feesCharged) {
		this.feesCharged = feesCharged;
	}
	
	
}
