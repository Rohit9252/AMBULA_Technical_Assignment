package com.userLocation.config;

import com.userLocation.jwt.AuthTokenFilter;
import com.userLocation.jwt.EntryPoint;
import com.userLocation.service.UserModelServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Configuration class for user-related settings and security filters.
 */

@Configuration
@RequiredArgsConstructor
public class UserConfig {

    private final  UserModelServiceImpl userModelServiceImpl;



    private final AuthTokenFilter authTokenFilter;


    private final EntryPoint entryPoint;

    /**
     * Configures the security filter chain for the application.
     *
     * @param http the {@link HttpSecurity} object to configure the security filters
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs during the configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.exceptionHandling().authenticationEntryPoint(entryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/","/api/signup","/h2-console/**", "/api/login","/api/test/all", "/api/getUser").permitAll()
                            .requestMatchers("/api/test/user").hasAnyAuthority("READER")
                            .requestMatchers("/api/test/admin", "/api/create_data", "/api/update_data/**").hasAnyAuthority("ADMIN")
                            .requestMatchers(("/api/get_users/**"), ("/api/get_users")).hasAnyAuthority("ADMIN", "READER")
                            .anyRequest().authenticated();
                }).cors().and().csrf().disable();
        http.authenticationProvider(daoAuthenticationProvider());
        http.addFilterBefore(tokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Creates and configures the {@link DaoAuthenticationProvider} bean.
     *
     * @return the configured {@link DaoAuthenticationProvider} bean
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userModelServiceImpl);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * Creates and configures the {@link PasswordEncoder} bean.
     *
     * @return the configured {@link PasswordEncoder} bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() ;

    }


    /**
     * Creates and configures the {@link AuthenticationManager} bean.
     *
     * @param auth the {@link AuthenticationConfiguration} used to retrieve the authentication manager
     * @return the configured {@link AuthenticationManager} bean
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {

        return auth.getAuthenticationManager()   ;

    }



    /**
     * Creates and configures the {@link AuthTokenFilter} bean.
     *
     * @return the configured {@link AuthTokenFilter} bean
     */
    @Bean
    public AuthTokenFilter tokenFilter(){
        return new AuthTokenFilter();
    }






}
