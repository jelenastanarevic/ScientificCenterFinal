package UPP.Science_Center.service;

import java.util.List;
import java.util.Optional;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.converter.ListOfFormFieldsToArticleConverter;
import UPP.Science_Center.dto.FieldIdNamePairDto;
import UPP.Science_Center.model.Article;


@Service
public class AddArticleTaskService implements JavaDelegate{

	@Autowired
	private ListOfFormFieldsToArticleConverter articleConverter;
	
	@Autowired
	private ArticleService articleService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		List<FieldIdNamePairDto> dto = (List<FieldIdNamePairDto>) execution.getVariable("articleDTO");
		
		Article createdArticle = articleConverter.convert(dto);
		
		String id_article = (String) execution.getVariable("articleId");
		
		Article toSave=null;
		if(id_article != null){
			Optional<Article> found = articleService.findOne(Long.parseLong(id_article));		
			 toSave = articleService.updateFields(createdArticle,found);
			Article newArticle = articleService.save(toSave);
		}else{
			toSave = articleService.save(createdArticle);
		}
		
		if(toSave !=null){
			
			execution.setVariable( "articleId", toSave.getId().toString());
		}

	}
}