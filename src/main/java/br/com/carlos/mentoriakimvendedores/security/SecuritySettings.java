package br.com.carlos.mentoriakimvendedores.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecuritySettings extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/product").hasRole("ADMIN")
                .antMatchers("/sale").hasRole("ADMIN")
                .antMatchers("/salesman").hasRole("ADMIN")
                .antMatchers("/tabelavenda").hasRole("ADMIN")
                .antMatchers("/tabelaproduto").hasRole("ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin()
                .loginPage("/login.html")
                .defaultSuccessUrl("/", true)
                .failureForwardUrl("/login-error.html")
                .and()
                .logout().logoutUrl("/logout.html")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .invalidateHttpSession(true);

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}