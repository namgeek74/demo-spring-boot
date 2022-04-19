package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent() {
        return  studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException("this student does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void editStudent(Student student) {
        Student currentStudent = studentRepository
                .findById(student.getId())
                .orElseThrow(
                        () -> new IllegalStateException("this student does not exist")
                );
        if (student.getName() != null) {
            currentStudent.setName(student.getName());
        }
        if (student.getEmail() != null) {
            currentStudent.setEmail(student.getEmail());
        }
        if (student.getAge() != null) {
            currentStudent.setAge(student.getAge());
        }
    }
}
