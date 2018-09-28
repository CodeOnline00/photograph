package cn.zjstu.photograph.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import ch.qos.logback.core.property.FileExistsPropertyDefiner;
import cn.zjstu.photograph.exception.FileException;

public class FileUtils {

	public static void copy(InputStream res, OutputStream des) throws IOException {
		
		try {
			if (res != null && des != null) {

				byte[] buffer = new byte[4096];
				int len = 0;
				while ((len = res.read(buffer)) != -1) {
					des.write(buffer);
				}
			}
		} catch (Exception e) {
			if(res!=null) res.close();
			if(des!=null) des.close();
		}
		

	}

	public static String makeDirByHash(String basepath, String filename) {

		String destPath = basepath + File.separator + Integer.toHexString(filename.hashCode()).charAt(0)
				+ File.separator + Integer.toHexString(filename.hashCode()).charAt(1);
		File file = new File(destPath);
		if (!file.exists())
			file.mkdirs();

		return destPath + File.separator + UUID() + "_" + filename;

	}

	// 主要将图片真实路径转换为web可以访问的路径，用于jsp页面展示图片
	public static String toWebUrl(String res, String sub) {
		String web_url = "";
		// linux下
		if (res.contains("/")) {
			int index = res.indexOf(sub);
			web_url = res.substring(index);
		} else {// windows下
			int index = res.indexOf(sub.replaceAll("/", "\\\\"));
			web_url = res.substring(index);
		}

		return web_url;
	}

	public static String UUID() {

		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

	}
	
	
	public static void rollback(String files) throws Exception{
		if(files=="" || files ==null) return;
		String[] paths = files.split(",");
		for (String path : paths) {
			System.out.println(path);
			boolean flag = new File(path).delete();
			if(!flag) throw new FileException("删除文件："+path+"失败!");
			
		}
	}
	
	
	public static void rollback(String files,String basePath) throws Exception{
		
		if(basePath == "" || basePath == null ) rollback(files);
		
		String[] paths = files.split(",");
		for (String path : paths) {
			System.out.println(basePath+path);
			String temPath = basePath+path;
//			if(!basePath.contains("/")){ //windows下
//				
//				temPath = basePath+path.replaceAll("\\","\\\\");
//				
//			}
//			
			boolean flag =  new File(temPath).delete();
			System.out.println(temPath);
			if(!flag) throw new FileException("删除文件："+temPath+"失败!");
 		}
		
	}
}
