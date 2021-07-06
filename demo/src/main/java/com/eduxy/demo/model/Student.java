package com.eduxy.demo.model;

import javax.persistence.Column;

public class Student {
	
    private int StudentId;
	private String subjects;
	private String institueName;
	private String idProof;
	private byte[] idPhoto;
	
	
	public int getStudentId() {
		return StudentId;
	}
	public void setStudentId(int studentId) {
		StudentId = studentId;
	}
	public String getSubjects() {
		return subjects;
	}
	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}
	public String getInstitueName() {
		return institueName;
	}
	public void setInstitueName(String institueName) {
		this.institueName = institueName;
	}
	public String getIdProof() {
		return idProof;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	public byte[] getIdPhoto() {
		return idPhoto;
	}
	public void setIdPhoto(byte[] idPhoto) {
		this.idPhoto = idPhoto;
	}
	
	
	


}
