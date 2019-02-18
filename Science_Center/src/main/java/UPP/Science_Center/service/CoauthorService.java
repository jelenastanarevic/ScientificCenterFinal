package UPP.Science_Center.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.converter.ListOfFormFieldsToUserConverter;
import UPP.Science_Center.dto.AuthorDTO;
import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.Author;
import UPP.Science_Center.model.Coauthor;
import UPP.Science_Center.repository.CoauthorRepository;



@Service
public class CoauthorService {
	
	@Autowired
	private CoauthorRepository coauthorRepository;
	
	@Autowired
	private ListOfFormFieldsToUserConverter authorConverter;

	public Coauthor create(Author author, Article currentArticle) {
		
		Coauthor coauthor = new Coauthor(author,currentArticle);
		coauthor.setArticle(currentArticle);
		
		return coauthorRepository.save(coauthor);
	}

	public List<Coauthor> findByArticleId(Long id) {
		
		 return coauthorRepository.findByArticleId(id);
	}

	public List<AuthorDTO> convertToAuthors(List<Coauthor> ret) {
		List<AuthorDTO> authors = new ArrayList<AuthorDTO>();
		for(Coauthor coauthor : ret){
			AuthorDTO newAuth = new AuthorDTO();
			newAuth.setFirst_name(coauthor.getAuthor().getFirst_name());
			newAuth.setLast_name(coauthor.getAuthor().getLast_name());
			newAuth.setEmail(coauthor.getAuthor().getEmail());
			newAuth.setCountry(coauthor.getAuthor().getCountry());
			newAuth.setCity(coauthor.getAuthor().getCity());
			newAuth.setId(coauthor.getAuthor().getId());
			authors.add(newAuth);
		}
		return authors;
	}

	
	

}
