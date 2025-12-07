package com.arron.Arron.Controller;

import com.arron.Arron.Model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/supbodega")
public class SupBodegaController {

    @GetMapping("/perfil")
    public String perfilSupBodega(HttpSession session, Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // ✅ Validación correcta de sesión y rol
        if (usuario == null || usuario.getTipoUsuario() != Usuario.TipoUsuario.Sup_bodega) {
            return "redirect:/iniciarsesion";
        }

        model.addAttribute("usuario", usuario);
        return "supbodega/perfil";
    }
}
