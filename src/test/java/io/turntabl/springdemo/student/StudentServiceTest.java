package io.turntabl.springdemo.student;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StudentService.class})
@ExtendWith(SpringExtension.class)
class StudentServiceTest {
    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @Test
    void testConstructor() {
        assertTrue((new StudentService(mock(StudentRepository.class))).getStudents().isEmpty());
    }

    @Test
    void testGetStudents() {
        ArrayList<Student> studentList = new ArrayList<Student>();
        when(this.studentRepository.findAll()).thenReturn(studentList);
        List<Student> actualStudents = this.studentService.getStudents();
        assertSame(studentList, actualStudents);
        assertTrue(actualStudents.isEmpty());
        verify(this.studentRepository).findAll();
    }

    @Test
    void testAssNewStudent() {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setAge(1);
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentRepository.findStudentsByEmail((String) any())).thenReturn(ofResult);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setAge(1);
        student1.setId(123L);
        student1.setName("Name");
        assertThrows(IllegalStateException.class, () -> this.studentService.assNewStudent(student1));
        verify(this.studentRepository).findStudentsByEmail((String) any());
    }

    @Test
    void testAssNewStudent2() {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setAge(1);
        student.setId(123L);
        student.setName("Name");
        when(this.studentRepository.save((Student) any())).thenReturn(student);
        when(this.studentRepository.findStudentsByEmail((String) any())).thenReturn(Optional.<Student>empty());

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setAge(1);
        student1.setId(123L);
        student1.setName("Name");
        this.studentService.assNewStudent(student1);
        verify(this.studentRepository).findStudentsByEmail((String) any());
        verify(this.studentRepository).save((Student) any());
        assertTrue(this.studentService.getStudents().isEmpty());
    }

    @Test
    void testDeleteStudents() {
        doNothing().when(this.studentRepository).deleteById((Long) any());
        when(this.studentRepository.existsById((Long) any())).thenReturn(true);
        this.studentService.deleteStudents(123L);
        verify(this.studentRepository).deleteById((Long) any());
        verify(this.studentRepository).existsById((Long) any());
        assertTrue(this.studentService.getStudents().isEmpty());
    }

    @Test
    void testDeleteStudents2() {
        doNothing().when(this.studentRepository).deleteById((Long) any());
        when(this.studentRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(IllegalStateException.class, () -> this.studentService.deleteStudents(123L));
        verify(this.studentRepository).existsById((Long) any());
    }

    @Test
    void testUpdateStudents() {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setAge(1);
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        this.studentService.updateStudents(123L, "Name", "jane.doe@example.org");
        verify(this.studentRepository).findById((Long) any());
        assertTrue(this.studentService.getStudents().isEmpty());
    }

    @Test
    void testUpdateStudents2() {
        Student student = new Student();
        student.setEmail(null);
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setAge(1);
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setAge(1);
        student1.setId(123L);
        student1.setName("Name");
        Optional<Student> ofResult1 = Optional.<Student>of(student1);
        when(this.studentRepository.findStudentsByEmail((String) any())).thenReturn(ofResult1);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class,
                () -> this.studentService.updateStudents(123L, "Name", "jane.doe@example.org"));
        verify(this.studentRepository).findById((Long) any());
        verify(this.studentRepository).findStudentsByEmail((String) any());
    }

    @Test
    void testUpdateStudents3() {
        when(this.studentRepository.findStudentsByEmail((String) any())).thenReturn(null);
        when(this.studentRepository.findById((Long) any())).thenReturn(Optional.<Student>empty());
        assertThrows(IllegalStateException.class,
                () -> this.studentService.updateStudents(123L, "Name", "jane.doe@example.org"));
        verify(this.studentRepository).findById((Long) any());
    }

    @Test
    void testUpdateStudents4() {
        Student student = new Student();
        student.setEmail(null);
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setAge(1);
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentRepository.findStudentsByEmail((String) any())).thenReturn(Optional.<Student>empty());
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        this.studentService.updateStudents(123L, "Name", "jane.doe@example.org");
        verify(this.studentRepository).findById((Long) any());
        verify(this.studentRepository).findStudentsByEmail((String) any());
        assertTrue(this.studentService.getStudents().isEmpty());
    }

    @Test
    void testUpdateStudents5() {
        Student student = new Student();
        student.setEmail(null);
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setAge(1);
        student.setId(123L);
        student.setName("Email Taken");
        Optional<Student> ofResult = Optional.<Student>of(student);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setAge(1);
        student1.setId(123L);
        student1.setName("Name");
        Optional<Student> ofResult1 = Optional.<Student>of(student1);
        when(this.studentRepository.findStudentsByEmail((String) any())).thenReturn(ofResult1);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class,
                () -> this.studentService.updateStudents(123L, "Name", "jane.doe@example.org"));
        verify(this.studentRepository).findById((Long) any());
        verify(this.studentRepository).findStudentsByEmail((String) any());
    }

    @Test
    void testUpdateStudents6() {
        Student student = new Student();
        student.setEmail(null);
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setAge(1);
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setAge(1);
        student1.setId(123L);
        student1.setName("Name");
        Optional<Student> ofResult1 = Optional.<Student>of(student1);
        when(this.studentRepository.findStudentsByEmail((String) any())).thenReturn(ofResult1);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class,
                () -> this.studentService.updateStudents(123L, null, "jane.doe@example.org"));
        verify(this.studentRepository).findById((Long) any());
        verify(this.studentRepository).findStudentsByEmail((String) any());
    }

    @Test
    void testUpdateStudents7() {
        Student student = new Student();
        student.setEmail(null);
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setAge(1);
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setAge(1);
        student1.setId(123L);
        student1.setName("Name");
        Optional<Student> ofResult1 = Optional.<Student>of(student1);
        when(this.studentRepository.findStudentsByEmail((String) any())).thenReturn(ofResult1);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(IllegalStateException.class,
                () -> this.studentService.updateStudents(123L, "", "jane.doe@example.org"));
        verify(this.studentRepository).findById((Long) any());
        verify(this.studentRepository).findStudentsByEmail((String) any());
    }

    @Test
    void testUpdateStudents8() {
        Student student = new Student();
        student.setEmail(null);
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setAge(1);
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setAge(1);
        student1.setId(123L);
        student1.setName("Name");
        Optional<Student> ofResult1 = Optional.<Student>of(student1);
        when(this.studentRepository.findStudentsByEmail((String) any())).thenReturn(ofResult1);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        this.studentService.updateStudents(123L, "Name", null);
        verify(this.studentRepository).findById((Long) any());
        assertTrue(this.studentService.getStudents().isEmpty());
    }

    @Test
    void testUpdateStudents9() {
        Student student = new Student();
        student.setEmail(null);
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setAge(1);
        student.setId(123L);
        student.setName("Name");
        Optional<Student> ofResult = Optional.<Student>of(student);

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setAge(1);
        student1.setId(123L);
        student1.setName("Name");
        Optional<Student> ofResult1 = Optional.<Student>of(student1);
        when(this.studentRepository.findStudentsByEmail((String) any())).thenReturn(ofResult1);
        when(this.studentRepository.findById((Long) any())).thenReturn(ofResult);
        this.studentService.updateStudents(123L, "Name", "");
        verify(this.studentRepository).findById((Long) any());
        assertTrue(this.studentService.getStudents().isEmpty());
    }
}

