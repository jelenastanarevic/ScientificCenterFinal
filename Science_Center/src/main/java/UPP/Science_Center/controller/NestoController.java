package UPP.Science_Center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import UPP.Science_Center.model.User;
import UPP.Science_Center.service.UserService;

@RestController
@RequestMapping("/nesto")
public class NestoController {
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@RequestMapping(
			value = "/start",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> start() {
		
		System.out.println("123");
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
