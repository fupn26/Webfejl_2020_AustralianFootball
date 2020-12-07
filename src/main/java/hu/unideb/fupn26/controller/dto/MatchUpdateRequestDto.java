package hu.unideb.fupn26.controller.dto;

import hu.unideb.fupn26.model.MatchLocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MatchUpdateRequestDto {

    private String id;
    private MatchLocation team1Location;
    private MatchLocation team2Location;
    private Integer team1Score;
    private Integer team2Score;
    private String startDate;
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
}
