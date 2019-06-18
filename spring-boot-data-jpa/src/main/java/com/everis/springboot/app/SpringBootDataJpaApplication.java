package com.everis.springboot.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.everis.springboot.app.models.service.IUploadFileService;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner{

    	private final Logger logger = LoggerFactory.getLogger(getClass());
    
    	@Autowired
    	private IUploadFileService uploadFileService;
    	
    	 @Autowired
    	    private BCryptPasswordEncoder passwordEncoder;
	
    	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	    uploadFileService.deleteAll();
	    uploadFileService.init();  
	    logger.info("Folder 'uploads' Created");
	    
	    String password = "12345";
	    for(int i = 0; i < 2; i++) {
		String bcryptPassword = passwordEncoder.encode(password);
		System.out.println(bcryptPassword);
	    }
	}

}
