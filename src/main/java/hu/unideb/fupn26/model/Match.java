package hu.unideb.fupn26.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@ToString
@Getter
@EqualsAndHashCode
public class Match {

    private String id;

    private Integer season;
    private MatchRound round;
    private String team1;
    private String team2;
    private MatchLocation team1Location;
    private MatchLocation team2Location;
    private Integer team1Score;
    private Integer team2Score;

    private LocalDateTime startDate;
    private String venue;
    private Integer attendants;
    private Integer margin;
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

    public Match(String id) {
        this.id = id;
    }
}
