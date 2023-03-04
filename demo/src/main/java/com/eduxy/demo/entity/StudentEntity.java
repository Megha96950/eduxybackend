package com.eduxy.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.*;


@Entity
@Table(name="student")
public class StudentEntity {
	
	@Id
	@Column(name="student_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int studentId;
	
	@Column(name="subject_to_study")
    private String subjects; 
	
	@Column(name="institute_name")
	private String instituteName;
	
	@Column(name="id_proof")
	private String idProof;
	
	@Column(name="photo",length=1000)
	private byte[] idPhoto;

	@Column(name="standard")
	private String standard;
	
	@Column(name="mother_name")
	private String motherName;

	@Column(name="father_name")
	private String fatherName;
	
	@Column(name="display_pic")
	private String displayImg;
	
	@Column(name="name")
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

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
        this.studentId = studentId;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

    

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
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

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}


	
	
}
