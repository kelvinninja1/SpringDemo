package io.turntabl.springdemo;

import io.turntabl.springdemo.student.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@RestController
public class SpringDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    @GetMapping
    public List<Student> hello(){
        return List.of(new Student(
                1L,
                "Kelvin",
                "man@ikelvin.co",
                LocalDate.of(1998, Month.JULY, 2),
                23));
    }

}
