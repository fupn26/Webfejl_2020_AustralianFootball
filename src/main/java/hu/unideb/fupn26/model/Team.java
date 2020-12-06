package hu.unideb.fupn26.model;

import lombok.*;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Team {

    Integer id;
    String teamName;

    public Team(int id) {
        this.id = id;
    }

    public Team(String teamName) {
        this.teamName = teamName;
    }
}
