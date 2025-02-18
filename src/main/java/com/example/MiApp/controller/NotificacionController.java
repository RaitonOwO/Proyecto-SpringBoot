package com.example.MiApp.controller;

import com.example.MiApp.entities.Notificacion;
import com.example.MiApp.service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {
    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva notificación",
               description = "Crea una nueva notificación para un usuario",
               responses = {
                   @ApiResponse(responseCode = "201", description = "Notificación creada",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Notificacion.class))),
                   @ApiResponse(responseCode = "400", description = "Solicitud inválida")
               })
    public ResponseEntity<Notificacion> crearNotificacion(
            @RequestParam @Schema(description = "ID del usuario que recibe la notificación", example = "1") Long idUsuario,
            @RequestBody(description = "Datos de la nueva notificación", required = true) Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionService.crearNotificacion(idUsuario, notificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaNotificacion);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener notificaciones de un usuario",
               description = "Obtiene una lista de notificaciones de un usuario por su ID",
               responses = {
                   @ApiResponse(responseCode = "200", description = "Lista de notificaciones obtenida",
                                content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = List.class))),
                   @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
               })
    public ResponseEntity<List<Notificacion>> obtenerNotificacionesDeUsuario(
            @PathVariable @Schema(description = "ID del usuario", example = "1") Long idUsuario) {
        return ResponseEntity.ok(notificacionService.obtenerNotificacionesDeUsuario(idUsuario));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una notificación",
               description = "Elimina una notificación por su ID",
               responses = {
                   @ApiResponse(responseCode = "204", description = "Notificación eliminada"),
                   @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
               })
    public ResponseEntity<Void> eliminarNotificacion(
            @PathVariable @Schema(description = "ID de la notificación", example = "1") Long id) {
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }
}
