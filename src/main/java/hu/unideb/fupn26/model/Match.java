package hu.unideb.fupn26.model;

import lombok.*;

import javax.persistence.Column;
import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@ToString
@Getter
@EqualsAndHashCode
public class Match {

    private Integer season;
    private String round;
    private String team1;
    private String team2;
    private String team1Location;
    private String team2Location;
    private Timestamp startDate;
    private String venue;
    private Integer attendants;
    private Integer margin;
    private String winnerTeam;
    private Integer winnerScore;
    private String winnerLocation;
    private String loserTeam;
    private Integer loserScore;
    private String loserLocation;
    private String homeTeam;
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
    private String awayTeam;
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
