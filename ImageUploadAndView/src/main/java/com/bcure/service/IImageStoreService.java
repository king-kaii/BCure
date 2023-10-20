package com.bcure.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IImageStoreService {
	
	public void uploadImage(MultipartFile file, String imageName) throws IOException;

}
