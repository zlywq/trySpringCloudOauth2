package sprssozdemo1.sprcldWeb3.feign;

import java.security.Principal;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import sprssozdemo1.libCommon.domain.*;

/*
 * 目前FeignClientProvider1大致全等于PostProvider1RibbonService。
 * 其实Service层不应该只是转发数据，而应有业务逻辑校验。当然，也可以简单转发，校验留给后端api去做，只是多费些性能。
 */

@FeignClient(name = "sprcldsrv-provider1")
public interface FeignClientProvider1 {
//	 @RequestMapping("/{id}")
//	  public User findByIdFeign(@RequestParam("id") Long id);
	
	@RequestMapping(method = RequestMethod.GET, value="/info1/procid")
	public String getServiceProcid();
	
	@RequestMapping(method = RequestMethod.GET, value="/post/postid/{postId}")
	public BbsPost getByPostId(@PathVariable("postId") long postId) ;
	
	@RequestMapping(method = RequestMethod.GET, value="/post/postid2/{postId}")
	public RestActResultTBbsPost getByPostId2(@PathVariable("postId") long postId) ;
	
	@RequestMapping(method = RequestMethod.GET, value="/post/getPosts1")
	public BbsPost[] getPosts1();
	
	@RequestMapping(method = RequestMethod.GET, value="/post/getPosts2")
	public RestActResultTBbsPostList getPosts2();
	
	@RequestMapping(method = RequestMethod.POST, value="/post/insertByJson")
	public RestActResultTLong insert(BbsPost post);
	
	@RequestMapping(method = RequestMethod.POST, value="/post/updateByJson")
	public RestActResultTLong update(BbsPost post);
	
	@RequestMapping(method = RequestMethod.POST, value="/post/delete/{postId}")
	public RestActResultTLong delete(@PathVariable("postId") long postId) ;
	
	
	
}
