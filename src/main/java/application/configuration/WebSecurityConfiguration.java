package application.configuration;

import application.security.RESTAuthenticationEntryPoint;
import application.security.authenticationProvider.PhoneNumberChallengeAuthenticationProvider;
import application.security.authenticationProvider.TokenAuthenticationProvider;
import application.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by matan,
 * On 04/10/2016.
 */

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${security.token.header}")
    private String tokenHeader;

    @Autowired
    private RESTAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private PhoneNumberChallengeAuthenticationProvider phoneNumberChallengeAuthenticationProvider;

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(phoneNumberChallengeAuthenticationProvider).
                authenticationProvider(tokenAuthenticationProvider);
    }

    @Bean(name="giftAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter(tokenHeader, authenticationManager());
    }

//    @Bean
//    public AuthenticationProvider domainUsernamePasswordAuthenticationProvider() {
//        return new PhoneNumberChallengeAuthenticationProvider(tokenService(), someExternalServiceAuthenticator());
//    }
//
//    @Bean
//    public AuthenticationProvider tokenAuthenticationProvider() {
//        return new TokenAuthenticationProvider(tokenService());
//    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception
    {
        webSecurity
                .ignoring()
                //whatever is here is ignored by All of Spring Security

                //Authentication
                .antMatchers("/authentication/phoneNumberChallenge")
                .antMatchers("/authentication/token")

                //Swagger
                .antMatchers("/webjars/springfox-swagger-ui/**")
                .antMatchers("/configuration/ui")
                .antMatchers("/configuration/security")
                .antMatchers("/swagger-resources")
                .antMatchers("/v2/api-docs")
                .antMatchers("/swagger-ui.html");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                authorizeRequests().
                antMatchers("/authentication/**").permitAll().
                anyRequest().authenticated().
                and().
                anonymous().disable().
                exceptionHandling().authenticationEntryPoint(unauthorizedHandler);


        // Custom JWT based security filter
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }
}