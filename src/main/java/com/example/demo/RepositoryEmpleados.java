
package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryEmpleados extends JpaRepository<Empleado, Long> {
    
}
