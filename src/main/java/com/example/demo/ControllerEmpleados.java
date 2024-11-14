
package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/empleados")
public class ControllerEmpleados {
    @Autowired
    private RepositoryEmpleados RepositoryEmpleados;
    
    @GetMapping()
    public List<Empleado> list() {
        return RepositoryEmpleados.findAll();
    }
    
    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
       Optional<Empleado> res= RepositoryEmpleados.findById(Long.valueOf(id));
        return res.get();
    }
    
      @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Empleado empleado) {
        Optional<Empleado> existingEmpleado = RepositoryEmpleados.findById(Long.valueOf(id));
        if (existingEmpleado.isPresent()) {
            Empleado emp = existingEmpleado.get();
            emp.setNombre(empleado.getNombre());
            emp.setDireccion(empleado.getDireccion());
            emp.setTelefono(empleado.getTelefono());
            emp.setDepto(empleado.getDepto());
            Empleado updatedEmpleado = RepositoryEmpleados.save(emp);
            return new ResponseEntity<>(updatedEmpleado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Empleado empleado) {
        Empleado empRes= RepositoryEmpleados.save(empleado);
        return new ResponseEntity<Empleado>(empRes, HttpStatus.CREATED);
    }

  @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Empleado> existingEmpleado = RepositoryEmpleados.findById(Long.valueOf(id));
        if (existingEmpleado.isPresent()) {
            RepositoryEmpleados.deleteById(Long.valueOf(id));
            return new ResponseEntity<>("Empleado eliminado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    
}