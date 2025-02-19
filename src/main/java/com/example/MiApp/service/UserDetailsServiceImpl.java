package com.example.MiApp.service;

import com.example.MiApp.entities.Usuario;
import com.example.MiApp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByNombreUsuario(username);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return User.builder()
                .username(usuario.get().getNombreUsuario())
                .password(usuario.get().getContrasenaHash())  // Asegúrate de almacenar contraseñas encriptadas
                .roles("USER")
                .build();
    }
}