package com.infosys.sejuta_kebaikan_cms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.infosys.sejuta_kebaikan_cms.service.cms.CmsUserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
    @Autowired
    private CmsUserDetailService cmsUserDetailService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    	.userDetailsService(cmsUserDetailService)
    	.passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        	.antMatchers("/").permitAll()
        	.antMatchers("/login").permitAll()
        	.antMatchers("/pages/**").hasAuthority("ADMIN").anyRequest().authenticated()
    	.and()
    		.csrf().disable()
        	.formLogin()
    		.loginPage("/login")
//    		.loginProcessingUrl("/login")
    		.failureUrl("/login?error=true")
    		.defaultSuccessUrl("/pages/dashboard")
    		.usernameParameter("username")
    		.passwordParameter("password")
        .and()
        	.logout()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        	.logoutSuccessUrl("/login").and().exceptionHandling()
        	.accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
        .ignoring()
        .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/assets/**", "/scripts/**");
    }
}
