package hu.unideb.fupn26.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class MatchStat {

    private String playerFirstName;
    private String playerLastName;
    private String playerTeam;
    private String opponentTeam;
    private Integer season;
    private String round;
    private String location;
    private Integer kicks;
    private Integer marks;
    private Integer handballs;
    private Integer disposals;
    private Integer goals;
    private Integer behinds;
    private Integer hitOuts;
    private Integer tackles;
    private Integer rebound50s;
    private Integer inside50s;
    private Integer clearances;
    private Integer clangers;
    private Integer freeKicksFor;
    private Integer freeKicksAgainst;
    private Integer brownlowVotes;
    private Integer contestedPossessions;
    private Integer uncontestedPossessions;
    private Integer contestedMarks;
    private Integer marksInside50;
    private Float onePercenters;
    private Integer bounces;
    private Integer goalAssist;
    private Float percentageOfGamePlayed;
}
