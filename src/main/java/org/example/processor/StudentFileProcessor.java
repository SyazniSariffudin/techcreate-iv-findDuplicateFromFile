package org.example.processor;

import org.example.dto.Student;

public class StudentFileProcessor extends FileProcessor<Student> {

    @Override
    public Student fileToObj(String[] rows) {
        return new Student(rows[0], rows[2], rows[3], rows[4]);
    }

    @Override
    public String getFileName() {
        return "student.csv";
    }
}
