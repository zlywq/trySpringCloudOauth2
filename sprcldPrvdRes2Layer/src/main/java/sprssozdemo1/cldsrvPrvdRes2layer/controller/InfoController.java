package sprssozdemo1.cldsrvPrvdRes2layer.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import sprssozdemo1.libCommon.util.*;


@RestController
@RequestMapping("/info1")
public class InfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

	@Value("${spring.application.name}")
	String spring_application_name;
	
	@RequestMapping("/procid")
	public String procid() {
	    String s = Util1.getProcessInfoStr();
	    s = spring_application_name + " | " + s;
	    return s;
	}
}
