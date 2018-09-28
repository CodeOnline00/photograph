package cn.zjstu.photograph;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.repository.cdi.Eager;

//@EnableCaching                     //开启缓存，我们这里使用作redis缓存
@SpringBootApplication
@MapperScan(basePackages={"cn.zjstu.photograph.mapper"})
public class SpringBootApp {


	public static void main(String[] args) {

		SpringApplication.run(SpringBootApp.class, args);
		
	}

}
