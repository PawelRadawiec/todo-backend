package com.info.todobackend.auth.basicAuth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JWTWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.get.token.uri}")
    private String authenticationPath;
    private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;
    private UserDetailsService jwtInMemoryUserDetailsService;
    private JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter;

    public JWTWebSecurityConfig(JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint, UserDetailsService jwtInMemoryUserDetailsService, JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationTokenFilter) {
        this.jwtUnAuthorizedResponseAuthenticationEntryPoint = jwtUnAuthorizedResponseAuthenticationEntryPoint;
        this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(jwtInMemoryUserDetailsService)
                .passwordEncoder(passwordEncoderBean());
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .anyRequest().authenticated();

        httpSecurity
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .headers()
                .frameOptions().sameOrigin()
                .cacheControl();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers(
                        HttpMethod.POST,
                        authenticationPath,
                        "/user/create",
                        "/user/activation/**"
                )
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .and()
                .ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/"
                )
                .and()
                .ignoring()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**")
                .and()
                .ignoring()
                .antMatchers("/h2-console/**/**");//Should not be in Production!
    }

}
