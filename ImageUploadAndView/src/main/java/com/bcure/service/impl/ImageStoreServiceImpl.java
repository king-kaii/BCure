package com.bcure.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bcure.service.IImageStoreService;

@Service
public class ImageStoreServiceImpl implements IImageStoreService {
	
	@Value("${project.image}") 
    private String uploadPath;

	@Override
    public void uploadImage(MultipartFile file, String imageName) throws IOException {
        // Generate a unique filename for each image
        String filePath = uploadPath + File.separator + imageName;
        try (OutputStream os = new FileOutputStream(filePath)) {
            os.write(file.getBytes());
        }
    }

}
