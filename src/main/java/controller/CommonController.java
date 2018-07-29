package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.DispatcherServlet;

@Controller
@SessionAttributes(value= {"beanName"})
public class CommonController {
	
	
	
	
	
	private int field=10;
	public CommonController() {
		System.out.println("commonController constructing....");
	}
	@RequestMapping(value="/login")
	public String login(@ModelAttribute String beanName,String password,Model model) {
		System.out.println(beanName+"\t"+password);
		
		model.addAttribute("user", "TomLi");
		//model.addAttribute("beanName","beanName123");
		return "forward:/login1";
	}
	
	@RequestMapping
	public String login1(@RequestAttribute("user") String u,@RequestAttribute("beanName") String bn) {
		System.out.println(u+"....."+bn);
		return "suc";
		
	}
	
	@ResponseBody
	@RequestMapping("/regist")
	public void regist(String uname,int age,OutputStream out) {
		//System.out.println(uname+"\t"+(age+1));
		try {
			out.write("suc".getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/other")
	public String other(@SessionAttribute("beanName") String bm,SessionStatus status) {
		System.out.println(bm);
		status.setComplete();
		return "suc";
	}
	
	@RequestMapping("work1")
	public String work1(HttpServletRequest req,HttpServletResponse resp) {
		Cookie cookie=new Cookie("xx","abc");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(10000);
		resp.addCookie(cookie);
		System.out.println(req.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE).getClass().getName());
		return "suc";
	}
	@RequestMapping("work2")
	public String work2(@CookieValue(value="xx") String xx) {
		System.out.println(xx);
		return "suc";
	}
	@RequestMapping("work3")
	public String work3(@DateTimeFormat(pattern="yyyy-MM-dd") Date birth) {
		System.out.println(birth);
		return "suc";
	}
}
