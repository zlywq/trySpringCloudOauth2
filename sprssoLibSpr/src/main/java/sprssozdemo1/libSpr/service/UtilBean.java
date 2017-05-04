package sprssozdemo1.libSpr.service;

import java.util.Date;

import org.springframework.stereotype.Service;


import sprssozdemo1.libCommon.util.*;

/*
这个bean专为Themeleaf提供自定义函数
 */
@Service
public class UtilBean {
	
	public Date convertDateFromIntSeconds(int secondsFromBegin){
		return Util1.convertDateFromIntSeconds(secondsFromBegin);
	}

}
