package com.pcprime.pcprime.controller;

import com.pcprime.pcprime.model.CategoriaModel;
import com.pcprime.pcprime.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listarCategorias(Model model) {
        List<CategoriaModel> categorias = categoriaService.listarTodos();
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriaNueva", new CategoriaModel());
        return "admin/listarcategorias";
    }

    @PostMapping("/crear")
    public String crearCategoria(@ModelAttribute("categoriaNueva") CategoriaModel categoria,
                                RedirectAttributes redirectAttributes) {
        try {
            categoriaService.crear(categoria);
            redirectAttributes.addFlashAttribute("success", "Categoría creada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear categoría: " + e.getMessage());
        }
        return "redirect:/admin/categorias";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCategoria(@PathVariable Integer id,
                                     @ModelAttribute("categoriaNueva") CategoriaModel categoria,
                                     RedirectAttributes redirectAttributes) {
        try {
            categoriaService.actualizar(id, categoria);
            redirectAttributes.addFlashAttribute("success", "Categoría actualizada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar categoría: " + e.getMessage());
        }
        return "redirect:/admin/categorias";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Integer id,
                                   RedirectAttributes redirectAttributes) {
        try {
            categoriaService.eliminar(id);
            redirectAttributes.addFlashAttribute("success", "Categoría eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar categoría: " + e.getMessage());
        }
        return "redirect:/admin/categorias";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getCategoriaJson(@PathVariable Integer id) {
        try {
            CategoriaModel categoria = categoriaService.obtenerPorId(id)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}