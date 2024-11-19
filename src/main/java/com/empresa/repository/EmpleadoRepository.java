package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.entity.Empleado;

@Repository
public interface  EmpleadoRepository extends JpaRepository<Empleado, String>{

	Empleado findByDniIgnoreCase(String dni);

	List<Empleado> findByNombreContainingIgnoreCase(String nombre);

	List<Empleado> findBySexoIgnoreCase(String sexo);

	List<Empleado> findByAnyos(Integer anyos);
	
    List<Empleado> findByCategoria(Integer categoria);

}
