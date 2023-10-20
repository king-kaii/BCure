package com.bcure.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.bcure.entity.Patient;


public interface IPatientService {
	
	public String imageStore(String path, MultipartFile file, Patient patient) throws IOException;
	
	InputStream getImage(String path, String fileName) throws FileNotFoundException;
	
	

}
