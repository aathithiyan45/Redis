package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private final Map<Long, Student> students = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Student createStudent(Student student) {
        Long id = idGenerator.incrementAndGet();
        student.setId(id);
        students.put(id, student);
        return student;
    }

    @Cacheable(value = "students", key = "#id")
    public Student getStudent(Long id) {
        Student student = students.get(id);

        if (student == null) {
            throw new IllegalArgumentException("Student not found: " + id);
        }

        return student;
    }

    @CachePut(value = "students", key = "#id")
    public Student updateStudent(Long id, Student updatedStudent) {
        if (!students.containsKey(id)) {
            throw new IllegalArgumentException("Student not found: " + id);
        }

        updatedStudent.setId(id);
        students.put(id, updatedStudent);
        return updatedStudent;
    }

    @CacheEvict(value = "students", key = "#id")
    public void deleteStudent(Long id) {
        if (students.remove(id) == null) {
            throw new IllegalArgumentException("Student not found: " + id);
        }
    }
}