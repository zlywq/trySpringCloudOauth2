package sprssozdemo1.oauthres.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sprssozdemo1.libCommon.util.Const;
import sprssozdemo1.libCommon.util.Util1;
import sprssozdemo1.libCommon.util.UtilMsg;
import sprssozdemo1.libSpr.service.UserInfoService;

@RestController
public class UserController {
	
	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping("/user")
    public Map<String, Object> user(Principal puser) {
		long userIdSession = Util1.getUserIdInSso(puser);
		Map<String, Object> mapData = userInfoService.getUserDataById(userIdSession);
		return mapData;
    }
	
	
	
	
	
	
	
	
	
}
