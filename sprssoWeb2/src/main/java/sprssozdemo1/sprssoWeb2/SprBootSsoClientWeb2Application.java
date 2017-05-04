package sprssozdemo1.sprssoWeb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import sprssozdemo1.ClassInTopPackage;

/*
 * TODO 目前mongodb只是在util中引用，没有实际使用，所以exclude了MongoAutoConfiguration.class
 */
//@SpringBootApplication
@EnableZuulProxy
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@Configuration
@ComponentScan(basePackageClasses=ClassInTopPackage.class)
public class SprBootSsoClientWeb2Application {

	public static void main(String[] args) {
		SpringApplication.run(SprBootSsoClientWeb2Application.class, args);
	}
}
