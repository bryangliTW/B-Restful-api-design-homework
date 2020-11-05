package com.thoughtworks.capability.gtb.restfulapidesign.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Integer id;
    private String name;
    private Gender gender;
    private String note;

    public Student(Integer id, CreateStudentRequest createStudentRequest) {
        this.id = id;
        this.name = createStudentRequest.getName();
        this.gender = createStudentRequest.getGender();
        this.note = createStudentRequest.getNote();
    }
}
