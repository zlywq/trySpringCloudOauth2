package sprssozdemo1.sprssoLibDs2Spr.service;

import java.util.*;


import org.apache.ibatis.session.SqlSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


import sprssozdemo1.libCommon.util.*;
import sprssozdemo1.libSpr.domain.*;

import sprssozdemo1.libSpr.service.IdGeneratorLikeSnowflake;
import sprssozdemo1.libSpr.service.UtilService;
import sprssozdemo1.sprssoLibDs2Spr.domain.Message;
import sprssozdemo1.sprssoLibDs2Spr.ibatisMapper.MessageMapper;


/*
TODO 待去除对主的sprssoLibSpr模块中的一些类的依赖，待细分模块......
 */

@Service
public class MessageService {
	
	@Autowired
	IdGeneratorLikeSnowflake idGeneratorLikeSnowflake;
	
	@Autowired
	private MessageMapper messageMapper;
	
		
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	
	@Autowired
	UtilService utilService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	public Message getById(long messageId){
		return messageMapper.getById(messageId);
	}
	@Transactional(rollbackFor=Exception.class)
	public Message getByIdInTran(long messageId){
		return messageMapper.getById(messageId);
	}
	
	
	public List<Message> getByUserId(long userId){
		return messageMapper.getByUserId(userId);
	}
	
	public List<Message> getMessages(){
		return messageMapper.getMessages();
	}
	
	public List<Message> getOnlyDeleted(){
		return messageMapper.getOnlyDeleted();
	}

	
	public void checkFields(Message message,boolean isInsert){
		String errMsg = null;
		if (isInsert){
			if ( message.getUserId()==0 ){
				utilService.throwEx(ErrCode.Bus_ParamErr,"userId needed", message);
			}
		}
		
		if ( message.getUserId()==0 || Util1.isStringEmpty(message.getTitle()) 
				|| Util1.isStringEmpty(message.getContent()) 
				){
			errMsg = "请输入标题，内容等必要字段";
			utilService.throwEx(ErrCode.Bus_ParamErr,errMsg, message);
		}else {	
		}
		return ;
	}
	
	private void checkTran_insert2AndThrow(){
		Message message = new Message();
		long messageId = idGeneratorLikeSnowflake.generateId(Message.class);
		message.setMessageId(messageId);
		message.setUserId(1);
		message.setTitle(messageId+"");
		message.setContent(messageId+"");
		checkFields(message,true);
		messageMapper.insert(message);
		
		Message message2 = new Message();
		long messageId2 = idGeneratorLikeSnowflake.generateId(Message.class);
		message2.setMessageId(messageId2);
		message2.setUserId(1);
		message2.setTitle(messageId+"");
		message2.setContent(messageId+"");
		messageMapper.insert(message2);
		
		MyBaseException ex = new MyBaseException();
		HashMap<String,Object> data = new HashMap<>();
		data.put("id1", messageId);
		data.put("id2", messageId2);
		ex.setData(data);
		throw ex;
	}

	@Transactional(value="secondTransactionManager", rollbackFor=Exception.class)
	public void checkTran1haveTran(){
		checkTran_insert2AndThrow();
	}
	public void checkTran1noTran(){
		checkTran_insert2AndThrow();
	}
	
	
	
	@Transactional(value="secondTransactionManager", rollbackFor=Exception.class)
	public void insert(Message message){
		checkFields(message,true);
		
		long messageId = idGeneratorLikeSnowflake.generateId(Message.class);
		message.setMessageId(messageId);
		messageMapper.insert(message);
	}
	
	
	
	@Transactional(value="secondTransactionManager", rollbackFor=Exception.class)
	public void update(Message message){
		checkFields(message,false);
		
		if (message.getMessageId()==0){
			utilService.throwEx(ErrCode.Bus_ParamErr,"messageId needed", message);
		}
		Message oldMessage = messageMapper.getByIdForUpdate(message.getMessageId());
		if (oldMessage == null){
			utilService.throwEx(ErrCode.Bus_ParamErr,"no this message", message);
		}
		if (oldMessage.getUserId() != message.getUserId()){
//			utilService.throwEx(ErrCode.Bus_Common,"only can edit self message", message);
		}
		
		messageMapper.update(message);
	}
	
	
	@Transactional(value="secondTransactionManager", rollbackFor=Exception.class)
	public void delete(long messageId, long userIdDoing){
		if (messageId==0){
			utilService.throwEx(ErrCode.Bus_ParamErr,"messageId needed");
		}
		Message message = messageMapper.getByIdForUpdate(messageId);
		if (message == null){
			return;
		}
		if (message.getUserId() != userIdDoing){
//			utilService.throwEx(ErrCode.Bus_Common,"only self can delete message", message);
		}
		messageMapper.delete(messageId);
	}
	
	
	
	public List<Message> getMessagesPaged(long upperMessageIdExclude, int pageSize){
		return messageMapper.getMessagesPaged(upperMessageIdExclude, pageSize);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
