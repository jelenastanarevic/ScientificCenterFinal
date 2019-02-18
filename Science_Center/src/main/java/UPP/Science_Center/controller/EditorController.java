package UPP.Science_Center.controller;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import UPP.Science_Center.converter.ArticleToArticleDTOConverter;
import UPP.Science_Center.dto.ArticleDTO;
import UPP.Science_Center.model.Article;
import UPP.Science_Center.service.ArticleService;
import UPP.Science_Center.service.UserService;

@Controller
@RequestMapping("/editor")
public class EditorController {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleToArticleDTOConverter articleToArticleDTOConverter;
	
	@CrossOrigin
	@RequestMapping(
			value = "/getHeadEditorTasks",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getHeadEditorTasks() {
		List<Task> tasksForHeadEditor = taskService.createTaskQuery().taskAssignee(userService.getCurrentUser().getEmail()).list();
		List<ArticleDTO> articlesToReviewDTO = new ArrayList<ArticleDTO>();
		for(Task task : tasksForHeadEditor){
			if(task.getName().equals("Head editor reviews title, keywords and apsract")){
			String processInstanceId = task.getProcessInstanceId();
			ArticleDTO dto = articleToArticleDTOConverter.convert((Article)runtimeService.getVariable(processInstanceId, "articleCreated"));
			dto.setIdTask(task.getId());
			dto.setProcessInstanceId(processInstanceId);
			articlesToReviewDTO.add(dto);
			}
		
		}
		
		return new ResponseEntity<>(articlesToReviewDTO,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/getEditorTasks",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getEditorTasks() {
		List<Task> tasksForHeadEditor = taskService.createTaskQuery().taskAssignee(userService.getCurrentUser().getEmail()).list();
		List<ArticleDTO> articlesToReviewDTO = new ArrayList<ArticleDTO>();
		for(Task task : tasksForHeadEditor){
			if(task.getName().equals("Choose reviewers")){
			String processInstanceId = task.getProcessInstanceId();
			ArticleDTO dto = articleToArticleDTOConverter.convert((Article)runtimeService.getVariable(processInstanceId, "articleCreated"));
			dto.setIdTask(task.getId());
			dto.setProcessInstanceId(processInstanceId);
			articlesToReviewDTO.add(dto);
			}
		
		}
		
		return new ResponseEntity<>(articlesToReviewDTO,HttpStatus.OK);
	}

}
