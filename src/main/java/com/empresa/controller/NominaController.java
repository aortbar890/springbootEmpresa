package com.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.empresa.service.NominaService;

@Controller
@RequestMapping("/empresa/nominas")
public class NominaController {

    @Autowired
    private NominaService nominaService;

    @GetMapping("/salario")
    public String verSalarioFormulario() {
        return "buscar-salario";
    }

    @PostMapping("/salario")
    public String verSalario(@RequestParam String dni, Model model) {
        try {
            double salario = nominaService.obtenerSalario(dni);
            model.addAttribute("salario", salario);
        } catch (Exception e) {
            model.addAttribute("error", "Empleado no encontrado o error en el cálculo.");
        }
        return "buscar-salario";
    }
}
