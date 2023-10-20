package com.bcure.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
//@NoArgsConstructor
@AllArgsConstructor
public class Patient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
//    @Column(columnDefinition = "VARCHAR(255)")
    private String symptoms;
    private Integer age;
    private String gender;
    private String[] previousDisease;
    private String imageName;

    @JsonCreator
    public Patient() {
    }
}
// JSON Data--> {  
// "name": "kaibalya",
// "symptoms": ["Fever","Headache"],
// "age": 27,
// "gender":"Male",
// "previousDisease": [ "Diabetes","HighSugar"],
// "imageName": "nit.png"
// }
