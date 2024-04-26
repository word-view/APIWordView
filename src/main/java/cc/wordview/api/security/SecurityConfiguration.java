package cc.wordview.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
        private final JwtTokenProvider jwtTokenProvider;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable());

                http.sessionManagement(management -> management
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                http.authorizeHttpRequests(requests -> requests
                                .requestMatchers("/api/v1/**").permitAll()
                                .requestMatchers("/swagger-ui").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .anyRequest().authenticated());

                http.exceptionHandling(handling -> handling.accessDeniedPage("/login"));

                http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

                return http.build();
        }

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
                return (web) -> web.ignoring().requestMatchers("/public", "/v2/api-docs", "/swagger-resources/**",
                                "/swagger-ui.html", "/swagger-ui", "/configuration/**", "/webjars/**");
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }
}
