
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
    private RepositoryEmpleados repositoryEmpleados;
    
    @GetMapping()
    public List<Empleado> list() {
        return repositoryEmpleados.findAll();
    }
    
    @GetMapping("/{id}")
    public Object get(@PathVariable String id) {
       Optional<Empleado> res= repositoryEmpleados.findById(Long.valueOf(id));
        return res.get();
    }
    
      @PutMapping("/{id}")
    public ResponseEntity<Empleado> put(@PathVariable Long id, @RequestBody Empleado input) {
        Optional<Empleado> empleado = repositoryEmpleados.findById(id);
        if(!empleado.isPresent()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Empleado emp = empleado.get();
        emp.setNombre(input.getNombre());
        emp.setTelefono(input.getTelefono());
        emp.setDireccion(input.getDireccion());
        emp.setDepto(input.getDepto());
        return ResponseEntity.ok(repositoryEmpleados.save(input));
    }
    
     @PostMapping
    public ResponseEntity<Empleado> post(@RequestBody Empleado empleado) {
        Empleado empRes = repositoryEmpleados.save(empleado);
        return new ResponseEntity<Empleado>(empRes,HttpStatus.CREATED);
    }

  @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Empleado> existingEmpleado = repositoryEmpleados.findById(Long.valueOf(id));
        if (existingEmpleado.isPresent()) {
            repositoryEmpleados.deleteById(Long.valueOf(id));
            return new ResponseEntity<>("Empleado eliminado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empleado no encontrado", HttpStatus.NOT_FOUND);
        }
    }
    
   
}