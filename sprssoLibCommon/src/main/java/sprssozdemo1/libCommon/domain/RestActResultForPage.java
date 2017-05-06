package sprssozdemo1.libCommon.domain;

import java.util.Map;

public class RestActResultForPage extends RestActResultSimple {
	int status;

	String errstack;
	


	
	public int getStatus(){
		return status;
	}
	public void setStatus(int status){
		this.status=status;
	}


	

	public String getErrstack(){
		return errstack;
	}
	public void setErrstack(String errstack){
		this.errstack=errstack;
	}
	
	
	

}










