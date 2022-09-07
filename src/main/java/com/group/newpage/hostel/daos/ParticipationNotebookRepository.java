package com.group.newpage.hostel.daos;

import com.group.newpage.hostel.models.ParticipationNotebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationNotebookRepository extends JpaRepository<ParticipationNotebook, Integer> {

    @Query("SELECT participationNotebook FROM ParticipationNotebook participationNotebook WHERE " +
            "participationNotebook.id = :participationNotebookId")
    ParticipationNotebook findParticipationNotebookById(@Param("participationNotebookId")int participationNotebookId);

    @Query("SELECT participationNotebook FROM ParticipationNotebook participationNotebook WHERE " +
            "participationNotebook.name = :nameParticipationNotebook")
    ParticipationNotebook findParticipationNotebookByName(@Param("nameParticipationNotebook")String nameParticipationNotebook);

    @Query(nativeQuery = true, value = "UPDATE participation_notebook_students SET status = :status WHERE " +
            "participation_notezbook_id = :participationNotebookId AND student_id = :studentId")
    void updateColumnStatusInTableParticipationNotebookStudents(@Param("status")boolean status,
                                                                @Param("participationNotebookId")int participationNotebookId,
                                                                @Param("studentId")int studentId);

}
