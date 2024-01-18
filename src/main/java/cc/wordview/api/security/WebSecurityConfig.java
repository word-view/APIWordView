package cc.wordview.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private final JwtTokenProvider jwtTokenProvider;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable());

                http.sessionManagement(management -> management
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                http.authorizeRequests(requests -> requests
                                .antMatchers("/api/v1/**").permitAll()
                                .anyRequest().authenticated());


                http.exceptionHandling(handling -> handling.accessDeniedPage("/login"));

                http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
                web.ignoring().antMatchers("/public");
        }

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
                return super.authenticationManagerBean();
        }

}