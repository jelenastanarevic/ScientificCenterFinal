package UPP.Science_Center.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import UPP.Science_Center.model.Magazine;
import UPP.Science_Center.repository.MagazineRepository;



@Service
public class MagazineService {
	
	@Autowired
	private MagazineRepository magazineRepository;

	public List<Magazine> findAll() {
		return magazineRepository.findAll();
	}

	public Magazine findOne(Long l) {
		// TODO Auto-generated method stub
		return magazineRepository.findById(l).orElseGet(null);
	}

	public Magazine findByIssn(String magazineIssn) {
		// TODO Auto-generated method stub
		return magazineRepository.findByIssn(magazineIssn);
	}
	
	

}
