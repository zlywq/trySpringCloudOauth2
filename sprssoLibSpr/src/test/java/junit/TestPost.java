package junit;




import java.util.*;

import org.bson.*;
import org.bson.types.*;

import com.mongodb.*;
import com.mongodb.client.*;

import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import sprssozdemo1.libSpr.service.*;
import sprssozdemo1.libCommon.domain.BbsPost;
import sprssozdemo1.libCommon.util.*;





@ContextConfiguration(locations = TestBase.springXmlConfigFilePath)
public class TestPost extends TestBase{

	protected final Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	BbsPostService postService;

	
	@Autowired
	UtilService utilService;
	



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain(){

		test1();
	}
	public void test1(){
		logger.debug(""+this.getClass().getSimpleName()+"."+Util1.getMethodName()+" enter");
		Date dtNow = new Date();
		BbsPost post = new BbsPost();
		long userId = dtNow.getTime();
		String title1 = "title"+dtNow.getTime(); 
		post.setUserId(userId);
		post.setTitle(title1);
		post.setContent("content"+dtNow.getTime());
		postService.insert(post);
		
		assertTrue(post.getPostId()>0);
		long postId = post.getPostId();
		
		BbsPost post10 = postService.getById(postId);
		assertTrue(post10!=null);
		assertTrue(title1.equals(post10.getTitle()));
		
		postService.delete(postId, 0);
		BbsPost post20 = postService.getById(postId);
		assertTrue(post20==null);
		
		
	}
	
	
}
