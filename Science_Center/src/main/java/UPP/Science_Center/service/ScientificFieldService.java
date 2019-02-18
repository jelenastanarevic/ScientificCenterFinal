package UPP.Science_Center.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.ScientificField;
import UPP.Science_Center.repository.ScientificFieldRepository;


@Service
public class ScientificFieldService {
	
	@Autowired
	private ScientificFieldRepository scientificFieldRepository;

	public List<ScientificField> findAll() {
		
		return scientificFieldRepository.findAll();
	}

	public ScientificField findOne(Long parseLong) {
		// TODO Auto-generated method stub
		return scientificFieldRepository.getOne(parseLong);
	}

	
	

}
