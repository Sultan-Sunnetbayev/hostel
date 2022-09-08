package com.group.newpage.hostel.controllers;

import com.group.newpage.hostel.helper.StudentParticipation;
import com.group.newpage.hostel.models.Student;
import com.group.newpage.hostel.services.ParticipationNotebookService;
import com.group.newpage.hostel.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController("/api/v1/participationNotebook")
public class ParticipationNotebookController {

    private final ParticipationNotebookService participationNotebookService;
    private final StudentService studentService;

    @Autowired
    public ParticipationNotebookController(ParticipationNotebookService participationNotebookService,
                                           StudentService studentService) {
        this.participationNotebookService = participationNotebookService;
        this.studentService = studentService;
    }

    @PostMapping(path = "/add/new/participationNotebook", produces = "application/json")
    public ResponseEntity addNewParticipationNotebook(final @RequestParam("StudentParticipation") List<StudentParticipation> studentParticipations) throws InterruptedException {

        List<Student>students=new LinkedList<>();

        for(StudentParticipation studentParticipation:studentParticipations){
            Student student=studentService.getStudentById(studentParticipation.getId());

            if(student!=null){
                students.add(student);
            }
        }
        participationNotebookService.addNewParticipationNotebook(studentParticipations,students);

        return null;
    }
}
