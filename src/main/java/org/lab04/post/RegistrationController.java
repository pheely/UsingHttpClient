package org.lab04.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/registration")
	public ResponseEntity<?> sayHello(@RequestParam("name") String name, 
			@RequestParam(value="password", required=false) String password,
			@RequestParam(value="mailId", required=false) String mailId) {
		logger.info("name = " + name);
		logger.info("password = " + password);
		logger.info("mailId = " + mailId);
		
		return new ResponseEntity<String>("This is the body. Hello " + name, HttpStatus.ACCEPTED);
	}
}
