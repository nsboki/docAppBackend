/**
 * 
 */
package ftn.diplomski.config;

import java.security.SecureRandom;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ftn.diplomski.service.UserSecurityService;

/**
 * @author Boki on Dec 20, 2017
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
	private static final String SALT = "salt";	//Salt should be protected carefully
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}

	private static final String[] PUBLIC_MATCHERS = {
			"/webjars/**",
			"/css/**",
			"/js/**",
			"/images/**",
			"/",
			"/about/**",
			"/contact/**",
			"/error/**/*",
			"/console/**",
			"/signup"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers(PUBLIC_MATCHERS)
				.permitAll().anyRequest().authenticated();
		http
				.csrf().disable().cors().disable()
				.formLogin()
				.loginPage("/index").permitAll()
				.loginProcessingUrl("/index").permitAll()
                .usernameParameter("username")
                .successHandler((request, response, authentication) -> {
                    response.setStatus(HttpServletResponse.SC_OK);
                })
                .failureHandler((request, response, exception) -> {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                })
//				.failureUrl("/index?error")
//				.defaultSuccessUrl("/userFront").loginPage("/index").permitAll()
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/index?logout").deleteCookies("remember-me").permitAll()
				.and()
				.rememberMe();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER"); // This is in-memory
		auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
	
}
