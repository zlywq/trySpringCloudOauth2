package sprssozdemo1.sprssoWeb2.cfg;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import sprssozdemo1.libWebCommon.sec.BaseSecurityConfigForSso;
import sprssozdemo1.libWebCommon.sec.SecuritySettings;

@Configuration
@EnableOAuth2Sso
@EnableConfigurationProperties(SecuritySettings.class)
public class SecurityConfigForSso extends BaseSecurityConfigForSso{

}
