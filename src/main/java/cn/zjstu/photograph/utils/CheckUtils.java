package cn.zjstu.photograph.utils;

public class CheckUtils {
	//用于校验数据是否为空或者为空字符串
	public static boolean isEmpty(Object res){
		
		if(res == null) return true; 
		if(res instanceof String && (String)res == "") return true;
		return false;
		
	}

}
