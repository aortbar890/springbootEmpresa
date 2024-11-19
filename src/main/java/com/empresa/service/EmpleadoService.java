package com.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Empleado;
import com.empresa.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Obtener todos los empleados
    public List<Empleado> obtenerEmpleados() {
        return empleadoRepository.findAll();
    }

    // Buscar empleados por diferentes criterios
    public List<Empleado> buscarEmpleados(String dni, String nombre, String sexo, Integer categoria, Integer anyos) {
        if (dni != null && !dni.trim().isEmpty()) {
            return List.of(empleadoRepository.findByDniIgnoreCase(dni));
        }
        if (nombre != null && !nombre.trim().isEmpty()) {
            return empleadoRepository.findByNombreContainingIgnoreCase(nombre);
        }
        if (sexo != null) {
            return empleadoRepository.findBySexoIgnoreCase(sexo);
        }
        if (categoria != null) {
            return empleadoRepository.findByCategoria(categoria);
        }
        if (anyos != null) {
            return empleadoRepository.findByAnyos(anyos);
        }
        return empleadoRepository.findAll();
    }

    // Buscar empleado por DNI
    public Empleado buscarEmpleadosPorDni(String dni) {
        return empleadoRepository.findByDniIgnoreCase(dni);
    }

    // Guardar o actualizar un empleado
    public void guardarOActualizar(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

}
