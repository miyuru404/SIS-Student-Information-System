package com.demo.demospring;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean addStudent(Student student) {
        if (!studentRepository.existsById(student.getIdNumber())) {
            studentRepository.save(student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(String id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByNameIgnoreCase(name);
    }

    public boolean updateStudent(String id, StudentDTO studentDTO) {
        Student student = getStudentById(id);
        if (student != null) {
            student.setName(studentDTO.getName());
            student.setAge(studentDTO.getAge());
            studentRepository.save(student);
            return true;
        }
        return false;
    }
}
