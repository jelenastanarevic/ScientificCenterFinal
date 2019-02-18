package UPP.Science_Center.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.User;

@Service
public class NotificationScientificFieldEditorTaskService implements JavaDelegate{
	
	@Autowired
	private EmailService emailService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Article article = (Article)execution.getVariable("articleCreated");
		
		
		emailService.getMail().setSubject("You have been added to check "+ article.getTitle());
		emailService.getMail().setText(article.getEditor().getFirst_name()+ " " + article.getEditor().getLast_name() 
			+".\n A new article has been created for "+article.getMagazine().getTitle()+". You have been added to review it");
		
		
		emailService.getMail().setTo(article.getEditor().getEmail());
		emailService.sendNotificaitionAsync();
		
	}

}
