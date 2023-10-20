package com.bcure.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bcure.repo.ImageRepo;

@Service
public class ImageStoreImpl implements IImageStore {
	
	@Autowired
	ImageRepo imageRepo;

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

		String contentType1 = "image/png";
		System.out.println(contentType1);

		if (contentType.equalsIgnoreCase(contentType1)) {
			// File Copy
			Files.copy(file.getInputStream(), Paths.get(filePath));
			System.out.println("File Upload Successfully !!!");
		} else {
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
	
	@Override
	public String imageStores(String path, MultipartFile file, String name) throws IOException {

		// File Name
		String name1 = file.getOriginalFilename();

		// FullPath
		String filePath = path + File.separator + name1;

		// Create folder if not exist
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// File Copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		 Path path2 = Paths.get(filePath);
		 System.out.println("path:  "+path2);
		
//		Image image=new Image();
//		image.setName(name);
//		
//		Image save = imageRepo.save(image);
		
		

		return "file name "+name1+"  with Patient  saved successfully !!";
	}

}
