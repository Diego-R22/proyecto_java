package com.arron.Arron.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VistaController {

    @GetMapping("/{vista}")
    public String cargarVista(@PathVariable String vista) {
        return vista; // carga cualquier .html dentro de /templates
    }
}