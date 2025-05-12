package com.pcprime.pcprime.controller;

import com.pcprime.pcprime.model.VendedoresModel;
import com.pcprime.pcprime.service.VendedoresService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class VendedoresController {

    private final VendedoresService vendedoresService;

    public VendedoresController(VendedoresService vendedoresService) {
        this.vendedoresService = vendedoresService;
    }

    @GetMapping("/vendedores")
    public String listarVendedores(Model model) {
        List<VendedoresModel> vendedores = vendedoresService.listarTodos();
        model.addAttribute("vendedores", vendedores);
        model.addAttribute("vendedorNuevo", new VendedoresModel());
        return "admin/listarvendedores";
    }

    @PostMapping("/vendedores/crear")
    public String crearVendedor(@ModelAttribute("vendedorNuevo") VendedoresModel vendedor,
                                RedirectAttributes redirectAttributes) {
        try {
            vendedoresService.crear(vendedor);
            redirectAttributes.addFlashAttribute("success", "El vendedor ha sido creado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al crear el vendedor: " + e.getMessage());
        }
        return "redirect:/admin/vendedores";
    }

    @PostMapping("/vendedores/actualizar/{id}")
    public String actualizarVendedor(@PathVariable Integer id,
                                    @ModelAttribute("vendedorNuevo") VendedoresModel vendedor,
                                    RedirectAttributes redirectAttributes) {
        try {
            vendedoresService.actualizar(id, vendedor);
            redirectAttributes.addFlashAttribute("success", "El vendedor ha sido actualizado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al actualizar el vendedor: " + e.getMessage());
        }
        return "redirect:/admin/vendedores";
    }

    @GetMapping("/vendedores/eliminar/{id}")
    public String eliminarVendedor(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            vendedoresService.eliminar(id);
            redirectAttributes.addFlashAttribute("success", "El vendedor ha sido eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Hubo un error al eliminar el vendedor: " + e.getMessage());
        }
        return "redirect:/admin/vendedores";
    }

    @GetMapping("/vendedores/{id}")
    @ResponseBody
    public ResponseEntity<?> getVendedorJson(@PathVariable Integer id) {
        try {
            VendedoresModel vendedor = vendedoresService.obtenerPorId(id)
                    .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
            return ResponseEntity.ok(vendedor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
