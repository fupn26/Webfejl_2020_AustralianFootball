package hu.unideb.fupn26.model;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
@Builder
public class Player {

    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;
    private String firstName;
    private String lastName;
    private int height;
    private int weight;
}
