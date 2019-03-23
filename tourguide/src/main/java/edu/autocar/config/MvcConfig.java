package edu.autocar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
/*
 * dao vs service
 * 여러 dao가 모여 하나의 의미있는 서비스를 만들 때
 */

import edu.autocar.interceptor.AdminInterceptor;
import edu.autocar.interceptor.LoginInterceptor;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
//Rollback / commit 결정 지원(AOP)

@ComponentScan(basePackages = { "edu.autocar" })
//controller, dao, service 빈 관리는 여기서
public class MvcConfig implements WebMvcConfigurer {
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		// css, js, 이미지 등의 정적 파일 배치 위치 등록 - 스프링이 처리 않함
		registry.addResourceHandler("/resources/**") // 적용 경로 //*이 두개있으면 그 아래 계속해서 다
				.addResourceLocations("/resources/") // 웹 경로 // *이 하나있으면 그 바로 아래만 다
				.setCachePeriod(0);
		// 서버단의 캐시 끄기
		// 브라우저의 캐시도 따로 꺼야 함
		// network - caching disabled
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		//'190320(gallery)
		//뷰의 이름을 빈의 이름으로 보고 먼저 물어본다. 그리고 tiles, 다음에 .jsp
		registry.viewResolver(new BeanNameViewResolver());
		
		// JSP 뷰 리졸러 설정
		// 뷰 이름 앞,뒤에 붙일 prefix(접두어), surfix(접미어) 설정
		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
		// registry.jsp("/WEB-INF/views/", ".jsp"); //이 메소드는 항상 뒤에 있어야 함!
		// hello.jsp의 경우
		// tiles에 hello라는 이름의 view가 등록이 안되어 있어 404에러 발생
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles/tiles-layout.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	// autowire 말고 직접 bean 등록하기
	// 클래스를 component로 등록 후 autowire로 멤버변수 활용 가능
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Bean
	public AdminInterceptor adminInterceptor() {
		return new AdminInterceptor();
	}

	// 인터셉터 등록
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 등록된 순서대로 검사
//    	registry.addInterceptor(loginInterceptor())
//    			.addPathPatterns(new String[] { 	//적용 url
//			    					"/member/**", 
//			    					"/admin/**", 
//			    					"/board/*"})
//         		.excludePathPatterns(new String[] {	//미적용 url
//		         					"/board/list",
//		         					"/board/view"});
//
//    	registry.addInterceptor(adminInterceptor())
//				.addPathPatterns("/admin/**");
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() { 
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		//incoding type이 멀티파트이면 commonsmutlipartresolver() 동작!
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

}
