package site.metacoding.white.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FilterConfig {

	// IoC등록 (서버 실행시)
	@Bean
	public FilterRegistrationBean<HelloFilter> jwtAuthenticationFilterRegister() {
		log.debug("디버그 : 인증 필터 등록");
		FilterRegistrationBean<HelloFilter> bean = new FilterRegistrationBean<>(new HelloFilter());
		bean.addUrlPatterns("/hello");

		return bean;
	}

}

// 디스패처서블릿 직전에서 실행되기에 servlet Filter 사용
@Slf4j
class HelloFilter implements Filter {

	// /hello 요청시
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)// request에 모든걸 담아서 요청을
																								// 받으면 response에 담아서 보냄.
																								// 최초여서 가장 순수함
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (req.getMethod().equals("POST")) {
			log.debug("디버그 : HelloFilter 실행됨");
		} else {
			log.debug("디버그 : POST요청이 아니어서 실행할 수 없습니다");
		}

		// chain.doFilter(req, resp); // 다음 필터를 걸지 않으면 통신 종료됨
	}

}