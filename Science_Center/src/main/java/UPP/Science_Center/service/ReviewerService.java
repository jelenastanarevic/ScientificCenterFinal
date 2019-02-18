package UPP.Science_Center.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.Reviewer;
import UPP.Science_Center.repository.ReviewerRepository;

@Service
public class ReviewerService {

	@Autowired
	private ReviewerRepository reviewerRepository;

	public Reviewer findById(Long parseLong) {
		// TODO Auto-generated method stub
		return reviewerRepository.findById(parseLong).orElseGet(null);
	}

	public Reviewer save(Reviewer rev) {
		// TODO Auto-generated method stub
		return reviewerRepository.save(rev);
	}

	public List<Reviewer> findByScientificId(Long scientific_field_id) {
		// TODO Auto-generated method stub
		return reviewerRepository.findByScientificId(scientific_field_id);
	}

	public Reviewer findByEmail(String email) {
		// TODO Auto-generated method stub
		return reviewerRepository.findByEmail(email);
	}
}
