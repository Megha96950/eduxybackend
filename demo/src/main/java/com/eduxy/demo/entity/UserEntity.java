package com.eduxy.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;



@Entity
@Table(name="user")
public class UserEntity {
	@Id
	@Column(name="email_id")
	private String emailId;
	
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;

	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="role")
	private String role;
 
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="user_email_id")
	private List<AddressEntity> addressEntities;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="user_email_id")
	private List<StudentEntity> studentEntity;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="user_email_id")
	private List<TeacherEntity> teacherEntity;
	
	
	
	public List<StudentEntity> getStudentEntity() {
		return studentEntity;
	}

	public void setStudentEntity(List<StudentEntity> studentEntity) {
		this.studentEntity = studentEntity;
	}

	public List<TeacherEntity> getTeacherEntity() {
		return teacherEntity;
	}

	public void setTeacherEntity(List<TeacherEntity> teacherEntity) {
		this.teacherEntity = teacherEntity;
	}

	public List<AddressEntity> getAddressEntities() {
		return addressEntities;
	}

	public void setAddressEntities(List<AddressEntity> addressEntities) {
		this.addressEntities = addressEntities;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	


}
