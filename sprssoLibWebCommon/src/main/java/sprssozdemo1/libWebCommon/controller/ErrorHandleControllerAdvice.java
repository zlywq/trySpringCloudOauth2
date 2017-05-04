package sprssozdemo1.libWebCommon.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import sprssozdemo1.ClassInTopPackage;
import sprssozdemo1.libCommon.util.Util1;


@ControllerAdvice(basePackageClasses = ClassInTopPackage.class)
public class ErrorHandleControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
		HttpStatus status = getStatus(request);
		HashMap<String , Object> data = new HashMap<>();
		data.put("status", status.value());
		data.put("errmsg", ex.getMessage());
		data.put("errstack", Util1.getExceptionStackTrace(ex));
		//Object obj = new CustomErrorType(status.value(), ex.getMessage());
		return new ResponseEntity<>(data, status);
	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}
}