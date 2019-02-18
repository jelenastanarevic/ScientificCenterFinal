package UPP.Science_Center.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.Science_Center.model.ArticlesBought;


public interface ArticlesBoughtRepository extends JpaRepository<ArticlesBought, Long>{

	List<ArticlesBought> findByUserId(Long id);

}
