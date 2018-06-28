package br.com.projeto.supermercado;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	DataSource dataSource;
 
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select email, senha, true from pessoa where email=?")
				.authoritiesByUsernameQuery("select email, papel from pessoa where email=?");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "/home", "/js/**", "/css/**", "/images/**").permitAll()
		.antMatchers("/cadastro").anonymous()
		.antMatchers("/gerencia/**").hasAnyAuthority("GERENTE")
		.antMatchers("/cliente/**").hasAnyAuthority("CLIENTE")
		.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
		.and().logout().permitAll();
		http.exceptionHandling().accessDeniedPage("/403");
	}
}