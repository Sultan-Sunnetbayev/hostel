package com.group.newpage.hostel.services;

import com.group.newpage.hostel.daos.StudentRepository;
import com.group.newpage.hostel.helper.FileUploadUtil;
import com.group.newpage.hostel.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Value("${image.path}")
    private String imagePath;
    @Value("${path.default.image.user}")
    private String pathDefaultImageUser;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public void addStudent(final Student student, final MultipartFile image){

        if(student.getPatronymicName()!=null && !student.getPatronymicName().isEmpty()){

            if(isStudentExistsByNameAndSurnameAndPatronymicNameAndTrainingYear(student)){

                return;
            }
        }else if(isStudentExistsByNameAndSurnameAndTrainingYear(student)){

            return;
        }
        Student savedStudent=Student.builder()
                .name(student.getName())
                .surname(student.getSurname())
                .patronymicName(student.getPatronymicName())
                .trainingYear(student.getTrainingYear())
                .gender(student.getGender())
                .hostel(student.getHostel())
                .build();

        if(image!=null && !image.isEmpty()){

            final String fileName= UUID.randomUUID().toString()+image.getOriginalFilename();

            try {

                FileUploadUtil.loadFile(imagePath, fileName, image);
                savedStudent.setImagePath(imagePath+"/"+fileName);

            } catch (IOException ioException) {

                ioException.printStackTrace();
            }
        }else{
            File defaultImage=new File(pathDefaultImageUser);

            if(defaultImage.exists()){

                final String fileName=UUID.randomUUID().toString()+defaultImage.getName();

                try {

                    FileUploadUtil.saveDefaultImageUser(imagePath, fileName, defaultImage);
                    savedStudent.setImagePath(imagePath+"/"+fileName);

                } catch (IOException ioException) {

                    ioException.printStackTrace();
                }
            }
        }
        studentRepository.save(savedStudent);

        return;
    }

    @Override
    public boolean isStudentExistsByNameAndSurnameAndTrainingYear(Student student) {

        if(studentRepository.findStudentByNameAndSurnameAndTrainingYear(student.getName(),
                student.getSurname(), student.getTrainingYear())!=null){

            return true;
        }else{

            return false;
        }
    }

    @Override
    public boolean isStudentExistsByNameAndSurnameAndPatronymicNameAndTrainingYear(Student student) {

        if(studentRepository.findStudentByNameAndSurnameAndPatronymicNameAndTrainingYear(student.getName(),
                student.getSurname(), student.getPatronymicName(), student.getTrainingYear())!=null){

            return true;
        }else{

            return false;
        }
    }

    @Override
    @Transactional
    public void editStudentById(final Student student, final MultipartFile image){

        Student editedStudent= studentRepository.findStudentById(student.getId());

        if(editedStudent==null){

            return;
        }
        Student check=null;
        if(student.getPatronymicName()!=null && !student.getPatronymicName().isEmpty()){

            check=studentRepository.findStudentByNameAndSurnameAndPatronymicNameAndTrainingYear(student.getName(),
                    student.getSurname(), student.getPatronymicName(), student.getTrainingYear());
        }else{
            check= studentRepository.findStudentByNameAndSurnameAndTrainingYear(student.getName(),
                    student.getSurname(), student.getTrainingYear());
        }
        if(check!=null && check.getId()!=editedStudent.getId()){

            return;
        }
        if(student.getName()!=null && !student.getName().isEmpty()){
            editedStudent.setName(student.getName());
        }
        if(student.getSurname()!=null && !student.getSurname().isEmpty()){
            editedStudent.setSurname(student.getSurname());
        }
        if(student.getPatronymicName()!=null && !student.getPatronymicName().isEmpty()){
            editedStudent.setPatronymicName(student.getPatronymicName());
        }
        if(student.getGender()!=null){
            editedStudent.setGender(student.getGender());
        }
        if(student.getTrainingYear()!=null){
            editedStudent.setTrainingYear(student.getTrainingYear());
        }
        if(student.getHostel()!=null){
            editedStudent.setHostel(student.getHostel());
        }
        if(image!=null && !image.isEmpty()){
            File lastImage=new File(editedStudent.getImagePath());

            if(lastImage.exists()){
                lastImage.delete();
            }
            final String fileName=UUID.randomUUID().toString()+image.getOriginalFilename();

            try {

                FileUploadUtil.loadFile(imagePath, fileName, image);
                editedStudent.setImagePath(imagePath+"/"+fileName);

            } catch (IOException ioException) {

                ioException.printStackTrace();
            }
        }

        studentRepository.save(editedStudent);

        return;
    }

    @Override
    @Transactional
    public void removeStudentById(final int studentId){

        Student student=studentRepository.findStudentById(studentId);

        if(student==null){

            return;
        }
        if(student.getImagePath()!=null && !student.getImagePath().isEmpty()){
            File image=new File(student.getImagePath());

            if(image.exists()){
                image.delete();
            }
        }
        studentRepository.deleteById(studentId);

        return;
    }

}
