package UPP.Science_Center.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.Article;

@Service
public class ReformatPDFNotificationTaskService implements JavaDelegate{

	@Autowired
	private EmailService emailService;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Article article = (Article)execution.getVariable("article");
		emailService.getMail().setSubject("Wrong PDF format for "+ article.getTitle());
		emailService.getMail().setText(article.getAuthor().getFirst_name()+ " " + article.getAuthor().getLast_name() 
			+".\n The article for "+article.getMagazine().getTitle()+" has the wrong .pdf format. Please add new .pdf file");
		
		
		emailService.getMail().setTo(article.getAuthor().getEmail());
		emailService.sendNotificaitionAsync();
	}

}
