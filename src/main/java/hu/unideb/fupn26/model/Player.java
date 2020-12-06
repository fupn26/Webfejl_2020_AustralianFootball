package hu.unideb.fupn26.model;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Player {

    private Integer id;
    private LocalDateTime birthOfDate;
    private String firstName;
    private String lastName;
    private int height;
    private int weight;

    public Player(int id) {
        this.id = id;
    }

    public Player(LocalDateTime birthOfDate, String firstName, String lastName, int height, int weight) {
        this.birthOfDate = birthOfDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
    }
}
