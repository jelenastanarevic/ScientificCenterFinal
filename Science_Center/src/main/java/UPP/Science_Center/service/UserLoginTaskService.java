package UPP.Science_Center.service;

import java.util.List;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.dto.FieldIdNamePairDto;
import UPP.Science_Center.model.User;



@Service
public class UserLoginTaskService implements JavaDelegate{

	@Autowired
	private UserService userService;
	@Autowired
	IdentityService identityService;
	
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		List<FieldIdNamePairDto> dto = (List<FieldIdNamePairDto>) execution.getVariable("loginData");
		User user = null;
		for(FieldIdNamePairDto field : dto){
			if(field.getFieldId().equals("email")){
				user = userService.findByEmail(field.getFieldValue());
			}
				
		}
			if(user == null){
				org.camunda.bpm.engine.identity.User loggedInUser = identityService.newUser("");
				loggedInUser.setEmail(user.getEmail());
				loggedInUser.setId(user.getId().toString());
				loggedInUser.setPassword(user.getPassword());
				identityService.saveUser(loggedInUser);
					execution.setVariable("userExists", false);
					
				}else{
					
					execution.setVariable("userExists", true);
					execution.setVariable("userLoggedIn", user);
				}
				
				
			
		
		
	}

}
