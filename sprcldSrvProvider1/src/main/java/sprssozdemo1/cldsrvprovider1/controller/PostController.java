package sprssozdemo1.cldsrvprovider1.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import sprssozdemo1.libCommon.domain.*;
import sprssozdemo1.libCommon.util.*;
import sprssozdemo1.libSpr.service.*;



/*
根据试验可见：



 */

@RestController
@RequestMapping("/post")
public class PostController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	BbsPostService postService;


	
	@Autowired
	UtilService utilService;
	
	@Autowired
	UtilBean utilBean;
	
	/**
	   * 本地服务实例的信息
	   * @return
	   */
	@GetMapping("/instance-info")
	public ServiceInstance showInfo() {
	    ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
	    return localServiceInstance;
	}
	
	/*
	 * 直接访问时。如果post为null，则返回200，但是response data没有
	 */
	@GetMapping("/postid/{postId}")
	public BbsPost getByPostId(@PathVariable long postId) {
		BbsPost post = postService.getById(postId);
	    return post;
	  
	}
	
	@GetMapping("/postid2/{postId}")
	public RestActResultT<BbsPost> getByPostId2(@PathVariable long postId) {
		RestActResultT<BbsPost> retData = new RestActResultT<BbsPost>();
		try{
			BbsPost post = postService.getById(postId);
			retData.setDataObj(post);
			retData.setSuccess(true);
			
//			Map<String,Object> mp = Util1.convertMapFromPojo(post);
//			retData.setDataMap(mp);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
		}
		return retData;
	  
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
//	@RequestMapping(value = "/getById1")
//	public BbsPost getById1(HttpServletRequest request, long postId) {
//		BbsPost post = postService.getById(postId);
//	    return post;
//	}
//	/*
//	 * 直接访问时。返回结果如下，看来可以bean嵌套bean。
//	 * {"success":true,"errCode":0,"errMsg":null,"errMsgDetail":null,"dataObj":null,"dataMap":null}
//	 * 或
//	 * {"success":true,"errCode":0,"errMsg":null,"errMsgDetail":null,"dataObj":{"postId":57000452041404416,"userId":56930709440753664,"groupId":0,"title":"ee","content":"ee","isDeleted":0,"createTime":1493885867,"updateTime":1493885867},"dataMap":null}
//	 * 或
//	 * {"success":true,"errCode":0,"errMsg":null,"errMsgDetail":null,"dataObj":{"postId":57000452041404416,"userId":56930709440753664,"groupId":0,"title":"ee","content":"ee","isDeleted":0,"createTime":1493885867,"updateTime":1493885867},"dataMap":{"isDeleted":0,"createTime":1493885867,"groupId":0,"updateTime":1493885867,"postId":57000452041404416,"title":"ee","userId":56930709440753664,"content":"ee"}}
//	 */
//	@RequestMapping(value = "/getById2")
//	public RestActResultT<BbsPost> getById2(HttpServletRequest request, long postId) {
//		RestActResultT<BbsPost> retData = new RestActResultT<BbsPost>();
//		try{
//			BbsPost post = postService.getById(postId);
//			retData.setDataObj(post);
//			retData.setSuccess(true);
//			
////			Map<String,Object> mp = Util1.convertMapFromPojo(post);
////			retData.setDataMap(mp);
//		}catch(Exception e){
//			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
//		}
//		return retData;
//	}
	
	
	
	
	@RequestMapping(value = "/getPosts1")
	public List<BbsPost> getPosts1(ModelMap model) {
		List<BbsPost> postList = postService.getPosts();
		return postList;
	}

	@RequestMapping(value = "/getPosts2")
	public RestActResultT<List<BbsPost>> getPosts2() {
		RestActResultT<List<BbsPost>> retData = new RestActResultT<List<BbsPost>>();
		try{
			List<BbsPost> postList = postService.getPosts();
			retData.setDataObj(postList);
			retData.setSuccess(true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
		}
		return retData;
	}

	/*
	 * 注意当restTemplate那边post过来的数据是json格式，此处不能给参数中的post设置值，需要下面的insertByJson才行。
	 * 这里是处理form形式的post。
	 */
	@PostMapping(value = "/insertByForm")
	public RestActResultT<Long> insertByForm(BbsPost post, Principal puser) {
		return insert1(post,puser);
	}
	/*
	 * 注意从restTemplate那边post过来的数据是json格式，如果使用 @RequestBody String reqBdStr，
	 * 可以看到reqBdStr为类似字符串: {"postId":0,"userId":0,"groupId":0,"title":"q1","content":"qq11","isDeleted":0,"createTime":0,"updateTime":0}
	 * 另外，此时不支持form形式的post了。
	 */
	@PostMapping(value = "/insertByJson")
	public RestActResultT<Long> insertByJson(@RequestBody BbsPost post, Principal puser) {
		//logger.debug("PostController.insert enter, post.title="+post.getTitle()+", .UserId="+post.getUserId()+", mp="+Util1.convertMapFromPojo(post));
		return insert1(post,puser);
	}
	private RestActResultT<Long> insert1(BbsPost post, Principal puser) {
		RestActResultT<Long> retData = new RestActResultT<Long>();
		try{
			if (post.getUserId() == 0){//暂且为了测试
				long userIdInSession = Util1.getUserIdInSso(puser,false);
				post.setUserId(userIdInSession);
			}
			postService.insert(post);
			retData.setSuccess(true);
			retData.setDataObj(post.getPostId());
			//TODO set latest obj
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
		}
		return retData;
	}
	 
	

	@PostMapping(value = "/updateByForm")
	public RestActResultT<Long> updateByForm(BbsPost post, Principal puser) {
		return update1(post,puser);
	}
	@PostMapping(value = "/updateByJson")
	public RestActResultT<Long> updateByJson(@RequestBody BbsPost post, Principal puser) {
		return update1(post,puser);
	}
	private RestActResultT<Long> update1(BbsPost post, Principal puser) {
		RestActResultT<Long> retData = new RestActResultT<Long>();
		try{
			long userIdInSession = Util1.getUserIdInSso(puser,false);
			postService.update(post,userIdInSession);
			retData.setSuccess(true);
			//TODO set latest obj
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
		}
		return retData;
	}
	
	
	@PostMapping(value = "/delete/{postId}")
	public RestActResultT<Long> delete(@PathVariable long postId, Principal puser) {
		return delete1(postId,puser);
	}
	@PostMapping(value = "/deleteByForm")
	public RestActResultT<Long> deleteByForm(long postId, Principal puser) {
		return delete1(postId,puser);
	}
	private RestActResultT<Long> delete1(long postId, Principal puser) {
		RestActResultT<Long> retData = new RestActResultT<Long>();
		try{
			long userIdInSession = Util1.getUserIdInSso(puser,false);
			postService.delete(postId, userIdInSession);
			retData.setSuccess(true);
			
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToPojo_withLog(e, retData);
		}
		return retData;
	}
	
	
}
