package UPP.Science_Center.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.Science_Center.model.Article;


public interface ArticleRepository extends JpaRepository<Article, Long> {

	List<Article> findByMagazineId(Long id);

	Article findByMagazineIdAndTitle(Long id, String articleTitle);

	Optional<Article> findById(Long articleId);

}
