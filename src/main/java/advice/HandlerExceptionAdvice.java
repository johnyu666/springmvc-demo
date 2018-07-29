package advice;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class HandlerExceptionAdvice {
	//此处只处理前面无法处理的异常
	@ExceptionHandler({ SQLException.class})
	public String handleArithmeticException(Exception ex) {
		return "Controller advice process excption";
	}
}
