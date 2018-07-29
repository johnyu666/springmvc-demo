package converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date>{
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public String print(Date object, Locale locale) {
		String rs=sdf.format(object);
		return rs;
	}
	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		Date rs=sdf.parse(text);
		return rs;
	}
}
