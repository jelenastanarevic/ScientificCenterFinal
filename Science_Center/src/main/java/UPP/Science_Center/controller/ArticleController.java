package UPP.Science_Center.controller;

import java.io.IOException;
import java.util.ArrayList;
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

import UPP.Science_Center.converter.ArticleToArticleDTOConverter;
import UPP.Science_Center.dto.ArticleDTO;
import UPP.Science_Center.dto.FieldIdNamePairDto;
import UPP.Science_Center.dto.OrderDTO;
import UPP.Science_Center.dto.UploadInfo;
import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.ArticlesBought;
import UPP.Science_Center.model.Magazine;
import UPP.Science_Center.model.Reviewer;
import UPP.Science_Center.model.User;
import UPP.Science_Center.service.ArticleService;
import UPP.Science_Center.service.ArticlesBoughtService;
import UPP.Science_Center.service.MagazineService;
import UPP.Science_Center.service.ReviewerService;
import UPP.Science_Center.service.UserService;



@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private ReviewerService reviewerService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private UserService userService;

	
	@Autowired
	private ArticleToArticleDTOConverter articleToArticleDTOConverter;
	
	@Autowired
	private MagazineService magazineService;
	
	/*@Autowired
	private RestTemplate restTemplate;*/
	
	@Autowired
	private ArticlesBoughtService articlesBoughtService;
	
	@CrossOrigin
	@RequestMapping(
			value = "/getCurrent/{PID}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getCurrent(@PathVariable String PID) {
		String id_article = (String) runtimeService.getVariable(PID, "articleId");
		
		
		if(id_article == null){
			return new ResponseEntity<>(new ArticleDTO(),HttpStatus.OK);
		}else{
			Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
			System.out.println(task.getName());
			
			Optional<Article> optArticle= articleService.findOne(Long.parseLong(id_article));
			Article found = optArticle.orElseGet(null);
			ArticleDTO ret = articleToArticleDTOConverter.convert(found);
			ret.setIdTask(task.getId());
			return new ResponseEntity<>(ret,HttpStatus.OK);
		}
	}
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/addArticleForm/{PID}/{idMagazine}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> addArticleForm(@PathVariable String PID, @PathVariable String idMagazine) {
		//metoda za pocetnu stranu sa listom svih NC
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", Long.parseLong(idMagazine));
		
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		System.out.println(task.getName());
		formService.submitTaskForm(task.getId(), map);
        
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/createArticle/{taskId}",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> createArticle(@RequestBody List<FieldIdNamePairDto> dto, @PathVariable String taskId) throws IOException {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FieldIdNamePairDto pair:dto){
		map.put(pair.getFieldId(), pair.getFieldValue());
		}
		
		System.out.println(task.getName());
		runtimeService.setVariable(task.getProcessInstanceId(), "articleDTO", dto);
		
		formService.submitTaskForm(task.getId(), map);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/addPDF",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> createArticle(@RequestBody UploadInfo dto) throws IOException {
		
		String content = dto.getFile_content().substring(28, dto.getFile_content().length());
		String retVal = articleService.saveFile(content, dto.getFile_name());
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/changePDF/{idArticle}/{PID}",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> changePDF(@RequestBody UploadInfo dto,@PathVariable Long idArticle,@PathVariable String PID) throws IOException {
		
		String content = dto.getFile_content().substring(28, dto.getFile_content().length());
		String retVal = articleService.saveFile(content, dto.getFile_name());
		Article article = articleService.findById(idArticle);
		article.setFile_name(dto.getFile_name());
		Article saved = articleService.save(article);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("file_name", dto.getFile_name());
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		runtimeService.setVariable(PID, "articleCreated", saved);
		formService.submitTaskForm(task.getId(), map);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/addCoauthor/{PID}",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> addCoauthor(@RequestBody List<FieldIdNamePairDto> dto, @PathVariable String PID) {
				
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FieldIdNamePairDto pair:dto){
		map.put(pair.getFieldId(), pair.getFieldValue());
		}
		
		runtimeService.setVariable(PID, "coauthorInfo", dto);
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		System.out.println(task.getName());
		
			formService.submitTaskForm(task.getId(), map);
			return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/getArticlesOfMagazine/{idMagazine}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getArticlesOfMagazine(@PathVariable Long idMagazine) {
		Magazine magazine = magazineService.findOne(idMagazine);
		
		List<Article> articleOfMagazine = articleService.findByMagazineId(idMagazine);
		List<ArticleDTO> articlesDTO = articleToArticleDTOConverter.convert(articleOfMagazine);
		return new ResponseEntity<>(articlesDTO,HttpStatus.OK);
		
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/findByIdArticle/{idArticle}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> findByIdArticle(@PathVariable Long idArticle) {
		
		Article article = articleService.findById(idArticle);
		ArticleDTO dto = articleToArticleDTOConverter.convert(article);
		return new ResponseEntity<>(dto,HttpStatus.OK);
		
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/addReviewers/{idTask}/{idArticle}",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> addReviewers(@RequestBody List<String> ids,@PathVariable String  idTask,@PathVariable Long idArticle) {
		String submit = "";
		List<Reviewer> reviewers = new ArrayList<Reviewer>();
		Article article = articleService.findById(idArticle);
		for(String str : ids){
			submit+=ids;
			Reviewer rev = reviewerService.findById(Long.parseLong(str));
			rev.setArticle(article);
			Reviewer saved = reviewerService.save(rev);
			reviewers.add(rev);
			
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("reviewersId", submit);
		
		Task task = taskService.createTaskQuery().taskId(idTask).singleResult();
		runtimeService.setVariable(task.getProcessInstanceId(), "reviewers", reviewers);
		
		formService.submitTaskForm(idTask, map);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/declineElementaryData/{idArticle}/{idTask}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> declineElementaryData(@PathVariable Long idArticle,@PathVariable String idTask) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("notificationContent", "decline");
		Task task = taskService.createTaskQuery().taskId(idTask.toString()).singleResult();
		runtimeService.setVariable(task.getProcessInstanceId(), "declinedArticle", articleService.findById(idArticle));
		
		runtimeService.setVariable(task.getProcessInstanceId(), "acceptable", false);
		formService.submitTaskForm(idTask, map);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/acceptElementaryData/{idArticle}/{idTask}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> acceptElementaryData(@PathVariable Long idArticle,@PathVariable String idTask) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("notificationContent", "accept");
		Task task = taskService.createTaskQuery().taskId(idTask.toString()).singleResult();
		
		runtimeService.setVariable(task.getProcessInstanceId(), "acceptable", true);
		
		formService.submitTaskForm(idTask.toString(), map);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/declinePDF/{idArticle}/{PID}",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> declinePDF(@RequestBody String comment,@PathVariable Long idArticle,@PathVariable String PID) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("formatNotificationContent", comment);
		Article article = articleService.findById(idArticle);
		article.setComment(comment);
		Article saved = articleService.save(article);
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		
		runtimeService.setVariable(task.getProcessInstanceId(), "correctFormat", false);
		runtimeService.setVariable(task.getProcessInstanceId(), "declinedPDFArticle", saved);
		
		
		formService.submitTaskForm(task.getId(), map);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/acceptPDF/{idArticle}/{PID}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> acceptPDF(@PathVariable Long idArticle,@PathVariable String PID) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("formatNotificationContent", "accept");
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		
		runtimeService.setVariable(task.getProcessInstanceId(), "correctFormat", true);
		
		formService.submitTaskForm(task.getId(), map);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}
	
	
	/*@CrossOrigin
	@RequestMapping(
			value = "/buyArticle/{idMagazine}/{idArticle}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> buyArticle(@PathVariable Long idMagazine,@PathVariable Long idArticle,HttpServletRequest request) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		Magazine magazine = magazineService.findOne(idMagazine);
		Optional<Article> articleOPT = articleService.findOne(idArticle);
		User creator = (User)request.getSession().getAttribute("user");
		
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setArticleTitle(articleOPT.orElseGet(null).getTitle());
		orderDTO.setArticlePrice(articleOPT.orElseGet(null).getPrice());
		//orderDTO.setBuyMagazine(false);
		orderDTO.setMagazineIssn(magazine.getIssn());
		orderDTO.setUserEmail("smiljana.dragoljevic-1@gmail.com");
		
	
	      //RestTemplate client=new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity< OrderDTO> entity = new HttpEntity< OrderDTO>(orderDTO, headers);
		String orderDTOStr = restTemplate.postForObject("http://192.168.43.30:9001/lb/order/create", entity,
				String.class);
		
		return new ResponseEntity<>(orderDTOStr,HttpStatus.OK);
		
		
	}*/
	
	@CrossOrigin
	@RequestMapping(
			value = "/getArticleById/{idArticle}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getArticleById(@PathVariable Long idArticle) {
		Optional<Article> article = articleService.findOne(idArticle);
		ArticleDTO articleDTO = articleToArticleDTOConverter.convert(article.orElseGet(null));
		
        
		return new ResponseEntity<>(articleDTO,HttpStatus.OK);
		
	}
	
	//kreiranje kupljenih clanaka
	//many to many tabela ArticlesBought sadrzi usera i radove
	@CrossOrigin
	@RequestMapping(
			value = "/setBought",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> setBought(@RequestBody List<OrderDTO> orders) {
		List<Article> articlesToShow = new ArrayList<Article>();
		
		for(OrderDTO orderDTO : orders){
			if(orderDTO.getArticlePrice()!=null && orderDTO.getArticleTitle()!=null){
				Magazine magazine = magazineService.findByIssn(orderDTO.getMagazineIssn());
				if(!(articlesToShow.contains(articleService.findByMagazineIdAndTitle(magazine.getId(),orderDTO.getArticleTitle())))){
				articlesToShow.add(articleService.findByMagazineIdAndTitle(magazine.getId(),orderDTO.getArticleTitle()));
				}
			}else{
				Magazine magazine = magazineService.findByIssn(orderDTO.getMagazineIssn());
				List<Article> articles = articleService.findByMagazineId(magazine.getId());
				for(Article art : articles){
					if(!(articlesToShow.contains(art))){
						articlesToShow.add(art);
					}
				}
				
				
			}
		}
			User user = userService.findByEmail(orders.get(0).getUserEmail());
			for(Article article : articlesToShow){
				ArticlesBought bought = new ArticlesBought();
				bought.setArticle(article);
				bought.setUser(user);
				ArticlesBought saved = articlesBoughtService.save(bought);
			}
		 //ovde mozes redirektovati na svoj html u svojoj nc
			//tabela za ispis svih kuljenih clanaka je u allArticlesBought.js
		String retStr = "http://localhost:1444/allArticlesBought.html";
		return new ResponseEntity<>(retStr,HttpStatus.OK);
		
	}
	
	//za ispis svih kupljenih clanaka 
	@CrossOrigin
	@RequestMapping(
			value = "/getArticlesOfMagazineBought",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getArticlesOfMagazineBought() {
		
        User user = userService.findByEmail("smiljana.dragoljevic-1@gmail.com");
        List<ArticlesBought> bought = articlesBoughtService.findByUserId(user.getId());
        List<Article> articles = new ArrayList<Article>();
        
        for(ArticlesBought ab : bought){
        	articles.add(ab.getArticle());
        }
        List<ArticleDTO> articlesDTO = articleToArticleDTOConverter.convert(articles);
		return new ResponseEntity<>(articlesDTO,HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/getProcessInstanceByTaskId/{idTask}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getProcessInstanceByTaskId(@PathVariable String idTask) {
		
		Task task = taskService.createTaskQuery().taskId(idTask.toString()).singleResult();
		
		return new ResponseEntity<>(task.getProcessInstanceId(),HttpStatus.OK);
		
	}
	
	

}
