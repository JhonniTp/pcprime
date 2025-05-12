package com.pcprime.pcprime.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.pcprime.pcprime.model.UsuariosModel;
import com.pcprime.pcprime.service.UsuariosService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UsuariosController {

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<UsuariosModel> usuarios = usuariosService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("usuarioNuevo", new UsuariosModel());
        return "admin/listarusuarios";
    }

    @PostMapping("/crear")
    public String crearUsuario(@ModelAttribute("usuarioNuevo") UsuariosModel usuario, RedirectAttributes redirectAttributes) {
        try {
            usuariosService.crear(usuario);
            redirectAttributes.addFlashAttribute("success", "El usuario ha sido creado correctamente.");
        } catch (Exception e) {
            if (e.getMessage().contains("correo") || e.getMessage().contains("correo ya registrado")) {
                redirectAttributes.addFlashAttribute("correoExistente", true);
            } else {
                redirectAttributes.addFlashAttribute("error", "Hubo un error al crear el usuario: " + e.getMessage());
            }
        }
        return "redirect:/admin/usuarios#abrirModal";
    }
    @PostMapping("/actualizar/{id}")
    public String actualizarUsuario(
            @PathVariable Integer id,
            @ModelAttribute("usuarioNuevo") UsuariosModel usuario,
            RedirectAttributes redirectAttributes) {
        try {
            usuariosService.actualizar(id, usuario);
            redirectAttributes.addFlashAttribute("success", "El usuario ha sido actualizado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al actualizar el usuario: " + e.getMessage());
        }
        return "redirect:/admin/usuarios"; // Rediriges
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            usuariosService.eliminar(id);
            redirectAttributes.addFlashAttribute("success", "El usuario ha sido eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al eliminar el usuario: " + e.getMessage());
        }
        return "redirect:/admin/usuarios";
    }


    @GetMapping("/usuarios/{id}")
    @ResponseBody
    public ResponseEntity<?> getUsuarioJson(@PathVariable Integer id) {
        try {
            UsuariosModel usuario = usuariosService.obtenerPorId(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
