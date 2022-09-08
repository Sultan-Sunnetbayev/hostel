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
    private String imagePath;
    private Boolean hostel;
    private Integer trainingYear;
    private Gender gender;
    private Boolean status;

    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymicName='" + patronymicName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", hostel=" + hostel +
                ", trainingYear=" + trainingYear +
                ", gender=" + gender +
                ", status=" + status +
                '}';
    }
}
