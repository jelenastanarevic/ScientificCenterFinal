package UPP.Science_Center.service;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.Editor;
@Service
public class ChooseScientificFieldEditorTaskService implements JavaDelegate{

	
	@Autowired
	private EditorService editorService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ScientificFieldService scientificFieldService;
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Article article = (Article)execution.getVariable("articleCreated");
		
		
		List<Editor> eligable = editorService.findByScientificFieldId(article.getScientific_field().getScientific_field_id());
		
		List<Editor> finalList = new ArrayList<Editor>();
		
		
		for(Editor e : eligable){
			if(!e.isHeadEditor()){
				finalList.add(e);
			}
		}
		Article saved = null;
		if(finalList.size() > 0){
			article.setEditor(finalList.get(0));
			saved = articleService.save(article);
		}else{
			article.setEditor(article.getMagazine().getHead_editor());
			saved = articleService.save(article);
			 
		}
		execution.setVariable("article", saved);
		
	}
	

}
