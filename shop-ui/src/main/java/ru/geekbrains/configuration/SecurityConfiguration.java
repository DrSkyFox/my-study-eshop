package ru.geekbrains.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.geekbrains.security.UserAuthService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    private Environment env;

    @Autowired
    public void authConfigure(AuthenticationManagerBuilder auth,
                              UserAuthService userAuthService,
                              PasswordEncoder passwordEncoder,
                              Environment env) throws Exception {
        logger.info("account.superadmin.enabled = {}", Boolean.valueOf(env.getProperty("account.superadmin.enabled")));
        if(Boolean.valueOf(env.getProperty("account.superadmin.enabled"))) {
            auth.inMemoryAuthentication()
                    .withUser("1")
                    .password(passwordEncoder.encode("1"))
                    .roles("SUPER_ADMIN");
        }
        logger.info("Set Dao provider");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userAuthService);
        provider.setPasswordEncoder(passwordEncoder);
        auth.authenticationProvider(provider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http
                //configure websocket
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/gs-guide-websocket").permitAll()
                .and()
                //configure other
                .authorizeRequests()
                .antMatchers("/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.png").permitAll()
                .antMatchers(
                        "/shop/**",
                        "/account/**",
                        "/cart/**",
                        "/chat/**").permitAll()
                .antMatchers("/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/account/login").permitAll()
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/product")
                .and().logout().permitAll().logoutUrl("/account/doLogout")
                .and()
                .csrf().disable();
        ;
    }
}
