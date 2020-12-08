package hu.unideb.fupn26.controller.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequestDto {

    private String birthOfDate;
    private String firstName;
    private String lastName;
    private Integer height;
    private Integer weight;
}
