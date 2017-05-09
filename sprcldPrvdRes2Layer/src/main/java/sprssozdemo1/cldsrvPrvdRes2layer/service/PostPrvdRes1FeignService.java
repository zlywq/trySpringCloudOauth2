package sprssozdemo1.cldsrvPrvdRes2layer.service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import sprssozdemo1.cldsrvPrvdRes2layer.feign.*;
import sprssozdemo1.libCommon.domain.*;
import sprssozdemo1.libCommon.util.*;



@Service
public class PostPrvdRes1FeignService {

	@Autowired
	PostPrvdRes1FeignClient feignClient1;
	
	
	public BbsPost getByPostId(long postId) {
		return this.feignClient1.getByPostId(postId);
	}
	
	public RestActResultTBbsPost getByPostId2(long postId) {
		return this.feignClient1.getByPostId2(postId);
	}
	
	
	
	public BbsPost[] getPosts1() {
		return this.feignClient1.getPosts1();
	}
	public RestActResultTBbsPostList getPosts2() {
		return this.feignClient1.getPosts2();
	}
	
	
	
	public RestActResultTLong insert(BbsPost post) {
		try{
			return this.feignClient1.insert(post);
		}catch(Exception e){
			RestActResultTLong retData = new RestActResultTLong();
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
			return retData;
		}
	}
	

	public RestActResultTLong update(BbsPost post, Principal puser) {
		try{
			return this.feignClient1.update(post);
		}catch(Exception e){
			RestActResultTLong retData = new RestActResultTLong();
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
			return retData;
		}
	}

	public RestActResultTLong delete(long postId) {
		try{
			return this.feignClient1.delete(postId);
		}catch(Exception e){
			RestActResultTLong retData = new RestActResultTLong();
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
			return retData;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
