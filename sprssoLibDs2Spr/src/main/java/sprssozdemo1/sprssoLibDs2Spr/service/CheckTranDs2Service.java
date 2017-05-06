package sprssozdemo1.sprssoLibDs2Spr.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import sprssozdemo1.libCommon.domain.Message;
import sprssozdemo1.libCommon.util.*;
import sprssozdemo1.sprssoLibDs2Spr.service.MessageService;

/*
注意最好避免类的同名，不然在默认情况下生成bean时会发生名称冲突
 */
@Service
public class CheckTranDs2Service {

	


	
	@Autowired
	private MessageService messageService;
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unchecked")
	public void checkTranMessage(){
		try{
			messageService.checkTran1noTran();
		}catch(MyBaseException ex){
			Map<String,Object> mpData = (Map<String,Object>)ex.getData();
			long id1 = (Long)mpData.get("id1");
			long id2 = (Long)mpData.get("id2");
			Message message1 = messageService.getById(id1);
			Message message2 = messageService.getById(id2);
			if (message1 == null || message2 == null){
				throw new RuntimeException("checkTran1noTran, insert 2 should succeed but not");
			}
		}
		
		try{
			messageService.checkTran1haveTran();
		}catch(MyBaseException ex){
			Map<String,Object> mpData = (Map<String,Object>)ex.getData();
			long id1 = (Long)mpData.get("id1");
			long id2 = (Long)mpData.get("id2");
			Message message1 = messageService.getById(id1);
			Message message2 = messageService.getById(id2);
			if (message1 != null || message2 != null){
				throw new RuntimeException("checkTran1haveTran, insert 2 should fail but not");
			}
		}
		
	}
	
	
	
	
	
}
