package com.group.newpage.hostel.services;

import com.group.newpage.hostel.daos.ParticipationNotebookRepository;
import com.group.newpage.hostel.models.ParticipationNotebook;
import com.group.newpage.hostel.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void addNewParticipationNoteboook(final ParticipationNotebook participationNotebook, final List<Student> students){

        ParticipationNotebook savedParticipationNotebook=ParticipationNotebook.builder()
                .name(participationNotebook.getName())
                .students(students)
                .build();
        participationNotebookRepository.save(savedParticipationNotebook);

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
