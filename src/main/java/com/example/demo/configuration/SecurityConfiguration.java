package com.example.demo.configuration;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable();
		
		http.authorizeHttpRequests((authorize) -> authorize
		.requestMatchers("/resources/**", "/login","/").permitAll()         
		.requestMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().permitAll()); 
		
		http.formLogin((form) -> form
				.loginPage("/login")
				.permitAll());
		
		http
        .logout(logout -> logout                                                
            .logoutUrl("/logout")                                            
            .logoutSuccessUrl("/")                                                           
            .invalidateHttpSession(true)                                                                          
            .deleteCookies()                                  
        );
		
		return http.build();
	}

	@Bean
	public UserDetailsService users() {
		// The builder will ensure the passwords are encoded before saving in memory
		UserDetails user1 = User.withUsername("luana")
			.password(passwordEncoder().encode("bingo11"))
			.roles("ADMIN")
			.build();
		UserDetails user2 = User.withUsername("maria")
			.password(passwordEncoder().encode("ciaociao99"))
			.roles("ADMIN")
			.build();
		List<UserDetails> l = new LinkedList<>();
		l.add(user1);
		l.add(user2);
		return new InMemoryUserDetailsManager(l);
	}

}

