package com.eduxy.demo.model;

import javax.persistence.Column;

public class Student {
	
    private int StudentId;
	private String Subjects;
	private String InstitueName;
	private String IdProof;
	private String Photo;
	
	
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public String getSubjects() {
		return Subjects;
	}
	public void setSubjects(String subjects) {
		Subjects = subjects;
	}
	public String getInstitueName() {
		return InstitueName;
	}
	public void setInstitueName(String institueName) {
		InstitueName = institueName;
	}
	public String getIdProof() {
		return IdProof;
	}
	public void setIdProof(String idProof) {
		IdProof = idProof;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	
	


}
