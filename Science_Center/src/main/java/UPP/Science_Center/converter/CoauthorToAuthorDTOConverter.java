package UPP.Science_Center.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import UPP.Science_Center.dto.AuthorDTO;
import UPP.Science_Center.model.Coauthor;


public class CoauthorToAuthorDTOConverter implements Converter<Coauthor, AuthorDTO>{

	@Override
	public AuthorDTO convert(Coauthor source) {
		
		if(source == null){
			return null;
		}
		
		AuthorDTO authorDTO = new AuthorDTO();
		
		authorDTO.setCity(source.getAuthor().getCity());
		authorDTO.setCountry(source.getAuthor().getCountry());
		authorDTO.setFirst_name(source.getAuthor().getFirst_name());
		authorDTO.setLast_name(source.getAuthor().getLast_name());
		authorDTO.setEmail(source.getAuthor().getEmail());
		authorDTO.setId(source.getAuthor().getId());
		
		return authorDTO;
	}
	
	public List<AuthorDTO> convertList(List<Coauthor> source) {
		List<AuthorDTO> ret = new ArrayList<AuthorDTO>();
		for(Coauthor co : source) {
			ret.add(convert(co));
		}
		return ret;
	}
	

}
