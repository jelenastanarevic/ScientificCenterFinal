package UPP.Science_Center.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.User;


@Service
public class ElementaryDataRejectedTaskService implements JavaDelegate{
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Article article = (Article)execution.getVariable("declinedArticle");
		
		emailService.getMail().setFrom(userService.getCurrentUser().getEmail());
		
		emailService.getMail().setSubject("Article "+ article.getTitle() + " has been rejected");
		emailService.getMail().setText(article.getAuthor().getFirst_name()+ " " + article.getAuthor().getLast_name() 
			+".\n Your article has been rejected due to unacceptable title,keywords and/or abstract.");
		
		
		
		emailService.getMail().setTo(article.getAuthor().getEmail());
		emailService.sendNotificaitionAsync();
		
		
		
	}
	
	

}
