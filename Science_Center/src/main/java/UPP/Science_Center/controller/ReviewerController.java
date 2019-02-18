package UPP.Science_Center.controller;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.TaskQueryImpl;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import UPP.Science_Center.converter.ArticleToArticleDTOConverter;
import UPP.Science_Center.converter.ReviewerToAuthorDTOConverter;
import UPP.Science_Center.dto.ArticleDTO;
import UPP.Science_Center.dto.AuthorDTO;
import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.Reviewer;
import UPP.Science_Center.service.ArticleService;
import UPP.Science_Center.service.ReviewerService;
import UPP.Science_Center.service.UserService;

@Controller
@RequestMapping("/reviewer")
public class ReviewerController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ReviewerService reviewerService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private ArticleToArticleDTOConverter articleToArticleDTOConverter;
	
	@Autowired
	private ReviewerToAuthorDTOConverter reviewerToAuthorDTOConverter;
	@CrossOrigin
	@RequestMapping(
			value = "/findReviewers/{idArticle}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> findReviewers(@PathVariable Long idArticle) {
		//metoda za pocetnu stranu sa listom svih NC
		Article article = articleService.findById(idArticle);
        List<Reviewer> reviewers = reviewerService.findByScientificId(article.getScientific_field().getScientific_field_id());
		List<AuthorDTO> dto = reviewerToAuthorDTOConverter.convertList(reviewers);
        return new ResponseEntity<>(dto, HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/getReviewerTasks",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getReviewerTasks() {
		List<Task> tasksForHeadEditor =  (List<Task>) taskService.createTaskQuery().taskCandidateUser(userService.getCurrentUser().getEmail()).list();
		List<ArticleDTO> articlesToReviewDTO = new ArrayList<ArticleDTO>();
		for(Task task : tasksForHeadEditor){
			
			taskService.claim(task.getId(),userService.getCurrentUser().getEmail());
			taskService.saveTask(task);
			System.out.println(task.getAssignee());
			String processInstanceId = task.getProcessInstanceId();
			ArticleDTO dto = articleToArticleDTOConverter.convert((Article)runtimeService.getVariable(processInstanceId, "articleCreated"));
			dto.setIdTask(task.getId());
			dto.setProcessInstanceId(processInstanceId);
			articlesToReviewDTO.add(dto);
		}
		
        return new ResponseEntity<>(articlesToReviewDTO, HttpStatus.OK);
		
	}
	

}
