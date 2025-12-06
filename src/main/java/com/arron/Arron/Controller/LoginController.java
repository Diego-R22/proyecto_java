package com.arron.Arron.Controller;

import com.arron.Arron.Model.Usuario;
import com.arron.Arron.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/iniciarsesion")
    public String mostrarLogin(Model model) {
        return "iniciarsesion";
    }

    @PostMapping("/login")
    public String validarLogin(
            @RequestParam("numeroDocumento") Long numeroDocumento,
            @RequestParam("contrasena") String contrasena,
            Model model,
            HttpSession session) {

        // Buscar usuario por número de documento
        Usuario acceso = usuarioService.buscarPorNumeroDocumento(numeroDocumento);

        if (acceso != null && acceso.getContrasena().equals(contrasena)) {

            // Guardar usuario en sesión
            session.setAttribute("usuario", acceso);

            // Redirigir según el tipo
            Usuario.TipoUsuario tipo = acceso.getTipoUsuario();

            if (tipo == Usuario.TipoUsuario.Administrador) {
                return "redirect:/admin/perfil";
            }

            if (tipo == Usuario.TipoUsuario.Gerente) {
                return "redirect:/gerente/perfil";
            }

            if (tipo == Usuario.TipoUsuario.Sup_bodega) {
                return "redirect:/supbodega/perfil";
            }

            return "redirect:/perfil"; // fallback
        }

        model.addAttribute("error", "Documento o contraseña incorrectos");
        return "iniciarsesion";
    }
}



