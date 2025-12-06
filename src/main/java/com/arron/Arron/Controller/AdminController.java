package com.arron.Arron.Controller;

import com.arron.Arron.Model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/perfil")
    public String perfilAdmin(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null || !usuario.getTipoUsuario().equals("Administrador")) {
            return "redirect:/iniciarsesion";  // No tiene permiso
        }

        model.addAttribute("usuario", usuario);
        return "admin/perfil-admin"; // tu html
    }
}
