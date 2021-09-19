package uz.spring.appanotherwarehouseproject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.spring.appanotherwarehouseproject.Dto.Response;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityOperation extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .inMemoryAuthentication()
                .withUser("Doston").password(passwordEncoder().encode("Ergashev")).roles("Director")
                .authorities("POST_ALL", "POST_ONE", "GET_ALL", "GET_ONE", "DELETE_ONE", "DELETE_ALL", "EDIT")
                .and()
                .withUser("Rustam").password(passwordEncoder().encode("Erdonov")).roles("Engineer")
                .authorities("GET_ALL", "GET_ONE", "EDIT")
                .and()
                .withUser("Boxodir").password(passwordEncoder().encode("Jalolov")).roles("Delivery")
                .authorities("GET_ONE");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
