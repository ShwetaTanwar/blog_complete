package initializ.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import initializ.blog.security.CustomUserDetailService;
import initializ.blog.security.JwtAuthenticationEntry;
import initializ.blog.security.JwtAuthenticationFilter;
@Configuration
@EnableWebSecurity
@EnableWebMvc //Swagger  cng 2)
@EnableGlobalMethodSecurity(prePostEnabled=true)  //apply security on each Api 1) in user controller applying first 
public class SecurityConfig extends WebSecurityConfigurerAdapter {   //swagger cng 3 n for extra formatting swaggerConfig is created
	
	
@Autowired
	private CustomUserDetailService customUserDetailService;
@Autowired
     private JwtAuthenticationEntry jwtAuthenticationEntry;

@Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http.
	        csrf().disable()                         // type of the 
	        .authorizeHttpRequests()
	        .antMatchers(AppConstansts.PUBLIC_URLS).permitAll()  
	        //.antMatchers("/api/auth/login").permitAll()
	        //.antMatchers("/api/auth/**").permitAll()
	        //.antMatchers(""/v3/api-docs").permitAll()
	        //.antMatchers(HttpMethod.GET).permitAll()   //without login all getmapping are going to work
	        .anyRequest()
	        .authenticated()
	        .and()
	        .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntry)
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
	
	http.addFilterBefore(this.jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
	}

	@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	

	
	

}
