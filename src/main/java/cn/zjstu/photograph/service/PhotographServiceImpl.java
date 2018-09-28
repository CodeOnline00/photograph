package cn.zjstu.photograph.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartFile;

import cn.zjstu.photograph.domain.Photograph;
import cn.zjstu.photograph.exception.PhotographException;
import cn.zjstu.photograph.mapper.PhotographMapper;
import cn.zjstu.photograph.response.Entity;
import cn.zjstu.photograph.utils.FileUtils;

@Service
@Transactional
public class PhotographServiceImpl implements PhotographService {

	@Resource
	private PhotographMapper photographMapper;

	// @CachePut(value="photograph",key="'allPhotograph'")
	public Photograph add(Photograph photo, MultipartFile[] images, String realPath, String basePath) throws Exception {
		// 用于回滚,真实路径
		StringBuilder sb1 = new StringBuilder("");
		// 用于存储数据库，web访问路径
		StringBuilder sb2 = new StringBuilder("");

		try {
			for (MultipartFile file : images) {
				String savePath = FileUtils.makeDirByHash(realPath, file.getOriginalFilename());

				// 存储上传文件到指定的文件夹
				FileUtils.copy(file.getInputStream(), new FileOutputStream(new File(savePath)));

				// 追加存储路径，多个文件路径之间用逗号隔开
				sb1.append(savePath).append(",");
				sb2.append(FileUtils.toWebUrl(savePath, basePath)).append(",");

			}

			photo.setImage(sb2.toString());

		} catch (Exception e) {
			photo.setFlag(false);
			try {
				// 回滚文件
				if (sb1.toString() != "")
					FileUtils.rollback(sb1.toString());
			} catch (Exception e2) {
				photo.setTem_msg("文件回滚失败：" + e2.getMessage());
				return photo;
			}

			photo.setTem_msg("文件存储出现问题");
			return photo;

		}

		// 存入数据库
		photographMapper.add(photo);

		photo.setTem_msg("发布成功");
		return photo;

	}

	// @Cacheable(value="photograph",key="'allPhotograph'")
	public List<Photograph> all() throws Exception {

		System.out.println("未使用缓存进入");

		List<Photograph> all = null;
		all = photographMapper.all();
		if (all == null)
			throw new PhotographException("获取的图片动态为空");
		return all;

	}

	public List<Photograph> search(String keywords) throws Exception {
		System.out.println("未使用缓存，进入方法");
		List<Photograph> res = null;
		// 如果关键字为空，就返回所有的结果
		if (keywords == null || keywords == "")
			return all();

		res = photographMapper.likeByKeywords(keywords);

		if (res == null || res.size() == 0)
			throw new PhotographException("获取结果为空");

		return res;
	}

	public Photograph deleteById(Photograph photo, String basePath) throws Exception {

		int id = photo.getId();

		if (id < 1)
			throw new PhotographException("id范围错误");

		// 查询出图片路径，执行删除操作
		Photograph findById = photographMapper.findById(id);
		if (findById == null)
			throw new PhotographException("没有此id的图片动态");
		FileUtils.rollback(findById.getImage(), basePath);

		// 服务器删除图片成功后再删除数据库数据
		photographMapper.deleteById(id);

		photo.setFlag(true);
		photo.setTem_msg("删除成功");

		return photo;
	}

}
