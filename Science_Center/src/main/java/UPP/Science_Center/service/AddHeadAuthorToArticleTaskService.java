package UPP.Science_Center.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.Editor;
import UPP.Science_Center.model.Magazine;
import UPP.Science_Center.model.User;


@Service
public class AddHeadAuthorToArticleTaskService implements JavaDelegate{

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	 
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long articleId = Long.parseLong((String)execution.getVariable("articleId"));
		
		if(articleId !=null){
			Optional<Article> currentArticleOpt = articleService.findOne(articleId);
			Article currentArticle = currentArticleOpt.orElseGet(null);
			Magazine magazine = currentArticle.getMagazine();
			Editor editor = magazine.getHead_editor();
			
			execution.setVariable("article",currentArticle);
			
			emailService.getMail().setTo(editor.getEmail());
			
			emailService.getMail().setSubject("New article request for "+ magazine.getTitle());
			emailService.getMail().setText(editor.getFirst_name()+ " " + editor.getLast_name() 
				+".\n A new article has been created for "+magazine.getTitle()+". Please review it");
			emailService.sendNotificaitionAsync();
			
			User user = userService.getCurrentUser();
			emailService.getMail().setTo(user.getEmail());
			
			emailService.getMail().setSubject("New article request for "+ magazine.getTitle());
			emailService.getMail().setText(user.getFirst_name()+ " " + user.getLast_name() 
				+".\n You have successfully created a new article has been created for "+magazine.getTitle()+". It has been sent for reviewing");
			emailService.sendNotificaitionAsync();
		}
		
	}

}
