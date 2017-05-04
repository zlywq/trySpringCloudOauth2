package sprssozdemo1.libWebCommon.controller;


import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamResult;

import org.springframework.oxm.Marshaller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import sprssozdemo1.libCommon.util.*;

/*
目前在ContentNegotiatingViewResolver使用本类返回的xml数据中，有时带有一些spring本身的类的数据，如org.springframework.validation.BeanPropertyBindingResult；
对比json返回，就没有这个，也许得参考json的源代码过滤之。......TODO
 */
public class MyXmlView extends AbstractView {

	/**
	 * Default content type. Overridable as bean property.
	 */
	public static final String DEFAULT_CONTENT_TYPE = "application/xml";


	

	/**
	 * Construct a new {@code MarshallingView} with no {@link Marshaller} set.
	 * The marshaller must be set after construction by invoking {@link #setMarshaller}.
	 */
	public MyXmlView() {
		setContentType(DEFAULT_CONTENT_TYPE);
		setExposePathVariables(false);
	}

	




//	@Override
//	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		String strXml = Util1.convertXmlStrFromMapSimple_ByElement(model);//TODO POJO attr to xml str
//		byte[] bytesXml = strXml.getBytes(Const.Charset_Encoding_UTF_8);
////		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
//		setResponseContentType(request, response);		
//		response.setContentLength(bytesXml.length);
//		response.getOutputStream().write(bytesXml);
////		baos.writeTo(response.getOutputStream());
//	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		XStream xstream=new XStream(new DomDriver());
		String strXml=xstream.toXML(model);
		byte[] bytesXml = strXml.getBytes(Const.Charset_Encoding_UTF_8);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
		setResponseContentType(request, response);		
		response.setContentLength(bytesXml.length);
		response.getOutputStream().write(bytesXml);
//		baos.writeTo(response.getOutputStream());
	}

	

}
