package com.bcure.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bcure.dto.ImageDto;
import com.bcure.entity.Image;
import com.bcure.payload.FileResponse;
import com.bcure.repo.ImageRepo;
import com.bcure.service.IImageStore;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {
	
	@Autowired
	IImageStore imageStore;
	
	@Autowired
	ImageRepo imageRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Value(value = "${image.path}")
	String path;
	
	@PostMapping(value="/store",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FileResponse> imageStore(@RequestParam("image") MultipartFile image) {

		String fileName = null;
		try {
			
			fileName = imageStore.imageStore(path, image);
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

		InputStream resource = imageStore.getImage(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);

		StreamUtils.copy(resource, response.getOutputStream());

	}
	
	@PostMapping(value = "/stores",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FileResponse> imageStore(
			@RequestPart String name,
			@RequestPart String gender,
			@RequestPart String age,
			@RequestPart String symptoms,
			@RequestPart String disEase[],
			@RequestPart("image") MultipartFile image) {

		String fileName = null;
		try {
			fileName = imageStore.imageStores(path, image, name);
			
			ImageDto imageDto=new ImageDto();
			imageDto.setAge(age);
			imageDto.setDisEase(disEase);
			
			
			String age2 = imageDto.getAge();
			System.out.println(age2);
			
			
	        Class<?> dataType = age2.getClass();
	        System.out.println("Data type of age2: " + dataType.getName()); // To Display the DatType of a Variable
	        
	        String[] disEase3 = imageDto.getDisEase();
	        
	        Class<? extends String[]> dataType3 = disEase3.getClass();
	        System.out.println("Data type of disEase3: " + dataType3.getName()); // To Display the DatType of a Variable
	        
	        
			
			Image images=modelMapper.map(imageDto, Image.class);
			
			Integer age3 = images.getAge();
			System.out.println(age3);
			
			
			Class<?> dataType1 = age3.getClass();
	        System.out.println("Data type of age3: " + dataType1.getName()); // To Display the DatType of a Variable
			
	        List<String> disEase2 = images.getDisEase();
	        
	        System.out.println(disEase2);
	        
	        Class<?> dataType2 = disEase.getClass();
	        System.out.println("Data type of disEase: " + dataType2.getName()); // To Display the DatType of a Variable
	        
			images.setName(name);
			images.setGender(gender);
			images.setSymptoms(symptoms);
//			images.setDisEase(disEase);
			
			
			imageRepo.save(images);
			
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new FileResponse(null, "Image is not stored.."),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(new FileResponse(fileName, "Image is successfully stored.."), HttpStatus.CREATED);

	}


}
