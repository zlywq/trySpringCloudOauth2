package sprssozdemo1.sprssoLogin.sprsec;


//import org.bson.Document;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import sprssozdemo1.libCommon.util.*;
import sprssozdemo1.libSpr.domain.*;
import sprssozdemo1.libSpr.service.*;


public class MyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private final Logger logger = LoggerFactory.getLogger(getClass());	
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	UtilService utilService;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails
			, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		logger.debug(""+this.getClass().getSimpleName()+"."+Util1.getMethodName()+" enter"+" userDetails="+userDetails+", authentication="+authentication);
		long userId = Long.parseLong(userDetails.getUsername());
		
		UserInfo userInfo = null;
		if (authentication instanceof MyUsernamePasswordAuthenticationToken){
			//说明是刚注册的user。为了避免读写分离时，slave数据库没有及时更新数据导致找不到这条记录的问题。
			userInfo = userInfoService.getByIdInTran(userId);
		}else{
			userInfo = userInfoService.getById(userId);
		}
		
		if (userInfo == null){
			throw new AuthenticationServiceException("找不到此用户("+userId+")");
		}
		if (userInfo.getIsDisabled()==1){
			throw new AuthenticationServiceException("用户被禁用");
		}
		
		String rawPassword = (String) authentication.getCredentials();
		String passwd = userDetails.getPassword();  
		// 验证加密后的密码
		if (!EncryptUtil.matches(rawPassword, passwd)) {
			throw new AuthenticationServiceException("密码错误");  
		}else{
		}
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		utilService.log(logger, ""+this.getClass().getSimpleName()+"."+Util1.getMethodName()+" enter authentication="+authentication);
		UserDetails user = null;  
	    try {
	    	if (authentication instanceof MyUsernamePasswordAuthenticationToken){
	    		user = myUserDetailsService.loadUserByUsername(username, true);
	    	}else{
	    		user = myUserDetailsService.loadUserByUsername(username);
	    	}
	    } catch (UsernameNotFoundException notFound) {  
	        throw notFound;  
	    }  
	    if (user == null) {  
	        throw new AuthenticationServiceException("无此用户");  
	    }  
	    return user; 
	}

}







