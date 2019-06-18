package com.everis.springboot.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.everis.springboot.app.auth.handler.LoginSuccessHandler;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private LoginSuccessHandler successHandler; 
    
   @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.authorizeRequests().antMatchers("/", "/css/**", "/js/**","/images/**","/listing").permitAll()
	//.antMatchers("/look/**").hasAnyRole("USER")
	//.antMatchers("/uploads/**").hasAnyRole("USER")
	//.antMatchers("/form/**").hasAnyRole("ADMIN")
	//.antMatchers("/bill/**").hasAnyRole("ADMIN")
	//.antMatchers("/delete/**").hasAnyRole("ADMIN")
	.anyRequest().authenticated()
	.and()
	.formLogin()
		.successHandler(successHandler)
		.loginPage("/login")
	.permitAll()
	.and()
	.logout().permitAll()
	.and()
	.exceptionHandling().accessDeniedPage("/error_403");
    }

    @Autowired
    public void configurerGlobal (AuthenticationManagerBuilder build) throws Exception {
	
	build.jdbcAuthentication()
	.dataSource(dataSource)
	.passwordEncoder(passwordEncoder)
	.usersByUsernameQuery("select username, password, enabled from users where username=?")
	.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?");
	
	/* Coding for InMemory Authentication
	 * 
	 * 
	 * PasswordEncoder encoder = passwordEncoder;
	UserBuilder users = User.builder().passwordEncoder(encoder::encode);
	
	builder.inMemoryAuthentication()
	.withUser(users.username("admin").password("12345").roles("ADMIN","USER"))
	.withUser(users.username("frank").password("12345").roles("USER"));
	
	*/
    }
    
}
