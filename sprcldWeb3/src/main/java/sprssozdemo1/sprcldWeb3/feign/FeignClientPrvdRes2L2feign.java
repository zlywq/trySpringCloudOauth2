package sprssozdemo1.sprcldWeb3.feign;



import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import sprssozdemo1.libCommon.domain.*;


@FeignClient(name = "sprcld-prvdres2layer")
public interface FeignClientPrvdRes2L2feign {
	
	
	@RequestMapping(method = RequestMethod.GET, value="/postprvdres1feign/postid/{postId}")
	public BbsPost getByPostId(@PathVariable("postId") long postId) ;
	
	@RequestMapping(method = RequestMethod.GET, value="/postprvdres1feign/postid2/{postId}")
	public RestActResultTBbsPost getByPostId2(@PathVariable("postId") long postId) ;
	
	@RequestMapping(method = RequestMethod.GET, value="/postprvdres1feign/getPosts1")
	public BbsPost[] getPosts1();
	
	@RequestMapping(method = RequestMethod.GET, value="/postprvdres1feign/getPosts2")
	public RestActResultTBbsPostList getPosts2();
	
	@RequestMapping(method = RequestMethod.POST, value="/postprvdres1feign/insertByJson")
	public RestActResultTLong insert(BbsPost post);
	
	@RequestMapping(method = RequestMethod.POST, value="/postprvdres1feign/updateByJson")
	public RestActResultTLong update(BbsPost post);
	
	@RequestMapping(method = RequestMethod.POST, value="/postprvdres1feign/delete/{postId}")
	public RestActResultTLong delete(@PathVariable("postId") long postId) ;
	
	
	
}
