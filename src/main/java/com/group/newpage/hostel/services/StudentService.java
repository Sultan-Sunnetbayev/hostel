package com.group.newpage.hostel.services;

import com.group.newpage.hostel.models.Student;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

public interface StudentService {

    @Transactional
    void addStudent(Student student, MultipartFile image);

    boolean isStudentExistsByNameAndSurnameAndTrainingYear(Student student);

    boolean isStudentExistsByNameAndSurnameAndPatronymicNameAndTrainingYear(Student student);

    @Transactional
    void editStudentById(Student student, MultipartFile image);

    @Transactional
    void removeStudentById(int studentId);
}
