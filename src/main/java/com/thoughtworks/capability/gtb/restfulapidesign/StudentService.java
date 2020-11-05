package com.thoughtworks.capability.gtb.restfulapidesign;

import com.thoughtworks.capability.gtb.restfulapidesign.model.CreateStudentRequest;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Gender;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private Map<Integer, Student> studentMap = new HashMap<>();
    private Map<Integer, Group> groupMap = new HashMap<>();

    public StudentService() {
        studentMap.put(1, new Student(1, "Alice", Gender.FEMALE, "Alice Chen"));
        studentMap.put(2, new Student(2, "Bryan", Gender.MALE, "Bryan Li"));
        studentMap.put(3, new Student(3, "Chris", Gender.MALE, "Chris Wu"));
        studentMap.put(4, new Student(4, "Danielle", Gender.FEMALE, "Danielle Jiang"));
        studentMap.put(5, new Student(5, "Eliza", Gender.FEMALE, "Eliza Brand"));
        studentMap.put(6, new Student(6, "Fiona", Gender.FEMALE, "Fiona Shrek"));
        studentMap.put(7, new Student(7, "George", Gender.MALE, "George Lin"));
        studentMap.put(8, new Student(8, "Hudson", Gender.FEMALE, "Hudson Gao"));

        groupMap.put(1, new Group(1, "Group 1", new ArrayList<Student>(), ""));
        groupMap.put(2, new Group(2, "Group 2", new ArrayList<Student>(), ""));
        groupMap.put(3, new Group(3, "Group 3", new ArrayList<Student>(), ""));
        groupMap.put(4, new Group(4, "Group 4", new ArrayList<Student>(), ""));
        groupMap.put(5, new Group(5, "Group 5", new ArrayList<Student>(), ""));
        groupMap.put(6, new Group(6, "Group 6", new ArrayList<Student>(), ""));
    }

    public Student addStudent(CreateStudentRequest createStudentRequest) {
        Integer newId = studentMap.size() + 1;
        studentMap.put(newId, new Student(newId, createStudentRequest));
        return studentMap.get(newId);
    }

    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();

        for (Student student : studentMap.values()) {
            students.add(student);
        }
        return students;
    }

    public Student findStudent(String name) {
        for (Student student : studentMap.values()) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getStudentsByGender(Gender gender) {
        List<Student> studentsByGender = new ArrayList<>();

        for (Student student : studentMap.values()) {
            if (student.getGender().equals(gender)) {
                studentsByGender.add(student);
            }
        }
        return studentsByGender;
    }

    public void deleteStudent(Integer id) {
        studentMap.remove(id);
    }

    public void update(Integer id, Student student) {
        studentMap.put(id, student);
    }

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();

        for (Group group : groupMap.values()) {
            groups.add(group);
        }

        return groups;
    }

    public List<Group> shuffleGroups() {
        List<Student> students = getAll();
        Collections.shuffle(students);

        int iterator = 1;
        for (Student student : students) {
            if (iterator == 7) {
                iterator -= 6;
            }
            Group group = groupMap.get(iterator);
            List<Student> studentGroup = group.getMembers();
            studentGroup.add(student);
            group.setMembers(studentGroup);
            groupMap.put(iterator, group);
            iterator++;
        }

        return getAllGroups();
    }

    public Group updateGroup(Integer id, String groupName) {
        Group group = groupMap.get(id);
        group.setName(groupName);
        groupMap.put(id, group);
        return group;
    }

    public Group getGroup(Integer id) {
        return groupMap.get(id);
    }
}
