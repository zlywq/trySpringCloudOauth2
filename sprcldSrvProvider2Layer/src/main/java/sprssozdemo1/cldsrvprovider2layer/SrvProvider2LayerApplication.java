package sprssozdemo1.cldsrvprovider2layer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import sprssozdemo1.ClassInTopPackage;

//@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackageClasses = { ClassInTopPackage.class, SrvProvider2LayerApplication.class })
@EnableDiscoveryClient
public class SrvProvider2LayerApplication {


	public static void main(String[] args) {
		SpringApplication.run(SrvProvider2LayerApplication.class, args);
	}
}
