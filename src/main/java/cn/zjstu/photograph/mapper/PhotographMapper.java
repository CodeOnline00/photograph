package cn.zjstu.photograph.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.zjstu.photograph.domain.Photograph;

@Repository
public interface PhotographMapper {
	
	public void add(Photograph photo) throws Exception;
	
	
	public List<Photograph> all() throws Exception;
	
	public List<Photograph> likeByKeywords(String keywords) throws Exception;
	
	
	public void deleteById(int id) throws Exception;
	
	
	public Photograph findById(int id) throws Exception;
}
