package UPP.Science_Center.service;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.converter.ListOfFormFieldsToUserConverter;
import UPP.Science_Center.dto.FieldIdNamePairDto;
import UPP.Science_Center.model.User;


@Service
public class CreateNewUserTaskService implements JavaDelegate{

	@Autowired
	private ListOfFormFieldsToUserConverter registerConverter;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		List<FieldIdNamePairDto> dto = (List<FieldIdNamePairDto>) execution.getVariable("registerData");
		System.out.println("jelena");
		User createdUser = registerConverter.convert(dto);
		User saved = null;
		if(createdUser !=null){
			 saved = userService.save(createdUser);
		}
		
		emailService.getMail().setTo(saved.getEmail());
		
		emailService.getMail().setSubject("Registration to Science Center");
		emailService.getMail().setText("Welcome, "+ saved.getFirst_name()+ " " + saved.getLast_name() 
			+".\n You have successfully registered your account on Science Center. Your password is:"
			+saved.getPassword());
		emailService.sendNotificaitionAsync();
		
	}

}
