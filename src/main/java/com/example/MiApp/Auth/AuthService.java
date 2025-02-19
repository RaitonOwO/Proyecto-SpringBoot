package com.example.MiApp.Auth;

import com.example.MiApp.JWT.JwtService;
import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public String authenticate(Usuario usuario) {
        Optional<Usuario> user = usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario());

        if (user.isEmpty() || !passwordEncoder.matches(usuario.getContrasenaHash(), user.get().getContrasenaHash())) {
            return null;
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.getNombreUsuario(), usuario.getContrasenaHash())
        );

        return jwtService.generateToken(user.get());
    }
}