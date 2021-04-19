package com.franktran.springbootcrudinmemory.student;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final BootStrapData bootStrapData;

    public StudentController(BootStrapData bootStrapData) {
        this.bootStrapData = bootStrapData;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return bootStrapData.getStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return bootStrapData.getStudents().stream()
            .filter(student -> student.getId() == id)
            .parallel()
            .findAny()
            .orElse(null);
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        Student lastStudent = bootStrapData.getStudents().get(bootStrapData.getStudents().size() - 1);
        int id = lastStudent.getId() + 1;
        student.setId(id);
        bootStrapData.getStudents().add(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student existStudent = getStudentById(id);
        if (Objects.isNull(existStudent)) {
            existStudent.setName(student.getName());
            existStudent.setEmail(student.getEmail());
            int index = bootStrapData.getStudents().indexOf(existStudent);
            bootStrapData.getStudents().set(index, existStudent);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        Student existStudent = getStudentById(id);
        if (Objects.nonNull(existStudent)) {
            bootStrapData.getStudents().remove(existStudent);
        }
    }
}
