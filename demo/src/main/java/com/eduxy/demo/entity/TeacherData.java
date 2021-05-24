package com.eduxy.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teacherData")
public class TeacherData {

	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="degree_photo")
	private byte[] DegreePhoto;
	
	@Column(name="id_photo")
	private byte[] idPhoto;

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
