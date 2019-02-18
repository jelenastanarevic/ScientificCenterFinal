package UPP.Science_Center.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import UPP.Science_Center.dto.FieldIdNamePairDto;
import UPP.Science_Center.model.User;
import UPP.Science_Center.service.RandomString;



@Component
public class ListOfFormFieldsToUserConverter implements Converter<List<FieldIdNamePairDto>, User>{

	@Override
	public User convert(List<FieldIdNamePairDto> source) {
		// TODO Auto-generated method stub
		if(source == null){
			return null;
		}
		User user = new User();
		
		for(FieldIdNamePairDto field : source){
			if(field.getFieldId().equals("first_name")){
				user.setFirst_name(field.getFieldValue());
			}
			if(field.getFieldId().equals("last_name")){
				user.setLast_name(field.getFieldValue());
			}
			if(field.getFieldId().equals("email")){
				user.setEmail(field.getFieldValue());
			}
			if(field.getFieldId().equals("city")){
				user.setCity(field.getFieldValue());
			}
			if(field.getFieldId().equals("country")){
				user.setCountry(field.getFieldValue());
			}
		
		}
		
		
		RandomString gen = new RandomString(10, ThreadLocalRandom.current());
		user.setPassword(gen.nextString());
		
		return user;
	}
	
	public List<User> convertList(List<List<FieldIdNamePairDto>> source) {
		List<User> ret = new ArrayList<User>();
		for(List<FieldIdNamePairDto> field : source) {
			ret.add(convert(field));
		}
		return ret;
	}
	

}
