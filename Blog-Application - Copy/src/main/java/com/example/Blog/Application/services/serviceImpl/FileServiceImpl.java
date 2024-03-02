package com.example.Blog.Application.services.serviceImpl;

import com.example.Blog.Application.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //file Name
        String name = file.getOriginalFilename();

        // random name generate file
        String randomID = UUID.randomUUID().toString();
        String filaName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

        //fullPath
        String filepath = path + File.separator + filaName1;

        // create folder If not created
        File f = new File(path);
        if (!f.exists()){
            f.mkdir();
        }

        // File copy
        Files.copy(file.getInputStream(), Paths.get(filepath));

        return name;

    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is  = new FileInputStream(fullPath);
        //db logic to return inputStream
        return  is;
    }
}
