package com.thoughtworks.capability.gtb.restfulapidesign;

import com.thoughtworks.capability.gtb.restfulapidesign.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this. studentService = studentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return studentService.addStudent(createStudentRequest);
    }

    @GetMapping("/")
    public List<Student> getStudents() {
        return studentService.getAll();
    }

    @GetMapping("/{name}")
    public Student findStudent(@PathVariable("name") String name) {
        return studentService.findStudent(name);
    }

    @GetMapping
    public List<Student> getStudentsByGender(@RequestParam(value="gender") Gender gender) {
        return studentService.getStudentsByGender(gender);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        studentService.update(id, student);
    }

    @GetMapping("/groups")
    public List<Group> getGroups() {
        return studentService.getAllGroups();
    }

    @GetMapping("/groups/shuffle")
    public List<Group> shuffleGroups() {
        return studentService.shuffleGroups();
    }

    @PatchMapping("/groups/{id}")
    public Group updateGroup(@PathVariable Integer id, @RequestBody ChangeGroupNameRequest changeGroupNameRequest) {
        return studentService.updateGroup(id, changeGroupNameRequest.getName());
    }

    @GetMapping("/groups/{id}")
    public Group getGroup(@PathVariable Integer id) {
        return studentService.getGroup(id);
    }

}
