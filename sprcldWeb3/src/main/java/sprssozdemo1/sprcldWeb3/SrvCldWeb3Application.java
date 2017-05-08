package sprssozdemo1.sprcldWeb3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import sprssozdemo1.ClassInTopPackage;

//@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@Configuration
@ComponentScan(basePackageClasses = { ClassInTopPackage.class, SrvCldWeb3Application.class })
@EnableDiscoveryClient
public class SrvCldWeb3Application {


	public static void main(String[] args) {
		System.setProperty("java.net.useSystemProxies", "true");
		
		SpringApplication.run(SrvCldWeb3Application.class, args);
	}
}
