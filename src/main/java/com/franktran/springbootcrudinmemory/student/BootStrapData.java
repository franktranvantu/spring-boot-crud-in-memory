package com.franktran.springbootcrudinmemory.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {

    public static List<Student> students;

    @Override
    public void run(String... args) throws Exception {
        students = new ArrayList<>();
        students.add(new Student(1, "Frank", "frank@gmail.com"));
        students.add(new Student(2, "Henry", "henry@gmail.com"));
        students.add(new Student(3, "Bean", "bean@gmail.com"));
    }

    public List<Student> getStudents() {
        return students;
    }
}
