

package com.demo.demospring;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        boolean status = studentService.addStudent(student);
        if (status) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Student already exists");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        boolean status = studentService.removeStudent(id);
        if (status) {
            return ResponseEntity.status(HttpStatus.OK).body("Student deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student is not found");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable String id , @RequestBody StudentDTO student) {
        boolean status = studentService.updateStudent(id, student);

        if (status) {
            return ResponseEntity.status(HttpStatus.OK).body("Student updated successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student is not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewStudent(@PathVariable String id) {
        Student s = studentService.getStudentById(id);
        if (s == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student is not found");
        }
        return ResponseEntity.ok(s);
    }

}
