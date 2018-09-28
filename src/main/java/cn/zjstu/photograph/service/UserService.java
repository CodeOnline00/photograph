package cn.zjstu.photograph.service;

import java.util.List;

import cn.zjstu.photograph.domain.User;
import cn.zjstu.photograph.response.Entity;

public interface UserService {
	
	public List<User> findAllUser() throws Exception;
	
	public User checkLogin(User user) throws Exception;
	
	public User exit(String token,User user) throws Exception;
	
	public User sign(User user) throws Exception;
}
