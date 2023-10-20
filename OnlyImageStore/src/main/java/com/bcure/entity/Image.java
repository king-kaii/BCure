package com.bcure.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String gender;

	private int age;

	@Column(name = "symptoms")
	private String symptoms;

	// @CollectionTable(name = "patient_diseases", joinColumns = @JoinColumn(name =
	// "patient_id"))
	// @Column(name = "disease")
	// @ElementCollection
	 private List<String> disEase;

//	public List<String> getDisEase() {
//		return disEase;
//	}
//
//	public void setDisEase(List<String> disEase) {
//		this.disEase = disEase;
//	}
//
//	public int getAge() {
//		return age;
//	}
//
//	public void setAge(int age) {
//		this.age = age;
//	}
//
//	public String getSymptoms() {
//		return symptoms;
//	}
//
//	public void setSymptoms(String symptoms) {
//		this.symptoms = symptoms;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

}
