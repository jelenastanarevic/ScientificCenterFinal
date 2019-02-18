package UPP.Science_Center.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import UPP.Science_Center.model.Editor;

public interface EditorRepository extends JpaRepository<Editor, Long>{

	Editor findByEmail(String email);

	List<Editor> findByScientificId(Long scientific_field_id);

}
