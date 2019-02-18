package UPP.Science_Center.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.Science_Center.dto.ArticleDTO;
import UPP.Science_Center.model.Article;

@Component
public class ArticleToArticleDTOConverter implements Converter<Article, ArticleDTO>{

	@Override
	public ArticleDTO convert(Article source) {
		if(source == null){
			return null;
		}
		
		ArticleDTO articleDTO = new ArticleDTO();
		articleDTO.setAbstract_description(source.getAbstract_description());
		articleDTO.setKeyWords(source.getKeyWords());
		articleDTO.setTitle(source.getTitle());
		articleDTO.setId_magazine(source.getMagazine().getId());
		articleDTO.setId(source.getId());
		articleDTO.setPrice(source.getPrice());
		articleDTO.setAuthor_name(source.getAuthor().getFirst_name() + " " +source.getAuthor().getLast_name());
		if(source.getScientific_field()!=null){
			articleDTO.setScientific_field_name(source.getScientific_field().getScientific_field_name());
			articleDTO.setScientific_field_id(source.getScientific_field().getScientific_field_id().toString());
			
		}
		if(source.getComment()!=null){
			articleDTO.setComment(source.getComment());
		}
		articleDTO.setFile_name(source.getFile_name());
		
		return articleDTO;
	}
	
	public List<ArticleDTO> convert(List<Article> source) {
		List<ArticleDTO> ret = new ArrayList<ArticleDTO>();
		for(Article article : source) {
			ret.add(convert(article));
		}
		return ret;
	}

}
