package UPP.Science_Center.converter;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.Science_Center.service.PDFHandler;

import UPP.Science_Center.dto.FieldIdNamePairDto;
import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.Author;
import UPP.Science_Center.model.Magazine;
import UPP.Science_Center.model.User;
import UPP.Science_Center.service.ArticleService;
import UPP.Science_Center.service.AuthorService;
import UPP.Science_Center.service.MagazineService;
import UPP.Science_Center.service.UserService;


@Component
public class ListOfFormFieldsToArticleConverter implements Converter<List<FieldIdNamePairDto>, Article>{

	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	 private PDFHandler pdfhandler;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private ArticleService articleService;
	
	
	
	
	@Override
	public Article convert(List<FieldIdNamePairDto> source) {
		if(source == null){
			return null;
		}
		
		String file_name="";
		
		Article article = new Article();
		for(FieldIdNamePairDto field : source){
			if(field.getFieldId().equals("title")){
				article.setTitle(field.getFieldValue());
			}
			if(field.getFieldId().equals("apstract")){
				article.setAbstract_description(field.getFieldValue());
			}
			if(field.getFieldId().equals("keywords")){
				article.setKeyWords(field.getFieldValue());
			}
			if(field.getFieldId().equals("id_magazine")){
				Magazine magazine = magazineService.findOne(Long.parseLong(field.getFieldValue()));
				article.setMagazine(magazine);
			}
			if(field.getFieldId().equals("file_name")){
				file_name = field.getFieldValue();
				article.setFile_name(field.getFieldValue());
			}
			
		}
			
			User loggedIn = userService.getCurrentUser();
			Author author = authorService.findByEmail(loggedIn.getEmail());
			
			/*try {
				String fileName = articleService.saveFile(file_content,file_name);
				Article articlePDF = pdfhandler.getIndexUnit(new File(fileName));
				//article.setContent(articlePDF.getContent());
				//article.setFile_name(articlePDF.getFile_name());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			if(author!=null){
				article.setAuthor(author);
			}
		
		
		return article;
	}
	
	public List<Article> convertList(List<List<FieldIdNamePairDto>> source) {
		List<Article> ret = new ArrayList<Article>();
		for(List<FieldIdNamePairDto> field : source) {
			ret.add(convert(field));
		}
		return ret;
	}

	
	
	

}
