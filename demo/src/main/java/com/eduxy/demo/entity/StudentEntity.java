package com.eduxy.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(name="photo")
	private String photo;

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

	public void setInstitueName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getIdProof() {
		return idProof;
	}

	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
}