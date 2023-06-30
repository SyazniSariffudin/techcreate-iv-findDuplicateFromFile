package org.example;

import com.google.common.collect.Lists;
import org.example.dto.Teacher;
import org.example.processor.FileProcessor;
import org.example.processor.TeacherFileProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class TeacherFileProcessorTest {
    FileProcessor<Teacher> teacherFileProcessor;

    @BeforeEach
    void setUp() {
        teacherFileProcessor = new TeacherFileProcessor();
    }

    @Test
    void givenPerson_whenNameMatched_thenDuplicateFound() {
        Teacher student = new Teacher("001", "John");
        Teacher cachedTeacher = new Teacher("002", "John");

        Optional<Teacher> duplicate = teacherFileProcessor.findDuplicate(student, Lists.newArrayList(cachedTeacher));
        Assertions.assertTrue(duplicate.isPresent());
        Assertions.assertEquals(cachedTeacher.getId(), duplicate.get().getId());
    }

    @Test
    void givenPerson_whenNoNameMatch_thenNoDuplicateFound() {
        Teacher student = new Teacher("001", "John");
        Teacher cachedTeacher = new Teacher("002", "John");

        Optional<Teacher> duplicate = teacherFileProcessor.findDuplicate(student, Lists.newArrayList(cachedTeacher));
        Assertions.assertTrue(duplicate.isPresent());
        Assertions.assertEquals(cachedTeacher.getId(), duplicate.get().getId());
    }

}
