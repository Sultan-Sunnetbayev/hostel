package com.group.newpage.hostel.services;

import com.group.newpage.hostel.models.ParticipationNotebook;
import com.group.newpage.hostel.models.Student;

import javax.transaction.Transactional;
import java.util.List;

public interface ParticipationNotebookService {
    @Transactional
    void addNewParticipationNoteboook(ParticipationNotebook participationNotebook, List<Student> students);

    @Transactional
    void editParticipationNotebookById(ParticipationNotebook participationNotebook, List<Student>students);

    @Transactional
    void removeParticipationNotebookById(int participationNotebookId);
}
