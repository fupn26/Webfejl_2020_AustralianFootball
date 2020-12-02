package hu.unideb.fupn26.model;

import lombok.*;

import javax.persistence.Column;
import java.sql.Timestamp;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Match {

    @NonNull
    private Integer season;
    @NonNull
    private String round;
    @NonNull
    private String team1;
    @NonNull
    private String team2;
    @NonNull
    private String team1Location;
    @NonNull
    private String team2Location;
    private Timestamp startDate;
    private String venue;
    private Integer attendants;
    private Integer margin;
    @NonNull
    private String winnerTeam;
    @NonNull
    private Integer winnerScore;
    private String winnerLocation;
    @NonNull
    private String loserTeam;
    @NonNull
    private Integer loserScore;
    private String loserLocation;
    @NonNull
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
    @NonNull
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
