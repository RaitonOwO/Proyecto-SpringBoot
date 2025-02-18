package com.example.MiApp.controller;

import com.example.MiApp.entities.Usuario;
import com.example.MiApp.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario",
               description = "Crea un nuevo usuario en la base de datos",
               responses = {
                   @ApiResponse(responseCode = "201", description = "Usuario creado",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Usuario.class))),
                   @ApiResponse(responseCode = "400", description = "Solicitud inválida")
               })
    public ResponseEntity<Usuario> crearUsuario(
            @RequestBody(description = "Datos del nuevo usuario", required = true)
            Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID",
               description = "Obtiene los detalles de un usuario por su ID",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Usuario.class))),
                   @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
               })
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario",
               description = "Elimina un usuario por su ID",
               responses = {
                   @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
                   @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
               })
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
