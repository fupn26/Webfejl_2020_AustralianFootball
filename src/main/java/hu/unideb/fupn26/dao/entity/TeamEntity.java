package hu.unideb.fupn26.dao.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="teams", schema="AustralianFootball")
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tid")
    private Integer id;

    @Column(name = "tname")
    private String name;
}
