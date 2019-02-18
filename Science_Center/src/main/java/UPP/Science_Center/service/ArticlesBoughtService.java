package UPP.Science_Center.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.ArticlesBought;
import UPP.Science_Center.repository.ArticlesBoughtRepository;


@Service
public class ArticlesBoughtService {
	
	@Autowired
	private ArticlesBoughtRepository articlesBoughtRepository;

	public ArticlesBought save(ArticlesBought bought) {
		// TODO Auto-generated method stub
		return articlesBoughtRepository.save(bought);
	}

	public List<ArticlesBought> findByUserId(Long id) {
		// TODO Auto-generated method stub
		return articlesBoughtRepository.findByUserId(id);
	}

}
