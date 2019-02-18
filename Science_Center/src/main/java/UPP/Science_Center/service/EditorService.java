package UPP.Science_Center.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.Editor;
import UPP.Science_Center.repository.EditorRepository;

@Service
public class EditorService {
	
	@Autowired
	private EditorRepository editorRepository;

	public Editor findByEmail(String email) {
		// TODO Auto-generated method stub
		return editorRepository.findByEmail(email);
	}

	public Editor findById(Long id) {
		// TODO Auto-generated method stub
		return editorRepository.findById(id).orElseGet(null);
	}

	public List<Editor> findByScientificFieldId(Long scientific_field_id) {
		// TODO Auto-generated method stub
		return editorRepository.findByScientificId(scientific_field_id);
	}

}
