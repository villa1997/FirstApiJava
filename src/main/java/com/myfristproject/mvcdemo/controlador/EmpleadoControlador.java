package com.myfristproject.mvcdemo.controlador;

import com.myfristproject.mvcdemo.exepciones.ResourcesNotFoundException;
import com.myfristproject.mvcdemo.modelo.Empleado;
import com.myfristproject.mvcdemo.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "https://localhost:4200")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoRepositorio repositorio;

    // Metodo para lista todos los empleados
    @GetMapping("/empleados")
    public List<Empleado> ListaTodosLosEmpleados(){
        return repositorio.findAll();
    }

    // Metodo para insertar un nuevo empleado
    @PostMapping("/empleados")
    public Empleado GuardarEmpleado(@RequestBody Empleado empleado){
        return repositorio.save(empleado);
    }

    // Metodo para buscar un empleado por su id
    @GetMapping("/empleados/{idEmpleado}")
    public ResponseEntity<Empleado> ObtenerEmpleado(@PathVariable Long idEmpleado){
        Empleado empleado = repositorio.findById(idEmpleado)
                .orElseThrow(() -> new ResourcesNotFoundException("No existe el empleado con el id: " + idEmpleado));

        return ResponseEntity.ok(empleado);

    }

    @PutMapping("/empleados/{idEmpleado}")
    public ResponseEntity<Empleado> ActualizarEmpleado(@PathVariable Long idEmpleado, @RequestBody Empleado empleadoBody){
        Empleado empleado = repositorio.findById(idEmpleado)
                .orElseThrow(() -> new ResourcesNotFoundException("No existe el empleado con el id: " + idEmpleado));

        empleado.setNombre(empleadoBody.getNombre());
        empleado.setApellido(empleadoBody.getApellido());
        empleado.setEmail(empleadoBody.getEmail());

        Empleado empleadoActualizado = repositorio.save(empleado);
        return ResponseEntity.ok(empleadoActualizado);

    }

    @DeleteMapping("/empleados/{idEmpleado}")
    public ResponseEntity<Map<String, Boolean>> EliminarEmpleado(@PathVariable Long idEmpleado){
        Empleado empleado = repositorio.findById(idEmpleado)
                                        .orElseThrow(() -> new ResourcesNotFoundException("No existe el empleado con id: "+  idEmpleado));

        repositorio.delete(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
