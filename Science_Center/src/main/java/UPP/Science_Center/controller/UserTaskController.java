package UPP.Science_Center.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import UPP.Science_Center.converter.ListOfFormFieldsToUserConverter;
import UPP.Science_Center.dto.FieldIdNamePairDto;
import UPP.Science_Center.dto.FormFieldsDto;
import UPP.Science_Center.dto.TaskDTO;
import UPP.Science_Center.model.Author;
import UPP.Science_Center.model.Editor;
import UPP.Science_Center.model.Reviewer;
import UPP.Science_Center.model.User;
import UPP.Science_Center.service.AuthorService;
import UPP.Science_Center.service.EditorService;
import UPP.Science_Center.service.ReviewerService;
import UPP.Science_Center.service.UserService;




@RestController
@RequestMapping("/user")
public class UserTaskController {
	
	@Autowired
	private RuntimeService runtimeService;

	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private ReviewerService reviewerService;
	
	@Autowired
	private ListOfFormFieldsToUserConverter registerConverter;

	@Autowired
	private EditorService editorService;
	
	@Autowired
	private AuthorService authorService;
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/startProcess/{idProcess}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> startProcess(@PathVariable String idProcess) {
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(idProcess);
		

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
		return new ResponseEntity<>(new FormFieldsDto(task.getId(), pi.getId(), properties),HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/getCurrentUser",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getCurrentUser() {
		
		User user = userService.getCurrentUser();
		Editor editor = editorService.findByEmail(user.getEmail());
		Author author = authorService.findByEmail(user.getEmail());
		Reviewer reviewer = reviewerService.findByEmail(user.getEmail());
		if(editor!=null){
			if(editor.isHeadEditor()){
			return new ResponseEntity<>("head_editor",HttpStatus.OK);
			}else{
				return new ResponseEntity<>("editor",HttpStatus.OK);
			}
		}else if(author!=null){
			return new ResponseEntity<>("author",HttpStatus.OK);
		}else if(reviewer!=null){
			return new ResponseEntity<>("reviewer",HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/loginUser",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> loginUser(@RequestBody List<FieldIdNamePairDto> dto,HttpServletRequest request) {
		
		User user = null;
		for(FieldIdNamePairDto field : dto){
			if(field.getFieldId().equals("email")){
				user = userService.findByEmail(field.getFieldValue());
			}
				
		}
		
		if(user !=null){
			for(FieldIdNamePairDto field : dto){
				if(field.getFieldId().equals("password")){
					if(user.getPassword().equals(field.getFieldValue())){
						userService.setCurrentUser(user);
						System.out.println(userService.getCurrentUser().getEmail());
						request.getSession().setAttribute("user", user);
						return new ResponseEntity<>(true,HttpStatus.OK);
					}else{
						return new ResponseEntity<>(false,HttpStatus.OK);
					}
				}
					
			}
			
		}else{
			return new ResponseEntity<>(false,HttpStatus.OK);
		}
		return new ResponseEntity<>(false,HttpStatus.OK);
		
 		
        
    }
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/formCheck/{processInstanceId}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> formCheck(@PathVariable String processInstanceId) {
		
		//provera koji je task u pitanju
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).list().get(0);
		System.out.println(task.getName());
		
		if(task.getName().equals("")){
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
        
			return new ResponseEntity<>(new TaskDTO(task.getName(),processInstanceId,task.getId()),HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/getFormFields/{PInsId}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getFormFields(@PathVariable String PInsId) {
		
		//provera koji je task u pitanju
		Task task = taskService.createTaskQuery().processInstanceId(PInsId).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
        
			return new ResponseEntity<>(new FormFieldsDto(task.getId(), PInsId, properties),HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/registerUser/{taskId}",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> registerUser(@RequestBody List<FieldIdNamePairDto> dto,@PathVariable String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FieldIdNamePairDto pair:dto){
		map.put(pair.getFieldId(), pair.getFieldValue());
		}
		runtimeService.setVariable(task.getProcessInstanceId(), "registerData", dto);
	/*	
		User createdUser = registerConverter.convert(dto);
		User saved = userService.save(createdUser);
		*/
		
			formService.submitTaskForm(taskId, map);
			return new ResponseEntity<>(HttpStatus.OK);
			
	}
	
	
	
}
	

	
	
	
	


