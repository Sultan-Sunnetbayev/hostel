package com.group.newpage.hostel.daos;

import com.group.newpage.hostel.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT student FROM Student student WHERE LOWER(student.name) = LOWER(:name) AND " +
            "LOWER(student.surname) = LOWER(:surname) AND LOWER(student.patronymicName) = LOWER(:patronymicName) AND " +
            "student.trainingYear = :trainingYear")
    Student findStudentByNameAndSurnameAndPatronymicNameAndTrainingYear(@Param("name")String name,
                                                                         @Param("surname")String surname,
                                                                         @Param("patronymicName")String patronymicName,
                                                                         @Param("trainingYear")int trainingYear);

    @Query("SELECT student FROM Student student WHERE LOWER(student.name) = LOWER(:name) AND " +
            "LOWER(student.surname) = LOWER(:surname) AND student.trainingYear = :trainingYear")
    Student findStudentByNameAndSurnameAndTrainingYear(@Param("name")String name,
                                                       @Param("surname")String surname,
                                                       @Param("trainingYear")int trainingYear);

    @Query("SELECT student FROM Student student WHERE student.id = :studentId")
    Student findStudentById(@Param("studentId")int studentId);

    @Query("SELECT student FROM Student student WHERE student.trainingYear = :trainingYear")
    List<Student> findStudentsByTrainingYear(@Param("trainingYear")int trainingYear);

    @Query("SELECT student FROM Student student WHERE student.hostel = :hostel")
    List<Student> findStudentByHostel(@Param("hostel")boolean hostel);

    @Query("SELECT student FROM Student student WHERE LOWER(student.name) LIKE '%'+LOWER(:search)+'%' OR " +
            "LOWER(student.surname) LIKE '%'+LOWER(:search)+'%' OR " +
            "((student.patronymicName IS NOT NULL) AND (student.patronymicName LIKE '%'+LOWER(:search)+'%'))")
    List<Student>findStudentsByNameLikeOrSurnameLikeOrPatronymicNameIsNotNullAndPatronymicNameLike(@Param("search")String search);

}
