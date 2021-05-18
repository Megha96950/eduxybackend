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
	private int StudentId;
	
	@Column(name="subject_to_study")
	private String Subjects;
	
	@Column(name="institute_name")
	private String InstitueName;
	
	@Column(name="id_proof")
	private String IdProof;
	
	@Column(name="photo")
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
