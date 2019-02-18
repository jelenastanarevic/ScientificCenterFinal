package UPP.Science_Center.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.Science_Center.model.Reviewer;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long>{

	List<Reviewer> findByScientificId(Long scientific_field_id);

	Reviewer findByEmail(String email);

	
}
