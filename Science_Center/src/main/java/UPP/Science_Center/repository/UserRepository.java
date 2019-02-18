package UPP.Science_Center.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.Science_Center.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String string);

	User findByPassword(String fieldValue);

	

	

}
