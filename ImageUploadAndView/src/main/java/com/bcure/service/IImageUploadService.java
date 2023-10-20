package com.bcure.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.bcure.entity.ImageUpload;


public interface IImageUploadService {
	
	public ImageUpload uploadImage(ImageUpload ImageUpload);
	
	public ImageUpload showImage(Integer id);
	
	public String imageStore(String path, MultipartFile file) throws IOException;
	
	InputStream getImage(String path, String fileName) throws FileNotFoundException;
	
	

}
