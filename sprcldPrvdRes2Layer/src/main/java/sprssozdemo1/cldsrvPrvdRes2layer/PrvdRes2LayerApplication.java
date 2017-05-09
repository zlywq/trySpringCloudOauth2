package sprssozdemo1.cldsrvPrvdRes2layer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import sprssozdemo1.ClassInTopPackage;

//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@EnableResourceServer
@ComponentScan(basePackageClasses = { ClassInTopPackage.class, PrvdRes2LayerApplication.class })
@EnableDiscoveryClient
@EnableFeignClients
public class PrvdRes2LayerApplication {


	public static void main(String[] args) {
		System.setProperty("java.net.useSystemProxies", "true");
		
		SpringApplication.run(PrvdRes2LayerApplication.class, args);
	}
}
