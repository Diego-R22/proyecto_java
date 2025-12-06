package com.arron.Arron.Controller;

import com.arron.Arron.Model.Usuario;
import com.arron.Arron.Service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroClienteController {

    private final UsuarioService usuarioService;

    public RegistroClienteController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registro")
    public String registrarCliente(
            @RequestParam Long numeroDocumento,
            @RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String contrasena,
            @RequestParam String direccion,
            @RequestParam String telefono,
            Model model
    ) {

        // 1️⃣ validar que no esté repetido
        if (usuarioService.existePorDocumento(numeroDocumento)) {
            model.addAttribute("error", "El usuario ya existe");
            return "registro";
        }

        // 2️⃣ crear usuario
        Usuario u = new Usuario();
        u.setNumeroDocumento(numeroDocumento);
        u.setNombre(nombre);
        u.setCorreo(correo);
        u.setContrasena(contrasena); // por ahora en texto plano
        u.setDireccion(direccion);
        u.setTelefono(telefono);

        // 3️⃣ valores AUTOMÁTICOS
        u.setTipoUsuario(Usuario.TipoUsuario.Cliente);
        u.setEstado(Usuario.Estado.A);

        // 4️⃣ guardar en BD
        usuarioService.guardarUsuario(u);

        // 5️⃣ volver al login
        return "redirect:/iniciarsesion";
    }
}
