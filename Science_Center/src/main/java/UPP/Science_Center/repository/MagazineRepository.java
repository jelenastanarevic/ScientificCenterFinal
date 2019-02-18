package UPP.Science_Center.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.Science_Center.model.Magazine;

public interface MagazineRepository extends JpaRepository<Magazine, Long> {

	Magazine findByIssn(String magazineIssn);

	

	
	
	

}
