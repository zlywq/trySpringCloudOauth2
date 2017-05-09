package sprssozdemo1.sprcldWeb3.feign;



import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import sprssozdemo1.libCommon.domain.*;


@FeignClient(name = "sprcld-prvdres2layer")
public interface FeignClientPrvdRes2L1 {
	
	@RequestMapping(method = RequestMethod.GET, value="/info1/procid")
	public String getServiceProcid();
	
	@RequestMapping(method = RequestMethod.GET, value="/postprov1/postid/{postId}")
	public BbsPost getByPostId(@PathVariable("postId") long postId) ;
	
	@RequestMapping(method = RequestMethod.GET, value="/postprov1/postid2/{postId}")
	public RestActResultTBbsPost getByPostId2(@PathVariable("postId") long postId) ;
	
	@RequestMapping(method = RequestMethod.GET, value="/postprov1/getPosts1")
	public BbsPost[] getPosts1();
	
	@RequestMapping(method = RequestMethod.GET, value="/postprov1/getPosts2")
	public RestActResultTBbsPostList getPosts2();
	
	@RequestMapping(method = RequestMethod.POST, value="/postprov1/insertByJson")
	public RestActResultTLong insert(BbsPost post);
	
	@RequestMapping(method = RequestMethod.POST, value="/postprov1/updateByJson")
	public RestActResultTLong update(BbsPost post);
	
	@RequestMapping(method = RequestMethod.POST, value="/postprov1/delete/{postId}")
	public RestActResultTLong delete(@PathVariable("postId") long postId) ;
	
	
	
}
