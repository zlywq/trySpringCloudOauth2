package sprssozdemo1.cldsrvprovider1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import sprssozdemo1.ClassInTopPackage;

//@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackageClasses={ClassInTopPackage.class,SrvProvider1Application.class})
@EnableDiscoveryClient
public class SrvProvider1Application {
  public static void main(String[] args) {
    SpringApplication.run(SrvProvider1Application.class, args);
  }
}
