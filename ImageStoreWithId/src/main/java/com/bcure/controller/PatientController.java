package com.bcure.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bcure.dto.PatientDto;
import com.bcure.payload.FileRespnse;
import com.bcure.service.IPatientService;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:4200")
//@Api("PatientController")
public class PatientController {
	
	@Value("${image.path}")
	String path;
	
	@Autowired
	IPatientService patientService;
	
	@PostMapping("/created")
//	@ApiOperation(value = "create Patient")
	public ResponseEntity<Integer> createPatient(@RequestBody PatientDto patientDto){
		System.out.println("Dto accepted");
		PatientDto createPatient = patientService.craetePatiennt(patientDto);
		System.out.println("service called");
		
		return new ResponseEntity<Integer>(createPatient.getId(), HttpStatus.CREATED);
		
	}
	
	
	@PutMapping(value = "/upload/prescription/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	@ApiOperation(value = "uploadPrescription of Patient")
	public ResponseEntity<FileRespnse> uploadPrescription(@RequestPart MultipartFile file,@PathVariable Integer id) throws IOException{
		
		String originalFilename = file.getOriginalFilename();
		patientService.uploadPrescription(file, path, id);
		
		
		return new ResponseEntity<FileRespnse>(new FileRespnse(originalFilename," File saved..."), HttpStatus.OK);
	}
	
	@PutMapping(value = "/upload/prescriptions/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//	@ApiOperation(value = "uploadPrescription of Patient")
	public ResponseEntity<FileRespnse> uploadPrescriptions(@RequestPart MultipartFile[] files,@PathVariable Integer id) throws IOException{
	
		patientService.uploadPrescriptions(files, path, id);

		return new ResponseEntity<FileRespnse>(new FileRespnse("all "," File saved..."), HttpStatus.OK);
	}
	
	

}
