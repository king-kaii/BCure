package com.bcure.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image/remote")
public class RemoteDirectoryController {

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Save the file to the remote directory on the other PC
            	File remoteDirectory = new File("\\\\192.168.1.39\\BCure Photos\\");

                if (!remoteDirectory.exists()) {
                    remoteDirectory.mkdirs(); 
                }

                File remoteFile = new File(remoteDirectory, file.getOriginalFilename());
                file.transferTo(remoteFile);

                return "File uploaded successfully!";
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed to upload file: " + e.getMessage();
            }
        } else {
            return "File is empty!";
        }
    }
}
