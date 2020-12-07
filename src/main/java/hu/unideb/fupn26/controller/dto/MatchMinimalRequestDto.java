package hu.unideb.fupn26.controller.dto;

import hu.unideb.fupn26.model.MatchLocation;
import hu.unideb.fupn26.model.MatchRound;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MatchMinimalRequestDto {

    private Integer season;
    private MatchRound round;
    private String team1;
    private String team2;
    private MatchLocation team1Location;
    private MatchLocation team2Location;
    private Integer team1Score;
    private Integer team2Score;
}
