package UPP.Science_Center.converter;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.Science_Center.dto.FieldIdNamePairDto;
import UPP.Science_Center.model.ScientificField;

@Component
public class ListOfFormFieldsToScientificFieldConverter implements Converter<List<FieldIdNamePairDto>, ScientificField>{

	@Override
	public ScientificField convert(List<FieldIdNamePairDto> source) {
		if(source == null){
			return null;
		}
		ScientificField scientificField = new ScientificField();
		for(FieldIdNamePairDto field : source){
			if(field.getFieldId().equals("scientific_field_id")){
				scientificField.setScientific_field_id(Long.parseLong((field.getFieldValue())));
			}
			if(field.getFieldId().equals("scientific_field_name")){
				scientificField.setScientific_field_name((field.getFieldValue()));
			}
			
			
		
		}
		return scientificField;
	}

}
