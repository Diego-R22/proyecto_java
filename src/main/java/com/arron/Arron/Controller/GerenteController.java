package com.arron.Arron.Controller;

import com.arron.Arron.Model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/gerente")
public class GerenteController {

    @GetMapping("/perfil")
    public String perfilGerente(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // ✅ Validación correcta de sesión y rol usando ENUM
        if (usuario == null || usuario.getTipoUsuario() != Usuario.TipoUsuario.Gerente) {
            return "redirect:/iniciarsesion";
        }

        model.addAttribute("usuario", usuario);
        return "gerente/perfil";
    }
}
