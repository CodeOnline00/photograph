package cn.zjstu.photograph.mapper;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import cn.zjstu.photograph.domain.User;

@Repository
public interface UserMapper {
	
	public List<User> findAllUser() throws Exception;
	
	
	public User findUserByName(String nickname) throws Exception;
	
	
	public void add(User user) throws Exception;

}
