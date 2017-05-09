package sprssozdemo1.sprcldWeb4.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sprssozdemo1.libCommon.domain.Model1;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Value("${myOauthServer.logoutUrl}")
	String myOauthServer_logoutUrl;
	

    @RequestMapping("/")
    public String home(Locale locale, Model model, Principal puser){
    	//logger.debug("myOauthServer_hostUrl="+myOauthServer_hostUrl+"  ,myOauthServer_logoutUrl="+myOauthServer_logoutUrl+"  ,security_oauth2_client_accessTokenUri="+security_oauth2_client_accessTokenUri);
    	
    	Date date = new Date();
    	
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("srvTimeStr", formattedDate );
		
		if (puser != null){
			model.addAttribute("username", puser.getName());
		}
    	
        return "home";
    }
    
    @RequestMapping(value="/innerZuul")
    public String innerZuul(Model model){
    	model.addAttribute("zuulProxyUrl", "");
        return "innerZuul";
    }

    @RequestMapping(value="/deny")
    public String handleDeny(){
        return "deny";
    }

    @RequestMapping("/signout2")
    public String signout2aboutsso(Model model) {
    	
    	//model.addAttribute("myOauthServer_hostUrl", myOauthServer_hostUrl);
    	model.addAttribute("myOauthServer_logoutUrl", myOauthServer_logoutUrl);
    	
        return "signout2";
    }


	@ResponseBody
	@RequestMapping("/getm1")//自动把bean转为json返回
	public Model1 getM1() {
		Model1 demo = new Model1();
		demo.setId(1);
		demo.setName("Angel");
		return demo;
	}
	
	@RequestMapping("/throwerr0")
	public int zeroException(){
		return 100/0;
	}
}
