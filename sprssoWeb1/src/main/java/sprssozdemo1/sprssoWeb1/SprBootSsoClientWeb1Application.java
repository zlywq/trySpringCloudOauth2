package sprssozdemo1.sprssoWeb1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import sprssozdemo1.ClassInTopPackage;


//@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackageClasses=ClassInTopPackage.class)
public class SprBootSsoClientWeb1Application {

	public static void main(String[] args) {
		SpringApplication.run(SprBootSsoClientWeb1Application.class, args);
	}
}
