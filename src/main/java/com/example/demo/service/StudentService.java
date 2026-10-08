package com.example.demo.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Cacheable(value = "students", key = "#id")
    public String getStudent(Long id) {

        System.out.println("Fetching student from database...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "Student-" + id;
    }
    @CacheEvict(value = "students", key = "#id")
    public String deleteStudent(Long id) {

        System.out.println("Deleting student from database...");

        return "Student-" + id + " deleted";
    }
    @CachePut(value = "students", key = "#id")
    public String updateStudent(Long id) {

        System.out.println("Updating student...");

        return "Updated-Student-" + id;
    }

}