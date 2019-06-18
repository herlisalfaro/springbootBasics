package com.everis.springboot.app.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	    Authentication authentication) throws IOException, ServletException {
	
	SessionFlashMapManager FlashMapManager = new SessionFlashMapManager();
	FlashMap flashMap = new FlashMap();
	
	flashMap.put("success","Hello, " + authentication.getName() + ",You're Succesfully Logged!");
	FlashMapManager.saveOutputFlashMap(flashMap, request, response);
	
	if(authentication != null) {
	    logger.info("User " + authentication + " Has Been Logged Successfully");
	    
	}
	
	super.onAuthenticationSuccess(request, response, authentication);
    }

    
}
