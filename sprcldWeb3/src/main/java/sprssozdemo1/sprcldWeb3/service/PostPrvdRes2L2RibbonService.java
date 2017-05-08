package sprssozdemo1.sprcldWeb3.service;

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

import sprssozdemo1.libCommon.domain.*;
import sprssozdemo1.libCommon.util.Util1;
import sprssozdemo1.libCommon.util.UtilMsg;

/*
经过试验，在oauth2保护下取2级resource没有问题。
即在这里取resource server1上的apiA，再在resource server1的apiA里面取resource server2的apiB.
 */

@Service
public class PostPrvdRes2L2RibbonService {
//	@Autowired
//	private RestTemplate restTemplate;
	@Autowired
	OAuth2RestTemplate oAuth2RestTemplate;
	
	@Autowired
	OAuth2ClientContext clientContext;
	
	private final String urlForProvider1 = "http://sprcld-prvdres2layer/" ;//注意这里不用特意指定端口......


	public String getServiceProcid() {
		// http://服务提供者的serviceId/url
		String url = urlForProvider1 + "info1/procid"  ;
		return this.oAuth2RestTemplate.getForObject(url, String.class);
	}
	
	
	public BbsPost getByPostId(long postId) {
		// http://服务提供者的serviceId/url
		String url = urlForProvider1 + "postprvdres1/postid/{postId}"  ;
		//return this.restTemplate.getForObject(url, BbsPost.class, postId);
		return this.oAuth2RestTemplate.getForObject(url, BbsPost.class, postId);
	}
	
	public RestActResultTBbsPost getByPostId2(long postId) {
		// http://服务提供者的serviceId/url
		String url = urlForProvider1 + "postprvdres1/postid2/{postId}"  ;
		//return this.restTemplate.getForObject(url, RestActResultTBbsPost.class, postId);
		return this.oAuth2RestTemplate.getForObject(url, RestActResultTBbsPost.class, postId);
	}
	
	
	/*
	 * 试验发现restTemplate.getForObject不支持query params的方式，而是参数都放到url中。
	 * 参考 http://blog.csdn.net/z69183787/article/details/41681101 里面的说明。看来要使用query params的方式，得自己手动拼接url。
	 *     或者 https://spring.io/blog/2009/03/27/rest-in-spring-3-resttemplate/ 。
每个方法的第一个参数都是一个url string，但是这个URI可以带有变量(还记得@PathVariable吗:)哦。参数有两种方式绑定值：
    作为字符串变量数组(String variable arguments array)
      String result = restTemplate.getForObject("http://example.com/hotels/{hotel}/bookings/{booking}", String.class, "42", "21");
        会转换为一个对http://example.com/hotels/42/bookings/21的GET请求。
    或者Map对象(Map)
      The map variant expands the template based on variable name, and is therefore more useful when using many variables, or when a single variable is used multiple times.
 Map<String, String> vars = new HashMap<String, String>();
 vars.put("hotel", "42");
 vars.put("booking", "21");
 String result = restTemplate.getForObject("http://example.com/hotels/{hotel}/bookings/{booking}", String.class, vars);
         会转换为一个对`http://example.com/hotels/42/rooms/42`的GET请求。
	 * 
	 */
//	public BbsPost getById1(long postId) {
//		String url = urlForProvider1 + "postprvdres1/getById1" ;
//		Map<String,Object> mpParam = new HashMap<>();
//		mpParam.put("postId", postId);
//		return this.restTemplate.getForObject(url, BbsPost.class, mpParam);
//	}
//	public RestActResultTBbsPost getById2(long postId) {
//		String url = urlForProvider1 + "postprvdres1/getById2" ;
//		Map<String,Object> mpParam = new HashMap<>();
//		mpParam.put("postId", postId);
//		//这里用泛型发现有编译问题，改用显式类型
//		return this.restTemplate.getForObject(url, RestActResultTBbsPost.class, mpParam);
//	}
	
	//???......
	public BbsPost[] getPosts1() {
		String url = urlForProvider1 + "postprvdres1/getPosts1" ;
		//return this.restTemplate.getForObject(url, BbsPost[].class);
		return this.oAuth2RestTemplate.getForObject(url, BbsPost[].class);
	}
	public RestActResultTBbsPostList getPosts2() {
		String url = urlForProvider1 + "postprvdres1/getPosts2" ;
		//return this.restTemplate.getForObject(url, RestActResultTBbsPostList.class);
		return this.oAuth2RestTemplate.getForObject(url, RestActResultTBbsPostList.class);
	}
	
	
	
	public RestActResultTLong insert(BbsPost post) {
		try{
			String url = urlForProvider1 + "postprvdres1/insertByJson" ;
			//注意这里的restTemplate.postForObject发送的body是json格式的字符串。在接收端需要给pojo参数加@RequestBody，如@RequestBody BbsPost post。
			//return this.restTemplate.postForObject(url, post, RestActResultTLong.class);
			return this.oAuth2RestTemplate.postForObject(url, post, RestActResultTLong.class);
			
//			HttpEntity<BbsPost> request = new HttpEntity<>(post); //OK
//			return this.restTemplate.postForObject(url, request, RestActResultTLong.class);
			
//			Map<String,Object> mpData = Util1.convertMapFromPojo(post);
//			return this.restTemplate.postForObject(url, mpData, RestActResultTLong.class);
			
		}catch(Exception e){
			RestActResultTLong retData = new RestActResultTLong();
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
			return retData;
		}
	}
	

	public RestActResultTLong update(BbsPost post, Principal puser) {
		try{
			String url = urlForProvider1 + "postprvdres1/updateByJson" ;
//			long userIdInSession = Util1.getUserIdInSso(puser,false);
//			if (userIdInSession != 0){
//				post.setUserId(userIdInSession);
//			}
			//return this.restTemplate.postForObject(url, post, RestActResultTLong.class);
			return this.oAuth2RestTemplate.postForObject(url, post, RestActResultTLong.class);
		}catch(Exception e){
			RestActResultTLong retData = new RestActResultTLong();
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
			return retData;
		}
	}

	public RestActResultTLong delete(long postId) {
		try{
			String url = urlForProvider1 + "postprvdres1/delete/{postId}" ;
			
//			MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
//			paramMap.add("postId", postId);
//			return this.restTemplate.postForObject(url, paramMap, RestActResultTLong.class);
			
			//return this.restTemplate.postForObject(url, null, RestActResultTLong.class,postId);
			return this.oAuth2RestTemplate.postForObject(url, null, RestActResultTLong.class,postId);
			
		}catch(Exception e){
			RestActResultTLong retData = new RestActResultTLong();
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
			return retData;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
