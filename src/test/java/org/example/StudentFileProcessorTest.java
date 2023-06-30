package org.example;

import com.google.common.collect.Lists;
import org.example.dto.Student;
import org.example.processor.FileProcessor;
import org.example.processor.StudentFileProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class StudentFileProcessorTest {

    FileProcessor<Student> personFileProcessor;

    @BeforeEach
    void setUp() {
        personFileProcessor = new StudentFileProcessor();
    }

    @Test
    void givenPerson_whenCacheMatchWithIdTypeNumberAndDob_thenDuplicateFound() {
        Student student = new Student("001", "IC", "007", "920/02/28");
        Student cachedPerson = new Student("002", "IC", "007", "920/02/28");

        Optional<Student> duplicate = personFileProcessor.findDuplicate(student, Lists.newArrayList(cachedPerson));
        Assertions.assertTrue(duplicate.isPresent());
        Assertions.assertEquals(cachedPerson.getId(), duplicate.get().getId());
    }

    @Test
    void givenPerson_whenCacheMatchWithIdAndDob_thenDuplicateFound() {
        Student student = new Student("001", "IC", "007", "920/02/28");
        Student cachedPerson = new Student("001", "Passport", "XXXX", "920/02/28");

        Optional<Student> duplicate = personFileProcessor.findDuplicate(student, Lists.newArrayList(cachedPerson));
        Assertions.assertTrue(duplicate.isPresent());
        Assertions.assertEquals(cachedPerson.getId(), duplicate.get().getId());
    }

    @Test
    void givenPerson_whenAllFieldsMatched_thenDuplicateFound() {
        Student student = new Student("001", "IC", "007", "920/02/28");
        Student cachedPerson = new Student("001", "IC", "007", "920/02/28");

        Optional<Student> duplicate = personFileProcessor.findDuplicate(student, Lists.newArrayList(cachedPerson));
        Assertions.assertTrue(duplicate.isPresent());
        Assertions.assertEquals(cachedPerson.getId(), duplicate.get().getId());
    }

    @Test
    void givenPerson_whenNoFieldsMatched_thenNoDuplicateFound() {
        Student student = new Student("001", "IC", "007", "920/02/28");
        Student cachedPerson = new Student("002", "Passport", "XXXX", "1992/02/28");

        Optional<Student> duplicate = personFileProcessor.findDuplicate(student, Lists.newArrayList(cachedPerson));
        Assertions.assertTrue(duplicate.isEmpty());
    }
}