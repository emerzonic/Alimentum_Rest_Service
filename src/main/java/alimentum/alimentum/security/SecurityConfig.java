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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static alimentum.alimentum.security.SecurityConstants.*;


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
                        CustomUserDetailsService customUserDetailsService,
                        JwtAuthenticationEntryPoint unauthorizedHandler
                        )                                                            {
    this.unauthorizedHandler = unauthorizedHandler;
    this.customUserDetailsService = customUserDetailsService;
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter(){return new JwtAuthenticationFilter();}


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
                  .antMatchers(
                          "/",
                          "/favicon.ico",
                          "/**/*.png",
                          "/**/*.gif",
                          "/**/*.svg",
                          "/**/*.jpg",
                          "/**/*.html",
                          "/**/*.css",
                          "/**/*.js"
                  ).permitAll()
                  .antMatchers("/",EXTERNAL_API_URLS,AUTH_URLS).permitAll()
                  .anyRequest().authenticated();
//              .antMatchers("/api/admin/**").hasRole("ADMIN");//for future development

    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();

  }
}





