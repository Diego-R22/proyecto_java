package com.arron.Arron.Controller;

import com.arron.Arron.Model.Usuario;
import com.arron.Arron.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // ----------------------------------------
    // LISTAR
    // ----------------------------------------
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuarios/listar"; // templates/usuarios/listar.html
    }

    // ----------------------------------------
    // FORMULARIO CREAR
    // ----------------------------------------
    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {

        model.addAttribute("usuario", new Usuario());
        return "usuarios/crear"; // templates/usuarios/crear.html
    }

    // ----------------------------------------
    // GUARDAR (POST)
    // ----------------------------------------
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {

        usuarioService.guardarUsuario(usuario);
        return "redirect:/usuarios";
    }

    // ----------------------------------------
    // EDITAR
    // ----------------------------------------
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {

        Usuario usuario = usuarioService.obtenerPorId(id);
        model.addAttribute("usuario", usuario);

        return "usuarios/editar"; // templates/usuarios/editar.html
    }

    // ----------------------------------------
    // ACTUALIZAR (POST)
    // ----------------------------------------
    @PostMapping("/actualizar")
    public String actualizarUsuario(@ModelAttribute Usuario usuario) {

        usuarioService.actualizarUsuario(usuario);
        return "redirect:/usuarios";
    }

    // ----------------------------------------
    // ELIMINAR
    // ----------------------------------------
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {

        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
}
