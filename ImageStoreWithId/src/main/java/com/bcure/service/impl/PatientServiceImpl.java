package com.bcure.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

/**
 * The `PatientServiceImpl` class implements the `IPatientService` interface and
 * provides methods for managing patient-related operations, including creating
 * patients and uploading prescription files.
 */
@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	ModelMapper modelMapper;

	/**
	 * Creates a new patient based on the provided patient data.
	 *
	 * @param patientDto The data of the patient to be created.
	 * @return A `PatientDto` object representing the created patient.
	 */
	@Override
	public PatientDto craetePatient(PatientDto patientDto) {
		Patient patient = modelMapper.map(patientDto, Patient.class);
		Patient save = patientRepository.save(patient);
		return modelMapper.map(save, PatientDto.class);
	}

	/**
	 * Uploads prescription files for a patient and updates the patient's
	 * information.
	 *
	 * @param files An array of prescription files to be uploaded.
	 * @param path  The base path for storing the uploaded files.
	 * @param id    The unique identifier of the patient.
	 * @return A message indicating the success of the upload.
	 * @throws IOException If there is an error during file handling.
	 */
	@Override
	public String uploadPrescriptions(MultipartFile[] files, String path, Integer id) throws IOException {

		Optional<Patient> findById = patientRepository.findById(id);
		Patient patient = findById.get();
		ArrayList<String> list = new ArrayList<>();

		for (MultipartFile file : files) {
			String filename = file.getOriginalFilename();
			String fullPath = path + File.separator + filename;

			// If the file directory does not exist, create it
			File f = new File(path);
			if (!f.exists()) {
				f.mkdir();
			}

			Files.copy(file.getInputStream(), Paths.get(fullPath));

			// Construct the URL for the uploaded file
			list.add(Constants.BASE_URL + "getImage/" + filename);

			// Update the patient's prescription URL
			patient.setUrl(list.toArray(new String[0]));

			// Debugging: Display the URLs for the uploaded files
			for (String locate : patient.getUrl()) {
				System.out.println("Prescription File URL: " + locate);
			}
		}

		// Save the updated patient information
		patientRepository.save(patient);

		// Debugging: Display the updated patient information
		System.out.println("Updated Patient Information: " + patient);

		return "Prescription(s) Uploaded Successfully";
	}
}
