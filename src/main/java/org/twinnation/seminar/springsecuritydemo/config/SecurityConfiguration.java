package org.twinnation.seminar.springsecuritydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableOAuth2Client
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.httpBasic()
		//		.and()
		//	.authorizeRequests()
		//		.anyRequest().authenticated()
		//		.and()
		//	.formLogin()
		//		.and()
		//	.logout()
		//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		//		.invalidateHttpSession(true)
		//		.clearAuthentication(true)
		//		.deleteCookies("JSESSIONID")
		//		.permitAll()
		//		.and()
		//	.oauth2Login()
		//		.and();
		//	.formLogin()
		//		.loginPage("/login")
		//		.defaultSuccessUrl("/", true)
		//		.permitAll();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
			.withUser("user").password(passwordEncoder().encode("user")).roles("USER")
				.and()
			.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
