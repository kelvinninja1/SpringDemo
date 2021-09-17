package io.turntabl.springdemo.student;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StudentController.class})
@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @Autowired
    private StudentController studentController;

    @MockBean
    private StudentService studentService;

    @Test
    void testConstructor() {
        StudentService studentService = new StudentService(mock(StudentRepository.class));
        assertTrue((new StudentController(studentService)).hello().isEmpty());
        assertTrue(studentService.getStudents().isEmpty());
    }

    @Test
    void testConstructor2() {
        StudentService studentService = new StudentService(mock(StudentRepository.class));
        assertTrue((new StudentController(studentService)).hello().isEmpty());
        assertTrue(studentService.getStudents().isEmpty());
    }

    @Test
    void testConstructor3() {
        StudentService studentService = new StudentService(mock(StudentRepository.class));
        assertTrue((new StudentController(studentService)).hello().isEmpty());
        assertTrue(studentService.getStudents().isEmpty());
    }

    @Test
    void testRegisterNewStudent() {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setDob(LocalDate.ofEpochDay(1L));
        student.setAge(1);
        student.setId(123L);
        student.setName("Name");
        StudentRepository studentRepository = mock(StudentRepository.class);
        when(studentRepository.save((Student) any())).thenReturn(student);
        when(studentRepository.findStudentsByEmail((String) any())).thenReturn(Optional.<Student>empty());
        StudentController studentController = new StudentController(new StudentService(studentRepository));

        Student student1 = new Student();
        student1.setEmail("jane.doe@example.org");
        student1.setDob(LocalDate.ofEpochDay(1L));
        student1.setAge(1);
        student1.setId(123L);
        student1.setName("Name");
        studentController.registerNewStudent(student1);
        verify(studentRepository).findStudentsByEmail((String) any());
        verify(studentRepository).save((Student) any());
        assertTrue(studentController.hello().isEmpty());
    }

    @Test
    void testDeleteStudent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/{studentId}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testHello() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testHello2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testHello3() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testUpdateStudent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/{studentId}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.studentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

