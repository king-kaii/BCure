package com.bcure.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bcure.entity.Patient;
import com.bcure.payload.FileResponse;
import com.bcure.service.IPatientService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

	@Autowired
	IPatientService patientService;

	@Value(value = "${project.image}")
	private String path;

	@PostMapping("/stores")
	public ResponseEntity<FileResponse> imageStore(
			@RequestPart Patient patient,
			@RequestPart("image") MultipartFile image) {

		String fileName = null;
		try {
			fileName = patientService.imageStore(path, image, patient);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new FileResponse(null, "Image is not stored.."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(new FileResponse(fileName, "Image is successfully stored.."), HttpStatus.CREATED);

	}

	@GetMapping(value = "/view/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void viewImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

		InputStream resource = patientService.getImage(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);

		StreamUtils.copy(resource, response.getOutputStream());

	}

}
