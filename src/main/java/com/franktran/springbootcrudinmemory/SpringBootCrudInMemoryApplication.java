package com.franktran.springbootcrudinmemory;

import com.franktran.springbootcrudinmemory.student.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootCrudInMemoryApplication {

  public static List<Student> students;

  public static void main(String[] args) {
    SpringApplication.run(SpringBootCrudInMemoryApplication.class, args);
  }

  @Bean
  public CommandLineRunner runner() {
    return args -> {
      students = new ArrayList<>();
      students.add(new Student(1, "Frank", "frank@gmail.com"));
      students.add(new Student(2, "Henry", "henry@gmail.com"));
      students.add(new Student(3, "Bean", "bean@gmail.com"));
    };
  }

}
