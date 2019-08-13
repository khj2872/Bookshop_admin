package com.kobobook.www.admin.config;

import com.kobobook.www.admin.service.AuthProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthProvider authProvider;

    private AuthFailureHandler authFailureHandler;

    private AuthSuccessHandler authSuccessHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 허용되어야 할 경로들
        web.ignoring().antMatchers("/resources/**", "/js/**", "/css/**", "/img/**", "/fonts/**", "/font-awesome-4.7.0/**", "/icons-reference/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 로그인 설정
        http.authorizeRequests()
                    // ROLE_USER, ROLE_ADMIN으로 권한 분리 URL 정의
                    .antMatchers("/members/login", "/profile", "/actuator/health", "/error**").permitAll()
    //                .antMatchers("/*").hasAuthority("ROLE_USER")
                    .antMatchers("/**").access("hasAuthority('ROLE_ADMIN')")
                    .antMatchers("/admin/**").access("hasAuthority('ROLE_ADMIN')")
                    .anyRequest().authenticated()
                .and()
                    // 로그인 페이지 및 성공 url, handler 그리고 로그인 시 사용되는 id, password 파라미터 정의
                    .formLogin()
                    .loginPage("/members/login")
                    .failureHandler(authFailureHandler)
                    .successHandler(authSuccessHandler)
                    .usernameParameter("userEmail")
                    .passwordParameter("password")
                .and()
                    // 로그아웃 관련 설정
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                    .logoutSuccessUrl("/members/login")
                    .invalidateHttpSession(true)
                .and()
                    // csrf 사용유무 설정
                    // csrf 설정을 사용하면 모든 request에 csrf 값을 함께 전달해야한다.
                    .csrf()
//                .disable()
                .and()
                    // 로그인 프로세스가 진행될 provider
                    .authenticationProvider(authProvider);
    }

}