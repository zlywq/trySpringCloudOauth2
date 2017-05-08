package sprssozdemo1.sprcldWeb3.controller;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.*;

//import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import sprssozdemo1.libCommon.domain.*;
import sprssozdemo1.libCommon.util.*;
import sprssozdemo1.libSpr.service.*;
//import sprssozdemo1.libSpr.sprsec.*;



@Controller
@RequestMapping("logreg")
public class LogRegController {
	

	@Autowired
	UtilService utilService;

	
	@Autowired
	UserInfoService userInfoService;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	

	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(Model model, Principal puser) {
		//logger.debug("Received request to show common page");
		
		String userName = "";
		if (puser != null){
			userName = puser.getName();	
		}
		
		String userNickName = "";
		if (!Util1.isStringEmpty(userName)){
			UserInfo userInfo = userInfoService.getUserByUniqueToken(userName);
			userNickName = userInfo.getNickName();
		}
		
		model.addAttribute("userName", userName );
		model.addAttribute("userNickName", userNickName );
				
		return "logreg/info";
	}
	
	
	
	
	
	
	
	

}
