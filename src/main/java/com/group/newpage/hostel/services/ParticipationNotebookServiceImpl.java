package com.group.newpage.hostel.services;

import com.group.newpage.hostel.daos.ParticipationNotebookRepository;
import com.group.newpage.hostel.helper.StudentParticipation;
import com.group.newpage.hostel.models.ParticipationNotebook;
import com.group.newpage.hostel.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ParticipationNotebookServiceImpl implements ParticipationNotebookService {

    private final ParticipationNotebookRepository participationNotebookRepository;

    @Autowired
    public ParticipationNotebookServiceImpl(ParticipationNotebookRepository participationNotebookRepository) {
        this.participationNotebookRepository = participationNotebookRepository;
    }

    @Override
    @Transactional
    public void addNewParticipationNotebook(final List<StudentParticipation>studentParticipations, final List<Student> students) throws InterruptedException {

        Thread.sleep(10);
        final String nameParticipationNotebook="gatnashyk "+new Date();

        ParticipationNotebook savedParticipationNotebook=ParticipationNotebook.builder()
                .name(nameParticipationNotebook)
                .students(students)
                .build();
        participationNotebookRepository.save(savedParticipationNotebook);
        ParticipationNotebook participationNotebook=participationNotebookRepository.findParticipationNotebookByName(nameParticipationNotebook);

        if(participationNotebook!=null){
            for(StudentParticipation studentParticipation:studentParticipations){
                participationNotebookRepository.updateColumnStatusInTableParticipationNotebookStudents(studentParticipation.isStatus(),
                        participationNotebook.getId(),studentParticipation.getId());
            }
        }

        return;
    }

    @Override
    @Transactional
    public void editParticipationNotebookById(final ParticipationNotebook participationNotebook, final List<Student>students){

        ParticipationNotebook editedParticipationNotebook=participationNotebookRepository.findParticipationNotebookById(
                participationNotebook.getId());

        if(editedParticipationNotebook==null){

            return;
        }
        if(participationNotebook.getName()!=null && !participationNotebook.getName().isEmpty()){

            editedParticipationNotebook.setName(participationNotebook.getName());
        }
        if(students!=null && !students.isEmpty()){

            editedParticipationNotebook.setStudents(students);
        }

        participationNotebookRepository.save(editedParticipationNotebook);

        return;
    }

    @Override
    @Transactional
    public void removeParticipationNotebookById(final int participationNotebookId){

        if(participationNotebookRepository.findParticipationNotebookById(participationNotebookId)==null){

            return;
        }
        participationNotebookRepository.deleteById(participationNotebookId);

        return;
    }

}
