package br.com.caelum.ingresso.configuracao;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/compra/**")
				.hasRole("COMPRADOR").antMatchers("/filme/**").permitAll().antMatchers("/sessao/**/lugares").permitAll()
				.antMatchers("/magic/**").permitAll().antMatchers("/").permitAll().anyRequest().authenticated().and()
				.formLogin().usernameParameter("email").loginPage("/login").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}
}
