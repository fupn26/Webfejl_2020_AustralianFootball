package hu.unideb.fupn26.model;

import lombok.*;

@AllArgsConstructor
@Builder
@ToString
@Getter
@EqualsAndHashCode
public class MatchStat {

    private String matchId;
    private Integer playerId;
    private Integer teamId;
    private Integer goals;
    private Integer behinds;


    private Integer kicks;
    private Integer marks;
    private Integer handballs;
    private Integer disposals;
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

    public MatchStat(String matchId, Integer playerId) {
        this.matchId = matchId;
        this.playerId = playerId;
    }
}
