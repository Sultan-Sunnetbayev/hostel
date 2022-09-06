package com.group.newpage.hostel.controllers;

import com.group.newpage.hostel.dtos.StudentDTO;
import com.group.newpage.hostel.models.Student;
import com.group.newpage.hostel.models.User;
import com.group.newpage.hostel.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/add/student", produces = "application/json")
    public ResponseEntity addStudent(final @ModelAttribute Student student,
                                     final @RequestParam(value = "image",required = false)MultipartFile image){

        Map<String,Object> response=new LinkedHashMap<>();

        if(student.getPatronymicName()!=null && !student.getPatronymicName().isEmpty()){

            if(studentService.isStudentExistsByNameAndSurnameAndPatronymicNameAndTrainingYear(student)){
                response.put("status",false);
                response.put("message","error this student already exists");

                return ResponseEntity.ok(response);
            }
        }
        if(studentService.isStudentExistsByNameAndSurnameAndTrainingYear(student)){
            response.put("status",false);
            response.put("message","error this student already exists");

            return ResponseEntity.ok(response);
        }
        studentService.addStudent(student,image);
        if(studentService.isStudentExistsByNameAndSurnameAndTrainingYear(student)){
            response.put("status",true);
            response.put("message","accept student successful added");
        }else{
            response.put("status",false);
            response.put("message","error student don't added");
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/remove/student/by/id", produces = "application/json")
    public ResponseEntity removeStudentById(final @RequestParam("studentId")int studentId){

        Map<String,Object>response=new LinkedHashMap<>();

        if(!studentService.isStudentExistsById(studentId)){
            response.put("status",false);
            response.put("message","error student not found with this id");
        }
        studentService.removeStudentById(studentId);
        if(!studentService.isStudentExistsById(studentId)){
            response.put("status",true);
            response.put("message","accept student successful removed");
        }else{
            response.put("status",false);
            response.put("message","error student don't removed");
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/edit/student/by/id", produces = "application/json")
    public ResponseEntity editStudentById(final @ModelAttribute Student student,
                                          final @RequestParam(value = "image",required = false)MultipartFile image){

        Map<String,Object>response=new LinkedHashMap<>();

        if(studentService.isStudentExistsById(student.getId())){
            response.put("status",false);
            response.put("message","error student not found with this id");

            return ResponseEntity.ok(response);
        }
        studentService.editStudentById(student,image);
        if(studentService.isStudentExistsByNameAndSurnameAndTrainingYear(student)){
            response.put("status",true);
            response.put("message","accpet student successful edited");
        }else{
            response.put("status",false);
            response.put("message","error student don't edited");
        }

        return ResponseEntity.ok(response);
    }
    @GetMapping(path = "/get/student", produces = "application/json")
    public ResponseEntity getStudentByParameter(@RequestParam(value = "trainingYear", required = false)Integer trainingYear,
                                                @RequestParam(value = "hostel", required = false)Boolean hostel,
                                                @RequestParam(value = "search",required = false)String search){

        List<StudentDTO> studentDTOS=studentService.getStudentDTOSByParameter(trainingYear, hostel, search);
        Map<String, Object>response=new LinkedHashMap<>();

        if(studentDTOS==null){

            studentDTOS=new ArrayList<>();
        }
        response.put("status",true);
        response.put("data",studentDTOS);

        return ResponseEntity.ok(response);
    }

}
