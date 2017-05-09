package sprssozdemo1.sprcldWeb4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import sprssozdemo1.ClassInTopPackage;

/*
 * 这里zuul自动和eureka整合，即eureka上注册的服务名自动成为这里的第一部分路径，不用手动配置映射，并且自动具有load balance特性。
 */

//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@Configuration
@ComponentScan(basePackageClasses = { ClassInTopPackage.class, SrvCldWeb4Application.class })
@EnableZuulProxy
public class SrvCldWeb4Application {


	public static void main(String[] args) {
		System.setProperty("java.net.useSystemProxies", "true");
		
		SpringApplication.run(SrvCldWeb4Application.class, args);
	}
}
