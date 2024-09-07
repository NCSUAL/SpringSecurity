package project.security_study.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){

        //머스테치 재설정
        MustacheViewResolver resolver = new MustacheViewResolver();
        resolver.setCharset("UTF-8");
        resolver.setContentType("text/html;charset=UTF-8"); //html 랜더링, UTF-8 기반
        resolver.setPrefix("classpath:/templates/"); //프로젝트 main 앞 /templates
        resolver.setSuffix(".html");    //파일명 .html

        registry.viewResolver(resolver);
    }

}
