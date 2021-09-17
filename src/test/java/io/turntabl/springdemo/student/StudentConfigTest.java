package io.turntabl.springdemo.student;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class StudentConfigTest {
    @Test
    void testCommandLineRunner() throws Exception {
        StudentConfig studentConfig = new StudentConfig();
        StudentRepository studentRepository = mock(StudentRepository.class);
        when(studentRepository.saveAll((Iterable<Student>) any())).thenReturn(new ArrayList<Student>());
        studentConfig.commandLineRunner(studentRepository).run("foo");
        verify(studentRepository).saveAll((Iterable<Student>) any());
    }
}

