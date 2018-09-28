package cn.zjstu.photograph.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import cn.zjstu.photograph.domain.Photograph;
import cn.zjstu.photograph.exception.PhotographException;
import cn.zjstu.photograph.response.Entity;
import cn.zjstu.photograph.service.PhotographService;
import cn.zjstu.photograph.utils.CheckUtils;

@RestController
@RequestMapping("photograph")
@PropertySource(value="classpath:file.properties")
public class PhotographController {
	
	@Resource
	private PhotographService photographService;
	
	@Value(value="${imagePath}")
	private String basePath;
	
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Entity add(Photograph photo,MultipartFile[] images,HttpServletRequest request) throws Exception {
		Entity entity = new Entity();
		try {
			
			if(CheckUtils.isEmpty(photo.getTitle()) || CheckUtils.isEmpty(photo.getDescription()) || CheckUtils.isEmpty(photo.getKeywords())){
				throw new PhotographException("标题,描述，关键字包含空值");
			}
			
			Photograph add = photographService.add(photo,images,request.getServletContext().getRealPath(basePath),basePath);
			entity.setFlag(add.isFlag());
			entity.setMes(add.getTem_msg());
			
		} catch (Exception e) {
			
			entity.setFlag(false);
			entity.setMes("controller出错："+e.getMessage());
			return entity;
		}
		
		return entity;
		
	} 
	
	@RequestMapping("all")
	public Entity all() throws Exception{
		Entity res = new Entity();
		try {
			List<Photograph> all = photographService.all();
			if(all == null) throw new PhotographException("获取图片为空");
			
			res.setFlag(true);
			res.setMes("获取成功");
			res.setData(all);
		} catch (Exception e) {
			res.setFlag(false);
			res.setMes("获取失败："+e.getMessage());
			return res;
		}
		return res;
	}
	
	
	@RequestMapping("search")
	public Entity search(String keywords) throws Exception{
		Entity res = new Entity();
		try {
			
			List<Photograph> search = photographService.search(keywords);
			res.setFlag(true);
			res.setData(search);
			
		} catch (Exception e) {
			res.setFlag(false);
			res.setMes("获取失败："+e.getMessage());
			return res;
		}
		
		return res;
		
	}
	
	@RequestMapping("delete")
	public Entity  delete(Photograph photograph,HttpServletRequest request) throws Exception{
		Entity res = new Entity();
		try {
			
			String basePath = request.getServletContext().getRealPath(request.getContextPath());
			Photograph deleteById = photographService.deleteById(photograph,basePath);
			res.setFlag(deleteById.isFlag());
			res.setMes(deleteById.getTem_msg());
		} catch (Exception e) {
			
			res.setFlag(false);
			res.setMes("删除出错："+e.getMessage());
			return res;
		}
		
		return res;
	}
}
