package hu.unideb.fupn26.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import java.sql.Timestamp;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Match {

    private int season;
    private String round;
    private String team1;
    private String team2;
    private String team1Location;
    private String team2Location;
    private Timestamp startDate;
    private String venue;
    private int attendants;
    private int margin;
    private String winnerTeam;
    private int winnerScore;
    private String winnerLocation;
    private String loserTeam;
    private int loserScore;
    private String loserLocation;
    private String homeTeam;
    private int homeScore;
    private int homeQ1Goals;
    private int homeQ2Goals;
    private int homeQ3Goals;
    private int homeQ4Goals;
    private int homeExtraTimeGoals;
    private int homeQ1Behinds;
    private int homeQ2Behinds;
    private int homeQ3Behinds;
    private int homeQ4Behinds;
    private int homeExtraTimeBehinds;
    private int awayTeam;
    private int awayScore;
    private int awayQ1Goals;
    private int awayQ2Goals;
    private int awayQ3Goals;
    private int awayQ4Goals;
    private int awayExtraTimeGoals;
    private int awayQ1Behinds;
    private int awayQ2Behinds;
    private int awayQ3Behinds;
    private int awayQ4Behinds;
    private int awayExtraTimeBehinds;
}
