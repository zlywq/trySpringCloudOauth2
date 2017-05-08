package sprssozdemo1.sprcldWeb3.controller;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

//import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import sprssozdemo1.sprcldWeb3.service.*;
import sprssozdemo1.libCommon.domain.*;
import sprssozdemo1.libCommon.util.*;




/*
根据试验可见：



 */

@RestController
@RequestMapping("/postprov1")
public class PostProvider1RibbonController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	PostProvider1RibbonService postRibbonService;

	@GetMapping("/serviceprocid")
	public String getServiceProcid() {
		String s = postRibbonService.getServiceProcid();
	    return s;
	}
	
	
	@GetMapping("/postid/{postId}")
	public BbsPost getByPostId(@PathVariable Long postId) {
		BbsPost post = postRibbonService.getByPostId(postId);
	    return post;
	}
	@GetMapping("/postid2/{postId}")
	public RestActResultTBbsPost getByPostId2(@PathVariable Long postId) {
		RestActResultTBbsPost retData = postRibbonService.getByPostId2(postId);
	    return retData;
	}

	@RequestMapping(value = "/getPosts1")
	public BbsPost[] getPosts(ModelMap model) {
		BbsPost[] postList = postRibbonService.getPosts1();
		return postList;
	}
	@RequestMapping(value = "/getPosts2")
	public RestActResultTBbsPostList getPosts() {
		try{
			RestActResultTBbsPostList retData = postRibbonService.getPosts2();
			return retData;
		}catch(Exception e){
			RestActResultTBbsPostList retData = new RestActResultTBbsPostList();
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
			return retData;
		}
	}

	@PostMapping(value = "/insert")
	public RestActResultTLong insert(BbsPost post, Principal puser) {
		try{
			long userIdInSession = Util1.getUserIdInSso(puser,false);
			if (userIdInSession == 0){//for 调试 TODO delete
				userIdInSession = 56930709440753664L;
			}
			post.setUserId(userIdInSession);
			
			RestActResultTLong retData = postRibbonService.insert(post);
			return retData;
			//TODO set latest obj
		}catch(Exception e){
			RestActResultTLong retData = new RestActResultTLong();
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
			return retData;
		}
	}
	
	

	@PostMapping(value = "/update")
	public RestActResultTLong update(BbsPost post, Principal puser) {
		try{
			long userIdInSession = Util1.getUserIdInSso(puser,false);
			post.setUserId(userIdInSession);
			
			RestActResultTLong retData = postRibbonService.update(post, puser);
			return retData;
			//TODO set latest obj
		}catch(Exception e){
			RestActResultTLong retData = new RestActResultTLong();
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
			return retData;
		}
	}
	
	
	@PostMapping(value = "/delete")
	public RestActResultTLong delete(long postId, Principal puser) {
		try{
			RestActResultTLong retData = postRibbonService.delete(postId);
			return retData;
		}catch(Exception e){
			RestActResultTLong retData = new RestActResultTLong();
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
			return retData;
		}
		
	}
	
	
}
