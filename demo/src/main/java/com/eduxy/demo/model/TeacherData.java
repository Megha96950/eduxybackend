package com.eduxy.demo.model;



public class TeacherData {
	
    private int id;
	private byte[] DegreePhoto;
    private byte[] idPhoto;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public byte[] getDegreePhoto() {
		return DegreePhoto;
	}
	public void setDegreePhoto(byte[] degreePhoto) {
		DegreePhoto = degreePhoto;
	}
	public byte[] getIdPhoto() {
		return idPhoto;
	}
	public void setIdPhoto(byte[] idPhoto) {
		this.idPhoto = idPhoto;
	}
    
    
}
