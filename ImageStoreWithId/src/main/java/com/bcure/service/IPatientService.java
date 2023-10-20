package com.bcure.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.bcure.dto.PatientDto;

/**
 * The IPatientService interface defines a set of methods for managing patient-related operations.
 * Implementing classes are responsible for providing concrete implementations of these methods.
 */


public interface IPatientService {
	
	/**
	 * create_patient
	 * @param patientDto receive data from client at the time of patient registration  
	 * and convert it patient object and store in DB.
	 * @return PatientDto value 
	 */
	
	
	public PatientDto craetePatient(PatientDto patientDto);
	
//	public String uploadPrescription(MultipartFile file, String path, Integer id) throws IOException;
	public String uploadPrescriptions(MultipartFile[] file, String path, Integer id) throws IOException;

}
