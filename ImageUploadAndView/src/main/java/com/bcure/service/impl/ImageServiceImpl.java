package com.bcure.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bcure.entity.ImageUpload;
import com.bcure.repo.ImageUploadRepository;
import com.bcure.service.IImageUploadService;

@Service
public class ImageServiceImpl implements IImageUploadService {

	@Autowired
	ImageUploadRepository imageUploadRepository;

	@Override
	public ImageUpload uploadImage(ImageUpload upload) {
		ImageUpload IUpload = imageUploadRepository.save(upload);
		return IUpload;
	}

	@Override
	public ImageUpload showImage(Integer id) {
		Optional<ImageUpload> findById = imageUploadRepository.findById(id);
		return findById.get();
	}

	@Override
	public String imageStore(String path, MultipartFile file) throws IOException {

		// File Name
		String name = file.getOriginalFilename();
		
		String contentType = file.getContentType();
		System.out.println(contentType);
		
		

		// FullPath
		String filePath = path + File.separator + name;

		// Create folder if not exist
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		
		String contentType1 ="image/png";
		System.out.println(contentType1);

		if(contentType.equalsIgnoreCase(contentType1)) {
		// File Copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		   System.out.println("File Upload Successfully !!!");
		}else {
			System.out.println("Only image/png file accepted !!!");
		}

		return name;
	}

	@Override
	public InputStream getImage(String path, String fileName) throws FileNotFoundException {

		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);

		return is;
	}

}
