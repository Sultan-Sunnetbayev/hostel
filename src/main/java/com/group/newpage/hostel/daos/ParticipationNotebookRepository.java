package com.group.newpage.hostel.daos;

import com.group.newpage.hostel.models.ParticipationNotebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationNotebookRepository extends JpaRepository<ParticipationNotebook, Integer> {
}
