package UPP.Science_Center.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import UPP.Science_Center.converter.ScientificFieldToScientificFieldDTOConverter;
import UPP.Science_Center.dto.FieldIdNamePairDto;
import UPP.Science_Center.dto.ScientificFieldDTO;
import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.ScientificField;
import UPP.Science_Center.service.ArticleService;
import UPP.Science_Center.service.ScientificFieldService;



@Controller
@RequestMapping("/scientific_field")
public class ScientificFieldController {
	
	@Autowired
	private ScientificFieldService scientificFieldService;
	
	@Autowired
	private ScientificFieldToScientificFieldDTOConverter scientificFieldConverter;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private ArticleService articleService;
	
	@CrossOrigin
	@RequestMapping(
			value = "/getAllScientificFields",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getAllScientificFields() {
		//metoda za pocetnu stranu sa listom svih naucnih oblasti
		List<ScientificField> allScientificFields = scientificFieldService.findAll();
		List<ScientificFieldDTO> allScientificFieldsDTO = scientificFieldConverter.convert(allScientificFields);
		return new ResponseEntity<>(allScientificFieldsDTO,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/addFieldToArticle/{taskId}",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> addFieldToArticle(@RequestBody List<FieldIdNamePairDto> dto, @PathVariable String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		System.out.println(task.getName());
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FieldIdNamePairDto pair:dto){
			if(!(pair.getFieldId().equals("changeArticle"))){
				map.put(pair.getFieldId(), pair.getFieldValue());
			}
		}
		
		runtimeService.setVariable(task.getProcessInstanceId(), "fieldDTO", dto);
		formService.submitTaskForm(task.getId(), map);
		
		//ScientificField scField = scientificFieldService.findOne(Long.parseLong(map.get("id").toString()));
		//Long foundId = (Long) runtimeService.getVariable(task.getProcessInstanceId(),"articleId");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/getScientificFieldById/{idScField}/{PID}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getScientificFieldById(@PathVariable String idScField,@PathVariable String PID) {
		
		ScientificField found  = scientificFieldService.findOne(Long.parseLong(idScField));
		Optional<Article> foundOpt = articleService.findOne(Long.parseLong((String) runtimeService.getVariable(PID, "articleId")));
		
		
		if(found !=null){
			if(foundOpt.orElseGet(null).getScientific_field() == null){
				articleService.addField(foundOpt.orElse(null),found);
				ScientificFieldDTO foundDTO = scientificFieldConverter.convert(found);
				return new ResponseEntity<>(foundDTO,HttpStatus.OK);
			}else{
				return new ResponseEntity<>(null,HttpStatus.OK);
			}
		}else{
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
	}
	
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/removeScientificFieldById/{PID}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> removeScientificFieldById(@PathVariable String PID) {
		
		Optional<Article> foundOpt = articleService.findOne(Long.parseLong((String) runtimeService.getVariable(PID, "articleId")));
		articleService.removeField(foundOpt.orElse(null));
		Article found = foundOpt.orElseGet(null);
		if(found !=null){
			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	

}
