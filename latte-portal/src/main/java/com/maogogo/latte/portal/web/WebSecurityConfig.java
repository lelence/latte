package com.maogogo.latte.portal.web;

import com.maogogo.latte.portal.admin.service.AuthenticationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationUserDetailsService authenticationUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**/*", "/**/*.css", "/**/*.js");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
//                .and()
//                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
//                .anyRequest()               // 任何请求,登录后可以访问
//                .authenticated();

//        http.formLogin()                                // 定义当需要用户登录时候，转到的登录页面。
//                .loginPage("/login")                        // 设置登录页面
//                .loginProcessingUrl("/signUp")            // 自定义的登录接口
//                .defaultSuccessUrl("/home").permitAll()        // 登录成功之后，默认跳转的页面
//                .and()//.userDetailsService(authUserDetailsService)
//                .authorizeRequests()                    // 定义哪些URL需要被保护、哪些不需要被保护
//                .antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**").permitAll()
//
//                // .antMatchers("/", "/index", "/user/login").permitAll()        // 设置所有人都可以访问登录页面
//                .anyRequest().authenticated()                // 任何请求,登录后可以访问
//                .and()
//                .logout().permitAll()
//                .and().csrf().disable();


        http.authorizeRequests()
//            配置不拦截静态文件
                .antMatchers("/css/**", "/js/**", "/img/**", "/fonts/**").permitAll()
                .and()//.userDetailsService(authUserDetailsService)
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/signUp").defaultSuccessUrl("/home")
                .failureUrl("/login?error").permitAll()
                .and()
                .logout().permitAll()
                .and().csrf().disable();
    }

}
