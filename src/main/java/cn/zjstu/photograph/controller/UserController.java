package cn.zjstu.photograph.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zjstu.photograph.domain.User;
import cn.zjstu.photograph.response.Entity;
import cn.zjstu.photograph.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("findAll")
	public Entity list() throws Exception{
		Entity res = new Entity();
		try {
			List<User> findAllUser = userService.findAllUser();
			res.setFlag(true);
			res.setMes("获取成功");
			res.setData(findAllUser);
		} catch (Exception e) {
			
			res.setFlag(false);
			res.setMes("controller出错："+e.getMessage());
			res.setData(null);
			return res;
		}
		return res;
		
	}
	@RequestMapping("sign")
	public Entity sign(User user) throws Exception{
		Entity res = new Entity();
		try {
			
			User sign = userService.sign(user);
			res.setFlag(sign.isFlag());
			res.setMes(sign.getTem_msg());
			
		} catch (Exception e) {
			
			res.setFlag(false);
			res.setMes("controller出错："+e.getMessage());
			return res;
			
		}
		
		return res;
		
	}
	
	
	@RequestMapping("checkLogin")
	public Entity login(User user,HttpServletRequest request) throws Exception{
		
		Entity res = new Entity();
		try {
			HttpSession session = request.getSession();
			user = userService.checkLogin(user);
			
			//保存会话信息
			session.setAttribute("username", user.getNickname());
			res.setFlag(user.isFlag());
			res.setMes(user.getTem_msg());
		} catch (Exception e) {
			
			res.setFlag(false);
			res.setMes("controller出错："+e.getMessage());
			return res;
		}
		
		
		return res;
	}
	
	@RequestMapping("exit")
	public Entity exit(String token,HttpServletRequest request) throws Exception{
		Entity res = new Entity();
		try {
			HttpSession session = request.getSession();
			User user = new User();
			user.setSession_id(session.getId());
			user = userService.exit(token,user);
			
			//移除会话信息
			session.removeAttribute("username");
			res.setFlag(user.isFlag());
			res.setMes(user.getTem_msg());
			
		} catch (Exception e) {
			
			res.setFlag(false);
			res.setMes("controller出错："+e.getMessage());
			return res;
		}
		
		
		
		return res;
	}
}
