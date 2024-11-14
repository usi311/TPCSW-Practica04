/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/Repository.java to edit this template
 */
package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author usiel
 */
public interface RepositoryEmpleados extends JpaRepository<Empleado, Long> {
    
}
