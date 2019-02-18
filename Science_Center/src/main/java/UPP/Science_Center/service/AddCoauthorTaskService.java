package UPP.Science_Center.service;

import java.util.List;
import java.util.Optional;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.converter.ListOfFormFieldsToUserConverter;
import UPP.Science_Center.dto.FieldIdNamePairDto;
import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.Author;
import UPP.Science_Center.model.Coauthor;
import UPP.Science_Center.model.User;



@Service
public class AddCoauthorTaskService implements JavaDelegate{

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ListOfFormFieldsToUserConverter userConverter;
	
	@Autowired
	private CoauthorService coauthorService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		List<FieldIdNamePairDto> dto = (List<FieldIdNamePairDto>) execution.getVariable("coauthorInfo");
		
		Long articleId = Long.parseLong((String)execution.getVariable("articleId"));
		
		if(articleId !=null){
			Optional<Article> currentArticleOpt = articleService.findOne(articleId);
			Article currentArticle = currentArticleOpt.orElseGet(null);
			
			
			if(!(dto.get(0).getFieldValue().equals(""))){
				User user = userConverter.convert(dto);
				
				Author author = authorService.findByEmail(user.getEmail());
				
				if(author !=null){
					Coauthor coauthor = coauthorService.create(author,currentArticle);
				}else{
					Author created = authorService.create(user);
					Coauthor coauthor = coauthorService.create(created,currentArticle);
				}
			}
			
		}
		for(FieldIdNamePairDto pair: dto){
			if(pair.getFieldId().equals("addMore")){
				if(pair.getFieldValue().equals("false")){
					Optional<Article> currentArticleOpt = articleService.findOne(articleId);
					Article currentArticle = currentArticleOpt.orElseGet(null);
					execution.setVariable("articleCreated", currentArticle);
				}
				execution.setVariable("addMore", pair.getFieldValue());
				break;
			}
		}

		
	}

}
