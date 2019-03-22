package edu.autocar.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	//여기서 등록된 설정은 dispatcher servlet에서 사용 가능
	//반대로는 안됨
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class, DatabaseConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { MvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	//한글 처리 - 문자 인코딩 필터 설정
	@Override public void onStartup(ServletContext servletContext) throws ServletException { 
		super.onStartup(servletContext); // 컨텍스트 경로를 application 스코프에 저장하기 
		String contextPath = servletContext.getContextPath(); 
		servletContext.setAttribute("contextPath", contextPath); // 문자 인코딩 필터 설정 
		FilterRegistration charEncodingFilterReg = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class); 
		charEncodingFilterReg.setInitParameter("encoding", "UTF-8"); 
		charEncodingFilterReg.setInitParameter("forceEncoding", "true"); 
		charEncodingFilterReg.addMappingForUrlPatterns(null, true, "/*"); 
	}

}
