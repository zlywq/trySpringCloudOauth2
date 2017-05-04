package sprssozdemo1.sprssoWeb1.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sprssozdemo1.libCommon.util.Const;
import sprssozdemo1.libCommon.util.UtilMsg;


@Controller
@RequestMapping("/tmptest")
public class TmptestController {
	
	@RequestMapping(value = "/getsome_json")
	@ResponseBody
	public Map<String,Object> getsome() {
		HashMap<String,Object> dtMap = new HashMap<>();
		try{
			
			dtMap.put("dtnow", new Date());
			dtMap.put(Const.Key_success, true);
		}catch(Exception e){
			UtilMsg.retriveErrMsgAndCodeToMap_withLog(e, dtMap);
		}
		return dtMap;
	}

}
