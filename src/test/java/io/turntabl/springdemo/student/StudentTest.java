package io.turntabl.springdemo.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class StudentTest {
    @Test
    void testConstructor() {
        Student actualStudent = new Student();
        actualStudent.setAge(1);
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualStudent.setDob(ofEpochDayResult);
        actualStudent.setEmail("jane.doe@example.org");
        actualStudent.setId(123L);
        actualStudent.setName("Name");
        assertEquals(1, actualStudent.getAge().intValue());
        assertSame(ofEpochDayResult, actualStudent.getDob());
        assertEquals("jane.doe@example.org", actualStudent.getEmail());
        assertEquals(123L, actualStudent.getId().longValue());
        assertEquals("Name", actualStudent.getName());
        assertEquals("Student{id=123, name='Name', email='jane.doe@example.org', dob=1970-01-02, age=1}",
                actualStudent.toString());
    }

    @Test
    void testConstructor2() {
        Student actualStudent = new Student(123L, "Name", "jane.doe@example.org", LocalDate.ofEpochDay(1L), 1);
        actualStudent.setAge(1);
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualStudent.setDob(ofEpochDayResult);
        actualStudent.setEmail("jane.doe@example.org");
        actualStudent.setId(123L);
        actualStudent.setName("Name");
        assertEquals(1, actualStudent.getAge().intValue());
        assertSame(ofEpochDayResult, actualStudent.getDob());
        assertEquals("jane.doe@example.org", actualStudent.getEmail());
        assertEquals(123L, actualStudent.getId().longValue());
        assertEquals("Name", actualStudent.getName());
        assertEquals("Student{id=123, name='Name', email='jane.doe@example.org', dob=1970-01-02, age=1}",
                actualStudent.toString());
    }

    @Test
    void testConstructor3() {
        Student actualStudent = new Student("Name", "jane.doe@example.org", LocalDate.ofEpochDay(1L), 1);
        actualStudent.setAge(1);
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualStudent.setDob(ofEpochDayResult);
        actualStudent.setEmail("jane.doe@example.org");
        actualStudent.setId(123L);
        actualStudent.setName("Name");
        assertEquals(1, actualStudent.getAge().intValue());
        assertSame(ofEpochDayResult, actualStudent.getDob());
        assertEquals("jane.doe@example.org", actualStudent.getEmail());
        assertEquals(123L, actualStudent.getId().longValue());
        assertEquals("Name", actualStudent.getName());
        assertEquals("Student{id=123, name='Name', email='jane.doe@example.org', dob=1970-01-02, age=1}",
                actualStudent.toString());
    }
}

