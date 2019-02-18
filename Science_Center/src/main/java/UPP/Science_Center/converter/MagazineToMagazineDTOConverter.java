package UPP.Science_Center.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.Science_Center.dto.MagazineDTO;
import UPP.Science_Center.model.Magazine;



@Component
public class MagazineToMagazineDTOConverter implements Converter<Magazine, MagazineDTO> {

	@Override
	public MagazineDTO convert(Magazine source) {
		if(source == null){
			return null;
		}
		
		MagazineDTO magazineDTO = new MagazineDTO();
		magazineDTO.setId(source.getId());
		magazineDTO.setIssn(source.getIssn());
		magazineDTO.setTitle(source.getTitle());
		
		return magazineDTO;
		
	}
	
	public List<MagazineDTO> convert(List<Magazine> source) {
		List<MagazineDTO> ret = new ArrayList<MagazineDTO>();
		for(Magazine grad : source) {
			ret.add(convert(grad));
		}
		return ret;
	}

}
