package org.example;

import org.example.dto.Student;
import org.example.dto.Teacher;
import org.example.processor.FileProcessor;
import org.example.processor.StudentFileProcessor;
import org.example.processor.TeacherFileProcessor;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        FileProcessor<Student> studentFileProcessor = new StudentFileProcessor();
        studentFileProcessor.readFileAndFindDuplicate();

        FileProcessor<Teacher> teacherFileProcessor = new TeacherFileProcessor();
        teacherFileProcessor.readFileAndFindDuplicate();
    }
}