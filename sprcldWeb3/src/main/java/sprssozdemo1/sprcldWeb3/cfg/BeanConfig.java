package sprssozdemo1.sprcldWeb3.cfg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import feign.RequestInterceptor;
import feign.RequestTemplate;


@Configuration
public class BeanConfig {
	private static final Logger logger = LoggerFactory.getLogger(BeanConfig.class);
	
	/**
	 * 实例化RestTemplate，通过@LoadBalanced注解开启均衡负载能力.
	 */

	
//	@Bean
//	@LoadBalanced
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
	
	/*
这是关于feign的支持oauth2的问题的解决办法，目前OK。
参考http://stackoverflow.com/questions/35265585/trying-to-use-oauth2-token-with-feign-client-and-hystrix 。
    据文章，还有问题，待发现......
	 */
	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {
	    return new RequestInterceptor() {
	        @Override
	        public void apply(RequestTemplate requestTemplate) {
	            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
	            requestTemplate.header("Authorization", "Bearer " + details.getTokenValue());                   
	        }
	    };
	}
	
	//OK for RestTemplate as client to call rest api. but FAIL for feign
	@Bean
	@LoadBalanced
	public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory factory) {
	    return factory.getUserInfoRestTemplate();
	}
	
	//OK for RestTemplate as client to call rest api. but FAIL for feign
//	@Value("${security.oauth2.client.clientId}")
//	private String oauth2_clientId;
//	@Value("${security.oauth2.client.clientSecret}")
//	private String oauth2_clientSecret;
//	@Value("${security.oauth2.client.accessTokenUri}")
//	private String oauth2_accessTokenUri;
//	@Value("${security.oauth2.client.userAuthorizationUri}")
//	private String oauth2_userAuthorizationUri;
//	@Bean
//	public OAuth2ProtectedResourceDetails getOAuth2ProtectedResourceDetails() {
//		AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
//		details.setId("sprcldSrvProvider2Layer");
//		details.setClientId(oauth2_clientId);
//		details.setClientSecret(oauth2_clientSecret);
//		details.setAccessTokenUri(oauth2_accessTokenUri);
//		details.setUserAuthorizationUri(oauth2_userAuthorizationUri);
//		details.setScope(Arrays.asList("read", "write"));
//		return details;
//	}
//	@Bean
//	@LoadBalanced
//	public OAuth2RestTemplate getOAuth2RestTemplate(OAuth2ClientContext clientContext) {
//		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(getOAuth2ProtectedResourceDetails(), clientContext);
////		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>(); 
////		MyAddAuthHeaderInterceptor addAuthHeaderInterceptor = new MyAddAuthHeaderInterceptor(restTemplate);
////		interceptors.add(addAuthHeaderInterceptor);
////		restTemplate.setInterceptors(interceptors);
//		return restTemplate;
//	}
////	public static class MyAddAuthHeaderInterceptor implements ClientHttpRequestInterceptor {
////		OAuth2RestTemplate oAuth2RestTemplate ;
////		public MyAddAuthHeaderInterceptor(OAuth2RestTemplate oAuth2RestTemplate){
////			this.oAuth2RestTemplate = oAuth2RestTemplate; 
////		}
////		@Override
////		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
////				throws IOException {
////			HttpHeaders headers = request.getHeaders();
////			
////			OAuth2AccessToken oAuth2AccessToken = oAuth2RestTemplate.getAccessToken();
////			logger.debug("MyAddAuthHeaderInterceptor.intercept oAuth2AccessToken TokenType="+oAuth2AccessToken.getTokenType()+", Value="+oAuth2AccessToken.getValue());
////			if (oAuth2AccessToken != null){
////				String oAuth2AccessTokenStr = oAuth2AccessToken.getValue();
////				headers.add("Authorization", "Bearer "+oAuth2AccessTokenStr);
////			}
////            return execution.execute(request, body);
////		}
////    }
	
	
	
	
	
	
	
	
	
	
	
}
