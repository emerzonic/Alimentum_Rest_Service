package alimentum.alimentum.security;

import alimentum.alimentum.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static alimentum.alimentum.security.SecurityConstants.SIGN_UP_URLS;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//  private DataSource dataSource;
  private CustomUserDetailsService customUserDetailsService;
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Autowired
  public SecurityConfig(
//          DataSource dataSource,
                        CustomUserDetailsService customUserDetailsService,
                        JwtAuthenticationEntryPoint unauthorizedHandler
                        )                                                            {
//    this.dataSource = dataSource;
    this.unauthorizedHandler = unauthorizedHandler;
    this.customUserDetailsService = customUserDetailsService;
  }

//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//    auth.jdbcAuthentication().dataSource(dataSource)
//            .usersByUsernameQuery("select username as principal,password as credentials, true from user where username=?")
//            .authoritiesByUsernameQuery("select user_username as principal,role_name as role from user_roles where user_username=?")
//            .passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
//  }

//
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  protected AuthenticationManager authenticationManager() throws Exception {
    return super.authenticationManager();
  }

  @Override
    protected void configure(HttpSecurity http) throws Exception {
          http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
              .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
//                .headers().frameOptions().sameOrigin()
//              .and()
                .authorizeRequests()
                  .antMatchers("/","/api/**",SIGN_UP_URLS).permitAll()
                  .anyRequest().authenticated();
//              .antMatchers("/api/admin/**").hasRole("ADMIN");
    }


  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();

  }
}





