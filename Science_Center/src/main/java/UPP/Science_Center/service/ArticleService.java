package UPP.Science_Center.service;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.Article;
import UPP.Science_Center.model.ScientificField;
import UPP.Science_Center.repository.ArticleRepository;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;

	public Article save(Article createdArticle) {
		// TODO Auto-generated method stub
		return articleRepository.save(createdArticle);
	}

	public Optional<Article> findOne(Long articleId) {
		// TODO Auto-generated method stub
		return articleRepository.findById(articleId);
	}

	public void addField(Article orElse, ScientificField found) {
		orElse.setScientific_field(found);
		articleRepository.save(orElse);
		
	}

	public Article updateFields(Article createdArticle, Optional<Article> found) {
		// createdArticle - source, found-destination
		Article foundArt = found.orElse(null);
		foundArt.setAbstract_description(createdArticle.getAbstract_description());
		foundArt.setKeyWords(createdArticle.getKeyWords());
		foundArt.setTitle(createdArticle.getTitle());
		
		return foundArt;
	}

	public void removeField(Article orElse) {
		orElse.setScientific_field(null);
		articleRepository.save(orElse);
		
	}

	public List<Article> findByMagazineId(Long id) {
		// TODO Auto-generated method stub
		return articleRepository.findByMagazineId(id);
	}

	public Article findByMagazineIdAndTitle(Long id, String articleTitle) {
		// TODO Auto-generated method stub
		return articleRepository.findByMagazineIdAndTitle(id,articleTitle);
	}
	
	//cuvanje fajl prilikom upload
	public String saveFile(String file, String filename) throws IOException {
		String retVal = null;
		DataOutputStream os;
		try {
			byte[] bytes = Base64.getDecoder().decode(file);
			Path path = Paths.get("C:\\Users\\Jelena\\Desktop\\Science_Center\\Science_Center\\src\\main\\java\\UPP\\Science_Center\\files" + File.separator +filename);
			
			os = new DataOutputStream(new FileOutputStream("C:\\Users\\Jelena\\Desktop\\Science_Center\\Science_Center\\src\\main\\java\\UPP\\Science_Center\\files"+File.separator+filename));
			os.write(bytes);
			os.close();
			retVal = path.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}

	public Article findById(Long idArticle) {
		// TODO Auto-generated method stub
		return articleRepository.findById(idArticle).orElseGet(null);
	}
	

}
