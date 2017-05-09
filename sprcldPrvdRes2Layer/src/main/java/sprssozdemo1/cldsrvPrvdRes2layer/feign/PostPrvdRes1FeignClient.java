package sprssozdemo1.cldsrvPrvdRes2layer.feign;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.HttpEntity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import sprssozdemo1.libCommon.domain.*;
import sprssozdemo1.libCommon.util.Util1;
import sprssozdemo1.libCommon.util.UtilMsg;

/*
 * 根据FeignClient的粒度，使用RestTemplate时也应该实现同一粒度，即只管网络发送与接收。
 * 不过，仍有些区别，使用RestTemplate时，如果远端api不是返回正常的json数据，比如redirect之类的，目前报错信息很弱，需要加不少代码来处理。
 * 而feign，待确认......
 */

@FeignClient(name = "sprcld-prvdres1")
public interface PostPrvdRes1FeignClient {

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
