package com.bcure.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;


public interface IImageStore {

	String imageStore(String path, MultipartFile file) throws IOException;

	InputStream getImage(String path, String fileName) throws FileNotFoundException;
	
	public String imageStores(String path, MultipartFile file, String name) throws IOException;


}
