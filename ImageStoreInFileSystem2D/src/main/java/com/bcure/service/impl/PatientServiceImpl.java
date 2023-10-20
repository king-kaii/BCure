package com.bcure.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bcure.entity.Patient;
import com.bcure.repo.PatientRepository;
import com.bcure.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	PatientRepository patientRepository;

	@Override
	public String imageStore(String path, MultipartFile file, Patient patient) throws IOException {

		// File Name
		String name = file.getOriginalFilename();

		// FullPath
		String filePath = path + File.separator + name;

		// Create folder if not exist
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// File Copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		Patient save = patientRepository.save(patient);

		return "file name "+name+"Patient id :"+save.getId()+" saved successfully !!";
	}

	@Override
	public InputStream getImage(String path, String fileName) throws FileNotFoundException {

		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);

		return is;
	}

}
