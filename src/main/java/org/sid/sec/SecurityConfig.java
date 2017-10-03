package org.sid.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource datasource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("admin").password("1234").roles("ADMIN","USER");
//		auth.inMemoryAuthentication().withUser("user").password("1234").roles("USER");
		auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("select id as principal, password as credentials, true from users where username=?")
		.authoritiesByUsernameQuery("select user_id as principal, roles_role as role from users_roles where user_id=?")
		.rolePrefix("ROLE_");
		
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/css/**","/js/**","/node_modules/**").permitAll()	
			.anyRequest()
					.authenticated()
						.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
			.defaultSuccessUrl("/accueil.html")
			.and()
			.logout()
				.invalidateHttpSession(true)
				.logoutUrl("/logout")
				.permitAll();
			//.failureUrl("/403");
		http.authorizeRequests().antMatchers("/index","/rechercheCandidat").hasRole("USER");
		http.authorizeRequests().antMatchers("/form","/editCandidat","/SupprimerCandidat").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403");
		
	}
}
