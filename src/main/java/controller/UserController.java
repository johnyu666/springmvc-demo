package controller;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;

import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Controller
public class UserController{
	@RequestMapping("users")
	public String handleRequest(int m) throws Exception {
		int x=1/m;//此处抛出ArithmeticException
		if(m==1) throw new ArrayIndexOutOfBoundsException();
		if(m==2) throw new SQLException("sql exception..");
		return "suc";
	}
	
	//xml中配置：<context:annotation-config></context:annotation-config>
	@ResponseBody //无此配置，返回值为viewName
	@ExceptionHandler({ArithmeticException.class})
	public String handlerMyException(Exception e) {
		//此处可对Exception进行判断，分别进行处理
		return "error";
	}
	
	//无法处理的RunTimeException在此处理
	@ResponseBody
	@ExceptionHandler({RuntimeException.class})
	public String handlerException(Exception e) {
		return "RuntimeException.....";
	}
	
}
