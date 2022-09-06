package com.group.newpage.hostel.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class Student implements Comparable<Student>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotBlank(message = "student name is mandatory")
    @NotEmpty(message = "student name is empty")
    private String name;
    @Column(name = "surname")
    @NotBlank(message = "student surname is mandatory")
    @NotEmpty(message = "student surname is empty")
    private String surname;
    @Column(name = "patronymic_name")
    private String patronymicName;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "training_year")
    @NotBlank(message = "training year is mandatory")
    @NotEmpty(message = "training year is empty")
    private Integer trainingYear;
    @Column(name = "hostel")
    private Boolean hostel;
    @Column(name = "created")
    @CreationTimestamp
    private Date created;
    @Column(name = "updated")
    @UpdateTimestamp
    private Date updated;

    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ParticipationNotebook> participationNotebooks;


    @Override
    public int compareTo(Student student) {

        return this.getId()<=student.getId()? -1 : 1;
    }

}
