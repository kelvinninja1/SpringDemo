package io.turntabl.springdemo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args ->{
            Student kelvin = new Student(
                    "Kelvin",
                    "man@ikelvin.co",
                    LocalDate.of(1998, Month.JULY, 2),
                    23
            );

            Student audrey = new Student(
                    "Audrey",
                    "bestie@turntabl.io",
                    LocalDate.of(1995, Month.JULY, 2),
                    23
            );

            repository.saveAll(
                    List.of(kelvin, audrey)
            );
        };
    }
}
