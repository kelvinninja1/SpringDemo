package io.turntabl.springdemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void assNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentsByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudents(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("student with id "+ studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }
}
