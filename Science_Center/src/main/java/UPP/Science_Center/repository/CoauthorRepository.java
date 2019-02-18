package UPP.Science_Center.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.Science_Center.model.Coauthor;

public interface CoauthorRepository extends JpaRepository<Coauthor, Long> {

	List<Coauthor> findByArticleId(Long id);

}
