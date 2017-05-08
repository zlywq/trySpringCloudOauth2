package sprssozdemo1.sprcldPrvdRes1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import sprssozdemo1.ClassInTopPackage;

//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@EnableResourceServer
@ComponentScan(basePackageClasses=ClassInTopPackage.class)
@EnableDiscoveryClient
public class PrvdRes1Application {
    public static void main(String[] args) {
        SpringApplication.run(PrvdRes1Application.class, args);
    }
}
