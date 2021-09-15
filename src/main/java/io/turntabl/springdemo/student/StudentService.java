package io.turntabl.springdemo.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
    public List<Student> getStudents(){
        return List.of(new Student(
                1L,
                "Kelvin",
                "man@ikelvin.co",
                LocalDate.of(1998, Month.JULY, 2),
                23));
    }
}
