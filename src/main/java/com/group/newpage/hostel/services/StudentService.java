package com.group.newpage.hostel.services;

import com.group.newpage.hostel.dtos.StudentDTO;
import com.group.newpage.hostel.models.Student;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentService {

    @Transactional
    void addStudent(Student student, MultipartFile image);

    boolean isStudentExistsByNameAndSurnameAndTrainingYear(Student student);

    boolean isStudentExistsByNameAndSurnameAndPatronymicNameAndTrainingYear(Student student);

    @Transactional
    void editStudentById(Student student, MultipartFile image);

    @Transactional
    void removeStudentById(int studentId);

    List<StudentDTO> getAllStudentDTOS();

    boolean isStudentExistsById(int studentId);

    List<StudentDTO>getStudentDTOSByParameter(Integer trainingYear, Boolean hostel, String search);

    Student getStudentById(int studentId);
}
