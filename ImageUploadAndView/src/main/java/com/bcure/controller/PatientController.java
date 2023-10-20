package com.bcure.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bcure.entity.Patient;
import com.bcure.repo.PatientRepository;
import com.bcure.service.IImageStoreService;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private IImageStoreService imageStoreService;

	@PostMapping("/upload-image")
	public ResponseEntity<String> uploadImage(
			@RequestParam("image") MultipartFile image,
			@RequestParam("imageName") String imageName,
			@RequestBody Patient patient) {
		
		try {
			// Save image to the file system
			imageStoreService.uploadImage(image, imageName);
			//patient.setImageName(imageName);
			patientRepository.save(patient);

			return ResponseEntity.ok("Image uploaded successfully.");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
		}
	}
}