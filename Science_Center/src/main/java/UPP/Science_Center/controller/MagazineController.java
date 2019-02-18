package UPP.Science_Center.controller;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.camunda.bpm.engine.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import UPP.Science_Center.converter.MagazineToMagazineDTOConverter;
import UPP.Science_Center.dto.MagazineDTO;
import UPP.Science_Center.dto.OrderDTO;
import UPP.Science_Center.model.Magazine;
import UPP.Science_Center.model.User;
import UPP.Science_Center.service.MagazineService;
import UPP.Science_Center.service.UserService;


@RestController
@RequestMapping("/naucna_centrala")
public class MagazineController {
	
	
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private MagazineToMagazineDTOConverter magazineConverter;
	
	@Autowired
	private UserService userService;
	
	/*@Autowired
	private RestTemplate restTemplate;
	*/
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/getMagazines",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getMagazines(HttpServletRequest request) {
		//metoda za pocetnu stranu sa listom svih NC
		
		
		User user = userService.getCurrentUser();
		System.out.println(user.getEmail());
		//System.out.println(user.getEmail() );
		List<Magazine> allMagazines = magazineService.findAll();
		List<MagazineDTO> allMagazinesDTO = magazineConverter.convert(allMagazines);
		return new ResponseEntity<>(allMagazinesDTO,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/getMagazineName/{idMagazine}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getMagazineName(@PathVariable Long idMagazine) {
		//metoda za pocetnu stranu sa listom svih NC
		Magazine magazine = magazineService.findOne(idMagazine);
		MagazineDTO magazineDTO = magazineConverter.convert(magazine);
		return new ResponseEntity<>(magazineDTO,HttpStatus.OK);
	}
	
	/*@CrossOrigin
	@RequestMapping(
			value = "/buyMagazine/{idMagazine}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> buyMagazine(@PathVariable Long idMagazine,HttpServletRequest request) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		Magazine magazine = magazineService.findOne(idMagazine);
		
		User creator = (User)request.getSession().getAttribute("user");
		
		OrderDTO orderDTO = new OrderDTO();
		
		//orderDTO.setBuyMagazine(false);
		orderDTO.setMagazineIssn(magazine.getIssn());
		orderDTO.setUserEmail("smiljana.dragoljevic-1@gmail.com");
		
	
	      //RestTemplate client=new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity< OrderDTO> entity = new HttpEntity< OrderDTO>(orderDTO, headers);
		String orderDTOStr = restTemplate.postForObject("http://192.168.43.30:9001/kp/koncentrator/order/create", entity,
				String.class);
		
		return new ResponseEntity<>(orderDTOStr,HttpStatus.OK);
		
		
	}
	*/
	
	

}
