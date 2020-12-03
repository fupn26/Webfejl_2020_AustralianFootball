package hu.unideb.fupn26.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {

    private Integer season;
    private String round;
    private String team1;
    private String team2;
    private String team1Location;
    private String team2Location;
    private int yearOfStart;
    private int monthOfStart;
    private int dayOfStart;
    private int hourOfStart;
    private int minuteOfStart;
    private int secondOfStart;
    private String venue;
    private Integer attendants;
    private Integer margin;
    private String winnerTeam;
    private Integer winnerScore;
    private String winnerLocation;
    private String loserTeam;
    private Integer loserScore;
    private String loserLocation;
    private Integer homeScore;
    private Integer homeQ1Goals;
    private Integer homeQ2Goals;
    private Integer homeQ3Goals;
    private Integer homeQ4Goals;
    private Integer homeExtraTimeGoals;
    private Integer homeQ1Behinds;
    private Integer homeQ2Behinds;
    private Integer homeQ3Behinds;
    private Integer homeQ4Behinds;
    private Integer homeExtraTimeBehinds;
    private Integer awayScore;
    private Integer awayQ1Goals;
    private Integer awayQ2Goals;
    private Integer awayQ3Goals;
    private Integer awayQ4Goals;
    private Integer awayExtraTimeGoals;
    private Integer awayQ1Behinds;
    private Integer awayQ2Behinds;
    private Integer awayQ3Behinds;
    private Integer awayQ4Behinds;
    private Integer awayExtraTimeBehinds;
}
