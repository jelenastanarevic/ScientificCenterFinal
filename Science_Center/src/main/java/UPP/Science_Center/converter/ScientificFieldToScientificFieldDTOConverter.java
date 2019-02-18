package UPP.Science_Center.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.Science_Center.dto.ScientificFieldDTO;
import UPP.Science_Center.model.ScientificField;


@Component
public class ScientificFieldToScientificFieldDTOConverter implements Converter<ScientificField, ScientificFieldDTO>{

	@Override
	public ScientificFieldDTO convert(ScientificField source) {
		// TODO Auto-generated method stub
		if(source == null){
			return null;
		}
		ScientificFieldDTO scientificFieldDTO = new ScientificFieldDTO();
		scientificFieldDTO.setName(source.getScientific_field_name());
		scientificFieldDTO.setId(source.getScientific_field_id());
		return scientificFieldDTO;
	}
	
	public List<ScientificFieldDTO> convert(List<ScientificField> source) {
		List<ScientificFieldDTO> ret = new ArrayList<ScientificFieldDTO>();
		for(ScientificField field : source) {
			ret.add(convert(field));
		}
		return ret;
	}

	

	
	

}
