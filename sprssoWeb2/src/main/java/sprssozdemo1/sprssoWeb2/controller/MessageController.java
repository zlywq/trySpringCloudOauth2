package sprssozdemo1.sprssoWeb2.controller;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

//import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import sprssozdemo1.libCommon.domain.*;
import sprssozdemo1.libCommon.util.*;
import sprssozdemo1.sprssoLibDs2Spr.service.*;





/*

 */

@Controller
@RequestMapping("/message")
public class MessageController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());


	@Autowired
	MessageService messageService;
	@Autowired
	CheckTranDs2Service checkTranService;

	
//	@Autowired
//	UtilService utilService;
//	
//	@Autowired
//	UtilBean utilBean;
	
	
	
	/*
	 * http://localhost:8080/smmpWeb/message/getById.json?messageId=123
	 */
	@RequestMapping(value = "/getById")
	public String getMessage(HttpServletRequest request, ModelMap model, long messageId) {
		try{
			//webContext.setVariable("utilBean", utilBean);
			//request.getServletContext().setAttribute
//			request.getSession().setAttribute("utilBean", utilBean);//err: DefaultSerializer requires a Serializable payload but received an object of type [g1.service.UtilBean]
//			SpringContextUtils a;

//			int i = request.getSession().getMaxInactiveInterval();
//			logger.debug("request.getSession().getMaxInactiveInterval()="+i);
			
			Message message = messageService.getById(messageId);
			model.put("message", message);
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
		}
		return "message/message";
	}
	
	//@RequestMapping(value = "/getById_json", produces="application/json")//Error resolving template "message/getById_json", template might not exist or might not be accessible by any of the configured Template Resolvers
	@RequestMapping(value = "/getById_json")
	@ResponseBody
	public Map<String,Object> getMessage(long messageId ) {
		HashMap<String,Object> dtMap = new HashMap<>();
		try{
			
			
			
			Message message = messageService.getById(messageId);
			dtMap.put("message", message);
			dtMap.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, dtMap);
		}
		return dtMap;
	}
	
	
	
	
	/*
	 * http://localhost:8080/smmpWeb/message/getMessages.json
	 */
	@RequestMapping(value = "/getMessages")
	public String getMessages(ModelMap model) {
		try{
			List<Message> messageList = messageService.getMessages();
			model.put("messageList", messageList);
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
		}
		return "message/messageList";
	}
	
	@RequestMapping(value = "/getOnlyDeleted")
	public String getOnlyDeleted(ModelMap model) {
		try{
			List<Message> messageList = messageService.getOnlyDeleted();
			model.put("messageList", messageList);
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
		}
		return "message/messageList";
	}
	
	@RequestMapping(value = "/checkTran")
	public String checkTran(ModelMap model) {
		try{
			checkTranService.checkTranMessage();
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
			return "error";
		}
		return "empty";
	}
	
	/*
	 * http://localhost:8080/smmpWeb/message/insert.json?title=aa&content=aaa
	 */
	@RequestMapping(value = "/insert")
	public String insert(ModelMap model, Message message, Principal puser) {
		try{
			//long userIdInSession = Util1.getUserIdInSession();
			long userIdInSession = Util1.getUserIdInSso(puser);
			message.setUserId(userIdInSession);
			
			messageService.insert(message);
			model.put("messageId", message.getMessageId());
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
		}
		return "empty";
	}
	
	
	/*
	 * http://localhost:8080/smmpWeb/message/update.json?messageId=12&title=aa&content=aaa
	 */
	@RequestMapping(value = "/update")
	public String update(ModelMap model, Message message, Principal puser) {
		try{
			//long userIdInSession = Util1.getUserIdInSession();
			long userIdInSession = Util1.getUserIdInSso(puser);
			message.setUserId(userIdInSession);
			
			messageService.update(message);
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
		}
		return "empty";
	}
	
	
	/*
	 * http://localhost:8080/smmpWeb/message/delete.json?messageId=12
	 */
	@RequestMapping(value = "/delete")
	public String delete(ModelMap model, long messageId, Principal puser) {
		try{
			//long userIdInSession = Util1.getUserIdInSession();
			long userIdInSession = Util1.getUserIdInSso(puser);
			messageService.delete(messageId, userIdInSession);
			model.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, model);
		}
		return "empty";
	}
	
	
}
