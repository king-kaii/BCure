package com.bcure.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.bcure.dto.PatientDto;

public interface IPatientService {
	
	public PatientDto craetePatiennt(PatientDto patientDto);
	
//	public String uploadPrescription(MultipartFile file, String path, Integer id) throws IOException;
	public String uploadPrescriptions(MultipartFile[] file, String path, Integer id) throws IOException;

}
