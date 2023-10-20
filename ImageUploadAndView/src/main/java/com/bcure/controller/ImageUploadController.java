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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bcure.entity.ImageUpload;
import com.bcure.payload.FileResponse;
import com.bcure.service.IImageUploadService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value="/image")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageUploadController {

	@Autowired
	IImageUploadService imageUploadService;

	@Value(value = "${project.image}")
	private String path;

	@PostMapping("/store")
	public ResponseEntity<ImageUpload> imageLoad(@RequestBody ImageUpload imageUpload) {

		ImageUpload uploadImage = imageUploadService.uploadImage(imageUpload);

		return new ResponseEntity<ImageUpload>(uploadImage, HttpStatus.CREATED);

	}

	@GetMapping("/show/{Id}")
	public ResponseEntity<ImageUpload> showImage(@PathVariable Integer Id) {

		ImageUpload showImage = imageUploadService.showImage(Id);

		return ResponseEntity.ok(showImage);

	}

	@PostMapping(value="/stores",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FileResponse> imageStore(@RequestParam("image") MultipartFile image) {

		String fileName = null;
		try {
			fileName = imageUploadService.imageStore(path, image);
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

		InputStream resource = imageUploadService.getImage(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);

		StreamUtils.copy(resource, response.getOutputStream());

	}

}
