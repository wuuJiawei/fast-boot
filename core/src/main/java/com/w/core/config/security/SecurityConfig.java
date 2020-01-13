package com.w.core.config.security;

import com.w.core.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wujiawei0926@yeah.net
 * @see
 * @since 2020/1/13
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 校验用户
        auth.userDetailsService(userDetailsService).passwordEncoder(new PasswordEncoder() {
            // 密码加密
            @Override
            public String encode(CharSequence charSequence) {
                return PasswordUtil.encrypt(charSequence.toString());
            }

            // 对密码进行匹配
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                String encrypt =  PasswordUtil.encrypt(charSequence.toString());
                return s.equals(encrypt);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                // 使用jwt，因此不需要HttpSession
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // OPTIONS请求全部放行，保证前后端分离时能正常通信
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 放行的接口
                .antMatchers("/login").permitAll()
                // 其他接口全部需要验证
                .anyRequest().authenticated();
    }

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
