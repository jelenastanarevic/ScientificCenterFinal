package UPP.Science_Center.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.Science_Center.dto.AuthorDTO;
import UPP.Science_Center.model.Coauthor;
import UPP.Science_Center.model.Reviewer;

@Component
public class ReviewerToAuthorDTOConverter implements Converter<Reviewer, AuthorDTO>{

	@Override
	public AuthorDTO convert(Reviewer source) {
		if(source == null){
			return null;
		}
		
		AuthorDTO authorDTO = new AuthorDTO();
		
		authorDTO.setCity(source.getCity());
		authorDTO.setCountry(source.getCountry());
		authorDTO.setFirst_name(source.getFirst_name());
		authorDTO.setLast_name(source.getLast_name());
		authorDTO.setEmail(source.getEmail());
		authorDTO.setId(source.getId());
		
		return authorDTO;
	}
	
	public List<AuthorDTO> convertList(List<Reviewer> source) {
		List<AuthorDTO> ret = new ArrayList<AuthorDTO>();
		for(Reviewer co : source) {
			ret.add(convert(co));
		}
		return ret;
	}
	

}
