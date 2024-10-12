package travelPlanPJ;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	InterceptorConfig interceptorConfig;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> excludeList = new ArrayList<String>();
		excludeList.add("/register/**/*");
		excludeList.add("/help/**/*");
		excludeList.add("/login/**/*");
		excludeList.add("/static/**/*"); //CSS, FONT, JS 등 정적 자원은 허용
		excludeList.add("/community/**/*");
		//excludeList.add("/admin/**/*"); 관리자 차단 여부 테스트하기
		registry.addInterceptor(interceptorConfig)
				.addPathPatterns("/**/*")
				.excludePathPatterns(excludeList);
	}
	
}
