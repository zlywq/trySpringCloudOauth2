package sprssozdemo1.sprssoLogin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import sprssozdemo1.libSpr.cfg.DataSourceConfiguration1;
import sprssozdemo1.libSpr.service.UserInfoService;
import sprssozdemo1.sprssoLogin.oauth.CustomJdbcClientDetailsService;
import sprssozdemo1.sprssoLogin.sprsec.MyUserDetailsService;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import java.security.KeyPair;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("serverKeystore.jks"), "123456".toCharArray())
        		.getKeyPair("alias1", "123456".toCharArray());
        converter.setKeyPair(keyPair);
        return converter;
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(jwtAccessTokenConverter())
        
        //如果要支持grant_type=password，需要设置authenticationManager，并且这个bean需要设置userDetailsService。
        //但看来这个authenticationManager已经在别处生成配置好了，这里只需要简单使用即可。
        .authenticationManager(authenticationManager) 
        //.userDetailsService(myUserDetailsService)
        ;
    }
    @Autowired
    private AuthenticationManager authenticationManager;
//    @Autowired
//    MyUserDetailsService myUserDetailsService;

    @Autowired
    @Qualifier("authDataSource") 
	DataSource dataSource;//TODO 单独一个数据库，一个datasource
    
    @Bean
    public ClientDetailsService clientDetailsService(){
    	return new CustomJdbcClientDetailsService(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	//DataSource dataSource;
    	//clients.jdbc(dataSource);
    	
    	clients.withClientDetails(clientDetailsService());//这里的autoApprove在对应表的列上，注意意义是AutoApproveScopes。
    	
//        clients.inMemory().withClient("ssoclient").secret("ssosecret")
//                .autoApprove(true)//注意这个如果为true，则/oauth/confirm_access页面不会显示
//                .authorizedGrantTypes("authorization_code", "refresh_token", "password").scopes("openid");
//                //.scopes("openid","read","write");
//                //.authorizedGrantTypes("password","authorization_code", "refresh_token","implicit","client_credentials");//.scopes("openid");
        
        
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
        	.allowFormAuthenticationForClients()
        	//.sslOnly();//...
        ;
        
        //TokenEndpoint te;
    }

    
    

}
