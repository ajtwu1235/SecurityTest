package com.example.SecurityTest.config;

import com.example.SecurityTest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity // Spring Security 설정 클래스로 등록
@RequiredArgsConstructor
public class SecurityConfig {


  private final UserService userService;
  /**
   * 비밀번호 암호화를 위한 Bean
   * @return
   */


  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.csrf(csrf->csrf.disable());

    return http.authorizeHttpRequests(auth->
            auth.requestMatchers("/user").hasRole("MEMBER")
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .requestMatchers("/*").permitAll()

    )
            .formLogin(login->login.permitAll())
            .userDetailsService(userService)
            .build();
  }

}