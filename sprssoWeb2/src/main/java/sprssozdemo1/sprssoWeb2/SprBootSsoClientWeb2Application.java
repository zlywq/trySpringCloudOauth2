package sprssozdemo1.sprssoWeb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
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
//		System.setProperty("http.proxyHost", "127.0.0.1");//没用，至少在调试时。
//		System.setProperty("http.proxyPort", "8888");
		System.setProperty("java.net.useSystemProxies", "true");//OK
		
		SpringApplication.run(SprBootSsoClientWeb2Application.class, args);
		
		BearerTokenExtractor a;
		OAuth2AuthenticationProcessingFilter b;
		OAuth2RestTemplate c;
		DefaultOAuth2RequestAuthenticator d;
		DefaultOAuth2AccessToken e;
	}
}
