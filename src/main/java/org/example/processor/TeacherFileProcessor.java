package org.example.processor;

import org.example.dto.Teacher;

public class TeacherFileProcessor extends FileProcessor<Teacher> {

    @Override
    public Teacher fileToObj(String[] rows) {
        return new Teacher(rows[0], rows[1]);
    }

    @Override
    public String getFileName() {
        return "teacher.csv";
    }

    @Override
    public String getFileDelimiter() {
        return "-";
    }
}
