package com.ideas.springboot.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ideas.springboot.app.auth.handler.LoginSuccessHandler;

/**
 * The Class SpringSecurityConfig. Clase donde se configura la seguridad (Spring Security)
 * @author Israel Bejarano
 */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/** The success handler. */
	@Autowired
	private LoginSuccessHandler successHandler;
	
	/** The password encoder. */
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	/**
	 * Configure. Permite el qué ver según tu rol en la app. Como los guards en Angular para las rutas
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
		// .antMatchers("/ver/**").hasAnyRole("USER")
		// .antMatchers("/uploads/**").hasAnyRole("USER")
		// .antMatchers("/form/**").hasAnyRole("ADMIN")
		// .antMatchers("/eliminar/**").hasAnyRole("ADMIN")
		// .antMatchers("/factura/**").hasAnyRole("ADMIN")
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
	
	/**
	 * Configurer globarl.
	 *
	 * @param builder the builder
	 * @throws Exception the exception
	 */
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		builder.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(passwordEncoder)
		.usersByUsernameQuery("select username, password, enabled from users where username=?")
		.authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?");
		/*PasswordEncoder encoder = this.passwordEncoder;
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
		.withUser(users.username("israel").password("12345").roles("USER"));*/
	}
}