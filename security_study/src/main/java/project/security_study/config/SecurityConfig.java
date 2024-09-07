package project.security_study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import project.security_study.config.oauth.TestOuath2Service;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨.
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //secured: 어노테이션 활성화, @PreAuthroize, @PostAuthorize 어노테이션 활성화
public class SecurityConfig {

    private final TestOuath2Service testOuath2Service;

    @Autowired
    public SecurityConfig(TestOuath2Service testOuath2Service){
        this.testOuath2Service = testOuath2Service;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf((auth)-> auth.disable()); //csrf 공격을 방어한다
        http.authorizeRequests((auth) -> auth

                        // /** -> 모든 경로
                        .requestMatchers("/user/**").authenticated() // /user/**로 들어온 사람들은 인증이 필요하다
                        .requestMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                        // /manager/**로 들어온 사람들은 권한이 필요함
                        .requestMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                        .anyRequest().permitAll()
                );

        //로그아웃
        http.logout(logout -> logout.logoutSuccessUrl("/"));
        //로그인 페이지 설정
        http.formLogin((auth)-> auth
                .loginPage("/login")        //이거 get요청임 ㅋㅋ
                .loginProcessingUrl("/loginForm")  // loginForm으로 요청이 들어오면 시큐리티에서 처리
                .defaultSuccessUrl("/")  //loginForm으로 요청이 마무리되면 리다이렉트
        );

        http.oauth2Login(oauth2 -> oauth2
                .loginPage("/login") //oauth2 로그인 진행하기 전 주소임
                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint //유저 정보를 받아옴
                        .userService(testOuath2Service)   //service 단에서 처리
                )
        );
        return http.build();
    }
}
