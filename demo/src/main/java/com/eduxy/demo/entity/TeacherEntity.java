package com.eduxy.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;


@Entity
@Table(name="teacher")
public class TeacherEntity {
	
	@Id
	@Column(name="teacher_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int teacherId;
	
	@Column(name="subject_to_teach")
    private String subjects;
	
	@Column(name="description")
	private String description;
	
	@Column(name="higher_qualification")
	private String higherQualification;
	
	@Column(name="id_proof")
	private String idProof;
	
	@Column(name="degree_photo",length = 1000)
	private byte[] DegreePhoto;
	
	@Column(name="id_photo",length = 1000)
	private String idPhoto;
	
	@Column(name="fees_per_student")
	private String feesCharged;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="teacher_id")
	private List<TeacherDataEntity> data;
	
	@Column(name="user_email_id")
	private String emailId;
     
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public List<TeacherDataEntity> getData() {
		return data;
	}
	public void setData(List<TeacherDataEntity> data) {
		this.data = data;
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
		return DegreePhoto;
	}
	public void setDegreePhoto(byte[] degreePhoto) {
		DegreePhoto = degreePhoto;
	}
	public String getIdPhoto() {
		return idPhoto;
	}
	public void setIdPhoto(String idPhoto) {
		this.idPhoto = idPhoto;
	}
	@Field
	public String getFeesCharged() {
		return feesCharged;
	}
	public void setFeesCharged(String feesCharged) {
		this.feesCharged = feesCharged;
	}
	
	

}
