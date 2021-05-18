package com.eduxy.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teacher")
public class TeacherEntity {
	
	@Id
	@Column(name="teacher_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int TeacherId;
	
	@Column(name="subject_to_teach")
	private String Subjects;
	
	@Column(name="description")
	private String Description;
	
	@Column(name="higher_qualification")
	private String HigherQualification;
	
	@Column(name="id_proof")
	private String IdProof;
	
	@Column(name="degree_photo")
	private String DegreePhoto;
	
	@Column(name="id_photo")
	private String IdPhoto;
	
	@Column(name="fees_per_student")
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
