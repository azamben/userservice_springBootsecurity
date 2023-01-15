package io.getarray.userservice.security;

import io.getarray.userservice.filter.CostumerAuthenticationFilter;
import io.getarray.userservice.filter.CostumerAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private  final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // utlisateur sont stockes  dans la mémoire InMemory
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
// methode pour spécifier les droits d'accès
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CostumerAuthenticationFilter costumerAuthenticationFilter = new CostumerAuthenticationFilter(authenticationManagerBean());
        costumerAuthenticationFilter.setFilterProcessesUrl("/api/login/**");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeHttpRequests().antMatchers("/api/login/**","/api/token/refresh/**").permitAll();

        http.authorizeHttpRequests().antMatchers(GET,"/api/user/**").hasAnyAuthority("ROLE_USER");
        http.authorizeHttpRequests().antMatchers(POST,"/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");

        //http.authorizeHttpRequests().anyRequest().permitAll();

        http.authorizeHttpRequests().anyRequest().authenticated();
        http.addFilter(costumerAuthenticationFilter);
        http.addFilterBefore(new CostumerAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean () throws Exception{
        return super.authenticationManagerBean();

    }

}
