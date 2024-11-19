package com.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.empresa.entity.Empleado;
import com.empresa.service.EmpleadoService;

@Controller
@RequestMapping("/empresa/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/listar")
    public String listarEmpleados(Model model) {
        List<Empleado> empleados = empleadoService.obtenerEmpleados();
        model.addAttribute("empleados", empleados);
        return "lista-empleados";
    }

    @GetMapping("/buscar")
    public String buscarEmpleado() {
        return "buscar-empleado";
    }

    @GetMapping("/resultados")
    public String resultadosBusqueda(@RequestParam(required = false) String dni,
                                     @RequestParam(required = false) String nombre,
                                     @RequestParam(required = false) String sexo,
                                     @RequestParam(required = false) Integer categoria,
                                     @RequestParam(required = false) Integer anyos,
                                     Model model) {
        List<Empleado> empleados = empleadoService.buscarEmpleados(dni, nombre, sexo, categoria, anyos);
        model.addAttribute("empleados", empleados);
        return "resultados-busqueda";
    }

    @GetMapping("/modificar/{dni}")
    public String mostrarFormularioModificacion(@PathVariable String dni, Model model) {
        Empleado empleado = empleadoService.buscarEmpleadosPorDni(dni);
        if (empleado == null) {
            model.addAttribute("error", "Empleado no encontrado");
            return "error";
        }
        model.addAttribute("empleado", empleado);
        return "modificar-empleado";
    }

    @PostMapping("/guardar")
    public String guardarCambios(@ModelAttribute Empleado empleado) {
        empleadoService.guardarOActualizar(empleado);
        return "redirect:/empresa/empleados/listar"; 
    }
    


}
