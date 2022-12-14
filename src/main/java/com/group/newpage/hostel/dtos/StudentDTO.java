package com.group.newpage.hostel.dtos;

import com.group.newpage.hostel.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

    private int id;
    private String name;
    private String surname;
    private String patronymicName;
    private String email;
    private String imagePath;
    private Boolean hostel;
    private Integer trainingYear;
    private Gender gender;
    private Boolean status;
    private Date created;
    private Date updated;

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymicName='" + patronymicName + '\'' +
                ", email='" + email + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", hostel=" + hostel +
                ", traingYear=" + trainingYear +
                ", gender=" + gender +
                ", status=" + status +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }

}
