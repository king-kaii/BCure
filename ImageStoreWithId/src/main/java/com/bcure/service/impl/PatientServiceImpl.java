package com.bcure.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bcure.constant.Constants;
import com.bcure.dto.PatientDto;
import com.bcure.entity.Patient;
import com.bcure.repo.PatientRepository;
import com.bcure.service.IPatientService;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public PatientDto craetePatiennt(PatientDto patientDto) {

		System.out.println("before convertService");
		Patient patient = modelMapper.map(patientDto, Patient.class);

		System.out.println("Service");

		Patient save = patientRepository.save(patient);

		return modelMapper.map(save, PatientDto.class);
	}

	@Override
	public String uploadPrescription(MultipartFile file, String path, Integer id) throws IOException {
		String filename = file.getOriginalFilename();

		String fullPath = path + File.separator + filename;

		// If file Directory not exist
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		Files.copy(file.getInputStream(), Paths.get(fullPath));

		String location = Constants.BASE_URL + "getImage/" + filename;

		Optional<Patient> findById = patientRepository.findById(id);

		boolean present = findById.isPresent();

		System.out.println(present);

		if (findById.isPresent()) {

			Patient patient = findById.get();
			System.out.println(patient);
//			patient.setUrl(location);
			patientRepository.save(patient);
			System.out.println(patient);
		}

		return filename;

	}

	@Override
	public String uploadPrescriptions(MultipartFile[] files, String path, Integer id) throws IOException {
		
		

		for (MultipartFile file : files) {

			String filename = file.getOriginalFilename();

			String fullPath = path + File.separator + filename;

			// If file Directory not exist
			File f = new File(path);
			if (!f.exists()) {
				f.mkdir();
			}

			Files.copy(file.getInputStream(), Paths.get(fullPath));

		String[]	location = { (Constants.BASE_URL + "getImage/" + filename)};


		}

		Optional<Patient> findById = patientRepository.findById(id);
		
		boolean present = findById.isPresent();
		
		System.out.println(present);
		if (findById.isPresent()) {
			
			Patient patient = findById.get();
			System.out.println(patient);
//			patient.setUrl(location);
			patientRepository.save(patient);
			System.out.println(patient);
		}
		return "Upload Successfully";

	}

}
