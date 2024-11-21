package com.example.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/departamentos")
public class ControllerDepartamentos {
    
    @Autowired
    private RepositoryDepartamentos repositoryDepartamentos;
    
    @GetMapping()
    public List<Departamento> list() {
        return (List<Departamento>) repositoryDepartamentos.findAll();
    }
    
    @GetMapping("/{id}")
    public Object get(@PathVariable String id){
        Optional<Departamento> res = repositoryDepartamentos.findById(Long.valueOf(id));
        return res.get();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Departamento> put(@PathVariable String id, @RequestBody Departamento input){
            Optional<Departamento> res = repositoryDepartamentos.findById(Long.valueOf(id));
        if (res.isPresent()) {
            Departamento departamentoExistente = res.get();
            departamentoExistente.setNombre(input.getNombre());
            Departamento updateDepartamento = repositoryDepartamentos.save(departamentoExistente);
            return new ResponseEntity<>(updateDepartamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping
    public ResponseEntity<Departamento> post(@RequestBody Departamento departamento){
        Departamento empRes = repositoryDepartamentos.save(departamento);
        return new ResponseEntity<Departamento>(empRes,HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<Departamento> res = repositoryDepartamentos.findById(Long.valueOf(id));
        if (res.isPresent()) {
            repositoryDepartamentos.deleteById(Long.valueOf(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}