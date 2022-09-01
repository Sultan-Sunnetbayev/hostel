package com.group.newpage.hostel.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipationNotebookDTO {

    private int id;
    private List<StudentDTO> studentDTOS;

    @Override
    public String toString() {
        return "ParticipationNotebookDTO{" +
                "id=" + id +
                ", studentDTOS=" + studentDTOS +
                '}';
    }

}
