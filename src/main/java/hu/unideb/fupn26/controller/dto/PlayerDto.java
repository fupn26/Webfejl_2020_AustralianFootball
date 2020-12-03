package hu.unideb.fupn26.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;
    private String firstName;
    private String lastName;
}
