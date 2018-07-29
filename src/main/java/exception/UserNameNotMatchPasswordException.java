package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="用户名和密码不匹配!")
public class UserNameNotMatchPasswordException extends RuntimeException{

}
