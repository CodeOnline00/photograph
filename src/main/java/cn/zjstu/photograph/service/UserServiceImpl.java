package cn.zjstu.photograph.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zjstu.photograph.domain.User;
import cn.zjstu.photograph.exception.UserException;
import cn.zjstu.photograph.mapper.UserMapper;
import cn.zjstu.photograph.response.Entity;
import cn.zjstu.photograph.utils.CheckUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	public List<User> findAllUser() throws Exception {
		
		List<User> findAllUser = userMapper.findAllUser();
		if(findAllUser == null || findAllUser.size()==0) throw new UserException("未获取到用户数据");
		
		return findAllUser;
		
	}
	
	
	public User checkLogin(User user) throws Exception{
		User data = userMapper.findUserByName(user.getNickname());
		Entity res = new Entity();
		if(data==null) throw new UserException("没有该昵称("+user.getNickname()+")的用户");
		if(data.getPassword().equals(user.getPassword())){
			user.setFlag(true);
			user.setTem_msg("登陆成功");
		}else{
			user.setFlag(false);
			user.setTem_msg("密码错误");
		}
			
		return user;
	}
	
	
	
	
	public User exit(String token,User user) throws Exception {
		String session_id = user.getSession_id();
		if(token==null || session_id==null || token.equals(session_id))
			throw new UserException("传入参数包含空值，或者传入的session_id错误");
		user.setFlag(true);
		user.setTem_msg("退出成功");
		return user;
	}
	
	
	public User sign(User user) throws Exception {
		
		if(CheckUtils.isEmpty(user.getNickname()) || CheckUtils.isEmpty(user.getPassword()) || CheckUtils.isEmpty(user.getConPassword()) || !user.getPassword().equals(user.getConPassword())){
			
			throw new UserException("用户密码为空或两次密码不一致");
			
		}
		
		userMapper.add(user);
		user.setFlag(true);
		user.setTem_msg("注册成功");
		return user;
	}
		
	
}
