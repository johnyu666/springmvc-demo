package converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String,Date>{
	@Override
	public Date convert(String source) {
		Date rs=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			rs=sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
