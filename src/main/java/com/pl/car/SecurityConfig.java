package com.pl.car;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails client = User.withDefaultPasswordEncoder()
                .username("client")
                .password("client")
                .roles("CLIENT")
                .build();

        UserDetails owner = User.withDefaultPasswordEncoder()
                .username("owner")
                .password("owner")
                .roles("OWNER")
                .build();

        UserDetails maker = User.withDefaultPasswordEncoder()
                .username("maker")
                .password("maker")
                .roles("MAKER")
                .build();

        UserDetails painter = User.withDefaultPasswordEncoder()
                .username("painter")
                .password("painter")
                .roles("PAINTER")
                .build();
        return new InMemoryUserDetailsManager(client, owner, maker, painter);
    }

    //zasada autoryzacji - dostepu przez uzytkownikow

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll()
                .and().authorizeRequests()
                .antMatchers(HttpMethod.GET,"/CarCompany/all").hasRole("CLIENT")
                .antMatchers(HttpMethod.POST,"/CarCompany").hasRole("MAKER")
                .antMatchers(HttpMethod.PUT,"/CarCompany").hasRole("PAINTER")
                .antMatchers(HttpMethod.PUT,"/CarCompany").hasRole("OWNER")
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
        http.headers().frameOptions().disable();
    }



}
