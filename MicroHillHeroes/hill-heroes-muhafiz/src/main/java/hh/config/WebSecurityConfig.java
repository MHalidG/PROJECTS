package hh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import hh.jwttoken.AuthTokenFilter;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	private UserDetailsService userDetailsService;
	
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().
		sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
		authorizeRequests().antMatchers("/register,/login","/mainmenu").permitAll().
		anyRequest().authenticated();
		
		http.addFilterBefore(authenticationJwtTokenFilter(),UsernamePasswordAuthenticationFilter.class );
		
	}
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() throws Exception {
		return new AuthTokenFilter();
	}
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
