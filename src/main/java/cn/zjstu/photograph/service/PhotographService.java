package cn.zjstu.photograph.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.zjstu.photograph.domain.Photograph;
import cn.zjstu.photograph.response.Entity;

public interface PhotographService {
	
	public Photograph add(Photograph photo,MultipartFile[] images,String realPath,String basePath) throws Exception;
	
	
	public List<Photograph> all() throws Exception;
	
	public List<Photograph> search(String keywords) throws Exception;
	
	public Photograph deleteById(Photograph photo,String basePath) throws Exception;
}
