package com.example.MiApp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.MiApp.JWT.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * Configuración de seguridad para la aplicación.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    /**
     * Configura la cadena de filtros de seguridad.
     *
     * @param http La instancia de HttpSecurity.
     * @return La cadena de filtros de seguridad configurada.
     * @throws Exception Si ocurre un error en la configuración de seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Habilita CORS
            .csrf(csrf -> csrf.disable()) // Desactiva CSRF (recomendado para APIs)
            .authorizeHttpRequests(authRequest -> authRequest
                .requestMatchers("/auth/**").permitAll()  // Permitir auth sin token
                .requestMatchers("/api/publicaciones/**").permitAll()  // Permitir el endpoint de publicaciones
                .anyRequest().authenticated()
            )
            .sessionManagement(sessionManager -> sessionManager
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    /**
     * Configura los orígenes de las solicitudes CORS.
     *
     * @return La fuente de configuración CORS configurada.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Permitir frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
