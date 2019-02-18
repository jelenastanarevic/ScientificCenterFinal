package UPP.Science_Center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import UPP.Science_Center.dto.UserDTO;
import UPP.Science_Center.model.User;
import UPP.Science_Center.service.UserService;


@RestController
@RequestMapping("/user1")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@RequestMapping(
			value = "/loginUser",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> loginUser(@RequestBody UserDTO dto) {
		/*HashMap<String, Object> map = new HashMap<String, Object>();
		for(FieldIdNamePairDto pair:dto){
		map.put(pair.getFieldId(), pair.getFieldValue());
		}*/
		
		User user = userService.findByEmail(dto.getEmail());
		
		
		//User user = (User)request.getSession().getAttribute("user");
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(
			value = "/start",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> start() {
		
		System.out.println("1");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
