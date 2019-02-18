package UPP.Science_Center.service;

import java.util.List;
import java.util.Optional;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.dto.FieldIdNamePairDto;
import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.ScientificField;



@Service
public class AddScientificFieldTaskService implements JavaDelegate{
	
	@Autowired
	private ScientificFieldService scientificFieldService;
	
	@Autowired
	private ArticleService articleService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		List<FieldIdNamePairDto> dto = (List<FieldIdNamePairDto>) execution.getVariable("fieldDTO");
		
		String id_field_string=null;
		for(FieldIdNamePairDto dtoField:dto){
			if(dtoField.getFieldId().equals("scientific_field_id")){
				id_field_string = dtoField.getFieldValue(); 
			}
		}
		
		if(id_field_string != null){
			//ScientificField createdScField = scientificFieldConverter.convert(dto);
			ScientificField scField = scientificFieldService.findOne(Long.parseLong(id_field_string));
			Optional<Article> article = articleService.findOne(Long.parseLong((String) execution.getVariable("articleId")));
			Article found = article.orElseGet(null);
			if(found.getScientific_field()!=null){
				found.setScientific_field(scField);
				Article saved = articleService.save(found);
			}
			
		}
		for(FieldIdNamePairDto dtoField:dto){
			if(dtoField.getFieldId().equals("changeArticle")){
				execution.setVariable("changeArticle",dtoField.getFieldValue() );
			}
		}
		
		
	}

}
