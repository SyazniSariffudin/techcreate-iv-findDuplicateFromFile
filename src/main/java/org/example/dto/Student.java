package org.example.dto;

import java.util.Objects;

public class Student extends Person {
    private String idType;
    private String idNumber;
    private String dob;

    public Student(String id, String idType, String idNumber, String dob) {
        super(id);
        this.idType = idType;
        this.idNumber = idNumber;
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        int totalMatch = 0;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        if (this.id == student.id) totalMatch++;
        if (Objects.equals(this.idType, student.idType) && Objects.equals(this.idNumber, student.idNumber))
            totalMatch++;
        if (Objects.equals(this.dob, student.dob)) totalMatch++;

        return totalMatch >= 2;
    }

}
