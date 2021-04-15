package com.franktran.springbootcrudinmemory.student;

import com.franktran.springbootcrudinmemory.SpringBootCrudInMemoryApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/students")
public class StudentController {

    private List<Student> students = SpringBootCrudInMemoryApplication.students;

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return students.stream()
            .filter(student -> student.getId() == id)
            .parallel()
            .findAny()
            .orElse(null);
    }

    @PostMapping
    public void createStudent(@RequestBody Student student) {
        Student lastStudent = students.get(students.size() - 1);
        int id = lastStudent.getId() + 1;
        student.setId(id);
        students.add(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        Student existStudent = getStudentById(id);
        if (Objects.isNull(existStudent)) {
            existStudent.setName(student.getName());
            existStudent.setEmail(student.getEmail());
            int index = students.indexOf(existStudent);
            students.set(index, existStudent);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        Student existStudent = getStudentById(id);
        if (Objects.nonNull(existStudent)) {
            students.remove(existStudent);
        }
    }
}
