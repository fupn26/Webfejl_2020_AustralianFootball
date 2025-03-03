package hu.unideb.fupn26.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="players", schema="AustralianFootball")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "pid")
    private Integer id;

    @Column(name = "dob")
    private LocalDateTime birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private int height;

    private int weight;
}
