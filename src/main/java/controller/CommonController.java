package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.servlet.DispatcherServlet;

import pojo.User;


/**
 * 一旦向model存储"user",将会向HttpSession中以同名方式存储
 */
@SessionAttributes(value= {"user"})
@Controller
public class CommonController {
	
	private int field=10;
	
	/**
	 * 在model中存储user，它会确保对本实例所有请求方法的访问，model中有一个"user",
	 * 同时以“user”做为key,存储在request范围内
	 * 并且session不失效，它将会缓存起来(在有@SessionAttribute的情况下)；
	 * 
	 * 
	 * 触发的时机：
	 * 1、本实例的任何@RequestMapping Method被访问
	 * 2、如有@SessionAttribute,本操作只有session创建，才会发生一次
	 * 3、同一session的本实例的任何访问，则不会再次执行
	 * 
	 */
	@ModelAttribute("user")
	public User createUserInModel(){
		System.out.println("create User in Model,name is user..");
		User u=new User();
		u.setId(100);
		return u;
	}
	
	
	@RequestMapping(value="/login")
	public String login( 
			/*从model中取出user,不使用@ModelAttribute效果相同，但无法按自定义的名字提取*/
			@ModelAttribute("user") User user,
			Map<String,Object> model) {
/*		本行操作，也会向model中存入"user" 
 		User user=new User(12,"tomli",10);
		model.put("user", user);
		*/
		System.out.println("login :"+user.getId()+"\t"+user.getUname());
		return "forward:/login1";
	}

	/**
	 * forward to this from '/login'
	 * request and session will both have "user"
	 */
	@RequestMapping("login1")
	public String login1(@RequestAttribute("user") User userInRequest
			,@SessionAttribute("user") User userInSession,
			Map<String, Object> model) {
		System.out.println("login1:"+userInRequest.getUname()+"....."+userInSession.getUname());
		
		//直接从Model中获取
		User user2=(User)model.get("user");
		System.out.println("login1 from map model:"+user2.getUname());
		return "suc";
	}
	
	/**
	 * 销毁session
	 */
	@ResponseBody
	@RequestMapping("/logoff")
	public String other(SessionStatus status) {
		status.setComplete();//等同于session.invalidate();
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

