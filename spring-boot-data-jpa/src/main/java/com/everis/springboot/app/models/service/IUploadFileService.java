package com.everis.springboot.app.models.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
    public Resource loadPicture(String filename) throws MalformedURLException;
    public String getNewPicture(MultipartFile file) throws IOException;
    public boolean deletePicture(String filename);
    
    public void deleteAll();
    public void init() throws IOException;
    
}
