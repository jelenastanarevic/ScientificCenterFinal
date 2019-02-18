package UPP.Science_Center.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.Science_Center.model.Author;



public interface AuthorRepository extends JpaRepository<Author, Long> {

	Author findByEmail(String email);

}
