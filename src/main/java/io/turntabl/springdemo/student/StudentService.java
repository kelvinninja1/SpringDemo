package io.turntabl.springdemo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    @Transactional
    public void updateStudents(Long studentId, String name, String email) {
       Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
               "student with id " + studentId + " does not exist"
       ));

       if (name != null && name.length() > 0 && !Objects.equals(name, student.getName())){
           student.setName(name);
       }

       if (email != null && email.length() > 0 && !Objects.equals(email, student.getEmail())){
           Optional<Student> studentsOptional = studentRepository.findStudentsByEmail(email);
           if (studentsOptional.isPresent()){
               throw new IllegalStateException("Email Taken");
           }
           student.setEmail(email);
       }

    }
}
