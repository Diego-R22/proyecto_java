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

    // ✅ GET → muestra el formulario
    @GetMapping("/iniciarsesion")
    public String mostrarLogin() {
        return "iniciarsesion";
    }

    // ✅ POST → procesa el login
    @PostMapping("/iniciarsesion")
    public String validarLogin(
            @RequestParam Long numeroDocumento,
            @RequestParam String contrasena,
            HttpSession session,
            Model model) {

        // ✅ MENSAJE DE PRUEBA
        System.out.println("✅ LLEGÓ AL POST DE LOGIN");

        Usuario acceso = usuarioService.buscarPorNumeroDocumento(numeroDocumento);

        if (acceso == null || !acceso.getContrasena().equals(contrasena)) {
            model.addAttribute("error", "Documento o contraseña incorrectos");
            return "iniciarsesion";
        }

        session.setAttribute("usuario", acceso);

        // REDIRECCIONES SEGÚN TIPO DE USUARIO
        if (acceso.getTipoUsuario() == Usuario.TipoUsuario.Administrador) {
            return "redirect:/admin/perfil";
        } else if (acceso.getTipoUsuario() == Usuario.TipoUsuario.Sup_bodega) {
            return "redirect:/supbodega/perfil"; // <-- nueva ruta para sub_bodega
        } else if (acceso.getTipoUsuario() == Usuario.TipoUsuario.Gerente) {
            return "redirect:/gerente/perfil";
        }

        // Por defecto
        return "redirect:/";
    }
}
