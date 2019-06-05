package com.everis.springboot.app.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final static String UPLOADS_FOLDER = "uploads";

    @Override
    public Resource loadPicture(String filename) throws MalformedURLException {
	Path picturePath = getPath(filename);
	logger.info("El picturePath es: " + picturePath);
	Resource resource = null;
	resource = new UrlResource(picturePath.toUri());
	if (!resource.exists() || !resource.isReadable()) {
	    throw new RuntimeException("Error: Cannot load picture " + picturePath.toString());
	}
	return resource;
    }

    @Override
    public String getNewPicture(MultipartFile file) throws IOException {
	String uniqueFileName = UUID.randomUUID().toString() + file.getOriginalFilename();
	Path rootPath = getPath(uniqueFileName);
	logger.info("rootPath: " + rootPath);
	Files.copy(file.getInputStream(), rootPath);
	return uniqueFileName;
    }

    @Override
    public boolean deletePicture(String filename) {
	Path recoveryPath = getPath(filename);
	File fileToDelete = recoveryPath.toFile();
	if (fileToDelete.exists() && fileToDelete.canRead()) {
	    if (fileToDelete.delete()) {
		return true;
	    }
	}
	return false;
    }

    public Path getPath(String filename) {
	return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }

    @Override
    public void deleteAll() {
	FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
	Files.createDirectories(Paths.get(UPLOADS_FOLDER));
	
    }
}
