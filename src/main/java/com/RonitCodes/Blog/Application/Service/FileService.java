package com.RonitCodes.Blog.Application.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileService {

    public String uploadFile(String path, MultipartFile file) throws IOException {

        // Validate
        if(file.isEmpty()){
            throw new RuntimeException("Image file is empty");
        }

        // file name
        String fileName = file.getOriginalFilename();
        String filePath = path + File.separator + fileName;

        // 4. Extract file extension safely
        String extension = "";
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1) {
            extension = fileName.substring(dotIndex);
        }

        String uniqueFilename = UUID.randomUUID().toString() + extension;

        // create folder if not created
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        // copy file
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return uniqueFilename;
    }

    // Serve the client
    public InputStream getResource(String path, String fileName) throws IOException {

        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }

}
